<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="대출대상/대출상품"></c:set>
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
					<li>대출대상/대출상품</li>
				</ul>
				<h3>대출대상/대출상품</h3>
			</div>
			
			<div class="group_target">
				<h4>대출대상</h4>
				<div class="desc">
					자립의지 및 자립능력이 있는 개인사업자 중에서 아래 요건 중 하나에 해당하는 경우 
				</div>
				<div class="detail_area">
					<ul class="lst_desc">
						<li>개인신용평점이 하위 20%이하 고객 (NICE 744점 / KCB 700점 이하 (’22년 4월))</li>
						<li>근로장려금 신청자격 요건에 해당하는 고객</li>
						<li>기초생활수급권자 및 차상위계층 이하 고객</li>
					</ul> 
				</div>
				<ul class="lst_confirm">
					<li>
						<p>대출가능여부 확인하기</p>
						<a href="/front/consultation/request" class="btn btn_md btn_blue btn_radius w200">상담신청</a>
					</li>
					<li>
						<p>신용 평점 직접 확인하기</p>
						<div>
							<a href="https://www.allcredit.co.kr/screen/sc5476913238?returnUrl=sc3434710796" target="_blank" class="btn btn_md btn_blue btn_radius w200">KCB 신용평점 조회</a>
							<a href="https://www.credit.co.kr/ib20/mnu/BZWOCCCSE98" target="_blank" class="btn btn_md btn_blue btn_radius w200">NICE 신용평점 조회</a>
						</div>
					</li>
				</ul>
			</div>

			<div class="group_reject">
				<h4>
					<span>대출불가</span>
					<button class="btn btn_sm btn_lightgray btn_more">내용보기</button>
				</h4>
				<div class="detail_area">
					<ul class="lst_desc">
						<li>한국신용정보원 신용정보전산망에 신용도판단정보 및 공공정보 등재</li>
						<li>책임재산을 도피, 은닉, 기타 책임재산 감소행위 초래 경력이 있는 자</li>
						<li>소유재산에 가등기, (가)압류, 가처분, 경매진행 등 법적절차 진행 중인 자</li>
						<li>미성년자, 피성년후견인, 피한정후견인, 피특정후견인, 재외국민, 외국인, 해외체류자</li>
						<li>기타 사회통념상 저소득/저신용층으로 보기 어렵거나 미소금융 지원 취지에 적합하지 않다고 판단되는 경우<br />(예 : 귀금속중개업, 유흥주점업 등 소상공인 정책자금 융자제외 대상업종)</li>
						<li>자립의지 및 능력, 상환가능성 등의 심사과정에서 부적격으로 판정되는 경우</li>
					</ul>
				</div>
			</div>

			<div class="group_product">
				<h4>대출상품</h4>
				<!-- 리스트	테이블 -->
				<div class="table_list">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
							<col width="18%">
							<col width="14%">
							<col width="*">
							<col width="15%">
							<col width="17%">
						</colgroup>
					<thead>
						<tr>
							<th colspan="3">대출상품</th>
							<th>대출한도(최대)</th>
							<th>이자율(연)</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td rowspan="3">운영자금</td>
							<td colspan="2">개인사업자 (택시, 화물 등 포함)</td>
							<td class="ar">2,000만원</td>
							<td rowspan="11" class="ac bb0">4.5%<br />(성실상환 고객 <br /> 3.5% <sup>주1 </sup>)</td>
						</tr>
						<tr>
							<td colspan="2">무등록사업자 <sup>주2)</sup></td>
							<td class="ar">500만원</td>
						</tr>
						<tr>
							<td colspan="2">인적용역 사업소득자 (프리랜서)</td>
							<td class="ar">1,000만원</td>
						</tr>
						<tr>
							<td rowspan="5">창업자금</td>
							<td rowspan="3">개인사업자</td>
							<td>사업장 임차보증금</td>
							<td class="ar">7,000만원</td>
						</tr>
						<tr>
							<td>1톤이하 상용차 구입자금</td>
							<td class="ar">2,000만원</td>
						</tr>
						<tr>
							<td>창업 초기운영자금</td>
							<td class="ar">2,000만원</td>
						</tr>
						<tr>
							<td rowspan="2">무등록사업자</td>
							<td>사업장 임차보증금</td>
							<td class="ar">500만원</td>
						</tr>
						<tr>
							<td>1톤이하 상용차 구입자금</td>
							<td class="ar">1,000만원</td>
						</tr>
						<tr>
							<td>긴급생계자금대출</td>
							<td colspan="2">미소금융 이용고객의 긴급자금</td>
							<td class="ar">1,000만원</td>
						</tr>
						<tr>
							<td>교육비지원대출</td>
							<td colspan="2">저소득층의 자녀 교육비</td>
							<td class="ar">500만원</td>
						</tr>
						<tr>
							<td>취약계층자립자금</td>
							<td colspan="2">취약계증 필요자금 <sup>주3)</sup></td>
							<td class="ar">1,200만원</td>
						</tr>
					</tbody>
					</table>
				</div>

				<ul class="lst_desc">
					<li><sup>주1)</sup> 3개월기간 성실상환 고객은 이후 3개월 금리인하, 누적연체일수에 따라 가산금리 적용</li>
					<li><sup>주2)</sup> 무등록사업자대출 : 연 2%</li>
					<li><sup>주3)</sup> 취약계층자립지원대출 : 연 3%</li>
				</ul>
			</div>
			
		</section>	<!--section-->
	</main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>

<script>
	$(function(){
		$('.btn_more').on('click', function(){
			$(this).toggleClass('on');
			if($(this).hasClass('on')) {
				$(this).text('내용접기');
			} else {
				$(this).text('내용보기');
			}
			$(this).parent().next().toggleClass('on');
		})
	});
</script>
</body>

</html>
