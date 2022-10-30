package com.neo.common.vo;

/**
 * 마스터키VO
 * @author leekw
 *
 */
public class MasterKeyVO{
	
	private String KEY_GBN;			// 키구분
	private String EXPLANATION;		// 설명
	private String KEY_PREFIX;		// 키접두어
	private String KEY_SUFFIX;		// 키접미어
	
	public String getKEY_GBN() {
		return KEY_GBN;
	}
	public void setKEY_GBN(String kEY_GBN) {
		KEY_GBN = kEY_GBN;
	}
	public String getEXPLANATION() {
		return EXPLANATION;
	}
	public void setEXPLANATION(String eXPLANATION) {
		EXPLANATION = eXPLANATION;
	}
	public String getKEY_PREFIX() {
		return KEY_PREFIX;
	}
	public void setKEY_PREFIX(String kEY_PREFIX) {
		KEY_PREFIX = kEY_PREFIX;
	}
	public String getKEY_SUFFIX() {
		return KEY_SUFFIX;
	}
	public void setKEY_SUFFIX(String kEY_SUFFIX) {
		KEY_SUFFIX = kEY_SUFFIX;
	}

}
