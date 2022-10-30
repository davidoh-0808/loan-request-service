package com.neo.consult.consult.controller;

import com.neo.common.vo.ConsultInfoExportVO;
import com.neo.consult.consult.service.ConsultInfoService;
import com.neo.consult.member.service.ConsultMemberService;
import com.neo.common.service.CommonService;
import com.neo.common.view.PagingView;
import com.neo.common.vo.ConsultInfoVO;
import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.MasterCodeVO;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilCommon;
import com.neo.util.UtilSession;
import com.neo.util.UtilExcel;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    상담리스트
    <li id="consultList"><a href="/admin/consult/consultList" data-auth="CONSULT_LIST">- 상담리스트</a>
    </li>
 */


/*
 * 상담신청내역, 상담리스트 공통 사용
 */

@RequestMapping("/consult/consult")
@Controller
public class ConsultListController {

	private static final Logger logger = LoggerFactory.getLogger(RequestListController.class);

	@Autowired
	LocaleResolver localeResolver;
	@Autowired
	MessageSource messageSource;
	@Resource(name = "consultInfoService")
	private ConsultInfoService consultInfoService;
	@Resource(name = "commonService")
	private CommonService commonService;
	@Resource(name = "consultMemberService")
	private ConsultMemberService consultMemberService;

	//엑셀 출력을 위한 필드 선언
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;


	/**
	 * 상담리스트 조회
	 *
	 * @param paramVO
	 * @return ModelAndView : consultList.jsp 뷰
	 * @throws Exception
	 */
	/* @RequestMapping(value = "/consultList") */
	@PostMapping(value = "/consultList")
	public ModelAndView consultList(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		List<ConsultInfoVO> resultList = new ArrayList<>();
		ConsultInfoVO statsCnt = new ConsultInfoVO();

		//세션 체크하고 admin 의 접근 가능한 URI 와 코드값 과 해당 페이지와 매칭 되는지 확인
		boolean isUserAllowed = UtilSession.checkPageAuthInSession(request);
		if (!isUserAllowed) {
			String errorMessage = messageSource.getMessage("error.server.403", null, localeResolver.resolveLocale(request));

			mav.setViewName("common/error/403error");
			mav.addObject("errorMessage", errorMessage);

			return mav;
		} else {
			mav.setViewName("consult/consult/consultList");
		}

		// 페이징 처리
		UtilCommon.setPageRow(paramVO);

		resultList = consultInfoService.consultInfoList(paramVO);
		
		if(0 < resultList.size()) {
			// 접수, 진행중, 완료 상태를 나타내기 위해 보내준다
			statsCnt = consultInfoService.consultStatsCount(paramVO);
			
			int totCnt = Integer.parseInt(statsCnt.getTOTAL_CNT());
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getPageSize(), paramVO.getBlockSize(), totCnt);
		
			mav.addObject("paging", pv.print());
		}

		//search param 에서 사용될 처리현황 가져오기
		MasterCodeVO codeVO = new MasterCodeVO();
		codeVO.setGROUP_CODE("MC0000000011");
		List<MasterCodeVO> stats_code = commonService.detailMasterCodeByGroupCode(codeVO);

		mav.addObject("orderValue", paramVO.getOrderValue());	// 컬럼필터링 ASC, DESC 정보
		mav.addObject("orderName", paramVO.getOrderName());		// 컬럼필터링 ASC, DESC 정보
		mav.addObject("resultList", resultList);
		mav.addObject("STATS_CODE", stats_code);
		mav.addObject("statsCnt", statsCnt);
		mav.addObject("paramVO", paramVO);

