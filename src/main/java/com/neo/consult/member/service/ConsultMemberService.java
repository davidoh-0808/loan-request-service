package com.neo.consult.member.service;

import com.neo.common.vo.ConsultMemberVO;
import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ConsultMemberService {

    /**
     * 상담원 페이지에서 상담원 리스트 용도 (지점 MC0002300001 , 심사팀 MC0002300002 유저만)
     *
     * @param paramVO
     * @return
     * @throws Exception
     */
    int consultantListCount(ConsultMemberVO paramVO) throws Exception;
    List<ConsultMemberVO> consultantList(ConsultMemberVO paramVO) throws Exception;

    /**
     * 시스템 관리 메뉴의 계정/권한 관리 페이지에 카운트 출력: (심사팀관리자 MC0002300003 와 시스템관리자 MC0002300004 만)
     *
     * @param paramVO
     * @return
     * @throws Exception
     */
    int systemConsultMemberListCount(ConsultMemberVO paramVO) throws Exception;
    /**
     * 시스템 관리 메뉴의 계정/권한 관리 페이지에 계정리스트 출력: (심사팀관리자 MC0002300003 와 시스템관리자 MC0002300004 만)
     *
     * @param paramVO
     * @return List<ConsultMemberVO>
     * @throws Exception
     */
    List<ConsultMemberVO> systemConsultMemberList(ConsultMemberVO paramVO) throws Exception;
    ConsultMemberVO consultMemberDetail(ConsultMemberVO paramVO) throws Exception;
    /**
     * 마이페이지 메뉴의 프로필설정 페이지에 수정 기능(완료버튼)
     */
    JSONObject userProfileUpdate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;

    /**
     * 시스템 관리 메뉴의 계정/권한 관리 페이지에 계정상세 출력: (심사팀관리자 MC0002300003 와 시스템관리자 MC0002300004 만)
     *
     * @param paramVO
     * @return ConsultMemberVO
     * @throws Exception
     */
    ConsultMemberVO systemConsultMemberDetail(ConsultMemberVO paramVO) throws Exception;
    JSONObject consultMemberInsert(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;
    
    /**
     *   시스템 관리 메뉴의 계정/권한 관리 페이지에 계정 등록 기능
     *
     * @param paramVO
     * @return JSONObject
     * @throws Exception
     */
    JSONObject systemConsultMemberInsert(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;
    JSONObject consultMemberUpdate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;

    /**
     *   consult OTP 재발행
     *
     * @param paramVO
     * @return JSONObject
     * @throws Exception
     */
    JSONObject otpReissue(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;

    /**
     *  시스템 관리 메뉴의 계정/권한 관리 페이지에 계정 수정 기능
     * 
     * @param paramVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    JSONObject systemConsultMemberUpdate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;
    JSONObject consultMemberPwReset(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;
    JSONObject consultMemberDelete(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;
    /**
     * 아이디중복체크
     *
     * @param paramVO
     * @param request
     * @return JSONObject
     * @throws Exception
     */
    JSONObject consultMemberIdDupCheck(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;
    /**
     * 회원정보상세 (회원권한 기반 - MEMBER_AUTHORITY)
     *
     * @param paramVO
     * @return JSONObject
     * @throws Exception
     */
    List<ConsultMemberVO> consultMemberDetailByAuthority(ConsultMemberVO paramVO) throws Exception;
    /**
     * 장기미사용(1개월) 유저의 사용, 미사용여부 업데이트
     *
     * @param paramVO
     * @return int
     * @throws Exception
     */
    JSONObject consultMemberActivate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception;
    JSONObject consultMemberDeactivate(ConsultMemberVO paramVO) throws Exception;

}
