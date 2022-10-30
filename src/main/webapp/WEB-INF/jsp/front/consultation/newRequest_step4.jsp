<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>


<!-- 프로퍼티메세지 -->
<c:set var="messageValidationEmpty_CONSULT_NAME"><spring:message code="validation.empty.input" arguments="이름을"/></c:set>
<c:set var="messageValidationEmpty_CONSULT_PHONE"><spring:message code="validation.empty.input" arguments="연락처를"/></c:set>
<c:set var="messageValidationEmpty_CONSULT_ADDRESS"><spring:message code="validation.empty.input" arguments="주소를"/></c:set>
<c:set var="messageValidationCHECK_PERSONAL_INFO_CHECK"><spring:message code="validation.check.required" /></c:set>
</head>

<body>
<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="대출상담 신청"></c:set>
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->

	<!-- slider -->
	<%@ include file="/WEB-INF/jsp/front/consultation/include/slider.jsp" %>
	<!-- slider -->

	<main id="container" class="inner">
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/front/consultation/include/lnb.jsp" %>
		<!-- lnb -->
		
            <section class="contents">
                <div class="group_title">
                    <ul class="breadcrumb">
                        <li>Home</li>
                        <li>대출상담 신청</li>
                        <li>대출상담 신청</li>
                    </ul>
                    <h3>대출상담 신청</h3>
                </div>
                <div class="group_content">
                    <div class="left">
                        <ul class="step">
                            <li >본인확인</li>
                            <li>정보입력</li>
                            <li  class="on">신청완료</li>
                        </ul>
                    </div>
                    <div class="right">
                        <div class="notice">
                            <p>대출상담은 고객님의 신용평점에 영향을 주지 않습니다.</p>
                            <span>대출상담 02 - 361 - 6000으로 연락주시면 상담원과 상담 가능합니다. (평일 오전 9시 ~ 오후 5시)</span>
                        </div>
                        <div class="request_completion">
                            <h5>접수가 완료되었습니다.</h5>
                            <div class="txt">
                                <p>00:00분 이후 접수건은 익영업일에 상담 가능합니다.</p>
                                <p>대출 대상자 자격기준은 당재단 신용 조회일 기준입니다.</p>
                                <p>신용 조회 결과는 문자 또는 대표번호(02 - 361 - 6000)로 연락드리겠습니다.</p>
                            </div>
                        </div>
                        <div class="btn_area">
                            <button type="button" class="btn btn_lg btn_blue" onclick="go_main();">홈으로</button>
                        </div>
                    </div>
                </div>
            </section>
            <!--section-->
        </main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>

<script>

function go_main() {
    window.location = "/front/main/mainList";
}


</script>

</body>
</html>
