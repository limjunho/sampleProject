package com.motionblue.mi;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import portal.common.util.BrowserUtil;

import portal.common.util.DateUtil;

import portal.common.PortalProperty;
import portal.common.exception.PortalServiceException;
import portal.common.util.ExceptionUtil;
import portal.common.util.FileHandler;
import portal.common.util.StringUtil;

@Controller
public class BaseController {

	public static Logger log = Logger.getLogger(BaseController.class);
	
	@Autowired
	protected ServletContext context;

	protected String path = "";
	public static HttpServletRequest getCurrentRequest(){
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest servletRequest = sra.getRequest();
		return servletRequest;
	}
	
	public static HttpServletResponse getCurrentResponse(){
		ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletResponse servletResponse = sra.getResponse();
		return servletResponse;
	}
	/**
	 * 페이지 메타데이터 연동 공통
	 */
	public Map<String, String> metadata = new HashMap<String, String>();
	
	public Map<String, String> getMetadata() {
		return metadata;
	}

	public void setMetadata(Map<String, String> metadata) {
		this.metadata = metadata;
	}

	/**
	 * Http Util 정의
	 */
	
	public String getContextPath(){
		return getCurrentRequest().getContextPath();
	}
	
	/**
	 * 게시물 순번 출력시 시작 번호(내림차순)
	 * @param maxNum
	 * @param currentPageNum
	 * @param pageSize
	 * @return
	 */
	public int getStartNumbering(int maxNum, int currentPageNum, int pageSize){
		int startNum = maxNum - ((currentPageNum - 1) * pageSize);
		
		return startNum;
	}
	
	/**
	 * 게시물 순번 출력시 시작 번호(오름차순)
	 * @param maxNum
	 * @param currentPageNum
	 * @param pageSize
	 * @return
	 */
	public int getStartNumberingAsc(int maxNum, int currentPageNum, int pageSize){
		int startNum = ((currentPageNum - 1) * pageSize) + 1;
		
		return startNum;
	}
	/**
	 * Exception 객체로 부터 로그 추철
	 * @param e
	 * @return
	 */
	public String getStackTrace(Exception e){
		return ExceptionUtil.getStackTrace(e);
	}
	/**
	 * 에디터로 부터 업로드된 파일 정리
	 * @param newCont 신규 글
	 * @param oldCont 이전 글
	 */
	public void cleanEditorFile(String newCont, String oldCont){
		
		log.debug("======================================== 에디터 파일 정리 시작");
		
		List<String> delFileList = new ArrayList<String>();

		// 새글로 부터 파일 추출
		List<String> newList = StringUtil.getSrcFromImageTag(newCont);
		
		// 이전글로 부터 파일 추출
		List<String> oldList = StringUtil.getSrcFromImageTag(oldCont);
		
		if ( newList.size() > 0 ){
			log.debug("DB 데이터 삭제 처리.");
			// DB 데이터 삭제 처리.
			this.removeEditorTmpData(newList);
		}
		
		// 이전 파일 정리대상 존재하지 않으므로 삭제. 
		if ( oldList.size() == 0 ){
			log.debug("이전 파일 없으므로 종료.");
			return;
		}
		
		// 파일 비교 - 이전글의 삭제 대상 추출.
		for ( String path : oldList ){
			boolean bDel = true;
			for ( String newPath : newList){
				if ( path.equals(newPath) ){
					bDel = false;
					break;
				}
			}

			// 삭제 대상에 추가.
			if ( bDel ){
				log.debug("삭제 대상 추가 : " + path);
				delFileList.add(path);
			}
		}
		
		// 파일 삭제 처리
		if ( delFileList.size() > 0 ){
			log.debug("삭제 처리 시작");
			for ( String path : delFileList ){
				try{
					String [] arrPath = path.split("/");
					log.debug(arrPath[arrPath.length-1]);
					log.debug(arrPath[arrPath.length-2]);
					log.debug(arrPath[arrPath.length-3]);
					log.debug(arrPath[arrPath.length-4]);
					
					path = PortalProperty.get("EDITOR_UPLOAD_FILE_PATH")
							+ File.separator + arrPath[arrPath.length-4]
									+ File.separator + arrPath[arrPath.length-3]
											+ File.separator + arrPath[arrPath.length-2]
													+ File.separator + arrPath[arrPath.length-1];
					FileHandler.deleteFile(path);
				}catch(Exception e){
					log.error(getStackTrace(e));
				}
			}
		}
		
		log.debug("======================================== 에디터 파일 정리 끝");
	}
	
