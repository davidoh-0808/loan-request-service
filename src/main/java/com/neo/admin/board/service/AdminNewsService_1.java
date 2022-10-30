package com.neo.admin.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import com.neo.common.vo.BoardVO;

public interface AdminNewsService {

	public int newsListCount(BoardVO paramVO) throws Exception;
	public List<BoardVO> newsList(BoardVO paramVO) throws Exception;
	public BoardVO newsDetail(BoardVO paramVO) throws Exception;
	public BoardVO newsDetailPre(BoardVO paramVO) throws Exception;
	public BoardVO newsDetailNext(BoardVO paramVO) throws Exception;
	public JSONObject newsInsert(BoardVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject newsUpdate(BoardVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject newsDelete(BoardVO paramVO, HttpServletRequest request) throws Exception;
	public int newsUpdateViewCnt(BoardVO paramVO) throws Exception;
}
