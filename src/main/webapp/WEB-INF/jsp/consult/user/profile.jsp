<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="/WEB-INF/jsp/common/include/consult/taglibs.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/doctype.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/style.jsp" %>
<%@ include file="/WEB-INF/jsp/common/include/consult/script.jsp" %>


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

    <!--content-->
    <div class="content">
      <h2 class="con_tit">프로필 설정</h2>
      <div class="info sp20 cellbox">
        <div class="left cell auto">
          <h3 class="s_tit">프로필 정보</h3>
        </div>

        <div class="right cell">
          <button type="button" class="btn btn_white btn_m" data-btn="btnUpdate">정보수정</button>
        </div>
      </div>

      <div class="tb_style tb_htype sp10">
        <table>
          <caption>고마워요 미소금융 조회</caption>
          <colgroup>
            <col style="width: 138px;">
            <col style="width: auto;">
            <col style="width: 138px;">
            <col style="width: auto;">
          </colgroup>
          <tbody>
          <tr>
            <th scope="row">성명</th>
            <td colspan="3">${result.MEMBER_NAME}</td>
          </tr>
          <tr>
            <th scope="row">아이디</th>
            <td colspan="3">${result.MEMBER_ID}</td>
          </tr>
          <tr>
            <th scope="row">권한</th>
            <td colspan="3">${result.MEMBER_AUTHORITY_NAME}</td>
          </tr>
          <tr>
            <th scope="row">연락처</th>
            <td>${result.MEMBER_PHONE}</td>
            <th scope="row">이메일</th>
            <td>${result.MEMBER_EMAIL}</td>
          </tr>
          </tbody>
        </table>
      </div>

    </div>
    <!--//content-->

  </div>
  <!-- //관리자 하단 -->

</div>
<!-- //Wrap-->

<!-- UpdateForm 에 현재 로그인한 유저의 멤버코드 값을 보내기 위함 -->
<form name="frm">
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token }"/>
  <input type="hidden" name="MEMBER_CODE" value="${result.MEMBER_CODE}"/>
  <input type="hidden" name="MEMBER_PW" value="${result.MEMBER_PW}"/>
  <input type="hidden" name="MEMBER_EMAIL" value="${result.MEMBER_EMAIL}"/>
  <input type="hidden" name="MEMBER_PHONE" value="${result.MEMBER_PHONE}"/>
</form>

<script>
  //수정 페이지 이동
  $("[data-btn=btnUpdate]").on("click", function () {
  $("form[name=frm]").attr("action", "/consult/user/profileConfirm");
  $("form[name=frm]").submit();
  });

</script>

</body>
</html>