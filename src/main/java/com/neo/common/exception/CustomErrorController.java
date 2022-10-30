package com.neo.common.exception;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.util.UtilCommon;
import com.neo.util.UtilJsonResult;

@Controller
public class CustomErrorController implements ErrorController{
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Value("${server.domain.pc}") String SERVER_DOMAIN_PC;
	@Value("${server.domain.mobile}") String SERVER_DOMAIN_MOBILE;
	@Value("${pageurl.login.member}") String PAGEURL_LOGIN_MEMBER;
	@Value("${pageurl.login.admin}") String PAGEURL_LOGIN_ADMIN;
	@Value("${pageurl.login.mobile}") String PAGEURL_LOGIN_MOBILE;
	@Value("${pageurl.login.consult}") String PAGEURL_LOGIN_CONSULT;
	
	@RequestMapping("${server.error.path:${error.path:/error}}")
	public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		String errorMsg = RequestDispatcher.ERROR_MESSAGE;
		
		ModelAndView mav = new ModelAndView();
		
		String ajaxHeader = request.getHeader("X-Requested-With");

//		logger.info("############# ajaxHeader : " + ajaxHeader);
//		logger.info("############# status : " + status);

		if ("XMLHttpRequest".equals(ajaxHeader)) {
			try {
				response.sendError(Integer.valueOf(String.valueOf(status)), errorMsg);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return mav;
		}
		
		if(UtilCommon.isMobile(request)) {	// 모바일
			if(UtilCommon.isNotEmpty(status)) {
				Integer statusCode = Integer.valueOf(status.toString());
				logger.info("Mobile error >>>>>>>> "+statusCode + request.getRequestURI());
				
				if(statusCode == HttpStatus.NOT_FOUND.value()) {
					//logger.info("404 not found");
					mav.setViewName("common/error/mobile/404error");
				}else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
					//logger.info("500 internal Server Error");
					mav.setViewName("common/error/mobile/500error");
				}else if("4".equalsIgnoreCase(Integer.toString(statusCode).substring(0, 1))){
					//logger.info("400번대 error");
					mav.setViewName("common/error/mobile/404error");
				}else if("5".equalsIgnoreCase(Integer.toString(statusCode).substring(0, 1))){
					//logger.info("500번대 error");
					mav.setViewName("common/error/mobile/500error");
				}else if(statusCode == 601){
					//logger.info("멤버권한이 없습니다(모바일)");
					mav.setViewName("redirect:"+SERVER_DOMAIN_MOBILE + PAGEURL_LOGIN_MOBILE);
				}else if(statusCode == 602){
					//logger.info("멤버권한이 없습니다(모바일)");
					mav.setViewName("redirect:"+SERVER_DOMAIN_MOBILE + PAGEURL_LOGIN_MOBILE);
				}else if(statusCode == 603){
					//logger.info("관리자권한이 없습니다(모바일)";
					mav.setViewName("redirect:"+SERVER_DOMAIN_MOBILE + PAGEURL_LOGIN_MOBILE);
				}else if(statusCode == 604){
					//logger.info("관리자권한이 없습니다(모바일)";
					mav.setViewName("redirect:"+SERVER_DOMAIN_MOBILE + PAGEURL_LOGIN_MOBILE);
				}else {
					mav.setViewName("mobile/common/error/etcerror");
				}
			}
		}else {			// PC
			if(UtilCommon.isNotEmpty(status)) {
				Integer statusCode = Integer.valueOf(status.toString());
				logger.info("error >>>>>>>> "+statusCode + request.getRequestURI());
				
				if(statusCode == HttpStatus.NOT_FOUND.value()) {
					//logger.info("404 not found");
					mav.setViewName("redirect:/common/error/404error");
				}else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
					//logger.info("500 internal Server Error");
					mav.setViewName("redirect:/common/error/500error");
				}else if("4".equalsIgnoreCase(Integer.toString(statusCode).substring(0, 1))){
					//logger.info("400번대 error");
					mav.setViewName("redirect:/common/error/404error");
				}else if("5".equalsIgnoreCase(Integer.toString(statusCode).substring(0, 1))){
					//logger.info("500번대 error");
					mav.setViewName("redirect:/common/error/500error");
				}else if(statusCode == 601){
					//logger.info("멤버권한이 없습니다");
					mav.setViewName("redirect:"+SERVER_DOMAIN_PC + PAGEURL_LOGIN_MEMBER);
				}else if(statusCode == 602){
					//logger.info("멤버권한이 없습니다");
					mav.setViewName("redirect:"+SERVER_DOMAIN_PC + PAGEURL_LOGIN_MEMBER);
				}else if(statusCode == 603){
					//logger.info("관리자권한이 없습니다");
					mav.setViewName("redirect:"+SERVER_DOMAIN_PC + PAGEURL_LOGIN_ADMIN);
				}else if(statusCode == 604){
					//logger.info("관리자권한이 없습니다");
					mav.setViewName("redirect:"+SERVER_DOMAIN_PC + PAGEURL_LOGIN_CONSULT);
				}else if(statusCode == 9900){
					mav.setViewName("jsonView");
					JSONObject json = new JSONObject();
					UtilJsonResult.setReturnCodeEtc(json, String.valueOf(statusCode), errorMsg);
					mav.addObject("errJson", json);
				}else {
					mav.setViewName("redirect:/common/error/etcerror");
				}
			}
		}
		
		return mav;
	}
}
