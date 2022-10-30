package com.neo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomLogoutHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Autowired	MessageSource messageSource;
	
//	@Resource(name="commonMapper")
//	private CommonMapper commonMapper;
	
	@Value("${mastercode.member_gubun.admin}") String MASTERCODE_MEMBER_GUBUN_ADMIN;
	@Value("${mastercode.member_gubun.member}") String MASTERCODE_MEMBER_GUBUN_MEMBER;
	@Value("${mastercode.member_gubun.consult}") String MASTERCODE_MEMBER_GUBUN_CONSULT;
	
	@Value("${mastercode.platform_gubun.pc}") String MASTERCODE_PLATFORM_GUBUN_PC;
	@Value("${mastercode.platform_gubun.mobile}") String MASTERCODE_PLATFORM_GUBUN_MOBILE;
	
	@Value("${pageurl.login.member}") String PAGEURL_LOGIN_MEMBER;
	@Value("${pageurl.login.admin}") String PAGEURL_LOGIN_ADMIN;
	@Value("${pageurl.login.mobile}") String PAGEURL_LOGIN_MOBILE;
	@Value("${pageurl.login.consult}") String PAGEURL_LOGIN_CONSULT;
	
	@Value("${server.domain.pc}") String SERVER_DOMAIN_PC;
	@Value("${server.domain.mobile}") String SERVER_DOMAIN_MOBILE;
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication arg2)
			throws IOException, ServletException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		String platform_gbn = request.getParameter("platform_gbn");	// 플랫폼구분: pc, mobile
		String member_gubun = request.getParameter("member_gubun");			// 유저구분: member, admin
		
		logger.info("member_gubun:"+member_gubun + "     platform_gbn:"+platform_gbn);
		
		if (arg2 == null) {
		
			redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_LOGIN_CONSULT);
		
		} else {
			
			// 멤버 로그아웃
			try {
				CustomMemberDetails memberDetails = (CustomMemberDetails) arg2.getDetails();
				String member_code = memberDetails.getMember_code();
				
				// TODO: 로그아웃시 처리할내용
				
				// 로그아웃성공시 이동할 페이지
//				if(MASTERCODE_PLATFORM_GUBUN_PC.equalsIgnoreCase(memberDetails.getPlatform_gbn())){
//					redirectStrategy.sendRedirect(request, response, PAGEURL_LOGIN_MEMBER);
//				}else if(MASTERCODE_PLATFORM_GUBUN_MOBILE.equalsIgnoreCase(memberDetails.getPlatform_gbn())){
//					redirectStrategy.sendRedirect(request, response, PAGEURL_LOGIN_MOBILE);
//				}else {
//					redirectStrategy.sendRedirect(request, response, PAGEURL_LOGIN_MEMBER);
//				}
				redirectStrategy.sendRedirect(request, response, PAGEURL_LOGIN_MEMBER);
				
				
			} catch (Exception e) {
//				e.printStackTrace();
			}

			// 관리자 로그아웃
			try {
				
				CustomConsultDetails adminDetails = (CustomConsultDetails) arg2.getDetails();
				String member_authority = adminDetails.getMember_authority();
				
				// TODO: 로그아웃시 처리할내용
				//redirectStrategy.sendRedirect(request, response, PAGEURL_LOGIN_ADMIN);
				if(MASTERCODE_MEMBER_GUBUN_ADMIN.equals(member_authority)) {
					if(savedRequest != null) {
						String targetUrl = savedRequest.getRedirectUrl();
						redirectStrategy.sendRedirect(request, response, targetUrl);
					} else {
						if(MASTERCODE_PLATFORM_GUBUN_PC.equals(platform_gbn)) {
							redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_LOGIN_ADMIN);
						} else if(MASTERCODE_PLATFORM_GUBUN_MOBILE.equals(platform_gbn)) {
							redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_MOBILE + PAGEURL_LOGIN_MOBILE);
						} else {
							redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_LOGIN_ADMIN);
						}
					}
				// 
				} else {
					
					if(savedRequest != null) {
						String targetUrl = savedRequest.getRedirectUrl();
						redirectStrategy.sendRedirect(request, response, targetUrl);
					} else {
						if(MASTERCODE_PLATFORM_GUBUN_PC.equals(platform_gbn)) {
							redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_LOGIN_CONSULT);
						} else if(MASTERCODE_PLATFORM_GUBUN_MOBILE.equals(platform_gbn)) {
							redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_MOBILE + PAGEURL_LOGIN_CONSULT);
						} else {
							redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_LOGIN_CONSULT);
						}
					}
				}
				
			} catch (Exception e) {
//				e.printStackTrace();
			}
		}

		super.onLogoutSuccess(request, response, arg2);
	}

}
