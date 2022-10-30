package com.neo.consult.system.controller;

import com.neo.common.service.CommonService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.MasterCodeVO;
import com.neo.consult.member.service.ConsultMemberService;
import com.neo.util.UtilCommon;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/consult/system/")
@Controller
public class SystemController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LocaleResolver localeResolver;
    @Autowired
    MessageSource messageSource;
    @Resource(name = "commonService")
    private CommonService commonService;
    @Resource(name = "consultMemberService")
    private ConsultMemberService consultMemberService;

    /**
     * 시스템 관리 - 계정/권한관리 페이지 (심사팀관리자 MC0002300003 와 시스템관리자 MC0002300004 만 리스팅)
     *
     * @param paramVO (ConsultMemberVO)
     * @return mav
     * @throws Exception
     */
     @GetMapping("/memberList")
     public ModelAndView memberList(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("consult/system/memberList");
         int totCnt = 0;
         List<ConsultMemberVO> resultList = new ArrayList<>();

         //세션 체크 스킵

         UtilCommon.setPageRow(paramVO);
         //심사팀관리자 MC0002300003, 시스템 관리자 MC0002300004 만 카운트
         totCnt = consultMemberService.systemConsultMemberListCount(paramVO);

         if (totCnt > 0) {
             //심사팀관리자 MC0002300003, 시스템 관리자 MC0002300004 만 select
             resultList = consultMemberService.systemConsultMemberList(paramVO);
             PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getPageSize(), paramVO.getBlockSize(), totCnt);

             mav.addObject("paging", pv.print());
             mav.addObject("totCnt", totCnt);
         }

         // 상담회원권한 스킵 : 조회에서 사용되는 회원권한 은 두개 (심사팀관리자 MC0002300003, 시스템관리자 MC0002300004) 밖에 없기 때문에 memberList.jsp 화면에 직접 입력

         //회원상태(MC0000000007) : 대기 MC0000700001 , 승인 MC0000700002 , 차단 MC0000700003
         MasterCodeVO memberStatusVO = new MasterCodeVO();
         memberStatusVO.setGROUP_CODE("MC0000000007");
         List<MasterCodeVO> memberStatuses = commonService.detailMasterCodeByGroupCode(memberStatusVO);

         mav.addObject("paramVO", paramVO);
         mav.addObject("totCnt", totCnt);
         mav.addObject("memberStatuses", memberStatuses);
         mav.addObject("resultList", resultList);

        return mav;
     }


    /**
     * 시스템 관리 - 계정/권한관리 페이지 (심사팀관리자 MC0002300003 와 시스템관리자 MC0002300004 만 리스팅)
     *
     * @param paramVO (ConsultMemberVO)
     * @return mav
     * @throws Exception
     */
     @GetMapping("/memberInsertForm")
     public ModelAndView memberInsertForm(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
         ModelAndView mav = new ModelAndView("consult/system/memberInsertForm");

         //권한 MEMBER_AUTHORITY 선택옵셥은 두개만 존재하므로, 화면에 직접 입력

         //회원상태(MC0000000007) : 대기 MC0000700001 , 승인 MC0000700002 , 차단 MC0000700003
         MasterCodeVO memberStatusVO = new MasterCodeVO();
         memberStatusVO.setGROUP_CODE("MC0000000007");
         List<MasterCodeVO> memberStatuses = commonService.detailMasterCodeByGroupCode(memberStatusVO);


         mav.addObject("memberStatuses", memberStatuses);

         return mav;
     }


    /**
     * 시스템 관리 메뉴- 계정/권한관리 페이지 : 아이디 중복체크 기능
     * (ConsultListController 의 메소드와 동일한 서비스 메소드 사용)
     *
     * @param paramVO (ConsultMemberVO), request
     * @return mav
     * @throws Exception
     */
    @PostMapping("/memberIdDupCheck")
    public ModelAndView memberIdDupCheck(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();

        resultJson = consultMemberService.consultMemberIdDupCheck(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }


    /**
     * 시스템 관리 메뉴 - 계정/권한관리 페이지 : 심사팀관리자, 시스템관리자 등록
     * (ConsultListController 의 메소드와 다른 서비스 메소드 사용)
     *
     * @param paramVO (ConsultMemberVO), request
     * @return mav
     * @throws Exception
     */
    @PostMapping("/memberInsert")
    public ModelAndView memberInsert(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();

        resultJson = consultMemberService.systemConsultMemberInsert(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }


    /**
     * 시스템 관리 메뉴 - 계정/권한관리 페이지 : 심사팀관리자, 시스템관리자 상세 화면 호출
     * (ConsultListController 의 메소드와 동일한 서비스 메소드 사용)
     *
     * @param paramVO (ConsultMemberVO)
     * @return mav
     * @throws Exception
     */
    @GetMapping("/memberDetail")
    public ModelAndView memberDetail(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
        //paramVO 의 MEMBER_CODE 로 쿼리하여 ConsultMemberDetail 가져오기 -> result 로 담아보내기
        ModelAndView mav = new ModelAndView("consult/system/memberDetail");
        ConsultMemberVO result = consultMemberService.systemConsultMemberDetail(paramVO);

        mav.addObject("result", result);

        return mav;
    }


    /**
     * 시스템 관리 메뉴 - 계정/권한관리 페이지 : 심사팀관리자, 시스템관리자 수정 화면 호출
     * (ConsultListController 의 메소드와 동일한 서비스 메소드 사용)
     *
     * @param paramVO (ConsultMemberVO)
     * @return mav
     * @throws Exception
     */
    @GetMapping("/memberUpdateForm")
    public ModelAndView memberUpdateForm(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("/consult/system/memberUpdateForm");


        //paramVO 의 MEMBER_CODE 로 쿼리하여 ConsultMemberDetail 가져오기
        ConsultMemberVO result = consultMemberService.consultMemberDetail(paramVO);


        //수정할 때 사용되는 select 옵션들에 대한 마스터코드,정보 들을 가져오기
        //심사팀관리자, 시스템관리자 의 상태 WORK_STATUS(MC0000000007) 마스터코드, 값
        MasterCodeVO memberStatusVO = new MasterCodeVO();
        memberStatusVO.setGROUP_CODE("MC0000000007");
        List<MasterCodeVO> memberStatuses = commonService.detailMasterCodeByGroupCode(memberStatusVO);

        mav.addObject("memberStatuses", memberStatuses);
        mav.addObject("result", result);

        return mav;
    }


    /**
     * 시스템 관리 메뉴 - 계정/권한관리 페이지 : 심사팀관리자, 시스템관리자 수정
     * (ConsultListController 의 메소드와 동일한 서비스 메소드 사용)
     *
     * @param paramVO (ConsultMemberVO)
     * @return mav
     * @throws Exception
     */
    @PostMapping("/memberUpdate")
    public ModelAndView memberUpdate(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();
        resultJson = consultMemberService.systemConsultMemberUpdate(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }


    /**
     * 시스템 관리 메뉴 - 계정/권한관리 페이지 : 심사팀관리자, 시스템관리자 패스워드 리셋 -> Miso!@34
     * (ConsultListController 의 메소드와 동일한 서비스 메소드 사용)
     *
     * @param paramVO (ConsultMemberVO)
     * @return mav
     * @throws Exception
     */
    @PostMapping("/memberPwReset")
    public ModelAndView memberPwReset(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();
        resultJson = consultMemberService.consultMemberPwReset(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }


    /**
     * 시스템 관리 메뉴 - 계정/권한관리 페이지 : 심사팀관리자, 시스템관리자 삭제
     * (ConsultListController 의 메소드와 동일한 서비스 메소드 사용)
     *
     * @param paramVO (ConsultMemberVO)
     * @return mav
     * @throws Exception
     */
    @PostMapping("/memberDelete")
    public ModelAndView memberDelete(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();

        resultJson = consultMemberService.consultMemberDelete(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }



}
