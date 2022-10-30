package com.neo.config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.neo.mappers.ReportMapper;
import com.neo.security.CustomAdminDetails;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilCommon;

@Component
public class InterceptorConfigAdmin implements HandlerInterceptor {
	

	private static final Logger logger = LoggerFactory.getLogger(InterceptorConfigAdmin.class);
	
	@Resource(name="reportMapper")
	private ReportMapper reportMapper;
	
	@Value("${pageurl.index.admin}") private String PAGEURL_INDEX_ADMIN;
	
	@Value("${mastercode.member_gubun.admin}") String MASTERCODE_MEMBER_GUBUN_ADMIN;
	@Value("${mastercode.member_gubun.member}") String MASTERCODE_MEMBER_GUBUN_MEMBER;
	@Value("${mastercode.member_gubun.consult}") String MASTERCODE_MEMBER_GUBUN_CONSULT;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		
		String url = request.getRequestURI();
		
		int matchedcnt = 0;
		
		// 관리자 로그인
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		String auth = adminDetail.getPlatform_auth();
		String auths[] = auth.split(",");
		
		if(url.indexOf("/admin/member") >= 0) {
			for(String item : auths) {
				if("ACCOUNT_USE".equalsIgnoreCase(item)) {
					matchedcnt = matchedcnt + 1;
				};
			}
		}
		if(url.indexOf("/admin/board/thanks") >= 0) {
			for(String item : auths) {
				if("THANKS_USE".equalsIgnoreCase(item)) {
					matchedcnt = matchedcnt + 1;
				};
			}
		}
		if(url.indexOf("/admin/board/news") >= 0) {
			for(String item : auths) {
				if("NEWS_USE".equalsIgnoreCase(item)) {
					matchedcnt = matchedcnt + 1;
				};
			}
		}
		if(url.indexOf("/admin/board/finan") >= 0) {
			for(String item : auths) {
				if("FINAN_USE".equalsIgnoreCase(item)) {
					matchedcnt = matchedcnt + 1;
				};
			}
		}
		if(url.indexOf("/admin/report") >= 0) {
			for(String item : auths) {
				if("REPORT_USE".equalsIgnoreCase(item)) {
					matchedcnt = matchedcnt + 1;
				};
			}
		}
		
		if(0 == matchedcnt) {
			response.sendRedirect(PAGEURL_INDEX_ADMIN);
		}
			
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
	
}
