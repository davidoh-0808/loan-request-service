package com.neo.admin.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.neo.common.vo.BoardFileVO;
import com.neo.common.vo.BoardVO;

public interface AdminThanksService {

	public int thanksListCount(BoardVO paramVO) throws Exception;
	public List<BoardVO> thanksList(BoardVO paramVO) throws Exception;
	public BoardVO thanksDetail(BoardVO paramVO) throws Exception;
	public BoardVO thanksDetailPre(BoardVO paramVO) throws Exception;
	public BoardVO thanksDetailNext(BoardVO paramVO) throws Exception;
	public JSONObject thanksUpdate(BoardVO paramVO, MultipartHttpServletRequest request) throws Exception;
	public JSONObject thanksDelete(BoardVO paramVO, HttpServletRequest request) throws Exception;
	public int thanksUpdateViewCnt(BoardVO paramVO) throws Exception;
	
	public JSONObject thanksInsert(BoardVO paramVO, MultipartHttpServletRequest request) throws Exception;
	public List<BoardFileVO> boardFileList(BoardFileVO paramVO) throws Exception;
	
}
