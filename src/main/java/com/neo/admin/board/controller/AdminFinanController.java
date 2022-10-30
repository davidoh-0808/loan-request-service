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

import com.neo.admin.board.service.AdminFinanService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.BoardVO;
import com.neo.security.CustomAdminDetails;
import com.neo.util.UtilCommon;
import com.neo.util.UtilString;

@Controller
public class AdminFinanController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="adminFinanService")
	private AdminFinanService adminFinanService;
	/**
	 * 재정보고 목록
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@GetMapping("/admin/board/finan/finanList")
	public ModelAndView finanList(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/finan/finanList");
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
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getLimit(), paramVO.getBlockSize(), totCount);
			mav.addObject("paging", pv.print());
		}
		
		mav.addObject("totCount", totCount);
		resultList = adminFinanService.finanList(paramVO);
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 재정보고 등록 폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/board/finan/finanInsertForm")
	public ModelAndView finanInsertForm(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/finan/finanInsertForm");
		return mav;
	}
	
	/**
	 * 재정보고 등록
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/admin/board/finan/finanInsert")
	public ModelAndView finanInsert(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
	
		UtilString.onlyNumReplace(paramVO.getPUBL_DATE());
		
		paramVO.setPUBL_DATE(UtilString.onlyNumReplace(paramVO.getPUBL_DATE()));
		
		resultJson = adminFinanService.finanInsert(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * 재정보고 상세
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/board/finan/finanDetail")
	public ModelAndView demoDetail(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/finan/finanDetail");
		
		BoardVO result = new BoardVO();
		result = adminFinanService.finanDetail(paramVO);
		
//		adminFinanService.finanUpdateViewCnt(paramVO);
		
		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 재정보고 수정 폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/board/finan/finanUpdateForm")
	public ModelAndView finanUpdateForm(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/finan/finanUpdateForm");
		
		BoardVO result = new BoardVO();
		result = adminFinanService.finanDetail(paramVO);
		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);
		return mav;
	}

	/**
	 * 재정보고 수정
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/admin/board/finan/finanUpdate")
	public ModelAndView finanUpdate(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		paramVO.setPUBL_DATE(UtilString.onlyNumReplace(paramVO.getPUBL_DATE()));
		resultJson = adminFinanService.finanUpdate(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	
	/**
	 * 재정보고 삭제
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/admin/board/finan/finanDelete")
	public ModelAndView finanDelete(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		resultJson = adminFinanService.finanDelete(paramVO, request);
		
		logger.info(resultJson.toJSONString());
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	

}
