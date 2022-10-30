package com.neo.front.foundation.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.admin.board.service.AdminFinanService;
import com.neo.common.view.PagingViewFront;
import com.neo.common.vo.BoardVO;
import com.neo.common.vo.MemberVO;
import com.neo.util.UtilCommon;
import com.neo.util.UtilString;

@Controller
public class FrontFoundationController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="adminFinanService")
	private AdminFinanService adminFinanService;
	/**
	 * 인사말
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value="/front/foundation/greeting")
	public ModelAndView greeting(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/foundation/greeting");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 재단비젼 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/foundation/vision")
	public ModelAndView vision(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/foundation/vision");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 조직도 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/foundation/organization")
	public ModelAndView organization(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/foundation/organization");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 지점안내 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/foundation/branch")
	public ModelAndView branch(@ModelAttribute MemberVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/foundation/branch");
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	
	/**
	 * 재정보고목록페이지 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/front/foundation/report")
	public ModelAndView report(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/foundation/report");
		
		int totCount = 0;
		List<BoardVO> resultList = new ArrayList<BoardVO>();
		// S:파람 초기값
		// 페이징 처리에 필요
		UtilCommon.setPageRow(paramVO);
		// E:파람 초기값
		paramVO.setBOARD_GUBUN("MC0000500003");	
		
		totCount = adminFinanService.finanListCount(paramVO);
		
		if(totCount > 0) {
			resultList = adminFinanService.finanList(paramVO);
			PagingViewFront pv = new PagingViewFront(paramVO.getPageNum(), paramVO.getLimit(), paramVO.getBlockSize(), totCount);
			mav.addObject("paging", pv.print());
		}
		mav.addObject("totCount", totCount);
		resultList = adminFinanService.finanList(paramVO);
		System.out.println(resultList);
		mav.addObject("resultList", resultList);
		
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 재정보고상세페이지
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/front/foundation/reportDetail")
	public ModelAndView newsDetail(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("front/foundation/reportDetail");
		
		BoardVO result = new BoardVO();
		BoardVO result_pre = new BoardVO();
		BoardVO result_next = new BoardVO();
		
		paramVO.setBOARD_GUBUN("MC0000500003");
		
		result = adminFinanService.finanDetail(paramVO);
		result_pre = adminFinanService.finanDetailPre(paramVO);
		result_next = adminFinanService.finanDetailNext(paramVO);
		
		//paramVO.setBOARD_CODE(null)
		
		adminFinanService.finanUpdateViewCnt(paramVO);
		mav.addObject("result", result);
		mav.addObject("result_pre", result_pre);
		mav.addObject("result_next", result_next);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
}
