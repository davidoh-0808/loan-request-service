package com.neo.util;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

/**
 * 스트링 처리 라이브러리
 */
public class UtilString {
	private static final Pattern TAG_PATTERN = Pattern.compile("<[^<]*?>");
	private static final Pattern SCRIPT_TAG_PATTERN = Pattern.compile("<\\s*[s|S][c|C][r|R][i|I][p|P][t|T].*?>.*?<\\s*/\\s*[s|S][c|C][r|R][i|I][p|P][t|T]\\s*>", Pattern.DOTALL);

	/**
	 * 생성자, 외부에서 객체를 인스턴스화 할 수 없도록 설정
	 */
	private UtilString() {
	}

	/**
	 * 특정 기호를 기준으로 스트링을 잘라서 배열로 반환하는 함수
	 * <br>
	 * ex) abc||def||efg : array[0]:"abc", array[1]:"def", array[2]:"efg"
	 *
	 * @param str 원본 문자열
	 * @param token 토큰 문자열
	 *
	 * @return 토큰으로 분리된 문자열의 배열
	 */
	public static String[] tokenFn(String str, String token) {
		StringTokenizer st = null;
		String[] toStr = null;
		int tokenCount = 0;
		int index = 0;
		int len = 0;
		try {
			// token이 두개이상 붙어있으면 token과 token 사이에 공백을 넣는다.
			len = str.length();
			for (int i = 0; i < len; i++) {
				if ((index = str.indexOf(token + token)) != -1) {
					str = str.substring(0, index) + token + " " + token + str.substring(index + 2, str.length());
				}
			}
			st = new StringTokenizer(str, token);
			tokenCount = st.countTokens();
			toStr = new String[tokenCount];
			for (int i = 0; i < tokenCount; i++) {
				toStr[i] = st.nextToken();
			}
		} catch (Exception e) {
			toStr = null;
		}
		return toStr;
	}

	/**
	 * 정해진 길이보다 문자열이 크면 문자열을 잘라서 ".."를 추가해 주는 기능.
	 * 게시판 제목 같은 곳에서 사용됨.
	 *
	 * @param str 원본 문자열
	 * @param len 유효 문자열 길이
	 *
	 * @return 유효문자열에 "..." 이 연결된 문자열
	 */
	public static String limitString(String str, int len) {
		String rval = "";
		byte[] bstr = null;
		int bcount = 0; // 인자로 넘어온 스트링의 총 바이트 수
		int scount = 0; // 인자로 넘어온 스트링의 총 글자 수
		int bindex = 0; // 제한하려 하는 바이트의 인덱스
		int i = 0;
		try {
			bstr = str.getBytes();
			bcount = bstr.length;
			if (bcount <= len) {
				rval = str;
			} else {
				scount = str.length();
				for (i = 0; i < scount - 1; i++) {
					int btmplen = str.substring(i, i + 1).getBytes().length;
					bindex += btmplen;
					if (bindex + 3 >= len) {
						break;
					}
				}
				rval = new String(bstr, 0, bindex) + "..";
			}
		} catch (Exception e) {
		}
		return rval;
	}

	/**
	 * 스트링의 특정 부분을 다른 기호로 변환하는 함수
	 *
	 * @param src 원본 문자열
	 * @param oldstr 찾을 문자열
	 * @param newstr 바꿀 문자열
	 *
	 * @return 찾을 문자열이 바꿀 문자열로 변환된 문자열
	 */
	public static String replaceStr(String src, String oldstr, String newstr) {
		if (src == null)
			return null;
		StringBuilder dest = new StringBuilder();
		int len = oldstr.length();
		int srclen = src.length();
		int pos = 0;
		int oldpos = 0;
		while ((pos = src.indexOf(oldstr, oldpos)) >= 0) {
			dest.append(src.substring(oldpos, pos));
			dest.append(newstr);
			oldpos = pos + len;
		}
		if (oldpos < srclen)
			dest.append(src.substring(oldpos, srclen));
		return dest.toString();
	}

