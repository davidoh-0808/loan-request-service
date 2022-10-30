<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/member/taglibs.jsp" %>

<spring:eval expression="@environment.getProperty('spring.mvc.format.date')" var="SPRING_MVC_FORMAT_DATE" />
<spring:eval expression="@environment.getProperty('spring.mvc.format.dateTime')" var="SPRING_MVC_FORMAT_DATETIME" />

<c:set var="errorXhr401"><spring:message code="error.xhr.401"/></c:set>
<c:set var="errorXhr403"><spring:message code="error.xhr.403"/></c:set>
<c:set var="errorXhr500"><spring:message code="error.xhr.500"/></c:set>
<c:set var="errorXhrElse"><spring:message code="error.xhr.else"/></c:set>


<!-- lnb -->
<nav id="lnb">
	<h2>
		<span class="tit">재단소개</span>
<!-- 		<span class="desc">About the Foundation</span> -->
	</h2>
	<ul class="lst_lnb">
		<li><a href="/front/foundation/greeting" id="first">인사말</a></li>
		<li><a href="/front/foundation/vision" id="second">재단비전</a></li>
		<li><a href="/front/foundation/organization" id="third">조직안내</a></li>
		<li><a href="/front/foundation/branch" id="fourth">지점안내</a></li>
		<li><a href="/front/foundation/report" id="fifth">재정보고</a></li>
	</ul>
	
	<select name="" id="selectTitle" class="selectbox">
		<option value="/front/foundation/greeting">인사말</option>
		<option value="/front/foundation/vision">재단비전</option>
		<option value="/front/foundation/organization">조직안내</option>
		<option value="/front/foundation/branch">지점안내</option>
		<option value="/front/foundation/report">재정보고</option>
	</select>
</nav>

<script>
$(document).ready(function() {
	var url = location.href;
	var getAr0 = url.indexOf("greeting");
	var getAr1 = url.indexOf("vision");
	var getAr2 = url.indexOf("organization");
	var getAr3 = url.indexOf("branch");
	var getAr4 = url.indexOf("report");

	if(getAr0 != -1) {
		$("#first").addClass("active");
		$('#selectTitle').val("/front/foundation/greeting").attr("selected", "selected").selectmenu("refresh");
	}
	if(getAr1 != -1) {
		$("#second").addClass("active");
		$('#selectTitle').val("/front/foundation/vision").attr("selected", "selected").selectmenu("refresh");
	}
	if(getAr2 != -1) {
		$("#third").addClass("active");
		$('#selectTitle').val("/front/foundation/organization").attr("selected", "selected").selectmenu("refresh");
	}
	if(getAr3 != -1) {
		$("#fourth").addClass("active");
		$('#selectTitle').val("/front/foundation/branch").attr("selected", "selected").selectmenu("refresh");
	}
	if(getAr4 != -1) {
		$("#fifth").addClass("active");
		$('#selectTitle').val("/front/foundation/report").attr("selected", "selected").selectmenu("refresh");
	}
});	

//메뉴 이동 선택
$('#selectTitle').change(function(){
	location.href = $(this).val();
});

</script>
<!-- // lnb -->