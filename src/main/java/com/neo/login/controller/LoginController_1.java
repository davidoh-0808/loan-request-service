package com.neo.login.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.vo.ConsultMemberVO;
import com.neo.login.service.OtpLoginService;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilOtp;

/**
 * 멤버로그인
 * @author leekw
 *
 */
@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired MessageSource messageSource;
	@Autowired LocaleResolver localeResolver;
	@Resource(name="OtpLoginService")
	private OtpLoginService otpLoginService;
	
	@Value("${pageurl.login.admin}") String LOGIN_ADMIN;
	@Value("${pageurl.index.dashboard}") String DASHBOARD;
	@Value("${company.homepage}") String HOMEPAGE;
	@Value("${company.name}") String COMPANY_NAME;
	
	/**
	 * 멤버로그인페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/login/loginFormMember")
	public ModelAndView loginFormMember() throws Exception{
		ModelAndView mav = new ModelAndView("login/loginFormMember");
		return mav;
	}
	
	
	/**
	 * 어드민로그인페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/login/loginFormAdmin")
	public ModelAndView loginFormAdmin() throws Exception{
		ModelAndView mav = new ModelAndView("login/loginFormAdmin");
		return mav;
	}
	
	/**
	 * consult 어드민로그인페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/login/loginFormConsult")
	public ModelAndView loginFormConsult() throws Exception{
		ModelAndView mav = new ModelAndView("login/loginFormConsult");
		return mav;
	}
	
	/**
	 * 중복로그인시 기 로그인자에게 메세지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/login/loginDeplication")
	public ModelAndView loginDeplication(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("login/loginDeplication");
		
		String rMsg = messageSource.getMessage("validation.login.deplication", null, localeResolver.resolveLocale(request));
		logger.debug("rMsg:"+rMsg);
		mav.addObject("sendMsg", rMsg);
		mav.addObject("toward", LOGIN_ADMIN);
		
		return mav;
	}
	
	
}
