<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>

							<ul class="board_list">
								<li>
									<div class="count">
										<em>전체</em>
										<p id="chTotalUser" class="c_blue">${boardResult.TOTAL_CNT}<span>건</span></p>
									</div>
									<div class="line_chart">
										<p>진행 중+완료+취소</p>
										<div id="progressbar" class="data01"></div>
									</div>
								</li>
								<li>
									<div class="count">
										<em>접수</em>
										<p id="chSubmitUser">${boardResult.SUBMIT_CNT}<span>건</span></p>
									</div>
									<div class="line_chart">
										<p id="chSubmitPerUser" class="per"><span>%</span></p>
										<div id="progressbar" class="data02"></div>
									</div>
								</li>
								<li>
									<div class="count">
										<em>진행 중</em>
										<p id="chProgressUser">${boardResult.SUM_PROGRESS_CNT}<span>건</span></p>
									</div>
									<div class="line_chart">
										<p id="chProgressPerUser" class="per"><span>%</span></p>
										<div id="progressbar" class="data03"></div>
									</div>
								</li>
								<li>
									<div class="count">
										<em>완료</em>
										<p id="chCompleteUser">${boardResult.COMPLETE_CNT}<span>건</span></p>
									</div>
									<div class="line_chart">
										<p id="chCompletePerUser" class="per"><span>%</span></p>
										<div id="progressbar" class="data04"></div>
									</div>
								</li>
								<li>
									<div class="count">
										<em>취소</em>
										<p id="chCancelUser">${boardResult.CANCEL_CNT}<span>건</span></p>
									</div>
									<div class="line_chart">
										<p id="chCancelPerUser" class="per"><span>%</span></p>
										<div id="progressbar" class="data05"></div>
									</div>
								</li>
							</ul>
							
							<script>
							$("#userDate").text("${currMonth}");

							var BOARD_TOTAL_CNT = "${boardResult.TOTAL_CNT}" || 0;
							var BOARD_SUBMIT_CNT = "${boardResult.SUBMIT_CNT}" || 0;
							var BOARD_PROGRESS_CNT = "${boardResult.SUM_PROGRESS_CNT}" || 0;
							var BOARD_COMPLETE_CNT = "${boardResult.COMPLETE_CNT}" || 0;
							var BOARD_CANCEL_CNT = "${boardResult.CANCEL_CNT}" || 0;

							var BOARD_SUBMIT_PER = ( (BOARD_SUBMIT_CNT / BOARD_TOTAL_CNT) * 100 )  || 0;
							var BOARD_PROGRESS_PER = ( (BOARD_PROGRESS_CNT / BOARD_TOTAL_CNT) * 100 ) || 0;
							var BOARD_COMPLETE_PER = ( (BOARD_COMPLETE_CNT / BOARD_TOTAL_CNT) * 100 ) || 0;
							var BOARD_CANCEL_PER = ( (BOARD_CANCEL_CNT / BOARD_TOTAL_CNT) * 100) || 0; 
							
							//나의 상담 (건수, 퍼센트)
							$('#chTotalUser').html("<span>" + BOARD_TOTAL_CNT + "건</span>");
							$('#chSubmitUser').html("<span>" + BOARD_SUBMIT_CNT + "건</span>");
							$('#chSubmitPerUser').html("<span>" + Math.round( BOARD_SUBMIT_PER ) + "%</span>");
							$('#chProgressUser').html("<span>" + BOARD_PROGRESS_CNT + "건</span>");
							$('#chProgressPerUser').html("<span>" + Math.round( BOARD_PROGRESS_PER ) + "%</span>");
							$('#chCompleteUser').html("<span>" + BOARD_COMPLETE_CNT + "건</span>");
							$('#chCompletePerUser').html("<span>" + Math.round( BOARD_COMPLETE_PER ) + "%</span>");
							$('#chCancelUser').html("<span>" + BOARD_CANCEL_CNT + "건</span>");
							$('#chCancelPerUser').html("<span>" + Math.round( BOARD_CANCEL_PER ) + "%</span>");

							//나의 상담 (progress bar)
							$('.data01').progressbar({
								value: progBarTotVal( BOARD_SUBMIT_PER, BOARD_PROGRESS_PER, BOARD_COMPLETE_PER, BOARD_CANCEL_PER),
							})
							$('.data02').progressbar({
								value: BOARD_SUBMIT_PER,
							})
							$('.data03').progressbar({
								value: BOARD_PROGRESS_PER,
							})
							$('.data04').progressbar({
								value: BOARD_COMPLETE_PER,
							})
							$('.data05').progressbar({
								value: BOARD_CANCEL_PER,
							})
							
							
								//차트 데이터에 들어갈 변수들 (유동적으로 일,월,년에 따라 바꾸어주기 위함)
	function progBarTotVal(submit_per, progress_per, complete_per, cancel_per) {
		if( isEmpty(submit_per) &&  isEmpty(progress_per) &&  isEmpty(complete_per) &&	isEmpty(cancel_per)) {
			return 0;
		} else {
			return 100;
		}
	}
							</script>