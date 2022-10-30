<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/member/taglibs.jsp" %>

<sec:authentication property="details.member_code" var="sec_member_code"/>
<sec:authentication property="details.member_id" var="sec_member_id"/>
<sec:authentication property="details.member_name" var="sec_member_name"/>

<spring:eval expression="@environment.getProperty('spring.mvc.format.date')" var="SPRING_MVC_FORMAT_DATE" />
<spring:eval expression="@environment.getProperty('spring.mvc.format.dateTime')" var="SPRING_MVC_FORMAT_DATETIME" />

<c:set var="errorXhr401"><spring:message code="error.xhr.401"/></c:set>
<c:set var="errorXhr403"><spring:message code="error.xhr.403"/></c:set>
<c:set var="errorXhr500"><spring:message code="error.xhr.500"/></c:set>
<c:set var="errorXhrElse"><spring:message code="error.xhr.else"/></c:set>




<ul>
	<li>
		<span>${sec_member_id }</span>
		<span>${sec_member_name }</span>
	</li>
	<li>
		<button type="button" data-btn="btnLogout">로그아웃</button>
	</li>
</ul>



<form name="frm_menu" method="get">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
</form>

<script>
// 로그아웃
$("[data-btn=btnLogout]").on("click", function() {
	goMenu("/login/logoutProcess");
});

</script>