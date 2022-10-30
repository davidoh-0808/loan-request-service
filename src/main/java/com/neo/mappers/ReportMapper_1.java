package com.neo.mappers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.neo.common.vo.BoardVO;
import com.neo.common.vo.ReportVO;
import com.neo.config.Mapper;


@Mapper
@Repository("reportMapper")
public interface ReportMapper {
	
	/**
	 * 접속자 정보 적재
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int reportInsert(ReportVO paramVO) throws Exception;
	
	/**
	 * 통계리스트카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int reportListCount(ReportVO paramVO) throws Exception;
	
	/**
	 * 통계기기별리스트조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ReportVO> reportDeviceList(ReportVO paramVO) throws Exception;
	
	/**
	 * 통계페이지뷰리스트조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ReportVO> reportPageList(ReportVO paramVO) throws Exception;

}
