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
				<span class="tit">대출상담 신청</span>
<!-- 				<span class="desc">Apply for loan consultation</span> -->
			</h2>
			<ul class="lst_lnb">
				<li><a href="/front/consultation/request" id="first">대출상담 신청</a></li>
				<li><a href="/front/consultation/newRequest" id="first">뉴 대출상담 신청</a></li>
				<li><a href="/front/consultation/qna" id="second">자주하는 질문</a></li>
			</ul>
			
			<select name="" id="selectTitle" class="selectbox">
				<option value="/front/consultation/request">대출상담 신청</option>
				<option value="/front/consultation/newRequest">대출상담 신청</option>
				<option value="/front/consultation/qna">자주하는 질문</option>
			</select>
		</nav>

<script>
$(document).ready(function() {
	var url = location.href;
	var getAr0 = url.indexOf("request");
	var getAr1 = url.indexOf("qna");

	if(getAr0 != -1) {
		$("#first").addClass("active");
		$('#selectTitle').val('/front/consultation/request').attr("selected", "selected").selectmenu("refresh");
		
	}
	if(getAr1 != -1) {
		$("#second").addClass("active");
		$('#selectTitle').val('/front/consultation/qna').attr("selected", "selected").selectmenu("refresh");
	}
});	

//메뉴 이동 선택
$('#selectTitle').change(function(){
	location.href = $(this).val();
});


</script>
<!-- // lnb -->