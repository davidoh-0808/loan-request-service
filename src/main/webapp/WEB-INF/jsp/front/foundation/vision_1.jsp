<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="재단비전"></c:set>
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->

	<!-- slider -->
	<%@ include file="/WEB-INF/jsp/front/foundation/include/slider.jsp" %>
	<!-- slider -->

	<main id="container" class="inner">
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/front/foundation/include/lnb.jsp" %>
		<!-- lnb -->
		
		<section class="contents">
			<div class="group_title">
				<ul class="breadcrumb">
					<li>Home</li>
					<li>재단소개</li>
					<li>재단비전</li>
				</ul>
				<h3>재단비전</h3>
			</div>

			<p class="msg">현대차미소금융재단은 금융 사각지대에 있는 소외계층의 자립기반 및 사회통합, 금융발전에 이바지하고자 합니다</p>
			
			<dl class="lst_vision">
				<dt>운영목표</dt>
				<dd>
					<ul>
						<li>- 보다 많은 서민에게 희망의 선순환 고리 제공</li>
					</ul>
				</dd>
				<dt>사회적 가치</dt>
				<dd>
					<ul>
						<li>- 금융사각지대 해소</li>
						<li>- 금융소외계층 자립기반 마련</li>
						<li>- 사회통합 및 균형발전에 이바지</li>
					</ul>
				</dd>
				<dt>효율적 가치</dt>
				<dd>
					<ul>
						<li>- 자격의지 있는 서민을 선별</li>
						<li>- 도덕적 해이 방지</li>
						<li>- 기금손실 최소화, 재투자 극대화</li>
					</ul>
				</dd>
				
			</dl>
		</section>	<!--section-->
	</main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->
</div>

<script>
	$(function(){
		
	});
</script>

</body>

</html>
