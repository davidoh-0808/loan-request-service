<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

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
			<h2 class="con_tit">계정 및 권한 관리</h2>

			<!-- 조회를 위한 form -->
			<form name="frm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
				<input type="hidden" name="MEMBER_CODE" value="${paramVO.MEMBER_CODE}" />

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
							<th scope="row">성명</th>
							<td>
								<input name="memberName" type="text" class="input_text w_100">
							</td>
							<th scope="row">권한</th>
							<td>
								<select name="memberAuthority" class="selbox w_100">
									<option value="">전체</option>
									<option value="MC0002300003">심사팀 관리자</option>
									<option value="MC0002300004">시스템 관리자</option>
								</select>
							</td>
						</tr>
						<tr>
							<th scope="row">상태</th>
							<td colspan="3">
								<div class="selbox_form">
									<select name="memberStatus" class="selbox">
										<option value="">전체</option>
										<c:forEach var="memberStatus" items="${memberStatuses}">
											<option value="${memberStatus.MASTER_CODE}">${memberStatus.CODE_NAME}</option>
										</c:forEach>
									</select>
								</div>
							</td>
						</tr>
						</tbody>
					</table>
				</div>

			</form>
			
			<div class="btn_area sp10">
				<div class="right_a">
					<a href="#" class="btn btn_white btn_m" data-btn="btnInit"><span>초기화</span></a>
					<a href="#" class="btn btn_black btn_m" data-btn="btnSearch"><span>조회</span></a>
				</div>
			</div>

			<div class="info sp40 cellbox">
				<div class="left cell auto">
					<h3 class="s_tit">목록</h3>
					<p class="total">총 <span><fmt:formatNumber value="${totCnt}" pattern="#,###" /></span>건</p>
				</div>		
				<div class="right cell">
					<a href="#" class="btn btn_blue btn_m" data-btn="insert"><span>등록</span></a>
				</div>
			</div>

			<c:choose>
				<c:when test="${empty resultList}">
					<div class="empty_list sp10">
						<p>해당 검색어로 검색된 결과가 없습니다.</p>
					</div>
				</c:when>
				<c:otherwise>
					<!-- 삭제 부분 : tr에 class del -->
					<div class="tb_style tb_vtype sp10 bg">
						<table>
							<caption>계정 및 권한 관리 목록</caption>
							<colgroup>
								<col style="width: 62px;">
								<col style="width: 110px;">
								<col style="width: 90px;">
								<col style="width: auto;">
								<col style="width: 98px;">
								<col style="width: 80px;">
								<col style="width: 120px;">
								<col style="width: 120px;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">NO</th>
									<th scope="col">아이디</th>
									<th scope="col">성명</th>
									<th scope="col">이메일</th>
									<th scope="col">권한</th>
									<th scope="col">상태</th>
									<th scope="col">등록일</th>
									<th scope="col">수정일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="result" items="${resultList}" varStatus="loop" >
									<tr data-btn="detailInfo" data-detailCode="${result.MEMBER_CODE}">
										<td>${loop.index + 1}</td>
										<td>${result.MEMBER_ID}</td>
										<td>${result.MEMBER_NAME}</td>
										<td>${result.MEMBER_EMAIL}</td>
										<td>${result.MEMBER_AUTHORITY_NAME}</td>
										<td>${result.MEMBER_WORK_STATUS_NAME}</td>
										<%--<td><fmt:formatDate value="${result.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>--%>
										<td>${result.IN_DTTM}</td>
										<%--<td><fmt:formatDate value="${result.UP_DTTM}" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>--%>
										<td>${result.UP_DTTM}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:otherwise>
			</c:choose>

			<!-- //페이징 -->
			<%@ include file="/WEB-INF/jsp/common/include/consult/paging.jsp" %>	
			<!-- //페이징 -->
		</div>
		<!--//content-->

	</div>
	<!-- //관리자 하단 -->

</div>
<!-- //Wrap-->

<!-- 등록폼이동 -->
<form name="frm_form">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
</form>

<script>

//조회폼 초기화
$("[data-btn=btnInit]").on("click", function () {
	$("form[name=frm] input[name=memberName]").val("");
	$("form[name=frm] select[name=memberAuthority]").val("");
	$("form[name=frm] select[name=memberStatus]").val("");
	goPage(1);
});

// 조회
$("form[name=frm] input[name=searchValue]").keyup(function(key){
	if(key.keyCode === 13) {
		goPage(1);
	}
});
$("[data-btn=btnSearch]").on("click", function() {
	goPage(1);
});

// 등록폼이동
$("[data-btn=insert]").on("click", function() {
	$("form[name=frm_form]").attr("action", "/consult/system/memberInsertForm");
	$("form[name=frm_form]").submit();
});

function goPage(pageNum){
	$("form[name=frm] input[name=pageNum]").val(pageNum);
	$("form[name=frm]").attr("action", "/consult/system/memberList")
	$("form[name=frm]").submit();
}

//상세페이지이동
$("[data-btn=detailInfo]").on("click", function(){
	$("form[name=frm] input[name=MEMBER_CODE]").val($(this).attr("data-detailCode"));
	$("form[name=frm]").attr("method", "GET");
	$("form[name=frm]").attr("action", "/consult/system/memberDetail");
	$("form[name=frm]").submit();
});


</script>
</body>
</html>