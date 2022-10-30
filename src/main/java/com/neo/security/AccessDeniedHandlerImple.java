package com.neo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;


@Component
public class AccessDeniedHandlerImple implements AccessDeniedHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired	MessageSource messageSource;

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		
		boolean isMemberLogin = request.getRequestURI().startsWith("/member/");
		boolean isAdminLogin = request.getRequestURI().startsWith("/admin/");
		boolean isMobileLogin = request.getRequestURI().startsWith("/mobile/");
		boolean isConsultLogin = request.getRequestURI().startsWith("/consult/");
		
		logger.debug("request.getRequestURI() : " + request.getRequestURI());

		if (isMemberLogin) {
			response.sendError(601, "회원권한이 없습니다");
		}
		
		if (isMobileLogin) {
			response.sendError(602, "모바일권한이 없습니다.");
		}

		if (isAdminLogin) {
			response.sendError(603, "관리자권한이 없습니다.");
		}
		
		if (isConsultLogin) {
			response.sendError(604, "권한이 없습니다.");
		}
		
	}
	


}