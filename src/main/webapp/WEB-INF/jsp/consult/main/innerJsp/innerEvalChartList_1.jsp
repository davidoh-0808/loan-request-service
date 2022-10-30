<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>

						<section class="dash_area">
							<h4>지점별 상담내역 현황</h4>
							<table id="statsListDaily" class="spot_list">
								<caption>지점별 상담내역 현황</caption>
								<colgroup>
									<col style="width: 90px;">
									<col style="width: 90px;">
									<col style="width: 90px;">
									<col style="width: 90px;">
									<col style="width: 90px;">
									<col style="width: 90px;">
									<col style="width: 90px;">
								</colgroup>
								<thead>
								<tr>
									<th scope="col">지점</th>
									<th scope="col">접수</th>
									<th scope="col">진행중(대기)</th>
									<th scope="col">진행중</th>
									<th scope="col">부재/보류</th>
									<th scope="col">완료</th>
									<th scope="col">취소</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach var="result" items="${resultList}" varStatus="status" >
									<tr>
										<td>${result.BRANCH_NAME}</td>
										<td>${result.SUBMIT_CNT}</td>
										<td>${result.PENDING_CNT}</td>
										<td>${result.PROGRESS_CNT}</td>
										<td>${result.ABSENCE_CNT}</td>
										<td>${result.COMPLETE_CNT}</td>
										<td>${result.CANCEL_CNT}</td>
									</tr>
								</c:forEach>
								</tbody>
								<tfoot>
									<!-- <tr>
										<td>심사</td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr> -->
									<tr>
										<td>합계</td>
										<td id="sumSubmit">${chartResult.SUBMIT_CNT}</td>
										<td id="sumPending">${chartResult.PENDING_CNT}</td>
										<td id="sumProgress">${chartResult.PROGRESS_CNT}</td>
										<td id="sumAbsence">${chartResult.ABSENCE_CNT}</td>
										<td id="sumComplete">${chartResult.COMPLETE_CNT}</td>
										<td id="sumCancel">${chartResult.CANCEL_CNT}</td>
									</tr>
								</tfoot>
							</table>
						</section>
							
<script>

</script>