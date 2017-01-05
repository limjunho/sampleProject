package com.motionblue.mi.common;

import java.util.Calendar;

/**
 * 
 * @author      : 박진수
 * @date        : 2014. 8. 13.
 * @file name   : DateUtil.java  
 * @description : 날짜, 날짜+시간 출력
 */
public class DateUtil 
{
	private static final String DATE_PATTERN = "yyyy-MM-dd";    
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm";
	private static final int HOUR_NEW = 48;
	
	//날짜만
	public static String getSqlDateString(String date)
	{
		return portal.common.util.DateUtil.getSqlDateString(date, DATE_PATTERN);
	}
	//날짜 + 시간
	public static String getSqlDateTimeString(String date)
	{
		return portal.common.util.DateUtil.getSqlDateString(date, DATETIME_PATTERN);
	}
	
	/**
	 * 최신여부
	 * String date -> yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateNewYn(String date)
	{
		String newYn = "N";
		try {
			if(portal.common.util.DateUtil.getDateDiffHour(Calendar.getInstance().getTime(), portal.common.util.DateUtil.getDateTime(date)) <= HOUR_NEW)
			{
				newYn = "Y";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newYn; 
	}
}