	/**
	 * 스트링 타입의 바이트 단위를 사람이 읽기 좋은 형태로 변환(KByte, MByte, GByte)
	 *
	 * @param stringbyte 스트링으로 표기된 바이트 문자열
	 *
	 * @return 사람이 읽기 좋은 형태의 문자열
	 */
	public static String byteToHumanReadable(String stringbyte) {
		double d = 0.0;
		String ret = "";
		try {
			if (stringbyte == null || stringbyte.equals("")) {
				ret = "0 Bytes";
				return ret;
			}
			double dbyte = Double.parseDouble(stringbyte);
			java.text.MessageFormat mf = new java.text.MessageFormat("{0,number,####.#}");
			if (dbyte == 0.0) {
				ret = "0 Bytes";
			} else if (dbyte >= 1024.0 && dbyte < 1048576.0) {
				d = dbyte / 1024.0;
				Object[] objs = { Double.valueOf(d) };
				ret = mf.format(objs);
				ret += " KB";
			} else if (dbyte >= 1048576.0 && dbyte < 1073741824.0) {
				d = dbyte / 1048576.0;
				Object[] objs = { Double.valueOf(d) };
				ret = mf.format(objs);
				ret += " MB";
			} else if (dbyte >= 1073741824.0) {
				d = dbyte / 1073741824.0;
				Object[] objs = { Double.valueOf(d) };
				ret = mf.format(objs);
				ret += " GB";
			} else {
				Object[] objs = { Double.valueOf(dbyte) };
				ret = mf.format(objs);
				ret += " Bytes";
			}
			return (ret);
		} catch (Exception e) {
			return "0 Bytes";
		}
	}

	/**
	 * long 타입의 바이트 단위를 사람이 읽기 좋은 형태로 변환(KByte, MByte, GByte)
	 *
	 * @param longbyte long타입으로 표기된 바이트 값
	 *
	 * @return 사람이 읽기 좋은 형태의 문자열
	 */
	public static String byteToHumanReadable(long longbyte) {
		Long L_byte = Long.valueOf(longbyte);
		double d = 0.0;
		String ret = "";
		if (L_byte.toString() == null || L_byte.toString().equals("")) {
			ret = "0 Bytes";
			return ret;
		}
		double dbyte = Double.parseDouble(L_byte.toString());
		java.text.MessageFormat mf = new java.text.MessageFormat("{0,number,####.#}");
		if (dbyte == 0.0) {
			ret = "0 Bytes";
		} else if (dbyte >= 1024.0 && dbyte < 1048576.0) {
			d = dbyte / 1024.0;
			Object[] objs = { Double.valueOf(d) };
			ret = mf.format(objs);
			ret += " KB";
		} else if (dbyte >= 1048576.0 && dbyte < 1073741824.0) {
			d = dbyte / 1048576.0;
			Object[] objs = { Double.valueOf(d) };
			ret = mf.format(objs);
			ret += " MB";
		} else if (dbyte >= 1073741824.0) {
			d = dbyte / 1073741824.0;
			Object[] objs = { Double.valueOf(d) };
			ret = mf.format(objs);
			ret += " GB";
		} else {
			Object[] objs = { Double.valueOf(dbyte) };
			ret = mf.format(objs);
			ret += " Bytes";
		}
		return (ret);
	}

