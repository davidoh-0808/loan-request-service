<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 알럿팝업 -->
<div class="pop_wrap alert_msg">
	<div class="pop_contents">
		<p class="msg" id="popMsg"></p>
	</div>
</div>

<!-- 파일다운로드용 -->
<form name="frm_fileDownload" method="post">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }" />
	<input type="hidden" name="FILE_SEQ" />
</form>

<footer id="footer">
	<div class="inner">
<!-- 		<h4 class="sr_only">함께 움직이는 세상</h4> -->
		<nav class="nav_footer">
			<ul class="lst_fmenu">
				<li><a href="javascript:void(0);" class="btn_terms">이용약관</a></li>
				<li><a href="javascript:void(0);" class="btn_policy">개인정보처리방침</a></li>
				<li><a href="javascript:void(0);" class="btn_emailreject">이메일무단수집거부</a></li>
				<li><a href="javascript:void(0);" class="btn_manage_image">영상정보처리기기 운영&middot;관리 방침</a></li>
				<li><a href="javascript:void(0);" class="btn_trust_work">업무 위탁 현황</a></li>
				<li><a href="https://www.hyundaicapital.com/custcent/cnsliqry/CPCCCI0301.hc?gubun=3" target="_blank" class="btn_cyber">사이버감사</a></li>
			</ul>
		</nav>
		<p class="info"><span>서울시 서대문구 통일로 135 충정빌딩 11층 (고객센터 02-361-6000, 상담시간 평일 오전 9시 ~ 오후 5시)</span><span>대표자  윤석훈</span></p>
		<p class="copy">COPYRIGHT&copy;2010 HYUNDAI CORP. ALL RIGHTS RESERVED</p>
	</div>
