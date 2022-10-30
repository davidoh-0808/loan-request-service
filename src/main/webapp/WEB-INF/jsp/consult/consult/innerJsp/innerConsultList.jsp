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
             <c:forEach var="result" items="${resultList}" >
                 <tr data-btn="detailInfo" onclick="checkStatsAndDirect('${result.CONS_SEQ}', '${result.STATS_CODE}')">
                     <td>${result.ROW_NUM}</td>
                     <td>${result.IN_DTTM}</td>
                     <td>${result.CONS_DTTM}</td>
                     <td>${result.CONS_MB_NAME}</td>
                     <td>${result.BRANCH_NAME}</td>
					<c:if test="${'GUEST' eq result.IN_USER}">
	               		<td>고객등록</td>
	                </c:if>
	                <c:if test="${'GUEST' ne result.IN_USER}">
						<td>${result.REGISTRAR}</td>
	                </c:if>
                     <td>${result.CUST_NM}</td>
                     <td>${result.CUST_REGI_NO_FM}</td>
                     <td>${result.INQU_CONS}</td>
                     <td>${result.RECORD_TIME}</td>
                     <td>${result.INFLOW_NAME2}</td>
                     <td>${result.INFLOW_NAME1}</td>
                     <td>${result.PRODUCT_NAME}</td>
                     <td>${result.TYPE_NAME}</td>
                     <td>${result.CORP_HIS}</td>
                     <td>${result.DEBT_SETT}</td>
                     <td>${result.STATS_NAME}</td>
                     <td>${result.COMP_DATE}</td>
                     <td>${result.EVAL_RESULT_NAME}</td>
                     <td>${result.DECLINE_REASON_NAME}</td>
                     <td>${result.CUST_HP_NO_FM}</td>
                 </tr>
             </c:forEach>
         </c:otherwise>
     </c:choose>
