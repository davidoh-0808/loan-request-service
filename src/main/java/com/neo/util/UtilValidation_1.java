package com.neo.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 유효성 체크 라이브러리
 */
public class UtilValidation {

	/**
	 * 주민등록번호/외국인등록번호 유효성 체크
	 *
	 * @param residentRegistrationNo 주민등록번호/외국인등록번호
	 * @return 유효한 주민등록번호/외국인등록번호 형식 여부
	 */
	public static boolean isResidentRegistrationNo(String residentRegistrationNo) {
		String juminNo = residentRegistrationNo.replaceAll("[^0-9]", "");
		if (juminNo.length() != 13) {
			return false;
		}
		int yy = to_int(juminNo.substring(0, 2));
		int mm = to_int(juminNo.substring(2, 4));
		int dd = to_int(juminNo.substring(4, 6));
		if (yy < 1 || yy > 99 || mm > 12 || mm < 1 || dd < 1 || dd > 31) {
			return false;
		}
		int sum = 0;
		int juminNo_6 = to_int(juminNo.charAt(6));
		if (juminNo_6 == 1 || juminNo_6 == 2 || juminNo_6 == 3 || juminNo_6 == 4) {
			//내국인
			for (int i = 0; i < 12; i++) {
				sum += to_int(juminNo.charAt(i)) * ((i % 8) + 2);
			}
			if (to_int(juminNo.charAt(12)) != (11 - (sum % 11)) % 10) {
				return false;
			}
			return true;
		} else if (juminNo_6 == 5 || juminNo_6 == 6 || juminNo_6 == 7 || juminNo_6 == 8) {
			//외국인
			if (to_int(juminNo.substring(7, 9)) % 2 != 0) {
				return false;
			}
			for (int i = 0; i < 12; i++) {
				sum += to_int(juminNo.charAt(i)) * ((i % 8) + 2);
			}
			if (to_int(juminNo.charAt(12)) != ((11 - (sum % 11)) % 10 + 2) % 10) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 주민등록번호/외국인등록번호 유효성 체크
	 *
	 * @param juminNo 주민등록번호/외국인등록번호
	 * @return 유효한 주민등록번호/외국인등록번호 형식 여부
	 */
	public static boolean isJuminNo(String juminNo) {
		return isResidentRegistrationNo(juminNo);
	}

	/**
	 * 법인번호 유효성 체크
	 *
	 * @param corporationRegistrationNo 법인번호
	 * @return 유효한 법인번호 형식 여부
	 */
	public static boolean isCorporationRegistrationNo(String corporationRegistrationNo) {
		String corpRegNo = corporationRegistrationNo.replaceAll("[^0-9]", "");
		if (corpRegNo.length() != 13) {
			return false;
		}
		int sum = 0;
		for (int i = 0; i < 12; i++) {
			sum += ((i % 2) + 1) * to_int(corpRegNo.charAt(i));
		}
		if (to_int(corpRegNo.charAt(12)) != (10 - (sum % 10)) % 10) {
			return false;
		}
		return true;

	}

	/**
	 * 사업자등록번호 유효성 체크
	 *
	 * @param businessRegistrationNo 사업자등록번호
	 * @return 유효한 사업자등록번호 형식 여부
	 */
	public static boolean isBusinessRegistrationNo(String businessRegistrationNo) {
		String bizRegNo = businessRegistrationNo.replaceAll("[^0-9]", "");
		if (bizRegNo.length() != 10) {
			return false;
		}
		int share = (int) (Math.floor(to_int(bizRegNo.charAt(8)) * 5) / 10);
		int rest = (to_int(bizRegNo.charAt(8)) * 5) % 10;
		int sum = (to_int(bizRegNo.charAt(0))) + ((to_int(bizRegNo.charAt(1)) * 3) % 10) + ((to_int(bizRegNo.charAt(2)) * 7) % 10) + ((to_int(bizRegNo.charAt(3)) * 1) % 10) + ((to_int(bizRegNo.charAt(4)) * 3) % 10) + ((to_int(bizRegNo.charAt(5)) * 7) % 10) + ((to_int(bizRegNo.charAt(6)) * 1) % 10) + ((to_int(bizRegNo.charAt(7)) * 3) % 10) + share + rest + (to_int(bizRegNo.charAt(9)));
		if (sum % 10 != 0) {
			return false;
		}
		return true;
	}

	/**
	 * 신용카드번호 유효성 체크
	 *
	 * @param creditCardNo 신용카드번호
	 * @return 유효한 신용카드번호 형식 여부
	 */
	public static boolean isCreditCardNo(String creditCardNo) {
		return UtilPattern.matchCreditCardNo(creditCardNo).find();
	}

	/**
	 * 여권번호 유효성 체크
	 *
	 * @param passportNo 여권번호
	 * @return 유효한 여권번호 형식 여부
	 */
	public static boolean isPassportNo(String passportNo) {
		return UtilPattern.matchPassportNo(passportNo).find();
	}

	/**
	 * 운전면허번호 유효성 체크
	 *
	 * @param driversLicenseNo 운전면허번호
	 * @return 유효한 운전면허번호 형식 여부
	 */
	public static boolean isDriversLicenseNo(String driversLicenseNo) {
		return UtilPattern.matchDriversLicenseNo(driversLicenseNo).find();
	}

	/**
	 * 휴대폰번호 유효성 체크
	 *
	 * @param cellphoneNo 휴대폰번호
	 * @return 유효한 휴대폰번호 형식 여부
	 */
	public static boolean isCellphoneNo(String cellphoneNo) {
		return UtilPattern.matchCellphoneNo(cellphoneNo).find();
	}

	/**
	 * 일반전화번호 유효성 체크
	 *
	 * @param telephoneNo 전화번호
	 * @return 유효한 전화번호 형식 여부
	 */
	public static boolean isTelephoneNo(String telephoneNo) {
		return UtilPattern.matchTelephoneNo(telephoneNo).find();
	}

	/**
	 * 건강보험번호 유효성 체크
	 *
	 * @param healthInsuranceNo 건강보험번호
	 * @return 유효한 건강보험번호 형식 여부
	 */
	public static boolean isHealthInsuranceNo(String healthInsuranceNo) {
		return UtilPattern.matchHealthInsuranceNo(healthInsuranceNo).find();
	}

	/**
	 * 계좌번호 유효성 체크
	 *
	 * @param bankAccountNo 은행계좌번호
	 * @return 유효한 은행계좌번호 형식 여부
	 */
	public static boolean isBankAccountNo(String bankAccountNo) {
		return UtilPattern.matchBankAccountNo(bankAccountNo).find();
	}

	/**
	 * 이메일주소 유효성 체크
	 *
	 * @param emailAddress 이메일주소
	 * @return 유효한 이메일주소 형식 여부
	 */
	public static boolean isEmailAddress(String emailAddress) {
		return UtilPattern.matchEmailAddress(emailAddress).find();
	}

	/**
	 * 아이피주소 유효성 체크
	 *
	 * @param ipAddress 아이피주소
	 * @return 유효한 아이피주소 형식 여부
	 */
	public static boolean isIPAddress(String ipAddress) {
		return UtilPattern.matchIPAddress(ipAddress).find();
	}

	/**
	 * 비밀번호 유효성 체크
	 *
	 * @param PASSWORD 비밀번호
	 * @return 유효한 비밀번호 형식 여부
	 */
	public static boolean isPassword(String password) {
		String pwd = password.replaceAll(" ","");
		String pwdPattern = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[~!@#$%^*()])[A-Za-z[0-9]~!@#$%^*()]{8,16}$";

		Matcher matcher = Pattern.compile(pwdPattern).matcher(pwd);
		if (!matcher.matches()) {
			return true;
		} else if (pwd.length() < 8 || pwd.length() > 16) {
			return true;
		}

		return false;
	}
	
	/**
	 * 아이디 유효성 체크
	 *
	 * @param ID 아이디
	 * @return 유효한 아이디 형식 여부
	 */
	public static boolean isId(String id) {
		String sId = id.replaceAll(" ","");
		String idPattern = "^(?=.*[a-z])(?=.*[0-9])(?=.*\\d).{5,20}$";
		Matcher matcher = Pattern.compile(idPattern).matcher(sId);
		if (!matcher.matches()) {
			return true;
		} else if (sId.length() < 5 || sId.length() > 20) {
			return true;
		}

		return false;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////Private 메소드

	/**
	 * char로 표현된 숫자를 타입을 int로 변경
	 */
	private static int to_int(char c) {
		return Integer.parseInt(String.valueOf(c));
	}

	/**
	 * String으로 표현된 숫자를 타입을 int로 변경
	 */
	private static int to_int(String s) {
		return Integer.parseInt(s);
	}
}
