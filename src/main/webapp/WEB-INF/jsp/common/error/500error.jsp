<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/include/member/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/script.jsp" %>


</head>
<body>
<div id="wrap" class="">
	<div id="header">
	<div class="nav_skip">
		<a href="#container">본문 바로가기</a>
		<a href="#footer">하단정보 바로가기</a>
	</div>
	<div class="inner">
		<h1>
			<a href="javascript:mainGo();" class="">
				<span class="sr_only">현대차미소금융</span>
			</a>
		</h1>
	</div>
</div>

	<div id="container">
		<!-- 서비스 접속 불가 -->
		<div class="error">
			<h3>서비스에 접속할 수 없습니다.</h3>
			<p class="message">
				죄송합니다. <br>
				기술적인 문제로 일시적으로 접속되지 않았습니다. <br>
				잠시 후 다시 이용을 부탁드리며 이용에 불편을 드려 사과드립니다.
			</p>
			<button type="button" class="btn btn_xlg btn_red" onclick="javascript:mainGo();">HOME</button>
<!-- 			<button class="btn btn_xlg btn_outline">이전</button> -->
		</div>

	</div>	<!--#container-->
</div>	<!--#wrap-->

<script>
	function mainGo(){
		location.href = "/front/main/mainList";
	}
</script>
</body>
</html>

