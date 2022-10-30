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
					<li>고마워요 미소금융</li>
					<li>미소금융 news</li>
				</ul>
				<h3>미소금융 news</h3>
			</div>

			<div class="group_news">
				<h4>${result.TITLE }</h4>

				<dl class="table_responsive">
					<dt class="col"><span class="">작성일</span></dt>
					<dd class="col">
						<fmt:formatDate value="${result.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/>
					</dd>
					<dt class="col"><span class="">조회수</span></dt>
					<dd class="col">${result.VIEW_CNT }</dd>
					<dd class="colspan2 textarea">
						<pre>${result.CONTENT }</pre>
					</dd>
			</dl> <!-- // table_responsive -->

				<div class="submit_area pt20">
					<button class="btn btn_lg btn_darkgray" data-btn="btnList">목록</button>
				</div>

				<dl class="table_paging">
					<dt>이전글</dt>
					<dd>
						<c:choose>
							<c:when test="${empty result_pre }">
								<a href="javascript:void(0);">
									<span class="tit">이전글이 없습니다.</span>
								</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);">
									<span class="tit" data-btn="preDetailInfo" data-detailCode="${result_pre.BOARD_CODE }">${result_pre.TITLE }</span>
									<span class="date"><fmt:formatDate value="${result_pre.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/></span>
								</a>
							</c:otherwise>
						</c:choose>
					</dd>
					<dt>다음글</dt>
					<dd>
						<c:choose>
							<c:when test="${empty result_next }">
								<a href="javascript:void(0);">
									<span class="tit">다음글이 없습니다.</span>
								</a>
							</c:when>
							<c:otherwise>
								<a href="javascript:void(0);">
									<span class="tit" data-btn="nextDetailInfo" data-detailCode="${result_next.BOARD_CODE }">${result_next.TITLE }</span>
									<span class="date"><fmt:formatDate value="${result_next.IN_DTTM }" pattern="${SPRING_MVC_FORMAT_DATE }"/></span>
								</a>
							</c:otherwise>
						</c:choose>
					</dd>
				</dl>
			</div>
		</section>	<!--section-->
	</main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>

<form name="frm">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
	<input type="hidden" name="searchValue" value="${paramVO.searchValue }" />
	<input type="hidden" name="pageNum" value="${paramVO.pageNum }" />
	<input type="hidden" name="BOARD_CODE" value="${paramVO.BOARD_CODE }" />
</form>	

<script>
//목록가기
function newsList(){
	$("form[name=frm]").attr("action", "/front/thank/news");
	$("form[name=frm]").submit();
}

//목록페이지 이동
$("[data-btn=btnList]").on("click", function() {
	newsList();
});

//이전 상세페이지이동
$("[data-btn=preDetailInfo]").on("click", function(){
	$("form[name=frm] input[name=BOARD_CODE]").val($(this).attr("data-detailCode"));
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/front/thank/newsDetail");
	$("form[name=frm]").submit();
});

//다음글 상세페이지이동
$("[data-btn=nextDetailInfo]").on("click", function(){
	$("form[name=frm] input[name=BOARD_CODE]").val($(this).attr("data-detailCode"));
	$("form[name=frm]").attr("method", "GET")
	$("form[name=frm]").attr("action", "/front/thank/newsDetail");
	$("form[name=frm]").submit();
});


</script>

</body>

</html>
