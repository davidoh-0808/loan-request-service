package com.neo.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


//LoginUrlAuthenticationEntryPoint

public class AjaxAwareAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired	MessageSource messageSource;
	
	String localLoginFormUrl;

	public String getLocalLoginFormUrl() {
		return localLoginFormUrl;
	}

	public void setLocalLoginFormUrl(String localLoginFormUrl) {
		this.localLoginFormUrl = localLoginFormUrl;
	}

	public AjaxAwareAuthenticationEntryPoint(String loginFormUrl) {
		this.localLoginFormUrl = loginFormUrl;
	}

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		Locale locale = LocaleContextHolder.getLocale();
		
		String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");

		logger.info("############# AjaxAwareAuthenticationEntryPoint ajaxHeader : " + ajaxHeader);

		if ("XMLHttpRequest".equals(ajaxHeader)) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Ajax Request Denied (Session Expired)");
		} else {
			boolean isMemberLogin = request.getRequestURI().startsWith("/member/");
			boolean isAdminLogin = request.getRequestURI().startsWith("/admin/");
			boolean isMobileLogin = request.getRequestURI().startsWith("/mobile/");
			boolean isConsultLogin = request.getRequestURI().startsWith("/consult/");
			
			logger.debug("request.getRequestURI() AjaxAwareAuthenticationEntryPoint :: " + request.getRequestURI());

			if (isMemberLogin) {
				response.sendError(601, "회원권한이 없습니다");
//				response.sendError(601, messageSource.getMessage("message.accessDenied.member", null, locale));
			}
			
			if (isMobileLogin) {
				response.sendError(602, "모바일권한이 없습니다.");
//				response.sendError(603, messageSource.getMessage("message.accessDenied.admin", null, locale));
			}

			if (isAdminLogin) {
				response.sendError(603, "관리자권한이 없습니다.");
//				response.sendError(603, messageSource.getMessage("message.accessDenied.admin", null, locale));
			}
			
			if (isConsultLogin) {
				response.sendError(604, "권한이 없습니다.");
//				response.sendError(603, messageSource.getMessage("message.accessDenied.admin", null, locale));
			}
			
		}
	}
}