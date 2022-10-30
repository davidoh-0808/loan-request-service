package com.neo.consult.consult.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import com.neo.common.service.CommonService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.ConsultInfoVO;
import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.MasterCodeVO;
import com.neo.consult.consult.service.RequestListService;
import com.neo.consult.member.service.ConsultMemberService;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilCommon;
import com.neo.util.UtilCrypt;
import com.neo.util.UtilSession;

@RequestMapping(value = "/consult/consult")
@Controller(value = "requestListController")
public class RequestListController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LocaleResolver localeResolver;
    // 로그 메시지에 실행환경에 대한 정보를 담기위해 필요 (locale specific validation message)
    @Autowired
    MessageSource messageSource;

	@Resource(name = "commonService")
	private CommonService commonService;
    @Resource(name = "requestListService")
    private RequestListService requestListService;
    @Resource(name = "consultMemberService")
    private ConsultMemberService consultMemberService;

    //주소api key
    @Value("${zipcode.confmKey}") private String zipcodeConfmKey;

    /**
     * 상담신청내역 - 지점/상담팀
     * @param paramVO
     * @return ModelAndView : requestList.jsp 뷰
     * @throws Exception
     */
	@PostMapping(value = "/requestList")
    public ModelAndView requestList(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
        List<ConsultInfoVO> resultList = new ArrayList<>();
        ConsultInfoVO statsCnt = new ConsultInfoVO();
        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        //현 유저세션 (혺은 Spring security context)의 MEMBER_AUTHORITY 에 따라 각기 다른 처리현황카운트,처리현황리스트를 호출한다
        HttpSession session = request.getSession();
//        String memberAuthority = (String) session.getAttribute("MEMBER_AUTHORITY");
        String memberAuthority = consultDetails.getMember_authority();

		//세션 체크하고 consult 의 접근 가능한 URI 와 코드값 과 해당 페이지와 매칭 되는지 확인
		boolean isUserAllowed = UtilSession.checkPageAuthInSession(request);
		if (!isUserAllowed) {
			String errorMessage = messageSource.getMessage("error.server.403", null, localeResolver.resolveLocale(request));

			mav.setViewName("common/error/403error");
			mav.addObject("errorMessage", errorMessage);

			return mav;
		}
			
		//search param 에서 사용될 처리현황 가져오기
		MasterCodeVO codeVO = new MasterCodeVO();
		codeVO.setGROUP_CODE("MC0000000011");
		List<MasterCodeVO> statuses = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//지점(상담사)인경우
		if("MC0002300001".equals(memberAuthority)) {
			//remove 된 배열은 << 자동 left shift 된다
			statuses.remove(1); //진행중(대기)
			statuses.remove(2); //부재/보류
			
			mav = new ModelAndView("consult/consult/requestBranchList");
			//현재 로그인한 유저의 지점에 해당하는 신청내역
			
		//심사팀	
		}else if("MC0002300002".equals(memberAuthority)) {
			
			mav = new ModelAndView("consult/consult/requestEvalList");
			//현재 로그인한 유저의 지점에 해당하는 신청내역
		}
		
		mav.addObject("statuses", statuses);

		paramVO.setBRANCH_CODE( consultDetails.getBranch_code() );
		
		paramVO.setMemberAuthority(memberAuthority);

		
		// 페이징 처리
		UtilCommon.setPageRow(paramVO);
		
		//권한 셋팅
		resultList = requestListService.consultInfoList(paramVO);
		
		if(0 < resultList.size()) {
			// 접수, 진행중, 완료 상태를 나타내기 위해 보내준다
			statsCnt = requestListService.consultStatsCount(paramVO);
			
			int totCnt = Integer.parseInt(statsCnt.getTOTAL_CNT());
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getPageSize(), paramVO.getBlockSize(), totCnt);
		
			mav.addObject("paging", pv.print());
		}

		mav.addObject("resultList", resultList);
		mav.addObject("statsCnt", statsCnt);
		// 페이징을 위해 paramVO 담아 보내준다
		mav.addObject("paramVO", paramVO);
		
        return mav;
    }

	/**
	 * 상담신청내역 - 지점/상담팀
	 * @param paramVO
	 * @return ModelAndView : requestList.jsp 뷰
	 * @throws Exception
	 */
	@PostMapping(value = "/requestSearch")
	public ModelAndView requestSearch(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		List<ConsultInfoVO> resultList = new ArrayList<>();
		CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		//현 유저세션 (혺은 Spring security context)의 MEMBER_AUTHORITY 에 따라 각기 다른 처리현황카운트,처리현황리스트를 호출한다
		HttpSession session = request.getSession();
//        String memberAuthority = (String) session.getAttribute("MEMBER_AUTHORITY");
		String memberAuthority = consultDetails.getMember_authority();
		ConsultInfoVO statsCnt = new ConsultInfoVO();
		
		
		//지점(상담사)인경우
		if("MC0002300001".equals(memberAuthority)) {
			mav = new ModelAndView("consult/consult/innerJsp/innerBranchList");
			paramVO.setBRANCH_CODE( consultDetails.getBranch_code() );
			
			//심사팀	
		}else if("MC0002300002".equals(memberAuthority)) {
			mav = new ModelAndView("consult/consult/innerJsp/innerEvalList");
			paramVO.setBRANCH_CODE("");
		}
		
		//mav.setViewName("consult/consult/requestList");
		
		// 페이징 처리
		UtilCommon.setPageRow(paramVO);
		
		//권한 셋팅
		paramVO.setMemberAuthority(memberAuthority);
		
		
		resultList = requestListService.consultInfoList(paramVO);
		
		 
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		
		return mav;
	}
	
	/**
	 * 상담신청내역 - 지점/상담팀
	 * @param paramVO
	 * @return ModelAndView : requestList.jsp 뷰
	 * @throws Exception
	 */
	@PostMapping(value = "/requestCountPage")
	public ModelAndView requestCountPage(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView("jsonView");
		List<ConsultInfoVO> resultList = new ArrayList<>();
		CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		//현 유저세션 (혺은 Spring security context)의 MEMBER_AUTHORITY 에 따라 각기 다른 처리현황카운트,처리현황리스트를 호출한다
		HttpSession session = request.getSession();
//        String memberAuthority = (String) session.getAttribute("MEMBER_AUTHORITY");
		String memberAuthority = consultDetails.getMember_authority();
		ConsultInfoVO statsCnt = new ConsultInfoVO();
		
		// 페이징 처리
		UtilCommon.setPageRow(paramVO);
		
		//권한 셋팅
		paramVO.setMemberAuthority(memberAuthority);
		
		//지점(상담사)인경우
		if("MC0002300001".equals(memberAuthority)) {	
			//현재 로그인한 유저의 지점에 해당하는 신청내역
			paramVO.setBRANCH_CODE( consultDetails.getBranch_code() );
			
			//심사팀인경우 등록폼에서 취소하여 넘어올때 불규칙 변수를 포함함.	
		}else if("MC0002300002".equals(memberAuthority)) {	
			//현재 로그인한 유저의 지점에 해당하는 신청내역
			paramVO.setBRANCH_CODE("");
		}
		
		resultList = requestListService.consultInfoList(paramVO);
		
		
		if(0 < resultList.size()) { // 접수, 진행중, 완료 상태를 나타내기 위해 보내준다 
			
			statsCnt =  requestListService.consultStatsCount(paramVO);
			
			int totCnt = Integer.parseInt(statsCnt.getTOTAL_CNT()); PagingView pv = new
					PagingView(paramVO.getPageNum(), paramVO.getPageSize(),
							paramVO.getBlockSize(), totCnt);
			
			mav.addObject("paging", pv.print()); 
			
		}else {
			
			statsCnt.setTOTAL_CNT("0");
			statsCnt.setSUBMIT_CNT("0");
			statsCnt.setSTANDBY_CNT("0");
			statsCnt.setPROGRESS_CNT("0");
			statsCnt.setDELAY_CNT("0");
			statsCnt.setCOMPLETE_CNT("0");
			statsCnt.setCANCEL_CNT("0");
		}
		
		mav.addObject("statsCnt", statsCnt);
		mav.addObject("resultList", resultList);
		mav.addObject("paramVO", paramVO);
		
		return mav;
	}
	

	/**
	 * 상담신청내역 등록 폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/requestInsertForm")
	public ModelAndView requestInsertForm(@ModelAttribute ConsultInfoVO paramVO) throws Exception {
		ModelAndView mav = new ModelAndView();
	
		CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		String memberAuthority = consultDetails.getMember_authority();
		
		//권한 셋팅
		paramVO.setMemberAuthority(memberAuthority);
		
		//지점(상담사)인경우
		if("MC0002300001".equals(memberAuthority)) {	
			mav = new ModelAndView("consult/consult/requestBranchInsertForm");

		//심사팀인경우 등록폼에서 취소하여 넘어올때 불규칙 변수를 포함함.	
		}else if("MC0002300002".equals(memberAuthority)) {	
			//현재 로그인한 유저의 지점에 해당하는 신청내역
			mav = new ModelAndView("consult/consult/requestEvalInsertForm");
			
		}
				
		//지점코드
		mav.addObject("BRANCH_CODE", consultDetails.getBranch_code());
		//지점명
		mav.addObject("BRANCH_NAME", consultDetails.getBranch_name());
		//멤버번호
		mav.addObject("MEMBER_CODE", consultDetails.getMember_code());
		//멤버이름
		mav.addObject("MEMBER_NAME", consultDetails.getMember_name());


		Date date = new Date();
		SimpleDateFormat todate  = new SimpleDateFormat("yyyy-M-dd");
		mav.addObject("TODAY", todate.format(date));	//접수일 표기용
		
		//search param 에서 사용될 처리현황 가져오기
		MasterCodeVO codeVO = new MasterCodeVO();
		
		//지점코드
		//codeVO.setGROUP_CODE("MC0000000009");
		//List<MasterCodeVO> branch_code = commonService.detailMasterCodeByGroupCode(codeVO);

		//유입경로1
		codeVO.setGROUP_CODE("MC0000000016");
		List<MasterCodeVO> inflow_route1 = commonService.detailMasterCodeByGroupCode(codeVO);
		inflow_route1.remove(inflow_route1.size()-1);	//고객등록(홈페이지)MC0001600007
		
		//유입경로2
		codeVO.setGROUP_CODE("MC0000000022");
		List<MasterCodeVO> inflow_route2 = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//상품구분명
		codeVO.setGROUP_CODE("MC0000000018");
		List<MasterCodeVO> product_type = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//업종구분
		codeVO.setGROUP_CODE("MC0000000008");
		List<MasterCodeVO> type_code = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//사업자등록 (고객타입)
		codeVO.setGROUP_CODE("MC0000000015");
		List<MasterCodeVO> cust_type = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//취약계층구분
		codeVO.setGROUP_CODE("MC0000000020");
		List<MasterCodeVO> vuln_class = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//직업구분
		codeVO.setGROUP_CODE("MC0000000019");
		List<MasterCodeVO> job_type = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//처리현황코드
		codeVO.setGROUP_CODE("MC0000000010");
		List<MasterCodeVO> stats_code = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//진행상태코드
		codeVO.setGROUP_CODE("MC0000000011");
		List<MasterCodeVO> result_code = commonService.detailMasterCodeByGroupCode(codeVO);
		
		
		//상담결과 사유코드
		codeVO.setGROUP_CODE("MC0000000012");
		List<MasterCodeVO> reason_code = commonService.detailMasterCodeByGroupCode(codeVO);
		
		//공통코드
		mav.addObject("INFLOW_ROUTE1", inflow_route1);
		mav.addObject("INFLOW_ROUTE2", inflow_route2);
		mav.addObject("PRODUCT_TYPE", product_type);
		mav.addObject("TYPE_CODE", type_code);
		mav.addObject("CUST_TYPE", cust_type);
		mav.addObject("VULN_CLASS", vuln_class);
		mav.addObject("JOB_TYPE", job_type);
		mav.addObject("STATS_CODE", stats_code);
		mav.addObject("RESULT_CODE", result_code);
		mav.addObject("REASON_CODE", reason_code);
		
		mav.addObject("zipcodeConfmKey", zipcodeConfmKey);	
		
		return mav;
	}
	
	/**
	 * 상담신청내역 등록
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/requestInsert")
	public ModelAndView requestInsert(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		
		resultJson = requestListService.requestInsert(paramVO, request);
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	/**
	 * 상담신청내역 상세
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/requestDetail")
	public ModelAndView requestDetail(@ModelAttribute ConsultInfoVO paramVO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

		String memberAuthority = consultDetails.getMember_authority();
        
		paramVO.setMemberAuthority(memberAuthority);
		
		//지점(상담사)인경우
		if("MC0002300001".equals(memberAuthority)) {
			mav = new ModelAndView("consult/consult/requestBranchDetail");
		//심사팀	
		}else if("MC0002300002".equals(memberAuthority)) {
			mav = new ModelAndView("consult/consult/requestEvalDetail");
		}
		
		ConsultInfoVO result = new ConsultInfoVO();
		result = requestListService.consultInfoDetail(paramVO);
		
		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);
		return mav;
	}
	
	/**
	 * 상담신청내역 수정 폼
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/requestUpdateForm")
	public ModelAndView requestUpdateForm(@ModelAttribute ConsultInfoVO paramVO) throws Exception{
		
		ModelAndView mav = new ModelAndView();
		
		CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

		String memberAuthority = consultDetails.getMember_authority();
        
		paramVO.setMemberAuthority(memberAuthority);
		
		//지점(상담사)인경우
		if("MC0002300001".equals(memberAuthority)) {
			mav = new ModelAndView("consult/consult/requestBranchUpdateForm");
		//심사팀	
		}else if("MC0002300002".equals(memberAuthority)) {
			mav = new ModelAndView("consult/consult/requestEvalUpdateForm");
		}
		
		//멤버번호
		mav.addObject("MEMBER_CODE", consultDetails.getMember_code());
		//멤버이름
		mav.addObject("MEMBER_NAME", consultDetails.getMember_name());
		//지점코드
		mav.addObject("BRANCH_CODE", consultDetails.getBranch_code());
		//지점명
		mav.addObject("BRANCH_NAME", consultDetails.getBranch_name());
		
		//선택란에서 사용할 MasterCodeVO 마스터코드, 값 가져오기
		MasterCodeVO codeVO = new MasterCodeVO();

		//지점코드
		//codeVO.setGROUP_CODE("MC0000000009");
		//List<MasterCodeVO> branch_code = commonService.detailMasterCodeByGroupCode(codeVO);

		//지점담당자
		ConsultMemberVO consultantVO = new ConsultMemberVO();
		consultantVO.setMEMBER_AUTHORITY("MC0002300001");
		List<ConsultMemberVO> branch_mb_code = consultMemberService.consultMemberDetailByAuthority(consultantVO);

		//상담자 (심사팀)
		consultantVO = new ConsultMemberVO();
		consultantVO.setMEMBER_AUTHORITY("MC0002300002");
		List<ConsultMemberVO> cons_mb_code = consultMemberService.consultMemberDetailByAuthority(consultantVO);

		//유입경로 1
		codeVO.setGROUP_CODE("MC0000000016");
		List<MasterCodeVO> inflow_route1 = commonService.detailMasterCodeByGroupCode(codeVO);
		inflow_route1.remove(inflow_route1.size()-1);	//고객등록(홈페이지)MC0001600007
		
		//유입경로 2
		codeVO.setGROUP_CODE("MC0000000022");
		List<MasterCodeVO> inflow_route2 = commonService.detailMasterCodeByGroupCode(codeVO);

		//상품구분명
		codeVO.setGROUP_CODE("MC0000000018");
		List<MasterCodeVO> product_type = commonService.detailMasterCodeByGroupCode(codeVO);

		//업종구분
		codeVO.setGROUP_CODE("MC0000000008");
		List<MasterCodeVO> type_code = commonService.detailMasterCodeByGroupCode(codeVO);

		//사업자등록 (고객타입)
		codeVO.setGROUP_CODE("MC0000000015");
		List<MasterCodeVO> cust_type = commonService.detailMasterCodeByGroupCode(codeVO);

		//취약계층구분
		codeVO.setGROUP_CODE("MC0000000020");
		List<MasterCodeVO> vuln_class = commonService.detailMasterCodeByGroupCode(codeVO);

		//직업구분
		codeVO.setGROUP_CODE("MC0000000019");
		List<MasterCodeVO> job_type = commonService.detailMasterCodeByGroupCode(codeVO);

		//처리현황코드
		codeVO.setGROUP_CODE("MC0000000011");
		List<MasterCodeVO> stats_code = commonService.detailMasterCodeByGroupCode(codeVO);

		//지점결과코드
		codeVO.setGROUP_CODE("MC0000000010");
		List<MasterCodeVO> branch_result_code = commonService.detailMasterCodeByGroupCode(codeVO);
		branch_result_code.remove(0); //접수 제거
		
		//심사결과코드
		codeVO.setGROUP_CODE("MC0000000021");
		List<MasterCodeVO> eval_result_code = commonService.detailMasterCodeByGroupCode(codeVO);
		eval_result_code.remove(0);	//접수 제거
		
		//거절사유코드
		codeVO.setGROUP_CODE("MC0000000012");
		List<MasterCodeVO> decline_reason_code = commonService.detailMasterCodeByGroupCode(codeVO);

		//취소사유코드
		codeVO.setGROUP_CODE("MC0000000017");
		List<MasterCodeVO> cancel_reason_code = commonService.detailMasterCodeByGroupCode(codeVO);
		
		ConsultInfoVO result = new ConsultInfoVO();
		result = requestListService.consultInfoDetail(paramVO);
		
		//실명번호 나누기
		String sBirthDate		= result.getCUST_REGI_NO().replace(".", "").substring(0, 6);
		String sGender			= result.getCUST_REGI_NO().substring(6, 7);
		
		//MasterCodeVO 마스터코드,값
		mav.addObject("sBirthDate", sBirthDate);
		mav.addObject("sGender", sGender);
		mav.addObject("BRANCH_MB_CODE", branch_mb_code);
		mav.addObject("CONS_MB_CODE", cons_mb_code);
		mav.addObject("INFLOW_ROUTE1", inflow_route1);
		mav.addObject("INFLOW_ROUTE2", inflow_route2);
		mav.addObject("PRODUCT_TYPE", product_type);
		mav.addObject("TYPE_CODE", type_code);
		mav.addObject("CUST_TYPE", cust_type);
		mav.addObject("VULN_CLASS", vuln_class);
		mav.addObject("JOB_TYPE", job_type);
		mav.addObject("STATS_CODE", stats_code);
		mav.addObject("BRANCH_RESULT_CODE", branch_result_code);
		mav.addObject("EVAL_RESULT_CODE", eval_result_code);
		mav.addObject("DECLINE_REASON_CODE", decline_reason_code);
		mav.addObject("CANCEL_REASON_CODE", cancel_reason_code);
		
		mav.addObject("zipcodeConfmKey", zipcodeConfmKey);	

		mav.addObject("paramVO", paramVO);
		mav.addObject("result", result);

		return mav;
	}

	/**
	 * 상담신청내역 수정
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/requestUpdate")
	public ModelAndView requestUpdate(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();
		
		paramVO.setLoginCode(adminDetail.getMember_code());
		
		resultJson = requestListService.requestUpdate(paramVO, request);
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}
	
	
	/**
	 * 상담신청내역 삭제
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/requestDelete")
	public ModelAndView requestDelete(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();
		
		paramVO.setLoginCode(adminDetail.getMember_code());
		
		resultJson = requestListService.requestDelete(paramVO, request);
		
		logger.info(resultJson.toJSONString());
		
		mav.addObject("resultJson", resultJson);
		
		return mav;
	}


	/**
	 * 신청조회리스트(requestList.jsp)에서 심사팀(상담자) 배정 기능
	 * 호출 시 바로 업데이트 쿼리를 보낸다
	 * @param paramVO, request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/assignConsultantOnList")
	public ModelAndView assignConsultantOnList(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject resultJson = new JSONObject();

		//체크된 신청내역 CONS_SEQ 들이 담겨있는 paramVO 를 통해 업데이트 쿼리
		resultJson = requestListService.assignConsultantOnList(paramVO, request);

		mav.addObject("resultJson", resultJson);

		return mav;
	}

	/**
	 * 신청조회수정(requestUpdateForm.jsp)에서 심사팀(상담자)이름, 상담일 (Java에서 생성한 현재기준 Date) 을 단순히 화면에 출력해주는 용도이다.  실제 업데이트 쿼리는 이루어지지 않는다는 점 유의.
	 * @param paramVO
	 * @return JSONObject
	 * @throws Exception
	 */
	@PostMapping("/assignConsultantOnDetail")
	public ModelAndView assignConsultantOnDetail(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		JSONObject resultJson = new JSONObject();

		//체크된 신청내역 CONS_SEQ가 담겨있는 paramVO 를 통해 업데이트 쿼리
		resultJson = requestListService.assignConsultantOnDetail(paramVO, request);

		mav.addObject("resultJson", resultJson);

		return mav;
	}


}