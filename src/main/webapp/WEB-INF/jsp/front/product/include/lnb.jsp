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
		<span class="tit">대출상품 안내</span>
<!-- 		<span class="desc">Microfinance Loan Products</span> -->
	</h2>
	<ul class="lst_lnb">
		<li><a href="/front/product/intro" id="first">사업소개</a></li>
		<li><a href="/front/product/target" id="second">대출대상/대출상품</a></li>
		<li><a href="/front/product/process" id="third">대출절차/서류안내</a></li>
		<li><a href="/front/product/policy" id="fourth">대출거래 기본약관</a></li>
	</ul>
	
	<select name="" id="selectTitle" class="selectbox">
		<option value="/front/product/intro">사업소개</option>
		<option value="/front/product/target">대출대상/대출상품</option>
		<option value="/front/product/process">대출절차/서류안내</option>
		<option value="/front/product/policy">대출거래 기본약관</option>
	</select>
</nav>

<script>
$(document).ready(function() {
	var url = location.href;
	var getAr0 = url.indexOf("intro");
	var getAr1 = url.indexOf("target");
	var getAr2 = url.indexOf("process");
	var getAr3 = url.indexOf("policy");

	if(getAr0 != -1) {
		$("#first").addClass("active");
		$('#selectTitle').val("/front/product/intro").attr("selected", "selected").selectmenu("refresh");
	}
	if(getAr1 != -1) {
		$("#second").addClass("active");
		$('#selectTitle').val("/front/product/target").attr("selected", "selected").selectmenu("refresh");
	}
	if(getAr2 != -1) {
		$("#third").addClass("active");
		$('#selectTitle').val("/front/product/process").attr("selected", "selected").selectmenu("refresh");
	}
	if(getAr3 != -1) {
		$("#fourth").addClass("active");
		$('#selectTitle').val("/front/product/policy").attr("selected", "selected").selectmenu("refresh");
	}
});	

//메뉴 이동 선택
$('#selectTitle').change(function(){
	location.href = $(this).val();
});
</script>
<!-- // lnb -->