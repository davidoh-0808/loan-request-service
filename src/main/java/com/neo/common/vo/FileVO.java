package com.neo.common.vo;

/**
 * 공통파일VO
 * @author leekw
 *
 */
public class FileVO {
	
	private Integer FILE_SEQ;
	private String UPLOAD_GROUP;
	private String UPLOAD_PATH;
	private String ORG_FILE_NAME;
	private String SYS_FILE_NAME;
	private String FILE_EXT;
	private String FILE_SIZE;
	private String ELEMENT_NM;
	
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
	public String getELEMENT_NM() {
		return ELEMENT_NM;
	}
	public void setELEMENT_NM(String eLEMENT_NM) {
		ELEMENT_NM = eLEMENT_NM;
	}
	public Integer getFILE_SEQ() {
		return FILE_SEQ;
	}
	public void setFILE_SEQ(Integer fILE_SEQ) {
		FILE_SEQ = fILE_SEQ;
	}

}
