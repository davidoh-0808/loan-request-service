package com.neo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.neo.security.AccessDeniedHandlerImple;
import com.neo.security.AjaxAwareAuthenticationEntryPoint;
import com.neo.security.AuthFailureHandler;
import com.neo.security.AuthSuccessHandler;
import com.neo.security.CustomLogoutHandler;
import com.neo.security.SecuritySessionExpiredStrategy;


@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@ComponentScan(basePackages = { "com.neo.*" })
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	AuthenticationProvider authProvider;

	@Autowired
	AuthFailureHandler authFailureHandler;

	@Autowired
	AuthSuccessHandler authSuccessHandler;

	@Autowired
	CustomLogoutHandler customLogoutHandler;

	@Autowired
	SecuritySessionExpiredStrategy securitySessionExpiredStrategy;

	public WebSecurityConfig() {
		super();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/common/editorfileUpload");
		super.configure(web);
		web.httpFirewall(allowUrlEncodeSlashHttpFirewall());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 로그인 설정
		http.authorizeRequests()
			.antMatchers("/admin/**").hasAnyRole("ADMIN")
//			.antMatchers("/member/**").hasAnyRole("MEMBER")
//			.antMatchers("/mobile/**").hasAnyRole("MOBILE")
			.antMatchers("/consult/**").hasAnyRole("CONSULT")
			.antMatchers("/**").permitAll().anyRequest()
			.authenticated();
		
		// 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의
		http.formLogin()
			.loginPage("/login/loginFormConsult")
			.usernameParameter("MEMBER_ID")
			.passwordParameter("MEMBER_OTP")
			.passwordParameter("MEMBER_PW")
			.loginProcessingUrl("/login/loginProcess")
			.failureHandler(authFailureHandler)
			.successHandler(authSuccessHandler)
			.permitAll();

		// 로그인 페이지 및 성공 url, handler 그리고 로그인 시 사용되는 id, password 파라미터 정의
		http.formLogin()
			.loginPage("/login/loginFormAdmin")
			.usernameParameter("MEMBER_ID")
			.passwordParameter("MEMBER_PW")
			.loginProcessingUrl("/login/loginProcess")
			.failureHandler(authFailureHandler)
			.successHandler(authSuccessHandler)
			.permitAll();

		// 로그아웃 관련 설정
		http.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/login/logoutProcess"))
			.logoutSuccessUrl("/login/loginFormConsult")
			.logoutSuccessUrl("/login/loginFormAdmin")
			.invalidateHttpSession(true)
			.logoutSuccessHandler(customLogoutHandler)
			.deleteCookies("JSESSIONID");
		
		http.csrf().ignoringAntMatchers("/login/loginProcess", "/join/joinFormMember", "/join/joinFormMobile", "/login/logoutProcess")		//csrf예외처리
			.and().exceptionHandling()
			.authenticationEntryPoint(new AjaxAwareAuthenticationEntryPoint("/pageAccessDeniedPage"))
			.accessDeniedHandler(new AccessDeniedHandlerImple())
			.and().sessionManagement()
			.maximumSessions(1)
			.maxSessionsPreventsLogin(false)
			.expiredSessionStrategy(securitySessionExpiredStrategy.setDefaultUrl("/login/loginDeplication"))
			.sessionRegistry(customSessionRegistry());

		http.headers().frameOptions().deny().disable();
		http.headers(headers -> headers .cacheControl(cache -> cache.disable()) );

	}

	@Bean
	public SessionRegistry customSessionRegistry() {
		SessionRegistry sessionRegistry = new SessionRegistryImpl();
		return sessionRegistry;
	}

	@Bean
	public static ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
		return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.userDetailsService(companyLoginService).passwordEncoder(passwordEncoder());
		auth.authenticationProvider(authProvider);
	}

	@Bean
	public HttpFirewall allowUrlEncodeSlashHttpFirewall() {
		StrictHttpFirewall firewall = new StrictHttpFirewall();
		firewall.setAllowUrlEncodedSlash(true);
		firewall.setAllowSemicolon(true);
		return firewall;
	}

}
