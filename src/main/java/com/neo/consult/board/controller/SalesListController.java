package com.neo.consult.board.controller;

import com.neo.consult.board.service.SalesListService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.BoardVO;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilCommon;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
    게시판 관리
    <li id="salesList"><a href="/consult/board/sales/salesList" data-auth="SALES_LIST">- 영업현황</a></li>
 */

@RequestMapping(value = "/consult/board/sales")
@Controller
public class SalesListController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LocaleResolver localeResolver;
    @Autowired
    MessageSource messageSource;

    @Resource(name = "salesListService")
    private SalesListService salesListService;


    /**
     * @param paramVO
     * @return ModelAndView : salesList.jsp 뷰
     * @throws Exception
     */
    @RequestMapping(value = "salesList")
    public ModelAndView salesList(@ModelAttribute BoardVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("consult/board/sales/salesList");
        int totCnt = 0;
        List<BoardVO> resultList = new ArrayList<>();

        //보드 구분 추가
        paramVO.setBOARD_GUBUN("MC0000500005");

        //페이징 처리
        UtilCommon.setPageRow(paramVO);
        totCnt = salesListService.salesListCount(paramVO);

        if (totCnt > 0) {
            resultList = salesListService.salesList(paramVO);
            PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getPageSize(), paramVO.getBlockSize(), totCnt);

            // jsp 단에서 페이징을 위해 paramVO 담아 보내준다
            mav.addObject("paramVO", paramVO);
            mav.addObject("paging", pv.print());
            mav.addObject("totCnt", totCnt);
        }
        
        mav.addObject("resultList", resultList);

        return mav;
    }

    @RequestMapping(value = "salesInsertForm")
    public ModelAndView salesListForm(@ModelAttribute BoardVO paramVO) {
        ModelAndView mav = new ModelAndView("consult/board/sales/salesInsertForm");

        return mav;
    }

    @PostMapping("salesInsert")
    public ModelAndView salesInsert(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");

        //해당 게시글을 TB_BOARD 에 insert 시 업로드한 등록자 멤버정보를 같이 기록하기 위함
        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        JSONObject resultJson = new JSONObject();
        paramVO.setLoginCode(consultDetails.getMember_code());

        resultJson = salesListService.salesListInsert(paramVO, request);
        mav.addObject("resultJson", resultJson);

        return mav;
    }

    @RequestMapping("salesDetail")
    public ModelAndView salesDetail(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("consult/board/sales/salesDetail");
        BoardVO result;
        result = salesListService.salesListDetail(paramVO);

        salesListService.salesListUpdateViewCnt(paramVO);

        mav.addObject("result", result);
        mav.addObject("paramVO", paramVO);

        return mav;
    }

    @PostMapping("salesDelete")
    public ModelAndView salesDelete(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");

        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        JSONObject resultJson = new JSONObject();
        paramVO.setLoginCode(consultDetails.getMember_code());
        
        resultJson = salesListService.salesListDelete(paramVO, request);

        logger.info(resultJson.toJSONString());

        mav.addObject("resultJson", resultJson);

        return mav;
    }

    @RequestMapping("salesUpdateForm")
    public ModelAndView salesUpdateForm(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("consult/board/sales/salesUpdateForm");
        BoardVO result = new BoardVO();
        result = salesListService.salesListDetail(paramVO);

        mav.addObject("result", result);

        return mav;
    }

    @PostMapping("salesUpdate")
    public ModelAndView salesUpdate(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        JSONObject resultJson = new JSONObject();
        paramVO.setLoginCode(consultDetails.getMember_code());

        resultJson = salesListService.salesListUpdate(paramVO, request);
        mav.addObject("resultJson", resultJson);

        return mav;
    }


}
