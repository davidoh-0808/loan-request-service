package com.neo.util;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.penta.scpdb.ScpDbAgent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 암/복호화 관련 기능을 하는 유틸리티 클래스이다.
 * 
 * 2022-08-30 DAMO 암복호화 모듈 추가
 */
// @Component
// @PropertySource(value = "classpath:applicaiton-dev.yml")
public class UtilCrypt {
	/* @Value 로 파일경로 필드를 가져오려면,
			-> UtilCrtpt 를 사용하는 서비스 단에서 @Value("${damo.inifilepath}") private String iniEncDecFilePath 를 선언해야함.

				UtilCrypt 내부에서 @Value 로 파일경로를 주입시키지 못하는 이유 :
	   				1) UtilCrypt 가 컴포넌트가 되도록 설정해야함 (하지만 보통 Util 파일은 컴포넌트화 시키지 않는다고 함)
	   				2) static 메소드에서 사용하기 위해선 파일경로 필드 또한 static 이 되어야 함.  하지만 static 필드는 @Value 로 주입이 불가능함.

				//@Value("${damo.inifilepath}")
				//private static String iniEncDecFilePath;
	 */

	/**
	 * 생성자, 외부에서 객체를 인스턴스화 할 수 없도록 설정
	 */
	private UtilCrypt() {

	}

	/**
	 * 메시지를 SHA-256 알고리즘으로 해쉬한다.
	 *
	 * @param message 원본메시지
	 * @return 해쉬된 문자열
	 */
	public static String hashSHA256(String message, String privateKey) {
		return hash(message, privateKey, "SHA-256");
	}

	/**
	 * 메시지를 BASE64 알고리즘으로 인코딩한다.
	 *
	 * @param message 원본메시지
	 * @return 인코딩된 문자열
	 */
	public static String encodeBase64(String message) {
		return new String(Base64.encodeBase64(message.getBytes()));
	}

	/**
	 * 메시지를 BASE64 알고리즘으로 디코딩한다.
	 *
	 * @param message 원본 메시지
	 * @return 디코딩된 문자열
	 */
	public static String decodeBase64(String message) {
		return new String(Base64.decodeBase64(message.getBytes()));
	}

	/**
	 * 메시지를 개인키를 이용하여 AES 알고리즘으로 암호화한다.
	 *
	 * @param message 원본메시지
	 * @param privateKey 개인키 ※반드시 16자리
	 * @return 암호화된 문자열
	 */
	public static String encryptAES(String message, String privateKey) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(privateKey.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
			return String.valueOf(Hex.encodeHex(cipher.doFinal(message.getBytes())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 메시지를 개인키를 이용하여 AES 알고리즘으로 복호화한다.
	 *
	 * @param message 원본메시지
	 * @param privateKey 개인키
	 * @return 복호화된 문자열
	 */
	public static String decryptAES(String message, String privateKey) {
		try {
			SecretKeySpec skeySpec = new SecretKeySpec(privateKey.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec);
			return new String(cipher.doFinal(Hex.decodeHex(message.toCharArray())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//////////////////////////////////////////////////////////////////////////////////////////Private 메소드

	/**
	 * 메시지를 솔트를 사용하여 주어진 알고리즘으로 해쉬한다.
	 *
	 * @param message 원본메시지
	 * @param salt 솔트값
	 * @param algorithm 해쉬 알고리즘
	 * @return 해쉬된 문자열
	 */
	private static String hash(String message, String salt, String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.reset();
			md.update(salt.getBytes());
			return new String(Hex.encodeHex(md.digest(message.getBytes())));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/**
	 * Damo 외부 암호화 모듈
	 */
	//@Value("${damo.inifilepath}")
	//private static String iniEncFilePath;
	public static String ScpEncStr(String message, String iniEncDecFilePath) {
		
	    //디아모 모듈로 변경
	    ScpDbAgent agt = new ScpDbAgent();
	    String encstr = iniEncDecFilePath;
		try {
			return  agt.ScpEncStr( iniEncDecFilePath, "KEY1", message );
            
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//@Value("${damo.inifilepath}")
	//static String iniDecFilePath;
	public static String ScpDecStr(String message, String iniEncDecFilePath) {
		
	    //디아모 모듈로 변경
	    ScpDbAgent agt = new ScpDbAgent();
	    String decstr = iniEncDecFilePath;
	    
		try {
			return  agt.ScpDecStr( iniEncDecFilePath, "KEY1", message);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
    
}
