package com.neo.consult.consult.service;

import com.neo.common.vo.ConsultInfoVO;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ConsultInfoService {

	public int consultInfoListCount(ConsultInfoVO paramVO) throws Exception;

	public ConsultInfoVO consultStatsCount(ConsultInfoVO paramVO) throws Exception;
	public List<ConsultInfoVO> consultInfoList(ConsultInfoVO paramVO) throws Exception;

	public ConsultInfoVO consultInfoDetail(ConsultInfoVO paramVO) throws Exception;

	public JSONObject consultInfoInsert(ConsultInfoVO paramVO) throws Exception;

	public JSONObject consultInfoUpdate(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception;

	public JSONObject consultInfoDelete(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception;


	public List<ConsultInfoVO> consultInfoExportList(ConsultInfoVO paramVO) throws Exception;

	public int connListCount(ConsultInfoVO paramVO) throws Exception;

	public List<ConsultInfoVO> connList(ConsultInfoVO paramVO) throws Exception;

    
}
