package com.neo.admin.board.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import com.neo.common.vo.BoardVO;

public interface AdminFinanService {

	public int finanListCount(BoardVO paramVO) throws Exception;
	public List<BoardVO> finanList(BoardVO paramVO) throws Exception;
	public BoardVO finanDetail(BoardVO paramVO) throws Exception;
	public BoardVO finanDetailPre(BoardVO paramVO) throws Exception;
	public BoardVO finanDetailNext(BoardVO paramVO) throws Exception;
	public JSONObject finanInsert(BoardVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject finanUpdate(BoardVO paramVO, HttpServletRequest request) throws Exception;
	public JSONObject finanDelete(BoardVO paramVO, HttpServletRequest request) throws Exception;
	public int finanUpdateViewCnt(BoardVO paramVO) throws Exception;
}
