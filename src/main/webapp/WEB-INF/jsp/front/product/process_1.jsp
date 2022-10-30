<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
<div id="wrap" class="">
	<!-- header -->
	<c:set var="m_title" value="대출절차/서류안내"></c:set>
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
					<li>대출절차/서류안내</li>
				</ul>
				<h3>대출절차/서류안내</h3>
			</div>
			
			<div class="group_process">
				<h4>대출절차</h4>
				<div class="detail_area">
					<div class="step step1">
						<div class="tit">
							<span>STEP  01</span>
							<P>대출상담 및 신청</P>
						</div>
						<ul class="lst_desc">
							<li>상담신청 [전화, 방문(지점), 인터넷(홈페이지)]</li>
							<li>지원대상 자격 확인</li>
							<li>필요서류 및 절차안내</li>
							<li>필요서류제출</li>
						</ul>
					</div>
					<div class="step step2">
						<div class="tit">
							<span>STEP 02</span>
							<P>대출심사</P>
						</div>
						<ul class="lst_desc">
							<li>사전 현장실사(사업성검토)</li>
							<li>적격여부심사, 대출한도 산정</li>
							<li>교육/컨설팅 이수 (필요시)</li>
						</ul>
					</div>
					<div class="step step3">
						<div class="tit">
							<span>STEP 03</span>
							<P>약정체결 및 지급</P>
						</div>
						<ul class="lst_desc">
							<li>대출약정서 작성/체결</li>
							<li>대출금 지급</li>
						</ul>
					</div>
				</div>
			</div>

			<div class="group_doc">
				<h4>구비서류</h4>
				<!-- 리스트  테이블 -->
				<div class="table_list">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
							<col width="25%">
							<col width="*">
							<col width="30%">
						</colgroup>
						<thead>
						<tr>
							<th>구 분</th>
							<th>구비서류</th>
							<th>비 고</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td rowspan="4">공통</td>
							<td>신분증 (주민등록증, 운전면허증, 여권 등)</td>
							<td rowspan="6" class="ac">신청인별 필요서류는<br />대출상담 시 개별안내<br /><br />차입신청서/대출약정서,<br />개인(신용)정보 조회 동의서 대면 작성</td>
						</tr>
						<tr>
						  <td>주민등록등본</td>
						</tr>
						<tr>
						  <td>사업자등록 증명원</td>
						</tr>
						<tr>
						  <td>사업장 임차계약서</td>
						</tr>
						<tr>
						  <td>소득 / 매출 확인</td>
						  <td>부가가치세 과세표준 증명원 등</td>
						</tr>
						<tr>
						  <td>현금흐름확인</td>
						  <td>주거래 통장사본 등</td>
						</tr>
						</tbody>
					</table>
				</div>

<!-- 				<ul class="lst_desc type_star">
					<li>신청인별 필요서류는 대출상담 시 개별안내</li>
					<li>차입신청서/대출약정서, 개인(신용)정보 조회 동의서 대면 작성</li>
				</ul>-->
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
			$(this).parent().next().slideToggle(200);
		})
	});
</script>

</body>

</html>
