<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

	<!-- 프로퍼티메세지 -->
	<c:set var="messageSaveConfirm"><spring:message code="message.insert.confirm"/></c:set>
	<c:set var="messageSaveSuccess"><spring:message code="message.insert.success"/></c:set>
	<c:set var="messageSaveCancelConfirm"><spring:message code="message.insert.cancel.confirm"/></c:set>
	
	<c:set var="messageValidationEmpty_MEMBER_ID"><spring:message code="validation.empty.input" arguments="아이디를"/></c:set>
	<c:set var="messageValidationEmpty_MEMBER_NAME"><spring:message code="validation.empty.input" arguments="이름을"/></c:set>
	<c:set var="messageValidationEmpty_MEMBER_PW"><spring:message code="validation.empty.input" arguments="비밀번호를"/></c:set>
	<c:set var="messageValidationEmpty_MEMBER_STATUS"><spring:message code="validation.empty.input" arguments="상태를"/></c:set>
	<c:set var="messageValidationEmpty_MEMBER_AUTHORITY"><spring:message code="validation.empty.input" arguments="권한을"/></c:set>
	<c:set var="messageValidationEmpty_MEMBER_PHONE"><spring:message code="validation.empty.input" arguments="전화번호를"/></c:set>
	<c:set var="messageValidationEmpty_MEMBER_EMAIL"><spring:message code="validation.empty.input" arguments="이메일을"/></c:set>
	
	<c:set var="validationJoinIdPattern"><spring:message code="validation.join.idPattern"/></c:set>
	<c:set var="validationJoinPwdInconsistency"><spring:message code="validation.join.pwdInconsistency"/></c:set>
	<c:set var="validationJoinPwdPattern"><spring:message code="validation.join.pwdPattern"/></c:set>
	<c:set var="validationJoinIdDupCheck"><spring:message code="validation.join.idDupCheck"/></c:set>
	
	<!-- 프로퍼티메세지 -->

</head>


<body>
<!--wrap -->
<div class="admin_wrap">

	<!-- header -->
	<%@ include file="/WEB-INF/jsp/common/include/consult/header.jsp" %>
	<!-- header -->
	
	<!-- 관리자 히단 -->
	<div class="admin_bottom">
	
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/common/include/consult/lnb.jsp" %>
		<!-- // lnb -->

		<!--content-->
		<div class="content">
			<h2 class="con_tit">계정 및 권한 관리</h2>
			<h3 class="s_tit">계정등록</h3>

			<form name="frm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				<input type="hidden" name="MEMBER_CODE" />
				
				<div class="tb_style tb_htype sp10">
					<table>
						<caption>계정 및 권한 관리 검색어(키워드)</caption>
						<colgroup>
							<col style="width: 142px;">
							<col style="width: auto;">
							<col style="width: 142px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">이름 <span class="point" title="필수항목"></span></th>
								<td colspan="3">
									<input type="text" class="input_text w_mid" title="제목입력" name="MEMBER_NAME" maxlength="20">
								</td>
							</tr>
							<tr>
								<th scope="row">아이디 <span class="point" title="필수항목"></span></th>
								<td colspan="3">
									<div class="btnarea_tb w_mid" data-form="memberPwForm">
										<input type="text" class="input_text w_mid" title="제목입력" name="MEMBER_ID" onkeydown="fnOnlyEng(this)" maxlength="20">
										<button type="button" class="btn btn_s btn_black" data-btn="idDupCheck">중복확인</button>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">비밀번호 <span class="point" title="필수항목"></span></th>
								<td>
									<div class="pw_ck_area ck_no" data-form="memberPwForm">
										<input type="password" class="input_text w_mid" title="비밀번호" name="MEMBER_PW" onkeydown="fnOnlyPwd(this)" maxlength="20">
										<span class="pw_ck_no" data-text="memberPwText">사용불가</span>
									</div>
