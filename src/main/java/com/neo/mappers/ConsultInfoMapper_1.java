package com.neo.mappers;

import com.neo.common.vo.ConsultInfoVO;
import com.neo.config.Mapper;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Mapper
@Repository("consultInfoMapper")
public interface ConsultInfoMapper {

	public List<ConsultInfoVO> consultInfoList(ConsultInfoVO paramVO) throws Exception;
	public ConsultInfoVO consultStatsCount(ConsultInfoVO paramVO) throws Exception;
	public ConsultInfoVO consultInfoDetail(ConsultInfoVO paramVO) throws Exception;

	
	public int consultInfoListCount(ConsultInfoVO paramVO) throws Exception;
    public int branchConsultInfoListCount(ConsultInfoVO paramVO) throws Exception;
    public int evalConsultInfoListCount(ConsultInfoVO paramVO) throws Exception;


    public ConsultInfoVO branchStatsCount(ConsultInfoVO paramVO) throws Exception;
    public ConsultInfoVO evalStatsCount(ConsultInfoVO paramVO) throws Exception;


    public List<ConsultInfoVO> branchConsultInfoList(ConsultInfoVO paramVO) throws Exception;

    public List<ConsultInfoVO> evalConsultInfoList(ConsultInfoVO paramVO) throws Exception;


    public int consultInfoInsert(ConsultInfoVO paramVO) throws Exception;

    public int consultInfoUpdate(ConsultInfoVO paramVO) throws Exception;

    public int consultInfoDelete(ConsultInfoVO paramVO) throws Exception;
    
    public int consultInfoDeleteBatch() throws Exception;

      // 대출상담관리 - 접속현황 페이지(고객인증 리스트)
    public int connListCount(ConsultInfoVO paramVO) throws Exception;
    public List<ConsultInfoVO> connList(ConsultInfoVO paramVO) throws Exception;

    // 엑셀 추출 - 전체리스트를 추출하기위해 consultInfoList(페이징 offset, limit 사용) 대신 별도 리스트 쿼리를 사용
    List<ConsultInfoVO> consultInfoListForExcel(ConsultInfoVO paramVO);
}
