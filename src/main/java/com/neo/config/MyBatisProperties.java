package com.neo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "mybatis")
@Component("myBatisProperties")
public class MyBatisProperties {

	private String typeAliasesPackage;
	private String mapperLocations;
	
	public String getTypeAliasesPackage() {
		return typeAliasesPackage;
	}
	public void setTypeAliasesPackage(String typeAliasesPackage) {
		this.typeAliasesPackage = typeAliasesPackage;
	}
	public String getMapperLocations() {
		return mapperLocations;
	}
	public void setMapperLocations(String mapperLocations) {
		this.mapperLocations = mapperLocations;
	}
	
}
