package com.neo.common.vo;

import java.util.Date;

public class ConsultMemberVO extends ComVO {
    private String MEMBER_CODE;
    private String MEMBER_ID;
    private String MEMBER_PW;
    private String MEMBER_OTP;
    private String MEMBER_NAME;
    private String MEMBER_PHONE;
    private String MEMBER_EMAIL;
    private String MEMBER_TYPE;
    private String BRANCH_CODE;
    private String BRANCH_NAME;
    private String MEMBER_AUTHORITY;
    private String MEMBER_AUTHORITY_NAME;
    private String MEMBER_STATUS;
    private String MEMBER_STATUS_NAME;
    private String EXPLANATION;
    private Date JOIN_DTTM;
    private Date LAST_LOGIN_DTTM;     // 날짜 포맷팅 (YY-mm-dd)을 위해 Date 에서 String 으로 변경
    private String MEMBER_WORK_STATUS;
    private String MEMBER_WORK_STATUS_NAME;
    private String USE_YN;
	private String CONN_IP;

	// 대시보드 - 심사팀관리자, 시스템관리자 페이지의 현재 유저 권한별 수 현황 (O)
	private String USER_TOTAL_CNT;
	private String USER_BRANCH_EVAL_CNT;
	private String USER_BRANCH_CNT;
	private String USER_EVAL_CNT;
	private String USER_EVAL_MG_CNT;
	private String USER_SYS_MG_CNT;

	private String USER_BRANCH_PER;
	private String USER_EVAL_PER;
	private String USER_EVAL_MG_PER;
	private String USER_SYS_MG_PER;




