<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="인사말"></c:set>
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
					<li>인사말</li>
				</ul>
				<h3>인사말</h3>
			</div>
<!-- 			
			<div class="group_ceo">
				<h4 class="tit">현대차미소금융재단  홈페이지를 찾아주셔서 감사드립니다.</h4>
				<p class="desc">
					현대차미소금융은 현대자동차그룹에서 기부한 재원으로 금융소외계층의 자립을 지원하기 위해<b>2009년 12월 설립되었습니다.</b><br />
					글로벌 경기침체, 소득 양극화가 커지는 환경에서 저희 재단은 자활의지는 있으나 제도 금융수혜가 어려운 저신용, 저소득 금융, 창업 컨설팅 등으로 경제적 자활기반 마련을 지원하고 있습니다.<br />
					<b>설립이후 12년간 2만 3천명의 금융취약계층 개인사업자에게 2천8백억을 지원하여 자립기반을 마련할 수 있도록 도움을 드렸습니다.</b><br />
					또한 운용체계를 지속적으로 개선하여 도덕적 해이를 예방하고 더 많은 분들께 혜택이 돌아갈 수 있도록 최선을 다하겠습니다.<br />
					이러한 지원사업을 통해 ‘모두가 함께 사는 세상’을 만들어 가는 일에 적극적으로 참여함으로써, 선량한 기업시민의 역할을 다하도록 하겠습니다.<br />
					홈페이지를 방문해주신 모든 분들께 다시 한번 진심으로 감사 드리며, 끊임없는 관심을 부탁드립니다.<br /><br />
					감사합니다.
				</p>
				<ul class="lst_sign">
					<li class="date">2018.8.24</li>
					<li class="sign">현대차미소금융재단 이사장</li>
					<li class="sr_only">윤석훈</li>
				</ul>
			</div> -->
			
			<div class="group_greeting">
				<h4 class="tit">현대차미소금융재단  홈페이지를 찾아주셔서 감사드립니다.</h4>
				<p class="desc">
					현대차미소금융은 현대자동차그룹에서 기부한 재원으로 금융소외계층의 자립을 지원하기 위해<b>2009년 12월 설립되었습니다.</b><br />
					글로벌 경기침체, 소득 양극화가 커지는 환경에서 저희 재단은 자활의지는 있으나<br />
					제도 금융수혜가 어려운 저신용, 저소득 금융, 창업 컨설팅 등으로 경제적 자활기반 마련을 지원하고 있습니다.<br />
					<b>설립이후 12년간 2만 3천명의 금융취약계층 개인사업자에게 2천8백억을 지원하여 자립기반을 마련할 수 있도록 도움을 드렸습니다.</b><br />
					또한 운용체계를 지속적으로 개선하여 도덕적 해이를 예방하고 더 많은 분들께 혜택이 돌아갈 수 있도록 최선을 다하겠습니다.<br />
					이러한 지원사업을 통해 ‘모두가 함께 사는 세상’을 만들어 가는 일에 적극적으로 참여함으로써, 선량한 기업시민의 역할을 다하도록 하겠습니다.<br />
					홈페이지를 방문해주신 모든 분들께 다시 한번 진심으로 감사 드리며,<br />끊임없는 관심을 부탁드립니다.<br /><br />
					감사합니다.
				</p>
			</div>
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
