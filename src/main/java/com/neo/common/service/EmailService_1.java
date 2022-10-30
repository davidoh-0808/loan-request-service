package com.neo.common.service;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.SendMailVO;

public interface EmailService {
    public JSONObject sendEmail(SendMailVO paramVO, HttpServletRequest request) throws Exception;
    public JSONObject sendEmailReport(SendMailVO paramVO, HttpServletRequest request) throws Exception;
    public JSONObject sendEmailOtp(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;
}