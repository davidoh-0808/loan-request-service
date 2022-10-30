package com.neo.mappers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.neo.common.vo.BoardFileVO;
import com.neo.common.vo.BoardVO;
import com.neo.config.Mapper;


@Mapper
@Repository("boardMapper")
public interface BoardMapper {
	
	/**
	 * 게시판리스트카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int boardListCount(BoardVO paramVO) throws Exception;
	
	/**
	 * 게시판리스트조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<BoardVO> boardList(BoardVO paramVO) throws Exception;

	/**
	 * 게시판상세
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BoardVO boardDetail(BoardVO paramVO) throws Exception;
	
	/**
	 * 게시판상세 이전글
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BoardVO boardDetailPre(BoardVO paramVO) throws Exception;
	
	/**
	 * 게시판상세 다음글
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public BoardVO boardDetailNext(BoardVO paramVO) throws Exception;
	
	/**
	 * 게시판등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int boardInsert(BoardVO paramVO) throws Exception;
	
	/**
	 * 게시판수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int boardUpdate(BoardVO paramVO) throws Exception;
	
	/**
	 * 게시판삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int boardDelete(BoardVO paramVO) throws Exception;
	
	/**
	 * 게시판파일리스트
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public List<BoardFileVO> boardFileList(BoardFileVO paramVO) throws Exception;

	/**
	 * 게시판파일상세
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public BoardFileVO boardFileDetail(BoardFileVO paramVO) throws Exception;
	
	/**
	 * 게시판파일등록
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public int boardFileInsert(BoardFileVO paramVO) throws Exception;

	/**
	 * 게시판파일삭제
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public int boardFileDelete(BoardFileVO paramVO) throws Exception;
	
	/**
	 * 게시판조회수증가
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public int boardUpdateViewCnt(BoardVO paramVO) throws Exception;

}
