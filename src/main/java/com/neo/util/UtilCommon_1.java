package com.neo.util;

import java.io.Closeable;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;

import com.neo.common.vo.ComVO;


public class UtilCommon {

	private static final Logger logger = LoggerFactory.getLogger(UtilCommon.class);

	private UtilCommon() {
	}
	
	@SuppressWarnings("rawtypes")
	public static boolean isEmpty(Object obj) {
		if (obj instanceof String)
			return obj == null || "".equals(obj.toString().trim());
		else if (obj instanceof List)
			return obj == null || ((List) obj).isEmpty();
		else if (obj instanceof Map)
			return obj == null || ((Map) obj).isEmpty();
		else if (obj instanceof Object[])
			return obj == null || Array.getLength(obj) == 0;
		else
			return obj == null;
	}

	public static boolean isNotEmpty(Object s) {
		return !isEmpty(s);
	}

	
	/**
	 * @title: 텍스트 인코딩 확인용
	 * @설명: 단순 로그만 찍는다
	 * @param param
	 */
	public static void checkCharEncoding(String param) {
		
		if(isNotEmpty(param)) {
			String[] charSet = {"utf-8", "euc-kr", "ksc5601", "iso-8859-1", "x-windows-949"};
			for(int i=0; i<charSet.length; i++) {
				for(int j = 0; j<charSet.length; j++) {
					try {
						logger.info("["+charSet[i]+"," + charSet[j] + "]="+ new String(param.getBytes(charSet[i]), charSet[j]));
					}catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
	public static boolean isMobile(HttpServletRequest request) {
		String userAgent = request.getHeader("user-agent");
		boolean mobile1 = userAgent.matches(".*(iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson).*");
		boolean mobile2 = userAgent.matches(".*(LG|SAMSUNG|Samsung).*");
		Device  device = DeviceUtils.getCurrentDevice(request); 
		if(mobile1 || mobile2 || (device != null  && (device.isMobile() || device.isTablet()))) {
			return true;
		}
		return false;
	}
	
	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
//		logger.info("> X-FORWARDED-FOR : " + ip);

		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
//			logger.info("> Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
//			logger.info(">  WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
//			logger.info("> HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//			logger.info("> HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
//			logger.info("> getRemoteAddr : "+ip);
		}
//		logger.info("> Result : IP Address : "+ip);
		return ip;
	}
	

	// 페이징시 사용
	public static ComVO setPageRow(ComVO comVO) {
		comVO.setOffset((comVO.getPageNum() - 1) * comVO.getLimit());
		return comVO;
	}
	
	/**
	 * <pre>
	 * 랜덤텍스트추출
	 * Required value : leng(필요한자리수)
	 * </pre>
	 * 
	 * [메소드 설명을 상세하게 작성해 주세요. (html태그 사용가능)] <br>
	 * <p style="color:red;">
	 * [강조 할 때 사용합니다.]
	 * <p>
	 * 
	 * @author leekw
	 * @since 2019. 9. 16.
	 * @param leng
	 * @return String [이글을 지우고 위 파라메타 및 설명을 각 값 오른쪽에 작성해주세요.]
	 */

	public static String getRandomStr(int leng, String gubun) {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.substring(0, leng);
		return uuid + gubun;
	}
	
	public static void close(Closeable... resources) {
		for (Closeable resource : resources) {
			if (resource != null) {
				try {
					resource.close();
				} catch (Exception ignore) {
				}
			}
		}
	}

	public static String nullString(String str) {
		String result = "";
		if(isNotEmpty(str)){
			result = str;
		}
		return result;
	}
	
}
