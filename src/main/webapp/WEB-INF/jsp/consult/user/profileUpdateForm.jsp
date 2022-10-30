<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

</head>

<!-- 프로퍼티 메시지 -->
<c:set var="validationPasswordNotMatched"><spring:message code="validation.password.notMatched"/></c:set>
<c:set var="validationPasswordReg"><spring:message code="validation.password.reg"/></c:set>
<c:set var="validationEmptyCheck"><spring:message code="validation.empty.check"/></c:set>
<c:set var="messageUpdatePasswordSuccess"><spring:message code="message.update.password.success"/></c:set>
<c:set var="messageUpdateSuccess"><spring:message code="message.update.success"/></c:set>

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
            <h2 class="con_tit">프로필 설정</h2>
            <h3 class="s_tit">정보변경</h3>

            <!-- Update 하기 위한 폼 -->
            <form name="frm">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
                <input type="hidden" name="MEMBER_CODE" value="${result.MEMBER_CODE}" />

                <div class="tb_style tb_htype sp10">
                    <table>
                        <caption>정보변경</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">새 비밀번호</th>
                            <td>
                                <input id="pw1" name="MEMBER_PW" type="password" class="input_text w_100" placeholder="비밀번호 변경이 필요 시 입력하세요." minlength="8" maxlength="16" autocomplete="on">
                            </td>
                            <th scope="row">새 비밀번호 확인</th>
                            <td>
                                <input id="pw2" type="password" class="input_text w_100" placeholder="비밀번호를 다시 한 번 입력하세요." minlength="8" maxlength="16" autocomplete="on">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <p class="pwMsg">*8~16자 영문 대소문자,숫자,특수문자를 사용하세요.</p>
                </div>

                <div class="tb_style tb_htype sp20">
                    <table>
                        <caption>정보변경</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">연락처</th>
                            <td>
                                <input id="phone" name="MEMBER_PHONE" type="text" class="input_text w_100" value="${result.MEMBER_PHONE}" maxlength="15" onBlur='addDashes(this)'>
                            </td>
                            <th scope="row">이메일</th>
                            <td>
                                <input type="hidden" id="emailDomain" value="@hyundaismile.or.kr" />
                                <input id="email" name="MEMBER_EMAIL" type="text" class="input_text w_50" placeholder="" value="${result.MEMBER_EMAIL}">
                                <span class="ml5">@hyundaismile.or.kr</span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </form>
            <!-- Update 하기 위한 폼 -->

            <div class="btn_area sp60">
                <div class="right_a">
                    <button type="button" class="btn btn_outline btn_l" data-btn="btnCancel">취소</button>
                    <button type="button" class="btn btn_blue btn_l" data-btn="btnComplete">완료</button>
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
    //****************** update 전 체크할 변수들 ******************
    var pwValidationCheck = false;
    var pwMatchCheck = false;
    var phoneCheck = false;
    var emailCheck = false;
    //****************** update 전 체크할 변수들 ******************
    //******************************** update 클릭 ********************************
    $("[data-btn=btnComplete]").on("click", function() {
        //패스워드 칸이 둘다 blank 가 아닐시, 체크
        if( !isEmpty($("#pw1").val()) && !isEmpty($("#pw2").val()) ) {
            //유효성 체크
            checkPwValidation();
            if( !pwValidationCheck ) {
                openPopAlert("${validationPasswordReg}");
                return;
            }
            //재입력 체크
            checkPwMatch();
            if( !pwMatchCheck ) {
                openPopAlert("${validationPasswordNotMatched}");
                return;
            }
        } else {
            openPopAlert("${validationPasswordNotMatched}");
            return;
        }
        //연락처 칸이 blank 가 아닐시, 체크
        if( !isEmpty($("#phone").val()) ) {
            checkPhoneValidation();
            if( !phoneCheck ) {
                openPopAlert("${validationEmptyCheck}");
                return;
            }
        }
        //이메일 칸이 blank 가 아닐시, 체크
        if( !isEmpty($("#email").val()) ) {
            checkEmailValidation();
            if( !emailCheck ) {
                openPopAlert("${validationEmptyCheck}");
                return;
            }
        }
        //위의 함수로 조건들 충족시, update Ajax 실행
        updateSystemMember();
    });

    // ******* 비밀번호 비교 체크 *******
    function checkPwMatch() {
        let pw1 = $("#pw1").val();
        let pw2 = $("#pw2").val();
        //패스워드가 동일하고 빈칸이 아닐경우, 체크확인
        if( pw1 == pw2 && !(pw1 == '' && pw2 == '') ) {
            pwMatchCheck = true;
        }
    }

    // ******* 비밀번호 정합성 체크 *******
    function checkPwValidation() {
        //최소 8자리, 영문 대,소문자, 특수문자
        var regex = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{8,16}$/;
        pwValidationCheck = regex.test( $("#pw1").val() );
    }

    // ******* 연락처 정합성 체크 *******
    function checkPhoneValidation() {
        //풀 maxlength="15" 이 아니라면 false
        //console.log( $("#phone").val().length );
        if( $("#phone").val().length == 13 ) {
            phoneCheck = true;
        }
    }

    // ******* 연락처 정합성 체크 *******
    function checkEmailValidation() {
        //딱히 유효성 체크할 건이 없음?
        emailCheck = true;
    }

    // ******* update AJAX 실행 ********
    function updateSystemMember() {
        //form submit 전, 첫번째 email 주소와 두번째 email 주소 합쳐주기
        let emailFirstPart = $("#email").val();
        let emailDomain = $("#emailDomain").val();
        let fullEmail = emailFirstPart + emailDomain;
        $("#email").val(fullEmail);

        //update ajax 호출
        $.ajax({
            type: "POST"
            , url: "/consult/user/profileUpdate"
            , data: $("form[name=frm]").serialize()
            , dataType: "json"
            , success: function (responseData) {
                //alert("****************** 등록 AJAX 호출 success *******************");
                var data = responseData.resultJson;
                if (data.rCode == "0000") {
                    // 0000 성공코드 받았을 시, 등록이 완료 되었습니다 메시지 창
                    openPopAlertAction("${messageUpdateSuccess}", goToProfile);
                } else {
                    // 0000 외의 실패코드 및 메시지 받았을 시, 메시지창에 실패 메시지
                    openPopAlertAction(data.rMsg);
                }
            }
            , error: function (xhr, status, error) {
                alert("****************** 등록 AJAX 호출 failure *******************");
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
    //******************************** update 클릭 ********************************



    //*********************** cancel 클릭 ***********************
    $("[data-btn=btnCancel]").on("click", function() {
        goToProfile();
    });
    function goToProfile() {
        $("form[name=frm]").attr("action", "/consult/user/profile");
        $("form[name=frm]").submit();
    }
    //*********************** cancel 클릭 ***********************


    //****************** 휴대폰번호 non-number input 제외 , 대시 더해주기 ******************
    function addDashes(input) {
        input.value = input.value.replace(/[^0-9]/g, '');
        input.value = input.value.slice(0,3)+"-"+input.value.slice(3,7)+"-"+input.value.slice(7,11);
    }
    //****************** 휴대폰번호 non-number input 제외 , 대시 더해주기 ******************

</script>

</body>
</html>