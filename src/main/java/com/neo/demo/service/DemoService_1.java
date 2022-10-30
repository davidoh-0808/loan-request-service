package com.neo.demo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.neo.common.vo.DemoFileVO;
import com.neo.common.vo.DemoVO;

public interface DemoService {

	public int demoListCount(DemoVO paramVO) throws Exception;
	public List<DemoVO> demoList(DemoVO paramVO) throws Exception;
	public DemoVO demoDetail(DemoVO paramVO) throws Exception;
	public JSONObject demoInsert(DemoVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject demoUpdate(DemoVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject demoDelete(DemoVO paramVO, HttpServletRequest request) throws Exception;

	/**
	 * 등록(파일)
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject demoInsert(DemoVO paramVO, MultipartHttpServletRequest request) throws Exception;

	/**
	 * 수정(파일)
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public JSONObject demoUpdate(DemoVO paramVO, MultipartHttpServletRequest request) throws Exception;
	
	/**
	 * 첨부파일리스트
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public List<DemoFileVO> demoFileList(DemoFileVO paramVO) throws Exception;

	/**
	 * 첨부파일상세
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public DemoFileVO demoFileDetail(DemoFileVO paramVO) throws Exception;
}
