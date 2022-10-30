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

		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/common/include/consult/lnb.jsp" %>
		<!-- // lnb -->

		<!--content-->
		<div class="content">
			<h2 class="con_tit">계정/권한조회</h2>
			<h3 class="s_tit"></h3>
			<div class="tb_style tb_htype sp10">
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
						<th scope="row">성명</th>
						<td>${result.MEMBER_NAME}</td>
						<th scope="row">등록일</th>
						<%--<td><fmt:formatDate value="${result.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>--%>
						<td>${result.IN_DTTM}</td>
					</tr>
					<tr>
						<th scope="row">아이디</th>
						<td>${result.MEMBER_ID}</td>
						<th scope="row">수정일</th>
						<%--<td><fmt:formatDate value="${result.UP_DTTM}" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>--%>
						<td>${result.UP_DTTM}</td>
					</tr>
					<tr>
						<th scope="row">권한</th>
						<td>${result.MEMBER_AUTHORITY_NAME}
							<c:if test="${result.BRANCH_NAME != null}">
								&nbsp/&nbsp ${result.BRANCH_NAME}
							</c:if></td>
						<th scope="row">상태</th>
						<td>${result.MEMBER_STATUS_NAME}</td>
					</tr>
					<tr>
						<th scope="row">연락처</th>
						<td>${result.MEMBER_PHONE}</td>
						<th scope="row">이메일</th>
						<td>${result.MEMBER_EMAIL}</td>
					</tr>
					</tbody>
					<tr>
						<th scope="row">비고</th>
						<td colspan="3">
							<div class="view_area">
                                <pre>${result.EXPLANATION}</pre>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<div class="btn_area sp60">
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

<!-- UpdateForm 에 해당 지점 , 심사팀 유저의 멤버코드 값을 보내기 위함 -->
<form name="frm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
	<input type="hidden" name="MEMBER_CODE" value="${result.MEMBER_CODE}"/>
</form>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/consult/footer.jsp" %>
<!-- footer -->

<script>
	//수정 페이지 이동
	$("[data-btn=btnUpdate]").on("click", function () {
		$("form[name=frm]").attr("action", "/consult/system/memberUpdateForm");
		$("form[name=frm]").submit();

	});

	//목록페이지 이동
	$("[data-btn=btnList]").on("click", function () {
		systemMemberList();
	});
	//계정/권한관리 리스트 이동
	function systemMemberList() {
		$("form[name=frm]").attr("action", "/consult/system/memberList");
		$("form[name=frm]").submit();
	}

	//멤버 삭제
	$("[data-btn=btnDelete]").on("click", function () {
		openPopConfirmAlert("${messageDeleteConfirm}", systemMemberDelete);
	})
	function systemMemberDelete() {
		$.ajax({
			type:"POST"
			, url: "/consult/system/memberDelete"
			, data: $("form[name=frm]").serialize()
			, dataType:"json"
			, success : function(responseData){
				var data = responseData.resultJson;
				if(data.rCode == "0000") {
					openPopAlertAction("${messageDeleteSuccess}", systemMemberList(), null);
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