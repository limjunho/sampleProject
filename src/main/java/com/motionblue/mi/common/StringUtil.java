package com.motionblue.mi.common;

import java.text.DecimalFormat;

public class StringUtil {

	/**
	 * 2015.03.19, 박진수, 입력된 숫자를 받아 천단위로 콤마를 붙여 반환한다.
	 * @param str
	 * @return
	 */
	public static String getThousandsCommas(Long num)
	{
		return new DecimalFormat("#,###").format(num);
	}
}
