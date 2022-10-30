package com.neo.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

@Component
public class SecuritySessionExpiredStrategy implements SessionInformationExpiredStrategy {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
//	CustomConsultDetails consultDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
//	
//	@Value("${mastercode.member_gubun.admin}") String MASTERCODE_MEMBER_GUBUN_ADMIN;
//	@Value("${mastercode.member_gubun.member}") String MASTERCODE_MEMBER_GUBUN_MEMBER;
//    @Value("${mastercode.member_gubun.consult}") String MASTERCODE_MEMBER_GUBUN_CONSULT;
    
	@Autowired
	MessageSource messageSource;

	private String defaultUrl;

	public String getDefaultURl() {
		return defaultUrl;
	}

	public SecuritySessionExpiredStrategy setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
		return this;
	}

	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		HttpServletResponse response = event.getResponse();
		HttpServletRequest request = event.getRequest();
		Locale locale = LocaleContextHolder.getLocale();

		String ajaxHeader = ((HttpServletRequest) request).getHeader("X-Requested-With");

		logger.info("############# ajaxHeader orignal : " + ajaxHeader);

		if ("XMLHttpRequest".equals(ajaxHeader)) {
			response.sendError(9900, messageSource.getMessage("message.loginDeplication", null, locale));
		} else {
			

			logger.info("############# ajaxHeader DEFAULT~~~~~~~~: " + ajaxHeader);
			// 회원 로그인
			//if (MASTERCODE_MEMBER_GUBUN_MEMBER.equals(consultDetail)) {
			response.sendRedirect(defaultUrl);

		}

	}

}