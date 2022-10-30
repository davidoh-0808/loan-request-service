<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

<!-- 프로퍼티 메시지 -->
<c:set var="messageSaveCancelConfirm"><spring:message code="message.insert.cancel.confirm"/></c:set>
<c:set var="messageForgotIdDupCheck"><spring:message code="validation.join.idDupCheck"/></c:set>
<c:set var="validationPasswordReg"><spring:message code="validation.password.reg"/></c:set>
<c:set var="validationPasswordNotMatched"><spring:message code="validation.password.notMatched"/></c:set>
<c:set var="messageForgotRequiredFields"><spring:message code="validation.empty.required"/></c:set>
<c:set var="messageValidationEmpty_MEMBER_ID"><spring:message code="validation.empty.input" arguments="아이디를"/></c:set>
<c:set var="messageSaveSuccess"><spring:message code="message.insert.success"/></c:set>

</head>

<body>
<!--wrap -->
<div class="admin_wrap">
	<!-- 관리자 상단 -->
	<%@ include file="/WEB-INF/jsp/common/include/consult/header.jsp" %>
	<!-- // 관리자 상단 -->

	<!-- 관리자 히단 -->
	<div class="admin_bottom">

		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/common/include/consult/lnb.jsp" %>
		<!-- // lnb -->

		<!--content-->
		<div class="content">
			<h2 class="con_tit">계정/권한등록</h2>
			<h3 class="s_tit"></h3>
			<div class="tb_style tb_htype sp10">

				<!-- IdDupCheck, Insert 하기 위한 폼 -->
				<form name="frm">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />

					<table>
						<caption>계정/권한등록</caption>
						<colgroup>
							<col style="width: 138px;">
							<col style="width: auto;">
							<col style="width: 138px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
						<tr>
							<th scope="row"><span class="point">성명</span></th>
							<td colspan="3">
								<input name="MEMBER_NAME" type="text" class="input_text form_md" maxlength="25" oninput="maxLengthCheck(this)">
							</td>
						</tr>
						<tr>
							<th scope="row"><span class="point">아이디</span></th>
							<td colspan="3">
								<div class="id_form">
									<input name="MEMBER_ID" id="consultantId" type="text" class="input_text form_md" maxlength="15" oninput="maxLengthCheck(this)">
									<%-- type=button 을 해주어야 버튼 클릭시 자동 form submit 이 일어나지 않음 --%>
									<button type="button" class="btn btn_black btn_sm ml5" data-btn="btnIdDupCheck">중복확인</button>
								</div>
								<div class="warning_msg">

									<p id="idDupCheckSuccess" class="c_blue" hidden>&dot; 사용 가능한 아이디입니다.</p>

									<p id="idDupCheckFail" class="c_red" hidden>&dot; 이미 사용 중이거나 차단된 아이디입니다
									</p>

								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><span class="point">비밀번호</span></th>
							<td>
								<div class="pw_form">
									<input name="MEMBER_PW" id="consultantPw1" type="password" class="input_text w_100" maxlength="25" onblur="checkPwValidation(this)" oninput="maxLengthCheck(this)" autocomplete="on">
								</div>

								<!--
                                <div class="warning_msg">
                                    <p class="c_red">&dot; 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.</p>
                                </div>
                                -->

							</td>
							<th scope="row"><span class="point">비밀번호 재입력</span></th>
							<td>
								<div class="pw_form">
									<input id="consultantPw2" type="password" class="input_text w_100" maxlength="25" oninput="maxLengthCheck(this)" autocomplete="on">
								</div>

								<div class="warning_msg">
									<p id="pwMatchSuccess" class="c_blue" hidden>&dot; 비밀번호 일치</p>

									<p id="pwMatchFail" class="c_red" hidden>&dot; 비밀번호가 일치하지 않습니다.</p>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row"><span class="point">권한</span></th>
							<td>
								<select name="MEMBER_AUTHORITY" class="selbox">
									<option value="">선택</option>
									<option value="MC0002300003">심사팀관리자</option>
									<option value="MC0002300004">시스템관리자</option>
								</select>
							</td>
							<th scope="row"><span class="point">상태</span></th>
							<td>
								<select name="MEMBER_STATUS" class="selbox">
									<option value="">선택</option>
									<c:forEach var="memberStatus" items="${memberStatuses}">
										<option value="${memberStatus.MASTER_CODE}">${memberStatus.CODE_NAME}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="row"><span class="point">연락처</span></th>
							<td><input name="MEMBER_PHONE" id="consultantPhone" type="text" class="input_text w_100" maxlength="11" oninput="maxLengthCheck(this)"></td>
							<th scope="row"><span class="point">이메일</span></th>
							<td><input name="MEMBER_EMAIL" id="consultantEmail" type="text" class="input_text w_100" maxlength="50" oninput="maxLengthCheck(this)"></td>
						</tr>
						<tr>
							<th scope="row">비고</th>
							<td colspan="3">
								<div class="memo_area ht w_100">
									<textarea name="EXPLANATION" id="consultantExp" style="resize: none;"></textarea>
								</div>
							</td>
						</tr>
						</tbody>
					</table>

				</form>
				<!-- IdDupCheck , Insert 하기 위한 폼 -->


				<div class="btn_area sp60">
					<div class="right_a">
						<button type="button" class="btn btn_outline btn_l" data-btn="btnCancel">취소</button>
						<button type="button" class="btn btn_blue btn_l" data-btn="btnInsert">등록</button>
					</div>
				</div>

			</div>
		</div>
		<!--//content-->

	</div>
	<!-- //관리자 하단 -->

