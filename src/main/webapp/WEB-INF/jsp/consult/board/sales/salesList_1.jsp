<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

<head>
    <title>현대차미소금융재단</title>
</head>


<body>
<!--wrap -->
<div class="admin_wrap">
    <!-- 관리자 상단 -->
    <%@ include file="/WEB-INF/jsp/common/include/consult/header.jsp" %>
    <!-- // 관리자 상단 -->

    <!-- 관리자 히단 -->
    <div class="admin_bottom">
        <!-- lnb_지점/심사팀 -->
        <%@ include file="/WEB-INF/jsp/common/include/consult/lnb.jsp" %>
        <!-- // lnb -->
        <!--content-->
        <div class="content">
            <h2 class="con_tit">영업 현황</h2>

            <form name="frm">
                <!-- csrf 및 페이징 -->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
                <input type="hidden" name="pageNum" value="${paramVO.pageNum }"/>
                <input type="hidden" name="BOARD_CODE"/>
                <!-- csrf 및 페이징 -->

                <div class="tb_style tb_htype">
                    <table>
                        <caption>대표번호 승인 현황 검색 테이블</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">제목</th>
                            <td>
                                <input type="text" class="input_text w_100" title="제목입력" name="title"
                                       value="${paramVO.title}">
                            </td>
                            <th scope="row">작성자</th>
                            <td>
                                <input type="text" class="input_text w_100" title="작성자" name="author"
                                       value="${paramVO.author}">
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">등록일</th>
                            <td colspan="3">
                                <div class="cal_wrap">
                                    <input type="text" class="input_text w_100 form_md datepicker" name="searchDate"
                                           value="${paramVO.searchDate}">
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </form>

            <div class="btn_area sp10">
                <div class="right_a">
                    <button type="button" class="btn btn_white btn_m" data-btn="btnInit">초기화</button>
                    <button type="button" class="btn btn_black btn_m" data-btn="btnSearch">검색</button>
                </div>
            </div>

            <div class="info sp40 cellbox">
                <div class="left cell auto">
                    <p class="total">총 <span>${totCnt}</span>건</p>
                </div>
                <div class="right cell">
                    <button type="button" class="btn btn_blue btn_m" data-btn="btnInsert">등록</button>
                </div>
            </div>

            <div class="tb_style tb_vtype sp10">
                <table>
                    <caption>NEWS 관리 목록</caption>
                    <colgroup>
                        <col style="width: 60px;">
                        <col style="width: auto;">
                        <col style="width: 136px;">
                        <col style="width: 143px;">
                        <col style="width: 106px;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th scope="col">NO</th>
                        <th scope="col">제목</th>
                        <th scope="col">작성자</th>
                        <th scope="col">등록일</th>
                        <th scope="col">조회수</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:choose>
                        <c:when test="${empty resultList }">
                            <tr>
                                <td colspan="5">작성된 게시글이 없습니다.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${resultList}" var="list">
                                <tr data-btn="detailInfo" data-detailCode="${list.BOARD_CODE}">
                                    <td>${list.ROW_NUM }</td>
                                    <td class="txt_l">${list.TITLE }</td>
                                    <td>${list.IN_USER_NAME }</td>
                                    <td>
                                       <fmt:formatDate value="${list.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/>
                                        <%-- ${list.IN_DTTM} --%>
                                    </td>
                                    <td>${list.VIEW_CNT }</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                    </tbody>
                </table>
            </div>

            <!-- //페이징 -->
            <%@ include file="/WEB-INF/jsp/common/include/consult/paging.jsp" %>
            <!-- //페이징 -->
        </div>
        <!--//content-->
    </div>
    <!-- //관리자 하단 -->
</div>
<!-- //Wrap-->
</body>

<!-- 등록폼이동 -->
<form name="frm_form">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
</form>

<script>
    function goPage(pageNum) {
        $("form[name=frm] input[name=pageNum]").val(pageNum);
        $("form[name=frm]").attr("method", "GET");
        $("form[name=frm]").attr("action", "/consult/board/sales/salesList");
        $("form[name=frm]").submit();
    }


    // 등록하기
    $("[data-btn=btnInsert]").on("click", function () {
        $("form[name=frm_form]").attr("action", "/consult/board/sales/salesInsertForm");
        $("form[name=frm_form]").attr("method", "GET");
        $("form[name=frm_form]").submit();
    });


    //조회폼 초기화
    $("[data-btn=btnInit]").on("click", function () {
        $("form[name=frm] input[name=title]").val("");
        $("form[name=frm] input[name=author]").val("");
        $("form[name=frm] input[name=searchDate]").val("");
        goPage(1);
    });

    // 조회
    $("[data-btn=btnSearch]").on("click", function () {
        //작성자 (author) 값에 특수문자 체크
        checkVal = $("input[name=author]").val();
        var reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/gi;

        //toDO : 메시지 창 교체하기
        if (reg.test(checkVal)) {
            $("input[name=author]").val("");
            $("input[name=author]").attr("placeholder", "특수 문자를 제외하고 검색해주세요");
            alert("특수 문자를 제외하고 검색해주세요");

        }

        goPage(1);
    })

    //상세페이지이동
    $("[data-btn=detailInfo]").on("click", function () {
        $("form[name=frm] input[name=BOARD_CODE]").val($(this).attr("data-detailCode"));
        $("form[name=frm]").attr("method", "GET");
        $("form[name=frm]").attr("action", "/consult/board/sales/salesDetail");
        $("form[name=frm]").submit();
    });

    $("[data-btn=btnSearch]").on("click", function () {
        $("form[name=frm]");
    });

</script>

</html>
