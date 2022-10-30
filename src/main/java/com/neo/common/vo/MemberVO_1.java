package com.neo.common.vo;

import java.util.Date;
import java.util.List;

public class MemberVO extends ComVO{
	
	private String MEMBER_CODE;			// 회원코드(MB)
	private String MEMBER_ID;			// 회원아이디
	private String MEMBER_PW;			// 회원비밀번호
	private String MEMBER_NAME;			// 회원이름
	private String MEMBER_PHONE;			// 전화번호
	private String MEMBER_EMAIL;			// EMAIL
	private String MEMBER_AUTHORITY;			// 회원권한(MC0000000006)
	private String MEMBER_STATUS;			// 회원상태(MC0000000007)
	private String EXPLANATION;			// 회원메모
	private String MENU_AUTH;
	private String ACCOUNT_USE;
	private String THANKS_USE;
	private String NEWS_USE;
	private String FINAN_USE;
	private String REPORT_USE;
	private String[] menu_auth_arr;
	private List<String> menuAuths;

	private Date JOIN_DTTM;			// 가입일시
	private Date LAST_LOGIN_DTTM;			// 최종로그인일시

	private String MEMBER_AUTHORITY_NAME;			// 회원권한(MC0000000006)
	private String MEMBER_STATUS_NAME;			// 회원상태(MC0000000007)
	public String getMEMBER_CODE() {
		return MEMBER_CODE;
	}
	public void setMEMBER_CODE(String mEMBER_CODE) {
		MEMBER_CODE = mEMBER_CODE;
	}
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	public String getMEMBER_PW() {
		return MEMBER_PW;
	}
	public void setMEMBER_PW(String mEMBER_PW) {
		MEMBER_PW = mEMBER_PW;
	}
	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}
	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}
	public String getMEMBER_PHONE() {
		return MEMBER_PHONE;
	}
	public void setMEMBER_PHONE(String mEMBER_PHONE) {
		MEMBER_PHONE = mEMBER_PHONE;
	}
	public String getMEMBER_EMAIL() {
		return MEMBER_EMAIL;
	}
	public void setMEMBER_EMAIL(String mEMBER_EMAIL) {
		MEMBER_EMAIL = mEMBER_EMAIL;
	}
	public String getMEMBER_AUTHORITY() {
		return MEMBER_AUTHORITY;
	}
	public void setMEMBER_AUTHORITY(String mEMBER_AUTHORITY) {
		MEMBER_AUTHORITY = mEMBER_AUTHORITY;
	}
	public String getMEMBER_STATUS() {
		return MEMBER_STATUS;
	}
	public void setMEMBER_STATUS(String mEMBER_STATUS) {
		MEMBER_STATUS = mEMBER_STATUS;
	}
	public String getEXPLANATION() {
		return EXPLANATION;
	}
	public void setEXPLANATION(String eXPLANATION) {
		EXPLANATION = eXPLANATION;
	}
	public String getMENU_AUTH() {
		return MENU_AUTH;
	}
	public void setMENU_AUTH(String mENU_AUTH) {
		MENU_AUTH = mENU_AUTH;
	}
	public String getACCOUNT_USE() {
		return ACCOUNT_USE;
	}
	public void setACCOUNT_USE(String aCCOUNT_USE) {
		ACCOUNT_USE = aCCOUNT_USE;
	}
	public String getTHANKS_USE() {
		return THANKS_USE;
	}
	public void setTHANKS_USE(String tHANKS_USE) {
		THANKS_USE = tHANKS_USE;
	}
	public String getNEWS_USE() {
		return NEWS_USE;
	}
	public void setNEWS_USE(String nEWS_USE) {
		NEWS_USE = nEWS_USE;
	}
	public String getFINAN_USE() {
		return FINAN_USE;
	}
	public void setFINAN_USE(String fINAN_USE) {
		FINAN_USE = fINAN_USE;
	}
	public String getREPORT_USE() {
		return REPORT_USE;
	}
	public void setREPORT_USE(String rEPORT_USE) {
		REPORT_USE = rEPORT_USE;
	}
	public String[] getMenu_auth_arr() {
		return menu_auth_arr;
	}
	public void setMenu_auth_arr(String[] menu_auth_arr) {
		this.menu_auth_arr = menu_auth_arr;
	}
	public Date getJOIN_DTTM() {
		return JOIN_DTTM;
	}
	public void setJOIN_DTTM(Date jOIN_DTTM) {
		JOIN_DTTM = jOIN_DTTM;
	}
	public Date getLAST_LOGIN_DTTM() {
		return LAST_LOGIN_DTTM;
	}
	public void setLAST_LOGIN_DTTM(Date lAST_LOGIN_DTTM) {
		LAST_LOGIN_DTTM = lAST_LOGIN_DTTM;
	}
	public String getMEMBER_AUTHORITY_NAME() {
		return MEMBER_AUTHORITY_NAME;
	}
	public void setMEMBER_AUTHORITY_NAME(String mEMBER_AUTHORITY_NAME) {
		MEMBER_AUTHORITY_NAME = mEMBER_AUTHORITY_NAME;
	}
	public String getMEMBER_STATUS_NAME() {
		return MEMBER_STATUS_NAME;
	}
	public void setMEMBER_STATUS_NAME(String mEMBER_STATUS_NAME) {
		MEMBER_STATUS_NAME = mEMBER_STATUS_NAME;
	}
	public List<String> getMenuAuths() {
		return menuAuths;
	}
	public void setMenuAuths(List<String> menuAuths) {
		this.menuAuths = menuAuths;
	}
	
}
