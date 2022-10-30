<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/member/taglibs.jsp" %>

<spring:eval expression="@environment.getProperty('spring.mvc.format.date')" var="SPRING_MVC_FORMAT_DATE" />
<spring:eval expression="@environment.getProperty('spring.mvc.format.dateTime')" var="SPRING_MVC_FORMAT_DATETIME" />

<c:set var="errorXhr401"><spring:message code="error.xhr.401"/></c:set>
<c:set var="errorXhr403"><spring:message code="error.xhr.403"/></c:set>
<c:set var="errorXhr500"><spring:message code="error.xhr.500"/></c:set>
<c:set var="errorXhrElse"><spring:message code="error.xhr.else"/></c:set>


<!-- lnb -->
<div class="lnb_wrap">
	<div class="lnb">
		<ul class="dep1">
			<li><p><a href="#none" class="tit">관리자 관리</a></p>
				<ul class="dep2">
					<li id="member"><a href="/admin/member/memberList" data-auth="ACCOUNT_USE">- 계정 및 권한</a></li>
				</ul>
			</li>
			<li><p><a href="#none" class="tit">게시판 관리</a></p>
				<ul class="dep2">
					<li id="news"><a href="/admin/board/news/newsList" data-auth="NEWS_USE">- NEWS</a></li>
					<li id="thanks"><a href="/admin/board/thanks/thanksList" data-auth="THANKS_USE">- 고마워요미소금융</a></li>
					<li id="finan"><a href="/admin/board/finan/finanList" data-auth="FINAN_USE">- 재정보고</a></li>
				</ul>
			</li>
			<li><p><a href="#none" class="tit">통계 관리</a></p>
				<ul class="dep2">
					<li id="report"><a href="/admin/report/reportDeviceList" data-auth="REPORT_USE">- 통계현황 관리</a></li>
				</ul>
			</li>
		</ul>
	</div>
</div>

<script>
$(document).ready(function() {
	var url = location.href;
	var getAr0 = url.indexOf("member");
	var getAr1 = url.indexOf("news");
	var getAr2 = url.indexOf("thanks");
	var getAr3 = url.indexOf("finan");
	var getAr4 = url.indexOf("report");
	if(getAr0 != -1) {
		$("#member").addClass("on");
	}
	if(getAr1 != -1) {
		$("#news").addClass("on");
	}
	if(getAr2 != -1) {
		$("#thanks").addClass("on");
	}
	if(getAr3 != -1) {
		$("#finan").addClass("on");
	}
	if(getAr4 != -1) {
		$("#report").addClass("on");
	}
});	


// 권한 별 링크 제거
const auth_arr = "${sec_platform_auth }".split(",");
$('a[data-auth]').each(function() {
	var matchedcnt = 0;
	for(var i=0; i < auth_arr.length; i++) {
		if($(this).data("auth") == auth_arr[i]){ //값 비교
			matchedcnt = matchedcnt + 1;
		}
	}
	if(0 == matchedcnt){
// 		$(this).attr("href", "javascript:void(0);")
	}
});

</script>
<!-- // lnb -->