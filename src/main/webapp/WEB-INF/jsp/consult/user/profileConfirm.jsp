<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>

<!-- 프로퍼티 메시지 -->
<c:set var="messagePwMatchFail"><spring:message code="validation.join.pwdInconsistency"/></c:set>

</head>


<body>
<!--wrap -->
<div class="admin_wrap">
  <!-- 관리자 상단 -->
  <%@ include file="/WEB-INF/jsp/common/include/consult/header.jsp" %>
  <!-- // 관리자 상단 -->

  <!-- 관리자 히단 -->
  <div class="admin_bottom">

    <!-- lnb -->
    <%@ include file="/WEB-INF/jsp/common/include/consult/lnb.jsp" %>
    <!-- // lnb -->

    <!-- UpdateForm 에 현재 로그인한 유저의 멤버코드 값을 보내기 위함 -->
    <form name="frm">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
      <input type="hidden" name="MEMBER_CODE" value="${result.MEMBER_CODE}"/>
      <input type="hidden" name="MEMBER_PW" value="${result.MEMBER_PW}"/>
      <input type="hidden" name="MEMBER_EMAIL" value="${result.MEMBER_EMAIL}"/>
      <input type="hidden" name="MEMBER_PHONE" value="${result.MEMBER_PHONE}"/>

      <!--content-->
      <div class="content">
        <h2 class="con_tit">프로필 설정</h2>
        <h3 class="s_tit">본인 확인</h3>

        <div class="tb_style tb_htype sp10">

          <table>
            <caption>본인 확인</caption>
            <colgroup>
              <col style="width: 138px;">
              <col style="width: auto;">
            </colgroup>
            <tbody>
            <tr>
              <th scope="row">비밀번호</th>
              <td>
                <input id="systemMemberPw" type="password" class="input_text w_mid" placeholder="기존 비밀번호를 입력해주세요." autocomplete="on">
              </td>
            </tr>
            </tbody>
          </table>

          <p class="pwMsg">*본인확인을 위해 현재 비밀번호를 입력하세요.</p>
        </div>

        <div class="btn_area sp30">
          <div class="right_a">
            <button type="button" class="btn btn_blue btn_l" data-btn="btnConfirm">확인</button>
          </div>
        </div>
      </div>
      <!--//content-->

    </form>
    <!-- UpdateForm 에 현재 로그인한 유저의 멤버코드 값을 보내기 위함 -->

  </div>
  <!-- //관리자 하단 -->

</div>
<!-- //Wrap-->

<!-- footer -->
<%@ include file="/WEB-INF/jsp/common/include/consult/footer.jsp" %>
<!-- footer -->

<script>
  // ************************ update 전 체크할 변수들 ************************
  var pwMatchChecked = false;
  // ************************ update 전 체크할 변수들 ************************


  // ************************ 수정 확인 버튼 (패스워드 매칭 체크 한후 페이지 이동) *****************************
  $("[data-btn=btnConfirm]").on("click", function () {
    pwMatch();
    directPage();
  });

  function pwMatch() {
    let pwInput = $("#systemMemberPw").val();
    let pwDb = "${result.MEMBER_PW}";

    if( pwInput == pwDb ) {
      pwMatchChecked = true;
    }
  }

  function directPage(e) {
    // e.preventDefault(); // 자동 form submit 방지
    if( pwMatchChecked ) {
      $("form[name=frm]").attr("action", "/consult/user/profileUpdateForm");
      $("form[name=frm]").submit();
    } else {
      openPopAlert("${messagePwMatchFail}");
      return;
    }
  }
  // ************************ 수정 확인 버튼 (패스워드 매칭 체크 한후 페이지 이동) *****************************

</script>

</body>
</html>