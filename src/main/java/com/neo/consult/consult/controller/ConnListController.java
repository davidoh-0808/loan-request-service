package com.neo.consult.consult.controller;

import com.neo.common.service.CommonService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.ConsultInfoVO;
import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.MasterCodeVO;
import com.neo.consult.consult.service.ConsultInfoService;
import com.neo.consult.member.service.ConsultMemberService;
import com.neo.util.UtilCommon;
import com.neo.util.UtilSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/*
    대출상담 관리
    <li id="connList"><a href="/consult/consult/connList" data-auth="CONN_LIST">- 접속현황</a></li>
 */
@RequestMapping(value = "/consult/consult")
@Controller
public class ConnListController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LocaleResolver localeResolver;
    @Autowired
    MessageSource messageSource;
    @Resource(name = "consultInfoService")
    private ConsultInfoService consultInfoService;
    @Resource(name = "commonService")
    private CommonService commonService;


    /**
     * 고객회원 접속현황 리스트 (상담신청내역 ConsultInfo 의 정보들을 참조)
     *
     * @param paramVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/connList")
    public ModelAndView connList(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        List<ConsultInfoVO> resultList = new ArrayList<>();

        //세션 체크하고 consult 의 접근 가능한 URI 와 코드값 과 해당 페이지와 매칭 되는지 확인
        boolean isUserAllowed = UtilSession.checkPageAuthInSession(request);
        if (!isUserAllowed) {
            String errorMessage = messageSource.getMessage("error.server.403", null, localeResolver.resolveLocale(request));

            mav.setViewName("common/error/403error");
            mav.addObject("errorMessage", errorMessage);

            return mav;
        } else {
            mav.setViewName("consult/consult/connList");
        }

        // 페이징 처리
        UtilCommon.setPageRow(paramVO);

        int totCnt = 0;
        totCnt = consultInfoService.connListCount(paramVO);
        if(totCnt > 0) {
            resultList = consultInfoService.connList(paramVO);
            PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getPageSize(), paramVO.getBlockSize(), totCnt);

            mav.addObject("paging", pv.print());
        }

        // search param 인증수단
        MasterCodeVO certiMthdTpVO = new MasterCodeVO();
        certiMthdTpVO.setGROUP_CODE("MC0000000014");
        List<MasterCodeVO> certiMthdTP = commonService.detailMasterCodeByGroupCode(certiMthdTpVO);


        mav.addObject("paramVO", paramVO);
        mav.addObject("totCnt", totCnt);
        mav.addObject("resultList", resultList);
        mav.addObject("certiMthdTP", certiMthdTP);

        return mav;
    }


}
