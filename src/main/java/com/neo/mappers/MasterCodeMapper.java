package com.neo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.neo.common.vo.MasterCodeVO;

/**
 * 마스터코드매퍼
 * @author leekw
 *
 */
@Mapper
@Repository("masterCodeMapper")
public interface MasterCodeMapper {
	
	/**
	 * 마스터코드리스트조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MasterCodeVO> listMasterCode(MasterCodeVO paramVO) throws Exception;

	/**
	 * 마스터코드상세
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MasterCodeVO detailMasterCode(MasterCodeVO paramVO) throws Exception;
	
	/**
	 * 마스터코드등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int insertMasterCode(MasterCodeVO paramVO) throws Exception;
	
	/**
	 * 마스터코드수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int updateMasterCode(MasterCodeVO paramVO) throws Exception;
	
	/**
	 * 마스터코드삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int deleteMasterCode(MasterCodeVO paramVO) throws Exception;
	
	/**
	 * 하위전체마스터코드리스트
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public List<MasterCodeVO> subAllListMasterCode(MasterCodeVO paramVO) throws Exception;
	
	/**
	 * 하위마스터코드리스트
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public List<MasterCodeVO> subListMasterCode(MasterCodeVO paramVO) throws Exception;

	/**
	 * 그룹코드로 마스터코드 조회
	 *
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	List<MasterCodeVO> detailMasterCodeByGroupCode(MasterCodeVO paramVO) throws Exception;

	/**
	 * 지점명 자동조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MasterCodeVO searchBranch(String branchNm) throws Exception;
	
}
