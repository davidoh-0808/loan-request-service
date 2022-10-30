<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
<div id="wrap" class="지점안내">
	<!-- header -->
	<c:set var="m_title" value=""></c:set>
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
					<li>지점안내</li>
				</ul>
				<h3>지점안내</h3>
			</div>
			
			<div class="group_branch">
				<h4>전국 지점 위치를 확인해 보세요.</h4>

				<div class="detail_area area1">
					<h5>수도권 지점</h5><br />
					<ul class="lst_map">
<!--  						<li><a href="javascript:void(0);"><i></i><span>서대문구</span></a></li>  -->
						<li><a href="javascript:void(0);"><i></i><span>중구</span></a></li>
						<li><a href="javascript:void(0);"><i></i><span>관악구</span></a></li>
						<li><a href="javascript:void(0);"><i></i><span>서초구</span></a></li>
						<li><a href="javascript:void(0);"><i></i><span>서대문구</span></a></li>
						<li><a href="javascript:void(0);"><i></i><span>인천</span></a></li>
						<li><a href="javascript:void(0);"><i></i><span>의왕</span></a></li>
					</ul>
<!-- 					<dl class="lst_office"> -->
<!-- 						<dt>사무국</dt> -->
<!-- 						<dd class="address">서울특별시 서대문구 통일로 135 충정빌딩 11층</dd> -->
<!-- 						<dd class="phone"></dd> -->
<!-- 					</dl> -->
					<dl class="lst_branch"></dl>
				</div>
				<div class="more_area more1">
					<h5>
						<span class="title"></span>
						<button class="btn_close ir">close</button>
					</h5>
					
					<div id="map"></div>
					
					<dl class="lst_address">
						<dt>주소</dt>
						<dd class="address"></dd>
						<dt>대중교통</dt>
						<dd>
							<ul class="lst_transit">
								<li class="bus"></li>
								<li class="subway"></li>
								<li class="etc"></li>
							</ul>
						</dd>
						<dt>전화</dt>
						<dd class="phone"></dd>
						<dt>팩스</dt>
						<dd class="fax"></dd>
					</dl>
				</div>

				<div class="detail_area area2">
					<h5>지방 지점</h5>
					<ul class="lst_map">
						<li><a href="javascript:void(0);"><i></i><span>대전</span></a></li>
						<li><a href="javascript:void(0);"><i></i><span>강원도</span></a></li>
						<li><a href="javascript:void(0);"><i></i><span>광주</span></a></li>
						<li><a href="javascript:void(0);"><i></i><span>대구</span></a></li>
						<li><a href="javascript:void(0);"><i></i><span>부산</span></a></li>
					</ul>
					<dl class="lst_branch"></dl>
				</div>
				<div class="more_area more2">
					<h5>
						<span class="title"></span>
						<button class="btn_close ir">close</button>
					</h5>
					
					<div id="map2"></div>
					
					<dl class="lst_address">
						<dt>주소</dt>
						<dd class="address"></dd>
						<dt>대중교통</dt>
						<dd>
							<ul class="lst_transit">
								<li class="bus"></li>
								<li class="subway"></li>
								<li class="etc"></li>
							</ul>
						</dd>
						<dt>전화</dt>
						<dd class="phone"></dd>
						<dt>팩스</dt>
						<dd class="fax"></dd>
					</dl>
				</div>
			</div>
			
			
		</section>	<!--section-->
	</main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>

