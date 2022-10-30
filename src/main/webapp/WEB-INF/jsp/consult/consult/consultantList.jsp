<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

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
                <h2 class="con_tit">상담원</h2>
				<div class="tb_style tb_htype sp10">
                <!-- 조회를 위한 form -->
                <form name="frm">
                    <!-- csrf 및 페이징 -->
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
                    <input type="hidden" name="orderValue" value="${paramVO.orderValue}">
                    <input type="hidden" name="orderName" value="${paramVO.orderName}">
                    <input type="hidden" name="pageNum" value="${paramVO.pageNum }"/>
                    <input type="hidden" name="MEMBER_CODE" value="${paramVO.MEMBER_CODE}"/>
                    <!-- csrf 및 페이징 -->

                    <div class="tb_style tb_htype sp10">
                        <table>
                            <caption>상담원 리스트 검색</caption>
                            <colgroup>
                                <col style="width: 138px;">
                                <col style="width: auto;">
                                <col style="width: 138px;">
                                <col style="width: auto;">
                            </colgroup>
                            <tbody>
	                            <tr>
	                                <th scope="row">권한</th>
	                                <td>
                                        <select id="memberAuthority" name="memberAuthority" class="selbox w_100">
                                            <option value="">전체</option>
                                            <c:forEach var="code" items="${AUTHORITY}">
                                                <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
                                            </c:forEach>
                                        </select>
	                                </td>
	                                <th scope="row">상태</th>
	                                <td>
	                                    <select id="workStatus" name="workStatus" class="selbox w_100">
	                                        <option value="">전체</option>
	                                        <c:forEach items="${MEMBER_WORK_STATUS}" var="code">
	                                            <option value="${code.MASTER_CODE}">${code.CODE_NAME}</option>
	                                        </c:forEach>
	                                    </select>
	                                </td>
	                            </tr>
	                            <tr>
	                                <th scope="row">사용여부</th>
	                                <td colspan="3">
	                                    <div class="selbox_form">
	                                        <select id="inUse" name="inUse" class="selbox">
	                                            <option value="">전체</option>
	                                            <option value="Y">사용</option>
	                                            <option value="N">미사용</option>
	                                        </select>
	                                    </div>
	                                </td>
	                            </tr>
                            </tbody>
                        </table>
                    </div>

                </form>
                <!-- 조회를 위한 form -->
				</div>
                <div class="btn_area sp10">
                    <div class="right_a">
                        <button type="button" class="btn btn_white btn_m" data-btn="btnInit">초기화</button>
                        <button type="button" class="btn btn_black btn_m" data-btn="btnSearch">검색</button>
                    </div>
                </div>

                <div class="info sp40 cellbox">
                    <div class="left cell auto bt">
                        <p class="total">총 <span>${totCnt}</span>건</p>
                    </div>
                    <div class="right cell">
                        <button type="button" class="btn btn_blue btn_m" data-btn="btnInsert">등록</button>
                    </div>
                </div>

                <div class="tb_style tb_vtype sp10 table_scroll">
                    <table>
                        <caption>상담원 리스트</caption>
                        <colgroup>
                            <col style="width: 48px;">
                            <col style="width: 120px;">
                            <col style="width: 80px;">
                            <col style="width: 260px;">
                            <col style="width: 200px;">
                            <col style="width: 100px;">
                            <col style="width: 90px;">
                            <col style="width: 90px;">
                            <col style="width: 110px;">
                            <col style="width: 90px;">
                        </colgroup>
                        <thead>
                        <tr>