</div>
<!-- //Wrap-->

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/consult/footer.jsp" %>
<!-- footer -->

<script>
	// ************************ insert 전 체크할 변수들 ************************
	var idDupCheck = false;
	var pwMatchCheck = false;
	var pwValidationCheck = false;
	var requiredFieldsCheck = false;
	// ************************ insert 전 체크할 변수들 ************************



	// ************************ ID 중복 체크 ************************
	$("[data-btn=btnIdDupCheck]").on("click", function () {
		//alert("****************** bntIdDupCheck clicked ******************");
		if (isEmpty( $("#consultantId").val() )) {
			//메시지 div 초기화
			$("#idDupCheckFail").attr("hidden", true);
			$("#idDupCheckSuccess").attr("hidden", true);
			//아이디 입력 안하면 메시지 dialog 띄우기
			openPopAlert("${messageValidationEmpty_MEMBER_ID}");
		} else {
			//메시지 div 초기화
			$("#idDupCheckFail").attr("hidden", true);
			$("#idDupCheckSuccess").attr("hidden", true);
			consultantIdDupCheck();
		}
	});
	function consultantIdDupCheck() {
		//alert("****************** consultantIdDupCheck() called ******************");
		$.ajax({
			type: "POST"
			, url: "/consult/system/memberIdDupCheck"
			, data: $("form[name=frm]").serialize()
			, dataType: "json"
			, success: function (responseData) {
				//alert("****************** memberIdDupCheck() success *******************");
				var data = responseData.resultJson;
				if (data.rCode == "0000") {
					idDupCheck = true;
					$("#idDupCheckFail").attr("hidden", true);
					$("#idDupCheckSuccess").removeAttr("hidden");
				} else {
					idDupCheck = false;
					$("#idDupCheckSuccess").attr("hidden", true);
					$("#idDupCheckFail").removeAttr("hidden");
				}
			}
			, error: function (xhr, status, error) {
				//alert("****************** memberIdDupCheck() failure *******************");
				if (xhr.status == 401) {
					openPopAlert("${errorXhr401}");
				} else if (xhr.status == 403) {
					openPopAlert("${errorXhr403}");
				} else if (xhr.status == 500) {
					openPopAlert("${errorXhr500}");
				} else {
					openPopAlert("${errorXhrElse}");
				}
			}
		});
	}
	// ************************ ID 중복 체크 ************************



	// ************************ PW 중복체크 ************************
	$("#consultantPw1, #consultantPw2").change('keyup', function () {
		//메시지 div 초기화
		$("#pwMatchSuccess").attr("hidden", true);
		$("#pwMatchFail").attr("hidden", true);

		pwMatch();
	});
	function pwMatch() {
		//패스워드 란들이 모두 빈 칸이 아닐때만 매칭 실행
		if( isEmpty($("#consultantPw1").val()) && isEmpty($("#consultantPw2").val()) ) {
			//메시지 div 초기화
			//console.log("both pw inputs are blank");
			$("#pwMatchSuccess").attr("hidden", true);
		} else if( $("#consultantPw1").val() !== $("#consultantPw2").val() ) {
			//console.log("passwords not matched");
			pwMatchCheck = false;
			$("#pwMatchFail").removeAttr("hidden");
		} else {
			//console.log("passwords matched");
			pwMatchCheck = true;
			$("#pwMatchSuccess").removeAttr("hidden");
		}
	}
	// ************************ PW 중복체크 ************************


	//************************ 비밀번호 정합성 체크 ************************
	function checkPwValidation(pwInput) {
		//최소 8자리, 영문 대,소문자, 특수문자
		var regex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
		pwValidationCheck = regex.test( pwInput.value );
		if( pwValidationCheck === true ) {
			$("#pwValidationFail").attr("hidden", true);
		} else {
			$("#pwValidationFail").removeAttr("hidden");
		}
	}
	//************************ 비밀번호 정합성 체크 ************************


	// ************************ 심사팀관리자 , 시스템관리자 등록 ************************
	$("[data-btn=btnInsert]").on("click", function() {
		//다음 체크사항들이 true가 아니라면 리턴하여 insert 진행 취소 (아이디중복 확인, 패스워드 매칭, 필수사항란 빈칸체크)
		if( !idDupCheck ) {
			openPopAlert("${messageForgotIdDupCheck}");
			return;
		}

		if( !pwValidationCheck ) {
			openPopAlert("${validationPasswordReg}");
			return
		}

		if( !pwMatchCheck ) {
			openPopAlert("${validationPasswordNotMatched}");
			return;
		}

		checkRequiredFields();
		if( !requiredFieldsCheck ) {
			openPopAlert("${messageForgotRequiredFields}");
			return;
		} else {
			//필수사항 모두 입력시, insert 진행
			consultantInsert();
		}

	});
	//필수항목란 빈칸체크
	function checkRequiredFields() {
		//필수사항란 빈칸 체크 후 boolean flag 설정, 빈칸 있을 시엔 insert 취소
		requiredFieldsCheck = false;

		if( isEmpty($("form[name=frm] input[name=MEMBER_NAME]").val()) ) {
			openPopAlertFocus("form[name=frm] input[name=MEMBER_NAME]", "${messageForgotRequiredFields}");
			return;
		}

		if( isEmpty($("form[name=frm] input[name=MEMBER_ID]").val()) ) {
			openPopAlertFocus("form[name=frm] input[name=MEMBER_ID]", "${messageForgotRequiredFields}");
			return;
		}

		if( isEmpty($("form[name=frm] input[name=MEMBER_PW]").val()) ) {
			openPopAlertFocus("form[name=frm] input[name=MEMBER_PW]", "${messageForgotRequiredFields}");
			return;
		}

		if( isEmpty($("form[name=frm] select[name=MEMBER_AUTHORITY]").val()) ) {
			openPopAlertFocus("form[name=frm] select[name=MEMBER_AUTHORITY]", "${messageForgotRequiredFields}");
			return;
		}

		if( isEmpty($("form[name=frm] select[name=MEMBER_STATUS]").val()) ) {
			openPopAlertFocus("form[name=frm] select[name=MEMBER_STATUS]", "${messageForgotRequiredFields}");
			return;
		}

		if( isEmpty($("form[name=frm] input[name=MEMBER_PHONE]").val()) ) {
			openPopAlertFocus("form[name=frm] input[name=MEMBER_PHONE]", "${messageForgotRequiredFields}");
			return;
		}

		if( isEmpty($("form[name=frm] input[name=MEMBER_EMAIL]").val()) ) {
			openPopAlertFocus("form[name=frm] input[name=MEMBER_EMAIL]", "${messageForgotRequiredFields}");
			return;
		}

		//필수사항 필드 모두 입력 시 플래그 true 로 설정
		requiredFieldsCheck = true;
	}
	// id 중복, pw 매칭, 필수사항기입 모두 확인 후, insert 진행
	function consultantInsert() {
		
		// 전송시 로딩바노출
        $("body").addClass("loading");
		
		//alert("consultantInsert() called .. 등록 AJAX 호출 전")
		$.ajax({
			type: "POST"
			, url: "/consult/system/memberInsert"
			, data: $("form[name=frm]").serialize()
			, dataType: "json"
			, success: function (responseData) {
				var data = responseData.resultJson;
				if (data.rCode == "0000") {
					// 0000 성공코드 받았을 시, 등록이 완료 되었습니다 메시지 창
					openPopAlertAction("${messageSaveSuccess}", consultantList, null);
				} else {
					// 0000 외의 실패코드 및 메시지 받았을 시, 메시지창에 실패 메시지
					openPopAlertAction(data.rMsg);
				}
			}
			, error: function (xhr, status, error) {
				if (xhr.status == 401) {
					openPopAlert("${errorXhr401}");
				} else if (xhr.status == 403) {
					openPopAlert("${errorXhr403}");
				} else if (xhr.status == 500) {
					openPopAlert("${errorXhr500}");
				} else {
					openPopAlert("${errorXhrElse}");
				}
			},
		});

	}
	// ************************ 심사팀관리자 , 시스템관리자 등록 ************************



	//************************ 등록취소 버튼 ************************
	$("[data-btn=btnCancel]").on("click", function() {
		openPopConfirmAlert("${messageSaveCancelConfirm}", consultantList, null);
	});
	function consultantList() {
		$("form[name=frm]").attr("action", "/consult/system/memberList");
		$("form[name=frm]").submit();
	}
	//************************ 등록취소 버튼 ************************



	//************************  input 의 자릿수 설정  ************************
	function maxLengthCheck(object) {
		if (object.value.length > object.max.length){
			object.value = object.value.slice(0, object.maxLength);
		}
	}
	//************************  input 의 자릿수 설정  ************************


	//toDO : 특수문자 regex validation

</script>

</body>
</html>
