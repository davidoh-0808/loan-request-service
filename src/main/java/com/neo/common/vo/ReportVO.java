package com.neo.common.vo;

public class ReportVO extends ComVO{
	
	private Integer REPO_SEQ;			// 통계시퀀스
	private String REPO_PATH;			// 페이지뷰 경로
	private String REPO_DEVICE;			// 접속환경
	private String REPO_IP;				// 접속아이피
	
	private String IN_DATE;				// 기준날짜
	private String PC_CNT;				// PC접속건수
	private String PC_RATE;				// PC접속비율
	private String MOBILE_CNT;			// 모바일접속건수
	private String MOBILE_RATE;			// 모바일접속비율

	private String SUNWI;				// 순위
	private String CNT;					// 접속건수
	private String REPO_RATE;			// 접속비율
	private String TOT_CNT;				// 총접속건수
	private String REPO_PATH_NAME;			// 페이지뷰 경로
	
	public Integer getREPO_SEQ() {
		return REPO_SEQ;
	}
	public void setREPO_SEQ(Integer rEPO_SEQ) {
		REPO_SEQ = rEPO_SEQ;
	}
	public String getREPO_PATH() {
		return REPO_PATH;
	}
	public void setREPO_PATH(String rEPO_PATH) {
		REPO_PATH = rEPO_PATH;
	}
	public String getREPO_DEVICE() {
		return REPO_DEVICE;
	}
	public void setREPO_DEVICE(String rEPO_DEVICE) {
		REPO_DEVICE = rEPO_DEVICE;
	}
	public String getREPO_IP() {
		return REPO_IP;
	}
	public void setREPO_IP(String rEPO_IP) {
		REPO_IP = rEPO_IP;
	}
	public String getIN_DATE() {
		return IN_DATE;
	}
	public void setIN_DATE(String iN_DATE) {
		IN_DATE = iN_DATE;
	}
	public String getPC_CNT() {
		return PC_CNT;
	}
	public void setPC_CNT(String pC_CNT) {
		PC_CNT = pC_CNT;
	}
	public String getPC_RATE() {
		return PC_RATE;
	}
	public void setPC_RATE(String pC_RATE) {
		PC_RATE = pC_RATE;
	}
	public String getMOBILE_CNT() {
		return MOBILE_CNT;
	}
	public void setMOBILE_CNT(String mOBILE_CNT) {
		MOBILE_CNT = mOBILE_CNT;
	}
	public String getMOBILE_RATE() {
		return MOBILE_RATE;
	}
	public void setMOBILE_RATE(String mOBILE_RATE) {
		MOBILE_RATE = mOBILE_RATE;
	}
	public String getSUNWI() {
		return SUNWI;
	}
	public void setSUNWI(String sUNWI) {
		SUNWI = sUNWI;
	}
	public String getCNT() {
		return CNT;
	}
	public void setCNT(String cNT) {
		CNT = cNT;
	}
	public String getREPO_RATE() {
		return REPO_RATE;
	}
	public void setREPO_RATE(String rEPO_RATE) {
		REPO_RATE = rEPO_RATE;
	}
	public String getTOT_CNT() {
		return TOT_CNT;
	}
	public void setTOT_CNT(String tOT_CNT) {
		TOT_CNT = tOT_CNT;
	}
	public String getREPO_PATH_NAME() {
		return REPO_PATH_NAME;
	}
	public void setREPO_PATH_NAME(String rEPO_PATH_NAME) {
		REPO_PATH_NAME = rEPO_PATH_NAME;
	}
	
}
