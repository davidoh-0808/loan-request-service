package com.neo.config;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.view.UrlMappingInfo;
import com.neo.common.vo.ReportVO;
import com.neo.mappers.ReportMapper;
import com.neo.util.UtilCommon;

@Component
public class InterceptorConfig implements HandlerInterceptor {
	

	private static final Logger logger = LoggerFactory.getLogger(InterceptorConfig.class);
	
	@Resource(name="reportMapper")
	private ReportMapper reportMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		ReportVO paramVO = new ReportVO();
		
		String url = request.getRequestURI();
		logger.info("Interceptor url ::::::"+url);
		
		// url 설정에 포함될경우 등록될 url 가공
		for(String key : UrlMappingInfo.urlInfo.keySet()) {
			if(url.indexOf(key) >= 0) {
				paramVO.setREPO_PATH(key);
				break;
			}
		}
		
		boolean device = UtilCommon.isMobile(request);
		logger.info(String.valueOf(device));
		String deviceTemp;
		if (device) {
			deviceTemp = "MC0000200002";
		}
		else 
		{ 
			deviceTemp = "MC0000200001";
		}
		paramVO.setREPO_DEVICE(deviceTemp);
		
		String client_ip = UtilCommon.getClientIP(request);
		logger.info(client_ip);
		paramVO.setREPO_IP(client_ip);
		
		
		try {
			if(UtilCommon.isNotEmpty(paramVO.getREPO_PATH())) {
				reportMapper.reportInsert(paramVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
