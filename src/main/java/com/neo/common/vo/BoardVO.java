package com.neo.common.vo;

import java.util.List;

public class BoardVO extends ComVO {

    private String BOARD_CODE;
    private String BOARD_GUBUN;
    private String TITLE;
    private String CONTENT;
    private String VIEW_CONTENT;
    private int VIEW_CNT;
    private String PUBL_DATE;
    private List<BoardFileVO> fileList;
    private String author;


    public String getBOARD_CODE() {
        return BOARD_CODE;
    }

    public void setBOARD_CODE(String bOARD_CODE) {
        BOARD_CODE = bOARD_CODE;
    }

    public String getBOARD_GUBUN() {
        return BOARD_GUBUN;
    }

    public void setBOARD_GUBUN(String bOARD_GUBUN) {
        BOARD_GUBUN = bOARD_GUBUN;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String tITLE) {
        TITLE = tITLE;
    }

    public String getCONTENT() {
        return CONTENT;
    }

    public void setCONTENT(String cONTENT) {
        CONTENT = cONTENT;
    }

    public String getPUBL_DATE() {
        return PUBL_DATE;
    }

    public void setPUBL_DATE(String pUBL_DATE) {
        PUBL_DATE = pUBL_DATE;
    }

    public int getVIEW_CNT() {
        return VIEW_CNT;
    }

    public void setVIEW_CNT(int vIEW_CNT) {
        VIEW_CNT = vIEW_CNT;
    }

    public List<BoardFileVO> getFileList() {
        return fileList;
    }

    public void setFileList(List<BoardFileVO> fileList) {
        this.fileList = fileList;
    }

    public String getVIEW_CONTENT() {
        return VIEW_CONTENT;
    }

    public void setVIEW_CONTENT(String vIEW_CONTENT) {
        VIEW_CONTENT = vIEW_CONTENT;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public void setAuthor(String author) {
        this.author = author;
    }
}
