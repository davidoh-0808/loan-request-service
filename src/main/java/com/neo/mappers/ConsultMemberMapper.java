package com.neo.mappers;

import com.neo.common.vo.ConsultMemberVO;
import com.neo.config.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("consultMemberMapper")
public interface ConsultMemberMapper {


	/**
	 * 상담원 페이지에서 상담원 리스트 용도 (지점 MC0002300001 , 심사팀 MC0002300002 유저만)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int consultantListCount(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 상담원 페이지에서 상담원 리스트 용도 (지점 MC0002300001 , 심사팀 MC0002300002 유저만)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ConsultMemberVO> consultantList(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 심사팀, 시스템관리자 멤버리스트카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int systemConsultMemberListCount(ConsultMemberVO paramVO) throws Exception;



	/**
	 * 심사팀, 시스템관리자 멤버리스트조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<ConsultMemberVO> systemConsultMemberList(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 회원정보상세(PK 멤버코드 기반)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ConsultMemberVO consultMemberDetail(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 심사팀, 시스템관리자 멤버상세
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public ConsultMemberVO systemConsultMemberDetail(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 멤버등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	 public int consultMemberInsert(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 심사팀, 시스템관리자 멤버 등록
	 */
	 public int systemConsultMemberInsert(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 멤버수정
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	 public int consultMemberUpdate(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 멤버로그인
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public ConsultMemberVO consultMemberLogin(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 회원정보상세(아이디기반)
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public ConsultMemberVO consultMemberDetailById(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 회원정보상세 (회원권한 기반 - MEMBER_AUTHORITY)
	 *
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public List<ConsultMemberVO> consultMemberDetailByAuthority(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 회원 삭제 (PK 멤버코드 기반 , USE_YN -> N)
	 *
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public int consultMemberDelete(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 멤버 otpkey 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int memberOtpKeyUpdate(ConsultMemberVO paramVO) throws Exception;

	/**
	 * 멤버 최종로그인일시 업데이트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int memberLastLoginDttmUpdate(ConsultMemberVO paramVO) throws Exception;
	
	
	/**
	 * 멤버 최종로그인 로그 저장
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int memberLoginLog(ConsultMemberVO paramVO) throws Exception;
	
	/**
	 * 멤버 장기미사용 처리 업데이트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int memberDeactivate() throws Exception;

	/**
	 * 대시보드(심사팀관리자 , 시스템관리자) - 현재 유저 권한별 수
	 */
	 public ConsultMemberVO getUserCntByAuth(ConsultMemberVO paramVO) throws Exception;

}
