package com.neo.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.apache.tomcat.util.scan.StandardJarScanner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class TomcatSslConfig {
	
	@Value("${server.porthttp}")
	private int serverPortHttp;
	
	@Value("${server.port}")
	private int serverPortHttps;
	
	@Bean
	public ServletWebServerFactory servletContainer() {
		
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
				
				 ((StandardJarScanner) context.getJarScanner()).setScanManifest(false);
			}
		};
		
		tomcat.addAdditionalTomcatConnectors(createSslConnector());
		return tomcat;
	}
	
	private Connector createSslConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");

		connector.setScheme("http");
		connector.setSecure(false);
		connector.setPort(serverPortHttp);
		connector.setRedirectPort(serverPortHttps);
		return connector;
	}
	

}
