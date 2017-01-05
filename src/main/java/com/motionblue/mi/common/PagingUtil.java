package com.motionblue.mi.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class PagingUtil {

	private static Logger log = Logger.getLogger(PagingUtil.class);
	public static String getNavi(Integer gotoPage, Integer pPageSize, Long recordCount, String gotoUrl, String strQueryString){
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		//ServletContext ctx = getServletContext();
		String contextPath = request.getContextPath();
		log.info("gotoPage : " + gotoPage);
		log.info("pPageSize : " + pPageSize);
		log.info("recordCount : " + recordCount);
		log.info("gotoUrl : " + gotoUrl);
		log.info("strQueryString : " + strQueryString);
		
		long pageCount = 0;
		if ( recordCount == 0 ) {
			return "";
		}
		
		pageCount = portal.common.util.PagingUtil.getPageCount(pPageSize, recordCount);
		log.debug("================== " + pageCount);
		
		int blockPage = 0;
		String strBuffer = "";
		
		String sPrevLink, sNextLink;
		String sFirstLink, sEndLink;
		
		if ( "".equals(strQueryString) ) {
			sFirstLink = gotoUrl + "?gotoPage=1";
			sEndLink = gotoUrl + "?gotoPage=" + pageCount;
			
			strQueryString = "?";
			gotoUrl = gotoUrl + strQueryString + "gotoPage=";
		} else {
			sFirstLink = gotoUrl + "?" + strQueryString + "&amp;gotoPage=1";
			sEndLink = gotoUrl + "?" + strQueryString + "&amp;gotoPage=" + pageCount;
			
			strQueryString = "?" + strQueryString;
			gotoUrl = gotoUrl + strQueryString + "&amp;gotoPage=";
		}	
		
		sFirstLink = "<a href=\"" + sFirstLink + "\" class=\"prev_all\">맨 앞</a>";
		sEndLink = "<a href=\"" + sEndLink + "\" class=\"next\">맨 끝</a>";
		
		blockPage = ((gotoPage-1)/10)*10+1;
		//************************ 이전 10 개구문 시작 ***************************
		if ( gotoPage == 1 ){
			sPrevLink = gotoUrl  + gotoPage;
		} else {
			// 'strBuffer = strBuffer & " <a href=""" & gotoUrl  & (gotoPage-1) & """><<이전</a> ";
			sPrevLink = gotoUrl  + (gotoPage-1);
		}
		sPrevLink = "<a href=\"" + sPrevLink + "\" class=\"prev\">이전</a>";
		//************************ 이전 10 개 구문 끝***************************
		for ( int i=0; i<pageCount; i++ )
		{
			if ( i >= 10 )
				break;
			
			if ( pageCount < blockPage )
				break;
			
			if ( blockPage == gotoPage ){
				// 현재 페이지
	    		strBuffer += "<strong>" + blockPage + "</strong>";
			}else{
				// 현재 페이지 이외
				strBuffer += "<a href=\"" + gotoUrl + blockPage + "\">" + blockPage + "</a>";
			}
			
			blockPage++;
		}

		// ************************ 다음 10 개 구문 시작*************************** 
		if ( gotoPage >= pageCount ){
			// 'strBuffer = strBuffer & " 다음>> ";
			sNextLink = gotoUrl  + gotoPage;
		} else {
			// 'strBuffer = strBuffer & " <a href=" & gotoUrl & gotoPage+1 & ">다음>></a>"		
			sNextLink = gotoUrl + ( gotoPage+1 );
		}
		sNextLink = "<a href=\"" + sNextLink + "\" class=\"next_all\">다음</a>";
		// ************************ 다음 10 개 구문 끝***************************

		return (sFirstLink + sPrevLink + strBuffer + sNextLink + sEndLink);

	}

	public static String getAdminNavi(Integer gotoPage, Integer pPageSize, Long recordCount, String gotoUrl, String strQueryString){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String contextPath = request.getContextPath();
		log.debug("pageUtil : " + contextPath);
		
		long pageCount = 0;
		if ( recordCount == 0 ) {
			return "";
		}
		
		pageCount = portal.common.util.PagingUtil.getPageCount(pPageSize, recordCount);
		
		int blockPage = 0;
		String strBuffer = "";
		
		String sPrevLink, sNextLink;
		String sFirstLink, sEndLink;
		
		if ( "".equals(strQueryString) ) {
			sFirstLink = gotoUrl + "?gotoPage=1";
			sEndLink = gotoUrl + "?gotoPage=" + pageCount;
			
			strQueryString = "?";
			gotoUrl = gotoUrl + strQueryString + "gotoPage=";
		} else {
			sFirstLink = gotoUrl + "?" + strQueryString + "&amp;gotoPage=1";
			sEndLink = gotoUrl + "?" + strQueryString + "&amp;gotoPage=" + pageCount;
			
			strQueryString = "?" + strQueryString;
			gotoUrl = gotoUrl + strQueryString + "&amp;gotoPage=";
		}	
		
		sFirstLink = "<a href=\"" + sFirstLink + "\"><img src=\"" + contextPath + "/images/admin/pre01.gif\" alt=\"처음\" /></a>";
		sEndLink = "<a href=\"" + sEndLink + "\"><img src=\"" + contextPath + "/images/admin/next01.gif\" alt=\"마지막\" /></a>";
		
		blockPage = ((gotoPage-1)/10)*10+1;
		//************************ 이전 10 개구문 시작 ***************************
		if ( gotoPage == 1 ){
			sPrevLink = gotoUrl  + gotoPage;
		} else {
			// 'strBuffer = strBuffer & " <a href=""" & gotoUrl  & (gotoPage-1) & """><<이전</a> ";
			sPrevLink = gotoUrl  + (gotoPage-1);
		}
		sPrevLink = "<a href=\"" + sPrevLink + "\"><img src=\"" + contextPath + "/images/admin/pre02.gif\" alt=\"이전\" /></a>";
		//************************ 이전 10 개 구문 끝***************************
		for ( int i=0; i<pageCount; i++ )
		{
			if ( i >= 10 )
				break;
			
			if ( pageCount < blockPage )
				break;
			
			if ( blockPage == gotoPage ){
				// 현재 페이지
	    		strBuffer += "<strong>" + blockPage + "</strong>";
			}else{
				// 현재 페이지 이외
				strBuffer += "<a href=\"" + gotoUrl + blockPage + "\">" + blockPage + "</a>";
			}
			
			blockPage++;
		}

		// ************************ 다음 10 개 구문 시작*************************** 
		if ( gotoPage >= pageCount ){
			// 'strBuffer = strBuffer & " 다음>> ";
			sNextLink = gotoUrl  + gotoPage;
		} else {
			// 'strBuffer = strBuffer & " <a href=" & gotoUrl & gotoPage+1 & ">다음>></a>"		
			sNextLink = gotoUrl + ( gotoPage+1 );
		}
		sNextLink = "</ul><span class=\"next\"><a href=\"" + sNextLink + "\"><img src=\"" + contextPath + "/images/admin/next02.gif\" alt=\"다음\" /></a>";
		// ************************ 다음 10 개 구문 끝***************************

		return (sFirstLink + sPrevLink + strBuffer + sNextLink + sEndLink);

	}

	/**
	 * 
	 * @author      : 박진수
	 * @date        : 2014. 9. 3.
	 * @description : 페이지 블록 사이즈 추가
	 */
	public static String getNavi2(Integer gotoPage, Integer pPageSize, Long recordCount, String gotoUrl, String strQueryString, Integer blockSize ){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String contextPath = request.getContextPath();
		log.debug("pageUtil : " + contextPath);
		
		long pageCount = 0;
		if ( recordCount == 0 ) {
			return "";
		}
		
		pageCount = portal.common.util.PagingUtil.getPageCount(pPageSize, recordCount);
		
		int blockPage = 0;
		String strBuffer = "";
		
		String sPrevLink, sNextLink;
		String sFirstLink, sEndLink;
		
		if ( "".equals(strQueryString) ) {
			sFirstLink = gotoUrl + "?gotoPage=1";
			sEndLink = gotoUrl + "?gotoPage=" + pageCount;
			
			strQueryString = "?";
			gotoUrl = gotoUrl + strQueryString + "gotoPage=";
		} else {
			sFirstLink = gotoUrl + "?" + strQueryString + "&amp;gotoPage=1";
			sEndLink = gotoUrl + "?" + strQueryString + "&amp;gotoPage=" + pageCount;
			
			strQueryString = "?" + strQueryString;
			gotoUrl = gotoUrl + strQueryString + "&amp;gotoPage=";
		}	
		
		sFirstLink = "<a href=\"" + sFirstLink + "\" class=\"prev_all\"></a>";
		sEndLink = "<a href=\"" + sEndLink + "\" class=\"next\"></a>";
		
		blockPage = ((gotoPage-1)/blockSize)*blockSize+1;
		//************************ 이전 10 개구문 시작 ***************************
		if ( gotoPage == 1 ){
			sPrevLink = gotoUrl  + gotoPage;
		} else {
			// 'strBuffer = strBuffer & " <a href=""" & gotoUrl  & (gotoPage-1) & """><<이전</a> ";
			sPrevLink = gotoUrl  + (gotoPage-1);
		}
		sPrevLink = "<a href=\"" + sPrevLink + "\" class=\"prev\"></a>";
		//************************ 이전 10 개 구문 끝***************************
		for ( int i=0; i<blockSize; i++ )
		{
			if ( i >= 10 )
				break;
			
			if ( pageCount < blockPage )
				break;
			
			if ( blockPage == gotoPage ){
				// 현재 페이지
	    		strBuffer += "<strong>" + blockPage + "</strong>";
			}else{
				// 현재 페이지 이외
				strBuffer += "<a href=\"" + gotoUrl + blockPage + "\">" + blockPage + "</a>";
			}
			
			blockPage++;
		}

		// ************************ 다음 10 개 구문 시작*************************** 
		if ( gotoPage >= pageCount ){
			// 'strBuffer = strBuffer & " 다음>> ";
			sNextLink = gotoUrl  + gotoPage;
		} else {
			// 'strBuffer = strBuffer & " <a href=" & gotoUrl & gotoPage+1 & ">다음>></a>"		
			sNextLink = gotoUrl + ( gotoPage+1 );
		}
		sNextLink = "<a href=\"" + sNextLink + "\" class=\"next_all\"></a>";
		// ************************ 다음 10 개 구문 끝***************************

		return (sFirstLink + sPrevLink + strBuffer + sNextLink + sEndLink);

	}

	/**
	 * 
	 * @author      : 박진수
	 * @date        : 2015. 2. 13.
	 * @description : 페이지 블록 사이즈 추가
	 */
	public static String getNaviSub(Integer gotoPage, Integer pPageSize, Long recordCount, String gotoUrl, String strQueryString, Integer blockSize ){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		String contextPath = request.getContextPath();
		log.debug("pageUtil : " + contextPath);
		
		long pageCount = 0;
		if ( recordCount == 0 ) {
			return "";
		}
		
		pageCount = portal.common.util.PagingUtil.getPageCount(pPageSize, recordCount);
		
		int blockPage = 0;
		String strBuffer = "";
		
		String sPrevLink, sNextLink;
		String sFirstLink, sEndLink;
		
		if ( "".equals(strQueryString) ) {
			sFirstLink = gotoUrl + "?subGotoPage=1";
			sEndLink = gotoUrl + "?subGotoPage=" + pageCount;
			
			strQueryString = "?";
			gotoUrl = gotoUrl + strQueryString + "subGotoPage=";
		} else {
			sFirstLink = gotoUrl + "?" + strQueryString + "&amp;subGotoPage=1";
			sEndLink = gotoUrl + "?" + strQueryString + "&amp;subGotoPage=" + pageCount;
			
			strQueryString = "?" + strQueryString;
			gotoUrl = gotoUrl + strQueryString + "&amp;subGotoPage=";
		}	
		
		sFirstLink = "<a href=\"" + sFirstLink + "\" class=\"prev_all\"></a>";
		sEndLink = "<a href=\"" + sEndLink + "\" class=\"next\"></a>";
		
		blockPage = ((gotoPage-1)/blockSize)*blockSize+1;
		//************************ 이전 10 개구문 시작 ***************************
		if ( gotoPage == 1 ){
			sPrevLink = gotoUrl  + gotoPage;
		} else {
			// 'strBuffer = strBuffer & " <a href=""" & gotoUrl  & (gotoPage-1) & """><<이전</a> ";
			sPrevLink = gotoUrl  + (gotoPage-1);
		}
		sPrevLink = "<a href=\"" + sPrevLink + "\" class=\"prev\"></a>";
		//************************ 이전 10 개 구문 끝***************************
		for ( int i=0; i<blockSize; i++ )
		{
			if ( i >= 10 )
				break;
			
			if ( pageCount < blockPage )
				break;
			
			if ( blockPage == gotoPage ){
				// 현재 페이지
	    		strBuffer += "<strong>" + blockPage + "</strong>";
			}else{
				// 현재 페이지 이외
				strBuffer += "<a href=\"" + gotoUrl + blockPage + "\">" + blockPage + "</a>";
			}
			
			blockPage++;
		}

		// ************************ 다음 10 개 구문 시작*************************** 
		if ( gotoPage >= pageCount ){
			// 'strBuffer = strBuffer & " 다음>> ";
			sNextLink = gotoUrl  + gotoPage;
		} else {
			// 'strBuffer = strBuffer & " <a href=" & gotoUrl & gotoPage+1 & ">다음>></a>"		
			sNextLink = gotoUrl + ( gotoPage+1 );
		}
		sNextLink = "<a href=\"" + sNextLink + "\" class=\"next_all\"></a>";
		// ************************ 다음 10 개 구문 끝***************************

		return (sFirstLink + sPrevLink + strBuffer + sNextLink + sEndLink);

	}
}