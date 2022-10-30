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