package com.neo.consult.consult.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.neo.security.CustomConsultDetails;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import com.neo.common.vo.ConsultInfoVO;
import com.neo.common.vo.ConsultMemberVO;
import com.neo.mappers.CommonMapper;
import com.neo.mappers.ConsultInfoMapper;
import com.neo.util.UtilCrypt;
import com.neo.util.UtilJsonResult;

@Service("requestListService")
@Transactional
public class RequestListServiceImpl implements RequestListService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MessageSource messageSource;
	@Autowired LocaleResolver localeResolver;

	@Resource(name = "consultInfoMapper")
    private ConsultInfoMapper consultInfoMapper;
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;

	//DAmo 암호,복호화 모듈의 설정파일 주입
	@Value("${damo.inifilepath}")
	private String iniEncDecFilePath;

	
	@Override
	public List<ConsultInfoVO> consultInfoList(ConsultInfoVO paramVO) throws Exception {
		
		//휴대폰 복호화
		paramVO.setPhoneNumber(UtilCrypt.ScpEncStr( paramVO.getPhoneNumber(), iniEncDecFilePath) );
		
		List<ConsultInfoVO> resultList = consultInfoMapper.consultInfoList(paramVO);

		//리스트출력을 위한 데이터 포맷팅
		ConsultInfoVO re = new ConsultInfoVO();
		String regex = "";
		String repl = "";
		for(int i=0; i<resultList.size(); i++) {
			re = resultList.get(i);

	    	//실명번호 (주민등록) 복호화
			re.setCUST_REGI_NO(UtilCrypt.ScpDecStr( re.getCUST_REGI_NO(), iniEncDecFilePath) );
			//휴대폰 복호화
			re.setCUST_HP_NO(UtilCrypt.ScpDecStr( re.getCUST_HP_NO(), iniEncDecFilePath) );
			//실명번호 (생년월일) - 생년월일 6자리 이상 일때만 yy.mm.dd 로 변환
			if ( re.getCUST_REGI_NO().length() >= 6 ) {
				regex = "^(\\d{6})(\\d+)";
				repl = re.getCUST_REGI_NO().replaceFirst( regex, "$1-$2" );
				re.setCUST_REGI_NO( repl );
			}
			//휴대폰번호 포매팅 - 휴대폰번호 가 7자리 이상 일때만 xxx-xxxx-xxxx 처리
			if( re.getCUST_HP_NO().length() >= 7 ) {
				regex = "^(\\d{3})(\\d{4})(\\d+)";
				repl = re.getCUST_HP_NO().replaceFirst( regex, "$1-$2-$3" );
				re.setCUST_HP_NO( repl );
			}
			//완료일 포맷팅
			if( re.getCOMP_DATE() != null && re.getCOMP_DATE().length() >= 6 ) {
				regex = "^(\\d{4})(\\d{2})(\\d+)";
				repl = re.getCOMP_DATE().replaceFirst( regex, "$1-$2-$3");
				re.setCOMP_DATE( repl );
			}

		}

		return resultList;
	}
	
	@Override
	public ConsultInfoVO consultStatsCount(ConsultInfoVO paramVO) throws Exception {
		return consultInfoMapper.consultStatsCount(paramVO);
	}
	
	
	@Override
	public ConsultInfoVO consultInfoDetail(ConsultInfoVO paramVO) throws Exception {

		ConsultInfoVO result = consultInfoMapper.consultInfoDetail(paramVO);

		String HpRegex = "^(\\d{3})(\\d{4})(\\d+)";
		String birthRegex = "^(\\d{6})(\\d+)";
		String corpRegex = "^(\\d{3})(\\d{2})(\\d+)";
		String SSN7 = "";                           // 주민번호 7번째자리
		String dec = "";
		String repl = "";

		// 주민번호 7번째: 1, 3, 9 (대한민국 남자) / 5, 7 (외국인 남자)
		Set<String> genderMSet = new HashSet<>( Arrays.asList("1", "3", "5", "7", "9") );
		// 주민번호 7번째: 5, 6, 7, 8 외국인
		Set<String> FrnSet = new HashSet<>( Arrays.asList("5", "6", "7", "8") );

		//휴대폰 포맷팅  CUST_HP_NO_FM
		if( result.getCUST_HP_NO() != null && result.getCUST_HP_NO().length() >= 7 ) {
			dec = UtilCrypt.ScpDecStr( result.getCUST_HP_NO(), iniEncDecFilePath );
			repl = dec.replaceFirst( HpRegex, "$1-$2-$3" );
			result.setCUST_HP_NO( dec );
			result.setCUST_HP_NO_FM( repl );
		}
		// 생년월일 포맷팅
		if( result.getCUST_REGI_NO() != null && result.getCUST_REGI_NO().length() >= 6) {
			dec = UtilCrypt.ScpDecStr( result.getCUST_REGI_NO() , iniEncDecFilePath );
			repl = dec.replaceFirst( birthRegex, "$1-$2" );
			result.setCUST_REGI_NO( dec );
			result.setCUST_REGI_NO_FM( repl );
		}
		// 성별 매핑
		SSN7 = dec.substring(6, 7);
		if( genderMSet.contains( SSN7 ) ) {
			result.setGENDER("남자");
		} else {
			result.setGENDER("여자");
		}
		// 내/외국인 매핑
		if( FrnSet.contains( SSN7 )  ) {
			result.setFRN_CHK("외국인");
			;           } else {
			result.setFRN_CHK("내국인");
		}
		// 사업자번호 포맷팅 .. ex) 000-00-00000
		if(result.getCUST_CORP_NO() != null && result.getCUST_CORP_NO().length() >= 5) {
			repl = result.getCUST_CORP_NO().replaceFirst( corpRegex, "$1-$2-$3" );
			result.setCUST_CORP_NO_FM( repl );
		}
//		// 업력 포맷팅
//		if ( result.getCORP_HIS() == "" || result.getCORP_HIS() == "0" || result.getCORP_HIS() == null) {
//			result.setCORP_HIS("-");
//		} else {
//			result.setCORP_HIS( result.getCORP_HIS() + "년" );
//		}


        return result;
	}
	
    
	@Override
	public JSONObject requestInsert(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		CustomConsultDetails consultDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
        
        //핸드폰
        String hpNoEnc = UtilCrypt.ScpEncStr( paramVO.getCUST_HP_NO().replace(".", "").replace("-", ""), iniEncDecFilePath );
        paramVO.setCUST_HP_NO( hpNoEnc );

        //사업자 번호 암호화
        String custRegiNoEnc = UtilCrypt.ScpEncStr( paramVO.getCUST_REGI_NO(), iniEncDecFilePath );
        paramVO.setCUST_REGI_NO( custRegiNoEnc );

		//CORP_REGI_DT , JOIN_DATE 데이터 포맷팅 2022.09.15 -> 20220915
        //paramVO.setCONS_DTTM( paramVO.getCONS_DTTM().replace(".", "").replace("-", "") );
		paramVO.setCORP_REGI_DT( paramVO.getCORP_REGI_DT().replace(".", "").replace("-", "") );
		paramVO.setJOIN_DATE( paramVO.getJOIN_DATE().replace(".", "").replace("-", "") );

		//지점, 심사팀, 고객상담신청에 따라 상태값을 다르게 셋팅한다.
		if("MC0002300001".equals(consultDetail.getMember_authority())) {
			//지점 : 접수시 -> 공통 처리현황 - 접수 / 지점코드, 지점담당자 입력
			paramVO.setBRANCH_CODE( consultDetail.getBranch_code() );
			paramVO.setBRANCH_MB_CODE( consultDetail.getMember_code() );
			paramVO.setSTATS_CODE("MC0001100001");
		}else if("MC0002300002".equals(consultDetail.getMember_authority())) {
			//심사팀 : 직접 접수시 -> 공통 처리현황 - 접수  /  심사팀결과 - 접수
			paramVO.setSTATS_CODE("MC0001100001");
			paramVO.setEVAL_RESULT_CODE("MC0002100001");
		}		
		
		// 신청내역 등록자 기록
		paramVO.setIN_USER( consultDetail.getMember_code() );
		paramVO.setUP_USER( consultDetail.getMember_code() );

		consultInfoMapper.consultInfoInsert(paramVO);

		UtilJsonResult.setReturnCodeSuc(json, rMsg);
		
		return json;
	}

	@Override
	public JSONObject requestUpdate(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

        //실명번호
        String realNo = UtilCrypt.ScpEncStr( paramVO.getCUST_REGI_NO().replace(".", "").replace("-", ""), iniEncDecFilePath );
        paramVO.setCUST_REGI_NO(realNo);
        
        //핸드폰
        String hpNo = UtilCrypt.ScpEncStr( paramVO.getCUST_HP_NO().replace(".", "").replace("-", ""), iniEncDecFilePath );
        paramVO.setCUST_HP_NO(hpNo);

		// 사업자등록일 CORP_REGI_DT 포맷팅 (. 제거) .. 데이터 있을시
		if( paramVO.getCORP_REGI_DT() != null ) {
			paramVO.setCORP_REGI_DT(
				paramVO.getCORP_REGI_DT()
					.replace(".", "")
					.replace("-", "") );
		}
		
		// 상담일 CONS_MB_CODE 포맷팅 (- , 제거)
		if( paramVO.getCONS_MB_CODE() != null ) {
			paramVO.setCONS_MB_CODE( paramVO.getCONS_MB_CODE().replace("-", "").replace(",", "") );
		}
		// 상담일 CONS_DTTM 포맷팅 (- , 제거)
		if( paramVO.getCONS_DTTM() != null ) {
			paramVO.setCONS_DTTM( paramVO.getCONS_DTTM().replace("-", "").replace(",", "") );
		}
		/*
		  매핑작업 : 처리현황코드 <-> 수정페이지의 지점결과코드 혹은 심사결과코드

		  처리현황코드
		  MC0001100001- 접수  MC0001100002- 진행중  MC0001100003- 부재/보류  MC0001100004- 완료  MC0001100005- 취소
		  지점결과코드
		  MC0001000001- 접수  MC0001000002- 상담이관  MC0001000003- 거절  MC0001000004- 고객취소
		  심사결과코드
		  MC0002100001- 접수  MC0002100002- 진행중  MC0002100003- 부재/보류  MC0002100004- 승인  MC0002100005- 거절  MC0002100006- 고객취소
		 */
		String newStatsCode = new String();
		String branchResultCode = paramVO.getBRANCH_RESULT_CODE();
		String evalResultCode = paramVO.getEVAL_RESULT_CODE();

		if (branchResultCode != null) {
			switch(branchResultCode) {
				case "MC0001000001": 				// 접수
					newStatsCode = "MC0001100001"; 	// 접수
					break;
				case "MC0001000002": 				// 상담이관
					newStatsCode = "MC0001100003";	// 진행중
					break;
				case "MC0001000003": 				// 거절
					newStatsCode = "MC0001100005";	// 완료
					break;
				case "MC0001000004": 				// 고객취소
					newStatsCode = "MC0001100006";	// 취소
					break;
				default:
					logger.error("RequestListServiceImpl.requestUpdate() 스위치 문 에러 발생");
			}
		}

		if (evalResultCode != null) {
			switch(evalResultCode) {
				case "MC0002100001": 				// 접수
					newStatsCode = "MC0001100001"; 	// 접수
					break;
				case "MC0002100002": 				// 진행중
					newStatsCode = "MC0001100003";	// 진행중
					break;
				case "MC0002100003": 				// 부재/보류
					newStatsCode = "MC0001100004";	// 부재/보류
					break;
				case "MC0002100004": 				// 승인
					newStatsCode = "MC0001100005";	// 완료
					break;
				case "MC0002100005": 				// 거절
					newStatsCode = "MC0001100005";	// 완료
					break;
				case "MC0002100006": 				// 고객취소
					newStatsCode = "MC0001100006";	// 취소
					break;
				default:
					logger.error("RequestListServiceImpl.requestUpdate() 스위치 문 에러 발생");
			}
		}
		//매핑된 처리현황 등록
		paramVO.setSTATS_CODE( newStatsCode );

		/* 처리현황 STATS_CODE가 완료 MC0001100005 ==> 상담 완료일 COMP_DATE 설정
		   처리현황 STATS_CODE가 취소 MC0001100006 ==> 상담 완료일 COMP_DATE 설정 */
	   	if( paramVO.getSTATS_CODE() != null) {
			if( paramVO.getSTATS_CODE().equals("MC0001100005") || paramVO.getSTATS_CODE().equals("MC0001100006") ) {
				SimpleDateFormat dateFm = new SimpleDateFormat("yyyymmdd");
				Date now = new Date();
				paramVO.setCOMP_DATE( dateFm.format(now) );
			}
		}
	   	
		// 상담내역 수정 및 json 성공코드 입력
		paramVO.setUP_USER( adminDetail.getMember_code() );
		consultInfoMapper.consultInfoUpdate(paramVO);
		UtilJsonResult.setReturnCodeSuc(resultJson, rMsg);

		return resultJson;
	}

	@Override
	public JSONObject requestDelete(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
//		if(UtilCommon.isEmpty(consultInfoMapper.boardDetail(paramVO))) {
//			rMsg = messageSource.getMessage("validation.delete.empty.object", null, localeResolver.resolveLocale(request)); 
//			UtilJsonResult.setReturnCodeFail(json, rMsg);
//			return json;
//		}
//		// E:유효성체크
//		
//		paramVO.setDEL_YN("Y");
//		paramVO.setUP_USER(paramVO.getLoginCode());
//		consultInfoMapper.boardDelete(paramVO);
		
		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}


	@Override
	public JSONObject assignConsultantOnList(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

		//현재 로그인된 유저의 정보 (MEMBER_CODE) 를 가져와 업데이트용 paramVO에 셋팅하기 위한 용도
		CustomConsultDetails consultDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

		//체크된 각 consSeq 마다 업데이트 쿼리 실행
		String[] consSeqArr = paramVO.getCheckedConsSeqArr();
		for(int i=0; i<consSeqArr.length; i++) {
			paramVO.setCONS_SEQ(consSeqArr[i]);
			//paramVO.setCONS_SEQ( Long.parseLong(consSeqArr[i]) );
			paramVO.setCONS_MB_CODE( consultDetail.getMember_code() );
			
			//상담자 배정시 상태현황을 진행 중(대기): MC0001100002 로 바꿔준다.
			paramVO.setSTATS_CODE("MC0001100002");
			
			consultInfoMapper.consultInfoUpdate(paramVO);
		}

		//json 성공 코드 입력
		UtilJsonResult.setReturnCodeSuc(json, rMsg);

		return json;
	}

	@Override
	public JSONObject assignConsultantOnDetail(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

		//현재 로그인된 유저의 정보 (MEMBER_CODE, MEMBER_NAME)를 가져와 업데이트용 paramVO에 셋팅
		CustomConsultDetails consultDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		/*
			paramVO.setCONS_MB_CODE( consultDetail.getMember_code() );
			paramVO.setMEMBER_NAME( consultDetail.getMember_name() );
		 */
		json.put( "consMbCode", consultDetail.getMember_code() );
		json.put( "consMbName", consultDetail.getMember_name() );

		//화면에 출력하기 위한 상담일 또한 생성하여 담는다
		LocalDateTime ldt = LocalDateTime.now();
		String formattedDate = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.KOREAN).format(ldt);
		/*
			paramVO.setCONS_DTTM( formattedDate );
		*/
		json.put("consDttm", formattedDate);

		//json에 담아 화면에 보내주기 때문에 업데이트 쿼리는 이루어지지 않는다
		UtilJsonResult.setReturnCodeSuc(json, rMsg);

		return json;
	}
}
