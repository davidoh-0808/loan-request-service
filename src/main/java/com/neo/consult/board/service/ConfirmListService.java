package com.neo.consult.board.service;

import com.neo.common.vo.BoardVO;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ConfirmListService {

	public int confirmListCount(BoardVO paramVO) throws Exception;

	public List<BoardVO> confirmList(BoardVO paramVO) throws Exception;

	public JSONObject confirmInsert(BoardVO paramVO, HttpServletRequest request) throws Exception;
    
	public BoardVO confirmListDetail(BoardVO paramVO) throws Exception;

	public BoardVO confirmListDetailPre(BoardVO paramVO) throws Exception;

	public BoardVO confirmListDetailNext(BoardVO paramVO) throws Exception;


	public JSONObject confirmListUpdate(BoardVO paramVO, HttpServletRequest request) throws Exception;

	public JSONObject confirmListDelete(BoardVO paramVO, HttpServletRequest request) throws Exception;

	public int confirmListUpdateViewCnt(BoardVO paramVO) throws Exception;
}
