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
			
			<div class="group_consult">
				<h4>대출상담 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 02-361-6000</h4>
				<div class="desc">
					상담사와 상담 가능합니다. (평일 오전 9시 ~ 오후 5시)
				</div>
				
				<!-- <div class="phone">
					대출상담<span class="num">02-361-6000</span>
				</div> -->
			</div>
			
			<div class="group_request">
				<h4>대출상담 신청</h4>
				<div class="desc">
					연락처를 남겨주시면 관할지점에서 연락드립니다.
				</div>
				<form name="frm_consult">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
					<div class="form_consult">
						<div class="form_label">
							<label for="username">이름</label>
							<input type="text" id="username" name="consult_name" class="form_control" placeholder="이름을 입력해주세요" />
						</div>
						<div class="form_label">
							<label for="username2">연락처</label>
							<input type="text" id="username2" name="consult_phone" class="form_control" placeholder="전화번호를 입력해주세요" />
						</div>
						<div class="form_label">
							<label for="username3">주소</label>
							<input type="text" id="username3" name="consult_address" class="form_control" placeholder="시·군·구 까지만 입력해주세요" />
						</div>
						<div class="agree_area">
							<label class="input_check">
								<input type="checkbox" name="personal_info_check">
								<span class="label_text">개인정보 수집 &middot; 이용 동의 </span>
							</label>
							<a href="javascript:void(0);" class="btn_detail">자세히보기</a>
						</div>
						<button type="button" class="btn btn_lg btn_blue w100" id="btn_request" data-btn="email_send">상담신청</button>
						<p class="desc">대출상담은 고객님의 신용평점에  영향을 주지 않습니다.<br />귀하는 개인정보 수집 이용동의를 거부할 권리가 있으나, 동의하지 않으실 경우 상담이 불가능할 수 있습니다.  
						</p>
					</div>
				</form>
			</div>

			<div class="group_terms">
<!-- 				<label class="input_check"> -->
<!-- 					<input type="checkbox" id="" name="" checked> -->
					<span class="label_text">개인정보 수집 &middot; 이용 동의 전문 </span>
<!-- 				</label> -->
				<div class="terms">
					<h5>현대차미소금융재단 귀중</h5>
					<ol>
						<li>①  개인정보를 수집 · 이용하는 자의 개인정보 이용목적 : 신용대출 상품 전화, 문자 등을 통한 상담 및 안내</li>
						<li> ②  수집 · 이용하는 개인정보의 항목 : 성명, 전화번호 또는 핸드폰번호, 주소</li>
						<li>③  개인정보를 수집 · 이용하는 자의 개인정보 보유 및 이용기간 : 동의일로부터 3개월 후 파기</li>
					</ol>
					<!-- <p>*귀하는 수집 · 이용 동의를 거부할 권리가 있으나, 동의하지 않으실 경우 상담이 불가능할 수 있음을 알려드립니다. </p> -->
				</div>
			</div>
		</section>	<!--section-->
	</main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>

<div id="pop_request" class="pop_wrap">
	<div class="pop_contents">
		<p class="msg">전화 상담 신청이 접수 되었습니다.</p>
	</div>
</div>

<script>
$('.btn_detail').on('click', function() {
	$('.group_terms').slideToggle(200);
});

//메일 발송
$("[data-btn=email_send]").on("click", function() {
	//openPopConfirmAlert("${messageSaveConfirm}", sendmail);
	sendmail();
});

// 신청
function sendmail(){
	// S: 유효성검사
	if (!$("input:checked[Name='personal_info_check']").is(":checked")){ 
		openPopAlertFocus($("form[name=frm_consult] input[name=personal_info_check]"), "${messageValidationCHECK_PERSONAL_INFO_CHECK}"); 
		return; 
	}

	if(isEmpty($("form[name=frm_consult] input[name=consult_name]").val())){
		openPopAlertFocus($("form[name=frm_consult] input[name=consult_name]"), "${messageValidationEmpty_CONSULT_NAME}");
		return;
	}
	if(isEmpty($("form[name=frm_consult] input[name=consult_phone]").val())){
		openPopAlertFocus($("form[name=frm_consult] input[name=consult_phone]"), "${messageValidationEmpty_CONSULT_PHONE}");
		return;
	}
	if(isEmpty($("form[name=frm_consult] input[name=consult_address]").val())){
		openPopAlertFocus($("form[name=frm_consult] input[name=consult_address]"), "${messageValidationEmpty_CONSULT_ADDRESS}");
		return;
	}
	// E: 유효성검사
	
	$.ajax({
		type:"POST"
		, url: "/common/sendmail"
		, data: $("form[name=frm_consult]").serialize()
		, dataType:"json"
		, success : function(responseData){
			var data = responseData.resultJson;
			if(data.rCode == "0000") {
				openPopAlert("상담신청이 접수되었습니다.빠른 시간 안에 전화드리겠습니다");
			} else {
				openPopAlertAction(data.rMsg);
			}
		}
		,error : function(xhr,status,error) {
			if(xhr.status == 401) {
				openPopAlert("${errorXhr401}");
			} else if(xhr.status == 403) {
				openPopAlert("${errorXhr403}");
			} else if(xhr.status == 500) {
				openPopAlert("${errorXhr500}");
			} else {
				openPopAlert("${errorXhrElse}");
			}
		},
	});
}	


</script>

</body>
</html>
