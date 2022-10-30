<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/member/taglibs.jsp" %>

<spring:eval expression="@environment.getProperty('spring.mvc.format.date')" var="SPRING_MVC_FORMAT_DATE" />
<spring:eval expression="@environment.getProperty('spring.mvc.format.dateTime')" var="SPRING_MVC_FORMAT_DATETIME" />

<c:set var="errorXhr401"><spring:message code="error.xhr.401"/></c:set>
<c:set var="errorXhr403"><spring:message code="error.xhr.403"/></c:set>
<c:set var="errorXhr500"><spring:message code="error.xhr.500"/></c:set>
<c:set var="errorXhrElse"><spring:message code="error.xhr.else"/></c:set>


<form name="frm_menu">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
</form>

<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=w8fmcdkndx">
</script>

<header id="header">
	<div class="nav_skip">
		<a href="#container">본문 바로가기</a>
		<a href="#footer">하단정보 바로가기</a>
	</div>
	
	<h1>
		<a href="/front/main/mainList" class="">
			<span class="sr_only">현대 미소금융</span>
		</a>
	</h1>
		
	<div class="titlebar">
		<button class="btn btn_md btn_prev ir" onclick="history.back()">이전</button>
		<h2>${m_title }</h2>
		<button class="btn btn_md btn_gnb ir">전체 메뉴 보기 토글</button>
	</div>
	
	<nav id="gnb">
	
		<h2 class="logo_gnb">
			<a href="/front/main/mainList" class="sr_only">현대차미소금융재단 전체메뉴</a>
		</h2>
		<button class="btn btn_md btn_close_gnb ir">전체 메뉴 닫기</button>

		<ul class="lst_gnb">
			<li>
				<a href="/front/consultation/request" class="">대출상담 신청</a>
				<ol class="lst_submenu">
					<li><a href="/front/consultation/request" class="">대출상담 신청</a></li>
					<li><a href="/front/consultation/qna">자주하는 질문</a></li>
				</ol>
			</li>
			<li>
				<a href="/front/product/intro">대출상품 안내</a>
				<ol class="lst_submenu">
					<li><a href="/front/product/intro">미소금융 사업소개</a></li>
					<li><a href="/front/product/target">대출대상/대출상품</a></li>
					<li><a href="/front/product/process">대출절차/서류안내</a></li>
					<li><a href="/front/product/policy">대출거래 기본약관</a></li>
				</ol>
			</li>
			<li>
				<a href="/front/thank/finance">미소금융소식</a>
				<ol class="lst_submenu">
					<li><a href="/front/thank/finance">고마워요 미소금융</a></li>
					<li><a href="/front/thank/news">미소금융 news</a></li>
					<li><a href="/front/thank/report">고객의 소리</a></li>
					
				</ol>
			</li>
			<li>
				<a href="/front/foundation/greeting">재단소개</a>
				<ol class="lst_submenu">
					<li><a href="/front/foundation/greeting">인사말</a></li>
					<li><a href="/front/foundation/vision">재단비전</a></li>
					<li><a href="/front/foundation/organization">조직안내</a></li>
					<li><a href="/front/foundation/branch">지점안내</a></li>
					<li><a href="/front/foundation/report">재정보고</a></li>
				</ol>
			</li>
		</ul>	 
	</nav>
	
</header>