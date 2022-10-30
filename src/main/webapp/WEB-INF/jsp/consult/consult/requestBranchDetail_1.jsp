<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>

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

        <!-- 유저 권한 (지점, 심사팀)에 따른 데이터 디스플레이 분류-->
        <c:set var="MEMBER_AUTHORITY" value="${MEMBER_AUTHORITY}"/>
        <!-- 유저 : 지점(상담원) -->
			<!--content-->
			<div class="content">
				<h2 class="con_tit">신청내역 조회</h2>
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
								<%--<td><fmt:formatDate value="${result.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>--%>
								<td>${result.IN_DTTM}</td>
							</tr>
							<tr>
								<th scope="row">지점</th>
								<td>${result.BRANCH_NAME}</td>
								<th scope="row">담당자</th>
								<td>${result.BRANCH_MB_NAME}</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="info sp40 cellbox">
					<div class="left cell auto">
						<h3 class="s_tit">신청고객 정보</h3>
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
								<td>${result.CUST_NM}</td>
								<th scope="row">주민등록번호</th>
								<td>${result.CUST_REGI_NO_FM}</td>
							</tr>
							<tr>
								<th scope="row">연락처</th>
								<td>${result.CUST_HP_NO_FM}</td>
								<th scope="row">유입경로</th>
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
									<td colspan="3">${result.CUST_CORP_NO_FM}</td>
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
									<th>자택주소</th>
									<td colspan="3">${result.HOME_ADDR1} ${result.HOME_ADDR2}</td>
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
									<td colspan="3"><fmt:formatDate value="${result.JOIN_DATE}" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>
								</tr>
								<tr>
									<th scope="row">직업</th>
									<td colspan="3">${result.JOB_TYPE_NAME}</td>
								</tr>
								<tr>
									<c:if test="${result.TYPE_CODE eq 'MC0000800001' 
												or result.TYPE_CODE eq 'MC0000800002' 
												or result.TYPE_CODE eq 'MC0000800003'}">
									<th class="address">자택주소</th>
									</c:if>
									<c:if test="${result.TYPE_CODE ne 'MC0000800001' 
												or result.TYPE_CODE ne 'MC0000800002' 
												or result.TYPE_CODE ne 'MC0000800003'}">
									<th class="address">사업자주소</th>
									</c:if>
									<td colspan="3">${result.CORP_ADDR1} ${result.CORP_ADDR2}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div> -->
				<!-- //직장정보 -->
				</c:if>
				<h3 class="s_tit sp40">최종 결과 정보</h3>
				<div class="tb_style tb_htype sp10">
					<table>
						<caption>최종 결과 정보</caption>
						<colgroup>
							<col style="width: 138px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">진행상태</th>
								<td>
									<span class="c_red">${result.STATS_NAME}</span>
								</td>
							</tr>
							<tr>
								<th scope="row">결과</th>
								<td>${result.BRANCH_RESULT_NAME}</td>
							</tr>
							<tr>
								<th scope="row">사유</th>
								<td>${CANCEL_REASON_NAME}</td>
							</tr>
							<tr>
								<th scope="row">비고</th>
								<td>
									<div class="view_area">
										<pre>${result.NOTE}</pre>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div class="btn_area sp50">
					<div class="right_a">
						<!-- <button type="button" class="btn btn_blue btn_l" data-btn="btnUpdate">수정</button> -->
						<button type="button" class="btn btn_white btn_l" data-btn="btnList">목록</button>
					</div>
				</div>
			</div>
			<!--//content-->
        <!-- 유저 : 지점(상담원) -->

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

    //수정 페이지 이동
    $("[data-btn=btnUpdate]").on("click", function () {
		$("form[name=frm]").attr("method", "POST");
        $("form[name=frm]").attr("action", "/consult/consult/requestUpdateForm");
        $("form[name=frm]").submit();

    });

    //목록페이지 이동
    $("[data-btn=btnList]").on("click", function () {
    	$("form[name=frm]").attr("method", "POST");
        $("form[name=frm]").attr("action", "/consult/consult/requestList");
        $("form[name=frm]").submit();
    });
</script>
</body>

</html>