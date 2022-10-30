package com.neo.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.neo.security.CustomConsultDetails;

@Component
public class InterceptorConfigConsult implements HandlerInterceptor {
	

	private static final Logger logger = LoggerFactory.getLogger(InterceptorConfigConsult.class);
	
	
	@Value("${pageurl.index.dashboard}") private String PAGEURL_INDEX_DASHBOARD;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		CustomConsultDetails consultDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		String url = request.getRequestURI();
		
		String auth = consultDetail.getPlatform_auth();
		String auths[] = auth.split(",");

		int matchedcnt = 0;

		// 대시보드 페이지, 기능 url
		if (url.indexOf("/consult/main") >= 0) {
			matchedcnt = matchedcnt + 1;
		}

        // 상담신청내역 페이지, 기능 url
        if (url.indexOf("/consult/consult") >= 0) {
            matchedcnt = matchedcnt + 1;
        }

        // 대표번호 승인현황 페이지, 기능 url
        if (url.indexOf("/consult/board/confirm") >= 0) {
            matchedcnt = matchedcnt + 1;
        }
        
        //영업현황 페이지, 기능 url
        if (url.indexOf("/consult/board/sales") >= 0) {
            matchedcnt = matchedcnt + 1;
        }
        
		//시스템 - 계정권한관리/세부항목관리 페이지, 기능 url
		if (url.indexOf("/consult/system") >= 0) {
			matchedcnt = matchedcnt + 1;
		}

        //마이페이지, 기능 url
        if (url.indexOf("/consult/user") >= 0) {
            matchedcnt = matchedcnt + 1;
        }

        //멤버 otp 재발송, 계정휴면 및 휴면해제 기능
        if (url.indexOf("/consult/member") >= 0) {
			matchedcnt = matchedcnt + 1;
		}

		if(0 == matchedcnt) {
			response.sendRedirect(PAGEURL_INDEX_DASHBOARD);
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
