package com.neo.consult.consult.controller;

import com.neo.common.service.CommonService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.MasterCodeVO;
import com.neo.consult.member.service.ConsultMemberService;
import com.neo.util.UtilCommon;
import com.neo.util.UtilSession;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

/*
    대출상담 관리
    <li id="consultMember"><a href="/consult/consult/consultMember" data-auth="CONSULT_MEMBER">- 상담원
        관리</a>
    </li>
 */
@RequestMapping(value = "/consult/consult")
@Controller
public class ConsultantController {

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
     * 상담원 관리 페이지 호출
     *
     * @param paramVO (ConsultMemberVO), request
     * @return mav
     * @throws Exception
     */
    @GetMapping(value = "/consultantList")
    public ModelAndView consultantList(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("consult/consult/consultantList");
        List<ConsultMemberVO> resultList = new ArrayList<>();

        // 세션 체크하고 admin 의 접근 가능한 URI 와 코드값 과 해당 페이지와 매칭 되는지 확인
        boolean isUserAllowed = UtilSession.checkPageAuthInSession(request);
        if (!isUserAllowed) {
            String errorMessage = messageSource.getMessage("error.server.403", null, localeResolver.resolveLocale(request));

            mav.setViewName("common/error/403error");
            mav.addObject("errorMessage", errorMessage);

            return mav;
        } else {
            mav.setViewName("/consult/consult/consultantList");
        }

        // 페이징 처리 및 결과 리스트 리턴
        UtilCommon.setPageRow(paramVO);
        int totCnt = consultMemberService.consultantListCount(paramVO);

        if (totCnt > 0) {
            resultList = consultMemberService.consultantList(paramVO);
            PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getPageSize(), paramVO.getBlockSize(), totCnt);

            // jsp 단에서 페이징을 위해 paramVO 담아 보내준다
            mav.addObject("paging", pv.print());
            
        }

        // 상담회원권한
        MasterCodeVO memberAuthorityVO = new MasterCodeVO();
        memberAuthorityVO.setGROUP_CODE("MC0000000023");
        List<MasterCodeVO> authority = commonService.detailMasterCodeByGroupCode(memberAuthorityVO);
        // 출력되는 회원권한 선택란은 0 지점, 1 심사팀, 2 심사팀관리자, 3 시스템관리자 중  0, 1만
        authority.remove(2);     
        authority.remove(2);

        // 회원상태(근태)
        MasterCodeVO memberWorkStatusVO = new MasterCodeVO();
        memberWorkStatusVO.setGROUP_CODE("MC0000000013");
        List<MasterCodeVO> member_work_status = commonService.detailMasterCodeByGroupCode(memberWorkStatusVO);


        mav.addObject("MEMBER_WORK_STATUS", member_work_status);
        mav.addObject("AUTHORITY", authority );
        mav.addObject("resultList", resultList);
        mav.addObject("paramVO", paramVO);
        mav.addObject("totCnt", totCnt);
        
        return mav;
    }


    @GetMapping("/consultantInsertForm")
    public ModelAndView consultantInsertForm(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("consult/consult/consultantInsertForm");

        //지점 마스터코드, 값
        MasterCodeVO branchParamVO = new MasterCodeVO();
        branchParamVO.setGROUP_CODE("MC0000000009");
        List<MasterCodeVO> branches = commonService.detailMasterCodeByGroupCode(branchParamVO);
        branches.remove(branches.size()-1); //심사팀 제거
        
        //지점(상담원) 상태 마스터코드, 값
        MasterCodeVO workStatusVO = new MasterCodeVO();
        workStatusVO.setGROUP_CODE("MC0000000013");
        List<MasterCodeVO> workStatuses = commonService.detailMasterCodeByGroupCode(workStatusVO);

        mav.addObject("branches", branches);
        mav.addObject("workStatuses", workStatuses);

        return mav;
    }

    @PostMapping("/consultantIdDupCheck")
    public ModelAndView consultantIdDupCheck(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();

        resultJson = consultMemberService.consultMemberIdDupCheck(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }

    @PostMapping("/consultantInsert")
    public ModelAndView consultantInsert(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();

        resultJson = consultMemberService.consultMemberInsert(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }

    @GetMapping("/consultantDetail")
    public ModelAndView consultantDetail(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
        //paramVO 의 MEMBER_CODE 로 쿼리하여 ConsultMemberDetail 가져오기 -> result 로 담아보내기
        ModelAndView mav = new ModelAndView("consult/consult/consultantDetail");
        ConsultMemberVO result = consultMemberService.consultMemberDetail(paramVO);

        mav.addObject("result", result);

        return mav;
    }

    @GetMapping("/consultantUpdateForm")
    public ModelAndView consultantUpdateForm(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("/consult/consult/consultantUpdateForm");


        //paramVO 의 MEMBER_CODE 로 쿼리하여 ConsultMemberDetail 가져오기
        ConsultMemberVO result = consultMemberService.consultMemberDetail(paramVO);


        //수정할 때 사용되는 select 옵션들에 대한 마스터코드,정보 들을 가져오기
        //지점 마스터코드, 값
        MasterCodeVO branchParamVO = new MasterCodeVO();
        branchParamVO.setGROUP_CODE("MC0000000009");
        List<MasterCodeVO> branches = commonService.detailMasterCodeByGroupCode(branchParamVO);
        branches.remove(branches.size()-1); //심사팀 제거
        
        //지점(상담원) 상태 마스터코드, 값
        MasterCodeVO workStatusVO = new MasterCodeVO();
        workStatusVO.setGROUP_CODE("MC0000000013");
        List<MasterCodeVO> workStatuses = commonService.detailMasterCodeByGroupCode(workStatusVO);


        mav.addObject("branches", branches);
        mav.addObject("workStatuses", workStatuses);
        mav.addObject("result", result);

        return mav;
    }

    @PostMapping("/consultantUpdate")
    public ModelAndView consultantUpdate(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();
        resultJson = consultMemberService.consultMemberUpdate(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }

    @PostMapping("/consultantPwReset")
    public ModelAndView consultantPwReset(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();
        resultJson = consultMemberService.consultMemberPwReset(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }


    @PostMapping("/consultantDelete")
    public ModelAndView consultantDelete(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        JSONObject resultJson = new JSONObject();

        resultJson = consultMemberService.consultMemberDelete(paramVO, request);

        mav.addObject("resultJson", resultJson);

        return mav;
    }

}
