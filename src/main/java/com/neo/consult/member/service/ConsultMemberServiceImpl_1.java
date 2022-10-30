package com.neo.consult.member.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import com.neo.common.vo.ConsultMemberVO;
import com.neo.common.vo.MasterKeyVO;
import com.neo.mappers.CommonMapper;
import com.neo.mappers.ConsultMemberMapper;
import com.neo.security.CustomConsultDetails;
import com.neo.util.UtilCommon;
import com.neo.util.UtilCrypt;
import com.neo.util.UtilJsonResult;
import com.neo.util.UtilOtp;

import com.neo.common.service.EmailService;
import com.neo.common.vo.SendMailVO;
import com.neo.login.service.OtpLoginService;

@Transactional
@Service("consultMemberService")
public class ConsultMemberServiceImpl implements ConsultMemberService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;
    @Autowired
    LocaleResolver localeResolver;
    @Value("${crypt.privatekey.sha256}")
    private String privateKey;
    @Resource(name = "consultMemberMapper")
    private ConsultMemberMapper consultMemberMapper;
    @Resource(name = "commonMapper")
    private CommonMapper commonMapper;

	@Resource(name="emailService")
	private EmailService emailService;
	
	@Resource(name="OtpLoginService")
	private OtpLoginService otpLoginService;

	//DAmo 암호,복호화 모듈의 설정파일 주입
    @Value("${damo.inifilepath}")
    private String iniEncDecFilePath;


    @Override
    public int consultantListCount(ConsultMemberVO paramVO) throws Exception {
        return consultMemberMapper.consultantListCount(paramVO);
    }
    @Override
    public int systemConsultMemberListCount(ConsultMemberVO paramVO) throws Exception {
        return consultMemberMapper.systemConsultMemberListCount(paramVO);
    }

    @Override
    public List<ConsultMemberVO> consultantList(ConsultMemberVO paramVO) throws Exception {
        List<ConsultMemberVO> resultList = consultMemberMapper.consultantList(paramVO);

        //디아모 복호화 (이메일)
        for (int i=0; i<resultList.size(); i++) {
            ConsultMemberVO result = resultList.get(i);
            result.setMEMBER_EMAIL(
                UtilCrypt.ScpDecStr( result.getMEMBER_EMAIL(), iniEncDecFilePath) );
        }

        return resultList;
    }

    @Override
    public List<ConsultMemberVO> systemConsultMemberList(ConsultMemberVO paramVO) throws Exception {
        List<ConsultMemberVO> resultList = consultMemberMapper.systemConsultMemberList(paramVO);

        //디아모 복호화 (이메일)
        for (int i=0; i<resultList.size(); i++) {
            ConsultMemberVO result = resultList.get(i);
            result.setMEMBER_EMAIL(
                    UtilCrypt.ScpDecStr( result.getMEMBER_EMAIL(), iniEncDecFilePath) );
        }

        return resultList;
    }

    @Override
    public ConsultMemberVO consultMemberDetail(ConsultMemberVO paramVO) throws Exception {
        ConsultMemberVO result = consultMemberMapper.consultMemberDetail(paramVO);

        //디아모 복호화 (비밀번호, 전화번호, 이메일)
        result.setMEMBER_PW( UtilCrypt.ScpDecStr(result.getMEMBER_PW(), iniEncDecFilePath) );
        result.setMEMBER_PHONE( UtilCrypt.ScpDecStr(result.getMEMBER_PHONE(), iniEncDecFilePath) );
        result.setMEMBER_EMAIL( UtilCrypt.ScpDecStr(result.getMEMBER_EMAIL(), iniEncDecFilePath) );

        return result;
    }

    @Override
    public ConsultMemberVO systemConsultMemberDetail(ConsultMemberVO paramVO) throws Exception {
        ConsultMemberVO result = consultMemberMapper.systemConsultMemberDetail(paramVO);

        //디아모 복호화 (전화번호, 이메일)
        result.setMEMBER_PHONE( UtilCrypt.ScpDecStr(result.getMEMBER_PHONE(), iniEncDecFilePath) );
        result.setMEMBER_EMAIL( UtilCrypt.ScpDecStr(result.getMEMBER_EMAIL(), iniEncDecFilePath) );
        result.setMEMBER_PW( UtilCrypt.ScpDecStr(result.getMEMBER_PW(), iniEncDecFilePath) );

        return result;
    }

    @Override
    public JSONObject consultMemberInsert(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject resultJson = new JSONObject();
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
        CustomConsultDetails userDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        //등록되는 지점/심사팀 유저의 필수사항 항목들의 유효성 검사
        //아이디
        if(UtilCommon.isEmpty( paramVO.getMEMBER_ID() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"아이디를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //비밀번호
        if(UtilCommon.isEmpty( paramVO.getMEMBER_PW() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"패스워드를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //권한
        if(UtilCommon.isEmpty( paramVO.getMEMBER_AUTHORITY() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"권한혹은 지점을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //유저 근무상태
        if(UtilCommon.isEmpty( paramVO.getMEMBER_WORK_STATUS() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"유저 근무상태를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //연락처
        if(UtilCommon.isEmpty( paramVO.getMEMBER_PHONE() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"연락처를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //이메일
        if(UtilCommon.isEmpty( paramVO.getMEMBER_EMAIL() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이메일을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }

        //지점(상담원) , 심사팀 유저의 PK 멤버코드를 설정해준다
        String masterKey = "";
        MasterKeyVO masterKeyVO = new MasterKeyVO();
        masterKeyVO.setKEY_GBN("MEMBER_CODE");
        masterKey = commonMapper.getFnGetMasterKey(masterKeyVO);
        paramVO.setMEMBER_CODE(masterKey);

        //개인정보를 디아모로 암호화하여 저장
        //패스워드
        String sendPw = paramVO.getMEMBER_PW();	//이메일 전송시 암호화가 되지 않은 비밀번호를 변수에 담아준다.
        String encPw = UtilCrypt.ScpEncStr( paramVO.getMEMBER_PW(), iniEncDecFilePath );
        paramVO.setMEMBER_PW(encPw);
        //전화번호
        String encPhone = UtilCrypt.ScpEncStr( paramVO.getMEMBER_PHONE(), iniEncDecFilePath );
        paramVO.setMEMBER_PHONE(encPhone);
        
        //이메일
        String sendEmail = paramVO.getMEMBER_EMAIL(); //이메일 전송시 암호화가 되지 않은 이메일 주소를 위해 변수에 담아준다.
        String encEmail = UtilCrypt.ScpEncStr( paramVO.getMEMBER_EMAIL(), iniEncDecFilePath);
        paramVO.setMEMBER_EMAIL(encEmail);

        //새 유저를 등록, 업데이트 하는 유저의 마스터코드 입력
        paramVO.setIN_USER(userDetails.getMember_code());
        paramVO.setUP_USER(userDetails.getMember_code());

        //등록되는 유저의 MEMBER_TYPE(회원구분) 은 MC0000100003(상담팀) 으로 설정
        paramVO.setMEMBER_TYPE("MC0000100003");

        //사용여부 USE_YN 을 Y로 설정
        paramVO.setUSE_YN("Y");

        //MEMBER_STATUS (대기 MC0000700001, 승인 MC0000700002, 차단 MC0000700003) 중 승인 설정
        paramVO.setMEMBER_STATUS("MC0000700002");
        
        //심사팀, 심사팀관리자, 시스템관리자는 구분을 위해 모두 심사팀 소속으로 등록한다(임시)
        if("MC0002300002".equals(paramVO.getMEMBER_AUTHORITY())){
        	paramVO.setBRANCH_CODE("MC0000900099"); //심사팀
        }

        //otp 인증키
        String memberOtp = UtilOtp.getSecretKey();
        paramVO.setMEMBER_OTP(memberOtp);
        
		try {
	        //저장
	        consultMemberMapper.consultMemberInsert(paramVO);

	        /**
	         *  email 로 qr코드 인증키 보내기 
	         */
	        
	        //암호화 되지 않은 pw를 다시 paramVO에 넣어준다. 메일에 포함용도
	        paramVO.setMEMBER_PW(sendPw);
	        //암호화 되지 않은 email을 다시 paramVO에 넣어준다.  메일에 포함용도
	        paramVO.setMEMBER_EMAIL(sendEmail);
	        
			resultJson = emailService.sendEmailOtp(paramVO, request);
			
			
	        //등록 완료 후 성공 코드 및 메시지 설정
	        UtilJsonResult.setReturnCodeSuc(resultJson);
	        
		} catch (MessagingException e) {
			e.printStackTrace();
		}

        return resultJson;
    }

    @Override
    public JSONObject systemConsultMemberInsert(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject resultJson = new JSONObject();
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
        CustomConsultDetails userDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        //등록되는 지점/심사팀 유저의 필수사항 항목들의 유효성 검사
        //아이디
        if(UtilCommon.isEmpty( paramVO.getMEMBER_ID() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"아이디를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //비밀번호
        if(UtilCommon.isEmpty( paramVO.getMEMBER_PW() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"패스워드를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //권한
        if(UtilCommon.isEmpty( paramVO.getMEMBER_AUTHORITY() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"권한혹은 지점을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //유저 계정/등록현황
        if(UtilCommon.isEmpty( paramVO.getMEMBER_STATUS() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"유저 계정/등록현황을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //연락처
        if(UtilCommon.isEmpty( paramVO.getMEMBER_PHONE() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"연락처를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //이메일
        if(UtilCommon.isEmpty( paramVO.getMEMBER_EMAIL() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이메일을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }

        //지점(상담원) , 심사팀 유저의 PK 멤버코드를 설정해준다
        String masterKey = "";
        MasterKeyVO masterKeyVO = new MasterKeyVO();
        
        masterKeyVO.setKEY_GBN("MEMBER_CODE");
        masterKey = commonMapper.getFnGetMasterKey(masterKeyVO);
        paramVO.setMEMBER_CODE(masterKey);

        //개인정보를 디아모로 암호화하여 저장
        //패스워드
        String sendPw = paramVO.getMEMBER_PW();	//이메일 전송시 암호화가 되지 않은 비밀번호를 변수에 담아준다.
        String encPw = UtilCrypt.ScpEncStr( paramVO.getMEMBER_PW(), iniEncDecFilePath );
        paramVO.setMEMBER_PW(encPw);
        //전화번호
        String encPhone = UtilCrypt.ScpEncStr( paramVO.getMEMBER_PHONE(), iniEncDecFilePath );
        paramVO.setMEMBER_PHONE(encPhone);
        //이메일
        String sendEmail = paramVO.getMEMBER_EMAIL(); //이메일 전송시 암호화가 되지 않은 이메일 주소를 위해 변수에 담아준다.
        String encEmail = UtilCrypt.ScpEncStr( paramVO.getMEMBER_EMAIL(), iniEncDecFilePath);
        paramVO.setMEMBER_EMAIL(encEmail);

        //새 유저를 등록, 업데이트 하는 유저의 마스터코드 입력
        paramVO.setIN_USER(userDetails.getMember_code());
        paramVO.setUP_USER(userDetails.getMember_code());

        //등록되는 유저의 MEMBER_TYPE(회원구분) 은 MC0000100001(관리자) 로 설정
        paramVO.setMEMBER_TYPE("MC0000100001");

        //심사팀, 심사팀관리자, 시스템관리자 는 구분을 위해 모두 소속을 심사팀으로 갖는다.(임시)
       	paramVO.setBRANCH_CODE("MC0000900099"); //심사팀
        
        //사용여부 USE_YN 을 Y로 설정
        paramVO.setUSE_YN("Y");
        
        //otp 인증키
        String memberOtp = UtilOtp.getSecretKey();
        paramVO.setMEMBER_OTP(memberOtp);
        
		try {
	        //저장
	        consultMemberMapper.systemConsultMemberInsert(paramVO);
	        
	        /**
	         *  email 로 qr코드 인증키 보내기 
	         */
	        //암호화 되지 않은 pw를 다시 paramVO에 넣어준다. 메일에 포함용도
	        paramVO.setMEMBER_PW(sendPw);
	        
	        //암호화 되지 않은 email을 다시 paramVO에 넣어준다.
	        paramVO.setMEMBER_EMAIL(sendEmail);
	        
			resultJson = emailService.sendEmailOtp(paramVO, request);
	        
	        //등록 완료 후 성공 코드 및 메시지 설정
	        UtilJsonResult.setReturnCodeSuc(resultJson);
	        
		} catch (MessagingException e) {
			e.printStackTrace();
		}

        return resultJson;
    }
    
	/**
	 * consult OTP 재발행
	 * @param locale
	 * @param paramVO
	 * @return
	 */   
    @Override
    public JSONObject otpReissue(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
    	
    	CustomConsultDetails adminDetail = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		JSONObject resultJson = new JSONObject();
		
        try {
            //otp 인증키 재발행
            String memberOtp = UtilOtp.getSecretKey();

        	paramVO.setMEMBER_OTP(memberOtp);
        	paramVO.setUP_USER( adminDetail.getMember_code() );
        	
        	/**
    		 *  인증키 db저장 
    		 */
        	resultJson = otpLoginService.otpKeyUpdate(paramVO, request);
        	
        	/**
    		 *  email 로 qr코드 인증키 보내기 
    		 */
    		resultJson = emailService.sendEmailOtp(paramVO, request);
            	
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        String rMsg = messageSource.getMessage("message.otp.success", null, localeResolver.resolveLocale(request));
        UtilJsonResult.setReturnCodeSuc(resultJson, rMsg);
        
    	return resultJson;
    }
    

    @Override
    public JSONObject consultMemberUpdate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject resultJson = new JSONObject();
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
        CustomConsultDetails userDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        //등록되는 지점/심사팀 유저의 필수사항 항목들의 유효성 검사
        /*아이디는 수정할 수 있는 항목이 아니기 때문에 스킵
        if(UtilCommon.isEmpty( paramVO.getMEMBER_ID() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"아이디를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }*/
        //비밀번호
        if(UtilCommon.isEmpty( paramVO.getMEMBER_PW() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"패스워드를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //권한
        if(UtilCommon.isEmpty( paramVO.getMEMBER_AUTHORITY() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"권한혹은 지점을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //유저 계정/등록현황
        if(UtilCommon.isEmpty( paramVO.getMEMBER_WORK_STATUS() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"유저 계정/등록현황을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //연락처
        if(UtilCommon.isEmpty( paramVO.getMEMBER_PHONE() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"연락처를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //이메일
        if(UtilCommon.isEmpty( paramVO.getMEMBER_EMAIL() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이메일을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }

        //개인정보를 디아모로 암호화하여 저장
        //패스워드
        String encPw = UtilCrypt.ScpEncStr( paramVO.getMEMBER_PW(), iniEncDecFilePath );
        paramVO.setMEMBER_PW(encPw);
        //전화번호
        String encPhone = UtilCrypt.ScpEncStr( paramVO.getMEMBER_PHONE(), iniEncDecFilePath );
        paramVO.setMEMBER_PHONE(encPhone);
        //이메일
        String encEmail = UtilCrypt.ScpEncStr( paramVO.getMEMBER_EMAIL(), iniEncDecFilePath );
        paramVO.setMEMBER_EMAIL(encEmail);

        //유저를 등록, 업데이트 하는 유저의 마스터코드 입력
        paramVO.setIN_USER(userDetails.getMember_code());
        paramVO.setUP_USER(userDetails.getMember_code());

        //등록되는 유저의 MEMBER_TYPE(회원구분) 은 MC0000100003(상담팀) 으로 설정
        paramVO.setMEMBER_TYPE("MC0000100003");

        //심사팀, 심사팀관리자, 시스템관리자는 구분을 위해 모두 심사팀 소속으로 등록한다(임시)
        if("MC0002300002".equals(paramVO.getMEMBER_AUTHORITY())){
        	paramVO.setBRANCH_CODE("MC0000900099"); //심사팀
        }
        
        //수정
        consultMemberMapper.consultMemberUpdate(paramVO);

        //등록 완료 후 성공 코드 및 메시지 설정
        UtilJsonResult.setReturnCodeSuc(resultJson);

        return resultJson;
    }


    @Override
    public JSONObject systemConsultMemberUpdate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject resultJson = new JSONObject();
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
        CustomConsultDetails userDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        
        //비밀번호
        if(UtilCommon.isEmpty( paramVO.getMEMBER_PW() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"패스워드를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //권한
        if(UtilCommon.isEmpty( paramVO.getMEMBER_AUTHORITY() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"권한혹은 지점을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //유저 
        if(UtilCommon.isEmpty( paramVO.getMEMBER_STATUS() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"유저 근무상태를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //연락처
        if(UtilCommon.isEmpty( paramVO.getMEMBER_PHONE() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"연락처를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }
        //이메일
        if(UtilCommon.isEmpty( paramVO.getMEMBER_EMAIL() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"이메일을 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }

        //개인정보를 디아모로 암호화하여 저장
        //패스워드
        String encPw = UtilCrypt.ScpEncStr( paramVO.getMEMBER_PW(), iniEncDecFilePath );
        paramVO.setMEMBER_PW(encPw);
        //전화번호
        String encPhone = UtilCrypt.ScpEncStr( paramVO.getMEMBER_PHONE(), iniEncDecFilePath );
        paramVO.setMEMBER_PHONE(encPhone);
        //이메일
        String encEmail = UtilCrypt.ScpEncStr( paramVO.getMEMBER_EMAIL(), iniEncDecFilePath );
        paramVO.setMEMBER_EMAIL(encEmail);

        //유저를 등록, 업데이트 하는 유저의 마스터코드 입력
        paramVO.setIN_USER(userDetails.getMember_code());
        paramVO.setUP_USER(userDetails.getMember_code());

        //등록되는 유저의 MEMBER_TYPE(회원구분) 은 MC0000100003(상담팀) 으로 설정
        paramVO.setMEMBER_TYPE("MC0000100003");
        
        //심사팀, 심사팀관리자, 시스템관리자 는 구분을 위해 모두 소속을 심사팀으로 갖는다.(임시)
       	paramVO.setBRANCH_CODE("MC0000900099"); //심사팀
       	
        //수정
        consultMemberMapper.consultMemberUpdate(paramVO);

        //등록 완료 후 성공 코드 및 메시지 설정
        UtilJsonResult.setReturnCodeSuc(resultJson);

        return resultJson;
    }


    @Override
    public JSONObject userProfileUpdate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject resultJson = new JSONObject();
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
        CustomConsultDetails userDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        //수정정보가 넘어왔을 경우 디아모로 암호화하여 저장
        //패스워드
        String inputPw = paramVO.getMEMBER_PW();
        if( inputPw != null && inputPw != "" ) {
            String encPw = UtilCrypt.ScpEncStr( inputPw, iniEncDecFilePath );
            paramVO.setMEMBER_PW(encPw);
        }
        //전화번호
        String inputPhone = paramVO.getMEMBER_PHONE();
        if( inputPhone != null && inputPhone != "" ) {
            //전화번호 - 대시 없애주기
            paramVO.setMEMBER_PHONE( inputPhone.replace("-", "") );

            String encPhone = UtilCrypt.ScpEncStr( inputPhone, iniEncDecFilePath );
            paramVO.setMEMBER_PHONE(encPhone);
        }
        //이메일
        String inputEmail = paramVO.getMEMBER_EMAIL();
        if( inputEmail != null && inputEmail != "" ) {
            String encEmail = UtilCrypt.ScpEncStr( inputEmail, iniEncDecFilePath );
            paramVO.setMEMBER_EMAIL(encEmail);
        }

        //유저를 등록, 업데이트 하는 유저의 마스터코드 입력
        paramVO.setIN_USER(userDetails.getMember_code());
        paramVO.setUP_USER(userDetails.getMember_code());

        //수정 (기존 쿼리 재사용)
        //************************
        consultMemberMapper.consultMemberUpdate(paramVO);

        //등록 완료 후 성공 코드 및 메시지 설정
        UtilJsonResult.setReturnCodeSuc(resultJson);


        return resultJson;
    }

    @Override
    public JSONObject consultMemberPwReset(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject resultJson = new JSONObject();
        CustomConsultDetails userDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

        /*
            수정버튼이 아닌 비밀번호 초기화 버튼을 클릭 시,
             비밀번호 외의 다른 정보들이 같이 넘어올 경우 이는 의도치 않은 것이라 간주
             따라서 지워주고 update를 시킨다.
         */
        paramVO.setMemberName("");
        paramVO.setMEMBER_AUTHORITY("");
        paramVO.setBRANCH_CODE("");
        paramVO.setMEMBER_WORK_STATUS("");
        paramVO.setMEMBER_STATUS("");
        paramVO.setMEMBER_PHONE("");
        paramVO.setMEMBER_EMAIL("");
        paramVO.setEXPLANATION("");

        // 초기화 패스워드 입력
        paramVO.setMEMBER_PW("Miso!@34");

        //비밀번호 유효성 검사
        if(UtilCommon.isEmpty( paramVO.getMEMBER_PW() )) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[] {"패스워드를 "}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        } else {
            //비밀번호 암호화
            String encPw = UtilCrypt.ScpEncStr( paramVO.getMEMBER_PW(), iniEncDecFilePath );
            paramVO.setMEMBER_PW(encPw);
        }

        //유저를 등록, 업데이트 하는 유저의 마스터코드 입력
        paramVO.setIN_USER(userDetails.getMember_code());
        paramVO.setUP_USER(userDetails.getMember_code());

        consultMemberMapper.consultMemberUpdate(paramVO);

        //등록 완료 후 성공 코드 및 메시지 설정
        UtilJsonResult.setReturnCodeSuc(resultJson);

        return resultJson;
    }

    @Override
    public JSONObject consultMemberDelete(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject resultJson = new JSONObject();
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));
        ConsultMemberVO result = new ConsultMemberVO();
        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();

        //삭제할 상담원유저의 MEMBER_CODE 가 있는지 유효성 검사 후, 실패 시 실패메시지 세팅
        if ( UtilCommon.isEmpty(paramVO.getMEMBER_CODE()) ) {
            rMsg = messageSource.getMessage("validation.empty.required", null, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }

        //삭제할 지점,심사팀 유저 혹은 심사팀관리자,시스템관리자 의 존재유무를 체크하고 아닐 경우, 실패 메시지 세팅
        result = consultMemberMapper.consultMemberDetail(paramVO);
        if (UtilCommon.isEmpty(result)) {
            rMsg = messageSource.getMessage("validation.delete.empty.object", null, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
            return resultJson;
        }

        //삭제 여부 셋팅 , 삭제를 진행하는 현재유저의 MEMBER_CODE 를 paramVO 에 입력
        paramVO.setDEL_YN("Y");
        paramVO.setUP_USER( consultDetails.getMember_code() );

        //삭제 쿼리 진행 및 성공코드 와 성공메시지 설정
        consultMemberMapper.consultMemberDelete(paramVO);
        UtilJsonResult.setReturnCodeSuc(resultJson);

        return resultJson;
    }

    @Override
    public JSONObject consultMemberIdDupCheck(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject resultJson = new JSONObject();
        ConsultMemberVO resultVO = new ConsultMemberVO();
        /*
            resource 안에 있는 message.yml 파일의 메시지를 가져오기 위해서는 MessageSource 가 필요하며, 이 messageSource 는 이 메시지 파일을 찾아내기 위해 request 에 담긴 LocaleResolver (현재코드실행위치 파인더) 가 필요하다
         */
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

        resultVO = consultMemberMapper.consultMemberDetailById(paramVO);

        if (UtilCommon.isEmpty(resultVO) ) {
            rMsg = messageSource.getMessage("validation.join.idSuccess", null, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeSuc(resultJson, rMsg);
        } else {
            rMsg = messageSource.getMessage("join.fail.duplicateId", null, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(resultJson, rMsg);
        }

        return resultJson;
    }

    @Override
    public List<ConsultMemberVO> consultMemberDetailByAuthority(ConsultMemberVO paramVO) throws Exception {
        return consultMemberMapper.consultMemberDetailByAuthority(paramVO);
    }

    @Override
    public JSONObject consultMemberActivate(ConsultMemberVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject resultJson = new JSONObject();

        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        paramVO.setMEMBER_WORK_STATUS("MC0001300001");      // 회원근태 MC0000000013 -> 근무중 MC0001300001

        if( consultDetails.getMember_code() == null ) {
            // 업로드 해주는 특정멤버유저가 없다면 시스템에서 배치 프로세스로 변경된 것이다
            paramVO.setUP_USER("Spring Batch Process");
        } else {
            paramVO.setUP_USER( consultDetails.getMember_code() );
        }

        consultMemberMapper.consultMemberUpdate(paramVO);
        UtilJsonResult.setReturnCodeSuc(resultJson);
        
        return resultJson;
    }

    @Override
    public JSONObject consultMemberDeactivate(ConsultMemberVO paramVO) throws Exception {
        JSONObject resultJson = new JSONObject();

        CustomConsultDetails consultDetails = (CustomConsultDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        paramVO.setMEMBER_WORK_STATUS("MC0001300005");      // 회원근태 MC0000000013 -> 장기미사용 MC0001300005

        if( consultDetails.getMember_code() == null ) {
            // 업로드 해주는 특정멤버유저가 없다면 시스템에서 배치 프로세스로 변경된 것이다
            paramVO.setUP_USER("Spring Batch Process");
        } else {
            paramVO.setUP_USER( consultDetails.getMember_code() );
        }

        consultMemberMapper.consultMemberUpdate(paramVO);
        UtilJsonResult.setReturnCodeSuc(resultJson);

        return resultJson;
    }



}
