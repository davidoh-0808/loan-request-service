package com.neo.common.vo;

public class ConsultInfoVO extends ComVO {

    private String CONS_SEQ;
    private String CONS_DTTM;
    private String CONS_MB_CODE;
    private String BRANCH_CODE;
    private String BRANCH_MB_CODE;
    private String CUST_NM;
    private String CUST_REGI_NO;
    private String CUST_REGI_NO_FM;
    private String CUST_HP_NO;
    private String CUST_HP_NO_FM; // 010-****-5555 포맷된 번호
    private String INQU_CONS;
    private String CERTI_MTHD_TP;
    private String CERTI_DTTM;
    private String RECORD_TIME;
    private String INFLOW_ROUTE1;
    private String INFLOW_ROUTE2;
    private String PRODUCT_TYPE;
    private String TYPE_CODE;
    private String TYPE_NAME;
    private String CORP_HIS;
    private String DEBT_SETT;
    private String CUST_TYPE;
    private String CUST_TYPE_NAME;
    private String CUST_CORP_NO;
    private String CUST_CORP_NO_FM;
    private String CUST_CORP_NM;
    private String CORP_REGI_DT;
    private String CUST_CORP_AREA;
    private String CUST_CORP_ADDR1;
    private String CUST_CORP_ADDR2;
    private String VULN_CLASS;
    private String VULN_CLASS_NAME;
    private String CORP_NM;
    private String JOIN_DATE;
    private String JOIN_DATE_FM;
    private String JOB_TYPE;
    private String JOB_TYPE_NAME;
    private String HOME_ADDR1;
    private String HOME_ADDR2;
    private String STATS_CODE;
    private String COMP_DATE;
    private String BRANCH_RESULT_CODE;
    private String EVAL_RESULT_CODE;
    private String DECLINE_REASON_CODE;
    private String CANCEL_REASON_CODE;
    private String NOTE;
    private String ETC;

    //DB 서 alias 컬럼명으로 마스터코드의 이름값을 가져오기 위한 필드
    private String CONS_MB_NAME;
    private String BRANCH_NAME;
    private String BRANCH_MB_NAME;
    private String REGISTRAR;
    private String PRODUCT_NAME;
    private String INFLOW_NAME1;
    private String INFLOW_NAME2;
    private String STATS_NAME;
    private String BRANCH_RESULT_NAME;
    private String EVAL_RESULT_NAME;
    private String DECLINE_REASON_NAME;
    private String CANCEL_REASON_NAME;


    //대시보드 날짜 쿼리 및 디스플레이
    private String currDate;
    //대시보드 신청상담내역 현황 (신청현황, 상담리스트 에서도 사용)
    private String TOTAL_CNT;
    private String SUBMIT_CNT;
    private String PROGRESS_CNT;
    private String COMPLETE_CNT;
    private String DELAY_CNT;
    private String CANCEL_CNT;
    private String STANDBY_CNT;
    private String SUM_PROGRESS_CNT;	//지점용(진행중 + 진행중(대기) + 부재/보류)

    //대시보드 심사팀 관리자 현황 (x) < ConsultMember 로 대체
    //private String BRANCH_TOTAL_CNT;
    //private String BRANCH_TOTAL_PER;
    //private String EVAL_TOTAL_CNT;
    //private String EVAL_TOTAL_PER;

    //체크박스에 체크된 상담신청들을 담기 위한 필드 (담당자배정기능)
    private String[] checkedConsSeqArr;

    // 대출상담관리 - 접속현황 (NICE 인증된 고객유저의 리스트)
    private String GENDER;
    private String FRN_CHK;
    private String CERTI_MTHD_TP_NAME;

    ///////////////////////////////////////////////////////////////


    public String getCONS_SEQ() {
        return CONS_SEQ;
    }

    public void setCONS_SEQ(String CONS_SEQ) {
        this.CONS_SEQ = CONS_SEQ;
    }

    public String getCONS_DTTM() {
        return CONS_DTTM;
    }

