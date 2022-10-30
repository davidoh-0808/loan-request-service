<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="사업소개"></c:set>
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->

	<!-- slider -->
	<%@ include file="/WEB-INF/jsp/front/product/include/slider.jsp" %>
	<!-- slider -->

	<main id="container" class="inner">
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/front/product/include/lnb.jsp" %>
		<!-- lnb -->
		
		<section class="contents">
			<div class="group_title">
				<ul class="breadcrumb">
					<li>Home</li>
					<li>대출상품 안내</li>
					<li>사업소개</li>
				</ul>
				<h3>사업소개</h3>
			</div>
			
			<div class="group_intro_top">
				<p>
					현대차미소금융재단은<br />현대자동차그룹에서 기부한 재원으로<br />
					제도권 금융기관 이용이 어려운 분들에게<br />사회&middot;경제적 자립 기반을 마련할 수 있는 금융 및 창업 컨설팅 등을 지원하고 있습니다.
				</p>
			</div>


			<!-- <div class="group_business">
				<h4>미소금융 주요사업</h4>
				<p>사업운영 ·임대주택보증금 등 필요자금을 담보나 보증 없이 저리에 지원하여<br />금융사각지대 해소는 물론 서민의 금융생활 안정에 기여하도록 노력하고 있습니다.</p>
			</div> -->

			<!-- <div class="group_title in">
				<h3>미소금융 사업분야</h3>
			</div> -->
			
			<dl class="lst_business">
				<dt>금융지원</dt>
				<dd>
					<ul class="lst_count">
						<li><b>12년</b><span>동안</span></li>
						<li><b>24,799명</b><span>서민들에게</span></li>
						<li><b>2,966억 원</b><span>지원하였습니다.</span><span>(`22.9월말 누적 기준)</span></li>
						<li><span>『서민의 금융생활 지원에 관한 법률』</span>에 의거하여 서민금융진흥원의 미소금융사업을 수행</li>
					</ul>
					<p class="desc">
						금융소외계층을 대상으로 자활에 필요한 자금을 담보없이 저금리로 지원하며,<br />
						보다 많은 분들이 편리하게 이용할 수 있도록 전국 11개 지점 및 출장소를 운영하고 있습니다.<br />
					</p>
				</dd>
				<dt>서민생활지원</dt>
				<dd>
					<p>현대차미소금융재단은 서민금융진흥원과 함께 일자리 상담, 금융교육, 사업 컨설팅 등을 진행하여<br />전문적이고 실질적인 자립을 지원하고 있습니다.</p>
					<ul>
						<li>- 취업알선 및 일자리 지원 : 취업전문가의 체계적인 상담 및 구직정보 제공</li>
						<li>- 금융교육 : 실질적 자활·자립을 위한 금융·창업 등 맞춤형 교육 지원</li>
						<li>- 컨설팅 : 전문 컨설턴트를 통한 상권 및 입지분석, 사업성 분석 등 사업운영자 컨설팅 지원</li>
					</ul>
				</dd>
				<!-- <dt>서민금융진흥원 디지털혁신 프로젝트 참여</dt>
				<dd>
					<p>서민금융 이용자들이 언제,어디서나 편리하게 상담서비스를 받을 수 있도록 서민금융진흥원,모바일 앱, 페이퍼리스 창구시스템 개발 등에 참여해 표창장을 수상하는 등 이용자 편의 향상에 기여했습니다.</p>
				</dd> -->
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
