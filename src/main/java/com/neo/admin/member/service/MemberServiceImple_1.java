package com.neo.admin.member.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import com.neo.common.vo.MasterKeyVO;
import com.neo.common.vo.MemberVO;
import com.neo.mappers.CommonMapper;
import com.neo.mappers.MemberMapper;
import com.neo.util.UtilCommon;
import com.neo.util.UtilCrypt;
import com.neo.util.UtilJsonResult;

@Service("adminMemberService")
@Transactional
public class MemberServiceImple implements MemberService {

	@Autowired MessageSource messageSource;
	@Autowired LocaleResolver localeResolver;
	@Resource(name="memberMapper")
	private MemberMapper memberMapper;
	@Resource(name="commonMapper")
	private CommonMapper commonMapper;
	
	@Value("${crypt.privatekey.sha256}") String CRYPT_PRIVATEKEY_SHA256;
	
	@Override
	public int memberListCount(MemberVO paramVO) throws Exception {
		return memberMapper.memberListCount(paramVO);
	}
	@Override
	public List<MemberVO> memberList(MemberVO paramVO) throws Exception {
		return memberMapper.memberList(paramVO);
	}
	@Override
	public MemberVO memberDetail(MemberVO paramVO) throws Exception {
		return memberMapper.memberDetail(paramVO);
	}
	@Override
	public JSONObject memberInsert(MemberVO paramVO, HttpServletRequest request) throws Exception {
		
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

		// S:유효성체크
		// 아이디 체크
		if(UtilCommon.isEmpty(paramVO.getMEMBER_ID())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"아이디"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// 비밀번호 체크
		if(UtilCommon.isEmpty(paramVO.getMEMBER_PW())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"비밀번호"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// 이름 체크
		if(UtilCommon.isEmpty(paramVO.getMEMBER_NAME())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이름"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// 전화번호 체크
		if(UtilCommon.isEmpty(paramVO.getMEMBER_PHONE())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"전화번호"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// 이메일 체크
		if(UtilCommon.isEmpty(paramVO.getMEMBER_EMAIL())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이메일"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크
		
		// S: 코드 생성
		String masterKey = "";
		MasterKeyVO masterKeyVO = new MasterKeyVO();		
		masterKeyVO.setKEY_GBN("MEMBER_CODE");
		masterKey = commonMapper.getFnGetMasterKey(masterKeyVO);
		paramVO.setMEMBER_CODE(masterKey);
		// E: 코드 생성
		
		//비밀번호 해쉬화
		String member_pw = UtilCrypt.hashSHA256(paramVO.getMEMBER_PW(), CRYPT_PRIVATEKEY_SHA256);
		paramVO.setMEMBER_PW(member_pw);
		
		// 권한
		if(UtilCommon.isNotEmpty(paramVO.getMenu_auth_arr())) { 
			for (String item: paramVO.getMenu_auth_arr()) {
				if ("ACCOUNT_USE".equalsIgnoreCase(item)) {
					paramVO.setACCOUNT_USE("1");
				}
				if ("THANKS_USE".equalsIgnoreCase(item)) {
					paramVO.setTHANKS_USE("1");
				}
				if ("NEWS_USE".equalsIgnoreCase(item)) {
					paramVO.setNEWS_USE("1");
				}
				if ("FINAN_USE".equalsIgnoreCase(item)) {
					paramVO.setFINAN_USE("1");
				}
			}
		}
		paramVO.setREPORT_USE("1");
		paramVO.setIN_USER(paramVO.getLoginCode());
		paramVO.setUP_USER(paramVO.getLoginCode());
		memberMapper.memberInsert(paramVO);

		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}
	@Override
	public JSONObject memberUpdate(MemberVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

		// S:유효성체크
		// 회원코드
		if(UtilCommon.isEmpty(paramVO.getMEMBER_CODE())) {
			rMsg = messageSource.getMessage("validation.empty.required", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// 이름 체크
		if(UtilCommon.isEmpty(paramVO.getMEMBER_NAME())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이름"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// 전화번호 체크
		if(UtilCommon.isEmpty(paramVO.getMEMBER_PHONE())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"전화번호"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// 이메일 체크
		if(UtilCommon.isEmpty(paramVO.getMEMBER_EMAIL())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이메일"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// 수정가능데이타 존재여부확인
		MemberVO result = new MemberVO();
		result = memberMapper.memberDetail(paramVO);
		if(UtilCommon.isEmpty(result)) {
			rMsg = messageSource.getMessage("validation.update.empty.object", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크
		
		//비밀번호 해쉬화
		if(UtilCommon.isNotEmpty(paramVO.getMEMBER_PW())) {
			String member_pw = UtilCrypt.hashSHA256(paramVO.getMEMBER_PW(), CRYPT_PRIVATEKEY_SHA256);
			paramVO.setMEMBER_PW(member_pw);
		}
		
		// 권한
		if(UtilCommon.isNotEmpty(paramVO.getMenu_auth_arr())) { 
			for (String item: paramVO.getMenu_auth_arr()) {
				if ("ACCOUNT_USE".equalsIgnoreCase(item)) {
					paramVO.setACCOUNT_USE("1");
				}
				if ("THANKS_USE".equalsIgnoreCase(item)) {
					paramVO.setTHANKS_USE("1");
				}
				if ("NEWS_USE".equalsIgnoreCase(item)) {
					paramVO.setNEWS_USE("1");
				}
				if ("FINAN_USE".equalsIgnoreCase(item)) {
					paramVO.setFINAN_USE("1");
				}
			}
		}
		paramVO.setREPORT_USE("1");
		paramVO.setUP_USER(paramVO.getLoginCode());
		memberMapper.memberUpdate(paramVO);

		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}
	@Override
	public JSONObject memberDelete(MemberVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

		// S:유효성체크
		// 회원코드
		if(UtilCommon.isEmpty(paramVO.getMEMBER_CODE())) {
			rMsg = messageSource.getMessage("validation.empty.required", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// 삭제가능데이타 존재여부확인
		MemberVO result = new MemberVO();
		result = memberMapper.memberDetail(paramVO);
		if(UtilCommon.isEmpty(result)) {
			rMsg = messageSource.getMessage("validation.delete.empty.object", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크
		
		paramVO.setIN_USER(paramVO.getLoginCode());
		paramVO.setUP_USER(paramVO.getLoginCode());
		memberMapper.memberDelete(paramVO);

		UtilJsonResult.setReturnCodeSuc(json);
		
		return json;
	}
	@Override
	public JSONObject memberIdDupCheck(MemberVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		MemberVO resultVO = new MemberVO();
		resultVO = memberMapper.memberDetailById(paramVO);
		
		if(UtilCommon.isEmpty(resultVO)) {
			rMsg = messageSource.getMessage("validation.join.idSuccess", null, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeSuc(json, rMsg);
		} else {
			rMsg = messageSource.getMessage("join.fail.duplicateId", null, localeResolver.resolveLocale(request));
			UtilJsonResult.setReturnCodeFail(json, rMsg);
		}
		return json;
	}
	

}