</footer>

	<!-- 약관 팝업 -->
	<div id="pop_terms" class="pop_wrap" title="이용약관">
		<div class="pop_contents">
			<div class="terms">
				<h5>■ 제1조 (목적)</h5>
				<p>
					본 약관은 현대차미소금융재단(이하 "재단"이라 합니다)이 제공하는 인터넷 서비스(이하 "서비스"라 합니다)를 이용함에 있어 재단과 이용자의 권리, 의무 및 책임사항을 규정함을 목적으로 합니다.
				</p>

				<h5>■ 제2조 (약관의 효력 및 변경)</h5>
				<ol>
					<li>① 이 약관은 재단 웹사이트 또는 전자우편 등의 수단을 이용하여 이를 공시함으로써 효력이 발생됩니다.</li>
					<li>② 합리적인 사유가 발생할 경우 관련법령에 위배되지 않는 범위 안에서 재단은 약관의 내용을 이용자의 사전 동의 없이 개정할 수 있으며, 변경된 약관은 온라인에서 공시함으로써 효력이 발생됩니다. 단, 이용자의 권리 또는 의무 등 중요한 규정의 개정은 사전에 공지합니다.</li>
					<li>③ 이 약관에 동의하는 것은 정기적으로 웹사이트를 방문하여 약관의 변경 사항을 확인하는 것에 동의함을 의미합니다. 변경된 약관에 대한 정보를 알지 못해 발생하는 이용자의 피해는 재단에서 책임지지 않습니다.</li>
				</ol>
	
				<h5>■ 제3조 (약관 외 준칙)</h5>
				<p>
					본 약관에 명시되지 아니한 사항에 대해서는 전기통신기본법, 전기통신사업법, 정보통신망 이용 촉진 등에 관한 법률 및 기타 관련 법령의 규정에 따릅니다.
				</p>
	
				<h5>■ 제4조 (용어의 정의)</h5>
				<p>이 약관에서 사용하는 주요한 용어의 정의는 다음과 같습니다.</p>
				<ol>
					<li>1. 재단 : 현대차미소금융재단</li>
					<li>2. 비밀번호 : 이용자가 게시물의 비밀을 보호하기 위하여 정한 문자와 숫자의 조합을 말합니다.</li>
					<li>3. 이용자 : 현대차미소금융재단이 제공하는 인터넷 서비스를 이용하는 사람을 말합니다.</li>
				</ol>
	
				<h5>■ 제5조 (이용계약의 성립)</h5>
				<ol>
					<li>① 게시물 등록 시 본 약관을 읽고 "동의함" 버튼을 클릭하면 이 약관에 동의하는 것으로 간주됩니다.</li>
					<li>② 이용계약은 서비스 이용자의 약관 동의 후 게시물을 등록함으로써 성립합니다.</li>
				</ol>
	
				<h5>■ 제6조 (이용 신청)</h5>
				<p>게시물 게시를 희망하는 자는 재단이 정한 소정 양식에 따라 요청하는 개인인적사항을 제공하여 이용 신청을 합니다.</p>
	
				<h5>■ 제7조 (이용 신청의 승낙)</h5>
				<ol>
					<li>재단은 제6조에 따른 이용 신청에 대하여 특별한 사정이 없는 한 접수 순서대로 게시됩니다.</li>
					<li>재단은 다음 각 호에 해당하는 사항을 인지하는 경우 동 이용계약 신청을 승낙하지 아니할 수 있습니다.
						<ul>
							<li>1. 이용 신청 시 필요 사항을 허위로 기재하여 신청한 경우</li>
							<li>2. 사회의 안녕과 질서 혹은 미풍양속을 저해할 목적으로 신청한 경우</li>
							<li>3. 기타 재단과 관련없는 사항을 기재하는 경우</li>
						</ul>
					</li>
				</ol>
	
				<h5>■ 제8조 (개인정보의 보호)</h5>
				<ol>
					<li>① 재단은 이용자의 개인정보를 보호하고 존중합니다.</li>
					<li>② 재단은 이용 신청 시 이용자가 제공하는 정보, 기타 서비스 이용 과정에서 수집 되는 정보 등을 통하여 이용자에 관한 정보를 수집하며, 이용자의 개인정보는 본 이용계약의 이행과 본 이용계약 상의 서비스 제공을 위한 목적으로 사용됩니다.</li>
					<li>③ 재단은 서비스 제공과 관련하여 취득한 이용자의 신상정보를 본인의 승낙없이 제3자에게 누설 또는 배포할 수 없으며, 상업적 목적으로 사용할 수 없습니다. 다만, 다음의 각 호에 해당하는 경우에는 그러하지 아니합니다.
						<ul>
							<li>1. 관계 법령에 의하여 수사상 목적으로 정해진 절차와 방법에 따라 관계기관의 요구가 있는 경우</li>
							<li>2. 다른 법률에 특별한 규정이 있는 경우</li>
							<li>3. 정보통신윤리위원회가 관계법령에 의하여 요청하는 경우</li>
						</ul>
					</li>
					<li>④ 이용자의 개인정보에 관해서는 관계법령 및 재단이 정하는 "개인정보처리방침"에 정한 바에 의합니다.
				</ol>
	
				<h5>■ 제9조 (개인정보의 이용)</h5>
				<ol>
					<li>① 재단이 수집하는 개인정보는 서비스의 제공에 필요한 최소한으로 하되, 필요한 경우 보다 더 자세한 정보를 요구할 수 있습니다.</li>
					<li>② 재단은 원칙적으로 수집된 개인정보를 외부업체(이하 위탁 받은 업체)에 제공하지 않으며, 만약 특정서비스의 제공을 위탁하는 경우 서비스 제공에 필요한 이용자의 개인정보를 이용자의 동의를 받아 위탁 받는 업체에 제공할 수 있으며, 서비스 위탁 사실을 명시 합니다. 위탁받는 업체는 제공받은 이용자의 개인정보의 수집, 취급, 관리에 있어 위탁받은 목적 외의 용도로 이를 이용하거나 제3자에게 제공하지 않습니다.</li>
					<li>③ 이용자는 원하는 경우 언제든 재단에 제공한 개인정보의 수집과 이용에 대한 동의를 철회할 수 있으며, 동의의 철회는 본인이 등록한 게시물을 삭제하는 것으로 이루어집니다.</li>
				</ol>
	
				<h5>■ 제10조 (계약 사항의 변경)</h5>
				<ol>
					<li>① 이용자는 본인이 등록한 게시물 조회를 통해 언제든지 본인의 개인정보를 열람하고 수정할 수 있습니다.</li>
					<li>② 이용자는 이용 신청 시 기재한 사항이 변경되었을 경우 온라인으로 수정을 해야 하며 개인정보를 변경하지 아니하여 발생되는 문제의 책임은 이용자에게 있습니다.</li>
					<li>③ 이용자가 원하는 경우 이용계약을 철회할 수 있으며, 이용계약을 철회한 경우 재단의 제반 서비스에 대한 이용에 제약이 따를 수 있습니다. 이용계약의 철회는 본인이 등록한 게시물을 삭제하는 것으로 이루어집니다.</li>
				</ol>
	
				<h5>■ 제11조 (재단의 의무)</h5>
				<ol>
					<li>① 재단은 특별한 사정이 없는 한 이용자가 게시물 등록을 신청한 날에 게시물이 등록될 수 있도록 합니다.</li>
					<li>② 재단은 이 약관에서 정한 바에 따라 계속적이고 안정적인 서비스의 제공을 위하여 지속적으로 노력하며, 설비에 장애가 생기거나 멸실된 때에는 지체 없이 이를 수리 복구하여야 합니다. 다만, 천재지변, 비상사태 또는 그 밖에 부득이한 경우에는 그 서비스를 일시 중단하거나 중지할 수 있습니다.</li>
					<li>③ 재단은 회원의 프라이버시 보호와 관련하여 제8조에 제시된 내용을 준수합니다.</li>
					<li>④ 재단은 이용계약의 체결, 계약사항의 변경 및 삭제 등 이용자와의 계약 관련 절차 및 내용 등에 있어 이용자에게 편의를 제공하도록 노력합니다.</li>
				</ol>
				
				<h5>■ 제12조 (이용자의 의무)</h5>
				<ol>
					<li>① 이용자는 약관에서 규정하는 사항과 이용안내 또는 공지사항 등을 통하여 재단이 공지하는 사항을 준수하여야 하며, 기타 재단의 업무에 방해되는 행위를 하여서는 안됩니다.</li>
					<li>② 이용자의 비밀번호에 관한 모든 관리책임은 이용자에게 있습니다. 이용자가 입력한 비밀번호의 관리 소홀에 의하여 발생하는 모든 결과에 대한 책임은 이용자에게 있습니다.</li>
					<li>③ 이용자는 비밀번호가 부정하게 사용되었다는 사실을 발견한 경우에는 즉시 재단에 신고하여야 하며, 신고를 하지 않아 발생하는 모든 결과에 대한 책임은 이용자에게 있습니다.</li>
					<li>④ 이용자는 재단의 명시적인 동의가 없는 한 서비스의 이용권한, 기타 이용계약상 지위를 타인에게 양도, 증여할 수 없으며, 이를 담보로 제공할 수 없습니다.</li>
					<li>⑤ 이용자는 재단의 사전승낙 없이는 서비스를 이용하여 영업활동을 할 수 없으며, 그 영업활동의 결과와 이용자가 약관에 위반한 영업활동을 하여 발생한 결과에 대하여 재단은 책임을 지지 않습니다. 이용자는 이와 같은 영업활동으로 재단이 손해를 입은 경우 이용자는 재단에 대하여 손해배상 의무를 집니다.</li>
					<li>⑥ 이용자는 서비스 이용과 관련하여 다음 각 호에 해당되는 행위를 하여서는 안됩니다.
						<ul>
							<li>1. 다른 이용자의 게시물, 연락처, 비밀번호 등을 도용하는 행위</li>
							<li>2. 본 서비스를 통하여 얻은 정보를 재단의 사전승낙 없이 이용자의 이용 이외 목적으로 복제하거나 이를 출판 및 방송 등에 사용하거나 제3자 에게 제공하는 행위</li>
							<li>3. 타인의 특허, 상표, 영업비밀, 저작권 기타 지적재산권을 침해하는 내용을 게시, 전자메일 또는 기타의 방법으로 타인에게 유포하는 행위</li>
							<li>4. 공공질서 및 미풍양속에 위반되는 저속, 음란한 내용의 정보, 문장, 도형 등을 전송, 게시, 전자메일 또는 기타의 방법으로 타인에게 유포 하는 행위</li>
							<li>5. 모욕적이거나 위협적이어서 타인의 프라이버시를 침해할 수 있는 내용을 전송, 게시, 전자메일 또는 기타의 방법으로 타인에게 유포하는 행위</li>
							<li>6. 범죄와 결부된다고 판단되는 행위</li>
							<li>7. 다른 이용자의 개인정보를 수집 또는 저장하는 행위</li>
							<li>8. 재단과 관련 없는 사항, 허위사실 등을 유포하는 행위</li>
							<li>9. 기타 관계법령에 위배되는 행위</li>
						</ul>
					</li>
				</ol>
	
				<h5>■ 제13조 (서비스 이용 범위)</h5>
				<p>이용자는 누구나 재단의 사이트를 이용할 수 있습니다.</p>
	
				<h5>■ 제14조 (정보의 제공)</h5>
				<p>재단은 이용자가 서비스 이용 중 필요가 있다고 인정되는 다양한 정보를 공지사항이나 전자우편 등의 방법으로 이용자에게 제공할 수 있습니다.</p>

				<h5>■ 제15조 (요금 및 유료정보 및 결제 등)</h5>
				<p>www.hyundaismile.or.kr 에서 제공하는 서비스는 무료입니다.</p>
				
				<h5>■ 제16조 (이용자의 게시물)</h5>
				<ol>
					<li>① 재단은 이용자가 게시하거나 등록하는 서비스 내의 내용물이 다음 각 호에 해당한다고 판단되는 경우에 사전통지 없이 삭제할 수 있습니다.
						<ul>
							<li>1. 다른 회원 또는 제3자를 비방하거나 중상모략으로 명예를 손상시키는 내용인 경우</li>
							<li>2. 공공질서 및 미풍양속에 위반되는 내용인 경우</li>
							<li>3. 범죄적 행위에 결부된다고 인정되는 내용인 경우</li>
							<li>4. 재단의 저작권, 제3자의 저작권 등 기타 권리를 침해하는 내용인 경우</li>
							<li>5. 재단에서 규정한 게시기간이나 용량을 초과한 경우</li>
							<li>6. 이용자가 자신의 홈페이지와 게시판에 음란물을 게재하거나 음란사이트를 링크하는 경우</li>
							<li>7. 게시판의 성격에 부합하지 않는 게시물의 경우</li>
							<li>8. 기타 관계법령에 위반된다고 판단되는 경우</li>
						</ul>
					</li>
				</ol>
				
				<h5>■ 제17조 (게시물의 저작권)</h5>
				<ol>
					<li>① 서비스에 게재된 자료에 대한 권리는 다음 각 호와 같습니다.
						<ul>
							<li>1. 게시물에 대한 권리와 책임은 게시자에게 있으며 재단은 게시자의 동의 없이는 이를 서비스 내 게재 이외에 영리적 목적으로 사용할 수 없습니다. 단, 비영리적인 경우에는 그러하지 아니하며 또한 재단은 서비스 내의 게재권을 갖습니다.</li>
							<li>2. 이용자는 서비스를 이용하여 얻은 정보를 가공, 판매하는 행위 등 서비스에 게재된 자료를 상업적으로 사용할 수 없습니다.</li>
						</ul>
					</li>
				</ol>
				
				<h5>제18조 (서비스 이용시간)</h5>
				<ol>
					<li>① 서비스의 이용은 재단의 업무상 또는 기술상 특별한 지장이 없는 한 연중무휴 1일 24시간 가능함을 원칙으로 합니다. 다만 정기 점검 등의 필요로 재단이 정한 날이나 시간은 그러하지 않습니다.</li>
					<li>② 재단은 서비스를 일정범위로 분할하여 각 범위별로 이용가능 시간을 별도로 정할 수 있습니다. 이 경우 사전에 공지를 통해 그 내용을 알립니다.</li>
				</ol>
				
				<h5>제19조 (서비스 이용 책임)</h5>
				<p>이용자는 재단의 서비스를 이용하여 상품을 판매하는 영업활동을 할 수 없으며 특히 해킹, 돈벌이 광고, 음란 사이트 등을 통한 상업행위, 상용S/W 불법배포 등을 할 수 없습니다. 이를 어기고 발생한 영업활동의 결과 및 손실, 관계기관에 의한 구속 등 법적 조치 등에 관해서는 재단이 책임을 지지 않습니다.</p>
				
				<h5>제20조 (서비스 제공의 중지 등)</h5>
				<ol>
					<li>① 재단은 다음 각 호에 해당하는 경우 서비스 제공을 중지할 수 있습니다.
						<ul>
							<li>1. 서비스용 설비의 보수 등 공사로 인한 부득이한 경우</li>
							<li>2. 전기통신사업법에 규정된 기간통신사업자가 전기통신 서비스를 중지 했을 경우</li>
							<li>3. 기타 불가항력적 사유가 있는 경우</li>
						</ul>
					</li>
					<li>② 이용자의 비밀번호에 관한 모든 관리책임은 이용자에게 있습니다. 이용자가 입력한 비밀번호의 관리 소홀에 의하여 발생하는 모든 결과에 대한 책임은 이용자에게 있습니다.</li>
					<li>③ 이용자는 비밀번호가 부정하게 사용되었다는 사실을 발견한 경우에는 즉시 재단에 신고하여야 하며, 신고를 하지 않아 발생하는 모든 결과에 대한 책임은 이용자에게 있습니다.</li>
				</ol>
				
				<h5>제21조 (계약 해지 및 이용 제한)</h5>
				<ol>
					<li>① 이용자가 이용 계약을 해지하고자 하는 경우에는 이용자 본인이 등록한 게시물을 삭제하여야 합니다.</li>
					<li>② 재단은 회원이 다음 각 호에 해당하는 행위를 하였을 경우 사전통지 없이 이용계약을 해지하거나 또는 기간을 정하여 서비스 이용을 중지할 수 있습니다.
						<ul>
							<li>1. 타인의 개인정보, 비밀번호를 도용한 경우</li>
							<li>2. 타인의 명예를 손상시키거나 불이익을 주는 행위를 한 경우</li>
							<li>3. 재단, 다른 이용자 또는 제3자의 지적재산권을 침해하는 경우</li>
							<li>4. 공공질서 및 미풍양속에 저해되는 내용을 고의로 유포시킨 경우</li>
							<li>5. 이용자가 국익 또는 사회적 공익을 저해할 목적으로 서비스 이용을 계획 또는 실행하는 경우</li>
							<li>6. 서비스 운영을 고의로 방해한 경우</li>
							<li>7. 서비스의 안정적 운영을 방해할 목적으로 다량의 정보를 전송하거나 광고성 정보를 전송하는 경우</li>
							<li>8. 정보통신설비의 오작동이나 정보의 파괴를 유발시키는 컴퓨터 바이러스 프로그램 등을 유포하는 경우</li>
							<li>9. 정보통신윤리위원회 등 외부기관의 시정요구가 있거나 선거운동, 정치적이슈문제 등 재단 고유의 업무와 관련 없는 게시물을 게재한 경우</li>
							<li>10. 재단의 서비스를 이용하여 얻은 정보를 재단의 사전 승낙 없이 복제 또는 유통시키거나 상업적으로 이용하는 경우</li>
							<li>11. 회원이 자신의 홈페이지와 게시판에 음란물을 게재하거나 음란 사이트를 링크하는 경우</li>
							<li>12. 본 약관을 포함하여 기타 재단이 정한 이용 조건에 위반한 경우</li>
						</ul>
					</li>
				</ol>
				
				<h5>제22조 (손해배상)</h5>
				<p>재단은 서비스 이용과 관련하여 이용자에게 발생한 손해에 관하여 책임을 지지 않습니다. 단, 재단의 과실로 인한 손해가 명백하다고 법원에서 판결한 경우는 예외로 합니다.</p>
				
				<h5>제23조 (면책조항)</h5>
				<ol>
					<li>① 재단은 천재지변 또는 이에 준하는 불가항력으로 인하여 서비스를 제공할 수 없는 경우에는 서비스 제공에 관한 책임이 면제됩니다.</li>
					<li>② 재단은 이용자의 귀책사유로 인한 서비스 이용의 장애에 대하여 책임을 지지 않습니다.</li>
					<li>③ 재단은 이용자가 서비스를 이용하여 기대하는 수익을 상실한 것이나 서비스를 통하여 얻은 자료로 인한 손해에 관하여 책임을 지지 않습니다.</li>
					<li>④ 재단은 이용자가 서비스에 게재한 정보, 자료, 사실의 신뢰도, 정확성 등 내용에 관하여는 책임을 지지 않습니다.</li>
					<li>⑤ 재단은 서비스 이용과 관련하여 이용자에게 발생한 손해 가운데 이용자의 고의, 과실에 의한 손해에 대하여 책임을 지지 않습니다.</li>
				</ol>
				
				<h5>제24조 (관할법원)</h5>
				<p>서비스 이용으로 발생한 분쟁에 대해 소송이 제기될 경우 재단의 본사 소재지를 관할하는 법원을 전속 관할법원으로 합니다.</p>
				<br>
				<p>부칙 (시행일) 이 약관은 2015년 1월 1일부터 시행합니다.</p>
				
			</div>
		</div>
	</div>
	
	<div id="pop_policy" class="pop_wrap" title="개인정보 처리방침">
		<div class="pop_contents">
			<div class="desc">
				현대차미소금융재단 사이트를 이용해 주셔서 감사합니다. 본 약관은 현대차 미소금융재단과 이용자의 권리&middot;의무 및 책임사항을 규정함을 목적으로 합니다.
			</div>
			<div class="terms">
				<h4>개인정보처리방침</h4>
				<p>
					현대차미소금융재단은 개인정보보호법 제30조에 따라 고객의 개인정보 보호 및 권익을 보호하고 개인정보와 관련한 고객의 고충을 원활하게 처리할 수 있도록 다음과 같은 처리방침을 두고 있습니다.
				</p>
	
				<h5>■ 제1조(개인정보의 처리 목적)</h5>
				<p>
					현대차미소금융재단(이하"재단")은 개인정보를 다음 각 호의 목적을 위해 처리합니다.<br />처리한 개인정보는 다음의 목적 외의 용도로는 사용되지 않으며, 이용 목적이 변경될 시에는 사전 동의를 구할 예정입니다.
				</p>
				<ol>
					<li>
						1. 신용정보 제공ㆍ이용
						<ul>
							<li>- (금융)거래와 관련하여 개인신용정보 활용, 금융사고 예방 조사, 분쟁 해결, 민원 처리 등의 목적으로 개인(신용)정보를 처리합니다.</li>
						</ul>
					</li>
					<li>
						2. (금융)거래 관계
						<ul>
							<li>- 대출 등 지원여부 결정, 복지사업 중복지원 여부 조회, 대출자에 대한 컨설팅 등 사후관리 지원, 금융사고 예방 조사, 분쟁 해결 및 민원 처리 등의 목적으로 개인정보를 처리합니다.</li>
						</ul>
					</li>
					<li>
						3. 봉사단 활동 등 관리
						<ul>
							<li>- 봉사자 및 수혜자의 봉사활동 등 신청과 관리업무 등을 목적으로 개인정보를 처리합니다.</li>
						</ul>
					</li>
					<li>
						4. 홈페이지 관리
						<ul>
							<li>- 홈페이지 이용자의 질문에 관한 응답과 서비스 제공을 목적으로 개인정보를 처리합니다.</li>
						</ul>
					</li>
					<li>
						5. 직원채용ㆍ인사관리 등
						<ul>
							<li>- 재단 직원채용ㆍ인사관리 등을 목적으로 개인정보를 처리합니다.</li>
						</ul>
					</li>
				</ol>
	
				<h5>■ 제2조(개인정보의 처리 및 보유 기간)</h5>
				<ol>
					<li>① 대출 등 지원여부 결정을 목적으로 처리하는 개인(신용)정보는 계약의 효력이 종료되는 날까지 보유ㆍ이용됩니다. 단, 목적 달성일 후에는 금융사고 예방 조사, 분쟁 해결, 민원처리 및 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
					<li>② 복지사업 중복지원 여부 조회 등은 제공받은 날로부터 조회 목적을 달성한 날까지 보유ㆍ이용됩니다. 단, 목적 달성 후에는 금융사고 예방 조사, 분쟁 해결, 민원처리 및 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
					<li>③ 홈페이지 이용자로부터 수집된 개인정보는 이용자의 홈페이지 이용계약 성립 시부터 이용계약 해지 시까지 보유ㆍ이용됩니다. 단, 홈페이지 이용자의 이용계약 해지 후에도 사고 예방 조사, 분쟁 해결, 민원처리, 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
					<li>④ 재단 임직원 등의 개인정보는 퇴직 후에도 사고 예방 조사, 분쟁 해결, 민원처리, 법령상 의무이행 만을 위하여 보유ㆍ이용됩니다.</li>
				</ol>
	
				<h5>■ 제3조(개인정보 수집 출처 등 고지)</h5>
				<ol>
					<li>① 재단은 정보주체 이외로부터 수집한 개인정보를 처리하는 때에는 정당한 사유가 없는 한 정보주체의 요구가 있은 날로부터 3일 이내에 수집 출처, 처리 목적, 개인정보 처리의 정지를 요구할 권리가 있다는 사실을 정보주체에게 알립니다.</li>
					<li>② 개인정보 보호법 제20조 제2항 각 호에 근거하여 제1항에 따른 정보주체의 요구를 거부하는 경우에는 정당한 사유가 없는 한 정보주체의 요구가 있은 날로부터 3일 이내에 그 거부의 근거와 사유를 정보주체에게 알립니다.</li>
				</ol>
	
				<h5>■ 제4조(개인정보의 제3자 제공)</h5>
				<ol>
					<li>1. 고객이 사전에 제3자 제공 및 공개에 동의한 경우</li>
					<li>2. 다른 법률에 특별한 규정이 있는 경우</li>
					<li>3. 고객 또는 그 법정대리인이 의사표시를 할 수 없는 상태에 있거나 주소불명 등으로 사전 동의를 받을 수 없는 경우로서 명백히 고객 또는 제3자의 급박한 생명, 신체, 재산의 이익을 위하여 필요하다고 인정되는 경우</li>
					<li>4. 통계작성 및 학술연구 등의 목적을 위하여 필요한 경우로서 특정 개인을 알아볼 수 없는 형태로 개인정보를 제공하는 경우</li>
				</ol>
	
				<h5>■ 제5조(고객의 권리ㆍ의무 및 그 행사방법)</h5>
				<ol>
					<li>① 고객은 재단이 처리하는 자신 및 14세 미만 아동(법정대리인만 해당)의 개인정보의 열람을 요구할 수 있습니다.</li>
					<li>② 자신의 개인정보를 열람한 고객은 사실과 다르거나 확인할 수 없는 개인정보에 대하여 재단에 정정 또는 삭제를 요구할 수 있습니다. 다만, 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.</li>
					<li>③ 고객은 재단에 대하여 자신의 개인정보 처리의 정지를 요구할 수 있습니다. 다만 다음 각 호의 어느 하나에 해당하거나 다른 정당한 사유가 있는 경우에는 재단은 해당 사유를 고객에게 알리고, 처리정지 요구를 거절할 수 있습니다.
						<ol>
							<li>1. 법률에 특별한 규정이 있거나 법령상 의무를 준수하기 위하여 불가피한 경우</li>
							<li>2. 다른 사람의 생명ㆍ신체를 해할 우려가 있거나 다른 사람의 재산과 그 밖의 이익을 부당하게 침해할 우려가 있는 경우</li>
							<li>3. 개인정보를 처리하지 아니하면 고객과 약정한 서비스를 제공하지 못하는 등 계약의 이행이 곤란한 경우로서 고객이 그 계약의 해지 의사를 명확하게 밝히지 아니한 경우</li>
						</ol>
					</li>
				</ol>
	
				<h5>■ 제6조(개인정보처리 위탁)</h5>
				<ol>
					<li>① 재단은 다음 각 호와 같이 개인정보의 처리를 위탁하고 있습니다.
						<ol>
							<li>
								1. 수탁업체<br />- 아이언마운틴 코리아(유)
							</li>
							<li>
								2. 수탁의 목적<br />- 재단 내부 업무자료(대출원장 및 회계서류, 장부 등)
							</li>
							<li>3. 개인정보 제공항목<br />- 개인식별정보: 성명, 주민번호, 계좌번호, 주소, 연락처 등</li>
							<li>4. 개인정보 보유 기간<br />- 개인(신용)정보는 제공된 날로부터 동의 철회 또는 제공된 목적을 달성할 때까지 보유ㆍ이용됩니다. 동의 철회 또는 제공된 목적 달성 후에는 위에 기재된 이용목적과 관련된 사고 조사, 분쟁해결, 민원처리, 법령상 의무이행을 위하여 필요한 범위 내에서만 보유ㆍ이용됩니다.</li>
						</ol>
					</li>
					<li>② 위탁계약 시 개인정보보호 관련 법규의 준수, 개인정보에 관한 제3자 제공 금지 및 책임부담 등을 명확히 규정하고, 당해 계약내용을 서면 및 전자 보관하고 있습니다.</li>
				</ol>
	
				<h5>■ 제7조(수집하는 개인정보의 항목)</h5>
				<p>재단은 필요한 최소한의 개인정보를 다음 각호와 같이 수집하고 있습니다.</p>
				<ol>
					<li>1. 필수적 정보
						<ul>
							<li>◎ 개인식별정보<br />- 성명, 주민등록번호 등 고유식별정보, 국적, 직업, 주소 전자우편 주소 전화번호 등 연락처</li>
							<li>◎ (금융)거래정보<br />- 상품종류, 거래조건(만기, 담보 등), 거래일시, 금액 등 거래 설정 및 내역 정보</li>
							<li>◎ 신용평가를 위한 정보(여신거래에 한정)<br />- 신용능력정보: 채무ㆍ소득의 총액, 납세실적<br />- 신용도판단정보: 연체, 대위변제, 대지급, 부도, 관련인 발생사실 등</li>
							<li>◎ 기타 금융거래의 설정 유지ㆍ이행ㆍ관리를 위한 상담 등을 통해 생성되는 정보<br />※ 재단은 고객의 사생활을 침해할 우려가 있는 민감정보에 대해서는 원칙적으로 수집하지 않으며, 필요한 경우 고객의 별도 동의를 받아 수집하고 동의 목적을 위해서만 제한적으로 이용합니다. 다만, 민감정보의 정확성, 최신성을 주기적으로 확인합니다.</li>
						</ul>
					</li>
					<li>2. 수집방법
						<ol>
							<li>① 재단은 제2조에 따른 개인정보의 보유기간이 경과된 경우에는 정당한 사유가 없는 한 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 정당한 사유가 없는 한 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.</li>
							<li>② 개인정보가 기록된 출력물, 서면 등은 파쇄 또는 소각의 방법으로 파기하고, 전자적 파일형태의 개인정보는 복원이 불가능한 방법으로 영구 삭제하는 방법으로 파기합니다.</li>
						</ol>
					</li>
				</ol>
				
				<h5>■ 제8조(개인정보의 파기)</h5>
				<ol>
					<li>① 재단은 제2조에 따른 개인정보의 보유기간이 경과된 경우에는 정당한 사유가 없는 한 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 정당한 사유가 없는 한 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.</li>
					<li>② 개인정보가 기록된 출력물, 서면 등은 파쇄 또는 소각의 방법으로 파기하고, 전자적 파일형태의 개인정보는 복원이 불가능한 방법으로 영구 삭제하는 방법으로 파기합니다.</li>
				</ol>
	
				<h5>■ 제9조(개인정보의 안전성 확보 조치)</h5>
				<p>재단은 개인정보보호법 제29조에 따라 다음 각 호와 같이 안전성 확보에 필요한 기술적/관리적 및 물리적 조치를 하고 있습니다.</p>
				<ol>
					<li>1. 개인정보의 암호화
						<ul>
							<li>고객의 개인정보 중 비밀번호는 암호화 되어 저장 및 관리되고 있어, 본인만이 알 수 있으며 중요한 데이터는 파일 및 전송 데이터를 암호화하여 사용하는 등의 별도 보안기능을 사용하고 있습니다.</li>
						</ul>
					</li>
					<li>2. 해킹 등에 대비한 기술적 대책
						<ul>
							<li>재단은 해킹이나 컴퓨터 바이러스 등에 의한 개인정보 유출 및 훼손을 막기 위하여 보안프로그램을 설치하고 주기적인 갱신ㆍ점검을 하며 외부로부터 접근이 통제된 구역에 시스템을 설치하고 기술적/물리적으로 감시 및 차단하고 있습니다.</li>
						</ul>
					</li>
					<li>3. 개인정보처리시스템 접근 제한
						<ul>
							<li>개인정보를 처리하는 데이터베이스시스템에 대한 접근권한의 부여, 변경, 말소를 통하여 개인정보에 대한 접근통제를 위하여 필요한 조치를 하고 있으며 침입차단시스템을 이용하여 외부로부터의 무단 접근을 통제하고 있습니다.</li>
						</ul>
					</li>
					<li>4. 개인정보 취급 직원의 최소화 및 교육
						<ul>
							<li>개인정보를 취급하는 직원을 지정하고 담당자에 한정시켜 최소화 하여 개인정보를 관리하는 대책을 시행하고 있습니다.</li>
						</ul>
					</li>
				</ol>
	
				<h5>■ 제10조(개인정보 처리방침의 변경)</h5>
				<p>재단은 개인정보 처리방침을 변경하는 경우에는 변경 및 시행의 시기, 변경된 내용을 지속적으로 공개하며, 변경된 내용은 고객이 쉽게 확인할 수 있도록 변경 전ㆍ후를 비교하여 공개합니다.</p>
	
				<h5>■ 제11조(임직원의 개인정보 처리)</h5>
				<p>재단 임직원의 개인정보 처리에 관하여도 본 개인정보 처리방침의 규정이 준용됩니다.</p>
	
				<h5>■ 제12조 (권익침해 구제방법)</h5>
				<p>고객은 개인정보침해로 인한 신고나 상담이 필요하신 경우 아래 기관에 문의하시기 바랍니다.</p>
				<ol>
					<li>1. 개인정보분쟁조정위원회</li>
					<li>2. 한국인터넷진흥원 개인정보침해신고센터(privacy.kisa.or.kr / 국번없이 118)</li>
					<li>3. 정보보호마크인증위원회(www.eprivacy.or.kr / 02-580-0533~4)</li>
					<li>4. 대검찰청 첨단범죄수사과 (www.spo.go.kr / 02-3480-2000)</li>
					<li>5. 경찰청 사이버테러대응센터 (www.ctrc.go.kr / 1566-0112)</li>
				</ol>
	
				<h5>■ 제13조(개인정보 보호책임자 등)</h5>
				<p>재단은 개인정보 보호법 제31조 제1항에 따라 고객의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 관련 부서 및 개인정보보호실무자를 지정하고 있습니다.</p>
				<ul>
					<li>개인정보보호책임자성명 : 경영관리팀장</li>
					<li>전화번호 : 02-361-6030</li>
					<li>이메일 : hsc032@hyundaismile.or.kr</li>
					<li>팩스 : 070-4850-8537</li>
				</ul>
	
				<ul>
					<li>고객서비스담당부서 : 경영관리팀</li>
					<li>전화번호 : 02-361-6000</li>
					<li>* 근무시간 : 09:00 ~ 18:00</li>
				</ul>
	
				<h5>■ 고지의 의무</h5>
				<p>현 개인정보처리방침 내용 추가, 삭제 및 수정이 있을 시에는 개정 최소 7일전부터 홈페이지의 '공지사항'을 통해 고지할 것입니다.</p>
			</div>
		</div>
	</div>
	
	<div id="pop_emailreject" class="pop_wrap" title="이메일무단수집거부">
		<div class="pop_contents">
			<div class="terms mt0">
				<p>
					정보통신망 이용촉진 및 정보보호 등에 관한 법률 제50조의2,
					제50조의7 등에 근거하여,
				</p>
				<ol>
					<li>
						① 사전동의 없이 현대차미소금융재단 사이트에서 자동으로 전자우편
						주소를 수집하는 프로그램 그 밖의 기술적 장치를 이용하여
						전자우편주소를 수집하는것을 거부합니다.
					</li>
				</ol>
			</div>
		</div>
	</div>
	
	<div id="pop_manage_image" class="pop_wrap" title="영상정보처리기기 운영&middot;관리 방침">
		<div class="pop_contents">
			<div class="desc">
				현대차미소금융재단 사이트를 이용해 주셔서 감사합니다. 본 약관은 현대차 미소금융재단과 이용자의 권리&middot;의무 및 책임사항을 규정함을 목적으로 합니다.
			</div>
			<div class="terms">
				<p>현대차미소금융재단(이하 재단이라 함)은 영상정보처리기기 운영⋅관리 방침을 통해 재단에서 처리하는 영상정보가 어떠한 용도와 방식으로 이용⋅관리되고 있는지 알려드립니다.</p>
				<h5>1. 영상정보처리기기의 설치 근거 및 설치 목적</h5>
				<p>재단은「개인정보 보호법」제25조 제1항에 따라 다음과 같은 목적으로 영상정보처리기기를 설치⋅운영 합니다.</p>
				<ul>
					<li>- 시설안전 및 화재 예방</li>
					<li>- 고객의 안전을 위한 범죄 예방</li>
				</ul>
				<h5>2. 설치 대수, 설치 위치 및 촬영범위</h5>
				<!-- 정보안내 가로형  테이블 -->
				<div class="table_list ac">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
							<col width="30%">
							<col width="*">
						</colgroup>
						<thead>
						<tr>
							<th>설치 대수</th>
							<th>설치 위치 및 촬영 범위</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>총 6대</td>
							<td>지점 내부 상담창구</td>
						</tr>
						</tbody>
					</table>
				</div>
				<h5>3. 관리책임자 및 접근권한자</h5>
				<p>귀하의 영상정보를 보호하고 개인영상정보와 관련한 불만을 처리하기 위하여 아래와 같이 개인영상정보 보호책임자를 두고 있습니다.</p>
				<!-- 정보안내 가로형  테이블 -->
				<div class="table_list ac">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
							<col width="20%">
							<col width="20%">
							<col width="20%">
							<col width="20%">
							<col width="*">
						</colgroup>
						<thead>
						<tr>
							<th></th>
							<th>이름</th>
							<th>직위</th>
							<th>소속</th>
							<th>연락처</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>관리책임자</td>
							<td>조도근</td>
							<td>경영관리팀장</td>
							<td>사무국</td>
							<td>02-361-6030</td>
						</tr>
						<tr>
							<td>접근권한자</td>
							<td>노광일</td>
							<td>대리</td>
							<td>사무국</td>
							<td>02-361-6042</td>
						</tr>
						</tbody>
					</table>
				</div>
				<h5>4. 영상정보의 촬영시간, 보관기간, 보관장소 및 처리방법</h5>
				<!-- 정보안내 가로형  테이블 -->
				<div class="table_list ac">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
							<col width="30%">
							<col width="30%">
							<col width="*">
						</colgroup>
						<thead>
						<tr>
							<th>촬영시간</th>
							<th>보관기간</th>
							<th>보관장소</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>24시간</td>
							<td>촬영일로부터 30일</td>
							<td>사무국 전산실 DVR</td>
						</tr>
						</tbody>
					</table>
				</div>
				<p>- 처리방법 : 개인영상정보의 목적 외 이용, 제3자 제공, 파기, 열람 등 요구에 관한 사항을 기록⋅관리하고, 보관기간 만료 시 복원이 불가능한 방법으로 영구 삭제(출력물의 경우 파쇄 또는 소각)합니다.</p>
				<h5>5. 개인영상정보의 확인 방법 및 장소에 관한 사항</h5>
				<ul>
					<li>- 확인 방법 : 영상정보 관리책임자에게 미리 연락하고 재단을 방문하시면 확인 가능합니다.</li>
					<li>- 확인 장소 : 사무국 경영관리팀</li>
				</ul>
				<h5>6. 정보주체의 영상정보 열람 등 요구에 대한 조치</h5>
				<p>귀하는 개인영상정보에 관하여 열람 또는 존재확인⋅삭제를 원하는 경우 언제든지 영상정보처리기기 운영자에게 요구하실 수 있습니다. 단, 귀하가 촬영된 개인영상정보 및 명백히 정보주체의 급박한 생명, 신체, 재산의 이익을 위하여 필요한 개인영상정보에 한정됩니다.<br />
				재단은 개인영상정보에 관하여 열람 또는 존재확인⋅삭제를 요구한 경우 지체 없이 필요한 조치를 하겠습니다.</p>
				<h5>7. 영상정보의 안전성 확보조치</h5>
				<p>재단은 처리하는 영상정보는 암호화 조치 등을 통하여 안전하게 관리되고 있습니다. 또한 재단은 개인영상정보보호를 위한 관리적 대책으로서 개인정보에 대한 접근 권한을 차등부여하고 있고, 개인영상정보의 위⋅변조 방지를 위하여 개인영상정보의 생성 일시, 열람 시 열람 목적⋅열람자⋅열람 일시 등을 기록하여 관리하고 있습니다. 이 외에도 개인영상정보의 안전한 물리적 보관을 위하여 잠금장치를 설치하고 있습니다.</p>
				<h5>8. 개인정보 처리방침 변경에 관한 사항</h5>
				<p>이 영상정보처리기기 운영⋅관리방침은 2017년 5월 18일에 제정되었으며 법령ㆍ정책 또는 보안기술의 변경에 따라 내용의 추가ㆍ삭제 및 수정이 있을 시에는 시행하기 최소 7일전에 재단 홈페이지를 통해 변경사유 및 내용 등을 공지하도록 하겠습니다.<br />- 공고일자 : 2017년 5월 18일 / 시행일자 : 2017년 5월 18일</p>
			</div>
		</div>
	</div>
	
	<div id="pop_trust_work" class="pop_wrap" title="업무위탁현황">
		<div class="pop_contents">
			<div class="terms mt0">
				<!-- 정보안내 가로형  테이블 -->
				<div class="table_list ac">
					<table>
						<caption class="sr_only">리스트</caption>
						<colgroup>
							<col width="20%">
							<col width="*">
							<col width="20%">
							<col width="20%">
						</colgroup>
						<thead>
						<tr>
							<th>업체명</th>
							<th>제공하는 항목</th>
							<th>제공받는 자의 이용 목적</th>
							<th>보유 및 이용기간</th>
						</tr>
						</thead>
						<tbody>
						<tr>
							<td>아이언마운틴(유)</td>
							<td>대출서류(개인정보 포함)</td>
							<td>문서보관</td>
							<td>수신 후 5년</td>
						</tr>
						<tr>
							<td>현대오토에버</td>
							<td>성명, 주민등록번호, 주소, 핸드폰번호, 이메일, 직장명, 직장주소,
								전화번호, 계좌번호, 그 외 운영에 필요한 정보</td>
							<td>시스템 운영</td>
							<td>보유안함</td>
						</tr>
						<tr>
							<td>캡스</td>
							<td>내부 영상정보</td>
							<td>방범보안</td>
							<td>보유안함</td>
						</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>