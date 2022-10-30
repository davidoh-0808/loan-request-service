package com.neo.consult.consult.service;

import com.neo.common.vo.ConsultInfoVO;
import com.neo.mappers.ConsultInfoMapper;
import com.neo.util.UtilCrypt;
import com.neo.util.UtilJsonResult;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.text.SimpleDateFormat;
import java.util.*;

@Transactional
@Service("consultInfoService")
public class ConsultInfoServiceImpl implements ConsultInfoService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;
    @Autowired
    LocaleResolver localeResolver;

    @Resource(name = "consultInfoMapper")
    private ConsultInfoMapper consultInfoMapper;

	// DAmo 암호,복호화 모듈의 설정파일 주입
	@Value("${damo.inifilepath}")
	private String iniEncDecFilePath;

    @Override
    public int consultInfoListCount(ConsultInfoVO paramVO) throws Exception {
        return consultInfoMapper.consultInfoListCount(paramVO);
    }

    @Override
    public ConsultInfoVO consultStatsCount(ConsultInfoVO paramVO) throws Exception {
        return consultInfoMapper.consultStatsCount(paramVO);
    }

    @Override
    public List<ConsultInfoVO> consultInfoList(ConsultInfoVO paramVO) throws Exception {
        
    	List<ConsultInfoVO> resultList = consultInfoMapper.consultInfoList(paramVO);

		//리스트출력을 위한 데이터 포맷팅
		ConsultInfoVO re = new ConsultInfoVO();
		String dateRegex =  "^(\\d{4})(\\d{2})(\\d+)";
		String HpRegex = "^(\\d{3})(\\d{4})(\\d+)";
		String HpDec = "";
		String realNoRegex = "^(\\d{6})(\\d+)";
        String dec = "";
		String repl = "";

		for(int i=0; i<resultList.size(); i++) {
			re = resultList.get(i);
            //상담일
            if( re.getCONS_DTTM() != null ) {
                repl = re.getCONS_DTTM().replaceFirst( dateRegex, "$1-$2-$3");
                re.setCONS_DTTM( repl );
            }
			//휴대폰
			if( re.getCUST_HP_NO() != null && re.getCUST_HP_NO().length() >= 7 ) {
                HpDec = UtilCrypt.ScpDecStr( re.getCUST_HP_NO(), iniEncDecFilePath );
                repl = HpDec.replaceFirst(HpRegex, "$1-$2-$3");
                re.setCUST_HP_NO_FM(repl);
            }
			
			//생년월일(실명번호) - ex. 7512161****** -> 75.12.16
			if ( re.getCUST_REGI_NO() != null ) {
                // 주민번호 포맷팅
                dec = UtilCrypt.ScpDecStr( re.getCUST_REGI_NO() , iniEncDecFilePath );
                re.setCUST_REGI_NO( dec );

                repl = dec.replaceFirst( realNoRegex, "$1-$2" );
                re.setCUST_REGI_NO_FM( repl );
            }
		}

		return resultList;
    }

    @Override
    public ConsultInfoVO consultInfoDetail(ConsultInfoVO paramVO) throws Exception {
		ConsultInfoVO result = consultInfoMapper.consultInfoDetail(paramVO);

        String HpRegex = "^(\\d{3})(\\d{4})(\\d+)";
        String realNoRegex = "^(\\d{6})(\\d+)";
        String SSN7 = "";                           // 주민번호 7번째자리
        String corpRegex = "^(\\d{3})(\\d{2})(\\d+)";
        String dateRegex =  "^(\\d{4})(\\d{2})(\\d+)";
        String dec = "";                            // 복호화된 값
        String fm = "";                             // 복호화 후 포맷팅 된 값


        // 주민번호 7번째: 1, 3, 9 (대한민국 남자) / 5, 7 (외국인 남자)
        Set<String> genderMSet = new HashSet<>( Arrays.asList("1", "3", "5", "7", "9") );
        // 주민번호 7번째: 5, 6, 7, 8 외국인
        Set<String> FrnSet = new HashSet<>( Arrays.asList("5", "6", "7", "8") );


        //휴대폰 포맷팅  CUST_HP_NO_FM
        if( result.getCUST_HP_NO() != null && result.getCUST_HP_NO().length() >= 7 ) {
            dec = UtilCrypt.ScpDecStr( result.getCUST_HP_NO(), iniEncDecFilePath );
            fm = dec.replaceFirst( HpRegex, "$1-$2-$3" );
            result.setCUST_HP_NO( dec );
            result.setCUST_HP_NO_FM( fm );
        }
        // 주민번호 포맷팅
        dec = UtilCrypt.ScpDecStr( result.getCUST_REGI_NO() , iniEncDecFilePath );
        result.setCUST_REGI_NO( dec );

        fm = dec.replaceFirst( realNoRegex, "$1-$2" );
        result.setCUST_REGI_NO_FM( fm );
        
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
        if(result.getCUST_CORP_NO() != null) {
            fm = result.getCUST_CORP_NO().replaceFirst( corpRegex, "$1-$2-$3" );
            result.setCUST_CORP_NO_FM( fm );
        }
        
		// 직장정보 - 입사일
		if( result.getJOIN_DATE() != null ) {
		    fm = result.getJOIN_DATE().replaceFirst( dateRegex, "$1-$2-$3" );
            result.setJOIN_DATE_FM( fm );
        }

        return result;
    }

    @Override
    public JSONObject consultInfoInsert(ConsultInfoVO paramVO) throws Exception {
        JSONObject json = new JSONObject();
        consultInfoMapper.consultInfoInsert(paramVO);

        return json;
    }

    @Override
    public JSONObject consultInfoUpdate(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        UtilJsonResult.setReturnCodeFail(json);
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
		
		// 상담일 CONS_DTTM 포맷팅 (- , 제거)
		if( paramVO.getCONS_DTTM() != null ) {
			paramVO.setCONS_DTTM( paramVO.getCONS_DTTM().replace("-", "").replace(".", "") );
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
//		String branchResultCode = paramVO.getBRANCH_RESULT_CODE();
		String evalResultCode = paramVO.getEVAL_RESULT_CODE();

//		if (branchResultCode != null) {
//			switch(branchResultCode) {
//				case "MC0001000001": 				// 접수
//					newStatsCode = "MC0001100001"; 	// 접수
//					break;
//				case "MC0001000002": 				// 상담이관
//					newStatsCode = "MC0001100003";	// 진행중
//					break;
//				case "MC0001000003": 				// 거절
//					newStatsCode = "MC0001100005";	// 완료
//					break;
//				case "MC0001000004": 				// 고객취소
//					newStatsCode = "MC0001100006";	// 취소
//					break;
//				default:
//					logger.error("RequestListServiceImpl.requestUpdate() 스위치 문 에러 발생");
//			}
//		}

//		if (evalResultCode != null) {
//			switch(evalResultCode) {
//				case "MC0002100001": 				// 접수
//					newStatsCode = "MC0001100001"; 	// 접수
//					break;
//				case "MC0002100002": 				// 진행중
//					newStatsCode = "MC0001100003";	// 진행중
//					break;
//				case "MC0002100003": 				// 부재/보류
//					newStatsCode = "MC0001100004";	// 부재/보류
//					break;
//				case "MC0002100004": 				// 승인
//					newStatsCode = "MC0001100005";	// 완료
//					break;
//				case "MC0002100005": 				// 거절
//					newStatsCode = "MC0001100005";	// 완료
//					break;
//				case "MC0002100006": 				// 고객취소
//					newStatsCode = "MC0001100006";	// 취소
//					break;
//				default:
//					logger.error("RequestListServiceImpl.requestUpdate() 스위치 문 에러 발생");
//			}
//		}
		//매핑된 처리현황 등록
//		paramVO.setSTATS_CODE( newStatsCode );
		
		/* 처리현황 STATS_CODE가 완료 MC0001100005 ==> 상담 완료일 COMP_DATE 설정
		   처리현황 STATS_CODE가 취소 MC0001100006 ==> 상담 완료일 COMP_DATE 설정 */
	   	if( paramVO.getSTATS_CODE() != null) {
			if( paramVO.getSTATS_CODE().equals("MC0001100005") || paramVO.getSTATS_CODE().equals("MC0001100006") ) {
				SimpleDateFormat dateFm = new SimpleDateFormat("yyyymmdd");
				Date now = new Date();
				paramVO.setCOMP_DATE( dateFm.format(now) );
			}
		}
	   	
        // 상담내역 수정 및 json 성공 코드 입력 (jsp AJAX 확인용)
        paramVO.setUP_USER(paramVO.getLoginCode());
        
        consultInfoMapper.consultInfoUpdate(paramVO);
        
        UtilJsonResult.setReturnCodeSuc(json, rMsg);

        return json;
    }

    @Override
    public JSONObject consultInfoDelete(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        UtilJsonResult.setReturnCodeFail(json);
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

        consultInfoMapper.consultInfoDelete(paramVO);
        UtilJsonResult.setReturnCodeSuc(json, rMsg);

        return json;
    }

    @Override
    public List<ConsultInfoVO> consultInfoExportList(ConsultInfoVO paramVO) throws Exception {
        // 전체리스트를 추출하기위해 consultInfoList(페이징 offset, limit 사용) 대신 별도 리스트쿼리 사용
    	List<ConsultInfoVO> resultList = consultInfoMapper.consultInfoListForExcel(paramVO);
    	//리스트출력을 위한 데이터 포맷팅
		ConsultInfoVO re = new ConsultInfoVO();
		
    	for(int i=0; i<resultList.size(); i++) {
    		re = resultList.get(i);
    		
	    	//실명번호
			re.setCUST_REGI_NO(UtilCrypt.ScpDecStr( re.getCUST_REGI_NO(), iniEncDecFilePath) );
	        
			//휴대폰
			re.setCUST_HP_NO(UtilCrypt.ScpDecStr( re.getCUST_HP_NO(), iniEncDecFilePath) );
    	
    	}	
    	
    	return resultList;
    }


    @Override
    public int connListCount(ConsultInfoVO paramVO) throws Exception {
        return consultInfoMapper.connListCount(paramVO);
    }

    @Override
    public List<ConsultInfoVO> connList(ConsultInfoVO paramVO) throws Exception {
        List<ConsultInfoVO> resultList = new ArrayList<>();
        resultList = consultInfoMapper.connList(paramVO);

        ConsultInfoVO re = new ConsultInfoVO();
        String birthDec = "";
        String birthRegex = "^(\\d{6})(\\d+)";
        String birthFm = "";
        String SSN7 = "";                           // 주민번호 7번째자리
        String corpRegex = "^(\\d{3})(\\d{2})(\\d+)";
        String corpFm = "";

        // 주민번호 7번째: 1, 3, 9 (대한민국 남자) / 5, 7 (외국인 남자)
        Set<String> genderMSet = new HashSet<>( Arrays.asList("1", "3", "5", "7", "9") );
        // 주민번호 7번째: 5, 6, 7, 8 외국인
        Set<String> FrnSet = new HashSet<>( Arrays.asList("5", "6", "7", "8") );

        for(int i=0; i<resultList.size(); i++) {
            re = resultList.get(i);

            // 생년월일 포맷팅
            birthDec = UtilCrypt.ScpDecStr( re.getCUST_REGI_NO() , iniEncDecFilePath );
            birthFm = birthDec.replaceFirst( birthRegex, "$1-$2" );
            re.setCUST_REGI_NO( birthFm );

            // 성별 매핑
            SSN7 = birthDec.substring(6, 7);
            if ( genderMSet.contains( SSN7 ) ) {
                re.setGENDER("남자");
            } else {
                re.setGENDER("여자");
            }

            // 내/외국인 매핑
            if ( FrnSet.contains( SSN7 )  ) {
                re.setFRN_CHK("외국인");
;           } else {
                re.setFRN_CHK("내국인");
            }

            // 사업자번호 포맷팅 .. ex) 000-00-00000
            if (re.getCUST_CORP_NO() != null) {
                corpFm = re.getCUST_CORP_NO().replaceFirst( corpRegex, "$1-$2-$3" );
                re.setCUST_CORP_NO( corpFm );
            }
        }

        return resultList;
    }
}