    public void setCONS_DTTM(String CONS_DTTM) {
        this.CONS_DTTM = CONS_DTTM;
    }

    public String getCONS_MB_CODE() {
        return CONS_MB_CODE;
    }

    public void setCONS_MB_CODE(String CONS_MB_CODE) {
        this.CONS_MB_CODE = CONS_MB_CODE;
    }

    public String getBRANCH_CODE() {
        return BRANCH_CODE;
    }

    public void setBRANCH_CODE(String BRANCH_CODE) {
        this.BRANCH_CODE = BRANCH_CODE;
    }

    public String getBRANCH_MB_CODE() {
        return BRANCH_MB_CODE;
    }

    public void setBRANCH_MB_CODE(String BRANCH_MB_CODE) {
        this.BRANCH_MB_CODE = BRANCH_MB_CODE;
    }

    public String getCUST_NM() {
        return CUST_NM;
    }

    public void setCUST_NM(String CUST_NM) {
        this.CUST_NM = CUST_NM;
    }

    public String getCUST_REGI_NO() {
        return CUST_REGI_NO;
    }

    public void setCUST_REGI_NO(String CUST_REGI_NO) {
        this.CUST_REGI_NO = CUST_REGI_NO;
    }

    public String getCUST_REGI_NO_FM() {
        return CUST_REGI_NO_FM;
    }

    public void setCUST_REGI_NO_FM(String CUST_REGI_NO_FM) {
        this.CUST_REGI_NO_FM = CUST_REGI_NO_FM;
    }

    public String getCUST_HP_NO() {
        return CUST_HP_NO;
    }

    public void setCUST_HP_NO(String CUST_HP_NO) {
        this.CUST_HP_NO = CUST_HP_NO;
    }

    public String getCUST_HP_NO_FM() {
        return CUST_HP_NO_FM;
    }

    public void setCUST_HP_NO_FM(String CUST_HP_NO_FM) {
        this.CUST_HP_NO_FM = CUST_HP_NO_FM;
    }

    public String getINQU_CONS() {
        return INQU_CONS;
    }

    public void setINQU_CONS(String INQU_CONS) {
        this.INQU_CONS = INQU_CONS;
    }

    public String getCERTI_MTHD_TP() {
        return CERTI_MTHD_TP;
    }

    public void setCERTI_MTHD_TP(String CERTI_MTHD_TP) {
        this.CERTI_MTHD_TP = CERTI_MTHD_TP;
    }

    public String getCERTI_DTTM() {
        return CERTI_DTTM;
    }

    public void setCERTI_DTTM(String CERTI_DTTM) {
        this.CERTI_DTTM = CERTI_DTTM;
    }

    public String getRECORD_TIME() {
        return RECORD_TIME;
    }

    public void setRECORD_TIME(String RECORD_TIME) {
        this.RECORD_TIME = RECORD_TIME;
    }

    public String getINFLOW_ROUTE1() {
        return INFLOW_ROUTE1;
    }

    public void setINFLOW_ROUTE1(String INFLOW_ROUTE1) {
        this.INFLOW_ROUTE1 = INFLOW_ROUTE1;
    }

    public String getINFLOW_ROUTE2() {
        return INFLOW_ROUTE2;
    }

    public void setINFLOW_ROUTE2(String INFLOW_ROUTE2) {
        this.INFLOW_ROUTE2 = INFLOW_ROUTE2;
    }

    public String getPRODUCT_TYPE() {
        return PRODUCT_TYPE;
    }

    public void setPRODUCT_TYPE(String PRODUCT_TYPE) {
        this.PRODUCT_TYPE = PRODUCT_TYPE;
    }

    public String getTYPE_CODE() {
        return TYPE_CODE;
    }

    public void setTYPE_CODE(String TYPE_CODE) {
        this.TYPE_CODE = TYPE_CODE;
    }

    public String getCORP_HIS() {
        return CORP_HIS;
    }

    public void setCORP_HIS(String CORP_HIS) {
        this.CORP_HIS = CORP_HIS;
    }

