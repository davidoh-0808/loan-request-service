package com.neo.common.service;


import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;

import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.SendMailVO;
import com.neo.util.UtilCommon;
import com.neo.util.UtilCrypt;
import com.neo.util.UtilJsonResult;
import com.neo.util.UtilOtp;

@Service("emailService")
public class EmailServiceImpl implements EmailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private JavaMailSender sender;
	
	@Value("${company.name}") String COMPANY_NAME;
	
	//DAmo 암호,복호화 모듈의 설정파일 주입
    @Value("${damo.inifilepath}")
    private String iniEncDecFilePath;
    
    @Resource
	private JavaMailSender javaMailSender;
    
	@Autowired MessageSource messageSource;
	@Autowired LocaleResolver localeResolver;

	public Map<String, Object> sendEmail(String subject, String body) {
		Map<String, Object> result = new HashMap<String, Object>();
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
//			helper.setTo(toAddress);
			helper.setSubject(subject);
			helper.setText(body);
			result.put("resultCode", 200);
		} catch (MessagingException e) {
			e.printStackTrace();
			result.put("resultCode", 500);
		}

		sender.send(message);
		return result;
	}
	
	@Override
	public JSONObject sendEmail(SendMailVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setTo("hs6666@hyundaismile.or.kr");  // 받는 담당자 정보
		//helper.setTo("ryan@seowoninfo.com");  // 받는 담당자 정보
		helper.setSubject("메일드립니다."); // 정해진 메일제목
		helper.setText("발송자정보"+"\n이름 : "+paramVO.getConsult_name()+"\n연락처 : "+paramVO.getConsult_phone()+"\n주소 : "+paramVO.getConsult_address()+"\n님으로부터 상담이 신청되었습니다.");
		// S:유효성체크
		// 이름 체크
		if(UtilCommon.isEmpty(paramVO.getConsult_name())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이름"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크

		UtilJsonResult.setReturnCodeSuc(json);
		
		sender.send(message);
		return json;
	}
	
	@Override
	public JSONObject sendEmailReport(SendMailVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setTo("hs6666@hyundaismile.or.kr");  // 받는 담당자 정보
		//helper.setTo("ryan@seowoninfo.com");  // 받는 담당자 정보
		helper.setSubject("상담문의 입니다."); // 메일제목
		
		helper.setText(
				"고객정보"+
				"\n이름 : "+paramVO.getReport_name()+
				"\n연락처 : "+paramVO.getReport_phone()+
				"\n이메일 : "+paramVO.getReport_email()+
				"\n\n문의유형 : "+paramVO.getReport_type()+
				"\n\n상담내용"+
				"\n제목 : "+paramVO.getReport_subject()+
				"\n내용 : "+paramVO.getReport_content()+
				"\n고객의소리-상담이 신청되었습니다.");
		// S:유효성체크
		// 이름 체크
		if(UtilCommon.isEmpty(paramVO.getReport_name())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이름"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크

		UtilJsonResult.setReturnCodeSuc(json);
		
		sender.send(message);
		return json;
	}
	
	@Override
	public JSONObject sendEmailOtp(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
		JSONObject json = new JSONObject();
		UtilJsonResult.setReturnCodeFail(json);
		String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
		
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mailHelper = new MimeMessageHelper(mailMessage, true);
		
		
		String companyName = COMPANY_NAME;
		String memberId = paramVO.getMEMBER_ID();
		String memberOtp = paramVO.getMEMBER_OTP();	//저장된 otp 키
		
		// 인증 QR코드 생성 :: 발급자(회사명), 계정명(유저ID), 개인키  
		String qrOtpUrl = UtilOtp.getQRBarcodeURL(companyName, memberId, memberOtp);
		
		StringBuffer sb = new StringBuffer();  // StringBuffer 객체 sb 생성
		sb.append("<br>안녕하세요. "+ paramVO.getMEMBER_NAME() + " 님");
		sb.append("<br><br><br>");
		sb.append("QR 코드를 GOOGLE OTP앱으로 바코드 등록해주세요.");
		sb.append("<br><br>");
		sb.append("<div class=\"_blockInner\">");
		sb.append("<iframe id=\"cid\" name=\"blockUI\"><img src='"+qrOtpUrl+"'></iframe>");
		sb.append("</div>");
		sb.append("<br><br>");
		sb.append("<br>1. 휴대폰으로 QR코드 인식 후 Google Authenticator 설치(iOS일 경우 Google Authenticator 설치)");
		sb.append("<br>2. 어플 실행 후 QR 코드 스캔 메뉴 선택");
		sb.append("<br>3. 하단에 OTP 인증번호 입력");
		sb.append("<br><br>");
		sb.append("- QR코드가 보이지 않으신 분은 아래 링크를 사용하시는 웹 브라우저에 복사하여 붙여넣기 하세요.");
		sb.append("<br><br>");
		sb.append("인증키 URL : " + qrOtpUrl);
		sb.append("<br><br>");
		sb.append("- QR코드 사용이 어려우신 분은 App 스토에서 직접 Google Authenticator 설치 후 하단 인증키로 등록하여 사용하세요.");
		sb.append("<br><br>");
		sb.append("인증키 : " + memberOtp);
		sb.append("<br><br>");

		String strHtml = sb.toString();

		// 받는 담당자 정보 (jsp 화면에 출력된 값을 paramVO에 담아오기 때문에 복호화 할 필요가 없다
		mailHelper.setTo( paramVO.getMEMBER_EMAIL() );

		mailHelper.setSubject("현대차미소금융 OTP 인증 QR Code"); // 메일제목
		
		mailHelper.setText(strHtml, true); //ture : html 형식 사용
	            
		// S:유효성체크
		// 이름 체크
		if(UtilCommon.isEmpty(paramVO.getMEMBER_NAME())) {
			rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이름"}, localeResolver.resolveLocale(request)); 
			UtilJsonResult.setReturnCodeFail(json, rMsg);
			return json;
		}
		// E:유효성체크
		
		try {
			
			javaMailSender.send(mailMessage);
			
			UtilJsonResult.setReturnCodeSuc(json);
			
		}catch(MailSendException e) {
			e.printStackTrace();
		
		}

		
		return json;
	}
	
}
