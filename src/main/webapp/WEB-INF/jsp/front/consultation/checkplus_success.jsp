<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
	<form name="form_chk" method="post">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
		<input type="hidden" id="sName" name="sName" value="${sName}">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" id="sBirthDate" name="sBirthDate" value="${sBirthDate}">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" id="sGender" name="sGender" value="${sGender}">						<!-- 필수 데이타로, 누락하시면 안됩니다. -->
		<input type="hidden" id="sMobileNo" name="sMobileNo" value="${sMobileNo }">		<!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
	</form>
<script>
//parent.window.close(); 
//이러면 닫히는듯 하고 	새로운 팝업창에서 기존창의 주소를 바꿀려면
//parent.opener.document.location="주소";

//window.onload = function(event){
     //clickme는 "닫기" 버튼의 id이다.
     var sName = document.getElementById("sName").value;
     var sBirthDate = document.getElementById("sBirthDate").value;
     var sGender = document.getElementById("sGender").value;
     var sMobileNo = document.getElementById("sMobileNo").value;
     
     window.opener.document.getElementById("sName").value = sName;
     window.opener.document.getElementById("sBirthDate").value = sBirthDate;
     window.opener.document.getElementById("sGender").value = sGender;
     window.opener.document.getElementById("sMobileNo").value = sMobileNo;
     
     
    window.opener.nextStep2();
    //window.opener.location.href = "/front/consultation/newRequest_step2?sName="+sName+"&sBirthDate="+sBirthDate+"&sGender="+sGender+"&sMobileNo="+sMobileNo;
     
     // $("form[name=form_chk]").attr("action", "/front/consultation/newRequest_step2");
     //$("form[name=form_chk]").attr("method", "post");
     //$("form[name=form_chk]").submit();
     
 	//window.opener.document.form_chk.action = "/front/consultation/newRequest_step2";
 	//document.form_chk.target = "popupChk";
 	//window.opener.document.form_chk.submit();
 	
    window.close();
//}

</script>

</body>
</html>
