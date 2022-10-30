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
		<span class="tit">미소금융소식</span>
<!-- 		<span class="desc">Smile Finance News</span> -->
	</h2>
	<ul class="lst_lnb">
		<li><a href="/front/thank/finance" id="first">고마워요 미소금융</a></li>
		<li><a href="/front/thank/news" id="second">미소금융 news</a></li>
		<li><a href="/front/thank/report" id="third">고객의 소리</a></li>
	</ul>
	
	<select name="" id="selectTitle" class="selectbox">
		<option value="/front/thank/finance">고마워요 미소금융</option>
		<option value="/front/thank/news">미소금융 news</option>
		<option value="/front/thank/report">고객의 소리</option>
	</select>
</nav>

<script>
$(document).ready(function() {
	var url = location.href;
	var getAr0 = url.indexOf("finance");
	var getAr1 = url.indexOf("news");
	var getAr2 = url.indexOf("report");

	if(getAr0 != -1) {
		$("#first").addClass("active");
		$('#selectTitle').val('/front/thank/finance').attr("selected", "selected").selectmenu("refresh");
	}
	if(getAr1 != -1) {
		$("#second").addClass("active");
		$('#selectTitle').val('/front/thank/news').attr("selected", "selected").selectmenu("refresh");
	}
	if(getAr2 != -1) {
		$("#third").addClass("active");
		$('#selectTitle').val('/front/thank/report').attr("selected", "selected").selectmenu("refresh");
	}
});	

//메뉴 이동 선택
$('#selectTitle').change(function(){
	location.href = $(this).val();
});

</script>
<!-- // lnb -->