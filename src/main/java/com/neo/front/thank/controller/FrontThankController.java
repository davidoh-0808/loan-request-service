package com.neo.front.thank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.admin.board.service.AdminNewsService;
import com.neo.admin.board.service.AdminThanksService;
import com.neo.common.view.PagingView;
import com.neo.common.view.PagingViewFront;
import com.neo.common.vo.BoardFileVO;
import com.neo.common.vo.BoardVO;
import com.neo.common.vo.MemberVO;
import com.neo.util.UtilCommon;

@Controller
public class FrontThankController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="adminNewsService")
	private AdminNewsService adminNewsService;
	
	@Resource(name="adminThanksService")
	private AdminThanksService adminThanksService;
	
	/**
	 * 고마워요미소금융 목록 페이지
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/thank/finance")
	public ModelAndView finance(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/thank/finance");
		
		int totCount = 0;
		List<BoardVO> resultList = new ArrayList<BoardVO>();
		// S:파람 초기값
		// 페이징 처리에 필요
		UtilCommon.setPageRow(paramVO);
		// E:파람 초기값
		paramVO.setBOARD_GUBUN("MC0000500002");	
		
		totCount = adminThanksService.thanksListCount(paramVO);
		
		if(totCount > 0) {
			resultList = adminThanksService.thanksList(paramVO);
			PagingViewFront pv = new PagingViewFront(paramVO.getPageNum(), paramVO.getLimit(), paramVO.getBlockSize(), totCount);
			mav.addObject("paging", pv.print());
			
			
			BoardFileVO paramFileVO;
			for(BoardVO item : resultList) {
				// 첨부파일리스트
				paramFileVO = new BoardFileVO();
				paramFileVO.setBOARD_CODE(item.getBOARD_CODE());
				item.setFileList(adminThanksService.boardFileList(paramFileVO));
				// 에디터데이타는 html 이므로 unescape 한다
				item.setCONTENT(StringEscapeUtils.unescapeHtml4(item.getCONTENT()));
			}
		}
		
		mav.addObject("totCount", totCount);
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 고마워요미소금융 상세페이지
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/front/thank/financeDetail")
	public ModelAndView financeDetail(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("/front/thank/financeDetail");
		
		BoardVO result = new BoardVO();
		BoardVO result_pre = new BoardVO();
		BoardVO result_next = new BoardVO();
		
		paramVO.setBOARD_GUBUN("MC0000500002");
		
		result = adminThanksService.thanksDetail(paramVO);
		result_pre = adminThanksService.thanksDetailPre(paramVO);
		result_next = adminThanksService.thanksDetailNext(paramVO);
		
		//paramVO.setBOARD_CODE(null)
		
		adminThanksService.thanksUpdateViewCnt(paramVO);
		mav.addObject("result", result);
		mav.addObject("result_pre", result_pre);
		mav.addObject("result_next", result_next);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 뉴스목록페이지 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/thank/news")
	public ModelAndView news(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/thank/news");
		
		int totCount = 0;
		List<BoardVO> resultList = new ArrayList<BoardVO>();
		// S:파람 초기값
		// 페이징 처리에 필요
		UtilCommon.setPageRow(paramVO);
		// E:파람 초기값
		paramVO.setBOARD_GUBUN("MC0000500001");	
		
		totCount = adminNewsService.newsListCount(paramVO);
		
		if(totCount > 0) {
			resultList = adminNewsService.newsList(paramVO);
			PagingViewFront pv = new PagingViewFront(paramVO.getPageNum(), paramVO.getLimit(), paramVO.getBlockSize(), totCount);
			mav.addObject("paging", pv.print());
		}
		
		mav.addObject("totCount", totCount);
		resultList = adminNewsService.newsList(paramVO);
		mav.addObject("resultList", resultList);
		
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 뉴스상세페이지
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/front/thank/newsDetail")
	public ModelAndView newsDetail(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("/front/thank/newsDetail");
		
		BoardVO result = new BoardVO();
		BoardVO result_pre = new BoardVO();
		BoardVO result_next = new BoardVO();
		
		paramVO.setBOARD_GUBUN("MC0000500001");
		
		result = adminNewsService.newsDetail(paramVO);
		result_pre = adminNewsService.newsDetailPre(paramVO);
		result_next = adminNewsService.newsDetailNext(paramVO);
		
		//paramVO.setBOARD_CODE(null)
		
		adminNewsService.newsUpdateViewCnt(paramVO);
		mav.addObject("result", result);
		mav.addObject("result_pre", result_pre);
		mav.addObject("result_next", result_next);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 고객의소리 페이지 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/thank/report")
	public ModelAndView report(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/thank/report");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
}