	/**
	 * @return the mEMBER_CODE
	 */
	public String getMEMBER_CODE() {
		return MEMBER_CODE;
	}
	/**
	 * @param mEMBER_CODE the mEMBER_CODE to set
	 */
	public void setMEMBER_CODE(String mEMBER_CODE) {
		MEMBER_CODE = mEMBER_CODE;
	}
	/**
	 * @return the mEMBER_ID
	 */
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	/**
	 * @param mEMBER_ID the mEMBER_ID to set
	 */
	public void setMEMBER_ID(String mEMBER_ID) {
		MEMBER_ID = mEMBER_ID;
	}
	/**
	 * @return the mEMBER_PW
	 */
	public String getMEMBER_PW() {
		return MEMBER_PW;
	}
	/**
	 * @param mEMBER_PW the mEMBER_PW to set
	 */
	public void setMEMBER_PW(String mEMBER_PW) {
		MEMBER_PW = mEMBER_PW;
	}
	/**
	 * @return the mEMBER_OTP
	 */
	public String getMEMBER_OTP() {
		return MEMBER_OTP;
	}
	/**
	 * @param mEMBER_OTP the mEMBER_OTP to set
	 */
	public void setMEMBER_OTP(String mEMBER_OTP) {
		MEMBER_OTP = mEMBER_OTP;
	}
	/**
	 * @return the mEMBER_NAME
	 */
	public String getMEMBER_NAME() {
		return MEMBER_NAME;
	}
	/**
	 * @param mEMBER_NAME the mEMBER_NAME to set
	 */
	public void setMEMBER_NAME(String mEMBER_NAME) {
		MEMBER_NAME = mEMBER_NAME;
	}
	/**
	 * @return the mEMBER_PHONE
	 */
	public String getMEMBER_PHONE() {
		return MEMBER_PHONE;
	}
	/**
	 * @param mEMBER_PHONE the mEMBER_PHONE to set
	 */
	public void setMEMBER_PHONE(String mEMBER_PHONE) {
		MEMBER_PHONE = mEMBER_PHONE;
	}
	/**
	 * @return the mEMBER_EMAIL
	 */
	public String getMEMBER_EMAIL() {
		return MEMBER_EMAIL;
	}
	/**
	 * @param mEMBER_EMAIL the mEMBER_EMAIL to set
	 */
	public void setMEMBER_EMAIL(String mEMBER_EMAIL) {
		MEMBER_EMAIL = mEMBER_EMAIL;
	}
	/**
	 * @return the mEMBER_TYPE
	 */
	public String getMEMBER_TYPE() {
		return MEMBER_TYPE;
	}
	/**
	 * @param mEMBER_TYPE the mEMBER_TYPE to set
	 */
	public void setMEMBER_TYPE(String mEMBER_TYPE) {
		MEMBER_TYPE = mEMBER_TYPE;
	}
	/**
	 * @return the bRANCH_CODE
	 */
	public String getBRANCH_CODE() {
		return BRANCH_CODE;
	}
	/**
	 * @param bRANCH_CODE the bRANCH_CODE to set
	 */
	public void setBRANCH_CODE(String bRANCH_CODE) {
		BRANCH_CODE = bRANCH_CODE;
	}
	/**
	 * @return the bRANCH_NAME
	 */
	public String getBRANCH_NAME() {
		return BRANCH_NAME;
	}
	/**
	 * @param bRANCH_NAME the bRANCH_NAME to set
	 */
	public void setBRANCH_NAME(String bRANCH_NAME) {
		BRANCH_NAME = bRANCH_NAME;
	}
	/**
	 * @return the mEMBER_AUTHORITY
	 */
	public String getMEMBER_AUTHORITY() {
		return MEMBER_AUTHORITY;
	}
	/**
	 * @param mEMBER_AUTHORITY the mEMBER_AUTHORITY to set
	 */
	public void setMEMBER_AUTHORITY(String mEMBER_AUTHORITY) {
		MEMBER_AUTHORITY = mEMBER_AUTHORITY;
	}
	/**
	 * @return the mEMBER_AUTHORITY_NAME
	 */
	public String getMEMBER_AUTHORITY_NAME() {
		return MEMBER_AUTHORITY_NAME;
	}
	/**
	 * @param mEMBER_AUTHORITY_NAME the mEMBER_AUTHORITY_NAME to set
	 */
	public void setMEMBER_AUTHORITY_NAME(String mEMBER_AUTHORITY_NAME) {
		MEMBER_AUTHORITY_NAME = mEMBER_AUTHORITY_NAME;
	}
	/**
	 * @return the mEMBER_STATUS
	 */
	public String getMEMBER_STATUS() {
		return MEMBER_STATUS;
	}
	/**
	 * @param mEMBER_STATUS the mEMBER_STATUS to set
	 */
	public void setMEMBER_STATUS(String mEMBER_STATUS) {
		MEMBER_STATUS = mEMBER_STATUS;
	}
	/**
	 * @return the eXPLANATION
	 */
	public String getEXPLANATION() {
		return EXPLANATION;
	}
	/**
	 * @param eXPLANATION the eXPLANATION to set
	 */
	public void setEXPLANATION(String eXPLANATION) {
		EXPLANATION = eXPLANATION;
	}
	/**
	 * @return the jOIN_DTTM
	 */
	public Date getJOIN_DTTM() {
		return JOIN_DTTM;
	}
	/**
	 * @param jOIN_DTTM the jOIN_DTTM to set
	 */
	public void setJOIN_DTTM(Date jOIN_DTTM) {
		JOIN_DTTM = jOIN_DTTM;
	}
	/**
	 * @return the lAST_LOGIN_DTTM
	 */
	public Date getLAST_LOGIN_DTTM() {
		return LAST_LOGIN_DTTM;
	}
	/**
	 * @param lAST_LOGIN_DTTM the lAST_LOGIN_DTTM to set
	 */
	public void setLAST_LOGIN_DTTM(Date lAST_LOGIN_DTTM) {
		LAST_LOGIN_DTTM = lAST_LOGIN_DTTM;
	}
	/**
	 * @return the mEMBER_WORK_STATUS
	 */
	public String getMEMBER_WORK_STATUS() {
		return MEMBER_WORK_STATUS;
	}
	/**
	 * @param mEMBER_WORK_STATUS the mEMBER_WORK_STATUS to set
	 */
	public void setMEMBER_WORK_STATUS(String mEMBER_WORK_STATUS) {
		MEMBER_WORK_STATUS = mEMBER_WORK_STATUS;
	}
	/**
	 * @return the uSE_YN
	 */
	public String getUSE_YN() {
		return USE_YN;
	}
	/**
	 * @param uSE_YN the uSE_YN to set
	 */
	public void setUSE_YN(String uSE_YN) {
		USE_YN = uSE_YN;
	}
	/**
	 * @return the MEMBER_WORK_STATUS_NAME
	 */
	public String getMEMBER_WORK_STATUS_NAME() {
		return MEMBER_WORK_STATUS_NAME;
	}
	/**
	 * @param mEMBER_WORK_STATUS_NAME the wORK_STATUS to set
	 */
	public void setMEMBER_WORK_STATUS_NAME(String mEMBER_WORK_STATUS_NAME) {
		MEMBER_WORK_STATUS_NAME = mEMBER_WORK_STATUS_NAME;
	}
	/**
	 * @return CONN_IP
	 */
	public String getCONN_IP() {
		return CONN_IP;
	}
	/**
	 * @param CONN_IP the CONN_IP to set
	 */
	public void setCONN_IP(String CONN_IP) {
		this.CONN_IP = CONN_IP;
	}

