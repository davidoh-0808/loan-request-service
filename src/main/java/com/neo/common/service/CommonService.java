package com.neo.common.service;

import java.util.List;

import com.neo.common.vo.MasterCodeVO;
import com.neo.common.vo.MasterKeyVO;

public interface CommonService {

	/**
	 * 키코드추출
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public String getFnGetMasterKey(MasterKeyVO paramVO) throws Exception;
	
	/**
	 * 하위전체마스터코드리스트
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public List<MasterCodeVO> subAllListMasterCode(MasterCodeVO paramVO) throws Exception;
	
	/**
	 * 하위마스터코드리스트
	 * @param locale
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
	 * 지점 자동검색
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public MasterCodeVO searchBranch(String branchNm) throws Exception;
}
