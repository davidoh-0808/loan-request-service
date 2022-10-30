package com.neo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 로그인 실패 핸들러
 * 
 * @author wedul
 *
 */
@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private String member_id;
	private String exceptionMsg;
	
	@Value("${mastercode.member_gubun.admin}") String MASTERCODE_MEMBER_GUBUN_ADMIN;
	@Value("${mastercode.member_gubun.member}") String MASTERCODE_MEMBER_GUBUN_MEMBER;
	@Value("${mastercode.member_gubun.consult}") String MASTERCODE_MEMBER_GUBUN_CONSULT;
	
	@Value("${mastercode.platform_gubun.pc}") String MASTERCODE_PLATFORM_GUBUN_PC;
	@Value("${mastercode.platform_gubun.mobile}") String MASTERCODE_PLATFORM_GUBUN_MOBILE;
	
	@Value("${pageurl.login.member}") String PAGEURL_LOGIN_MEMBER;
	@Value("${pageurl.login.admin}") String PAGEURL_LOGIN_ADMIN;
	@Value("${pageurl.login.mobile}") String PAGEURL_LOGIN_MOBILE;
	@Value("${pageurl.login.consult}") String PAGEURL_LOGIN_CONSULT;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		member_id = request.getParameter("MEMBER_ID");
		exceptionMsg = exception.getMessage();
		
		request.setAttribute("member_id", member_id);
		request.setAttribute("exceptionMsg", exceptionMsg);
		
		String member_gubun = request.getParameter("member_gubun");			// 유저구분: member, admin
		String platform_gbn = request.getParameter("platform_gbn");	// 플랫폼구분: pc, mobile
		
		logger.info("member_gubun:"+member_gubun + "     platform_gbn:"+platform_gbn);
		
		// 회원
		if(MASTERCODE_MEMBER_GUBUN_MEMBER.equals(member_gubun)) {
			if(MASTERCODE_PLATFORM_GUBUN_PC.equals(platform_gbn)) {
				request.getRequestDispatcher(PAGEURL_LOGIN_MEMBER).forward(request, response);
			} else if(MASTERCODE_PLATFORM_GUBUN_MOBILE.equals(platform_gbn)) {
				request.getRequestDispatcher(PAGEURL_LOGIN_ADMIN).forward(request, response);
			} else {
				request.getRequestDispatcher(PAGEURL_LOGIN_MEMBER).forward(request, response);
			}
		// 관리자
		} else if(MASTERCODE_MEMBER_GUBUN_ADMIN.equals(member_gubun)) {
			request.getRequestDispatcher(PAGEURL_LOGIN_ADMIN).forward(request, response);
			
		// 상담팀 관련자...
		} else if(MASTERCODE_MEMBER_GUBUN_CONSULT.equals(member_gubun)) {
			request.getRequestDispatcher(PAGEURL_LOGIN_CONSULT).forward(request, response);
			
		} else {
			request.getRequestDispatcher(PAGEURL_LOGIN_MEMBER).forward(request, response);
		}
		
	}

	public RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getExceptionMsg() {
		return exceptionMsg;
	}

	public void setExceptionMsg(String exceptionMsg) {
		this.exceptionMsg = exceptionMsg;
	}
	
	
}
