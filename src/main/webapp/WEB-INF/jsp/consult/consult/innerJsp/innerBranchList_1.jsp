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
			<tr data-btn="detailInfo" onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">
				<td>${result.ROW_NUM}</td>
				<%-- <td><fmt:formatDate value="${result.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/></td> --%>
				<td>${result.IN_DTTM}</td>
				<td>${result.BRANCH_MB_NAME}</td>
				<td>${result.CUST_NM}</td>
				<td>${result.CUST_HP_NO}</td>
				<td>${result.INFLOW_NAME1}</td>
				<td>${result.PRODUCT_NAME}</td>
				<td>${result.TYPE_NAME}</td>
				<td>
					<c:if test="${not empty result.CORP_HIS}">
						${result.CORP_HIS} 년
					</c:if>
				</td>
				<td>${result.STATS_NAME}</td>
				<td>${result.EVAL_RESULT_NAME}</td>
				<td>${result.DECLINE_REASON_NAME}</td>
				<td>${result.BRANCH_RESULT_NAME}</td>
			</tr>
		</c:forEach>
         </c:otherwise>
     </c:choose>