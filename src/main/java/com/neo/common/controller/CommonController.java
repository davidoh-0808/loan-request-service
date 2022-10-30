package com.neo.common.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.service.CommonService;
import com.neo.common.service.EmailService;
import com.neo.common.vo.ConsultInfoVO;
import com.neo.common.vo.MasterCodeVO;
import com.neo.common.vo.SendMailVO;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilOtp;

/**
 * 공통컨트롤러
 * @author leekw
 *
 */
@Controller
public class CommonController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@Resource(name="emailService")
	private EmailService emailService;

	@Autowired LocaleResolver localeResolver;
	@Autowired MessageSource messageSource;
	
	@Value("${company.name}") String COMPANY_NAME;
	
	/**
	 * 에러페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/common/error/{pageId}")
	public ModelAndView errorPage(Locale locale, @PathVariable String pageId){
		ModelAndView mav = new ModelAndView("common/error/" + pageId);
		logger.debug("ERRORPAGE=====================================");
		logger.debug(pageId);
		
		return mav;
	}
	
	/**
	 * csrf 미스매칭
	 * @param locale
	 * @return
	 */
	@RequestMapping(value="/pageAccessDeniedPage")
	public ModelAndView pageAccessDeniedPage(Locale locale){
		ModelAndView mav = new ModelAndView();
		
		logger.debug("시큐어오류:SECURE csrf missmatching");
		
		mav.setViewName("redirect:/login/loginFormMember");
		return mav;
	}
	
	/**
	 * 언어설정변경
	 * @param locale
	 * @param request
	 * @return
	 */
	@PutMapping(value="/common/localeChange")
	public ModelAndView localeChange(Locale locale, HttpServletRequest request){
		ModelAndView mav = new ModelAndView("jsonView");
		// RequestMapingHandler로 부터 받은 Locale 객체를 출력해 봅니다. 
		logger.info("Welcome i18n! The client locale is {}.", locale);
		// localeResolver 로부터 Locale 을 출력해 봅니다.
		logger.info("Session locale is {}.", localeResolver.resolveLocale(request));
		
		return mav;
	}
	
	/**
	 * 상담신청 이메일 발송
	 * @param params
	 * @return
	 */
	
	@PostMapping("/common/sendmail")
	public ModelAndView sendmail(@ModelAttribute SendMailVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject resultJson = new JSONObject();
		resultJson = emailService.sendEmail(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	@PostMapping("/common/sendmailReport")
	public ModelAndView sendmailReport(@ModelAttribute SendMailVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject resultJson = new JSONObject();
		resultJson = emailService.sendEmailReport(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * consult first OTP 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/login/loginFirstOtp")
	public ModelAndView firstOtp(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("login/loginFirstOtp");
		
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		//JSONObject resultJson = new JSONObject();
		
		String companyName = COMPANY_NAME;
		String memberId = adminDetail.getMember_id();
		String memberCode = adminDetail.getMember_code();
		String memberOtp = UtilOtp.getSecretKey();
		
		mav.addObject("memberId", memberId);
		mav.addObject("memberCode", memberCode);
		mav.addObject("otpKey", memberOtp);
		
		// 인증 QR코드 생성 :: 발급자(회사명), 계정명(유저ID), 개인키  
		String qrOtpUrl = UtilOtp.getQRBarcodeURL(companyName, memberId, memberOtp);

		// 인증 URL 생성 :: 개인키, 계정명(유저ID), 발급자(회사명)
		//String qrOtpUrl = UtilOtp.getOtpAuthURL(memberId, companyName, memberOtp);
		
		mav.addObject("qrOtpUrl", qrOtpUrl);

		return mav;
	}
	
	/**
	 * consult OTP 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/login/loginOtp")
	public ModelAndView loginOtp(HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("login/loginOtp");
		
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
//		JSONObject resultJson = new JSONObject();
		
		String memberId = adminDetail.getMember_id();
		String memberCode = adminDetail.getMember_code();
		String memberOtp = adminDetail.getMember_otp();
		
		
		mav.addObject("memberCode", memberCode);
		mav.addObject("memberId", memberId);
		mav.addObject("otpKey", memberOtp);
		
		return mav;
	}
	
	/**
	 * 지점 자동검색에 사용
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/common/searchBranch")
	public ModelAndView searchBranch(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception{

		ModelAndView mav = new ModelAndView("jsonView");
		MasterCodeVO codeVo = new MasterCodeVO();
		
		String branchNm = paramVO.getBRANCH_NAME().substring(0, 2)+'%';
		
		codeVo = commonService.searchBranch(branchNm);
		
		mav.addObject("codeVo", codeVo);
		
		return mav;
	}
}
