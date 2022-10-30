package com.neo.consult.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import com.neo.common.vo.BoardVO;

public interface SalesListService {

	public int salesListCount(BoardVO paramVO) throws Exception;
	
	public List<BoardVO> salesList(BoardVO paramVO) throws Exception;
	
	public BoardVO salesListDetail(BoardVO paramVO) throws Exception;
	
	public BoardVO salesListDetailPre(BoardVO paramVO) throws Exception;
	
	public BoardVO salesListDetailNext(BoardVO paramVO) throws Exception;
	
	public JSONObject salesListInsert(BoardVO paramVO, HttpServletRequest request) throws Exception;
	
	public JSONObject salesListUpdate(BoardVO paramVO, HttpServletRequest request) throws Exception;
	
	public JSONObject salesListDelete(BoardVO paramVO, HttpServletRequest request) throws Exception;
	
	public int salesListUpdateViewCnt(BoardVO paramVO) throws Exception;
}
