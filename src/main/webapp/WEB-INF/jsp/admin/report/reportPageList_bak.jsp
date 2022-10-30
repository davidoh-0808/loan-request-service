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
			<h2 class="con_tit">통계현황 관리</h2>

			<div class="tab-default">
				<ul class="tabs">
					<li class="current"><a href="#">접속경로</a></li>
					<li><a href="#">페이지 뷰</a></li>
				</ul>
			</div>
			
			<div class="tab_contents">
				<h3 class="txt_hidden">접속경로</h3>

				<div class="tb_style tb_htype">
					<table>
						<caption>통계현황 기간선택</caption>
						<colgroup>
							<col style="width: 163px;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">기간선택</th>
								<td>
									<div class="cal_wrap">
										<input type="text" class="input_text datepicker"> ~ 
										<input type="text" class="input_text datepicker">
									</divclass>
								</td>
							</tr>
						</tbody>
					</table>
				</div>	

				<div class="btn_area sp10">
					<div class="right_a">
						<button type="button" class="btn btn_white btn_m">초기화</button>
						<button type="button" class="btn btn_black btn_m">조회</button>
					</div>
				</div>


				<div class="tb_style tb_vtype sp30">
					<table>
						<caption>접속경로 목록</caption>
						<colgroup>
							<col style="width: 50px;">
							<col style="width: 150px;">
							<col style="width: auto;">
							<col style="width: 150px;">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">순위</th>
								<th scope="col">페이지명</th>								
								<th scope="col">접속페이지경로</th>
								<th scope="col">비율</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty resultList }">
									<tr>
										<td colspan="4">데이터가 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${resultList }" var="list">
										<tr data-btn="detailInfo">
											<td>${list.SUNWI }</td>
											<td>페이지명</td>
											<td>${list.REPO_PATH }</td>
											<td><fmt:formatNumber value="${list.REPO_RATE }" pattern="###.##"/>% (
											<fmt:formatNumber value="${list.CNT }" pattern="#,###"/>건)</td>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>

				<!-- 페이징 -->
	 			<%@ include file="/WEB-INF/jsp/common/include/admin/paging.jsp" %>	
				<!-- //페이징 -->
			</div>
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
	$("form[name=frm]").attr("action", "/admin/board/news/newsList")
	$("form[name=frm]").submit();
}

// 등록하기
$("[data-btn=btnInsert]").on("click", function() {
	$("form[name=frm_form]").attr("action", "/admin/board/news/newsInsertForm")
	$("form[name=frm_form]").attr("method", "GET")
	$("form[name=frm_form]").submit();
});

//상세페이지이동
$("[data-btn=detailInfo]").on("click", function(){
	$("form[name=frm] input[name=BOARD_CODE]").val($(this).attr("data-detailCode"));
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/admin/board/news/newsDetail");
	$("form[name=frm]").submit();
});

//조회폼 초기화
$("[data-btn=btnInit]").on("click", function() {
	$("form[name=frm] input[name=searchStartDt]").val("");
	$("form[name=frm] input[name=searchEndDt]").val("");
	goPage(1);
});
</script>
</body>
</html>