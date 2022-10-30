<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>


<!-- 프로퍼티 메시지 -->
<c:set var="messageResetPasswordConfirm"><spring:message code="message.reset.password.confirm"/></c:set>
<c:set var="messageResetPasswordSuccess"><spring:message code="message.reset.password.success"/></c:set>
<c:set var="validationPasswordReg"><spring:message code="validation.password.reg"/></c:set>
<c:set var="validationPasswordNotMatched"><spring:message code="validation.password.notMatched"/></c:set>
<c:set var="messageUpdateCancelConfirm"><spring:message code="message.update.cancel.confirm"/></c:set>
<c:set var="messageUpdateSuccess"><spring:message code="message.update.success"/></c:set>
<c:set var="messageActivateConfirm"><spring:message code="message.activate.confirm"/></c:set>
<c:set var="messageActivateSuccess"><spring:message code="message.activate.success"/></c:set>
<c:set var="messageOtpConfirm"><spring:message code="message.otp.confirm"/></c:set>
<c:set var="messageOtpSuccess"><spring:message code="message.otp.success"/></c:set>
<c:set var="validationEmptyRequired"><spring:message code="validation.empty.required"/></c:set>
<c:set var="validationPasswordNotMatched"><spring:message code="validation.password.notMatched"/></c:set>

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
            <h2 class="con_tit">상담원 수정</h2>
            <div class="tb_style tb_htype sp10">

                <!-- Update 하기 위한 폼 -->
                <form name="frm" id="memberUpdateForm">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
                    <input type="hidden" name="MEMBER_CODE" value="${result.MEMBER_CODE}" />

                    <table>
                        <caption>상담원 등록</caption>
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
                                <input name="MEMBER_NAME" value="${result.MEMBER_NAME}" type="text" class="input_text form_md" maxlength="25" oninput="maxLengthCheck(this)">
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">아이디</th>
                            <td colspan="3">${result.MEMBER_ID}</td>
                        </tr>
                        <tr>
                            <th scope="row"><span>비밀번호</span></th>
                            <td>
                                <div class="pw_form">
                                    <input id="MEMBER_PW1" name="MEMBER_PW" type="password" class="input_text w_sm" maxlength="25" placeholder="Miso!@34" onblur="checkPwValidation(this)" oninput="maxLengthCheck(this)" autocomplete="on">
                                    <button class="btn btn_black btn_sm" data-btn="consultantPwReset">비밀번호 초기화</button>
                                </div>
                            </td>
                            <th scope="row"><span>비밀번호 재입력</span></th>
                            <td>
                                <div class="pw_form">
                                    <input id="MEMBER_PW2" type="password" class="input_text w_100" maxlength="25" placeholder="Miso!@34" oninput="maxLengthCheck(this)" autocomplete="on">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><span class="point">권한</span></th>
                            <td>
                                <div class="selbox_form sm">
                                    <select id="memberAuthoritySelection" name="MEMBER_AUTHORITY" class="selbox">
                                        <option value="">선택</option>
                                        <option value="MC0002300001" <c:if test="${'MC0002300001' eq result.MEMBER_AUTHORITY}">selected</c:if>>지점(상담원)</option>
                                        <option value="MC0002300002" <c:if test="${'MC0002300002' eq result.MEMBER_AUTHORITY}">selected</c:if>>심사팀</option>
                                    </select>
                                </div>
                                <div class="selbox_form sm ml5">
                                    <select id="branchSelection" name="BRANCH_CODE" class="selbox" <c:if test="${'MC0002300002' eq result.MEMBER_AUTHORITY}">disabled</c:if>>
                                        <option value="">선택</option>
                                        <c:forEach var="branch" items="${branches}">
                                            <option value="${branch.MASTER_CODE}" <c:if test="${branch.MASTER_CODE eq result.BRANCH_CODE}">selected</c:if>>${branch.CODE_NAME}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </td>
                            <th scope="row"><span class="point">상태</span></th>
                            <td>
                                <select id="workStatusSelection" name="MEMBER_WORK_STATUS" class="selbox">
                                    <option value="">선택</option>
                                    <c:forEach var="workStatus" items="${workStatuses}">
                                        <option value="${workStatus.MASTER_CODE}" <c:if test="${workStatus.MASTER_CODE eq result.MEMBER_WORK_STATUS}">selected</c:if>>${workStatus.CODE_NAME}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row"><span class="point">연락처</span></th>
                            <td><input id="consultantPhone" name="MEMBER_PHONE" value="${result.MEMBER_PHONE}" type="text" class="input_text w_100" maxlength="11" oninput="maxLengthCheck(this)" placeholder="- 를 제외한 숫자를 입력해주세요"></td>

                            <th scope="row"><span class="point">이메일</span></th>
                            <td><input name="MEMBER_EMAIL" value="${result.MEMBER_EMAIL}" type="text" class="input_text w_100" maxlength="30" oninput="maxLengthCheck(this)" placeholder="MisoFinance@hyundaismile.or.kr"></td>
                        </tr>
                        <tr>
                            <th scope="row">비고</th>
                            <td colspan="3">
                                <div class="memo_area ht w_100">
                                    <textarea id="consultantExp" name="EXPLANATION" style="resize: none;"></textarea>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
                <!-- Update 하기 위한 폼 -->

            </div>
            <div class="btn_area sp60">
                <div class="left_a">
                    <button type="button" class="btn btn_outline btn_l" data-btn="btnOtpReissue">OTP 초기화</button>
                    <c:if test="${result.MEMBER_WORK_STATUS eq 'MC0001300005'}">
                        <button type="button" class="btn btn_outline btn_l" data-btn="btnActivate">휴면해제</button>
                    </c:if>
                </div>
                <div class="right_a">
                    <button type="button" class="btn btn_outline btn_l" data-btn="btnCancel">취소</button>
                    <button type="button" class="btn btn_blue btn_l" data-btn="btnUpdate">수정</button>
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
    // ****************** start - 지점(상담원) , 심사팀 update ******************
    //update 전 체크할 변수들
    var pwMatched = false;
    var pwValidationCheck = false;
    var requiredFieldsCheck = false;

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

    $("[data-btn=btnUpdate]").on("click", function(e) {
        e.preventDefault();
        //다음 체크사항들이 true가 아니라면 리턴하여 insert 진행 취소 (패스워드 매칭, 필수사항란 빈칸체크)
        let pw1 = $("#MEMBER_PW1").val();
        let pw2 = $("#MEMBER_PW2").val();

        // 패스워드 정합성 체크
        /* if( !pwValidationCheck ) {
            openPopAlert("${validationPasswordReg}");
            return
        } */

        //패스워드 와 패스워드 확인이 동일하거나 빈칸일 경우
        if( pw1 == pw2 || (pw1 == '' && pw2 == '') ) {
            //console.log("password matched");
            pwMatched = true;
        }
        if( !pwMatched ) {
            openPopAlert("${validationPasswordNotMatched}");
            return;
        }


        //필수항목란 빈칸체크
        checkRequiredFields();
        if( !requiredFieldsCheck ) {
            openPopAlert("${messageForgotRequiredFields}");
            return;
        } else {
            //필수사항 모두 입력시, insert 진행
            consultantUpdate();
        }
    });
    //필수항목란 빈칸체크
    function checkRequiredFields() {
        //필수사항란 빈칸 체크 후 boolean flag 설정, 빈칸 있을 시엔 insert 취소

        if( isEmpty($("#memberUpdateForm input[name=MEMBER_NAME]").val()) ) {
            openPopAlertFocus($("#memberUpdateForm input[name=MEMBER_NAME]"), "${validationEmptyRequired}");
            return;
        }

        /* if( isEmpty($("#memberUpdateForm input[name=MEMBER_PW]").val()) ) {
            openPopAlertFocus($("#memberUpdateForm input[name=MEMBER_PW]"), "${validationEmptyRequired}");
            return;
        }

        if( isEmpty($("#memberUpdateForm input[id=MEMBER_PW1]").val()) ) {
            openPopAlertFocus($("#memberUpdateForm input[id=MEMBER_PW_CHECK]"), "${validationEmptyRequired}");
            return;
        } */

        if( isEmpty($("#memberUpdateForm select[name=MEMBER_AUTHORITY]").val()) ) {
            openPopAlertFocus($("#memberUpdateForm input[name=MEMBER_AUTHORITY]"), "${validationEmptyRequired}");
            return;
        }

        // 유저권한(MEMBER_AUTHORITY) 이 지점(상담원)일 경우 지점(BRANCH_CODE) 또한 유효성 검사 추가
        let memberAuthoritySelected = $("#memberUpdateForm select[name=MEMBER_AUTHORITY]").val();
        if( (memberAuthoritySelected !== '') && (memberAuthoritySelected == 'MC0002300001') ) {
            if( isEmpty($("#memberUpdateForm select[name=BRANCH_CODE]").val()) ) {
                openPopAlertFocus($("#memberUpdateForm input[name=BRANCH_CODE]"), "${validationEmptyRequired}");
                return;
            }
        }

        if( isEmpty($("#memberUpdateForm select[name=MEMBER_WORK_STATUS]").val()) ) {
            openPopAlertFocus($("#memberUpdateForm input[name=MEMBER_WORK_STATUS]"), "${validationEmptyRequired}");
            return;
        }

        if( isEmpty($("#memberUpdateForm input[name=MEMBER_PHONE]").val()) ) {
            openPopAlertFocus($("#memberUpdateForm input[name=MEMBER_PHONE]"), "${validationEmptyRequired}");
            return;
        }

        if( isEmpty($("#memberUpdateForm input[name=MEMBER_EMAIL]").val()) ) {
            openPopAlertFocus($("#memberUpdateForm input[name=MEMBER_EMAIL]"), "${validationEmptyRequired}");
            return;
        }

        //필수사항 필드 모두 입력 시 플래그 true 로 설정
        //console.log("required fields validation pass")
        requiredFieldsCheck = true;
    }
    // id 중복, pw 매칭, 필수사항기입 모두 확인 후, insert 진행
    function consultantUpdate() {
        //alert("consultantUpdate() called .. 수정 AJAX 호출 전")
        $.ajax({
            type: "POST"
            , url: "/consult/consult/consultantUpdate"
            , data: $("#memberUpdateForm").serialize()
            , dataType: "json"
            , success: function (responseData) {
                var data = responseData.resultJson;
                if (data.rCode == "0000") {
                    // 0000 성공코드 받았을 시, 등록이 완료 되었습니다 메시지 창
                    openPopAlertAction("${messageUpdateSuccess}", consultantList);
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

    function consultantList() {
        $("form[name=frm]").attr("action", "/consult/consult/consultantList");
        $("form[name=frm]").submit();
    }
    // ****************** end - 지점(상담원) , 심사팀 update ******************



    //************************ 비밀번호 초기화 버튼  ************************
    $("[data-btn=consultantPwReset]").on("click", function(e) {
        e.preventDefault(); // 폼 submit 을 방지
        openPopConfirmAlert("${messageResetPasswordConfirm}", consultantPwReset);
    });
    function consultantPwReset() {
        //$("#consultantPw1").attr("value", "Miso!@34");
        $.ajax({
            type: "POST"
            , url: "/consult/consult/consultantPwReset"
            , data: $("#memberUpdateForm").serialize()
            , dataType: "json"
            , success: function (responseData) {
                var data = responseData.resultJson;
                if (data.rCode == "0000") {
                    // 0000 성공코드 받았을 시, 등록이 완료 되었습니다 메시지 창
                    openPopAlertAction("${messageResetPasswordSuccess}", consultantList);
                } else {
                    // 0000 외의 실패코드 및 메시지 받았을 시, 메시지창에 실패 메시지
                    openPopAlertAction(data.rMsg);
                }
            }
            , error: function (xhr, status, error) {
                alert("****************** 패스워드 리셋 AJAX 호출 failure *******************");
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
    //************************ 비밀번호 초기화 버튼  ************************



    //************************ 등록취소 버튼  ************************
    $("[data-btn=btnCancel]").on("click", function(e) {
        openPopConfirmAlert("${messageUpdateCancelConfirm}", previousPage);
    });
    function previousPage() {
        history.back();
    }
    //************************ 등록취소 버튼  ************************



    //************************  input 의 자릿수 설정  ************************
    function maxLengthCheck(object) {
        if (object.value.length > object.max.length){
            object.value = object.value.slice(0, object.maxLength);
        }
    }
    //************************  input 의 자릿수 설정  ************************


    //************************ OTP 초기화 버튼 ***************************
    $("[data-btn=btnOtpReissue]").on("click", function( e ) {
        e.preventDefault(); // 폼 submit 을 방지
        openPopConfirmAlert("${messageOtpConfirm}", btnOtpReissue);
    });
    function btnOtpReissue() {
       // alert("btnOtpReissue clicked");
        $.ajax({
            type: "POST"
            , url: "/consult/member/otpReissue"
            , data: $("form[name=frm]").serialize()
            , dataType: "json"
            , success: function (responseData) {
                var data = responseData.resultJson;
                if (data.rCode == "0000") {
                    // 0000 성공코드 받았을 시, 등록이 완료 되었습니다 메시지 창
                    openPopAlertAction("${messageOtpSuccess}", consultantList);
                } else {
                    // 0000 외의 실패코드 및 메시지 받았을 시, 메시지창에 실패 메시지
                    openPopAlertAction(data.rMsg);
                }
            }
            , error: function (xhr, status, error) {
                alert("****************** 패스워드 리셋 AJAX 호출 failure *******************");
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
    // ************************ OTP 초기화 버튼 ***************************




    //************************  휴면계정 활성화  ************************
    $("[data-btn=btnActivate]").on("click", function() {
        openPopConfirmAlert("${messageActivateConfirm}", activate);
    });
    function activate() {
        $.ajax({
            type: "POST"
            , url: "/consult/member/consultMemberActivate"
            , data: $("#memberUpdateForm").serialize()
            , dataType: "json"
            , success: function (responseData) {
                console.log( responseData );
                var data = responseData.resultJson;
                if (data.rCode == "0000") {
                    // 0000 성공코드 받았을 시, 등록이 완료 되었습니다 메시지 창
                    openPopAlertAction("${messageActivateSuccess}", consultantList);
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
                    console.log(error);
                    openPopAlert("${errorXhrElse}");
                }
            },
        });

    }
    //************************  휴면계정 활성화   ************************



    //************************ select tag default 옵션 설정 ************************
    // 지점 , 심사팀 여부에 따른 지점선택 디스플레이
    $('#memberAuthoritySelection').change( function () {
        if( $(this).val() === 'MC0002300001' ) {
            $('#branchSelection').prop('disabled', false);
        } else {
            $('#branchSelection').prop('disabled', true);
            $('#branchSelection').val('');
        }
    });


</script>

</body>
</html>
