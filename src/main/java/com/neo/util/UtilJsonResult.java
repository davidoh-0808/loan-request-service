package com.neo.util;

import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UtilJsonResult {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	/** 
	 * 성공
	 * 
	 * @param json
	 */
	public static void setReturnCodeSuc(JSONObject json) {
		json.put("rCode", "0000");
		json.put("rMsg", "완료되었습니다.");
	}

	/**
	 * 성공이며 특별한 메세지가 필요한 경우
	 * @param json
	 * @param rMsg
	 */
	public static void setReturnCodeSuc(JSONObject json, String rMsg) {
		json.put("rCode", "0000");
		json.put("rMsg", rMsg);
	}

	/**
	 * 실패
	 * @param json
	 */
	public static void setReturnCodeFail(JSONObject json) {
		json.put("rCode", "9999");
		json.put("rMsg", "처리중 문제가 발생하였습니다.");
	}

	/**
	 * 실패지만 특별한 메세지가 필요한 경우
	 * @param json
	 * @param str
	 */
	public static void setReturnCodeFail(JSONObject json, String str) {
		json.put("rCode", "9999");
		json.put("rMsg", str);
	}

	/**
	 * 결과와 메세지를 각각 설정 할 경우
	 * @param json
	 * @param rCode
	 * @param rMsg
	 */
	public static void setReturnCodeEtc(JSONObject json, String rCode, String rMsg) {
		json.put("rCode", rCode);
		json.put("rMsg", rMsg);
	}

	/**
	 * Map을 jsonString으로 변환한다.
	 *
	 * @param map Map<String, Object>.
	 * @return String.
	 */
	public static JSONObject renderJsonFromMap(Map<String, Object> map) {
		JSONObject json = new JSONObject();
		for( Map.Entry<String, Object> entry : map.entrySet() ) {
			String key = entry.getKey();
			Object value = entry.getValue();
			json.put(key, value);
		}
		return json;
	}	
	
	/**
	 * Json string 을 JSONObject로 변환
	 * @param str
	 * @return
	 */
	public static JSONObject renderJsonFromString(String str) throws Exception {
		JSONObject json = new JSONObject();
		
		if(UtilCommon.isNotEmpty(str)){
			
			JSONParser parser = new JSONParser();
//			try {
				Object obj = parser.parse(str);
				json = (JSONObject) obj;
//			} catch (ParseException e) {
//				e.printStackTrace();
//				return json;
//			}
			
		}
		
		return json;
	}
	
}
