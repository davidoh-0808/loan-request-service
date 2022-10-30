package com.neo.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.neo.common.vo.MasterCodeVO;
import com.neo.common.vo.MasterKeyVO;
import com.neo.mappers.CommonMapper;
import com.neo.mappers.MasterCodeMapper;

@Service("commonService")
public class CommonServiceImpl implements CommonService {

	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired	MessageSource messageSource;
	
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;

	@Resource(name = "masterCodeMapper")
	private MasterCodeMapper masterCodeMapper;

	
	@Override
	public String getFnGetMasterKey(MasterKeyVO paramVO) throws Exception {
		return commonMapper.getFnGetMasterKey(paramVO);
	}

	@Override
	public List<MasterCodeVO> subAllListMasterCode(MasterCodeVO paramVO) throws Exception {
		return masterCodeMapper.subAllListMasterCode(paramVO);
	}

	@Override
	public List<MasterCodeVO> subListMasterCode(MasterCodeVO paramVO) throws Exception {
		return masterCodeMapper.subListMasterCode(paramVO);
	}

	@Override
	public List<MasterCodeVO> detailMasterCodeByGroupCode(MasterCodeVO paramVO) throws Exception {
		return masterCodeMapper.detailMasterCodeByGroupCode(paramVO);
	}

	@Override
	public MasterCodeVO searchBranch(String branchNm) throws Exception {
		return masterCodeMapper.searchBranch(branchNm);
	}
	
}
