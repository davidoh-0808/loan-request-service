<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

<!-- 프로퍼티메세지 -->
<c:set var="messageDeleteConfirm"><spring:message code="message.delete.confirm"/></c:set>
<c:set var="messageDeleteSuccess"><spring:message code="message.delete.success"/></c:set>
<!-- 프로퍼티메세지 -->

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
        <div class="admin_bottom">

            <!--content-->
            <div class="content">
                <h2 class="con_tit">상담리스트 조회</h2>
                <h3 class="s_tit">상담 정보</h3>
                <div class="tb_style tb_htype sp10">
                    <table>
                        <caption>상담 정보</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
							<tr>
								<th scope="row">상품명</th>
								<td>${result.PRODUCT_NAME}</td>
								<th scope="row">접수일</th>
								<td>${result.IN_DTTM}</td>
							</tr>
							<tr>
								<th scope="row">지점</th>
								<td>${result.BRANCH_NAME}</td>
								<th scope="row">게시자</th>
	                            <c:if test="${'GUEST' eq result.IN_USER}">
	                           		<td>고객등록</td>
	                            </c:if>
	                            <c:if test="${'GUEST' ne result.IN_USER}">
		                            <td>${result.REGISTRAR}</td>
	                            </c:if>
							</tr>
							<tr>
								<th scope="row">상담자</th></th>
								<td>${result.CONS_MB_NAME}</td>
								<th scope="row">상담일</th>
								<td>${result.CONS_DTTM}</td>
							</tr> 
                        </tbody>
                    </table>
                </div>

                <div class="info sp40 cellbox">
                    <div class="left cell auto">
                        <h3 class="s_tit">신청고객 정보</h3>
                    </div>
                    <!-- NICE 약관동의 확인 후 적용 예정 -->
                    <div class="right cell">
                        <button id="btnVeriInfo" type="button" class="btn btn_white btn_m">고객정보 조회</button>
                    </div>

                    <%--  고객정보 조회 팝업  --%>
                    <div id="pop_info" class="pop_wrap" title="고객정보 조회">
                        <div class="pop_contents">
                            <div class="tb_style tb_vtype">
                                <table>
                                    <caption>고객정보 조회</caption>
                                    <colgroup>
                                        <col style="width: 10%;">
                                        <col style="width: 18%;">
                                        <col style="width: 8%;">
                                        <col style="width: 12%;">
                                        <col style="width: 18%;">
                                        <col style="width: 14%;">
                                        <col style="width: 21%;">
                                    </colgroup>
                                    <thead>
                                    <tr>
                                        <th>성명</th>
                                        <th>주민등록번호</th>
                                        <th>성별</th>
                                        <th>내/외국인</th>
                                        <th>사업자번호</th>
                                        <th>인증수단</th>
                                        <th>약관 및 이용동의 여부</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr>
                                        <td>${result.CUST_NM}</td>
                                        <td>${result.CUST_REGI_NO_FM}</td>
                                        <td>${result.GENDER}</td>
                                        <td>${result.FRN_CHK}</td>
                                        <td>${result.CUST_CORP_NO}</td>
                                        <td>${result.CERTI_MTHD_TP_NAME}</td>
                                            <td>
                                                <c:if test="${not empty result.CERTI_DTTM} ">
                                                    <span class="c_red">동의</span>
                                                </c:if>
                                            </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
                <div class="tb_style tb_htype sp10">
                    <table>
                        <caption>신청고객 정보</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
	                        <tr>
	                            <th scope="row">고객명</th>
	                            <td colspan="3">${result.CUST_NM}</td>
	                        </tr>
	                        <tr>
	                            <th scope="row">연락처</th>
	                            <td id="formatPhone">${result.CUST_HP_NO_FM}</td>
	                            <th scope="row">주민등록번호</th>
	                            <td id="formatBirthDate">${result.CUST_REGI_NO_FM}</td>
	                        </tr>
	                        <tr>
	                            <th scope="row">유입경로(1)</th>
	                            <td>${result.INFLOW_NAME2}</td>
	                            <th scope="row">유입경로(2)</th>
	                            <td>${result.INFLOW_NAME1}</td>
	                        </tr>
                        </tbody>
                    </table>
                </div>

                <h3 class="s_tit sp40">사업자 등록여부</h3>
                <div class="tb_style tb_htype sp10 registration_table">
                    <table>
                        <caption>사업자 등록여부 테이블</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">사업자 등록</th>
                            <td>${result.CUST_TYPE_NAME}</td>
                            <c:if test="${result.CUST_TYPE ne 'MC0001500001'}">
                                <th scope="row" class="optionBox">취약계층 여부</th>
                                <td class="optionBox">${result.VULN_CLASS_NAME}</td>
                            </c:if>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <c:if test="${result.CUST_TYPE eq 'MC0001500001'}">
                    <!-- 사업자정보(개인사업자) -->
                    <div class="business_info">
                        <div class="info sp40 cellbox">
                            <div class="left cell auto">
                                <h3 class="s_tit">사업자 정보</h3>
                            </div>
                        </div>
                        <div class="tb_style tb_htype sp10">
                            <table>
                                <caption>사업자 정보</caption>
                                <colgroup>
                                    <col style="width: 138px;">
                                    <col style="width: auto;">
                                    <col style="width: 138px;">
                                    <col style="width: auto;">
                                </colgroup>
                                <tbody>
	                                <tr>
	                                    <th scope="row">사업자 번호</th>
	                                    <td colspan="3">${result.CUST_CORP_NO}</td>
	                                </tr>
	                                <tr>
	                                    <th scope="row">사업자 등록일</th>
	                                    <td>${result.CORP_REGI_DT}</td>
	                                    <th scope="row">사업년수</th>
	                                    <td>${result.CORP_HIS}</td>
	                                </tr>
	                                <tr>
	                                    <th scope="row">업종</th>
	                                    <td colspan="3">${result.TYPE_NAME}</td>
	                                </tr>
	                                <tr>
	                                    <c:if test="${result.TYPE_CODE eq 'MC0000800001' or result.TYPE_CODE eq 'MC0000800002' or result.TYPE_CODE eq 'MC0000800003'}">
	                                        <th>자택주소</th>
	                                    </c:if>
	                                    <c:if test="${result.TYPE_CODE ne 'MC0000800001' and result.TYPE_CODE ne 'MC0000800002' and result.TYPE_CODE ne 'MC0000800003'}">
	                                        <th>사업자주소</th>
	                                    </c:if>
	                                    <td colspan="3">${result.CUST_CORP_ADDR1} ${result.CUST_CORP_ADDR2}</td>
	                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- //사업자정보 -->
                </c:if>
                <c:if test="${result.CUST_TYPE ne 'MC0001500001'}">
                    <!-- 직장정보 -->
                    <div class="company_info">
                        <div class="info sp40 cellbox">
                            <div class="left cell auto">
                                <h3 class="s_tit">직장 정보</h3>
                            </div>
                        </div>
                        <div class="tb_style tb_htype sp10">
                            <table>
                                <caption>직장 정보 테이블</caption>
                                <colgroup>
                                    <col style="width: 138px;">
                                    <col style="width: auto;">
                                    <col style="width: 138px;">
                                    <col style="width: auto;">
                                </colgroup>
                                <tbody>
                                <tr>
                                    <th scope="row">직장명</th>
                                    <td colspan="3">${result.CORP_NM}</td>
                                </tr>
                                <tr>
                                    <th scope="row">입사일</th>
                                    <%--<td colspan="3"><fmt:formatDate value="${result.JOIN_DATE}" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>--%>
                                    <td colspan="3">${result.JOIN_DATE_FM}</td>
                                </tr>
                                <tr>
                                    <th scope="row">직업</th>
                                    <td colspan="3">${result.JOB_TYPE_NAME}</td>
                                </tr>
								<tr>
                                    <th>자택주소</th>
                                    <td colspan="3">${result.HOME_ADDR1} ${result.HOME_ADDR2}</td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- //직장정보 -->
                </c:if>

                <h3 class="s_tit sp40">추가 정보 입력</h3>
                <div class="tb_style tb_htype sp10">
                    <table>
                        <caption>최종 결과 정보</caption>
                        <colgroup>
                            <col style="width: 138px;">
                            <col style="width: auto;">
                            <col style="width: 138px;">
                            <col style="width: auto;">
                        </colgroup>
                        <tbody>
                        <tr>
                            <th scope="row">조회동의</th>
                            <td>${result.INQU_CONS}</td>
                            <th scope="row">녹취시간</th>
                            <td>${result.RECORD_TIME}</td>
                        </tr>
                        <tr>
                            <th scope="row">채무조정</th>
                            <td colspan="3">${result.DEBT_SETT}</td>
                        </tr>
                        <tr>
                            <th scope="row">처리현황</th>
                            <td>${result.STATS_NAME}</td>
                            <th scope="row">완료일</th>
                            <td>${result.COMP_DATE}</td>
                        </tr>
                        <tr>
                            <th scope="row">상담결과</th>
                            <td>${result.EVAL_RESULT_NAME}</td>
                            <th scope="row">거절사유</th>
                            <td>${result.DECLINE_REASON_NAME}</td>
                        </tr>
                        <tr>
                            <th scope="row">비고</th>
                            <td colspan="3">
                                <div class="view_area">
									<pre>${result.NOTE}</pre>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row">기타</th>
                            <td colspan="3">
                                <div class="view_area">
									<pre>${result.ETC}</pre>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>


                <div class="btn_area sp50">
                    <div class="left_a">
                        <button type="button" class="btn btn_outline btn_l" data-btn="btnDelete">삭제</button>
                    </div>
                    <div class="right_a">
                        <button type="button" class="btn btn_blue btn_l" data-btn="btnUpdate">수정</button>
                        <button type="button" class="btn btn_white btn_l" data-btn="btnList">목록</button>
                    </div>
                </div>
            </div>
            <!--//content-->

        </div>
        <!-- //관리자 하단 -->

    </div>
    <!-- //Wrap-->



    <!-- UpdateForm 에 해당 상담신청내역 의 CONS_SEQ 키 값을 보내기 위함 -->
    <form name="frm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
        <input type="hidden" name="CONS_SEQ" value="${result.CONS_SEQ}"/>
    </form>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/consult/footer.jsp" %>