	/**
	 * 인자에 해당하는 스트링의 charter-set을 한글로 변환하는 함수
	 *
	 * @param str 원본 문자열
	 *
	 * @return 한글(EUC-KR)로 변환된 문자열
	 *
	 * @throws UnsupportedEncodingException UnsupportedEncodingException
	 */
	public static String convertKorean(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("iso-8859-1"), "EUC-KR");
	}

	/**
	 * 인자에 해당하는 스트링의 charter-set을 utf-8로 변환하는 함수
	 *
	 * @param str 원본 문자열
	 *
	 * @return 유니코드(UTF-8)로 변환된 문자열
	 *
	 * @throws UnsupportedEncodingException UnsupportedEncodingException
	 */
	public static String convertUTF8(String str) throws UnsupportedEncodingException {
		return new String(str.getBytes("iso-8859-1"), "utf-8");
	}

	/**
	 * int 타입의 숫자를 숫자형태(세자리마다 ,로 구분)로 변환하는 함수
	 *
	 * @param num 원본 int형 숫자
	 *
	 * @return 세자리마다 콤마(,)로 구분된 문자열
	 */
	public static String numberFormat(int num) {
		return numberFormat(Integer.toString(num));
	}

	/**
	 * long 타입의 숫자를 숫자형태(세자리마다 ,로 구분)로 변환하는 함수
	 *
	 * @param num 원본 long형 숫자
	 *
	 * @return 세자리마다 콤마(,)로 구분된 문자열
	 */
	public static String numberFormat(long num) {
		return numberFormat(Long.toString(num));
	}

	/**
	 * 스트링 타입의 숫자를 숫자형태(세자리마다 ,로 구분)로 변환하는 함수
	 *
	 * @param str 원본 문자열
	 *
	 * @return 세자리마다 콤마(,)로 구분된 문자열
	 */
	public static String numberFormat(String str) {
		try {
			return java.text.NumberFormat.getInstance().format(Integer.parseInt(str));
		} catch (Exception e) {
			return "0";
		}
	}
	
	/**
	 * 스트링 타입의 숫자를 숫자형태(세자리마다 ,로 구분)로 변환하는 함수 더블
	 * @param str
	 * @return
	 */
	public static String doubleNumberFormat(String str) {
		try {
			return java.text.NumberFormat.getInstance().format(Double.parseDouble(str));
		} catch (Exception e) {
			return "0";
		}
	}

	/**
	 * 인자에 포함된 모든 태크를 제거하는 함수
	 * @param src 원본문자열
	 * @return 태그가 제거된 문자열
	 */
	public static String stripTag(String src) {
		if (src == null) {
			return "";
		}
		String noTag = src;
		while (TAG_PATTERN.matcher(noTag).find()) {
			noTag = TAG_PATTERN.matcher(noTag).replaceAll("");
		}
		return noTag;
	}

	/**
	 * 인자에 포함된 스크립트 태크를 제거하는 함수
	 * @param src 원본문자열
	 * @return 스크립트 태그가 제거된 문자열
	 */
	public static String stripScriptTag(String src) {
		if (src == null) {
			return "";
		}
		String noScriptTag = src;
		while (SCRIPT_TAG_PATTERN.matcher(noScriptTag).find()) {
			noScriptTag = SCRIPT_TAG_PATTERN.matcher(noScriptTag).replaceAll("");
		}
		return noScriptTag;
	}

	/**
	 * 숫자를 제외한 모든 문자는 ""으로 변환
	 * @param str
	 * @return
	 */
	public static String onlyNumReplace(String str) {
		String result = "";
		if(UtilCommon.isEmpty(str)) {
			return result;
		}else {
			String match = "[^0-9\\s]";
			result = str.replaceAll(match, "");
		}
		return result;
	}
	
	/**
	 * 숫자 및 .,-를 제외한 모든 문자는 ""으로 변환
	 * @param str
	 * @return
	 */
	public static String onlyDoubleReplace(String str) {
		String result = "0";
		
		if(UtilCommon.isEmpty(str)) {
			return result;
		}else {
//			String match = "[^0-9\\.\\-\\s]";
//			result = str.replaceAll(match, "");
			
			try {
				double resultDouble = Double.parseDouble(str.replaceAll(",", ""));
				result = String.valueOf(resultDouble);
			} catch (Exception e) {
				e.printStackTrace();
				result = "0";
			}
		}
		
		return result;
	}
	
	/**
	 * 영어(대소문자) 를 제외한 모든 문자는 ""으로 변환
	 * @param str
	 * @return
	 */
	public static String onlyAlpaReplace(String str) {
		String result = "";
		String match = "[^a-zA-Z\\s]";
		result = str.replaceAll(match, "");
		return result;
	}
	
	/**
	 * 숫자랑 영어(대소문자) 를 제외한 모든 문자는 ""으로 변환
	 * @param str
	 * @return
	 */
	public static String onlyNumAlpaReplace(String str) {
		String result = "";
		String match = "[^0-9a-zA-Z\\s]";
		result = str.replaceAll(match, "");
		return result;
	}
	
	/**
	 * &lt;body&gt; 등과 같은 html 데이타를 <body> 와 같은 
	 * html 구문으로 변경해줌
	 * @param str
	 * @return
	 */
	public static String escapeHtml4(String str) {
		String result = "";
		// html코드 unescape
		if(UtilCommon.isNotEmpty(str)) {
			result = StringEscapeUtils.unescapeHtml4(str);
		}
		return result;
	}
	
	/**
	 * 랜덤 텍스트구하기
	 * @param type: 랜덤텍스트형식
	 * NUM: 숫자만
	 * ENG: 영문만(대소)
	 * ENG_UP: 영문대문자만
	 * ENG_LOW: 영문소문자만
	 * NUM_ENG: 숫자 + 영문(대소)
	 * NUM_ENG_UP: 숫자 + 영문(대)
	 * NUM_ENG_LOW: 숫자 + 영문(소)
	 * 그외는 숫자만
	 * @param count: 글자수
	 * @return
	 */
	public static String randomStr(String type, int count) {
		StringBuffer buf = new StringBuffer();
		Random rnd = new Random();
		for(int i=0; i<count; i++){
			
			if("NUM".equalsIgnoreCase(type)) {
				// 숫자만
				buf.append((rnd.nextInt(10)));
			}else if("ENG".equalsIgnoreCase(type)) {
				// 영문만
				Random rnd2 = new Random();
				if(rnd2.nextBoolean()){
					// 대문자
					buf.append((char)((int)(rnd.nextInt(26))+65));
				}else {
					// 소문자
					buf.append((char)((int)(rnd.nextInt(26))+97));
				}
			}else if("ENG_UP".equalsIgnoreCase(type)) {
				// 영문 대문자만
				buf.append((char)((int)(rnd.nextInt(26))+65));
			}else if("ENG_LOW".equalsIgnoreCase(type)) {
				// 영문 소문자만
				buf.append((char)((int)(rnd.nextInt(26))+97));
			}else if("NUM_ENG".equalsIgnoreCase(type)) {
				// 숫자영문조합
				// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
				if(rnd.nextBoolean()){
					Random rnd2 = new Random();
					if(rnd2.nextBoolean()){
						// 대문자
						buf.append((char)((int)(rnd.nextInt(26))+65));
					}else {
						// 소문자
						buf.append((char)((int)(rnd.nextInt(26))+97));
					}
				}else{
					buf.append((rnd.nextInt(10)));
				}
			}else if("NUM_ENG_UP".equalsIgnoreCase(type)) {
				// 숫자영문(대)조합
				// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
				if(rnd.nextBoolean()){
					// 대문자
					buf.append((char)((int)(rnd.nextInt(26))+65));
				}else{
					buf.append((rnd.nextInt(10)));
				}
			}else if("NUM_ENG_LOW".equalsIgnoreCase(type)) {
				// 숫자영문(소)조합
				// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.
				if(rnd.nextBoolean()){
					// 대문자
					buf.append((char)((int)(rnd.nextInt(26))+97));
				}else{
					buf.append((rnd.nextInt(10)));
				}
			}else{
				buf.append((rnd.nextInt(10)));
			}
		}
		return buf.toString();
	}
	
	
	/**
	 * 개행문자변환처리
	 * @param str
	 * @param gubun
	 * @return
	 */
	public static String removeCRLF(String str, String gubun) {
		String result = "";
		// html코드 unescape
		if(UtilCommon.isNotEmpty(str)) {
			result = str.replaceAll("(\r\n|\r|\n|\n\r)", gubun);
		}
		return result;
	}
	
	
}