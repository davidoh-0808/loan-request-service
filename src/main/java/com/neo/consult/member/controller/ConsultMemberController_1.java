package com.neo.consult.member.controller;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.service.CommonService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.MasterCodeVO;
import com.neo.consult.member.service.ConsultMemberService;
import com.neo.login.service.OtpLoginService;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilCommon;
import com.neo.util.UtilJsonResult;
import com.neo.util.UtilOtp;

@Controller(value = "consultMemberController")
public class ConsultMemberController {

	private static final Logger logger = LoggerFactory.getLogger(ConsultMemberController.class);

	@Autowired
	MessageSource messageSource;

	@Resource(name = "consultMemberService")
	private ConsultMemberService consultMemberService;
	@Resource(name = "commonService")
	private CommonService commonService;

	/**
	 * 멤버리스트
	 * 
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/consult/member/consultMemberList")
	public ModelAndView consultMemberList(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		ModelAndView mav = new ModelAndView("consult/member/consultMemberList");
		List<ConsultMemberVO> resultList = new ArrayList<ConsultMemberVO>();
		int totCnt = 0;

		// S:파람 초기값
		// 페이징 처리에 필요
		UtilCommon.setPageRow(paramVO);
		// E:파람 초기값

		totCnt = consultMemberService.consultantListCount(paramVO);
		if (totCnt > 0) {
			resultList = consultMemberService.consultantList(paramVO);
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
	 * 
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/consult/member/consultMemberDetail")
	public ModelAndView consultMemberDetail(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		ModelAndView mav = new ModelAndView("consult/member/consultMemberDetail");
		ConsultMemberVO result = new ConsultMemberVO();

		result = consultMemberService.consultMemberDetail(paramVO);

		List<String> menuAuths = new ArrayList<String>();
		String MENU_AUTH = "";
		/*
		 * if(UtilCommon.isNotEmpty(result)) {
		 * if("1".equalsIgnoreCase(result.getACCOUNT_USE())) { menuAuths.add("계정관리"); }
		 * if("1".equalsIgnoreCase(result.getTHANKS_USE())) { menuAuths.add("고마워요미소금융");
		 * } if("1".equalsIgnoreCase(result.getNEWS_USE())) { menuAuths.add("뉴스"); }
		 * if("1".equalsIgnoreCase(result.getFINAN_USE())) { menuAuths.add("재정보고"); }
		 * if("1".equalsIgnoreCase(result.getREPORT_USE())) { menuAuths.add("통계"); }
		 * for(int i=0; i<menuAuths.size(); i++) {
		 * 
		 * MENU_AUTH += menuAuths.get(i); if(i != (menuAuths.size() -1)){ MENU_AUTH +=
		 * ", "; } } }
		 */

//		result.setMENU_AUTH(Arrays.toString(menuAuths.toArray()));
//		result.setMENU_AUTH(menuAuths.toString());
//		result.setMENU_AUTH(MENU_AUTH);
		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);
		return mav;
	}

	/**
	 * 멤버등록폼
	 * 
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/consult/member/consultMemberInsertForm")
	public ModelAndView consultMemberInsertForm(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		ModelAndView mav = new ModelAndView("consult/member/consultMemberInsertForm");

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
	 * 
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/consult/member/consultMemberInsert")
	public ModelAndView consultMemberInsert(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request)
			throws Exception {
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		ModelAndView mav = new ModelAndView("jsonView");

		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);

		logger.info(paramVO.toStringVo());

		paramVO.setLoginCode(adminDetail.getMember_code());

		resultJson = consultMemberService.consultMemberInsert(paramVO, request);
		logger.info("memberInsert:" + resultJson.toJSONString());

		mav.addObject("resultJson", resultJson);

		return mav;
	}

	/**
	 * 멤버수정폼
	 * 
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/consult/member/consultMemberUpdateForm")
	public ModelAndView consultMemberUpdateForm(@ModelAttribute ConsultMemberVO paramVO) throws Exception {
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		ModelAndView mav = new ModelAndView("consult/member/consultMemberUpdateForm");
		ConsultMemberVO result = new ConsultMemberVO();

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

		result = consultMemberService.consultMemberDetail(paramVO);

		List<String> menuAuths = new ArrayList<String>();

		/*
		 * if(UtilCommon.isNotEmpty(result)) {
		 * if("1".equalsIgnoreCase(result.getACCOUNT_USE())) {
		 * menuAuths.add("ACCOUNT_USE"); }
		 * if("1".equalsIgnoreCase(result.getTHANKS_USE())) {
		 * menuAuths.add("THANKS_USE"); } if("1".equalsIgnoreCase(result.getNEWS_USE()))
		 * { menuAuths.add("NEWS_USE"); }
		 * if("1".equalsIgnoreCase(result.getFINAN_USE())) { menuAuths.add("FINAN_USE");
		 * } if("1".equalsIgnoreCase(result.getREPORT_USE())) {
		 * menuAuths.add("REPORT_USE"); } }
		 * 
		 * result.setMenuAuths(menuAuths);
		 */

		mav.addObject("masterCodeList_memberAuthority", masterCodeList_memberAuthority);
		mav.addObject("masterCodeList_memberStatus", masterCodeList_memberStatus);

		mav.addObject("paramVO", paramVO);
		mav.addObject("result", result);
		return mav;
	}

	/**
	 * 멤버수정
	 * 
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/consult/member/consultMemberUpdate")
	public ModelAndView consultMemberUpdate(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request)
			throws Exception {
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		ModelAndView mav = new ModelAndView("jsonView");

		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);

		paramVO.setLoginCode(adminDetail.getMember_code());

		resultJson = consultMemberService.consultMemberUpdate(paramVO, request);
		logger.info("consultMemberUpdate:" + resultJson.toJSONString());

		mav.addObject("resultJson", resultJson);

		return mav;
	}

	/**
	 * 멤버삭제
	 * 
	 * @param locale
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/consult/member/consultMemberDelete")
	public ModelAndView consultMemberDelete(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request)
			throws Exception {
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication()
				.getDetails();
		ModelAndView mav = new ModelAndView("jsonView");

		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);

		paramVO.setLoginCode(adminDetail.getMember_code());
		paramVO.setDEL_YN("Y");
		resultJson = consultMemberService.consultMemberDelete(paramVO, request);
		logger.info("consultMemberDelete:" + resultJson.toJSONString());

		mav.addObject("resultJson", resultJson);

		return mav;
	}

	/**
	 * 아이디중복확인
	 * 
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/consult/member/consultMemberIdDupCheck")
	public ModelAndView consultMemberIdDupCheck(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);

		resultJson = consultMemberService.consultMemberIdDupCheck(paramVO, request);

		logger.info("consultMemberIdDupCheck:" + resultJson.toJSONString());
		mav.addObject("resultJson", resultJson);
		return mav;
	}

	/**
	 * consult OTP 재발행
	 * 
	 * @param locale
	 * @param paramVO
	 * @return
	 */
	@RequestMapping(value = "/consult/member/otpReissue")
	public ModelAndView otpReissue(@ModelAttribute ConsultMemberVO paramVO, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject resultJson = new JSONObject();
		UtilJsonResult.setReturnCodeFail(resultJson);

		resultJson = consultMemberService.otpReissue(paramVO, request);

		logger.info("otp Reissue:" + resultJson.toJSONString());

		// 등록 완료 후 성공 코드 및 메시지 설정
		UtilJsonResult.setReturnCodeSuc(resultJson);

		mav.addObject("resultJson", resultJson);
		return mav;
	}

	/**
	 * ConsultMember의 휴면 설정 (USE_YN 설정) 사용되는 페이지 : 상담원 수정페이지 , 계정 권한 수정페이지
	 */
	/*
	 * @ResponseStatus(HttpStatus.ACCEPTED)
	 * 
	 * @PostMapping(value = "/consult/member/consultMemberActivate") //@ResponseBody
	 * // 리스폰스 json serialize 하여 ajax 에러방지 (Unexpected token '<', ...) public
	 * ResponseEntity<JSONObject>ModelAndView activate(@ModelAttribute
	 * ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
	 * ModelAndView mav = new ModelAndView("jsonView"); JSONObject resultJson = new
	 * JSONObject(); UtilJsonResult.setReturnCodeFail(resultJson);
	 * 
	 * //jsp form 에는 휴면여부에 불필요한 정보가 모두 담겨있기 때문에 paramVO 를 새로 만든다 (하지만, 업데이트에 필요한
	 * MEMBER_CODE 는 입력) ConsultMemberVO newParamVO = new ConsultMemberVO();
	 * newParamVO.setMEMBER_CODE( paramVO.getMEMBER_CODE() ); resultJson =
	 * consultMemberService.consultMemberActivate(newParamVO, request);
	 * 
	 * mav.addObject("resultJson", resultJson); return mav; }
	 */
	
	/**
	 * ConsultMember의 휴면 설정 (회원근태 MC0000000013 설정) 사용되는 페이지 : 상담원 수정페이지 , 계정 권한 수정페이지
	 */
	@PostMapping(value = "/consult/member/consultMemberActivate")
	public ModelAndView activate(@ModelAttribute ConsultMemberVO paramVO,
			HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject resultJson = new JSONObject();

		// jsp form 에는 휴면여부에 불필요한 정보가 모두 담겨있기 때문에 paramVO 를 새로 만든다 (하지만, 업데이트에 필요한
		// MEMBER_CODE 는 입력)
		ConsultMemberVO newParamVO = new ConsultMemberVO();
		newParamVO.setMEMBER_CODE(paramVO.getMEMBER_CODE());
		resultJson = consultMemberService.consultMemberActivate(newParamVO, request);

		mav.addObject("resultJson", resultJson);
		
		return mav;
	}

}
