package com.neo.common.view;

import java.util.HashMap;
import java.util.Map;

/**
 * 통계로그용 URL 매핑
 * @author leekw
 * 여기에 URL을 병기해야 로그에 적재됨
 */
public class UrlMappingInfo {
	public static final Map<String, String> urlInfo = new HashMap<>();
	
	static {
		urlInfo.put("/front/main/mainList", "메인");
		urlInfo.put("/front/consultation/request", "상담신청");
		urlInfo.put("/front/consultation/qna", "자주하는질문");
		urlInfo.put("/front/product/intro", "사업소개");
		urlInfo.put("/front/product/target", "지원대상");
		urlInfo.put("/front/product/process", "대출절차");
		urlInfo.put("/front/product/policy", "거래기본약관");
		urlInfo.put("/front/thank/finance", "고마워요미소금융");
		urlInfo.put("/front/thank/news", "뉴스");
		urlInfo.put("/front/thank/report", "고객의소리");
		urlInfo.put("/front/foundation/greeting", "인사말");
		urlInfo.put("/front/foundation/vision", "재단비전");
		urlInfo.put("/front/foundation/organization", "조직도");
		urlInfo.put("/front/foundation/branch", "지점안내");
		urlInfo.put("/front/foundation/report", "재정보고");
	}
}