    public String getDEBT_SETT() {
        return DEBT_SETT;
    }

    public void setDEBT_SETT(String DEBT_SETT) {
        this.DEBT_SETT = DEBT_SETT;
    }

    public String getCUST_TYPE() {
        return CUST_TYPE;
    }

    public void setCUST_TYPE(String CUST_TYPE) {
        this.CUST_TYPE = CUST_TYPE;
    }

    public String getCUST_TYPE_NAME() {
		return CUST_TYPE_NAME;
	}

	public void setCUST_TYPE_NAME(String cUST_TYPE_NAME) {
		CUST_TYPE_NAME = cUST_TYPE_NAME;
	}

	public String getCUST_CORP_NO() {
        return CUST_CORP_NO;
    }

    public void setCUST_CORP_NO(String CUST_CORP_NO) {
        this.CUST_CORP_NO = CUST_CORP_NO;
    }

    public String getCUST_CORP_NO_FM() {
        return CUST_CORP_NO_FM;
    }

    public void setCUST_CORP_NO_FM(String CUST_CORP_NO_FM) {
        this.CUST_CORP_NO_FM = CUST_CORP_NO_FM;
    }

    public String getCUST_CORP_NM() {
        return CUST_CORP_NM;
    }

    public void setCUST_CORP_NM(String CUST_CORP_NM) {
        this.CUST_CORP_NM = CUST_CORP_NM;
    }

    public String getCORP_REGI_DT() {
        return CORP_REGI_DT;
    }

    public void setCORP_REGI_DT(String CORP_REGI_DT) {
        this.CORP_REGI_DT = CORP_REGI_DT;
    }

    public String getCUST_CORP_AREA() {
        return CUST_CORP_AREA;
    }

    public void setCUST_CORP_AREA(String CUST_CORP_AREA) {
        this.CUST_CORP_AREA = CUST_CORP_AREA;
    }

    public String getCUST_CORP_ADDR1() {
        return CUST_CORP_ADDR1;
    }

    public void setCUST_CORP_ADDR1(String CUST_CORP_ADDR1) {
        this.CUST_CORP_ADDR1 = CUST_CORP_ADDR1;
    }

    public String getCUST_CORP_ADDR2() {
        return CUST_CORP_ADDR2;
    }

    public void setCUST_CORP_ADDR2(String CUST_CORP_ADDR2) {
        this.CUST_CORP_ADDR2 = CUST_CORP_ADDR2;
    }

    public String getVULN_CLASS() {
        return VULN_CLASS;
    }

    public void setVULN_CLASS(String VULN_CLASS) {
        this.VULN_CLASS = VULN_CLASS;
    }

    public String getVULN_CLASS_NAME() {
		return VULN_CLASS_NAME;
	}

	public void setVULN_CLASS_NAME(String vULN_CLASS_NAME) {
		VULN_CLASS_NAME = vULN_CLASS_NAME;
	}

	public String getCORP_NM() {
        return CORP_NM;
    }

    public void setCORP_NM(String CORP_NM) {
        this.CORP_NM = CORP_NM;
    }

    public String getJOIN_DATE() {
        return JOIN_DATE;
    }

    public void setJOIN_DATE(String JOIN_DATE) {
        this.JOIN_DATE = JOIN_DATE;
    }

    public String getJOIN_DATE_FM() {
        return JOIN_DATE_FM;
    }

    public void setJOIN_DATE_FM(String JOIN_DATE_FM) {
        this.JOIN_DATE_FM = JOIN_DATE_FM;
    }

    public String getJOB_TYPE() {
        return JOB_TYPE;
    }

    public void setJOB_TYPE(String JOB_TYPE) {
        this.JOB_TYPE = JOB_TYPE;
    }

    
    public String getJOB_TYPE_NAME() {
		return JOB_TYPE_NAME;
	}

	public void setJOB_TYPE_NAME(String jOB_TYPE_NAME) {
		JOB_TYPE_NAME = jOB_TYPE_NAME;
	}

	public String getHOME_ADDR1() {
        return HOME_ADDR1;
    }

