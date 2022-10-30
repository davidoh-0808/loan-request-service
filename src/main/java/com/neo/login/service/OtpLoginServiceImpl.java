package com.neo.login.service;

import com.neo.common.vo.ConsultMemberVO;
import com.neo.mappers.ConsultMemberMapper;
import com.neo.util.UtilJsonResult;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Transactional
@Service("OtpLoginService")
public class OtpLoginServiceImpl implements OtpLoginService {

    @Autowired
    MessageSource messageSource;
    @Autowired
    LocaleResolver localeResolver;
    
    @Resource(name = "consultMemberMapper")
    private ConsultMemberMapper consultMemberMapper;


    @Override
    public JSONObject otpKeyUpdate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        
    	JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		
    	consultMemberMapper.memberOtpKeyUpdate(paramVO);
    	
    	UtilJsonResult.setReturnCodeSuc(json);
    	
    	return json;
    }

//    @Override
//    public JSONObject consultMemberIdDupCheck(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
//        return null;
//    }

}
