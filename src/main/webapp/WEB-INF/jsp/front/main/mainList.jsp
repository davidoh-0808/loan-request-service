<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
<div id="wrap" class="wrap_main">

	<!-- header -->
	<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
	<!-- header -->
	<nav></nav>
	<!-- slider -->
	<%@ include file="/WEB-INF/jsp/front/main/include/slider.jsp" %>
	<!-- slider -->
	
		<main id="container">
		<section class="inner">
			<ul class="lst_service">
				<li>
					<a href="/front/consultation/request">
						<h3>대출상담 신청</h3>
						<p class="desc">
							대출가능여부, 한도를 확인하실 수 있습니다.
						</p>
					</a>
				</li>
				<li>
					<a href="/front/product/target">
						<h3>대출대상/상품 안내</h3>
						<p class="desc">
							대출상품종류, 자격기준을 확인하실 수 있습니다.
						</p>
					</a>
				</li>
				<li>
					<a href="/front/foundation/branch">
						<h3>지점 안내</h3>
						<p class="desc">
							전국 지점위치를 확인해보세요
						</p>
					</a>
				</li>
				<li>
					<a href="/front/product/intro">
						<h3>미소금융 소개</h3>
						<p class="desc">
							현대차미소금융재단의<br />
							소개화면 입니다.
						</p>
					</a>
				</li>
				<li>
					<a href="/front/thank/finance">
						<h3>고마워요 미소금융</h3>
						<p class="desc">
							현대차미소금융재단의<br />
							지원 사례를 확인하실 수 있습니다.
						</p>
					</a>
				</li>
				<li>
					<a href="https://blog.naver.com/hyundaimiso/" target ="_blank">
						<h3>재단블로그 보기</h3>
						<p class="desc">
							현대차미소금융재단에서 진행하는 블로그<br />
							홍보물을 보실 수 있습니다.
						</p>
					</a>
				</li>
			</ul>
		</section>	<!--inner-->
	</main>
    <aside class="group_family inner">
        <!-- Additional required wrapper -->
        <div class="swiper swiper_family">
            <!-- Slides -->
            <ul class="lst_family swiper-wrapper">
				<li class="swiper-slide"><a href="https://www.hyundai.co.kr/main/mainRecommend" target="_blank"><img src="/resources/front/img/family1.png" alt="HYUNDAI MOTOR GROUP"></a></li>
				<li class="swiper-slide"><a href="https://www.hyundai.co.kr/sustainability/right" target="_blank"><img src="/resources/front/img/family2.png" alt="현대차그룹 사회공헌 함께 움직이는 세상"></a></li>
<!-- 				<li class="swiper-slide"><a href="https://www.fss.or.kr/fss/main/main.do" target="_blank"><img src="/resources/front/img/family3.png" alt="금융감독원"></a></li> -->
				<li class="swiper-slide"><a href="https://www.fsc.go.kr/index" target="_blank"><img src="/resources/front/img/family4.png" alt="금융위원회"></a></li>
				<li class="swiper-slide"><a href="https://www.kinfa.or.kr/intro.do" target="_blank"><img src="/resources/front/img/family8.png" alt="서금원"></a></li>
				<li class="swiper-slide"><a href="https://www.fss.or.kr/fss/main/sub1voice.do?menuNo=200012" target="_blank"><img src="/resources/front/img/family5.png" alt="보이스피싱지킴이"></a></li>
				<li class="swiper-slide"><a href="https://www.fss.or.kr/s1332/" target="_blank"><img src="/resources/front/img/family6.png" alt="서민금융1332"></a></li>
				<li class="swiper-slide"><a href="https://fine.fss.or.kr/main/index.jsp" target="_blank"><img src="/resources/front/img/family7.png" alt="금융소비자정보보호포털"></a></li>
            </ul>
        </div>

        <div class="swiper-button-prev"></div>
        <div class="swiper-button-next"></div>
    </aside>
<!-- 	<aside class="swiper_family inner"> -->
<!-- 		<!-- Additional required wrapper --> 
<!-- 		<div class="swiper-wrapper"> -->
<!-- 			<!-- Slides --> 
<!-- 			<div class="swiper-slide"> -->
<!-- 				<ul class="lst_family"> -->
<!-- 					<li><a href="https://www.hyundai.co.kr/main/mainRecommend" target="_blank"><img src="/resources/front/img/family1.png" alt="HYUNDAI MOTOR GROUP"></a></li> -->
<!-- 					<li><a href="https://www.hyundai.co.kr/sustainability/right" target="_blank"><img src="/resources/front/img/family2.png" alt="현대차그룹 사회공헌 함께 움직이는 세상"></a></li> -->
<!-- 					<li><a href="https://www.fss.or.kr/fss/main/main.do" target="_blank"><img src="/resources/front/img/family3.png" alt="금융감독원"></a></li> -->
<!-- 					<li><a href="https://www.fsc.go.kr/index" target="_blank"><img src="/resources/front/img/family4.png" alt="금융위원회"></a></li> -->
<!-- 					<li><a href="https://www.fss.or.kr/fss/main/sub1voice.do?menuNo=200012" target="_blank"><img src="/resources/front/img/family5.png" alt="보이스피싱지킴이"></a></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 			<div class="swiper-slide"> -->
<!-- 				<ul class="lst_family"> -->
<!-- 					<li><a href="https://www.fss.or.kr/s1332/" target="_blank"><img src="/resources/front/img/family6.png" alt="서민금융1332"></a></li> -->
<!-- 					<li><a href="https://fine.fss.or.kr/main/index.jsp" target="_blank"><img src="/resources/front/img/family7.png" alt="금융소비자정보보호포털"></a></li> -->
<!-- 					<li><a href="https://www.kinfa.or.kr/intro.do" target="_blank"><img src="/resources/front/img/family8.png" alt="서금원"></a></li> -->
<!-- 					<li><a href="javascript:void(0);"></a></li> -->
<!-- 					<li><a href="javascript:void(0);"></a></li> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->

<!-- 		<div class="swiper-button-prev"></div> -->
<!-- 		<div class="swiper-button-next"></div> -->
<!-- 	</aside> -->
	

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>	<!--#wrap-->

<script>
	$(function(){
        const swiper = new Swiper('.swiper_main', {
            // Optional parameters
            direction: 'horizontal',
            loop: false,
            autoplay: { delay: 5000 },
            
            // If we need pagination
            pagination: {
                el: '.swiper-pagination',
                clickable : true
            },
        });

        const swiper_family = new Swiper('.swiper_family', {
            // Optional parameters
            slidesPerView: 'auto',
            spaceBetween: 20,
            autoplay: { delay: 5000 },

            // Navigation arrows
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            
        });

	});
</script>

</body>
</html>