    public void setHOME_ADDR1(String HOME_ADDR1) {
        this.HOME_ADDR1 = HOME_ADDR1;
    }

    public String getHOME_ADDR2() {
        return HOME_ADDR2;
    }

    public void setHOME_ADDR2(String HOME_ADDR2) {
        this.HOME_ADDR2 = HOME_ADDR2;
    }

    public String getSTATS_CODE() {
        return STATS_CODE;
    }

    public void setSTATS_CODE(String STATS_CODE) {
        this.STATS_CODE = STATS_CODE;
    }

    public String getCOMP_DATE() {
        return COMP_DATE;
    }

    public void setCOMP_DATE(String COMP_DATE) {
        this.COMP_DATE = COMP_DATE;
    }

    public String getBRANCH_RESULT_CODE() {
        return BRANCH_RESULT_CODE;
    }

    public void setBRANCH_RESULT_CODE(String BRANCH_RESULT_CODE) {
        this.BRANCH_RESULT_CODE = BRANCH_RESULT_CODE;
    }

    public String getEVAL_RESULT_CODE() {
        return EVAL_RESULT_CODE;
    }

    public void setEVAL_RESULT_CODE(String EVAL_RESULT_CODE) {
        this.EVAL_RESULT_CODE = EVAL_RESULT_CODE;
    }

    public String getDECLINE_REASON_CODE() {
        return DECLINE_REASON_CODE;
    }

    public void setDECLINE_REASON_CODE(String DECLINE_REASON_CODE) {
        this.DECLINE_REASON_CODE = DECLINE_REASON_CODE;
    }

    public String getCANCEL_REASON_CODE() {
        return CANCEL_REASON_CODE;
    }

    public void setCANCEL_REASON_CODE(String CANCEL_REASON_CODE) {
        this.CANCEL_REASON_CODE = CANCEL_REASON_CODE;
    }

    public String getNOTE() {
        return NOTE;
    }

    public void setNOTE(String NOTE) {
        this.NOTE = NOTE;
    }

    public String getETC() {
        return ETC;
    }

    public void setETC(String ETC) {
        this.ETC = ETC;
    }

    public String getCONS_MB_NAME() {
        return CONS_MB_NAME;
    }

    public void setCONS_MB_NAME(String CONS_MB_NAME) {
        this.CONS_MB_NAME = CONS_MB_NAME;
    }

    public String getBRANCH_NAME() {
        return BRANCH_NAME;
    }

    public void setBRANCH_NAME(String BRANCH_NAME) {
        this.BRANCH_NAME = BRANCH_NAME;
    }

    public String getBRANCH_MB_NAME() {
        return BRANCH_MB_NAME;
    }

    public void setBRANCH_MB_NAME(String BRANCH_MB_NAME) {
        this.BRANCH_MB_NAME = BRANCH_MB_NAME;
    }

    public String getREGISTRAR() {
        return REGISTRAR;
    }

    public void setREGISTRAR(String REGISTRAR) {
        this.REGISTRAR = REGISTRAR;
    }

    public String getPRODUCT_NAME() {
        return PRODUCT_NAME;
    }

    public void setPRODUCT_NAME(String PRODUCT_NAME) {
        this.PRODUCT_NAME = PRODUCT_NAME;
    }

    public String getINFLOW_NAME1() {
        return INFLOW_NAME1;
    }

    public void setINFLOW_NAME1(String INFLOW_NAME1) {
        this.INFLOW_NAME1 = INFLOW_NAME1;
    }

    public String getINFLOW_NAME2() {
        return INFLOW_NAME2;
    }

    public void setINFLOW_NAME2(String INFLOW_NAME2) {
        this.INFLOW_NAME2 = INFLOW_NAME2;
    }

    public String getTYPE_NAME() {
        return TYPE_NAME;
    }

    public void setTYPE_NAME(String TYPE_NAME) {
        this.TYPE_NAME = TYPE_NAME;
    }

    public String getSTATS_NAME() {
        return STATS_NAME;
    }

