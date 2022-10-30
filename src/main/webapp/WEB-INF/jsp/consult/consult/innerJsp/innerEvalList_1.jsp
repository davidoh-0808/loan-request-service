<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
	
	
     <c:choose>
         <c:when test="${empty resultList}">
			<tr>
				<td class="empty" colspan="100%">
					<div class="empty_list">
						<p>검색 결과가 존재하지 않습니다.</p>
					</div>
				</td>
			</tr>
         </c:when>
         <c:otherwise>	
		<c:forEach var="result" items="${resultList}">
			<tr>
				<!--
					담당자배정 (x) -> 체크박스 디스플레이
					체크박스 클릭 -> 상담신청일련번호 저장
				-->
				<c:choose>
					<c:when test="${not empty result.CONS_MB_CODE}">
						<td></td>
					</c:when>
					<c:otherwise>
						<td>
							<input class="input_check blank" name="CHECK" type="checkbox" value="${result.CONS_SEQ}" >
						</td>
					</c:otherwise>
				</c:choose>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.ROW_NUM}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.IN_DTTM}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.CONS_DTTM}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.CONS_MB_NAME}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.BRANCH_NAME}</td>
				<c:if test="${'GUEST' eq result.IN_USER}">
               		<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">고객등록</td>
                </c:if>
                <c:if test="${'GUEST' ne result.IN_USER}">
					<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.REGISTRAR}</td>
                </c:if>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.CUST_NM}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.CUST_REGI_NO}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.INQU_CONS}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.RECORD_TIME}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.INFLOW_NAME2}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.INFLOW_NAME1}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.PRODUCT_NAME}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.TYPE_NAME}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.CORP_HIS}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.DEBT_SETT}</td>
				<td class="txt_c" onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.STATS_NAME}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">
					<c:if test="${not empty result.COMP_DATE}">${result.COMP_DATE}</c:if>
				</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.EVAL_RESULT_NAME}</td>
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.DECLINE_REASON_NAME}</td>
				<!-- <td>비고</td>
				<td>기타</td> -->
				<td onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">${result.CUST_HP_NO}</td>
			</tr>
		</c:forEach>
         </c:otherwise>
     </c:choose>