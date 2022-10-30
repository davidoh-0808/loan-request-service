package com.neo.consult.main.controller;

import com.neo.common.vo.ConsultInfoVO;
import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.DashboardVO;
import com.neo.consult.main.service.DashboardServiceImpl;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilCommon;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@RequestMapping(value = "/consult/main")
@Controller(value = "dashboardController")
public class DashboardController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LocaleResolver localeResolver;
    @Autowired
    MessageSource messageSource;
    @Resource
    private DashboardServiceImpl dashboardService;


    /**
     * 대시보드 페이지 호출
     * (유저의 권한에 따라 다른 서비스 메소드를 호출한다)
     *
     * @param
     * @return mav
     * @throws Exception
     */
     @GetMapping("/dashboard")
     public ModelAndView dashboard() throws Exception {
         ModelAndView mav = new ModelAndView();

         DashboardVO boardResult = new DashboardVO();
         DashboardVO chartResult = new DashboardVO();

         List<DashboardVO> resultList = new ArrayList<>();
         
         JSONObject dashBoard = new JSONObject();

         // 일, 월, 년 선택시 다른 날짜 표출하기 위한 용도
         LocalDateTime ldt = LocalDateTime.now();
         String currDate = DateTimeFormatter.ofPattern("yyyy.MM.dd", Locale.KOREAN).format(ldt);
         String currMonth = DateTimeFormatter.ofPattern("yyyy.MM", Locale.KOREAN).format( ldt );
         String currYear = DateTimeFormatter.ofPattern("yyyy", Locale.KOREAN).format( ldt );

         CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

         // 쿼리필터용 정보 셋팅 (지점, 지점유저코드)
         DashboardVO boardVO = new DashboardVO();
         DashboardVO chartVO = new DashboardVO();
         DashboardVO resultVO = new DashboardVO();

         //디폴트 조회기준 :  daily 일, monthly 월, yearly 년
         boardVO.setDateRange("monthly");
         chartVO.setDateRange("monthly");
         resultVO.setDateRange("monthly");
         
         String userAuth = consultDetails.getMember_authority();
         switch( userAuth ) {
            // 지점
            case "MC0002300001":
                mav.setViewName("consult/main/dashboardBranch");
                
                boardVO.setBRANCH_MB_CODE( consultDetails.getMember_code() );
                
                chartVO.setBRANCH_CODE(consultDetails.getBranch_code());
                
                // 대시보드
                boardResult = dashboardService.getDashboard(boardVO);
                
                // 차트
                chartResult = dashboardService.getDashboard(chartVO);

                mav.addObject("boardResult", boardResult);
                mav.addObject("chartResult", chartResult);
                
                break;
            // 심사팀
            case "MC0002300002":
                mav.setViewName("consult/main/dashboardEval");
                
                boardVO.setCONS_MB_CODE(consultDetails.getMember_code());	//심사자코드
                
                // 대시보드 
                boardResult = dashboardService.getDashboard(boardVO);
                
                // 차트
                chartResult = dashboardService.getDashboard(chartVO);
                
                // 차트 LIST
                resultList = dashboardService.getDashboardChart(resultVO);
                
                mav.addObject("boardResult", boardResult);
                mav.addObject("chartResult", chartResult);
                mav.addObject("resultList", resultList);

                break;
            // 심사팀 관리자
            case "MC0002300003":
                mav.setViewName("consult/main/dashboardEvalMg");
                
                boardVO.setCONS_MB_CODE(consultDetails.getMember_code());	//심사자코드
                
                // 사용자 현황
                boardResult = dashboardService.getUserCnt(boardVO);
                
                // 차트
                chartResult = dashboardService.getDashboard(chartVO);
                
                // 차트 LIST
                resultList = dashboardService.getDashboardChart(resultVO);
                
                mav.addObject("boardResult", boardResult);
                mav.addObject("chartResult", chartResult);
                mav.addObject("resultList", resultList);
                
                break;
            // 시스템 관리자
            case "MC0002300004":
                mav.setViewName("consult/main/dashboardSysMg");

                // 사용자 현황
                boardResult = dashboardService.getUserTotalCnt(boardVO);
                
                // 차트
                chartResult = dashboardService.getDashboard(chartVO);
                
                // 차트 LIST
                resultList = dashboardService.getDashboardChart(resultVO);
                
                mav.addObject("boardResult", boardResult);
                mav.addObject("chartResult", chartResult);
                mav.addObject("resultList", resultList);
                
                break;
            default:
                logger.error("DashboardController.dashboard() 스위치 문 에러 발생");

         }

         mav.addObject("dateRange", boardVO.getDateRange());

         mav.addObject("currDate", currDate);
         mav.addObject("currMonth", currMonth);
         mav.addObject("currYear", currYear);
         
         mav.addObject("boardResult", boardResult);
         mav.addObject("chartResult", chartResult);
         mav.addObject("resultList", resultList);
         
         return mav;
     }
     
 	/**
 	 * 대시보드 보드영역
 	 * @param paramVO
 	 * @return ModelAndView : dashboard.jsp 뷰
 	 * @throws Exception
 	 */
 	@PostMapping(value = "/getBoard")
 	public ModelAndView getBoard(@ModelAttribute DashboardVO paramVO, HttpServletRequest request) throws Exception {
 		
 		
 		ModelAndView mav = new ModelAndView();
 		//List<DashboardVO> resultList = new ArrayList<>();
 		
 		CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
 		//현 유저세션 (혺은 Spring security context)의 MEMBER_AUTHORITY 에 따라 각기 다른 처리현황카운트,처리현황리스트를 호출한다
 		HttpSession session = request.getSession();

 		DashboardVO boardResult = new DashboardVO();
 		
		paramVO.setBRANCH_CODE( consultDetails.getBranch_code() );
 			
        // 쿼리필터용 정보 셋팅 (지점, 지점유저코드)
        DashboardVO boardVO = new DashboardVO();

        boardVO.setDateRange(paramVO.getDateRange());
 		
        String userAuth = consultDetails.getMember_authority();
        switch( userAuth ) {
           // 지점
           case "MC0002300001":
        	   mav = new ModelAndView("consult/main/innerJsp/innerBranchBoard");
               
        	   boardVO.setBRANCH_MB_CODE( consultDetails.getMember_code() );
        	   
               break;
               
           // 심사팀
           case "MC0002300002":
        	   mav = new ModelAndView("consult/main/innerJsp/innerEvalBoard");
        	   
               boardVO.setCONS_MB_CODE(consultDetails.getMember_code());	//심사자코드
               
               break;
           // 심사팀 관리자
           case "MC0002300003":
               mav.setViewName("consult/main/dashboardEvalMg");

               
               break;
               // 시스템 관리자
           case "MC0002300004":
               mav.setViewName("consult/main/dashboardSysMg");

               
               break;
           default:
               logger.error("DashboardController.dashboard() 스위치 문 에러 발생");

        }
        
        // 대시보드
        boardResult = dashboardService.getDashboard(boardVO);
        
        mav.addObject("boardResult", boardResult);
 		mav.addObject("paramVO", paramVO);
 		
 		return mav;
 	}
 	
 	/**
 	 * 상담신청내역 - 지점/상담팀
 	 * @param paramVO
 	 * @return ModelAndView : requestList.jsp 뷰
 	 * @throws Exception
 	 */
 	@PostMapping(value = "/getChartList")
 	public ModelAndView getChartList(@ModelAttribute DashboardVO paramVO, HttpServletRequest request) throws Exception {
 		
 		
 		ModelAndView mav = new ModelAndView();
 		
 		List<DashboardVO> resultList = new ArrayList<>();
 		
 		CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
 		//현 유저세션 (혺은 Spring security context)의 MEMBER_AUTHORITY 에 따라 각기 다른 처리현황카운트,처리현황리스트를 호출한다
 		HttpSession session = request.getSession();
 		
 		paramVO.setBRANCH_CODE( consultDetails.getBranch_code() );
 		
 		// 쿼리필터용 정보 셋팅 (지점, 지점유저코드)
 		DashboardVO resultVO = new DashboardVO();
 		
 		//디폴트 조회기준 :  daily 일, monthly 월, yearly 년
 		resultVO.setDateRange(paramVO.getDateRange());
 		
 		String userAuth = consultDetails.getMember_authority();
 		switch( userAuth ) {
 		// 지점
 		case "MC0002300001":
 			mav = new ModelAndView("consult/main/innerJsp/innerBranchChart");
 			
 			//chartVO.setBRANCH_CODE(consultDetails.getBranch_code());
 			
 			// 지점 대시보드 - 전체
 			//chartResult = dashboardService.getDashboard(chartVO);
 			
 			//mav.addObject("chartResult", chartResult);
 			
 			break;
 			// 심사팀
 		case "MC0002300002":
 			mav = new ModelAndView("consult/main/innerJsp/innerEvalChartList");
 			
 			// 차트 LIST
 			resultList = dashboardService.getDashboardChart(resultVO);
 			
 			mav.addObject("resultList", resultList);
 			
 			break;
 			// 심사팀 관리자
 		case "MC0002300003":
 			mav = new ModelAndView("consult/main/innerJsp/innerEvalChartList");
 			
 			// 차트 LIST
 			resultList = dashboardService.getDashboardChart(resultVO);
 			
 			mav.addObject("resultList", resultList);
 			
 			break;
 			// 시스템 관리자
 		case "MC0002300004":
 			mav = new ModelAndView("consult/main/innerJsp/innerEvalChartList");
 			
 			// 차트 LIST
 			resultList = dashboardService.getDashboardChart(resultVO);
 			
 			mav.addObject("resultList", resultList);
 			
 			break;
 		default:
 			logger.error("DashboardController.dashboard() 스위치 문 에러 발생");
 			
 		}
 		
 		
 		mav.addObject("paramVO", paramVO);
 		
 		return mav;
 	}
	/**
	 * 상담신청내역 - 지점/상담팀
	 * @param paramVO
	 * @return ModelAndView : requestList.jsp 뷰
	 * @throws Exception
	 */
	@PostMapping(value = "/getChart")
 	public ModelAndView getChart(@ModelAttribute DashboardVO paramVO, HttpServletRequest request) throws Exception {
 		
 		
 		ModelAndView mav = new ModelAndView();
 		
 		CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
 		//현 유저세션 (혺은 Spring security context)의 MEMBER_AUTHORITY 에 따라 각기 다른 처리현황카운트,처리현황리스트를 호출한다
 		HttpSession session = request.getSession();

 		DashboardVO chartResult = new DashboardVO();
 		
		paramVO.setBRANCH_CODE( consultDetails.getBranch_code() );
 			
        // 쿼리필터용 정보 셋팅 (지점, 지점유저코드)
        DashboardVO chartVO = new DashboardVO();

        //디폴트 조회기준 :  daily 일, monthly 월, yearly 년
        chartVO.setDateRange(paramVO.getDateRange());
 		
        String userAuth = consultDetails.getMember_authority();
        switch( userAuth ) {
           // 지점
           case "MC0002300001":
        	   mav = new ModelAndView("consult/main/innerJsp/innerBranchChart");
               
        	   chartVO.setBRANCH_CODE(consultDetails.getBranch_code());
               
               // 지점 대시보드 - 전체
               chartResult = dashboardService.getDashboard(chartVO);

               mav.addObject("chartResult", chartResult);
               
               break;
           // 심사팀
           case "MC0002300002":
        	   mav = new ModelAndView("consult/main/innerJsp/innerEvalChart");

               // 차트
               chartResult = dashboardService.getDashboard(chartVO);
               
               mav.addObject("chartResult", chartResult);
               
               break;
           // 심사팀 관리자
           case "MC0002300003":
        	   mav = new ModelAndView("consult/main/innerJsp/innerEvalChart");

               // 차트
               chartResult = dashboardService.getDashboard(chartVO);
               
               mav.addObject("chartResult", chartResult);

               break;
           // 시스템 관리자
           case "MC0002300004":
        	   mav = new ModelAndView("consult/main/innerJsp/innerEvalChart");

               // 차트
               chartResult = dashboardService.getDashboard(chartVO);
               
               mav.addObject("chartResult", chartResult);

               
               break;
           default:
               logger.error("DashboardController.dashboard() 스위치 문 에러 발생");

        }
        
 		
 		mav.addObject("paramVO", paramVO);
 		
 		return mav;
 	}
}
