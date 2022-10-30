package com.neo.consult.user.controller;

import com.neo.common.vo.ConsultMemberVO;
import com.neo.consult.member.service.ConsultMemberService;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilCrypt;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RequestMapping(value = "/consult/user")
@Controller
public class userController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "consultMemberService")
    private ConsultMemberService consultMemberService;


    /**
     * 마이페이지 - 프로파일 페이지 호출
     */
    @GetMapping("/profile")
    public ModelAndView profile(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("consult/user/profile");

        //현재 접속한 유저의 멤버코드를 paramVO 에 담아 쿼리하기
        CustomConsultDetails customConsultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String currentUserMemberCode = customConsultDetails.getMember_code();
        paramVO.setMEMBER_CODE(currentUserMemberCode);
        ConsultMemberVO result = consultMemberService.systemConsultMemberDetail(paramVO);

        // result 의 MEMBER_CODE, MEMBER_PW, MEMBER_EMAIL, MEMBER_PHONE 은 폼 인풋에 숨겨서 profileConfirm, profileUpdateForm 에 보내줄 의도
        mav.addObject("result", result);

        return mav;
    }

    /**
     * 마이페이지 - 본인확인 페이지 호출
     */
    @GetMapping("/profileConfirm")
    public ModelAndView profileConfirm(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("consult/user/profileConfirm");

        //profile.jsp 에서 paramVO 로 멤버코드와 패스워드 (이미 복호화 된) 를 hidden input 을 통해 받아 그대로 넘겨준다
        mav.addObject("result", paramVO);

        return mav;
    }

    @GetMapping("profileUpdateForm")
    public ModelAndView profileUpdateForm(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("consult/user/profileUpdateForm");

        //hidden input form submit 에서 받는 Email 앞주소만 가져오기
        String firstAddr = paramVO.getMEMBER_EMAIL().split("@")[0];
        paramVO.setMEMBER_EMAIL( firstAddr );
        

        //profile.jsp 에서 paramVO 로 멤버코드와 패스워드 (이미 복호화 된) 를 hidden input 을 통해 받아 그대로 넘겨준다
        mav.addObject("result", paramVO);

        return mav;
    }

    @PostMapping("/profileUpdate")
    public ModelAndView profileUpdate(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();
        resultJson = consultMemberService.userProfileUpdate(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }



}
