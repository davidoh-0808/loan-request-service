<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>

<spring:eval expression="@environment.getProperty('spring.mvc.format.date')" var="SPRING_MVC_FORMAT_DATE" />
<spring:eval expression="@environment.getProperty('spring.mvc.format.dateTime')" var="SPRING_MVC_FORMAT_DATETIME" />

<sec:authentication property="details.member_code" var="sec_member_code"/>
<sec:authentication property="details.member_id" var="sec_member_id"/>
<sec:authentication property="details.member_name" var="sec_member_name"/>
<sec:authentication property="details.platform_auth" var="sec_platform_auth"/>
<sec:authentication property="details.branch_name" var="sec_branch_name"/>
<sec:authentication property="details.member_authority_name" var="sec_authority_name"/>

<c:set var="errorXhr401"><spring:message code="error.xhr.401"/></c:set>
<c:set var="errorXhr403"><spring:message code="error.xhr.403"/></c:set>
<c:set var="errorXhr500"><spring:message code="error.xhr.500"/></c:set>
<c:set var="errorXhrElse"><spring:message code="error.xhr.else"/></c:set>


<!-- 관리자 상단 -->
<div class="admin_top">
	<div class="top_area">
		<!-- 로고 -->
		<div class="logo">
			<h1>
			    <span>
			    <a href="/consult/main/dashboard">
			        <img src="/resources/publish/img/h_logo.gif" alt="서민금융진흥원 사업수행기관 현대차미소금융재단">
                </a>
                </span>
            </h1>
		</div>
		<!-- // 로고 -->
		<!-- 사용자 -->
        <div class="user user_wrap">
            <div class="user_login">
                <div class="user_id">
                    <p>${sec_member_name }</p>
                </div>
                
                <div class="user_info">
                    <c:if test="${not empty sec_branch_name }"><p>${sec_branch_name }</p></c:if>
                    <c:if test="${not empty sec_authority_name }"><p>${sec_authority_name }</p></c:if>
                </div>
                
            </div>
            <button type="button" class="r_box" data-btn=btnLogout>Log-Out</button>
        </div>
    <!-- //사용자 -->
	</div>
</div>
<!-- // 관리자 상단 -->


<form name="frm_menu" method="get">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
</form>

<script>
// 로그아웃
$("[data-btn=btnLogout]").on("click", function() {
	goMenu("/login/logoutProcess");
});

</script>