<!-- footer -->

    <script>
        // 고객정보 확인 클릭 시 팝업
        $("#btnVeriInfo").on("click", function() {
            $('#pop_info').dialog({
                dialogClass: 'pop_modal info',
                width: 723,
            });
        });

        //수정 페이지 이동
        $("[data-btn=btnUpdate]").on("click", function () {
        	$("form[name=frm] input[name=pageNum]").val("1");
        	$("form[name=frm]").attr("method", "POST");
        	$("form[name=frm]").attr("action", "/consult/consult/consultUpdateForm");
            $("form[name=frm]").submit();
        });


        //목록페이지 이동
        $("[data-btn=btnList]").on("click", function () {
            consultList();
        });
        function consultList() {
            $("form[name=frm] input[name=pageNum]").val("1");
            $("form[name=frm]").attr("method", "POST");
            $("form[name=frm]").attr("action", "/consult/consult/consultList");
            $("form[name=frm]").submit();
        }


        // 상담내역 삭제
        $("[data-btn=btnDelete]").on("click", function () {
            openPopConfirmAlert("${messageDeleteConfirm}", consultDelete);
        })
        function consultDelete() {
            $.ajax({
                type:"POST"
                , url: "/consult/consult/consultDelete"
                , data: $("form[name=frm]").serialize()
                , dataType:"json"
                , success : function(responseData){
                    var data = responseData.resultJson;
                    if(data.rCode == "0000") {
                        openPopAlertAction("${messageDeleteSuccess}", consultList, null);
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
