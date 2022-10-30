package com.neo.common.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class ComVO {

    private String DEL_YN;
    private String IN_USER;
    private String IN_DTTM;    
    private String UP_USER;
    private String UP_DTTM;     
    private String IN_USER_NAME;
    private String UP_USER_NAME;
    private String loginId;
    private String loginCode;

    /* 검색 param 및 DB 조회로 불러오는 마스터코드 CODE_NAME*/
    private String searchValue;
    private String searchName;
    private String status;
    private String workStatus;
    private String customer;
    private String phoneNumber;
    private String title;
    private String author;
    private String searchDate;
    private String searchStartDt;
    private String searchEndDt;
    private String inUse;
    private String memberAuthority;
    private String lastLoginIP;
    private String branchName;
    private String memberName;
    private String memberStatus;
    /* 검색 및 날짜 */

    /* 페이징	*/
    private int totCount;

    private int ROW_NUM;
    private int pageNum = 1;
    private int limit = 10;
    private int blockSize = 10;
    private int offset = 0;
    private int pageSize = 10;

    /* 페이징	*/

    /* 정렬 */
    private String orderName;
    private String orderValue;
    /* 정렬 */

    public String toStringVo() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .concat("\nVO로그시작 ===========VO종료");
    }

    public String getDEL_YN() {
        return DEL_YN;
    }

    public void setDEL_YN(String dEL_YN) {
        DEL_YN = dEL_YN;
    }

    public String getIN_USER() {
        return IN_USER;
    }

    public void setIN_USER(String iN_USER) {
        IN_USER = iN_USER;
    }

    
    public String getIN_DTTM() {
		return IN_DTTM;
	}

	public void setIN_DTTM(String iN_DTTM) {
		IN_DTTM = iN_DTTM;
	}

	public String getUP_DTTM() {
		return UP_DTTM;
	}

	public void setUP_DTTM(String uP_DTTM) {
		UP_DTTM = uP_DTTM;
	}

	public String getUP_USER() {
        return UP_USER;
    }

    public void setUP_USER(String uP_USER) {
        UP_USER = uP_USER;
    }

    public String getIN_USER_NAME() {
        return IN_USER_NAME;
    }

    public void setIN_USER_NAME(String iN_USER_NAME) {
        IN_USER_NAME = iN_USER_NAME;
    }

    public String getUP_USER_NAME() {
        return UP_USER_NAME;
    }

    public void setUP_USER_NAME(String uP_USER_NAME) {
        UP_USER_NAME = uP_USER_NAME;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getSearchStartDt() {
        return searchStartDt;
    }

    public void setSearchStartDt(String searchStartDt) {
        this.searchStartDt = searchStartDt;
    }

    public String getSearchEndDt() {
        return searchEndDt;
    }

    public void setSearchEndDt(String searchEndDt) {
        this.searchEndDt = searchEndDt;
    }

    public int getROW_NUM() {
        return ROW_NUM;
    }

    public void setROW_NUM(int rOW_NUM) {
        ROW_NUM = rOW_NUM;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }


    public int getBlockSize() {
        return blockSize;
    }

    public void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(String orderValue) {
        this.orderValue = orderValue;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotCount() {
        return totCount;
    }

    public void setTotCount(int totCount) {
        this.totCount = totCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(String workStatus) {
        this.workStatus = workStatus;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public String getInUse() {
        return inUse;
    }

    public void setInUse(String inUse) {
        this.inUse = inUse;
    }

    public String getMemberAuthority() {
        return memberAuthority;
    }

    public void setMemberAuthority(String memberAuthority) {
        this.memberAuthority = memberAuthority;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(String memberStatus) {
        this.memberStatus = memberStatus;
    }
}
