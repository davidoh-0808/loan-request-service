<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/admin/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/script.jsp" %>


<script src="/resources/common/js/jquery.form.min.js"></script>
<script src="/resources/ckeditor/ckeditor.js"></script>

<!-- 프로퍼티메세지 -->
<c:set var="messageSaveConfirm"><spring:message code="message.delete.confirm"/></c:set>
<c:set var="messageSaveSuccess"><spring:message code="message.delete.success"/></c:set>
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
            <h2 class="con_tit">대표번호 승인현황 조회</h2>

            <div class="tb_style tb_htype sp10">

                <form name="frm_editor">
                    <table>
                        <caption>대표번호 승인현황 등록</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">작성자</th>
                            <td>${result.IN_USER_NAME}</td>
                            <th scope="row">등록일</th>
                            <td>
                               <fmt:formatDate value="${result.IN_DTTM}" pattern="${SPRING_MVC_FORMAT_DATE}"/>
                                <%-- ${result.IN_DTTM} --%>
                            </td>
                            <th scope="row">조회수</th>
                            <td>${result.VIEW_CNT}</td>
                        </tr>
                        <tr>
                            <th scope="row">제목</th>
                            <td colspan="5">
                                <div class="input_text w_100">
                                    ${result.TITLE}
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">내용</th>
                            <td colspan="5">
                                <div class="memo_area w_100">
                                    <p name="" id="" style="resize: none;">

                                        <c:set var="CONTENT" value="${result.CONTENT}"/>
                                        <c:if test="${not empty CONTENT}">
                                            ${result.CONTENT}
                                        </c:if>

                                    </p>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>

            </div>

            <div class="btn_area sp30">
                <div class="left_a">
                    <button type="button" class="btn btn_gray btn_l" data-btn="btnDelete">삭제</button>
                </div>
                <div class="right_a">
                    <button type="button" class="btn btn_white btn_l pop_btn btn_cancel" data-btn="btnUpdate">수정
                    </button>
                    <button type="button" class="btn btn_blue btn_l" data-btn="btnList">목록</button>
                </div>
            </div>

        </div>
        <!--//content-->

    </div>
    <!-- //관리자 하단 -->

</div>
<!-- //Wrap-->


<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/member/footer.jsp" %>
<!-- footer -->

<form name="frm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
    <input type="hidden" name="searchValue" value="${paramVO.searchValue }"/>
    <input type="hidden" name="pageNum" value="${paramVO.pageNum }"/>
    <input type="hidden" name="BOARD_CODE" value="${paramVO.BOARD_CODE }"/>
</form>

<script>
    // CKeditor
    CKEDITOR.replace('ckeditor_write', {
        height: 130, //editor height 높이 지정
        resize_enabled: false //editor 크기조정 삭제
    });

    //목록가기
    function confirmList() {
        $("form[name=frm]").attr("action", "/consult/board/confirm/confirmList");
        $("form[name=frm]").submit();
    }

    //수정 페이지 이동
    $("[data-btn=btnUpdate]").on("click", function () {
        $("form[name=frm]").attr("action", "/consult/board/confirm/confirmUpdateForm");
        $("form[name=frm]").submit();

    });

    //목록페이지 이동
    $("[data-btn=btnList]").on("click", function () {
        confirmList();
    });

    //삭제
    $("[data-btn=btnDelete]").on("click", function () {
        openPopConfirmAlert("${messageSaveConfirm}", confirmDelete, null);
    });


    function confirmDelete() {
        $(function () {
            //팝업
            $(".btn_cancel").on("click", function () {

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
                                $(this).dialog("close");
                            }
                        }
                    ]
                });

            });

            $.ajax({
                type: "POST"
                , url: "/consult/board/confirm/confirmDelete"
                , data: $("form[name=frm]").serialize()
                , dataType: "json"
                , success: function (responseData) {

                    console.log("*********************** success ***********************");
                    var data = responseData.resultJson;
                    if (data.rCode == "0000") {
                        openPopAlertAction("${messageSaveSuccess}", confirmList, null);
                    } else {
                        openPopAlertAction(data.rMsg);
                    }
                }
                , error: function (xhr, status, error) {

                    console.log("*********************** fail ***********************");
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
            })
        })
    }

</script>
</body>
</html>
