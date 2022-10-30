package com.neo.common.vo;

public class DashboardVO {

    private String currDate;
    private String dateRange;       // all, daily, monthly, yearly
    private String BRANCH_CODE;
    private String BRANCH_MB_CODE;
    private String CONS_MB_CODE;

    private String BRANCH_NAME;     // 지점별 상담내역 현황

    private int TOTAL_CNT = 0; 		//전체
    private int SUBMIT_CNT = 0;		//접수
    private int PROGRESS_CNT = 0;	//진행
    private int COMPLETE_CNT = 0;	//완료
    private int PENDING_CNT = 0;		//대기(STANDBY_CNT)
    private int ABSENCE_CNT = 0;		//부재(DELAY_CNT)
    private int CANCEL_CNT = 0;		//취소
    private int SUM_PROGRESS_CNT = 0;	//지점용(진행중 + 진행중(대기) + 부재/보류)

    private int TOTAL_USER_CNT = 0; 	//지점+심사팀
    private int BRANCH_USER_CNT = 0;			//지점(상담원)
    private int EVAL_USER_CNT = 0;			//심사팀
    private int EVAL_MG_USER_CNT = 0;			//심사팀관리자
    private int SYS_MG_USER_CNT = 0;			//시스템관리자
    
    public String getCurrDate() {
        return currDate;
    }

    public void setCurrDate(String currDate) {
        this.currDate = currDate;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
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

    public String getCONS_MB_CODE() {
        return CONS_MB_CODE;
    }

    public void setCONS_MB_CODE(String CONS_MB_CODE) {
        this.CONS_MB_CODE = CONS_MB_CODE;
    }

    public String getBRANCH_NAME() {
        return BRANCH_NAME;
    }

    public void setBRANCH_NAME(String BRANCH_NAME) {
        this.BRANCH_NAME = BRANCH_NAME;
    }

	/**
	 * @return the tOTAL_CNT
	 */
	public int getTOTAL_CNT() {
		return TOTAL_CNT;
	}

	/**
	 * @param tOTAL_CNT the tOTAL_CNT to set
	 */
	public void setTOTAL_CNT(int tOTAL_CNT) {
		TOTAL_CNT = tOTAL_CNT;
	}

	/**
	 * @return the sUBMIT_CNT
	 */
	public int getSUBMIT_CNT() {
		return SUBMIT_CNT;
	}

	/**
	 * @param sUBMIT_CNT the sUBMIT_CNT to set
	 */
	public void setSUBMIT_CNT(int sUBMIT_CNT) {
		SUBMIT_CNT = sUBMIT_CNT;
	}

	/**
	 * @return the pROGRESS_CNT
	 */
	public int getPROGRESS_CNT() {
		return PROGRESS_CNT;
	}

	/**
	 * @param pROGRESS_CNT the pROGRESS_CNT to set
	 */
	public void setPROGRESS_CNT(int pROGRESS_CNT) {
		PROGRESS_CNT = pROGRESS_CNT;
	}

	/**
	 * @return the cOMPLETE_CNT
	 */
	public int getCOMPLETE_CNT() {
		return COMPLETE_CNT;
	}

	/**
	 * @param cOMPLETE_CNT the cOMPLETE_CNT to set
	 */
	public void setCOMPLETE_CNT(int cOMPLETE_CNT) {
		COMPLETE_CNT = cOMPLETE_CNT;
	}

	/**
	 * @return the pENDING_CNT
	 */
	public int getPENDING_CNT() {
		return PENDING_CNT;
	}

	/**
	 * @param pENDING_CNT the pENDING_CNT to set
	 */
	public void setPENDING_CNT(int pENDING_CNT) {
		PENDING_CNT = pENDING_CNT;
	}

	/**
	 * @return the aBSENCE_CNT
	 */
	public int getABSENCE_CNT() {
		return ABSENCE_CNT;
	}

	/**
	 * @param aBSENCE_CNT the aBSENCE_CNT to set
	 */
	public void setABSENCE_CNT(int aBSENCE_CNT) {
		ABSENCE_CNT = aBSENCE_CNT;
	}

	/**
	 * @return the cANCEL_CNT
	 */
	public int getCANCEL_CNT() {
		return CANCEL_CNT;
	}

	/**
	 * @param cANCEL_CNT the cANCEL_CNT to set
	 */
	public void setCANCEL_CNT(int cANCEL_CNT) {
		CANCEL_CNT = cANCEL_CNT;
	}

	/**
	 * @return the tOTAL_USER_CNT
	 */
	public int getTOTAL_USER_CNT() {
		return TOTAL_USER_CNT;
	}

	/**
	 * @param tOTAL_USER_CNT the tOTAL_USER_CNT to set
	 */
	public void setTOTAL_USER_CNT(int tOTAL_USER_CNT) {
		TOTAL_USER_CNT = tOTAL_USER_CNT;
	}

	/**
	 * @return the bRANCH_USER_CNT
	 */
	public int getBRANCH_USER_CNT() {
		return BRANCH_USER_CNT;
	}

	/**
	 * @param bRANCH_USER_CNT the bRANCH_USER_CNT to set
	 */
	public void setBRANCH_USER_CNT(int bRANCH_USER_CNT) {
		BRANCH_USER_CNT = bRANCH_USER_CNT;
	}

	/**
	 * @return the eVAL_USER_CNT
	 */
	public int getEVAL_USER_CNT() {
		return EVAL_USER_CNT;
	}

	/**
	 * @param eVAL_USER_CNT the eVAL_USER_CNT to set
	 */
	public void setEVAL_USER_CNT(int eVAL_USER_CNT) {
		EVAL_USER_CNT = eVAL_USER_CNT;
	}

	/**
	 * @return the eVAL_MG_USER_CNT
	 */
	public int getEVAL_MG_USER_CNT() {
		return EVAL_MG_USER_CNT;
	}

	/**
	 * @param eVAL_MG_USER_CNT the eVAL_MG_USER_CNT to set
	 */
	public void setEVAL_MG_USER_CNT(int eVAL_MG_USER_CNT) {
		EVAL_MG_USER_CNT = eVAL_MG_USER_CNT;
	}

	/**
	 * @return the sYS_MG_USER_CNT
	 */
	public int getSYS_MG_USER_CNT() {
		return SYS_MG_USER_CNT;
	}

	/**
	 * @param sYS_MG_USER_CNT the sYS_MG_USER_CNT to set
	 */
	public void setSYS_MG_USER_CNT(int sYS_MG_USER_CNT) {
		SYS_MG_USER_CNT = sYS_MG_USER_CNT;
	}

	/**
	 * @return the sUM_PROGRESS_CNT
	 */
	public int getSUM_PROGRESS_CNT() {
		return SUM_PROGRESS_CNT;
	}

	/**
	 * @param sUM_PROGRESS_CNT the sUM_PROGRESS_CNT to set
	 */
	public void setSUM_PROGRESS_CNT(int sUM_PROGRESS_CNT) {
		SUM_PROGRESS_CNT = sUM_PROGRESS_CNT;
	}

}
