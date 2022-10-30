package com.neo.mappers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.neo.common.vo.DemoFileVO;
import com.neo.common.vo.DemoVO;
import com.neo.config.Mapper;


@Mapper
@Repository("demoMapper")
public interface DemoMapper {
	
	/**
	 * 데모리스트카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int demoListCount(DemoVO paramVO) throws Exception;
	
	/**
	 * 데모리스트조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<DemoVO> demoList(DemoVO paramVO) throws Exception;

	/**
	 * 데모상세
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public DemoVO demoDetail(DemoVO paramVO) throws Exception;
	
	/**
	 * 데모등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int demoInsert(DemoVO paramVO) throws Exception;
	
	/**
	 * 데모수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int demoUpdate(DemoVO paramVO) throws Exception;
	
	/**
	 * 데모삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int demoDelete(DemoVO paramVO) throws Exception;
	
	/**
	 * 데모파일리스트
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public List<DemoFileVO> demoFileList(DemoFileVO paramVO) throws Exception;

	/**
	 * 데모파일상세
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public DemoFileVO demoFileDetail(DemoFileVO paramVO) throws Exception;
	
	/**
	 * 데모파일등록
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public int demoFileInsert(DemoFileVO paramVO) throws Exception;

	/**
	 * 데모파일삭제
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public int demoFileDelete(DemoFileVO paramVO) throws Exception;
}
