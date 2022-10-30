package com.neo.mappers;

import org.springframework.stereotype.Repository;

import com.neo.common.vo.MasterKeyVO;
import com.neo.config.Mapper;

/**
 * 공통매퍼
 * @author leekw
 *
 */
@Mapper
@Repository("commonMapper")
public interface CommonMapper {
	
	/**
	 * 마스터코드추출
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public String getFnGetMasterKey(MasterKeyVO paramVO) throws Exception;
	
}
