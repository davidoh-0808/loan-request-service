package com.neo.front.consultation.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.service.CommonService;
import com.neo.common.vo.ConsultInfoVO;
import com.neo.common.vo.MasterCodeVO;
import com.neo.common.vo.MemberVO;
import com.neo.front.consultation.service.FrontConsultationService;
import com.neo.util.UtilJsonResult;

import CheckPlus.nice.*;

@Controller(value = "frontConsultationController")
public class FrontConsultationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "commonService")
	private CommonService commonService;
	@Resource(name = "frontConsultationService")
    private FrontConsultationService frontConsultationService;
    
    //nice 모듈 정보
    @Value("${nice.sitecode}") private String siteCode;
    @Value("${nice.password}") private String passWord;
    @Value("${server.domain.pc}") private String domain;
    
    //주소api key
    @Value("${zipcode.confmKey}") private String zipcodeConfmKey;
    
    
	/**
	 * 대출상담신청 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/request")
	public ModelAndView mainList(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/consultation/request");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 자주하는질문 페이지 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/consultation/qna")
	public ModelAndView qna(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/consultation/qna");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 대출상담신청 리뉴얼 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/newRequest")
	public ModelAndView newRequest(@ModelAttribute MemberVO paramVO, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView("front/consultation/newRequest");
		
		return mav;
	}
	
	/**
	 * 대출상담신청 리뉴얼 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/niceCheck")
	public ModelAndView niceCheck(HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
		
		String sSiteCode = siteCode;			// NICE로부터 부여받은 사이트 코드
		String sSitePassword = passWord;		// NICE로부터 부여받은 사이트 패스워드
		
		logger.info("sSiteCode================="+sSiteCode);
		logger.info("sSitePassword================="+sSitePassword);
		
		String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
		// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
		sRequestNumber = niceCheck.getRequestNO(sSiteCode);
		session.setAttribute("REQ_SEQ" , sRequestNumber);	// 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
		
		String sAuthType = request.getParameter("AUTH_TYPE");      	// 없으면 기본 선택화면, M(휴대폰), X(인증서공통), U(공동인증서), F(금융인증서), S(PASS인증서), C(신용카드)
		
		logger.info("sAuthType================="+sAuthType);
		
		String customize 	= "";		//없으면 기본 웹페이지 / Mobile : 모바일페이지
		//if("M".equals(sAuthType)) {
		//	customize = "Mobile";
		//}
		
		// CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다.
		//리턴url은 인증 전 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url : http://www.~
		String sReturnUrl =  domain + "/front/consultation/checkplus_success";      		// 성공시 이동될 URL
		String sErrorUrl =  domain + "/front/consultation/checkplus_fail";          // 실패시 이동될 URL
		
		logger.info("sReturnUrl================="+sReturnUrl);
		logger.info("sErrorUrl================="+sErrorUrl);
		
		// 입력될 plain 데이타를 만든다.
		String sPlainData = "7:REQ_SEQ" + sRequestNumber.getBytes().length + ":" + sRequestNumber +
				"8:SITECODE" + sSiteCode.getBytes().length + ":" + sSiteCode +
				"9:AUTH_TYPE" + sAuthType.getBytes().length + ":" + sAuthType +
				"7:RTN_URL" + sReturnUrl.getBytes().length + ":" + sReturnUrl +
				"7:ERR_URL" + sErrorUrl.getBytes().length + ":" + sErrorUrl +
				"9:CUSTOMIZE" + customize.getBytes().length + ":" + customize;
		
		String sMessage = "";
		String sEncData = "";
		
		int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData);
		if( iReturn == 0 )
		{
			sEncData = niceCheck.getCipherData();
		}
		else if( iReturn == -1)
		{
			sMessage = "암호화 시스템 에러입니다.";
		}    
		else if( iReturn == -2)
		{
			sMessage = "암호화 처리오류입니다.";
		}    
		else if( iReturn == -3)
		{
			sMessage = "암호화 데이터 오류입니다.";
		}    
		else if( iReturn == -9)
		{
			sMessage = "입력 데이터 오류입니다.";
		}    
		else
		{
			sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
		}
		
		mav.addObject("sEncData", sEncData);
		mav.addObject("sMessage", sMessage);
		return mav;
	}
	
	/**
	 * 대출상담신청 리뉴얼 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/niceCheckSocket")
	public ModelAndView niceCheckSocket(HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
		
		String sSiteCode	= siteCode;			// NICE로부터 부여받은 사이트 코드
		String sSitePw		= passWord;		// NICE로부터 부여받은 사이트 패스워드
		
		logger.info("sSiteCode================="+sSiteCode);
		logger.info("sSitePassword================="+sSitePw);
		
		String sJumin			= request.getParameter("REGI_NO1");			// 주민등록번호 앞 7자리
		String sName			= request.getParameter("CUST_NM");			// 성명
		String sMobileCo	= "1";			// 이통사 구분 (SKT : 1 / KT : 2 / LG : 3 / SKT알뜰폰 : 5 / KT알뜰폰 : 6 / LGU+알뜰폰 : 7)
		String sMobileNo	= request.getParameter("CUST_HP_NO");			// 휴대폰 번호 CUST_HP_NO
		
		logger.info("sJumin================="+sJumin);
		logger.info("sName================="+sName);
		logger.info("sMobileCo================="+sMobileCo);
		logger.info("sMobileNo================="+sMobileNo);
		
		/*
		┌ sCPRequest 변수에 대한 설명  ─────────────────────────────────────────────────────
			[CP 요청번호]로 귀사에서 데이타를 임의로 정의하시면 됩니다.
			
			CP 요청번호는 인증 완료 후, 결과 데이타에 함께 제공되며
			데이타 위변조 방지 및 특정 사용자가 요청한 것임을 확인하기 위한 목적으로 이용하실 수 있습니다.
			
			따라서 귀사의 프로세스에 응용하여 이용할 수 있는 데이타이기에, 필수값은 아닙니다.
		└────────────────────────────────────────────────────────────────────
		*/
		String sCPRequest		= "";
		//String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
		
		// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
		sCPRequest = niceCheck.getRequestNO(sSiteCode);
		
		// 객체 생성
		MCheckPlus cpMobile = new MCheckPlus();
		
		int iReturn = -1;
		// Method 결과값(iReturn)에 따라, 프로세스 진행여부를 파악합니다.
		
		iReturn = cpMobile.fnRequestSafeAuth(sSiteCode, sSitePw, sJumin, sName, sMobileCo, sMobileNo, sCPRequest);
	  
		String sMessage = ""; 
		String sEncData = "";
		
		// Method 결과값에 따른 처리사항
		if (iReturn == 0)
		{
			/*
				- 응답코드에 따라 SMS 인증 여부를 판단합니다.
				- 응답코드 정의 : 첨부해드린 xls 파일을 참고하세요.
			*/
			System.out.println("RETURN_CODE=" + cpMobile.getReturnCode());              // 인증결과코드
			System.out.println("REQ_SEQ=" + cpMobile.getRequestSEQ());                  // 요청 고유번호
			System.out.println("RES_SEQ=" + cpMobile.getResponseSEQ());                 // 응답 고유번호
			
			sEncData =  cpMobile.getReturnCode();
		}
		else if (iReturn == -7 || iReturn == -8)
		{
	    	System.out.println("AUTH_ERROR=" + iReturn);
	    	System.out.println("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
	    	System.out.println("IP : 121.131.196.200 / Port : 3700 ~ 3715");
	    	
	    	sMessage = "서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.";
		}
		else if (iReturn == -9 || iReturn == -10 || iReturn == 12)
		{
			System.out.println("AUTH_ERROR=" + iReturn);
			System.out.println("입력값 오류 : fnRequest 함수 처리시, 필요한 6개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
			
			sMessage = "입력값 오류 : fnRequest 함수 처리시, 필요한 6개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.";
		}
		else
		{
			System.out.println("AUTH_ERROR=" + iReturn);
			System.out.println("iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.");
			
			sMessage = "iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.";
		}
	
		mav.addObject("iReturn", iReturn); 
		mav.addObject("sEncData", sEncData); 
		mav.addObject("sMessage", sMessage);
		
		/////////////////////////////////////////////////////////////////
		/*
		 * String sRequestNumber = "REQ0000000001"; // 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게
		 * 되므로 // 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다. sRequestNumber =
		 * niceCheck.getRequestNO(sSiteCode); session.setAttribute("REQ_SEQ" ,
		 * sRequestNumber); // 해킹등의 방지를 위하여 세션을 쓴다면, 세션에 요청번호를 넣는다.
		 * 
		 * String sAuthType = request.getParameter("AUTH_TYPE"); // 없으면 기본 선택화면, M(휴대폰),
		 * X(인증서공통), U(공동인증서), F(금융인증서), S(PASS인증서), C(신용카드)
		 * 
		 * logger.info("sAuthType================="+sAuthType);
		 * 
		 * String customize = ""; //없으면 기본 웹페이지 / Mobile : 모바일페이지
		 * //if("M".equals(sAuthType)) { // customize = "Mobile"; //}
		 * 
		 * // CheckPlus(본인인증) 처리 후, 결과 데이타를 리턴 받기위해 다음예제와 같이 http부터 입력합니다. //리턴url은 인증 전
		 * 인증페이지를 호출하기 전 url과 동일해야 합니다. ex) 인증 전 url : http://www.~ 리턴 url :
		 * http://www.~ String sReturnUrl = domain +
		 * "/front/consultation/checkplus_success"; // 성공시 이동될 URL String sErrorUrl =
		 * domain + "/front/consultation/checkplus_fail"; // 실패시 이동될 URL
		 * 
		 * logger.info("sReturnUrl================="+sReturnUrl);
		 * logger.info("sErrorUrl================="+sErrorUrl);
		 * 
		 * // 입력될 plain 데이타를 만든다. String sPlainData = "7:REQ_SEQ" +
		 * sRequestNumber.getBytes().length + ":" + sRequestNumber + "8:SITECODE" +
		 * sSiteCode.getBytes().length + ":" + sSiteCode + "9:AUTH_TYPE" +
		 * sAuthType.getBytes().length + ":" + sAuthType + "7:RTN_URL" +
		 * sReturnUrl.getBytes().length + ":" + sReturnUrl + "7:ERR_URL" +
		 * sErrorUrl.getBytes().length + ":" + sErrorUrl + "9:CUSTOMIZE" +
		 * customize.getBytes().length + ":" + customize;
		 * 
		 * String sMessage = ""; String sEncData = "";
		 * 
		 * int iReturn = niceCheck.fnEncode(sSiteCode, sSitePassword, sPlainData); if(
		 * iReturn == 0 ) { sEncData = niceCheck.getCipherData(); } else if( iReturn ==
		 * -1) { sMessage = "암호화 시스템 에러입니다."; } else if( iReturn == -2) { sMessage =
		 * "암호화 처리오류입니다."; } else if( iReturn == -3) { sMessage = "암호화 데이터 오류입니다."; }
		 * else if( iReturn == -9) { sMessage = "입력 데이터 오류입니다."; } else { sMessage =
		 * "알수 없는 에러 입니다. iReturn : " + iReturn; }
		 * 
		 * mav.addObject("sEncData", sEncData); mav.addObject("sMessage", sMessage);
		 */
		return mav;
	}
	
	/**
	 * 대출상담신청 리뉴얼 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/fnNiceCerti")
	public ModelAndView fnNiceCerti(HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
		
		String sSiteCode	= siteCode;			// NICE로부터 부여받은 사이트 코드
		String sSitePw		= passWord;		// NICE로부터 부여받은 사이트 패스워드
		
		logger.info("sSiteCode================="+sSiteCode);
		logger.info("sSitePassword================="+sSitePw);
		
		//String sJumin			= request.getParameter("REGI_NO1");			// 주민등록번호 앞 7자리
		//String sName			= request.getParameter("CUST_NM");			// 성명
		//String sMobileCo	= "1";			// 이통사 구분 (SKT : 1 / KT : 2 / LG : 3 / SKT알뜰폰 : 5 / KT알뜰폰 : 6 / LGU+알뜰폰 : 7)
		//String sMobileNo	= request.getParameter("CUST_HP_NO");			// 휴대폰 번호 CUST_HP_NO
		
		//logger.info("sJumin================="+sJumin);
		//logger.info("sName================="+sName);
		//logger.info("sMobileCo================="+sMobileCo);
		//logger.info("sMobileNo================="+sMobileNo);
		
    	String sResSeq		= "";			// 응답 고유번호 (CPMobileStep1 에서 확인된 cpMobile.getResponseSEQ() 데이타)
    	String sAuthNo		= "";			// SMS 인증번호    	
    	String sCPRequest	= "";			// 요청 고유번호 (CPMobileStep1 에서 정의한 cpMobile.getRequestSEQ() 데이타)
    	
    	
		/*
		┌ sCPRequest 변수에 대한 설명  ─────────────────────────────────────────────────────
			[CP 요청번호]로 귀사에서 데이타를 임의로 정의하시면 됩니다.
			
			CP 요청번호는 인증 완료 후, 결과 데이타에 함께 제공되며
			데이타 위변조 방지 및 특정 사용자가 요청한 것임을 확인하기 위한 목적으로 이용하실 수 있습니다.
			
			따라서 귀사의 프로세스에 응용하여 이용할 수 있는 데이타이기에, 필수값은 아닙니다.
		└────────────────────────────────────────────────────────────────────
		 */
		//String sRequestNumber = "REQ0000000001";        	// 요청 번호, 이는 성공/실패후에 같은 값으로 되돌려주게 되므로 
		// 업체에서 적절하게 변경하여 쓰거나, 아래와 같이 생성한다.
		sCPRequest = niceCheck.getRequestNO(sSiteCode);
		
		// 객체 생성
		MCheckPlus cpMobile = new MCheckPlus();
		
		int iReturn = -1;
		// Method 결과값(iReturn)에 따라, 프로세스 진행여부를 파악합니다.
		
		iReturn = cpMobile.fnRequestConfirm(sSiteCode, sSitePw, sResSeq, sAuthNo, sCPRequest);
		
		String sMessage = ""; 
		String sEncData = "";
		String sConfirmDateTime = "";
		// Method 결과값에 따른 처리사항
		if (iReturn == 0)
		{
    		/*
				- 응답코드에 따라 귀사의 기획의도에 맞게 진행하시면 됩니다.
				- 응답코드 정의 : 첨부해드린 xls 파일을 참고하세요.
			*/
    		System.out.println("RETURN_CODE=" + cpMobile.getReturnCode());              // 응답코드
    		System.out.println("CONFIRM_DATETIME=" + cpMobile.getConfirmDateTime());    // 인증 완료시간
    		System.out.println("REQ_SEQ=" + cpMobile.getRequestSEQ());                  // 요청 고유번호
    		System.out.println("RES_SEQ=" + cpMobile.getResponseSEQ());                 // 응답 고유번호
    		System.out.println("RES_CI=" + cpMobile.getResponseCI());                   // 아이핀 연결정보(CI)
    		System.out.println("RES_DI=" + cpMobile.getResponseDI());                   // 아이핀 중복가입확인정보(DI)
			
			sEncData =  cpMobile.getReturnCode();
			sConfirmDateTime = cpMobile.getConfirmDateTime();
		}
		else if (iReturn == -7 || iReturn == -8)
		{
			System.out.println("AUTH_ERROR=" + iReturn);
			System.out.println("서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.");
			System.out.println("IP : 121.131.196.200 / Port : 3700 ~ 3715");
			
			sMessage = "서버 네트웍크 및 방확벽 관련하여 아래 IP와 Port를 오픈해 주셔야 이용 가능합니다.";
		}
		else if (iReturn == -9 || iReturn == -10 || iReturn == 12)
		{
			System.out.println("AUTH_ERROR=" + iReturn);
			System.out.println("입력값 오류 : fnRequest 함수 처리시, 필요한 6개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.");
			
			sMessage = "입력값 오류 : fnRequest 함수 처리시, 필요한 6개의 파라미터값의 정보를 정확하게 입력해 주시기 바랍니다.";
		}
		else
		{
			System.out.println("AUTH_ERROR=" + iReturn);
			System.out.println("iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.");
			
			sMessage = "iReturn 값 확인 후, NICE평가정보 전산 담당자에게 문의해 주세요.";
		}
		
		mav.addObject("iReturn", iReturn); 
		mav.addObject("sEncData", sEncData); 
		mav.addObject("sMessage", sMessage);
		mav.addObject("sConfirmDateTime", sConfirmDateTime);
		
		return mav;
	}
	
	/**
	 * 대출상담신청 리뉴얼 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/checkplus_success")
	public ModelAndView checkplus_success(@ModelAttribute MemberVO paramVO, HttpServletRequest request, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView("front/consultation/checkplus_success");
		
		//인증 후 결과값이 null로 나오는 부분은 관리담당자에게 문의 바랍니다.
		NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();
		
		String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");
		
		logger.info("checkplus_success ::::::::::::::::::::::::::::::");
		
	    String sSiteCode = siteCode;			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = passWord;		// NICE로부터 부여받은 사이트 패스워드
		
		String sCipherTime = "";			// 복호화한 시간
		String sRequestNumber = "";			// 요청 번호
		String sResponseNumber = "";		// 인증 고유번호
		String sAuthType = "";				// 인증 수단
		String sName = "";					// 성명
		String sDupInfo = "";				// 중복가입 확인값 (DI_64 byte)
		String sConnInfo = "";				// 연계정보 확인값 (CI_88 byte)
		String sBirthDate = "";				// 생년월일(YYYYMMDD)
		String sGender = "";				// 성별
		String sNationalInfo = "";			// 내/외국인정보 (개발가이드 참조)
		String sMobileNo = "";				// 휴대폰번호
		String sMobileCo = "";				// 통신사
		String sMessage = "";
		String sPlainData = "";
		
		int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);
		
		logger.info("iReturn:::::::::::::::::::::::::::::"+iReturn);
		
		if( iReturn == 0 )
		{
			sPlainData = niceCheck.getPlainData();
			sCipherTime = niceCheck.getCipherDateTime();
			
			// 데이타를 추출합니다.
			java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
			
			//equestNumber  = (String)mapresult.get("REQ_SEQ");
			//esponseNumber = (String)mapresult.get("RES_SEQ");
			//sAuthType		= (String)mapresult.get("AUTH_TYPE");
			sName			= (String)mapresult.get("NAME");
			//sName			= (String)mapresult.get("UTF8_NAME"); //charset utf8 사용시 주석 해제 후 사용
			sBirthDate		= (String)mapresult.get("BIRTHDATE");
			sGender			= (String)mapresult.get("GENDER");
			sNationalInfo  	= (String)mapresult.get("NATIONALINFO");
			//sDupInfo		= (String)mapresult.get("DI");
			sConnInfo		= (String)mapresult.get("CI");
			sMobileNo		= (String)mapresult.get("MOBILE_NO");
			//sMobileCo		= (String)mapresult.get("MOBILE_CO");
			
			
			logger.info("sName:::::::::::::::::::::::::::::"+sName);
			logger.info("sBirthDate:::::::::::::::::::::::::::::"+sBirthDate);
			logger.info("sGender:::::::::::::::::::::::::::::"+sGender);
			logger.info("sNationalInfo:::::::::::::::::::::::::::::"+sNationalInfo);
			logger.info("sMobileNo:::::::::::::::::::::::::::::"+sMobileNo);
			logger.info("sConnInfo:::::::::::::::::::::::::::::"+sConnInfo);
			
			mav.addObject("sName", sName);							//이름
			mav.addObject("sBirthDate", sBirthDate.substring(2));	//생년월일
			mav.addObject("sGender", sGender);						//성별
			mav.addObject("sNationalInfo", sNationalInfo);			//내외국인구분
			mav.addObject("sMobileNo", sMobileNo);					//휴대폰번호
			mav.addObject("sConnInfo", sConnInfo);					//CI 연계정보
			
			String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
			if(!sRequestNumber.equals(session_sRequestNumber))
			{
				sMessage = "세션값 불일치 오류입니다.";
				sResponseNumber = "";
				sAuthType = "";
			}
		}
		else if( iReturn == -1)
		{
			sMessage = "복호화 시스템 오류입니다.";
		}    
		else if( iReturn == -4)
		{
			sMessage = "복호화 처리 오류입니다.";
		}    
		else if( iReturn == -5)
		{
			sMessage = "복호화 해쉬 오류입니다.";
		}    
		else if( iReturn == -6)
		{
			sMessage = "복호화 데이터 오류입니다.";
		}    
		else if( iReturn == -9)
		{
			sMessage = "입력 데이터 오류입니다.";
		}    
		else if( iReturn == -12)
		{
			sMessage = "사이트 패스워드 오류입니다.";
		}    
		else
		{
			sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
		}
		
		mav.addObject("sMessage", sMessage);		//메시지
		
		return mav;
	}
	
	/**
	 * 대출상담신청 리뉴얼 페이지 step2
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/newRequest_step2")
	public ModelAndView newRequest_step2(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("front/consultation/newRequest_step2");
		JSONObject resultJson = new JSONObject();
		//선택란에서 사용할 MasterCodeVO 마스터코드, 값 가져오기
		MasterCodeVO codeVO = new MasterCodeVO();
		
		//사업자등록 (고객타입)
		codeVO.setGROUP_CODE("MC0000000015");
		List<MasterCodeVO> cust_type = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//업종구분
		codeVO.setGROUP_CODE("MC0000000008");
		List<MasterCodeVO> type_code = commonService.detailMasterCodeByGroupCode(codeVO);

		//취약계층구분
		codeVO.setGROUP_CODE("MC0000000020");
		List<MasterCodeVO> vuln_class = commonService.detailMasterCodeByGroupCode(codeVO);

		//직업구분
		codeVO.setGROUP_CODE("MC0000000019");
		List<MasterCodeVO> job_type = commonService.detailMasterCodeByGroupCode(codeVO);
				
//		String sName = request.getParameter("sName");
//		String sBirthDate = request.getParameter("sBirthDate");
//		String sGender = request.getParameter("sGender");
//		String sMobileNo = request.getParameter("sMobileNo");
		
		/////////////////////////////////////////////////////
		String custNm = request.getParameter("custNm");
		String custRegiNo1 = request.getParameter("custRegiNo1");
		String custRegiNo2 = request.getParameter("custRegiNo2");
		String custMobile = request.getParameter("custMobile");
		mav.addObject("CUST_NM", custNm);				//이름
		mav.addObject("CUST_REGI_NO", custRegiNo1 + custRegiNo2);	//주민번호
		mav.addObject("sBirthDate", custRegiNo1);					//생년월일
		mav.addObject("sGender", custRegiNo2.substring(0, 1));		//성별
		mav.addObject("CUST_HP_NO", custMobile);					//연락처
/////////////////////////////////////////////////////
		
		String regex =  "^(\\d{3})(\\d{4})(\\d+)";
//		String repl = sMobileNo.replaceFirst(regex, "$1-$2-$3");
		String repl = custMobile.replaceFirst(regex, "$1-$2-$3");
		
		//mav.addObject("paramVO", paramVO);							//데이터값
//		mav.addObject("CUST_NM", sName);				//이름
//		mav.addObject("CUST_REGI_NO", paramVO.getCUST_REGI_NO());	//주민번호
//		mav.addObject("sBirthDate", sBirthDate);					//생년월일
//		mav.addObject("sGender", sGender);							//성별
//		mav.addObject("CUST_HP_NO", sMobileNo);						//연락처
		mav.addObject("sMobileNo", repl);							//연락처(-포함)
		mav.addObject("CUST_TYPE", cust_type);						//사업자등록
		mav.addObject("TYPE_CODE", type_code);						//업종구분
		mav.addObject("VULN_CLASS", vuln_class);					//취약계층구분
		mav.addObject("JOB_TYPE", job_type);						//직업구분
		
		mav.addObject("zipcodeConfmKey", zipcodeConfmKey);					//주소api key
		
		//UtilJsonResult.setReturnCodeFail(resultJson);
		UtilJsonResult.setReturnCodeSuc(resultJson);
		
		return mav;
	}
	
	/**
	 * 대출상담신청 리뉴얼 페이지 step3
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/newRequest_step3")
	public ModelAndView newRequest_step3(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("front/consultation/newRequest_step3");
		
		String sBirthDate = request.getParameter("sBirthDate");
		String sGender = request.getParameter("sGender");
		String sMobileNo = request.getParameter("sMobileNo");
		
		String regex =  "^(\\d{3})(\\d{4})(\\d+)";
		String repl = sMobileNo.replaceFirst(regex, "$1-$2-$3");
		
		mav.addObject("sBirthDate", sBirthDate);					//생년월일
		mav.addObject("sGender", sGender);							//성별
		mav.addObject("sMobileNo", repl);							//연락처(-포함)
		mav.addObject("paramVO", paramVO);
		
		return mav;
	}
	
	/**
	 * 대출상담신청 리뉴얼 페이지 step4
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/newRequest_step4")
	public ModelAndView newRequest_step4(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("front/consultation/newRequest_step4");
		
		JSONObject resultJson = new JSONObject();
		
		resultJson = frontConsultationService.frontRequestInsert(paramVO, request);
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * 대출상담신청 리뉴얼 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/consultation/checkplus_fail")
	public ModelAndView checkplus_fail(@ModelAttribute MemberVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("front/consultation/checkplus_fail");
		
		logger.info("checkplus_fail ::::::::::::::::::::::::::::::");
		
	    NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

	    String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

	    String sSiteCode = siteCode;			// NICE로부터 부여받은 사이트 코드
	    String sSitePassword = passWord;		// NICE로부터 부여받은 사이트 패스워드

	    String sCipherTime = "";			// 복호화한 시간
	    String sRequestNumber = "";			// 요청 번호
	    String sErrorCode = "";				// 인증 결과코드
	    String sAuthType = "";				// 인증 수단
	    String sMessage = "";
	    String sPlainData = "";
	    
	    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

	    if( iReturn == 0 )
	    {
	        sPlainData = niceCheck.getPlainData();
	        sCipherTime = niceCheck.getCipherDateTime();
	        
	        // 데이타를 추출합니다.
	        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
	        
	        sRequestNumber 	= (String)mapresult.get("REQ_SEQ");
	        sErrorCode 		= (String)mapresult.get("ERR_CODE");
	        sAuthType 		= (String)mapresult.get("AUTH_TYPE");
	    }
	    else if( iReturn == -1)
	    {
	        sMessage = "복호화 시스템 에러입니다.";
	    }    
	    else if( iReturn == -4)
	    {
	        sMessage = "복호화 처리오류입니다.";
	    }    
	    else if( iReturn == -5)
	    {
	        sMessage = "복호화 해쉬 오류입니다.";
	    }    
	    else if( iReturn == -6)
	    {
	        sMessage = "복호화 데이터 오류입니다.";
	    }    
	    else if( iReturn == -9)
	    {
	        sMessage = "입력 데이터 오류입니다.";
	    }    
	    else if( iReturn == -12)
	    {
	        sMessage = "사이트 패스워드 오류입니다.";
	    }    
	    else
	    {
	        sMessage = "알수 없는 에러 입니다. iReturn : " + iReturn;
	    }
		return mav;
	}
	
	public String requestReplace (String paramValue, String gubun) {

        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	
        	result = paramValue;
            
        }
        return result;
	}
	
}
