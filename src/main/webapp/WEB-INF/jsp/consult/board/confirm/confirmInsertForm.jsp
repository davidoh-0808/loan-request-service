<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>


<script src="/resources/common/js/jquery.form.min.js"></script>
<script src="/resources/ckeditor/ckeditor.js"></script>

<!-- 프로퍼티메세지 -->
<c:set var="messageSaveConfirm"><spring:message code="message.insert.confirm"/></c:set>
<c:set var="messageSaveCancelConfirm"><spring:message code="message.insert.cancel.confirm"/></c:set>
<c:set var="messageSaveSuccess"><spring:message code="message.insert.success"/></c:set>

<c:set var="messageValidationEmpty_TITLE"><spring:message code="validation.empty.input" arguments="제목을"/></c:set>
<c:set var="messageValidationEmpty_CONTENT"><spring:message code="validation.empty.input" arguments="내용을"/></c:set>

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

        <!-- lnb_지점/심사팀 -->
        <%@ include file="/WEB-INF/jsp/common/include/consult/lnb.jsp" %>
        <!-- // lnb -->

        <!--content-->
        <div class="content">
            <h2 class="con_tit">승인현황 관리</h2>
            <h3 class="s_tit">승인현황 등록</h3>

            <div class="tb_style tb_htype sp10">
                <form name="frm_editor">

                    <!-- csrf 토큰을 실어주어야 ajax post form 이 정상적으로 가동된다 -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
                    <input type="hidden" name="CONTENT"/>

                    <table>
                        <caption>대표번호 승인현황 등록</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">제목</th>
                            <td>
                                <input name="TITLE" type="text" class="input_text w_100" title="제목입력" maxlength="50">
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">내용</th>
                            <td>
                                <div class="memo_area w_100">
                                    <textarea textarea id="ckeditor_write" style="resize: none;"></textarea>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>

            <div class="btn_area sp30">
                <div class="right_a">
                    <button type="button" class="btn btn_white btn_l pop_btn btn_cancel" data-btn="btnCancel">취소
                    </button>
                    <button type="button" class="btn btn_blue btn_l" data-btn="btnSave">등록</button>
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

    // 에디터저장 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CKeditor
    CKEDITOR.replace('ckeditor_write', {
        height: 130, //editor height 높이 지정
        resize_enabled: false //editor 크기조정 삭제
    });

    //입력폼 리프레쉬
    function confirmInsertForm() {
        $("form[name=frm_editor]").attr("action", "/consult/board/confirm/confirmInsertForm");
    }

    //목록가기
    function confirmList() {
        $("form[name=frm_editor]").attr("action", "/consult/board/confirm/confirmList");
        $("form[name=frm_editor] input[name=CONTENT]").val("");
        $("form[name=frm_editor]").submit();
    }

    //저장
    $("[data-btn=btnSave]").on("click", function () {
        openPopConfirmAlert("${messageSaveConfirm}", confirmInsert);
    });

    // 취소
    $("[data-btn=btnCancel]").on("click", function () {
        openPopConfirmAlert("${messageSaveCancelConfirm}", confirmList);
    });


    // 등록 버튼 클릭 시
    function confirmInsert() {

        // 1-1) 승인현황 등록 전 유효성검사 먼저 - 제목
        if (isEmpty($("form[name=frm_editor] input[name=TITLE]").val())) {
            openPopAlertFocus($("form[name=frm_editor] input[name=TITLE]"), "${messageValidationEmpty_TITLE}");
            return;
        }


        // 1-2) 승인현황 등록 전 유효성검사 먼저 - CKeditor
        var txt = CKEDITOR.instances.ckeditor_write.getData();
        if (isEmpty(txt)) {
            openPopAlertFocus($("#ckeditor_write"), "${messageValidationEmpty_CONTENT}");
            return;
        }
        $("form[name=frm_editor] input[name=CONTENT]").val(txt);


        // 2) 유효성 검사 후, 저장 확인 dialog 띄우기
        $('.alert_msg').dialog({
            dialogClass: 'pop_alert',
            showAlertMessage: '취소하시겠습니까?',
            buttons: [
                {
                    text: "확인",
                    class: "btn btn_m btn_blue",
                    click: function () {
                        $(this).dialog("close");
                    }
                },
                {
                    text: "취소",
                    class: "btn btn_m btn_white",
                    click: function () {
                        // confirmList();
                    }
                }
            ]
        });

        // 3) 폼 submit
        $("form[name=frm_editor]").ajaxSubmit({
            type: "POST"
            , url: "/consult/board/confirm/confirmInsert"
            , dataType: "json"
            , success: function (responseData) {

                var data = responseData.resultJson;
                if (data.rCode == "0000") {
                    openPopAlertAction("${messageSaveSuccess}", confirmList, null);
                } else {
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

    // 등록 끝


</script>
</body>
</html>