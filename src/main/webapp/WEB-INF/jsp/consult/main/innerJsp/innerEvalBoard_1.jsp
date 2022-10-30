<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>

						<ul>
							<li>
								<div class="count w_100">
									<em>접수</em>
									<p id="chSubmitUser" class="c_blue">${boardResult.SUBMIT_CNT}<span>건</span></p>
								</div>
							</li>
							<li>
								<div class="count w_100">
									<em>진행 중(대기)</em>
									<p id="chPendingCnt" class="c_blue">${boardResult.PENDING_CNT}<span>건</span></p>
								</div>
							</li>
							<li>
								<div class="count w_100">
									<em>진행 중</em>
									<p id="chProgressCnt" class="c_blue">${boardResult.PROGRESS_CNT}<span>건</span></p>
								</div>
							</li>
							<li>
								<div class="count w_100">
									<em>부재/보류</em>
									<p id="chAbsenceCnt" class="c_blue">${boardResult.ABSENCE_CNT}<span>건</span></p>
								</div>
							</li>
							<li>
								<div class="count w_100">
									<em>완료</em>
									<p id="chCompleteUser" class="c_blue">${boardResult.COMPLETE_CNT}<span>건</span></p>
								</div>
							</li>
							<li>
								<div class="count w_100">
									<em>취소</em>
									<p id="chCancelUser" class="c_blue">${boardResult.CANCEL_CNT}<span>건</span></p>
								</div>
							</li>
						</ul>
							
	<script>
	
	$("#chTotalUser").text("${boardResult.TOTAL_CNT}");

	</script>