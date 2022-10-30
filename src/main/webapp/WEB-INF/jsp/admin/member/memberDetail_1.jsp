<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/admin/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/script.jsp" %>

	<!-- 프로퍼티메세지 -->
	<c:set var="messageSaveConfirm"><spring:message code="message.delete.confirm"/></c:set>
	<c:set var="messageSaveSuccess"><spring:message code="message.delete.success"/></c:set>
	<!-- 프로퍼티메세지 -->

</head>


<body>
<!--wrap -->
<div class="admin_wrap">

	<!-- header -->
	<%@ include file="/WEB-INF/jsp/common/include/admin/header.jsp" %>
	<!-- header -->
	
	<!-- 관리자 히단 -->
	<div class="admin_bottom">
	
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/common/include/admin/lnb.jsp" %>
		<!-- // lnb -->
		
		<!--content-->
		<div class="content">
			<h2 class="con_tit">계정 및 권한 관리</h2>
			<h3 class="s_tit">계정 조회</h3>

			<div class="tb_style tb_htype sp10">
				<table>
					<caption>계정 수정</caption>
					<colgroup>
						<col style="width: 142px;">
						<col style="width: auto;">
						<col style="width: 142px;">
						<col style="width: auto;">
					</colgroup>
					<tbody>
						<tr>
							<th scope="row">아이디</th>
							<td>${result.MEMBER_ID }</td>
							<th scope="row">이름</th>
							<td>${result.MEMBER_NAME }</td>
						</tr>
						<tr>
							<th scope="row">상태</th>
							<td>${result.MEMBER_STATUS_NAME }</td>
							<th scope="row">권한</th>
							<td>${result.MEMBER_AUTHORITY_NAME }</td>
						</tr>
						<tr>
							<th scope="row">전화번호</th>
							<td>${result.MEMBER_PHONE }</td>
							<th scope="row">이메일</th>
							<td>${result.MEMBER_EMAIL }</td>
						</tr>
						<tr>
							<th scope="row">메뉴권한</th>
							<td colspan="3">${result.MENU_AUTH }</td>
						</tr>
						<tr>
							<th scope="row">메모</th>
							<td colspan="3">
								<div class="memo_con">${result.EXPLANATION }</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>	

			<div class="btn_area sp30">
				<div class="left_a">
					<button type="button" class="btn btn_gray btn_l" data-btn="list">목록</button>
				</div>
				<div class="center_a">
					<button type="button" class="btn btn_blue btn_l" data-btn="update">수정</button>
					<button type="button" class="btn btn_white btn_l pop_btn btn_delete" data-btn="delete">삭제</button>
				</div>
			</div>

		</div>
		<!--//content-->
	</div>
	<!-- //하단 -->

</div>
<!-- //Wrap-->

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/admin/footer.jsp" %>
<!-- footer -->

<form name="frm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
	<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
	<input type="hidden" name="searchValue" value="${paramVO.searchValue }" />
	<input type="hidden" name="MEMBER_CODE" value="${result.MEMBER_CODE }" />
</form>

<script>
$('input:checkbox[name="menu_auth_arr"]').each(function() {
	if(this.value == "1"){ //값 비교
		this.checked = true; //checked 처리
	}
});


//목록페이지 이동
$("[data-btn=list]").on("click", function() {
	memberList();
});
function memberList(){
	$("form[name=frm]").attr("action", "/admin/member/memberList");
	$("form[name=frm]").submit();
}
//수정 페이지 이동
$("[data-btn=update]").on("click", function() {
	$("form[name=frm]").attr("action", "/admin/member/memberUpdateForm");
	$("form[name=frm]").submit();	
});
// 삭제
$("[data-btn=delete]").on("click", function() {
	openPopConfirmAlert("${messageSaveConfirm}", memberDelete);
});
function memberDelete(){
	$.ajax({
		type:"POST"
		, url: "/admin/member/memberDelete"
		, data: $("form[name=frm]").serialize()
		, dataType:"json"
		, success : function(responseData){
			var data = responseData.resultJson;
			if(data.rCode == "0000") {
				openPopAlertAction("${messageSaveSuccess}", memberList, null);
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