		return mav;
	}

	/**
	 * 상담신청내역 - 지점/상담팀
	 * @param paramVO
	 * @return ModelAndView : requestList.jsp 뷰
	 * @throws Exception
	 */
	@PostMapping(value = "/consultSearch")
	public ModelAndView consultSearch(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		List<ConsultInfoVO> resultList = new ArrayList<>();
		ConsultInfoVO statsCnt = new ConsultInfoVO();
		CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		//현 유저세션 (혺은 Spring security context)의 MEMBER_AUTHORITY 에 따라 각기 다른 처리현황카운트,처리현황리스트를 호출한다
		HttpSession session = request.getSession();
//        String memberAuthority = (String) session.getAttribute("MEMBER_AUTHORITY");
		String memberAuthority = consultDetails.getMember_authority();
		
		mav = new ModelAndView("consult/consult/innerJsp/innerConsultList");
		
		// 페이징 처리
		UtilCommon.setPageRow(paramVO);

		//권한 셋팅
		paramVO.setMemberAuthority(memberAuthority);
				
		resultList = consultInfoService.consultInfoList(paramVO);
		
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
	@PostMapping(value = "/consultCountPage")
	public ModelAndView consultCountPage(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		
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
				
		resultList = consultInfoService.consultInfoList(paramVO);
		
		if(0 < resultList.size()) {
			// 접수, 진행중, 완료 상태를 나타내기 위해 보내준다
			statsCnt = consultInfoService.consultStatsCount(paramVO);

			int totCnt = Integer.parseInt(statsCnt.getTOTAL_CNT());
			PagingView pv = new PagingView(paramVO.getPageNum(), paramVO.getPageSize(), paramVO.getBlockSize(), totCnt);
			
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
		
		mav.addObject("resultList", resultList);
		mav.addObject("statsCnt", statsCnt);
		mav.addObject("paramVO", paramVO);
		
		return mav;
	}
	
	/**
	 * 상담리스트 상세
	 *
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/consultDetail")
	public ModelAndView consultListDetail(@ModelAttribute ConsultInfoVO paramVO) throws Exception {
		ModelAndView mav = new ModelAndView("consult/consult/consultDetail");

		ConsultInfoVO result = new ConsultInfoVO();
		result = consultInfoService.consultInfoDetail(paramVO);
		
		mav.addObject("result", result);
		mav.addObject("paramVO", paramVO);

		return mav;
	}

	/**
	 * 상담리스트 등록 폼
	 *
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/consultInsertForm")
	public ModelAndView consultListInsertForm(@ModelAttribute ConsultInfoVO paramVO) throws Exception {
		ModelAndView mav = new ModelAndView("consult/consult/consultInsertForm");

		return mav;
	}

	/**
	 * 상담리스트 등록
	 *
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/consultInsert")
	public ModelAndView consultListInsert(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");

		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

		JSONObject resultJson = new JSONObject();
		paramVO.setLoginCode(adminDetail.getMember_code());
		resultJson = consultInfoService.consultInfoInsert(paramVO);
		mav.addObject("resultJson", resultJson);

		return mav;
	}

	/**
	 * 상담리스트 수정 폼
	 *
	 * @param paramVO
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/consultUpdateForm")
	public ModelAndView consultListUpdateForm(@ModelAttribute ConsultInfoVO paramVO) throws Exception {
		ModelAndView mav = new ModelAndView("consult/consult/consultUpdateForm");
		ConsultInfoVO result = new ConsultInfoVO();


		//상담신청VO 에 담긴 키 값을 이용하여 디테일을 불러와 화면에 보여주기 위함
		result = consultInfoService.consultInfoDetail(paramVO);

		//화면에 출력해줄 지점, 업종, 유입경로 등의 정보를 가져오기 위해 각 GroupCode 에 해당한 MasterCode 들을 가져오기
		//지점
		MasterCodeVO branchParamVO = new MasterCodeVO();
		branchParamVO.setGROUP_CODE("MC0000000009");
		List<MasterCodeVO> branch_code = commonService.detailMasterCodeByGroupCode(branchParamVO);

		//지점담당자
		ConsultMemberVO consultantVO = new ConsultMemberVO();
		consultantVO.setMEMBER_AUTHORITY("MC0002300001");
		List<ConsultMemberVO> branch_mb_code = consultMemberService.consultMemberDetailByAuthority(consultantVO);

		//상담자(심사팀)
		consultantVO = new ConsultMemberVO();
		consultantVO.setMEMBER_AUTHORITY("MC0002300002");
		List<ConsultMemberVO> cons_mb_code = consultMemberService.consultMemberDetailByAuthority(consultantVO);

		//상품명
		MasterCodeVO productTypeParamVO = new MasterCodeVO();
		productTypeParamVO.setGROUP_CODE("MC0000000018");
		List<MasterCodeVO> product_type = commonService.detailMasterCodeByGroupCode(productTypeParamVO);

		//업종
		MasterCodeVO typeCodeParamVO = new MasterCodeVO();
		typeCodeParamVO.setGROUP_CODE("MC0000000008");
		List<MasterCodeVO> type_code = commonService.detailMasterCodeByGroupCode(typeCodeParamVO);

		//고객유형
		MasterCodeVO custTypeParamVO = new MasterCodeVO();
		custTypeParamVO.setGROUP_CODE("MC0000000015");
		List<MasterCodeVO> cust_type = commonService.detailMasterCodeByGroupCode(custTypeParamVO);

		//유입경로 1 (MC0000000022)
		MasterCodeVO inFlowRouteParamVO = new MasterCodeVO();
		inFlowRouteParamVO.setGROUP_CODE("MC0000000022");
		List<MasterCodeVO> inflow_route1 = commonService.detailMasterCodeByGroupCode(inFlowRouteParamVO);
		inflow_route1.remove(inflow_route1.size()-1);	//고객등록(홈페이지)MC0001600007
		
		//유입경로 2 (MC0000000016)
		inFlowRouteParamVO = new MasterCodeVO();
		inFlowRouteParamVO.setGROUP_CODE("MC0000000016");
		List<MasterCodeVO> inflow_route2 = commonService.detailMasterCodeByGroupCode(inFlowRouteParamVO);

		//처리현황
		MasterCodeVO StatsCodeParamVO = new MasterCodeVO();
		StatsCodeParamVO.setGROUP_CODE("MC0000000011");
		List<MasterCodeVO> stats_code = commonService.detailMasterCodeByGroupCode(StatsCodeParamVO);

		//상담결과
		MasterCodeVO resultCodeParamVO = new MasterCodeVO();
		resultCodeParamVO.setGROUP_CODE("MC0000000021");
		List<MasterCodeVO> eval_result_code = commonService.detailMasterCodeByGroupCode(resultCodeParamVO);
		eval_result_code.remove(0);	//접수 제거
		
		//거절사유
		MasterCodeVO reasonCodeParamVO = new MasterCodeVO();
		reasonCodeParamVO.setGROUP_CODE("MC0000000012");
		List<MasterCodeVO> decline_reason_code = commonService.detailMasterCodeByGroupCode(reasonCodeParamVO);


		//실명번호 나누기
		String sBirthDate		= result.getCUST_REGI_NO().replace(".", "").substring(0, 6);
		String sGender			= result.getCUST_REGI_NO().replace(".", "").substring(6, 7);


		mav.addObject("result", result);
		mav.addObject("BRANCH_CODE", branch_code);
		mav.addObject("BRANCH_MB_CODE", branch_mb_code);
		mav.addObject("CONS_MB_CODE", cons_mb_code);
		mav.addObject("PRODUCT_TYPE", product_type);
		mav.addObject("TYPE_CODE", type_code);
		mav.addObject("CUST_TYPE", cust_type);
		mav.addObject("INFLOW_ROUTE1", inflow_route1);
		mav.addObject("INFLOW_ROUTE2", inflow_route2);
		mav.addObject("STATS_CODE", stats_code);
		mav.addObject("EVAL_RESULT_CODE", eval_result_code);
		mav.addObject("DECLINE_REASON_CODE", decline_reason_code);
		mav.addObject("sBirthDate", sBirthDate);
		mav.addObject("sGender", sGender);

		return mav;
	}

	/**
	 * 상담리스트 수정
	 *
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/consultUpdate")
	public ModelAndView consultUpdate(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();

		paramVO.setLoginCode(adminDetail.getMember_code());
		resultJson = consultInfoService.consultInfoUpdate(paramVO, request);

		mav.addObject("resultJson", resultJson);

		return mav;
	}


	/**
	 * 상담리스트 삭제
	 *
	 * @param paramVO
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@PostMapping("/consultDelete")
	public ModelAndView consultListDelete(@ModelAttribute ConsultInfoVO paramVO, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");
		CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		JSONObject resultJson = new JSONObject();

		paramVO.setLoginCode(adminDetail.getMember_code());

		resultJson = consultInfoService.consultInfoDelete(paramVO, request);

		logger.info(resultJson.toJSONString());

		mav.addObject("resultJson", resultJson);

		return mav;
	}


	/**
	 * 상담리스트 엑셀 다운로드
	 *
	 * @param paramVO, request , response
	 * @return mav ( ResponseEntity<byte[]> )
	 * @throws Exception
	 */
	@PostMapping("/consultExport")
	public ResponseEntity<byte[]> consultExport(/*@RequestBody ConsultInfoExportVO paramVO, */ConsultInfoVO paramVO, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("jsonView");

		//설정: 게시글 데이터를 입력할 response 객체의 content type and header info
		//String filePath = System.getProperty("user.home"); // C:\\users\\~유저~\\
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String currTime = LocalDateTime.now().format(formatter).toString();
		String name = "LoanRequests_" + currTime;
		String fileExtension = ".xlsx";
		String filename = name + fileExtension;

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + filename;
		response.setContentType("application/octet-stream");
		response.setHeader(headerKey, headerValue);

		//export service 호출
		//List<ConsultInfoVO> resultList = consultInfoService.(paramVO, boardIdsForExport, response);
		List<ConsultInfoVO> resultList = consultInfoService.consultInfoExportList(paramVO);
		workbook = UtilExcel.writeHeaderRow(resultList, workbook, sheet);


		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);

		return ResponseEntity.ok().body(os.toByteArray());
	}


}