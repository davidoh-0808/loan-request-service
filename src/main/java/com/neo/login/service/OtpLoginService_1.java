package com.neo.login.service;

import org.json.simple.JSONObject;

import com.neo.common.vo.ConsultMemberVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OtpLoginService {

	//개인 otpkey 저장
    JSONObject otpKeyUpdate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;


    /**
     * otp key중복체크
     *
     * @param paramVO
     * @param request
     * @return
     * @throws Exception
     */
    //JSONObject consultMemberIdDupCheck(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;


}