<%-- 									<p>${validationJoinPwdPattern }</p> --%>
									<p>8~16자 영문, 숫자, 특수문자를 혼합하여 사용하세요</p>
								</td>
								<th scope="row">비밀번호 재입력<span class="point" title="필수항목"></span></th>
								<td>
									<div class="pw_ck_area ck_no" data-form="memberPwChkForm">
										<input type="password" class="input_text w_mid" title="비밀번호" id="MEMBER_PW_CHK" onkeydown="fnOnlyPwd(this)" maxlength="20">
										<span class="pw_ck_no" data-text="memberPwChkText">불일치</span>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">상태 <span class="point" title="필수항목"></span></th>
								<td>
									<div class="selbox w_mid">
										<select name="MEMBER_STATUS">
											<c:forEach items="${masterCodeList_memberStatus }" var="list">
												<option value="${list.MASTER_CODE }">${list.CODE_NAME }</option>
											</c:forEach>
										</select>
									</div>
								</td>
								<th scope="row">권한<span class="point" title="필수항목"></span></th>
								<td>
									<div class="selbox w_mid">
										<select name="MEMBER_AUTHORITY">
											<c:forEach items="${masterCodeList_memberAuthority }" var="list">
												<option value="${list.MASTER_CODE }">${list.CODE_NAME }</option>
											</c:forEach>
										</select>
								</td>
							</tr>
							<tr>
								<th scope="row">전화번호 <span class="point" title="필수항목"></span></th>
								<td>
									<input type="text" class="input_text w_mid" name="MEMBER_PHONE" maxlength="20">
								</td>
								<th scope="row">이메일<span class="point" title="필수항목"></span></th>
								<td>
									<input type="text" class="input_text w_mid" name="MEMBER_EMAIL" maxlength="20">
								</td>
							</tr>
							<tr>
								<th scope="row">메뉴권한</th>
								<td colspan="3">
								<label class="input_check">
									<input type="checkbox"  name="menu_auth_arr" value="ACCOUNT_USE" checked>
									<span class="label_text">계정관리</span>
								</label>
								<label class="input_check">
									<input type="checkbox"  name="menu_auth_arr" value="THANKS_USE" checked>
									<span class="label_text">고마워요미소금융</span>
								</label>
								<label class="input_check">
									<input type="checkbox"  name="menu_auth_arr" value="NEWS_USE" checked>
									<span class="label_text">계정관리</span>
								</label>뉴스
								<label class="input_check">
									<input type="checkbox"  name="menu_auth_arr" value="FINAN_USE" checked>
									<span class="label_text">재정보고</span>
								</label>
								</td>
							</tr>
							<tr>
								<th scope="row txt_m">메모</th>
								<td colspan="3">
									<div class="memo_area">
										<textarea name="EXPLANATION" style="resize: none;"></textarea>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>	
			</form>
			
			<div class="btn_area sp30">
				<div class="center_a">
					<button type="button" class="btn btn_blue btn_l" data-btn="save">완료</button>
					<button type="button" class="btn btn_white btn_l pop_btn btn_cancel" data-btn="cancel">취소</button>
				</div>
			</div>
		</div>
		<!--//content-->

	</div>
	<!-- //하단 -->

</div>
<!-- //Wrap-->

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/consult/footer.jsp" %>
<!-- footer -->

<script>
//비밀번호 체크
$("form[name=frm] input[name=MEMBER_PW], #MEMBER_PW_CHK").on("keyup", function () {
	if($("form[name=frm] input[name=MEMBER_PW]").val() == $("#MEMBER_PW_CHK").val()) {
		$("[data-form=memberPwChkForm]").removeClass("ck_no").addClass("ck_ok");
		$("[data-text=memberPwChkText]").removeClass("pw_ck_no").addClass("pw_ck_ok");
		$("[data-text=memberPwChkText]").text("비밀번호일치");
	} else {
		$("[data-form=memberPwChkForm]").removeClass("ck_ok").addClass("ck_no");
		$("[data-text=memberPwChkText]").removeClass("pw_ck_ok").addClass("pw_ck_no");
		$("[data-text=memberPwChkText]").text("불일치");
	}
	if(checkPassword($("form[name=frm] input[name=MEMBER_PW]").val())){
		$("[data-form=memberPwForm]").removeClass("ck_no").addClass("ck_ok");
		$("[data-text=memberPwText]").removeClass("pw_ck_no").addClass("pw_ck_ok");
		$("[data-text=memberPwText]").text("사용가능");
	}else{
		$("[data-form=memberPwForm]").removeClass("ck_ok").addClass("ck_no");
		$("[data-text=memberPwText]").removeClass("pw_ck_ok").addClass("pw_ck_no");
		$("[data-text=memberPwText]").text("사용불가");
	}
});

// 아이디체크
var falgIdChk = false;
$("form[name=frm] input[name=MEMBER_ID]").on("change", function () {
	// 필드 변경되면 아이디체크
	falgIdChk = false;
});
$("[data-btn=idDupCheck]").on("click", function () {
	var id = $("form[name=frm] input[name=MEMBER_ID]").val();
	if(!checkId(id)) {
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_ID]"), "${validationJoinIdPattern}");
		return;
	} else {
		$.ajax({
			type:"GET"
			,url: "/consult/member/consultMemberIdDupCheck"
			,data: $("form[name=frm]").serialize()
			,dataType:'json' 
			,success : function(responseData){
				var data = responseData.resultJson;
				openPopAlert(data.rMsg);
				if(data.rCode == "0000") {
					falgIdChk = true;
				} else {
					falgIdChk = false;
				}
			}
			,error : function(xhr,status,error) {
				if(xhr.status == 401) {
					openPopAlert("${errorXhrStatus401}");
					location.href = "/";
				} else if(xhr.status == 403) {
					openPopAlert("${errorXhrStatus403}");
					location.href = "/";
				} else if(xhr.status == 500) {
					openPopAlert("${errorXhrStatus500}");
				} else {
					openPopAlert("${errorXhrStatusElse}");
					location.href = "/";
				}
			},
		});
	}
});

