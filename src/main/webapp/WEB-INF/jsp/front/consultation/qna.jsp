<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/common/include/front/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/front/script.jsp" %>

</head>

<body>
	<div id="wrap" class="">
		<!-- header -->
		<c:set var="m_title" value="자주하는질문"></c:set>
		<%@ include file="/WEB-INF/jsp/common/include/front/header.jsp" %>
		<!-- header -->
	
		<!-- slider -->
		<%@ include file="/WEB-INF/jsp/front/consultation/include/slider.jsp" %>
		<!-- slider -->
	
		<main id="container" class="inner">
		<!-- lnb -->
		<%@ include file="/WEB-INF/jsp/front/consultation/include/lnb.jsp" %>
		<!-- lnb -->
			
		<section class="contents">
			<div class="group_title">
				<ul class="breadcrumb">
					<li>Home</li>
					<li>대출상담 신청</li>
					<li>자주하는질문</li>
				</ul>
				<h3>자주하는질문</h3>
			</div>
			
			<div class="group_search">
				<div class="form_search">
					<input type="text" id="input_search" class="form_control" placeholder="검색어를 입력해 주세요">
					<button class="btn_clear ir">검색어 삭제</button>
					<button class="btn_search ir">검색</button>
				</div>
			</div>
			
			<dl class="lst_qna"></dl>

			<div class="group_blank off">
				<p class="blank">해당 검색어로 검색된 결과가 없습니다.</p>
			</div>
		
		</section>	<!--section-->
	</main>

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/front/footer.jsp" %>
<!-- footer -->

</div>