<%--                            <th scope="col">NO</th>--%>
<%--                            <th scope="col"><span class="sort">아이디<a id="MEMBER_ID" href="#" class="icon_sort"></a></span></th>--%>
<%--                            <th scope="col"><span class="sort">성명<a id="MEMBER_NAME" href="#" class="icon_sort"></a></span></th>--%>
<%--                            <th scope="col"><span class="sort">이메일<a id="MEMBER_EMAIL" href="#" class="icon_sort"></a></span></th>--%>
<%--                            <th scope="col"><span class="sort">권한<a id="MEMBER_AUTHORITY" href="#" class="icon_sort"></a></span></th>--%>
<%--                            <th scope="col"><span class="sort">상태<a id="MEMBER_STATUS" href="#" class="icon_sort"></a></span></th>--%>
<%--                            <th scope="col"><span class="sort">최근접속IP<a id="CONN_IP" href="#" class="icon_sort"></a></span></th>--%>
<%--                            <th scope="col"><span class="sort">최근접속일<a id="LAST_LOGIN_DTTM" href="#" class="icon_sort"></a></span></th>--%>
<%--                            <th scope="col"><span class="sort">등록일<a id="IN_DTTM" href="#" class="icon_sort"></a></span></th>--%>
<%--                            <th scope="col"><span class="sort">수정일<a id="UP_USER" href="#" class="icon_sort"></a></span></th>--%>
                            <th scope="col">NO</th>
                            <th scope="col">아이디<a id="MEMBER_ID" href="#"></a></th>
                            <th scope="col">성명<a id="MEMBER_NAME" href="#"></a></th>
                            <th scope="col">이메일<a id="MEMBER_EMAIL" href="#"></a></th>
                            <th scope="col">권한<a id="MEMBER_AUTHORITY" href="#"></a></th>
                            <th scope="col">상태<a id="MEMBER_STATUS" href="#"></a></th>
                            <th scope="col">최근접속IP<a id="CONN_IP" href="#"></a></th>
                            <th scope="col">최근접속일<a id="LAST_LOGIN_DTTM" href="#"></a></th>
                            <th scope="col">등록일<a id="IN_DTTM" href="#"></a></th>
                            <th scope="col">수정일<a id="UP_USER" href="#"></a></th>
                        </tr>
                        <tbody>
                        </thead>
                            <c:choose>
                                <c:when test="${empty resultList}">
                                    <tr>
										<td class="empty" colspan="100%">
											<div class="empty_list">
												<p>검색 결과가 존재하지 않습니다.</p>
											</div>
										</td>
									</tr>
                                </c:when>
                                <c:otherwise>
                                    <c:forEach var="result" items="${resultList}">
                                        <tr data-btn="detailInfo" data-detailCode="${result.MEMBER_CODE}">
                                            <td>${result.ROW_NUM}</td>
                                            <td>${result.MEMBER_ID}</td>
                                            <td>${result.MEMBER_NAME}</td>
                                            <td>${result.MEMBER_EMAIL}</td>
                                            <td>${result.MEMBER_AUTHORITY_NAME} &nbsp <c:if test="${ result.BRANCH_NAME != null }">|</c:if> &nbsp ${result.BRANCH_NAME}</td>
                                            <td><span  class="c_red">${result.MEMBER_WORK_STATUS_NAME}</span></td>
                                            <td>${result.CONN_IP}</td>
                                            <td><fmt:formatDate value="${result.LAST_LOGIN_DTTM}" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>
                                            <%--<td><fmt:formatDate value="${result.IN_DTTM}" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>--%>
                                            <td>${result.IN_DTTM}</td>
                                            <%--<td><fmt:formatDate value="${result.UP_DTTM}" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>--%>
                                            <td>${result.UP_DTTM}</td>
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

<script>
    // ********************************* 컬럼필터 ORDER BY *********************************
    // 컬럼필터 클릭 시, 폼에 orderValue 와 orderName (asc, desc 체크 후) 입력하여 submit
    // $(".icon_sort").click(function() {
    //     let clickCol = $(this).attr('id');
    //     let orderByAscDesc = $(this).hasClass("up") ? "ASC" : "DESC";
    //
    //     alert("clicked clickCol : " + clickCol + " clicked orderByAscDesc: " + orderByAscDesc);
    //
    //     if(orderByAscDesc == "ASC"){
    //         $(".icon_sort").removeClass("up");
    //     }else if(orderByAscDesc == "DESC"){
    //         $(".icon_sort").removeClass("up");
    //         $(this).addClass("up");
    //     }
    //
    //     $("form[name=frm] input[name=orderValue]").val(clickCol);
    //     $("form[name=frm] input[name=orderName]").val(orderByAscDesc);
    //     goPage(1);
    // });

    function goPage(pageNum) {
        $("form[name=frm] input[name=pageNum]").val(pageNum);
        $("form[name=frm]").attr("method", "GET");
        $("form[name=frm]").attr("action", "/consult/consult/consultantList");
        $("form[name=frm]").submit();
    }

    // ********************************* 컬럼필터 ORDER BY *********************************


    //조회폼 초기화
    $("[data-btn=btnInit]").on("click", function () {
        $("form[name=frm] select[name=memberAuthority]").val("");
        $("form[name=frm] select[name=workStatus]").val("");
        $("form[name=frm] select[name=inUse]").val("");
        goPage(1);
    });

    // 조회
    $("[data-btn=btnSearch]").on("click", function () {
        goPage(1);
    })

    // 조회 후 search param 유지
    let memberAuthority = "${paramVO.memberAuthority}";
    let workStatus = "${paramVO.workStatus}";
    let inUse = "${paramVO.inUse}";
    if(isNotEmpty( memberAuthority )) {
        $("#memberAuthority").val(memberAuthority);
    }
    if(isNotEmpty( workStatus )) {
        $("#workStatus").val(workStatus);
    }
    if(isNotEmpty( inUse )) {
        $("#inUse").val(inUse);
    }

    //상세페이지이동
    $("[data-btn=detailInfo]").on("click", function () {
        $("form[name=frm] input[name=MEMBER_CODE]").val($(this).attr("data-detailCode"));
        $("form[name=frm]").attr("method", "GET");
        $("form[name=frm]").attr("action", "/consult/consult/consultantDetail");
        $("form[name=frm]").submit();
    });


    //등록
    $("[data-btn=btnInsert]").on("click", function() {
        //일단 전체 출력 - 추후에 조회결과만 출력 되도록 해보기
        $("form[name=frm]").attr("method", "GET");
        $("form[name=frm]").attr("action", "/consult/consult/consultantInsertForm");
        $("form[name=frm]").submit();
    })

</script>

</html>