    public void setSTATS_NAME(String STATS_NAME) {
        this.STATS_NAME = STATS_NAME;
    }

    public String getBRANCH_RESULT_NAME() {
        return BRANCH_RESULT_NAME;
    }

    public void setBRANCH_RESULT_NAME(String BRANCH_RESULT_NAME) {
        this.BRANCH_RESULT_NAME = BRANCH_RESULT_NAME;
    }

    public String getEVAL_RESULT_NAME() {
        return EVAL_RESULT_NAME;
    }

    public void setEVAL_RESULT_NAME(String EVAL_RESULT_NAME) {
        this.EVAL_RESULT_NAME = EVAL_RESULT_NAME;
    }

    public String getDECLINE_REASON_NAME() {
        return DECLINE_REASON_NAME;
    }

    public void setDECLINE_REASON_NAME(String DECLINE_REASON_NAME) {
        this.DECLINE_REASON_NAME = DECLINE_REASON_NAME;
    }

    public String getCANCEL_REASON_NAME() {
        return CANCEL_REASON_NAME;
    }

    public void setCANCEL_REASON_NAME(String CANCEL_REASON_NAME) {
        this.CANCEL_REASON_NAME = CANCEL_REASON_NAME;
    }

    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    public String getTOTAL_CNT() {
        return TOTAL_CNT;
    }

    public void setTOTAL_CNT(String TOTAL_CNT) {
        this.TOTAL_CNT = TOTAL_CNT;
    }

    public String getSUBMIT_CNT() {
        return SUBMIT_CNT;
    }

    public void setSUBMIT_CNT(String SUBMIT_CNT) {
        this.SUBMIT_CNT = SUBMIT_CNT;
    }

    public String getPROGRESS_CNT() {
        return PROGRESS_CNT;
    }

    public void setPROGRESS_CNT(String PROGRESS_CNT) {
        this.PROGRESS_CNT = PROGRESS_CNT;
    }

    public String getCOMPLETE_CNT() {
        return COMPLETE_CNT;
    }

    public void setCOMPLETE_CNT(String COMPLETE_CNT) {
        this.COMPLETE_CNT = COMPLETE_CNT;
    }

    public String getDELAY_CNT() {
		return DELAY_CNT;
	}

	public void setDELAY_CNT(String dELAY_CNT) {
		DELAY_CNT = dELAY_CNT;
	}

	public String getCANCEL_CNT() {
        return CANCEL_CNT;
    }

    public void setCANCEL_CNT(String CANCEL_CNT) {
        this.CANCEL_CNT = CANCEL_CNT;
    }

    public String[] getCheckedConsSeqArr() {
        return checkedConsSeqArr;
    }

    public void setCheckedConsSeqArr(String[] checkedConsSeqArr) {
        this.checkedConsSeqArr = checkedConsSeqArr;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getFRN_CHK() {
        return FRN_CHK;
    }

    public void setFRN_CHK(String FRN_CHK) {
        this.FRN_CHK = FRN_CHK;
    }

    public String getCERTI_MTHD_TP_NAME() {
        return CERTI_MTHD_TP_NAME;
    }

    public void setCERTI_MTHD_TP_NAME(String CERTI_MTHD_TP_NAME) {
        this.CERTI_MTHD_TP_NAME = CERTI_MTHD_TP_NAME;
    }

	public String getSTANDBY_CNT() {
		return STANDBY_CNT;
	}

	public void setSTANDBY_CNT(String sTANDBY_CNT) {
		STANDBY_CNT = sTANDBY_CNT;
	}

	/**
	 * @return the sUM_PROGRESS_CNT
	 */
	public String getSUM_PROGRESS_CNT() {
		return SUM_PROGRESS_CNT;
	}

	/**
	 * @param sUM_PROGRESS_CNT the sUM_PROGRESS_CNT to set
	 */
	public void setSUM_PROGRESS_CNT(String sUM_PROGRESS_CNT) {
		SUM_PROGRESS_CNT = sUM_PROGRESS_CNT;
	}
    
}
