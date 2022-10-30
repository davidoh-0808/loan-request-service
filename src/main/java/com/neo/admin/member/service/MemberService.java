package com.neo.admin.member.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import com.neo.common.vo.MemberVO;

public interface MemberService {

	int memberListCount(MemberVO paramVO) throws Exception;
	List<MemberVO> memberList(MemberVO paramVO) throws Exception;
	MemberVO memberDetail(MemberVO paramVO) throws Exception;
	JSONObject memberInsert(MemberVO paramVO, HttpServletRequest request) throws Exception;
	JSONObject memberUpdate(MemberVO paramVO, HttpServletRequest request) throws Exception;
	JSONObject memberDelete(MemberVO paramVO, HttpServletRequest request) throws Exception;
	
	/**
	 * 아이디중복체크
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	JSONObject memberIdDupCheck(MemberVO paramVO, HttpServletRequest request) throws Exception;

}