	/**
	 * 에디터 업로드 임시 파일 정보 삭제.
	 * @param fileList
	 */
	private void removeEditorTmpData(List<String> fileList){
		// 파일 db 정리
	}
	
	/**
	 * 리턴 URL 검증 - http, https 로 시작 불가.
	 * @param rurl 검토 대상 리턴 URL
	 * @throws Exception
	 */
	protected void checkReturnUrl(String rurl) throws Exception{
		
		//String host = request.getHeader("host");
		String host = getCurrentRequest().getServerName();
		log.debug("#### host : " + host + " ###### rurl : " + rurl);
		if ( rurl == null )
			return;
		
		if ( "".equals(rurl) )
			return;
		log.debug("#### rurl.indexOf('http://' + host) : " + rurl.indexOf("http://" + host) + " ###### rurl.indexOf('https://' + host) : " + rurl.indexOf("https://" + host));
		if ( rurl.indexOf("http://") == 0 || rurl.indexOf("https://") == 0 ){
			if ( rurl.indexOf("http://" + host) != 0 && rurl.indexOf("https://" + host) != 0 ){
				throw new PortalServiceException("잘 못된 접근입니다. 외부 사이트로 이동이 불가능합니다.");
			}
		}
		
	}
	
	/**
	 * 등록/수정/삭제 등 서비스 사이트에서 접근하지 않고 referer 조작 또는 임의 접근의 경우 검사.
	 * @param request
	 * @throws Exception
	 */
	protected void checkRefererUrl(HttpServletRequest request) throws Exception{
		String referer = request.getHeader("referer");
		String host = request.getHeader("host");
		
		if ( referer == null )
			throw new PortalServiceException("잘 못된 접근입니다.");
		
		if ( "".equals(referer) )
			throw new PortalServiceException("잘 못된 접근입니다.");

		if ( referer.indexOf(("http://" + host)) == 0 || referer.indexOf(("https://" + host)) == 0 ){
			return;
		}
		
		throw new PortalServiceException("잘 못된 접근입니다.");
	}
	
	/**
	 * @author : 박진수
	 * @date : 2013. 10. 31.
	 * @description : history back 경고 메세지를 띄움
	 */
	protected void alertHistoryBackMsg(String msg) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<script type=\"text/javascript\" >");
			if (msg != null) {
				sb.append("alert('" + msg + "');");
			}
			// sb.append("history.go(-1);");
			sb.append("history.back();");
			sb.append("</script>");

			getCurrentResponse().setCharacterEncoding("utf-8");
			getCurrentResponse().setContentType("text/html;charset:UTF-8");
			getCurrentResponse().setHeader("Cache-Control", "no-cache");
			PrintWriter out = getCurrentResponse().getWriter();
			out.println(sb.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * alert msg 후 script 수행.
	 * @param msg
	 * @param script
	 */
	protected void alert(String msg, String script) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<script type=\"text/javascript\" >");
			if (msg != null) {
				sb.append("alert('" + msg + "');");
			}
			// sb.append("history.go(-1);");
			sb.append(script);
			sb.append("</script>");

			getCurrentResponse().setCharacterEncoding("utf-8");
			getCurrentResponse().setContentType("text/html;charset:UTF-8");
			getCurrentResponse().setHeader("Cache-Control", "no-cache");
			PrintWriter out = getCurrentResponse().getWriter();
			out.println(sb.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void printJson(String text) {
		try {
			HttpServletResponse res = getCurrentResponse();
			res.setCharacterEncoding("utf-8");
			res.setContentType("text/html");
			// res.setCharacterEncoding("UTF-8");
			PrintWriter out = null;
			out = res.getWriter();
			out.print(text);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
     * @author      : 박진수
     * @date        : 2014. 8. 17.
     * @description : 엑셀다운로드 여부 확인 및 설정
     */
    public String checkMediaType(String jspPath, String excelFileName){
    	String mediaMode = StringUtil.isNull(getCurrentRequest().getParameter("mediaMode"));
    	log.debug("######################################### mediaMode : " + mediaMode);
    	if("excel".equals(mediaMode))
		{
    		String fileName = excelFileName + "_" + DateUtil.getCurrentDate("yyMMdd");
			try {
				fileName = BrowserUtil.setBrowserFileName(fileName + ".xls");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			getCurrentResponse().setContentType("application/vnd.ms-excel");
			getCurrentResponse().setHeader("content-Disposition", "inline; filename=" + fileName);
			
			return jspPath;
		}
    	else
    	{
    		return jspPath;
    	}
    }
	
}
