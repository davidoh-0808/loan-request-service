package com.neo.mappers;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.neo.common.vo.MemberVO;
import com.neo.config.Mapper;


@Mapper
@Repository("memberMapper")
public interface MemberMapper {
	
	/**
	 * 멤버리스트카운트
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int memberListCount(MemberVO paramVO) throws Exception;
	
	/**
	 * 멤버리스트조회
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<MemberVO> memberList(MemberVO paramVO) throws Exception;

	/**
	 * 멤버상세
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public MemberVO memberDetail(MemberVO paramVO) throws Exception;
	
	/**
	 * 멤버등록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int memberInsert(MemberVO paramVO) throws Exception;
	
	/**
	 * 멤버수정
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int memberUpdate(MemberVO paramVO) throws Exception;
	
	/**
	 * 멤버삭제
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public int memberDelete(MemberVO paramVO) throws Exception;
	
	/**
	 * 멤버로그인
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public MemberVO memberLogin(MemberVO paramVO) throws Exception;

	/**
	 * 회원정보상세(아이디기반)
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	public MemberVO memberDetailById(MemberVO paramVO) throws Exception;
	
}
