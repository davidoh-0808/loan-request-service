<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/admin/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/admin/script.jsp" %>

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
			<h2 class="con_tit">고마워요미소금융 관리</h2>
			<form name="frm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
				<input type="hidden" name="BOARD_CODE"/>
			</form>

			<div class="info sp40 cellbox">
				<div class="left cell auto">
					<h3 class="s_tit">목록</h3>
					<p class="total">총 <span>${totCount }</span>건</p>
				</div>		
				<div class="right cell">
					<button type="button" class="btn btn_blue btn_m" data-btn="btnInsert">등록하기</button>
				</div>
			</div>
			<div class="tb_style tb_vtype sp10">
				<table>
					<caption>고마워요미소금융 관리 목록</caption>
					<colgroup>
						<col style="width: 50px;">
						<col style="width: auto;">
						<col style="width: 136px;">
						<col style="width: 143px;">
						<col style="width: 106px;">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">번호</th>
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
								<c:forEach items="${resultList }" var="list">
									<tr data-btn="detailInfo" data-detailCode="${list.BOARD_CODE }">
										<td>${list.ROW_NUM}</td>
										<td>${list.TITLE}</td>
										<td>${list.IN_USER_NAME}</td>
										<td>${list.IN_DTTM}"</td>
										<td>${list.VIEW_CNT}</td>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>

			<!-- //페이징 -->
			<%@ include file="/WEB-INF/jsp/common/include/admin/paging.jsp" %>	
			<!-- //페이징 -->

		</div>
		<!--//content-->

	</div>
	<!-- //관리자 하단 -->

</div>
<!-- //Wrap-->

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/admin/footer.jsp" %>
<!-- footer -->

<!-- 등록폼이동 -->
<form name="frm_form">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
</form>

<script>

function goPage(pageNum){
	$("form[name=frm] input[name=pageNum]").val(pageNum);
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/admin/board/thanks/thanksList")
	$("form[name=frm]").submit();
}

// 등록하기
$("[data-btn=btnInsert]").on("click", function() {
	$("form[name=frm_form]").attr("action", "/admin/board/thanks/thanksInsertForm")
	$("form[name=frm_form]").attr("method", "GET")
	$("form[name=frm_form]").submit();
});

//상세페이지이동
$("[data-btn=detailInfo]").on("click", function(){
	$("form[name=frm] input[name=BOARD_CODE]").val($(this).attr("data-detailCode"));
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/admin/board/thanks/thanksDetail");
	$("form[name=frm]").submit();
});
</script>
</body>
</html>