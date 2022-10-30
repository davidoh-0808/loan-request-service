
package com.neo.util;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 날짜관련 유틸리티 클래스이다.
 */
public class UtilDate {

	private UtilDate() {
		
	}
	
	/**
	 * 날짜여부 체크
	 * @param year : 년
	 * @param month : 월
	 * @param day : 일
	 * @return boolean : 날짜여부
	 */
	public static boolean isDate(int year, int month, int day) {
		return (toDate(year, month, day) != null);
	}

	/**
	 * 날짜여부 체크
	 * @param dateStr : 년월일
	 * @return boolean : 날짜여부
	 */
	public static boolean isDate(String dateStr) {
		return (toDate(dateStr) != null);
	}

	/**
	 * 날짜여부 체크
	 * @param dateStr : 년월일
	 * @param format : 날짜형식(ex : yyyyMMdd, yyyy-MM-dd...)
	 * @return boolean : 날짜여부
	 */
	public static boolean isDate(String dateStr, String format) {
		return (toDate(dateStr, format) != null);
	}

	/**
	 * 원하는 날짜 Date 생성
	 * @param year : 년
	 * @param month : 월
	 * @param day : 일
	 * @return Date : 원하는 날짜의 Date객체
	 */
	public static Date toDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.clear();
		cal.setLenient(false);
		cal.set(year, month - 1, day);
		return cal.getTime();
	}

	/**
	 * 원하는 날짜 Date 생성
	 * @param dateStr : 년월일(yyyy-MM-dd)
	 * @return Date : 원하는 날짜의 Date객체
	 */
	public static Date toDate(String dateStr) {
		return toDate(dateStr.replaceAll("[-|/|.]", ""), "yyyyMMdd");
	}

	/**
	 * 원하는 날짜 Date 생성
	 * @param dateStr : 년월일
	 * @param format : 날짜형식(ex : yyyyMMdd, yyyy-MM-dd...)
	 * @return Date : 원하는 날짜의 Date객체
	 */
	public static Date toDate(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException pe) {
			return null;
		}
	}

	/**
	 * Date 객체를 String으로 변환(yyyy-MM-dd)
	 * @param date : 날짜객체
	 * @return String : 날짜의 String 객체(yyyy-MM-dd)
	 */
	public static String toString(Date date) {
		return toString(date, "yyyy-MM-dd");
	}

	/**
	 * Date 객체를 String으로 변환
	 * @param date : 날짜객체
	 * @param format : 원하는 날짜형식(ex : yyyyMMdd, yyyy-MM-dd...)
	 * @return String : 날짜의 String 객체
	 */
	public static String toString(Date date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 * 두 날짜 사이의 차
	 * @param startday : 시작일
	 * @param endday : 종료일
	 * @return 두 날짜 사이의 차
	 */
	public static long getDateDiff(Date startday, Date endday) {
		long diff = endday.getTime() - startday.getTime();
		return (diff / (1000 * 60 * 60 * 24));
	}

	/**
	 * 날짜계산
	 * @설명: date 파람과 format 의 형식을 일치해야함
	 * @param date : 기준일자(2020-01-01)
	 * @param format : 포멧(yyyy-MM-dd)
	 * @param type : YEAR, MONTH, DAY
	 * @param val : + - 값 
	 * @return
	 */
	public static String calcDate(String date, String format, String type, int val) {
		String result = "";
		
		if(UtilCommon.isEmpty(date) || UtilCommon.isEmpty(format) || UtilCommon.isEmpty(type) || UtilCommon.isEmpty(val)) {
			return result;
		}
		
		try {
			Calendar cal = null;
			cal = getCalendar(date, format);
			
			if("YEAR".equalsIgnoreCase(type)) {
				result = addYear(cal, format, val);
			}else if("MONTH".equalsIgnoreCase(type)) {
				result = addMonth(cal, format, val);
			}else if("DAY".equalsIgnoreCase(type)) {
				result = addDay(cal, format, val);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static Calendar getCalendar(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date d = sdf.parse(date, new ParsePosition(0));
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		return cal;
	}
	
	public static String setDateFormat(Calendar cal, String format) {
		String dateStr = "";
		SimpleDateFormat dFormat = null;
		Date d = null;
		try {
			d = cal.getTime();
			dFormat = new SimpleDateFormat(format);
			dateStr = dFormat.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return dateStr;
	}
	
	public static String getDateFormatt(String formatt) {
		// 현재시간
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(formatt, Locale.KOREA);
		Date currentTime = new Date();

		return mSimpleDateFormat.format(currentTime);
	}
	
	public static String addYear(Calendar cal, String format, int year) {
		cal.add(Calendar.YEAR, year);
		String returnStr = setDateFormat(cal, format);
		return returnStr;
	}

	public static String addMonth(Calendar cal, String format, int month) {
		cal.add(Calendar.MONTH, month);
		String returnStr = setDateFormat(cal, format);
		return returnStr;
	}

	public static String addDay(Calendar cal, String format, int day) {
		cal.add(Calendar.DATE, day);
		String returnStr = setDateFormat(cal, format);
		return returnStr;
	}

	/*	날짜계산 종료	*/

	/**
	 * 오늘 날짜를 인자에 해당하는 형태로 가져오는 함수
	 *
	 * @param option 1 은 "2000-11-12", 2 는 "2000", 3 은 "11", 4 는 "12", 5 는 "20001112", 6 은 시, 7 은 분, 8 은 초, 9 는 요일별 정수전환, 10은 오늘이 몇번째 주인지
	 *
	 * @return 오늘날짜를 포맷한 문자열
	 */
	public static String makeToday(int option) {
		Calendar calToday00;
		calToday00 = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		String dayVal = calToday00.get(Calendar.DATE) + "";
		String monthVal = Integer.toString(calToday00.get(Calendar.MONTH) + 1);
		int ampm = calToday00.get(Calendar.AM_PM);
		if (Integer.parseInt(dayVal) < 10)
			dayVal = "0" + dayVal;
		if (Integer.parseInt(monthVal) < 10)
			monthVal = "0" + monthVal;
		String dateVal = "";
		// ===================================================
		//	1 은 "2000-11-12"
		//	2 는 "2000"
		//	3 은 "11"
		//	4 는 "12"
		//	5 는 "20001112"
		//	6 은 시
		//	7 은 분
		//	8 은 초
		//	9 는 요일별 정수전환
		//	10은 오늘이 몇번째 주인지
		// ===================================================
		switch (option) {
		case 1:
			dateVal = Integer.toString(calToday00.get(Calendar.YEAR)) + "-" + monthVal + "-" + dayVal;
			break;
		case 2:
			dateVal = Integer.toString(calToday00.get(Calendar.YEAR));
			break;
		case 3:
			dateVal = monthVal;
			break;
		case 4:
			dateVal = dayVal;
			break;
		case 5:
			dateVal = Integer.toString(calToday00.get(Calendar.YEAR)) + monthVal + dayVal;
			break;
		case 6:
			dateVal = Integer.toString(calToday00.get(Calendar.HOUR) + ampm * 12);
			break;
		case 7:
			dateVal = Integer.toString(calToday00.get(Calendar.MINUTE));
			break;
		case 8:
			dateVal = Integer.toString(calToday00.get(Calendar.SECOND));
			break;
		case 9:
			dateVal = Integer.toString(calToday00.get(Calendar.DAY_OF_WEEK));
			break;
		case 10:
			dateVal = Integer.toString(calToday00.get(Calendar.WEEK_OF_MONTH));
			break;
		}
		return dateVal;
	}

	/**
	 * 인자에 해당하는 날짜로부터 몇 일 이동한 날짜를 가져오는 함수
	 *
	 * @param curDate 기준 날짜
	 * @param option 1은 day 만큼 이후의 날짜, 2는 day 만큼 이전의 날짜
	 * @param day 이후, 이전으로 계산할 일자(일 단위)
	 *
	 * @return 변환된 문자열
	 */
	public static String moveDate(String curDate, int option, int day) {
		String destDate = "";
		int curYear;
		int curMonth;
		int curDay;
		Calendar cal;
		curYear = Integer.parseInt(curDate.substring(0, 4));
		curMonth = Integer.parseInt(curDate.substring(4, 6));
		curDay = Integer.parseInt(curDate.substring(6, 8));
		cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		if (option == 1)
			cal.set(curYear, curMonth - 1, curDay + day); // day 만큼 이후의 날짜.
		else
			cal.set(curYear, curMonth - 1, curDay - day); // day 만큼 이전의 날짜.
		curYear = cal.get(Calendar.YEAR);
		curMonth = cal.get(Calendar.MONTH) + 1;
		curDay = cal.get(Calendar.DATE);
		destDate = Integer.toString(curYear);
		if (curMonth < 10)
			destDate += "0" + Integer.toString(curMonth);
		else
			destDate += Integer.toString(curMonth);
		if (curDay < 10)
			destDate += "0" + Integer.toString(curDay);
		else
			destDate += Integer.toString(curDay);
		return destDate;
	}

	/**
	 * 인자에 해당하는 날짜와 현재 날짜의 간격이 interval에 포함되면 true, 포함되지 않으면 false를 반환하는 함수
	 * interval 의 기본값은 1일로 설정된다.
	 * @param regday 등록 날짜 문자열
	 *
	 * @return interval 보다 작으면 true, 같거나 크면 false
	 */
	public static boolean isNew(String regday) {
		int default_interval = 1;
		return isNew(regday, default_interval);
	}

	/**
	 * 인자에 해당하는 날짜와 현재 날짜의 간격이 interval에 포함되면 true, 포함되지 않으면 false를 반환하는 함수
	 *
	 * @param regday 등록 날짜 문자열
	 * @param interval 비교할 시간 간격(일 단위)
	 *
	 * @return interval 보다 작으면 true, 같거나 크면 false
	 */
	public static boolean isNew(String regday, int interval) {
		Calendar today = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		Calendar regCal = Calendar.getInstance();
		Date current;
		Date regdate;
		int diffDay;
		boolean isnew;
		try {
			int regYear = Integer.parseInt(regday.substring(0, 4));
			int regMonth = Integer.parseInt(regday.substring(4, 6)) - 1;
			int regDay = Integer.parseInt(regday.substring(6, 8));
			int regHour = Integer.parseInt(regday.substring(8, 10));
			int regMinute = Integer.parseInt(regday.substring(10, 12));
			int regSecond = Integer.parseInt(regday.substring(12, 14));
			regCal.set(regYear, regMonth, regDay, regHour, regMinute, regSecond);
			current = today.getTime();
			regdate = regCal.getTime();
			diffDay = Math.abs((int) ((current.getTime() - regdate.getTime()) / 1000.0 / 60.0 / 60.0 / 24.0));
			isnew = (diffDay < interval) ? true : false;
		} catch (Exception e) {
			isnew = false;
		}
		return isnew;
	}
	
	/**
	 * 스트링 타입의 날짜 데이타를 정해진 포맷으로 변환하는 함수
	 *
	 * <br>
	 * ex1) StringUtil.nalDesign("20080101090000", 1): "2008-01-01"
	 * <br>
	 * ex2) StringUtil.nalDesign("20080101090000", 2): "08-01-01 09:00"
	 * <br>
	 * ex3) StringUtil.nalDesign("20080101090000", 3): "09:00"
	 * <br>
	 * ex4) StringUtil.nalDesign("20080101090000", 4): "01-01"
	 * <br>
	 * ex5) StringUtil.nalDesign("20080101090000", 5): "08-01-01"
	 * <br>
	 * ex6) StringUtil.nalDesign("20080101090000", 6): "01-01 09:00"
	 * <br>
	 * ex7) StringUtil.nalDesign("20080101090000", 7): "2008년 01월 01일"
	 *
	 * @param str 원본 문자열
	 * @param option 날짜 옵션
	 *
	 * @return 포맷된 날짜 문자열
	 */
	public static String nalDesign(String str, int option) {
		String returnValue = "";
		if (str != null && str.length() > 7) {
			if (option == 1)
				returnValue = str.substring(0, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
			else if (option == 2) // 12자리 이상의 날짜를 인자로 받아서 년 월 일 사이에 "/" 시 분 사이에 ":"를 끼워 넣는다.
				returnValue = str.substring(2, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10) + ":" + str.substring(10, 12);
			else if (option == 3) // 12자리 이상의 날짜를 인자로 받아서 시 분 사이에 ":"를 끼워 넣는다.(시,분 만 리턴한다.)
				returnValue = str.substring(8, 10) + ":" + str.substring(10, 12);
			else if (option == 4)
				returnValue = str.substring(4, 6) + "-" + str.substring(6, 8);
			else if (option == 5)
				returnValue = str.substring(2, 4) + "-" + str.substring(4, 6) + "-" + str.substring(6, 8);
			else if (option == 6)
				returnValue = str.substring(4, 6) + "-" + str.substring(6, 8) + " " + str.substring(8, 10) + ":" + str.substring(10, 12);
			else if (option == 7) // 8자리 날짜를 인자로 받아서 2006년 03월 28일 형식으로 만든다.
				returnValue = str.substring(0, 4) + "년 " + str.substring(4, 6) + "월 " + str.substring(6, 8) + "일";
			else
				returnValue = "";
		} else {
			returnValue = "-";
		}
		return returnValue;
	}
	
}