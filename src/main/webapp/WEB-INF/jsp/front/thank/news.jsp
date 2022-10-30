<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="미소금융 news"></c:set>
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->

	<!-- slider -->
	<%@ include file="/WEB-INF/jsp/front/thank/include/slider.jsp" %>
	<!-- slider -->

	<main id="container" class="inner">
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/front/thank/include/lnb.jsp" %>
		<!-- lnb -->
		
		<section class="contents">
			<div class="group_title">
				<ul class="breadcrumb">
					<li>Home</li>
					<li>고마워요미소금융</li>
					<li>미소금융 news</li>
				</ul>
				<h3>미소금융 news</h3>
			</div>
			
			<div class="group_news">
				<form name="frm">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
					<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
					<input type="hidden" name="BOARD_CODE"/>
				</form>
				<h4>
					<span>목록</span>
					<span class="count">총 <span>${totCount }개</span>
				</h4>

				<div class="table_list ac">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
						<col width="8%">
						<col width="*">
						<col width="18%">
						<col width="12%">
						</colgroup>
						<thead>
						<tr>
							<th>NO</th>
							<th>제목</th>
							<th>등록일</th>
							<th>조회</th>
						</tr>
						</thead>
						
						<tbody>
							<c:choose>
								<c:when test="${empty resultList }">
									<tr>
										<td colspan="2">등록된 글이 없습니다.</td>
									</tr>
								</c:when>
								<c:otherwise>
									<c:forEach items="${resultList }" var="list">
										<tr data-btn="detailInfo" data-detailCode="${list.BOARD_CODE }">
											<td>${list.ROW_NUM }</td>
											<td class="al txt_ellipsis">${list.TITLE }</td>
											<td>
	 											<fmt:formatDate value="${list.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/> 
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
			<%@ include file="/WEB-INF/jsp/common/include/front/paging.jsp" %>	
			<!-- //페이징 -->
			</div>
		</section>	<!--section-->
	</main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>

<script>

function goPage(pageNum){
	$("form[name=frm] input[name=pageNum]").val(pageNum);
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/front/thank/news")
	$("form[name=frm]").submit();
}

//상세페이지이동
$("[data-btn=detailInfo]").on("click", function(){
	$("form[name=frm] input[name=BOARD_CODE]").val($(this).attr("data-detailCode"));
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/front/thank/newsDetail");
	$("form[name=frm]").submit();
});
</script>

</body>

</html>
