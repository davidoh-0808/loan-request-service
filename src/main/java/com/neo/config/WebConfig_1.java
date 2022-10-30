package com.neo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${application.index}")
	private String indexPage;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", indexPage);
	}
	
	@Value("${file.path.external}")
	private String FILE_PATH_EXTERNAL;
	@Value("${file.viewPath.external}")
	private String FILE_VIEWPATH_EXTERNAL;
	
	/**
	 * 외부파일리소스경로설정(에디터이미지 등)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry  registry) {
		registry.addResourceHandler(FILE_VIEWPATH_EXTERNAL + "/**").addResourceLocations("file:" + FILE_PATH_EXTERNAL + "/");
	}
	
	/**
	 * @Date: 2020. 3. 10. 
	 * @title: XSS 필터
	 * @설명: 설명
	 * @return
	 */
	@Bean
	public FilterRegistrationBean<XssEscapeServletFilter> filterRegistrationBean() {
		FilterRegistrationBean<XssEscapeServletFilter> filterRegistration = new FilterRegistrationBean<>();
		filterRegistration.setFilter(new XssEscapeServletFilter());
		filterRegistration.setOrder(2);
		filterRegistration.addUrlPatterns("/*");

		return filterRegistration;
	}
	
	/**
	 * viewResolve(jsonView)
	 * @return
	 */
	@Bean
	public MappingJackson2JsonView jsonView() {
		return new MappingJackson2JsonView();
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(escapingConverter());
	}

	@Bean
	public HttpMessageConverter<?> escapingConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
//		objectMapper.getFactory().setCharacterEscapes(new HTMLCharacterEscapes());

		MappingJackson2HttpMessageConverter escapingConverter = new MappingJackson2HttpMessageConverter();
		escapingConverter.setObjectMapper(objectMapper);

		return escapingConverter;
	}
	
	// 인터셉터
	@Autowired @Qualifier(value="interceptorConfig") private InterceptorConfig interceptorConfig;
	// 어드민인터셉터(특정 IP 외 차단)
	@Autowired @Qualifier(value="interceptorConfigAdmin") private InterceptorConfigAdmin interceptorConfigAdmin;
	
	// 컨설트인터셉터(특정 IP 외 차단)
	@Autowired @Qualifier(value="interceptorConfigConsult") private InterceptorConfigConsult interceptorConfigConsult;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorConfig)
		.addPathPatterns("/front/**")	// 사용자모든 url
		.addPathPatterns("/mobile/**")	// 모바일 모든 url
		;
		registry.addInterceptor(interceptorConfigAdmin)
		.addPathPatterns("/admin/**")	// 관리자모든 url
		;
		registry.addInterceptor(interceptorConfigConsult)
		//.addPathPatterns("/admin/**")	// 관리자모든 url
		.addPathPatterns("/consult/**")	// 컨설트모든 url
		;
	}
	
}