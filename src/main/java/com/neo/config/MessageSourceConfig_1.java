package com.neo.config;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import dev.akkinoc.util.YamlResourceBundle;


/**
 * @author leekw
 * message.yml 사용함
 */
@Configuration
public class MessageSourceConfig implements WebMvcConfigurer {
	
	@Value("${spring.messages.basename}") String basename;
	@Value("${spring.messages.encoding}") String encoding;
	@Value("${spring.messages.cache-duration}") Integer cacheDuration;
	@Value("${spring.messages.use-code-as-default-message}") Boolean useCodeAsDefaultMessage;
	
	@Bean // 세션에 지역설정. default는 KOREAN = 'ko'
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.KOREAN);
		return slr;
	}

	@Bean // 지역설정을 변경하는 인터셉터. 요청시 파라미터에 lang 정보를 지정하면 언어가 변경됨.
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override // 인터셉터를 시스템 레지스트리에 등록
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean // yml 파일을 참조하는 MessageSource 선언
	public MessageSource messageSource() {
		YamlMessageSource ms = new YamlMessageSource();
		ms.setBasename(basename);
		ms.setDefaultEncoding(encoding);
		ms.setUseCodeAsDefaultMessage(useCodeAsDefaultMessage);
		ms.setCacheSeconds(cacheDuration);
		ms.setAlwaysUseMessageFormat(true);
		ms.setFallbackToSystemLocale(true);
		return ms;
	}

	// locale 정보에 따라 다른 yml 파일을 읽도록 처리
	private static class YamlMessageSource extends ResourceBundleMessageSource {
		@Override
		protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
//			logger.info("로케일:"+ locale.toString());
			return ResourceBundle.getBundle(basename, locale, YamlResourceBundle.Control.INSTANCE);
		}
	}
}