package com.neo.common.vo;

public class MasterCodeVO extends ComVO{
	
	private String MASTER_CODE;			// 코드아이디
	private String GROUP_CODE;			// 코드그룹
	private String PARENT_MASTER_CODE;		// 상위코드아이디
	private String CODE_NAME;			// 코드명
	private String CODE_VAL;			// 코드값
	private String EXPLANATION;			// 설명
	private String SORT_ORDER;			// 정렬순서
	private String SYSTEM_YN;			// 시스템코드여부
	
	public String getMASTER_CODE() {
		return MASTER_CODE;
	}
	public void setMASTER_CODE(String mASTER_CODE) {
		MASTER_CODE = mASTER_CODE;
	}
	public String getGROUP_CODE() {
		return GROUP_CODE;
	}
	public void setGROUP_CODE(String gROUP_CODE) {
		GROUP_CODE = gROUP_CODE;
	}
	public String getPARENT_MASTER_CODE() {
		return PARENT_MASTER_CODE;
	}
	public void setPARENT_MASTER_CODE(String pARENT_MASTER_CODE) {
		PARENT_MASTER_CODE = pARENT_MASTER_CODE;
	}
	public String getCODE_NAME() {
		return CODE_NAME;
	}
	public void setCODE_NAME(String cODE_NAME) {
		CODE_NAME = cODE_NAME;
	}
	public String getCODE_VAL() {
		return CODE_VAL;
	}
	public void setCODE_VAL(String cODE_VAL) {
		CODE_VAL = cODE_VAL;
	}
	public String getEXPLANATION() {
		return EXPLANATION;
	}
	public void setEXPLANATION(String eXPLANATION) {
		EXPLANATION = eXPLANATION;
	}
	public String getSORT_ORDER() {
		return SORT_ORDER;
	}
	public void setSORT_ORDER(String sORT_ORDER) {
		SORT_ORDER = sORT_ORDER;
	}
	public String getSYSTEM_YN() {
		return SYSTEM_YN;
	}
	public void setSYSTEM_YN(String sYSTEM_YN) {
		SYSTEM_YN = sYSTEM_YN;
	}
	
}
