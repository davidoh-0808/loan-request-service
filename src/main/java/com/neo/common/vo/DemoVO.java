package com.neo.common.vo;

public class DemoVO extends ComVO{
	
	private String DEMO_CODE;			// 데모코드
	private String DEMO_TITLE;			// 데모타이틀
	private String DEMO_CONTENTS;		// 데모내용
	
	public String getDEMO_CODE() {
		return DEMO_CODE;
	}
	public void setDEMO_CODE(String dEMO_CODE) {
		DEMO_CODE = dEMO_CODE;
	}
	public String getDEMO_TITLE() {
		return DEMO_TITLE;
	}
	public void setDEMO_TITLE(String dEMO_TITLE) {
		DEMO_TITLE = dEMO_TITLE;
	}
	public String getDEMO_CONTENTS() {
		return DEMO_CONTENTS;
	}
	public void setDEMO_CONTENTS(String dEMO_CONTENTS) {
		DEMO_CONTENTS = dEMO_CONTENTS;
	}
	
}
