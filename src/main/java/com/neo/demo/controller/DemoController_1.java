package com.neo.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.view.PagingView;
import com.neo.common.vo.DemoFileVO;
import com.neo.common.vo.DemoVO;
import com.neo.demo.service.DemoService;
import com.neo.util.UtilCommon;

/**
 * @author leekw
 * 데모컨트롤러
 *
 */
@Controller
public class DemoController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource(name="demoService")
	private DemoService demoService;
	
	/**
	 * 데모 리스트
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/demo/demoList")
	public ModelAndView demoList(@ModelAttribute DemoVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("/demo/demoList");
		
		int totCount = 0;
		List<DemoVO> resultList = new ArrayList<DemoVO>();

		// S:파람 초기값
		// 페이징 처리에 필요
		UtilCommon.setPageRow(paramVO);
		// E:파람 초기값
		
		totCount = demoService.demoListCount(paramVO);
		if(totCount > 0) {
			resultList = demoService.demoList(paramVO);
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getLimit(), paramVO.getBlockSize(), totCount);
			mav.addObject("paging", pv.print());
		}
		
		mav.addObject("totCount", totCount);
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		
		return mav;
	}
	
	/**
	 * 데모 상세
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/demo/demoDetail")
	public ModelAndView demoDetail(@ModelAttribute DemoVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("/demo/demoDetail");
		
		DemoVO result = new DemoVO();
		result = demoService.demoDetail(paramVO);
		mav.addObject("result", result);
		
		// 첨부파일리스트
		DemoFileVO paramFileVO = new DemoFileVO();
		List<DemoFileVO> resultFileList = new ArrayList<DemoFileVO>();
		paramFileVO.setDEMO_CODE(paramVO.getDEMO_CODE());
		resultFileList = demoService.demoFileList(paramFileVO);
		mav.addObject("resultFileList", resultFileList);
		
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 데모 등록폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/demo/demoInsertForm")
	public ModelAndView demoInsertForm(@ModelAttribute DemoVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("demo/demoInsertForm");
		return mav;
	}

	/**
	 * 데모 등록
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/demo/demoInsert")
	public ModelAndView demoInsert(@ModelAttribute DemoVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginId("MB0000000003");
		resultJson = demoService.demoInsert(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * 데모 수정폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/demo/demoUpdateForm")
	public ModelAndView demoUpdateForm(@ModelAttribute DemoVO paramVO) throws Exception{
		ModelAndView mav = new ModelAndView("demo/demoUpdateForm");
		
		DemoVO result = new DemoVO();
		result = demoService.demoDetail(paramVO);
		mav.addObject("result", result);
		
		// 첨부파일리스트
		DemoFileVO paramFileVO = new DemoFileVO();
		List<DemoFileVO> resultFileList = new ArrayList<DemoFileVO>();
		paramFileVO.setDEMO_CODE(paramVO.getDEMO_CODE());
		resultFileList = demoService.demoFileList(paramFileVO);
		mav.addObject("resultFileList", resultFileList);
		
		mav.addObject("paramVO", paramVO);
		return mav;
	}

	/**
	 * 데모 수정
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PutMapping("/demo/demoUpdate")
	public ModelAndView demoUpdate(@ModelAttribute DemoVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginId("MB0000000003");
		resultJson = demoService.demoUpdate(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * 데모 삭제
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@DeleteMapping("/demo/demoDelete")
	public ModelAndView demoDelete(@ModelAttribute DemoVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginId("MB0000000003");
		resultJson = demoService.demoDelete(paramVO, request);
		
		logger.info(resultJson.toJSONString());
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	


	/**
	 * 데모 등록(파일)
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/demo/demoInsert2")
	public ModelAndView demoInsert2(@ModelAttribute DemoVO paramVO, MultipartHttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginId("MB0000000003");
		resultJson = demoService.demoInsert(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	@PutMapping("/demo/demoUpdate2")
	public ModelAndView demoUpdate2(@ModelAttribute DemoVO paramVO, MultipartHttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginId("MB0000000003");
		resultJson = demoService.demoUpdate(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
}