	/**
	 *
	 * @return MEMBER_STATUS_NAME
	 */
	public String getMEMBER_STATUS_NAME() {
		return MEMBER_STATUS_NAME;
	}

	/**
	 *
	 * @param MEMBER_STATUS_NAME
	 */
	public void setMEMBER_STATUS_NAME(String MEMBER_STATUS_NAME) {
		this.MEMBER_STATUS_NAME = MEMBER_STATUS_NAME;
	}

	public String getUSER_TOTAL_CNT() {
		return USER_TOTAL_CNT;
	}

	public void setUSER_TOTAL_CNT(String USER_TOTAL_CNT) {
		this.USER_TOTAL_CNT = USER_TOTAL_CNT;
	}

	public String getUSER_BRANCH_EVAL_CNT() {
		return USER_BRANCH_EVAL_CNT;
	}

	public void setUSER_BRANCH_EVAL_CNT(String USER_BRANCH_EVAL_CNT) {
		this.USER_BRANCH_EVAL_CNT = USER_BRANCH_EVAL_CNT;
	}

	public String getUSER_BRANCH_CNT() {
		return USER_BRANCH_CNT;
	}

	public void setUSER_BRANCH_CNT(String USER_BRANCH_CNT) {
		this.USER_BRANCH_CNT = USER_BRANCH_CNT;
	}

	public String getUSER_EVAL_CNT() {
		return USER_EVAL_CNT;
	}

	public void setUSER_EVAL_CNT(String USER_EVAL_CNT) {
		this.USER_EVAL_CNT = USER_EVAL_CNT;
	}

	public String getUSER_EVAL_MG_CNT() {
		return USER_EVAL_MG_CNT;
	}

	public void setUSER_EVAL_MG_CNT(String USER_EVAL_MG_CNT) {
		this.USER_EVAL_MG_CNT = USER_EVAL_MG_CNT;
	}

	public String getUSER_SYS_MG_CNT() {
		return USER_SYS_MG_CNT;
	}

	public void setUSER_SYS_MG_CNT(String USER_SYS_MG_CNT) {
		this.USER_SYS_MG_CNT = USER_SYS_MG_CNT;
	}

	public String getUSER_BRANCH_PER() {
		return USER_BRANCH_PER;
	}

	public void setUSER_BRANCH_PER(String USER_BRANCH_PER) {
		this.USER_BRANCH_PER = USER_BRANCH_PER;
	}

	public String getUSER_EVAL_PER() {
		return USER_EVAL_PER;
	}

	public void setUSER_EVAL_PER(String USER_EVAL_PER) {
		this.USER_EVAL_PER = USER_EVAL_PER;
	}

	public String getUSER_EVAL_MG_PER() {
		return USER_EVAL_MG_PER;
	}

	public void setUSER_EVAL_MG_PER(String USER_EVAL_MG_PER) {
		this.USER_EVAL_MG_PER = USER_EVAL_MG_PER;
	}

	public String getUSER_SYS_MG_PER() {
		return USER_SYS_MG_PER;
	}

	public void setUSER_SYS_MG_PER(String USER_SYS_MG_PER) {
		this.USER_SYS_MG_PER = USER_SYS_MG_PER;
	}
}
