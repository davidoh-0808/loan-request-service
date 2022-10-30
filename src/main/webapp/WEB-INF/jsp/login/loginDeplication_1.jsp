<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/member/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/member/script.jsp" %>
</head>

<body>

<!-- 알럿팝업 -->
<div class="pop_wrap alert_msg">
	<div class="pop_contents">
		<p class="msg" id="popMsg"></p>
	</div>
</div>

<script language="javascript">
$(function(){
	openPopAlertAction("${sendMsg}", goPage);
	
	function goPage(){
		location.href = "${toward}";
	}
});
</script>


</body>
</html>