<script>
$(function(){
	
	var lst_qna = $('.lst_qna');
	var btn_search = $('.btn_search');
	var input_search = $('#input_search');
	var blank = $('.group_blank');
	var table_qna = '<p>가까운 지점으로 연락주시면 대출상품, 지원대상 및 요건 등에 대하여 안내해드리고 있습니다.</p>' +
	'<div class="table_list ac">' +
		'<table>' +
			'<caption class="sr_only">리스트</caption>' +
			'<colgroup>' +
				'<col width="30%">' +
				'<col width="30%">' +
				'<col width="*">' +
			'</colgroup>' +
			'<thead>' +
			'<tr>' +
				'<th>지역</th>' +
				'<th>지점</th>' +
				'<th>전화번호</th>' +
			'</tr>' +
			'</thead>' +
			'<tbody>' +
			'<tr>' +
				'<td rowspan="6">서울/경기</td>' +
				'<td>강북지점</td>' +
				'<td>02) 964-7900</td>' +
			'</tr>' +
			'<tr>' +
				'<td>노원출장소</td>' +
				'<td>02) 964-7900</td>' +
			'</tr>' +
			'<tr>' +
				'<td>관악지점</td>' +
				'<td>02) 3282-9359</td>' +
			'</tr>' +
			'<tr>' +
				'<td>서초지점</td>' +
				'<td>02) 453-6730</td>' +
			'</tr>' +
			'<tr>' +
				'<td>인천지점</td>' +
				'<td>032) 715-6078</td>' +
			'</tr>' +
			'<tr>' +
				'<td>의왕지점</td>' +
				'<td>031) 426-6380</td>' +
			'</tr>' +
			'<tr>' +
				'<td>대전/충청</td>' +
				'<td>대전지점</td>' +
				'<td>042) 639-2199</td>' +
			'</tr>' +
			'<tr>' +
				'<td>광주/호남</td>' +
				'<td>광주지점</td>' +
				'<td>062) 369-7400</td>' +
			'</tr>' +
			'<tr>' +
				'<td>대구/경북</td>' +
				'<td>대구지점</td>' +
				'<td>053) 557-8917</td>' +
			'</tr>' +
			'<tr>' +
				'<td>부산/경남</td>' +
				'<td>부산지점</td>' +
				'<td>051) 305-8730</td>' +
			'</tr>' +
			'<tr>' +
				'<td>강원</td>' +
				'<td>속초출장소</td>' +
				'<td>033) 633-2910</td>' +
			'</tr>' +
			'</tbody>' +
		'</table>' +
	'</div>';


	var arr_qna = [
		{
			question: '신청할 수 있는 조건 및 제한이 있나요?',
			answer: '아래 요건 중 하나에 해당하면 신청할 수 있습니다.<br>- 개인신용폄점이 하위 100분의 20에 해당하는 자 <br>- 근로장려금 수령자 <br>- 기초생활수급권자 또는 차상위계층 <br>상기요건 외에 대출가능여부 및 가능금액은 개인신용평점과 채무등에 따라 차등 적용되므로 자세한 사항은 지점에 문의   바랍니다.',
		},
		{
			question: '대출 상담을 받으려면 어떻게 해야 하나요?',
			answer: table_qna,
		},
		{
			question: '개인사업체 실제 운영자와 사업자등록증의 명의(대표자 성명)가 다른 경우에도 지원되나요?',
			answer: '지원되지 않습니다.<br>반드시 사업자등록 명의자와 실제 운영자가 동일하여야 합니다.',
		},
		{
			question: '창업시 대출이 제한되는 업종이 있나요?',
			answer: '제조업, 금융(보험) 및 관련 서비스업, 사치성향적 소비나 투기 조장하는 업종 등 생활형 서비스업 이외의 업종을 창업 할 예정인 경우는 지원대상에서 제한됩니다.<br>	다만, 제조업의 경우 과자점, 양복점, 양장점, 제분업(떡방아간) 등 제조업종으로 분류되나, 5인 미만 영세 가내수공업은 제외됩니다.',
		},
		{
			question: '대출신청에서 대출금송금까지 시간은 얼마나 걸리나요?',
			answer: '대출 소요기간은 서류 제출일로부터 2~3 영업일 가량 소요됩니다.<br>컨설팅, 교육 등에 따라 기간이 더 연장될 수 있으므로  보다 자세한 사항은 지점으로 문의 바랍니다.',
		},
		{
			question: '기존 대출이 있으면 대출이 불가능한가요?',
			answer: '기존 대출이 있다는 이유만으로는 대출이 불가능하진 않습니다.<br>다만, 기존 대출의 규모ㆍ대출기관ㆍ월상환액 등을 감안하여 대출 적격여부를 심사합니다.',
		},
		{
			question: '담보나 보증인이 필요한가요?',
			answer: '담보나 보증인이 필요하지 않습니다.<br>미소금융 사업은 저신용·저소득층의 자활에 필요한 창업자금ㆍ운영자금을 무담보·무보증으로 지원하는 대출 사업입니다.',
		},
		{
			question: '대출 상담을 대리인이 할 수 있나요?',
			answer: '대출 상담, 신청 및 약정 체결 등은 반드시 본인이 하여야 합니다.',
		},
		{
			question: '차량 구입자금도 대출이 되나요?',
			answer: '사업에 사용되는 1톤 이하의 트럭 또는 기타 상용차*  구입자금을 지원하고 있습니다.<br>*개인택시, 택배용 소형차량 등<br>차량 구입자금 외에 등록세 등의 부대비용, 영업용 번호판 구입 등에 대해서는 지원 불가합니다.<br>자세한 사항은 지점으로 문의 바랍니다.',
		},
		{
			question: '법인도 지원대상이 되나요?',
			answer: '불가합니다.<br>미소금융은 영세자영업자의 자활을 지원하기 위한 사업으로 개인 및 개인사업자를 대상으로 하며, 법인은 지원대상에서 제외됩니다.',
		}
	];

	function creatList(arr) {
		lst_qna.find('dt').remove();
		lst_qna.find('dd').remove();
		blank.addClass('off');
		lst_qna.removeClass('off');
		for(var i = 0; i < arr.length; i++) {
			lst_qna.append('<dt><span class="tit">' + arr[i].question + '</span><button class="btn btn_sm btn_lightgray ir">열기/닫기 토글 버튼</button></dt>');
			lst_qna.append('<dd>' + arr[i].answer + '</dd>');
		}
	}

	creatList(arr_qna);
	
	btn_search.on('click', function(){
		var word = input_search.val();
		var result = arr_qna.filter((lst) => {
			return lst.question.indexOf(word) > -1 || lst.answer.indexOf(word) > -1
		});

		if (result.length > 0) {
			creatList(result);
		} else {
			lst_qna.addClass('off');
			blank.removeClass('off');
		}
	});

	input_search.on("keyup",function(key){
		if(key.keyCode==13) {
			btn_search.click();
		}
	});

	$(document).on('click', '.lst_qna dt',function() {
		$(this).toggleClass('on').next().slideToggle(200);
	});

	$(document).on('click', '.btn_clear',function() {
		creatList(arr_qna);
	});

	$(document).on('propertychange change keyup paste input', '#input_search', function() {
		
		if(input_search.val() === '' || input_search.val() === null) {
			console.log(input_search.val());
			creatList(arr_qna);
		}
	});
});
</script>

</body>
</html>
