package com.neo.front.consultation.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import com.neo.common.vo.ConsultInfoVO;

public interface FrontConsultationService {

	public JSONObject frontRequestInsert(ConsultInfoVO paramVO, HttpServletRequest request) throws Exception;
}