//저장
$("[data-btn=save]").on("click", function() {
	openPopConfirmAlert("${messageSaveConfirm}", memberInsert, null);
});

// 취소
$("[data-btn=cancel]").on("click", function() {
	openPopConfirmAlert("${messageSaveCancelConfirm}", memberList, null);
});

// 등록
function memberInsert(){
	if(isEmpty($("form[name=frm] input[name=MEMBER_ID]").val())){
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_ID]"), "${messageValidationEmpty_MEMBER_ID}");
		return;
	}
	if(isEmpty($("form[name=frm] input[name=MEMBER_NAME]").val())){
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_NAME]"), "${messageValidationEmpty_MEMBER_NAME}");
		return;
	}
	if(isEmpty($("form[name=frm] input[name=MEMBER_PW]").val())){
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_PW]"), "${messageValidationEmpty_MEMBER_PW}");
		return;
	}
	if(isEmpty($("form[name=frm] select[name=MEMBER_STATUS]").val())){
		openPopAlert("${messageValidationEmpty_MEMBER_STATUS}");
		return;
	}
	if(isEmpty($("form[name=frm] select[name=MEMBER_AUTHORITY]").val())){
		openPopAlert("${messageValidationEmpty_MEMBER_AUTHORITY}");
		return;
	}
	if(isEmpty($("form[name=frm] input[name=MEMBER_PHONE]").val())){
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_PHONE]"), "${messageValidationEmpty_MEMBER_PHONE}");
		return;
	}
	if(isEmpty($("form[name=frm] input[name=MEMBER_EMAIL]").val())){
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_EMAIL]"), "${messageValidationEmpty_MEMBER_EMAIL}");
		return;
	}
	
	// 2. 아이디 확인
	if(!checkId($("form[name=frm] input[name=MEMBER_ID]").val())) {
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_ID]"), "${validationJoinIdPattern}");
		return;
	}
	
	if(!falgIdChk) {
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_ID]"), "${validationJoinIdDupCheck}");
		return;
	}
	
	// 3. 비밀번호 확인
	if(!checkPwd()) {
		openPopAlertFocus($("form[name=frm] input[name=MEMBER_PW]"), "${validationJoinPwdPattern}");
		return;
	}
	
	// 전송시 로딩바노출
    $("body").addClass("loading");
	
	$.ajax({
		type:"POST"
		, url: "/consult/member/consultMemberInsert"
		, data: $("form[name=frm]").serialize()
		, dataType:"json"
		, success : function(responseData){
			var data = responseData.resultJson;
			if(data.rCode == "0000") {
				openPopAlertAction("${messageSaveSuccess}", memberList, null);
			} else {
				openPopAlertAction(data.rMsg);
			}
		}
		,error : function(xhr,status,error) {
			if(xhr.status == 401) {
				openPopAlert("${errorXhrStatus401}");
				location.href = "/";
			} else if(xhr.status == 403) {
				openPopAlert("${errorXhrStatus403}");
				location.href = "/";
			} else if(xhr.status == 500) {
				openPopAlert("${errorXhrStatus500}");
			} else {
				openPopAlert("${errorXhrStatusElse}");
				location.href = "/";
			}
		},
	});
}

//비밀번호 체크 
function checkPwd() {
	var pwd = $("form[name=frm] input[name=MEMBER_PW]").val();
	
	if(pwd != $('#MEMBER_PW_CHK').val()) {
		$("[data-form=memberPwChkForm]").removeClass("ck_ok").addClass("ck_no");
		$("[data-text=memberPwChkText]").removeClass("pw_ck_ok").addClass("pw_ck_no");
		$("[data-text=memberPwChkText]").text("불일치");
		return false;
	} else {
		$("[data-form=memberPwChkForm]").removeClass("ck_no").addClass("ck_ok");
		$("[data-text=memberPwChkText]").removeClass("pw_ck_no").addClass("pw_ck_ok");
		$("[data-text=memberPwChkText]").text("비밀번호일치");
	}
	
	//정규식 체크
	if(!checkPassword(pwd)){
		$("[data-form=memberPwForm]").removeClass("ck_ok").addClass("ck_no");
		$("[data-text=memberPwText]").removeClass("pw_ck_ok").addClass("pw_ck_no");
		$("[data-text=memberPwText]").text("사용불가");
		openPopAlertFocus($("input[name=MEMBER_PW]"), "${validationJoinPwdPattern}");
		return false;
	}

	return true;
}

// 목록가기
function memberList(){
	$("form[name=frm]").attr("action", "/consult/member/consultMemberList");
	$("form[name=frm]").submit();
}
</script>

</body>
</html>