<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/member/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/script.jsp" %>

<script src="/resources/front/js/vendor/jquery-3.4.1.min.js"></script>
<script src="/resources/front/js/vendor/jquery-ui.min.js"></script>
<script src="/resources/front/js/vendor/swiper.min.js"></script>
<script src="/resources/front/js/common.js"></script>

<link type="text/css" rel="stylesheet" href="/resources/front/css/vendor/jquery-ui.min.css"/>
<link type="text/css" rel="stylesheet" href="/resources/front/css/vendor/swiper.min.css"/>
<link type="text/css" rel="stylesheet" href="/resources/front/css/common.css"/>
<link type="text/css" rel="stylesheet" href="/resources/front/css/main.css"/>

</head>

<body>
<div id="wrap" class="wrap_error">
	<div class="group_error">
		<h2>존재하지 않는 페이지 입니다.</h2>
		<p class="desc">
			기술적인 문제로 접속되지 않습니다.<br />
			서비스 관리에게 문의해 주세요.<br />
			감사합니다.
		</p>
		<div class="btn_area">
			<button class="btn btn_xs btn_blue" id="history_back">이전</button>
			<button class="btn btn_xs btn_white" onclick="go_main()">HOME</button>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	$("#history_back").click(function(){
		window.history.back();
	});
});

function go_main() {
    window.location = "/front/main/mainList";
}
</script>

</body>

</html>