<script>
	$(function(){
		// 수도권 지점
		var dataset1 = [
			{
				title: '강북지점',
				x:37.5670789,
				y:126.9871202,
				address: '서울특별시 중구 삼일대로 363 장교빌딩 3층 <b>(서울 고용복지 센터 내)</b>',
				bus: '100, 152, 202, 261, 471, 472, 7017, 7021, 1005-1, 5500-1, 9000',
				subway: '2호선 을지로입구역 4번출구, 을지로 3가역 1번출구 도보 5분',
				etc: '',
				phone: '02) 964-7900',
				fax: '070) 4850-9043'
			},
			{
				title: '관악지점',
				x: 37.48545621411675, 
				y: 126.8982040151243,
				address: '서울특별시 구로구 디지털로34길 27\n대륭포스트타워 3차 1층 <b>(서울 관악고용복지 센터 내)</b>',
				bus: '',
				subway: '2호선 구로디지털단지역 3번 출구(도보 5분) → 구로 이마트 후문 맞은편 → 대륭포스트타워 3차 1층',
				etc: '',
				phone: '02) 3282-9359',
				fax: '070) 4850-9040'
			},
			{
				title: '서초지점',
				x: 37.48417852050705, 
				y: 127.0108118623473,
				address: '서울특별시 서초구 반포대로 43 2층 <b>(서울 서초고용복지 센터 내)</b>',
				bus: '서초3동 주민센터 하차 : 405, 5413',
				subway: '3호선 남부터미널역 5번 출구 도보 5분',
				etc: '',
				phone: '02) 453-6730',
				fax: '070) 4850-9042'
			},
			{
				title: '서대문출장소',
				x:37.5663957,
				y:126.965443,
				address: '서울특별시 서대문구 통일로 135, 충정빌딩 11층',
				bus: '서대문역 사거리 하차 : 470, 741, N75, N37번 버스 이용',
				subway: '서대문역 2번 출구 100m',
				etc: '',
				phone: '02) 3789-5006',
				fax: '070) 4850-9041'
			},
			{
				title: '인천지점',
				x: 37.46874857668268, 
				y: 126.66081836241969,
				address: '인천광역시 미추홀구 석정로 229 제물포스마트타운 6층<b>(인천 소상공인  서민금융지원센터 내)</b>',
				bus: '상수도사업본부(스마트타운) 정류장 : 2, 10, 13, 14, 21, 28번 버스 이용',
				subway: '지하철 1호선 제물포역 2번 출구(도보 10분)',
				etc: '',
				phone: '032) 715-6078',
				fax: '070) 4850-9045'
			},
			{
				title: '의왕지점',
				x: 37.38768109943066, 
				y: 126.98156698567473,
				address: '경기도 의왕시 복지로 109 <b>(내손  2동 주민센터 내)</b>',
				transit: [],
				bus: '',
				subway: '지하철 4호선 인덕원역 4번출구에서 버스이용(51번, 8-1번) → 대우 아파트 하차(내손 초등학교옆 공영청사)',
				etc: '',
				phone: '031) 426-6380',
				fax: '070) 4850-9044'
			},
		];

		// 지방 지점
		var dataset2 = [
			{
				title: '대전지점',
				x: 36.35881736987765,
				y: 127.41269509800283,
				address: '대전광역시 대덕구 한밭대로 1027 우성빌딩 5층',
				bus: '운암빌딩 앞 : 102, 605, 614, 617, 703, 706, 606',
				subway: '',
				etc: '',
				phone: '042) 639-2199',
				fax: '070) 4850-9047'
			},
			{
				title: '속초출장소',
				x: 38.19269251998934, 
				y: 128.5788474979798,
				address: '강원도 속초시 동해대로 4178  엠클리닉빌딩  3층 <b>(속초 고용복지+ 센터 내)</b>',
				bus: '부영아파트입구 하차 : 1, 1-1, 7, 7-1, 7-2, 9, 9-1, 9-2, 12, 13-1, 13-2, 18, 19, 22, 66',
				subway: '',
				etc: '',
				phone: '033) 633-2910',
				fax: '070) 4850-9051'
			},
			{
				title: '광주지점',
				x: 35.16106801848882, 
				y: 126.88625904024899,
				address: '광주광역시 서구 무진대로 966  3층 <b>(현대 자동차 빌딩 내)</b>',
				bus: '현대자동차 서비스(씨엘병원)하차 : 09, 16, 30, 48, 65, 69, 72, 84, 89, 518, 1000',
				subway: '농성역 신세계백화점 출구방향 도보 10분 거리 *신세계 백화점 사거리에서 우측(시내방면)으로',
				etc: '',
				phone: '062) 369-7400',
				fax: '070) 4850-9046'
			},
			{
				title: '대구지점',
				x: 35.8529331,
				y: 128.5376181,
				address: '대구광역시 달서구 와룡로 226 송죽빌딩 2층',
				bus: '대구 2호선 죽전역 정류장 : 503, 서구1',
				subway: '죽전역 6번출구 도보 100m',
				etc: '',
				phone: '053) 557-8917',
				fax: '070) 4850-9054'
			},
			{
				title: '부산지점',
				x: 35.20993512234469, 
				y: 129.00583997106577,
				address: '부산광역시 북구 백양대로 1204 동강빌딩 4층',
				bus: '8-1, 33, 46, 110, 111, 121, 124, 126, 127, 128-1, 133,160, 169, 200, 307, 1004, 1009',
				subway: '지하철 2호선, 3호선 덕천역 2번출구 앞',
				etc: '',
				phone: '051) 305-8730',
				fax: '070) 4850-9048'
			},
		];
		
		var area1 = $('.detail_area.area1');
		var li1 = area1.find('.lst_map li');
		var dt1 = area1.find('.lst_branch dt');
		var dd1 = area1.find('.lst_branch dd');
		var more_map1 = $('.more_area.more1');
		var map1 = 'map';

		var area2 = $('.detail_area.area2');
		var li2 = area2.find('.lst_map li');
		var dt2 = area2.find('.lst_branch dt');
		var dd2 = area2.find('.lst_branch dd');
		var more_map2 = $('.more_area.more2');
		var map2 = 'map2';
		
		function registInfo(arr, area) {
			for(var i = 0; i < arr.length; i ++) {
				var title = arr[i].title;
				var address = arr[i].address.replace(/\n/g, '<br/>');
				var phone = arr[i].phone;
				area.find('.lst_branch').append('<dt>' + title + '</dt><dd class="address">' + address + '</dd><dd class="phone">' + phone + '</dd>');
			}
		}
		
		registInfo(dataset1, area1);
		registInfo(dataset2, area2);

		function drowMap(idx, more, data, target) {
			more.slideDown(200, function(){
				var position = new naver.maps.LatLng(data[idx].x, data[idx].y);
				var map = new naver.maps.Map(target, {
					center: position,
					zoom: 17
				});
				var markerOptions = {
					position: position,
					map: map,
				};
				var marker = new naver.maps.Marker(markerOptions);

				more.find('.title').text(data[idx].title);
				more.find('.address').html(data[idx].address);
				more.find('.bus').text(data[idx].bus);
				more.find('.subway').text(data[idx].subway);
				more.find('.etc').text(data[idx].etc);
				more.find('.phone').text(data[idx].phone);
				more.find('.fax').text(data[idx].fax);
			});
		}

		// 수도권 지점
		li1.on('click', function() {
			var idx = $(this).index();
			if(idx !== -1) {
				drowMap(idx, more_map1, dataset1, map1);
			}
		   
		});

		$(document).on('click', '.area1 .lst_branch dt', function(){
			var idx = $(this).index('.area1 .lst_branch dt');
			drowMap(idx, more_map1, dataset1, map1);
		});

		$(document).on('click', '.area1 .lst_branch dd', function(){
			var idx = Math.floor($(this).index('.area1 .lst_branch dd')/2);
			console.log(idx);
			drowMap(idx, more_map1, dataset1, map1);
		});

		// 지방 지점
		li2.on('click', function() {
			var idx = $(this).index();
			drowMap(idx, more_map2, dataset2, map2);
		});

		$(document).on('click', '.area2 .lst_branch dt', function(){
			var idx = $(this).index('.area2 .lst_branch dt');
			drowMap(idx, more_map2, dataset2, map2);
		});

		$(document).on('click', '.area2 .lst_branch dd', function(){
			var idx = Math.floor($(this).index('.area2 .lst_branch dd')/2);
			drowMap(idx, more_map2, dataset2, map2);
		});

		$('.btn_close').on('click', function() {
			$(this).closest('.more_area').slideUp(200, function() {});
		});

	});
</script>

</body>

</html>
