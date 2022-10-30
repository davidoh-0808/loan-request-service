package com.neo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

/**
 * 로그인성공핸들러
 * @author leekw
 *
 */
@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Value("${mastercode.member_gubun.admin}") String MASTERCODE_MEMBER_GUBUN_ADMIN;
	@Value("${mastercode.member_gubun.member}") String MASTERCODE_MEMBER_GUBUN_MEMBER;
	@Value("${mastercode.member_gubun.consult}") String MASTERCODE_MEMBER_GUBUN_CONSULT;
	
	@Value("${mastercode.platform_gubun.pc}") String MASTERCODE_PLATFORM_GUBUN_PC;
	@Value("${mastercode.platform_gubun.mobile}") String MASTERCODE_PLATFORM_GUBUN_MOBILE;
	
	@Value("${pageurl.index.member}") String PAGEURL_INDEX_MEMBER;
	@Value("${pageurl.index.admin}") String PAGEURL_INDEX_ADMIN;
	@Value("${pageurl.index.mobile}") String PAGEURL_INDEX_MOBILE;
	@Value("${pageurl.index.dashboard}") String PAGEURL_INDEX_DASHBOARD;
	
	@Value("${pageurl.login.firstotp}") String PAGEURL_LOGIN_FIRSTOTP;
	@Value("${pageurl.login.loginotp}") String PAGEURL_LOGIN_LOGINOTP;
	
	@Value("${server.domain.pc}") String SERVER_DOMAIN_PC;
	@Value("${server.domain.mobile}") String SERVER_DOMAIN_MOBILE;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		//super.onAuthenticationSuccess(request, response, authentication);
		resultRedirectStrategy(request, response, authentication);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		String member_gubun = request.getParameter("member_gubun");			// 유저구분: member, admin
		String platform_gbn = request.getParameter("platform_gbn");	// 플랫폼구분: pc, mobile
		
		logger.info(">>>member_gubun>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+member_gubun);
		logger.info(">>>platform_gbn>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+platform_gbn);
		logger.info(">>>savedRequest>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+savedRequest);

		
		// 회원
		if(MASTERCODE_MEMBER_GUBUN_MEMBER.equals(member_gubun)) {
			if(savedRequest != null) {
				String targetUrl = savedRequest.getRedirectUrl();
				logger.info(">>targetUrl>>>>>MEMBER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+targetUrl);
				redirectStrategy.sendRedirect(request, response, targetUrl);
			} else {
				if(MASTERCODE_PLATFORM_GUBUN_PC.equals(platform_gbn)) {
					redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_INDEX_MEMBER);
				} else if(MASTERCODE_PLATFORM_GUBUN_MOBILE.equals(platform_gbn)) {
					redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_MOBILE + PAGEURL_INDEX_MOBILE);
				} else {
					redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_INDEX_MEMBER);
				}
			}
		// 관리자
		} else if(MASTERCODE_MEMBER_GUBUN_ADMIN.equals(member_gubun)) {
			if(savedRequest != null) {
				String targetUrl = savedRequest.getRedirectUrl();
				logger.info(">>targetUrl>>>>>ADMIN>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+targetUrl);
				redirectStrategy.sendRedirect(request, response, targetUrl);
			} else {
				if(MASTERCODE_PLATFORM_GUBUN_PC.equals(platform_gbn)) {
					redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_INDEX_ADMIN);
				} else if(MASTERCODE_PLATFORM_GUBUN_MOBILE.equals(platform_gbn)) {
					redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_MOBILE + PAGEURL_INDEX_MOBILE);
				} else {
					redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_INDEX_ADMIN);
				}
			}
		// 리뉴얼_상담팀
		} else if(MASTERCODE_MEMBER_GUBUN_CONSULT.equals(member_gubun)) {
			
			CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
			
			if(savedRequest != null) {
				String targetUrl = savedRequest.getRedirectUrl();
				logger.info(">>targetUrl>>>>CONSULT>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+targetUrl);
				redirectStrategy.sendRedirect(request, response, targetUrl);
			} else {
				if(MASTERCODE_PLATFORM_GUBUN_PC.equals(platform_gbn)) {
					
					redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_INDEX_DASHBOARD);
					
				} else if(MASTERCODE_PLATFORM_GUBUN_MOBILE.equals(platform_gbn)) {
					
					redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_MOBILE + PAGEURL_INDEX_MOBILE);
					
				} else {
					
					redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_INDEX_DASHBOARD);
				}
				//redirectStrategy.sendRedirect(request, response, SERVER_DOMAIN_PC + PAGEURL_INDEX_DASHBOARD);
			}
		} else {
			if(savedRequest != null) {
				String targetUrl = savedRequest.getRedirectUrl();
				logger.info(">>targetUrl>>>>>else>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+targetUrl);
				redirectStrategy.sendRedirect(request, response, targetUrl);
			} else {
				redirectStrategy.sendRedirect(request, response, "/");
			}
		}
	}
}
