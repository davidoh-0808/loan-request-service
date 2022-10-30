package com.neo.front.consultation.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

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
import com.neo.mappers.CommonMapper;
import com.neo.mappers.ConsultInfoMapper;
import com.neo.util.UtilCrypt;
import com.neo.util.UtilJsonResult;

@Service("frontConsultationService")
@Transactional
public class FrontConsultationServiceImpl implements FrontConsultationService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MessageSource messageSource;
	@Autowired
	LocaleResolver localeResolver;

	@Resource(name = "consultInfoMapper")
	private ConsultInfoMapper consultInfoMapper;
	@Resource(name = "commonMapper")
	private CommonMapper commonMapper;

	// DAmo 암호,복호화 모듈의 설정파일 주입
	@Value("${damo.inifilepath}")
	private String iniEncDecFilePath;

	@Override
	public JSONObject frontRequestInsert(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		// S:유효성체크
		// 이름 체크
//		if(UtilCommon.isEmpty(paramVO.getCUST_NM())) {
//			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"고객명"}, localeResolver.resolveLocale(request));
//			UtilJsonResult.setReturnCodeFail(json, rMsg);
//			return json;
//			
//		}
		// E:유효성체크

        //실명번호
        String realNo = UtilCrypt.ScpEncStr( paramVO.getCUST_REGI_NO().replace(".", "").replace("-", ""), iniEncDecFilePath );
        paramVO.setCUST_REGI_NO(realNo);
        
        //핸드폰
        String hpNo = UtilCrypt.ScpEncStr( paramVO.getCUST_HP_NO().replace(".", "").replace("-", ""), iniEncDecFilePath );
        paramVO.setCUST_HP_NO(hpNo);
        
		//CORP_REGI_DT , JOIN_DATE 데이터 포맷팅 2022.09.15 -> 20220915
		paramVO.setCORP_REGI_DT( paramVO.getCORP_REGI_DT().replace(".", "").replace("-", "") );
		paramVO.setJOIN_DATE( paramVO.getJOIN_DATE().replace(".", "").replace("-", "") );

		//고객상담시 유입경로는 홈페이지
		paramVO.setINFLOW_ROUTE1("MC0001600006"); //유입경로1 : 고객등록(홈페이지)

		//고객상담신청시 지점에선 확인불가하며, 심사팀에서 확인 가능하도록 상태코드를 진행중으로 넣어준다.
		paramVO.setSTATS_CODE("MC0001100001");
		
		//고객상담신청시 심사결과코드를 접수 로 넣어준다.
		paramVO.setEVAL_RESULT_CODE("MC0002100001");
		
		// 글등록
		paramVO.setIN_USER("GUEST"); //고객코드??

		consultInfoMapper.consultInfoInsert(paramVO);

		UtilJsonResult.setReturnCodeSuc(json, rMsg);
		
		return json;
	}

	//@Override
	/*
	 * public JSONObject requestUpdate(ConsultInfoVO paramVO, HttpServletRequest
	 * request) throws Exception { CustomConsultDetails adminDetail =
	 * (CustomConsultDetails)
	 * SecurityContextHolder.getContext().getAuthentication().getDetails();
	 * JSONObject resultJson = new JSONObject();
	 * UtilJsonResult.setReturnCodeFail(resultJson); String rMsg =
	 * messageSource.getMessage("error.system.default", null,
	 * localeResolver.resolveLocale(request));
	 * 
	 * // 사업자등록일 CORP_REGI_DT 포맷팅 (. 제거) paramVO.setCORP_REGI_DT(
	 * paramVO.getCORP_REGI_DT().replace(".", "") ); paramVO.setCORP_REGI_DT(
	 * paramVO.getCORP_REGI_DT().replace("-", "") ); // 상담 완료일 COMP_DATE 포매팅 (. 제거)
	 * paramVO.setCOMP_DATE( paramVO.getCOMP_DATE().replace(".", "") );
	 * paramVO.setCOMP_DATE( paramVO.getCOMP_DATE().replace("-", "") );
	 * 
	 * 
	 * 매핑작업 : 처리현황코드 <-> 수정페이지의 지점결과코드 혹은 심사결과코드
	 * 
	 * 처리현황코드 MC0001100001- 접수 MC0001100002- 진행중 MC0001100003- 부재/보류 MC0001100004-
	 * 완료 MC0001100005- 취소 지점결과코드 MC0001000001- 상담이관 MC0001000002- 거절 MC0001000003-
	 * 고객취소 심사결과코드 MC0002100001- 접수 MC0002100002- 진행중 MC0002100003- 부재/보류 MC0002100004- 승인
	 * MC0002100005- 거절 MC0002100006- 고객취소
	 * 
	 * String newStatsCode = new String(); String branchResultCode =
	 * paramVO.getBRANCH_RESULT_CODE(); String evalResultCode =
	 * paramVO.getEVAL_RESULT_CODE();
	 * 
	 * if (branchResultCode != null) { switch(branchResultCode) { case
	 * "MC0001000001": // 상담이관 newStatsCode = "MC0001100002"; // 진행중 break; case
	 * "MC0001000002": // 거절 newStatsCode = "MC0001100004"; // 완료 break; case
	 * "MC0001000003": // 고객취소 newStatsCode = "MC0001100005"; // 취소 break; default:
	 * logger.error("RequestListServiceImpl.requestUpdate() 스위치 문 에러 발생"); } }
	 * 
	 * logger.error("RequestListServiceImpl.requestUpdate() 스위치 문 에러 발생"); } } //매핑된
	 * 처리현황 등록 paramVO.setSTATS_CODE( newStatsCode );
	 * 
	 * 
	 * // 상담내역 수정 및 json 성공코드 입력 paramVO.setUP_USER( adminDetail.getMember_code() );
	 * consultInfoMapper.consultInfoUpdate(paramVO);
	 * UtilJsonResult.setReturnCodeSuc(resultJson, rMsg);
	 * 
	 * return resultJson; }
	 */

}
