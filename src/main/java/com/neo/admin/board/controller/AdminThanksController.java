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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.neo.admin.board.service.AdminThanksService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.BoardFileVO;
import com.neo.common.vo.BoardVO;
import com.neo.security.CustomAdminDetails;
import com.neo.util.UtilCommon;

@Controller
public class AdminThanksController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name="adminThanksService")
	private AdminThanksService adminThanksService;
	/**
	 * 고마워요미소금융 목록
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@GetMapping("/admin/board/thanks/thanksList")
	public ModelAndView thanksList(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/thanks/thanksList");
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
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getLimit(), paramVO.getBlockSize(), totCount);
			mav.addObject("paging", pv.print());
		}
		
		mav.addObject("totCount", totCount);
		resultList = adminThanksService.thanksList(paramVO);
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 고마워요미소금융 등록 폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/board/thanks/thanksInsertForm")
	public ModelAndView thanksInsertForm(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/thanks/thanksInsertForm");
		return mav;
	}
	
	/**
	 * 고마워요미소금융 등록
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/admin/board/thanks/thanksInsert")
	public ModelAndView thanksInsert(@ModelAttribute BoardVO paramVO, MultipartHttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		resultJson = adminThanksService.thanksInsert(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	
	/**
	 * 고마워요미소금융 상세
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/board/thanks/thanksDetail")
	public ModelAndView demoDetail(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/thanks/thanksDetail");
		
		BoardVO result = new BoardVO();
		result = adminThanksService.thanksDetail(paramVO);
		
//		adminThanksService.thanksUpdateViewCnt(paramVO);
		// 첨부파일리스트
		BoardFileVO paramFileVO = new BoardFileVO();
		List<BoardFileVO> resultFileList = new ArrayList<BoardFileVO>();
		paramFileVO.setBOARD_CODE(paramVO.getBOARD_CODE());
		resultFileList = adminThanksService.boardFileList(paramFileVO);
		mav.addObject("resultFileList", resultFileList);

		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);
		return mav;
	}

	
	/**
	 * 고마워요미소금융 수정 폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/admin/board/thanks/thanksUpdateForm")
	public ModelAndView thanksUpdateForm(@ModelAttribute BoardVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("admin/board/thanks/thanksUpdateForm");
		
		BoardVO result = new BoardVO();
		result = adminThanksService.thanksDetail(paramVO);
		
		// 첨부파일리스트
		BoardFileVO paramFileVO = new BoardFileVO();
		List<BoardFileVO> resultFileList = new ArrayList<BoardFileVO>();
		paramFileVO.setBOARD_CODE(paramVO.getBOARD_CODE());
		resultFileList = adminThanksService.boardFileList(paramFileVO);
		mav.addObject("resultFileList", resultFileList);

		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);
		return mav;
	}

	/**
	 * 고마워요미소금융 수정
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/admin/board/thanks/thanksUpdate")
	public ModelAndView thanksUpdate(@ModelAttribute BoardVO paramVO, MultipartHttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		resultJson = adminThanksService.thanksUpdate(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	
	/**
	 * 고마워요미소금융 삭제
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/admin/board/thanks/thanksDelete")
	public ModelAndView thanksDelete(@ModelAttribute BoardVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		resultJson = adminThanksService.thanksDelete(paramVO, request);
		
		logger.info(resultJson.toJSONString());
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	

}
