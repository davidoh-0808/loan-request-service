package com.neo.consult.board.controller;

///consult/board/confirm/confirmList

import com.neo.consult.board.service.ConfirmListService;
import com.neo.consult.consult.controller.RequestListController;
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
    <li id="confirmList"><a href="/consult/board/confirm/confirmList" data-auth="CONFIRM_LIST">- 대표번호 승인현황</a>
    </li>
 */

@RequestMapping(value = "/consult/board/confirm")
@Controller
public class ConfirmListController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LocaleResolver localeResolver;
    @Autowired
    MessageSource messageSource;

    @Resource(name = "confirmListService")
    private ConfirmListService confirmListService;


    /**
     * @param paramVO
     * @return ModelAndView : confirmList.jsp 리스트 및 조회 화면
     * @throws Exception
     */
    @RequestMapping(value = "/confirmList")
    public ModelAndView confirmList(@ModelAttribute BoardVO paramVO) throws Exception {
        ModelAndView mav = new ModelAndView("consult/board/confirm/confirmList");
        int totCnt = 0;
        List<BoardVO> resultList = new ArrayList<>();

        //보드 구분 추가
        paramVO.setBOARD_GUBUN("MC0000500004");

        //페이징 처리
        UtilCommon.setPageRow(paramVO);
        totCnt = confirmListService.confirmListCount(paramVO);

        if (totCnt > 0) {
            resultList = confirmListService.confirmList(paramVO);
            PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getPageSize(), paramVO.getBlockSize(), totCnt);

            // jsp 단에서 페이징을 위해 paramVO 담아 보내준다
            mav.addObject("paramVO", paramVO);
            mav.addObject("paging", pv.print());
            mav.addObject("totCnt", totCnt);
        }
        
        mav.addObject("resultList", resultList);

        return mav;
    }


    @RequestMapping(value = "confirmInsertForm")
    public ModelAndView confirmListForm(@ModelAttribute BoardVO paramVO) {
        ModelAndView mav = new ModelAndView("consult/board/confirm/confirmInsertForm");

        return mav;
    }

    @PostMapping("confirmInsert")
    public ModelAndView confirmInsert(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");

        //해당 게시글을 TB_BOARD 에 insert 시 업로드한 등록자 멤버정보를 같이 기록하기 위함
        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        paramVO.setLoginCode(consultDetails.getMember_code());

        JSONObject resultJson = confirmListService.confirmInsert(paramVO, request);
        mav.addObject("resultJson", resultJson);

        return mav;
    }

    @RequestMapping("confirmDetail")
    public ModelAndView confirmDetail(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("consult/board/confirm/confirmDetail");
        BoardVO result;
        result = confirmListService.confirmListDetail(paramVO);

        confirmListService.confirmListUpdateViewCnt(paramVO);

        mav.addObject("result", result);
        mav.addObject("paramVO", paramVO);

        return mav;
    }

    @PostMapping("confirmDelete")
    public ModelAndView confirmDelete(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");

        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        JSONObject resultJson = new JSONObject();
        paramVO.setLoginCode(consultDetails.getMember_code());
        resultJson = confirmListService.confirmListDelete(paramVO, request);

        logger.info(resultJson.toJSONString());

        mav.addObject("resultJson", resultJson);

        return mav;
    }

    @RequestMapping("confirmUpdateForm")
    public ModelAndView confirmUpdateForm(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("consult/board/confirm/confirmUpdateForm");
        BoardVO result = new BoardVO();
        result = confirmListService.confirmListDetail(paramVO);

        mav.addObject("result", result);

        return mav;
    }

    @PostMapping("confirmUpdate")
    public ModelAndView confirmUpdate(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView("jsonView");
        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        JSONObject resultJson = new JSONObject();
        paramVO.setLoginCode(consultDetails.getMember_code());

        resultJson = confirmListService.confirmListUpdate(paramVO, request);
        mav.addObject("resultJson", resultJson);

        return mav;
    }

}
