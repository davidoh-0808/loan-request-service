package com.neo.admin.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.admin.board.service.AdminNewsService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.BoardVO;
import com.neo.security.CustomAdminDetails;
import com.neo.util.UtilCommon;

@Controller
public class AdminNewsController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="adminNewsService")
	private AdminNewsService adminNewsService;
	/**
	 * 뉴스 목록
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@GetMapping("/admin/board/news/newsList")
	public ModelAndView newsList(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/news/newsList");
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
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getLimit(), paramVO.getBlockSize(), totCount);
			mav.addObject("paging", pv.print());
		}
		
		mav.addObject("totCount", totCount);
		resultList = adminNewsService.newsList(paramVO);
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 뉴스 등록 폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/board/news/newsInsertForm")
	public ModelAndView newsInsertForm(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/news/newsInsertForm");
		return mav;
	}
	
	/**
	 * 뉴스 등록
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/admin/board/news/newsInsert")
	public ModelAndView newsInsert(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		resultJson = adminNewsService.newsInsert(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * 뉴스 상세
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/board/news/newsDetail")
	public ModelAndView newsDetail(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/news/newsDetail");
		
		BoardVO result = new BoardVO();
		result = adminNewsService.newsDetail(paramVO);
		
//		adminNewsService.newsUpdateViewCnt(paramVO);
		
		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 뉴스 수정 폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/board/news/newsUpdateForm")
	public ModelAndView newsUpdateForm(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/news/newsUpdateForm");
		
		BoardVO result = new BoardVO();
		result = adminNewsService.newsDetail(paramVO);
		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);
		return mav;
	}

	/**
	 * 뉴스 수정
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/admin/board/news/newsUpdate")
	public ModelAndView newsUpdate(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		resultJson = adminNewsService.newsUpdate(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	
	/**
	 * 뉴스 삭제
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/admin/board/news/newsDelete")
	public ModelAndView newsDelete(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		resultJson = adminNewsService.newsDelete(paramVO, request);
		
		logger.info(resultJson.toJSONString());
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	

}
