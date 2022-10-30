<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/member/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/script.jsp" %>

<script src='/resources/common/js/jquery.cookie.js'></script>

	<spring:eval expression="@environment.getProperty('mastercode.member_gubun.member')" var="MASTERCODE_MEMBER_GUBUN_MEMBER" />
	<spring:eval expression="@environment.getProperty('mastercode.platform_gubun.pc')" var="MASTERCODE_PLATFORM_GUBUN_PC" />
	
	<!-- 프로퍼티메세지 -->
	<c:set var="messageValidationEmpty_MEMBER_ID"><spring:message code="validation.empty.input" arguments="ID를"/></c:set>
	<c:set var="messageValidationEmpty_MEMBER_PW"><spring:message code="validation.empty.input" arguments="PASSWORD를"/></c:set>
	<c:set var="errorXhr401"><spring:message code="error.xhr.401"/></c:set>
	<c:set var="errorXhr403"><spring:message code="error.xhr.403"/></c:set>
	<c:set var="errorXhr500"><spring:message code="error.xhr.500"/></c:set>
	<c:set var="errorXhrElse"><spring:message code="error.xhr.else"/></c:set>
	<!-- 프로퍼티메세지 -->
	
</head>

<body>

<div id="wrap">

	<form name="frm" method="post" action="/login/loginProcess">
		<input type="hidden" name="member_gubun" value="${MASTERCODE_MEMBER_GUBUN_MEMBER }">
		<input type="hidden" name="platform_gubun" value="${MASTERCODE_PLATFORM_GUBUN_PC }">
	
		<dl>
			<dt>로그인</dt>
			<dd>
				<label for="input_id">ID</label>
				<input type="text" name="MEMBER_ID" value="${paramVO.MEMBER_ID }" placeholder="아이디를 입력해주세요" maxlength="30">
			</dd>
			<dd>
				<label for="input_pw">PASSWORD</label>
				<input type="password" name="MEMBER_PW" value="${paramVO.MEMBER_PW }" placeholder="비밀번호를 입력해주세요" maxlength="30" >
			</dd>
			<dd>
				<button type="button" data-btn="btnLogin">로그인</button>
				<button type="button" data-btn="btnJoin">회원가입</button>
			</dd>
		</dl>
	
	</form>
	
</div>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/member/footer.jsp" %>
<!-- footer -->

<script>

<c:if test="${not empty exceptionMsg}">
	var exMsg = "org.springframework.security.authentication.BadCredentialsException:";
	openPopAlert("${exceptionMsg}");
	<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
</c:if>
function fn_ErrMsg() {
	// 로그인실패시 전달되는 메세지
	var exMsg = "org.springframework.security.authentication.BadCredentialsException:";
	openPopAlert("${exceptionMsg}");
}

// 임시
$("form[name=frm] input[name=MEMBER_ID]").val("member");
$("form[name=frm] input[name=MEMBER_PW]").val("1234");

//쿠키에저장된아이디 사용하기
var cookie_member_id = $.cookie("cookie_member_id");
if(isNotEmpty(cookie_member_id)){
	$("form[name=frm] input[name=MEMBER_ID]").val(cookie_member_id);
}

// 회원가입
$("[data-btn=join]").on("click", function() {
	$("form[name=frm]").attr("action", "/join/joinFormMember");
	$("form[name=frm]").submit();
});

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
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_ID]"), "${messageValidationEmpty_MEMBER_ID}");
		return;
	}
	
	if(isEmpty($("form[name=frm] input[name=MEMBER_PW]").val())){
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_PW]"), "${messageValidationEmpty_MEMBER_PW}");
		return;
	}
	// 전송시 로딩바노출
	$("body").addClass("loading");
	
	// 쿠키에아이디저장(1년)
	$.cookie("cookie_member_id", $("form[name=frm] input[name=MEMBER_ID]").val(), {expires: 365, path:'/'});
	
	$("form[name=frm]").submit();
}

</script>

</body>
</html>
