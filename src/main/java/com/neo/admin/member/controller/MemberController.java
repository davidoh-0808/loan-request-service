package com.neo.admin.member.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.neo.admin.member.service.MemberService;
import com.neo.common.service.CommonService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.MasterCodeVO;
import com.neo.common.vo.MemberVO;
import com.neo.security.CustomAdminDetails;
import com.neo.util.UtilCommon;
import com.neo.util.UtilJsonResult;


@Controller(value = "adminMemberController")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired	MessageSource messageSource;
	
	@Resource(name="adminMemberService")
	private MemberService memberService;
	@Resource(name="commonService")
	private CommonService commonService;
	
	/**
	 * 멤버리스트
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/member/memberList")
	public ModelAndView memberList(@ModelAttribute MemberVO paramVO) throws Exception {
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		ModelAndView mav = new ModelAndView("admin/member/memberList");

		int totCnt = 0;
		List<MemberVO> resultList = new ArrayList<MemberVO>();

		// S:파람 초기값
		// 페이징 처리에 필요
		UtilCommon.setPageRow(paramVO);
		// E:파람 초기값
		
		totCnt = memberService.memberListCount(paramVO);
		if(totCnt > 0) {
			resultList = memberService.memberList(paramVO);
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getPageSize(), paramVO.getBlockSize(), totCnt);
			mav.addObject("paging", pv.print());
		}
		
		mav.addObject("totCnt", totCnt);
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		
		return mav;
	}
	
	/**
	 * 멤버상세
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/member/memberDetail")
	public ModelAndView memberDetail(@ModelAttribute MemberVO paramVO) throws Exception {
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		ModelAndView mav = new ModelAndView("admin/member/memberDetail");
		MemberVO result = new MemberVO();
		
		result = memberService.memberDetail(paramVO);
		
		List<String> menuAuths = new ArrayList<String>();
		String MENU_AUTH = "";
		if(UtilCommon.isNotEmpty(result)) {
			if("1".equalsIgnoreCase(result.getACCOUNT_USE())) {
				menuAuths.add("계정관리");
			}
			if("1".equalsIgnoreCase(result.getTHANKS_USE())) {
				menuAuths.add("고마워요미소금융");
			}
			if("1".equalsIgnoreCase(result.getNEWS_USE())) {
				menuAuths.add("뉴스");
			}
			if("1".equalsIgnoreCase(result.getFINAN_USE())) {
				menuAuths.add("재정보고");
			}
			if("1".equalsIgnoreCase(result.getREPORT_USE())) {
				menuAuths.add("통계");
			}
			for(int i=0; i<menuAuths.size(); i++) {
				
				MENU_AUTH += menuAuths.get(i);
				if(i != (menuAuths.size() -1)){
					MENU_AUTH += ", ";
				}
			}
		}
		

//		result.setMENU_AUTH(Arrays.toString(menuAuths.toArray()));
//		result.setMENU_AUTH(menuAuths.toString());
		result.setMENU_AUTH(MENU_AUTH);
		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 멤버등록폼
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/member/memberInsertForm")
	public ModelAndView memberInsertForm(@ModelAttribute MemberVO paramVO) throws Exception{
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		ModelAndView mav = new ModelAndView("admin/member/memberInsertForm");
		
		// 회원권한(MC0000000006)
		MasterCodeVO param_memberAuthority = new MasterCodeVO();
		List<MasterCodeVO> masterCodeList_memberAuthority = new ArrayList<MasterCodeVO>();
		param_memberAuthority.setPARENT_MASTER_CODE("MC0000000006");
		masterCodeList_memberAuthority = commonService.subListMasterCode(param_memberAuthority);
		
		// 회원상태(MC0000000007)
		MasterCodeVO param_memberStatus = new MasterCodeVO();
		List<MasterCodeVO> masterCodeList_memberStatus = new ArrayList<MasterCodeVO>();
		param_memberStatus.setPARENT_MASTER_CODE("MC0000000007");
		masterCodeList_memberStatus = commonService.subListMasterCode(param_memberStatus);
		
		mav.addObject("masterCodeList_memberAuthority", masterCodeList_memberAuthority);
		mav.addObject("masterCodeList_memberStatus", masterCodeList_memberStatus);
		
		return mav;
	}

	/**
	 * 멤버등록
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/admin/member/memberInsert")
	public ModelAndView memberInsert(@ModelAttribute MemberVO paramVO, HttpServletRequest request) throws Exception{
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		ModelAndView mav = new ModelAndView("jsonView");
		
		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);
		
		logger.info(paramVO.toStringVo());
		
		paramVO.setLoginCode(adminDetail.getMember_code());
		
		resultJson = memberService.memberInsert(paramVO, request);
		logger.info("memberInsert:" + resultJson.toJSONString());
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * 멤버수정폼
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/member/memberUpdateForm") 
	public ModelAndView memberUpdateForm(@ModelAttribute MemberVO paramVO) throws Exception {
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		ModelAndView mav = new ModelAndView("admin/member/memberUpdateForm");
		MemberVO result = new MemberVO();

		// 회원권한(MC0000000006)
		MasterCodeVO param_memberAuthority = new MasterCodeVO();
		List<MasterCodeVO> masterCodeList_memberAuthority = new ArrayList<MasterCodeVO>();
		param_memberAuthority.setPARENT_MASTER_CODE("MC0000000006");
		masterCodeList_memberAuthority = commonService.subListMasterCode(param_memberAuthority);
		
		// 회원상태(MC0000000007)
		MasterCodeVO param_memberStatus = new MasterCodeVO();
		List<MasterCodeVO> masterCodeList_memberStatus = new ArrayList<MasterCodeVO>();
		param_memberStatus.setPARENT_MASTER_CODE("MC0000000007");
		masterCodeList_memberStatus = commonService.subListMasterCode(param_memberStatus);
		
		result = memberService.memberDetail(paramVO);
		
		List<String> menuAuths = new ArrayList<String>();
		
		if(UtilCommon.isNotEmpty(result)) {
			if("1".equalsIgnoreCase(result.getACCOUNT_USE())) {
				menuAuths.add("ACCOUNT_USE");
			}
			if("1".equalsIgnoreCase(result.getTHANKS_USE())) {
				menuAuths.add("THANKS_USE");
			}
			if("1".equalsIgnoreCase(result.getNEWS_USE())) {
				menuAuths.add("NEWS_USE");
			}
			if("1".equalsIgnoreCase(result.getFINAN_USE())) {
				menuAuths.add("FINAN_USE");
			}
			if("1".equalsIgnoreCase(result.getREPORT_USE())) {
				menuAuths.add("REPORT_USE");
			}
		}
		
		result.setMenuAuths(menuAuths);
		
		mav.addObject("masterCodeList_memberAuthority", masterCodeList_memberAuthority);
		mav.addObject("masterCodeList_memberStatus", masterCodeList_memberStatus);
		
		mav.addObject("paramVO", paramVO);
		mav.addObject("result", result);
		return mav;
	}
	
	/**
	 * 멤버수정
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/admin/member/memberUpdate") 
	public ModelAndView memberUpdate(@ModelAttribute MemberVO paramVO, HttpServletRequest request) throws  Exception{
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		ModelAndView mav = new ModelAndView("jsonView");
		
		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);
		
		paramVO.setLoginCode(adminDetail.getMember_code());
		
		resultJson = memberService.memberUpdate(paramVO, request);	
		logger.info("memberUpdate:" + resultJson.toJSONString());
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * 멤버삭제
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value="/admin/member/memberDelete") 
	public ModelAndView deleteMember(@ModelAttribute MemberVO paramVO, HttpServletRequest request) throws  Exception{
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		ModelAndView mav = new ModelAndView("jsonView");
		
		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);
		
		paramVO.setLoginCode(adminDetail.getMember_code());
		paramVO.setDEL_YN("Y");
		resultJson = memberService.memberDelete(paramVO, request);
		logger.info("memberDelete:" + resultJson.toJSONString());
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * 아이디중복확인
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/admin/member/memberIdDupCheck")
	public ModelAndView memberIdDupCheck(@ModelAttribute MemberVO paramVO, HttpServletRequest request) throws Exception {
		CustomAdminDetails adminDetail = (CustomAdminDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);
		
		resultJson = memberService.memberIdDupCheck(paramVO, request);
		
		logger.info("memberIdDupCheck:" + resultJson.toJSONString());
		mav.addObject("resultJson", resultJson);
		return mav;
	}
	
}
