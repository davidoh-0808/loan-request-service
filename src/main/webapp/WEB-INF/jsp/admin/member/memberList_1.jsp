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
			<h2 class="con_tit">계정 및 권한 관리</h2>
			<form name="frm">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
				<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
				<input type="hidden" name="MEMBER_CODE" value="" />
				
				<div class="tb_style tb_htype">
					<table>
						<caption>계정 및 권한 관리 검색어(키워드)</caption>
						<colgroup>
							<col style="width: 17%;">
							<col style="width: auto;">
						</colgroup>
						<tbody>
							<tr>
								<th scope="row">검색어(키워드)</th>
								<td>
									<input type="text" class="input_text w_100" title="제목입력" placeholder="이름, 아이디, 연락처" name="searchValue" value="${paramVO.searchValue }">
								</td>
							</tr>
						</tbody>
					</table>
				</div>	
			</form>
			
			<div class="btn_area sp10">
				<div class="right_a">
					<a href="#" class="btn btn_white btn_m" data-btn="searchInit"><span>초기화</span></a>
					<a href="#" class="btn btn_black btn_m" data-btn="search"><span>조회</span></a>
				</div>
			</div>

			<div class="info sp40 cellbox">
				<div class="left cell auto">
					<h3 class="s_tit">목록</h3>
					<p class="total">총 <span><fmt:formatNumber value="${totCnt }" pattern="#,###" /></span>건</p>
				</div>		
				<div class="right cell">
					<a href="#" class="btn btn_blue btn_m" data-btn="insert"><span>등록하기</span></a>
				</div>
			</div>

			<c:choose>
				<c:when test="${empty resultList }">
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
								<col style="width: 88px;">
								<col style="width: 90px;">
								<col style="width: 120px;">
								<col style="width: 120px;">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">아이디</th>
									<th scope="col">이름</th>
									<th scope="col">이메일</th>
									<th scope="col">권한</th>
									<th scope="col">상태</th>
									<th scope="col">등록일</th>
									<th scope="col">수정일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${resultList }" var="list">
									<tr data-btn="detailInfo" data-detailCode="${list.MEMBER_CODE }" <c:if test="${list.MEMBER_STATUS eq 'MC0000700003'}"> class="del" </c:if>>
										<td>${list.ROW_NUM }</td>
										<td>${list.MEMBER_ID }</td>
										<td>${list.MEMBER_NAME }</td>
										<td>${list.MEMBER_EMAIL }</td>
										<td>${list.MEMBER_AUTHORITY_NAME }</td>
										<td>
											<span class=' 
												<c:choose>
													<c:when test="${list.MEMBER_STATUS eq 'MC0000700001'}"> </c:when>
													<c:when test="${list.MEMBER_STATUS eq 'MC0000700002'}"> r_box </c:when>
													<c:when test="${list.MEMBER_STATUS eq 'MC0000700003'}"> </c:when>
													<c:when test="${list.MEMBER_STATUS eq 'MC0000700004'}"> </c:when>
													<c:otherwise></c:otherwise>
												</c:choose>
											'>${list.MEMBER_STATUS_NAME }</span>
										</td>
											<td><fmt:formatDate value="${list.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>
											<td><fmt:formatDate value="${list.UP_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</c:otherwise>
			</c:choose>

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

// 조회폼 초기화
$("[data-btn=searchInit]").on("click", function() {
	$("form[name=frm] input[name=searchValue]").val("");
	goPage(1);
});

// 조회
$("form[name=frm] input[name=searchValue]").keyup(function(key){
	if(key.keyCode === 13) {
		goPage(1);
	}
});
$("[data-btn=search]").on("click", function() {
	goPage(1);
});

// 등록폼이동
$("[data-btn=insert]").on("click", function() {
	$("form[name=frm_form]").attr("action", "/admin/member/memberInsertForm")
	$("form[name=frm_form]").submit();
});

function goPage(pageNum){
	$("form[name=frm] input[name=pageNum]").val(pageNum);
	$("form[name=frm]").attr("action", "/admin/member/memberList")
	$("form[name=frm]").submit();
}

//상세페이지이동
$("[data-btn=detailInfo]").on("click", function(){
	$("form[name=frm] input[name=MEMBER_CODE]").val($(this).attr("data-detailCode"));
	$("form[name=frm]").attr("action", "/admin/member/memberDetail");
	$("form[name=frm]").submit();
});
</script>

</body>
</html>