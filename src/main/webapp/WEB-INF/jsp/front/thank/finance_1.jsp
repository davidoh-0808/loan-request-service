<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="고마워요 미소금융"></c:set>
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
					<li>미소금융소식</li>
					<li>고마워요 미소금융</li>
				</ul>
				<h3>고마워요 미소금융</h3>
			</div>
			
			<div class="group_list">
				<form name="frm">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
					<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
					<input type="hidden" name="BOARD_CODE"/>
				</form>
				<h4>
					<span>목록</span>
					<span class="count">총 <span>${totCount }개</span>
				</h4>

				<div class="table_list_thumbnail">
				
					<c:choose>
						<c:when test="${empty resultList }">
							<span>등록된 글이 없습니다.</span>
						</c:when>
						<c:otherwise>
							<c:forEach items="${resultList }" var="list">
								<div class="row" data-btn="detailInfo" data-detailCode="${list.BOARD_CODE }">
									<div class="thumb_area">
										<c:choose>
											<c:when test="${empty list.fileList}">
												<img src="/resources/front/img/temp_thumb.png">
											</c:when>
											<c:otherwise>
												<c:forEach items="${list.fileList }" var="item">
													<img src="/external${item.UPLOAD_PATH}/${item.SYS_FILE_NAME}" height="121" width="249" onerror="this.src='/resources/front/img/temp_thumb.png';" >
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</div>
									<div class="desc_area">
										<p class="tit">${list.TITLE }</p>
										<pre><p class="desc">${list.VIEW_CONTENT}</p></pre>
										<p class="date">
											<span><fmt:formatDate value="${list.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/></span>
											<span>조회 ${list.VIEW_CNT }</span>
										</p>
									</div>
								</div>
							</c:forEach>
						</c:otherwise>
					</c:choose>

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
	$("form[name=frm]").attr("action", "/front/thank/finance")
	$("form[name=frm]").submit();
}

//상세페이지이동
$("[data-btn=detailInfo]").on("click", function(){
	$("form[name=frm] input[name=BOARD_CODE]").val($(this).attr("data-detailCode"));
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/front/thank/financeDetail");
	$("form[name=frm]").submit();
});
</script>

</body>

</html>
