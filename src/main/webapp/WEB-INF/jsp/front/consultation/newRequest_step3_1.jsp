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
				<form name="frm">
				<input type="hidden" class="csrfname" name="${_csrf.parameterName}" value="${_csrf.token }"/>
				<input type="hidden" id="ADDR_TYPE" name="ADDR_TYPE" value=""/>
				<input type="hidden" id="TEAM_CODE" name="TEAM_CODE" value="MC0000900099"/><!-- 심사팀코드 -->
				<input type="hidden" id="BRANCH_CODE" name="BRANCH_CODE" value="${paramVO.BRANCH_CODE}"/>
				<input type="hidden" id="BRANCH_NAME" name="BRANCH_NAME" value="${paramVO.BRANCH_NAME}"/>
				
				<input type="hidden" id="CUST_NM" name="CUST_NM" value="${paramVO.CUST_NM}"/>
				<input type="hidden" id="CUST_REGI_NO" name="CUST_REGI_NO" value="${paramVO.CUST_REGI_NO}"/>
				<input type="hidden" id="CUST_HP_NO" name="CUST_HP_NO" value="${paramVO.CUST_HP_NO}"/>
				<input type="hidden" id="CUST_TYPE" name="CUST_TYPE" value="${paramVO.CUST_TYPE}"/>
				<input type="hidden" id="VULN_CLASS" name="VULN_CLASS" value="${paramVO.VULN_CLASS}"/>
				<input type="hidden" id="CUST_CORP_NO" name="CUST_CORP_NO" value="${paramVO.CUST_CORP_NO}"/>
				<input type="hidden" id="CORP_REGI_DT" name="CORP_REGI_DT" value="${paramVO.CORP_REGI_DT}"/>
				<input type="hidden" id="TYPE_CODE" name="TYPE_CODE" value="${paramVO.TYPE_CODE}"/>
				<input type="hidden" id="CUST_CORP_AREA" name="CUST_CORP_AREA" value="${paramVO.CUST_CORP_AREA}"/>
				<input type="hidden" id="CUST_CORP_ADDR1" name="CUST_CORP_ADDR1" value="${paramVO.CUST_CORP_ADDR1}"/>
				<input type="hidden" id="CUST_CORP_ADDR2" name="CUST_CORP_ADDR2" value="${paramVO.CUST_CORP_ADDR2}"/>
				<input type="hidden" id="CORP_NM" name="CORP_NM" value="${paramVO.CORP_NM}"/>
				<input type="hidden" id="JOIN_DATE" name="JOIN_DATE" value="${paramVO.JOIN_DATE}"/>
				<input type="hidden" id="JOB_TYPE" name="JOB_TYPE" value="${paramVO.JOB_TYPE}"/>
				<input type="hidden" id="JOB_TYPE_NAME" name="JOB_TYPE_NAME" value="${paramVO.JOB_TYPE_NAME}"/>
				<input type="hidden" id="HOME_ADDR1" name="HOME_ADDR1" value="${paramVO.HOME_ADDR1}"/>
				<input type="hidden" id="HOME_ADDR2" name="HOME_ADDR2" value="${paramVO.HOME_ADDR2}"/>
				
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
                            <li class="on">정보입력</li>
                            <li>신청완료</li>
                        </ul>
                    </div>
                    <div class="right">
                        <div class="notice">
                            <p>대출상담은 고객님의 신용평점에 영향을 주지 않습니다.</p>
                            <span>대출상담 02 - 361 - 6000으로 연락주시면 상담원과 상담 가능합니다. (평일 오전 9시 ~ 오후 5시)</span>
                        </div>
                        <div class="request_creditinfo">
                            <h4><span>신청고객 정보</span></h4>
                            <dl class="table_responsive">
                                <dt class="col"><span>이름</span></dt>
                                <dd class="col">${paramVO.CUST_NM}</dd>
                                <dt class="col">주민등록번호</dt>
                                <dd class="col">${sBirthDate}-${sGender}******</dd>
                                <dt class="col">연락처</dt>
                                <dd class="col formatPhone">${sMobileNo}</dd>
                                <dt class="col">유입경로</dt>
                                <dd class="col">홈페이지</dd>
                            </dl>
                        </div>
                        <div class="request_registration">
                            <h4><span>사업자 등록여부</span></h4>
                        
                            <dl class="table_responsive">
                                <dt class="col"><span>사업자 등록</span></dt>
                                <dd class="col">${paramVO.CUST_TYPE_NAME }</dd>
                                <c:if test="${'MC0001500001' ne paramVO.CUST_TYPE }">
			                        <dt class="col optionBox"><span>취약계층 여부</span></dt>
			                        <dd class="col optionBox">${paramVO.VULN_CLASS_NAME }</dd>
		                        </c:if>
                            </dl>
                        </div>
                        <!-- 사업자 정보 -->
                        <c:if test="${'MC0001500001' eq paramVO.CUST_TYPE }">
                        <div class="request_business">
                            <h4><span>사업자정보</span></h4>
                            <dl class="table_responsive">
                                <dt><span>사업자 번호</span></dt>
                                <dd>${paramVO.CUST_CORP_NO }</dd>
                                <dt><span>사업자 등록일</span></dt>
                                <dd>${paramVO.CORP_REGI_DT }</dd>
                                <dt><span>사업년수</span></dt>
								<dd>${paramVO.CORP_HIS }</dd>
                                <dt><span >업종</span></dt>
                                <dd>${paramVO.TYPE_NAME }</dd>
                                <dt><span class="address">사업자주소</span></dt>
                                <dd>${paramVO.CUST_CORP_ADDR1 } ${paramVO.CUST_CORP_ADDR2 }</dd>
                            </dl>
                            <h4>담당자 정보</h4>
                            <dl class="table_responsive">
                                <dt><span>부서명</span></dt>
                                <dd>심사팀</dd>
                                <dt><span>담당자 </span></dt>
                                <dd>${paramVO.BRANCH_NAME }</dd>
                            </dl>
                        </div>
                        </c:if>
                        <!--// 사업자 정보 -->
                        <!-- 직장 정보 -->
                        <c:if test="${'MC0001500001' ne paramVO.CUST_TYPE }">
			                <div class="request_company">
			                    <h4><span class="emp_red">직장 정보</span></h4>
			                    <dl class="table_responsive">
			                        <dt><span>직장명</span></dt>
			                        <dd>${paramVO.CORP_NM }</dd>
			                        <dt><span>입사일</span></dt>
			                        <dd>${paramVO.JOIN_DATE }</dd>
			                        <dt><span>직업</span></dt>
			                        <dd>${paramVO.JOB_TYPE_NAME }</dd>
			                        <dt><span>자택주소</span></dt>
			                        <dd>${paramVO.HOME_ADDR1 } ${paramVO.HOME_ADDR2 }</dd>
			                    </dl>
			                    <h4>담당자 정보</h4>
			                    <dl class="table_responsive">
			                        <dt><span>부서명</span></dt>
			                        <dd>심사팀</dd>
			                        <dt><span>지점</span></dt>
			                        <dd>${paramVO.BRANCH_NAME }</dd>
			                    </dl>
			                </div>
			                </c:if>
                        <!--// 직장 정보 -->
                        <div class="btn_area">
                            <button type="button" class="btn btn_lg btn_white" onclick="window.history.back();">수정</button>
                            <button type="button" class="btn btn_lg btn_blue" onclick="sendStep4();">다음</button>
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


