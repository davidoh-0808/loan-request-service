<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/admin/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/script.jsp" %>

<script src='/resources/common/js/jquery.cookie.js'></script>

	<spring:eval expression="@environment.getProperty('mastercode.member_gubun.admin')" var="MASTERCODE_MEMBER_GUBUN_ADMIN" />
	<spring:eval expression="@environment.getProperty('mastercode.platform_gubun.pc')" var="MASTERCODE_PLATFORM_GUBUN_PC" />
	
	<!-- 프로퍼티메세지 -->
	<c:set var="messageMissingFieldRequired_ID"><spring:message code="validation.empty.input" arguments="ID를"/></c:set>
	<c:set var="messageMissingFieldRequired_PASSWORD"><spring:message code="validation.empty.input" arguments="PASSWORD를"/></c:set>
	
	<c:set var="errorXhr401"><spring:message code="error.xhr.401"/></c:set>
	<c:set var="errorXhr403"><spring:message code="error.xhr.403"/></c:set>
	<c:set var="errorXhr500"><spring:message code="error.xhr.500"/></c:set>
	<c:set var="errorXhrElse"><spring:message code="error.xhr.else"/></c:set>
	<!-- 프로퍼티메세지 -->
	
</head>

<body>

<!--wrap -->
<div class="admin_main">
	<form name="frm" method="post" action="/login/loginProcess">
		<input type="hidden" name="member_gubun" value="${MASTERCODE_MEMBER_GUBUN_ADMIN }">
		<input type="hidden" name="platform_gubun" value="${MASTERCODE_PLATFORM_GUBUN_PC }">
		<!-- 로그인 -->
		<div class="login_wrap">
			<div class="main_login">
				<h1><img src="/resources/publish/img/h_main_logo.gif" alt="서민금융진흥원 사업수행기관 현대차미소금융재단"></h1>
				<dl class="id">
					<dt>ID</dt>
					<dd> <input type="text"  name="MEMBER_ID" value="${paramVO.MEMBER_ID }" placeholder="아이디를 입력해주세요"></dd>
				</dl>
				<dl class="pw">
					<dt>PASSWORD</dt>
					<dd> <input type="password" name="MEMBER_PW" value="${paramVO.MEMBER_PW }" placeholder="비밀번호를 입력해주세요"> </dd>
				</dl>
				<button type="button" class="btn pop_btn btn_login" data-btn="btnLogin">로그인</button>
			</div>
		</div>
		<!-- //로그인 -->
	</form>
</div>



<!-- //Wrap-->
<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/admin/footer.jsp" %>
<!-- footer -->



<script>
<c:if test="${not empty exceptionMsg}">
	openPopAlert("${exceptionMsg}");
	<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
</c:if>

// 임시
//$("form[name=frm] input[name=MEMBER_ID]").val("admin");
//$("form[name=frm] input[name=MEMBER_PW]").val("1234");


//쿠키에저장된아이디 사용하기
var cookie_admin_member_id = $.cookie("cookie_admin_member_id");
if(isNotEmpty(cookie_admin_member_id)){
	$("form[name=frm] input[name=MEMBER_ID]").val(cookie_admin_member_id);
}

$("form[name=frm] input[name=MEMBER_ID], form[name=frm] input[name=MEMBER_PW]").keyup(function(key){
	if(key.keyCode === 13) {
		goLogin();
	}
});
$("[data-btn=btnLogin]").on("click", function() {
	goLogin();
});
// 로그인
function goLogin(){
	if(isEmpty($("form[name=frm] input[name=MEMBER_ID]").val())){
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_ID]"), "${messageMissingFieldRequired_ID}");
		return;
	}
	
	if(isEmpty($("form[name=frm] input[name=MEMBER_PW]").val())){
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_PW]"), "${messageMissingFieldRequired_PASSWORD}");
		return;
	}
	// 전송시 로딩바노출
	$("body").addClass("loading");
	
	// 쿠키에아이디저장(1년)
	$.cookie("cookie_admin_member_id", $("form[name=frm] input[name=MEMBER_ID]").val(), {expires: 365, path:'/'});
	
	$("form[name=frm]").submit();
}

</script>

</body>
</html>
