package com.neo.common.vo;

public class BoardFileVO extends ComVO{
	
	private Integer FILE_SEQ;			// 파일순번
	private String BOARD_CODE;			// 데모코드
	private String UPLOAD_GROUP;		// 업로드그룹(PROPERTIE)
	private String UPLOAD_PATH ;			// 저장경로
	private String ORG_FILE_NAME;		// 원본파일명
	private String SYS_FILE_NAME;		// 실제파일명
	private String FILE_EXT;			// 확장자
	private String FILE_SIZE;			// 파일사이즈
	
	public Integer getFILE_SEQ() {
		return FILE_SEQ;
	}
	public void setFILE_SEQ(Integer fILE_SEQ) {
		FILE_SEQ = fILE_SEQ;
	}
	public String getBOARD_CODE() {
		return BOARD_CODE;
	}
	public void setBOARD_CODE(String bOARD_CODE) {
		BOARD_CODE = bOARD_CODE;
	}
	public String getUPLOAD_GROUP() {
		return UPLOAD_GROUP;
	}
	public void setUPLOAD_GROUP(String uPLOAD_GROUP) {
		UPLOAD_GROUP = uPLOAD_GROUP;
	}
	public String getUPLOAD_PATH() {
		return UPLOAD_PATH;
	}
	public void setUPLOAD_PATH(String uPLOAD_PATH) {
		UPLOAD_PATH = uPLOAD_PATH;
	}
	public String getORG_FILE_NAME() {
		return ORG_FILE_NAME;
	}
	public void setORG_FILE_NAME(String oRG_FILE_NAME) {
		ORG_FILE_NAME = oRG_FILE_NAME;
	}
	public String getSYS_FILE_NAME() {
		return SYS_FILE_NAME;
	}
	public void setSYS_FILE_NAME(String sYS_FILE_NAME) {
		SYS_FILE_NAME = sYS_FILE_NAME;
	}
	public String getFILE_EXT() {
		return FILE_EXT;
	}
	public void setFILE_EXT(String fILE_EXT) {
		FILE_EXT = fILE_EXT;
	}
	public String getFILE_SIZE() {
		return FILE_SIZE;
	}
	public void setFILE_SIZE(String fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}
	
}