// 유효성검사
function ajaxCheck(){
	
	let chk = true;
	// S: 유효성검사
	
	//이름
	if(isEmpty($("form[name=form_chk] input[name=CUST_NM]").val())){
		openPopAlertFocus($("form[name=form_chk] input[name=CUST_NM]"), "${messageValidationEmpty_CUST_NM}");
		chk = false;
		return; 
	}
	
	//실명번호
	if(isEmpty($("form[name=form_chk] input[name=CUST_REGI_NO]").val())){
		openPopAlertFocus($("form[name=form_chk] input[name=CUST_REGI_NO]"), "${messageValidationEmpty_CUST_REGI_NO}");
		chk = false;
		return; 
	}
	
	//휴대폰
	if(isEmpty($("form[name=form_chk] input[name=CUST_HP_NO]").val())){
		openPopAlertFocus($("form[name=form_chk] input[name=CUST_HP_NO]"), "${messageValidationEmpty_CUST_HP_NO}");
		chk = false;
		return; 
	}
	
	//전체동의
	if (!$("input:checked[Name='CHECK_ALL']").is(":checked")){ 
		openPopAlertFocus($("form[name=form_chk] input[name=CHECK_ALL]"), "${messageValidationCHECK_PERSONAL_CHECK_ALL}"); 
		chk = false;
		return; 
	}
	// E: 유효성검사
	
	return chk;
}

//step4로 이동
function sendStep4(){
    
    $("form[name=frm]").attr("action", "/front/consultation/newRequest_step4");
    $("form[name=frm]").attr("method", "post");
    $("form[name=frm]").submit();
    
}

</script>

</body>
</html>
