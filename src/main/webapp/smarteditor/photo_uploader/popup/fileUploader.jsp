<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.io.Reader"%>
<%@ page import="java.util.List" %>  

<%@ page import="org.apache.log4j.Logger" %>

<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="org.springframework.context.support.FileSystemXmlApplicationContext" %>

<%@page import="java.io.*"%>
<%@page import="java.util.UUID"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page import="portal.common.model.FileInfoVO"%>
<%@ page import="portal.common.util.MbUpload" %>
<%@ page import="portal.common.util.RequestHelper" %>
<%@ page import="portal.common.util.StringUtil" %>

<%@page import="portal.common.*"%>
<%@page import="portal.common.util.*"%>
<%@page import="com.motionblue.mi.common.*"%>

<%
Logger logger = Logger.getLogger(this.getClass());

EditorService editorService = null;
WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
editorService = (EditorService)wac.getBean("editorService");

String domainUrl = "";//PortalProperty.get("DIRECT_URL");
String uploadWebRoot = PortalProperty.get("EDITOR_UPLOAD_FILE_PATH");
String fileUploadPath = application.getRealPath(uploadWebRoot);

//이미지이므로 신규 파일로 디렉토리 설정 및 업로드   
//파일 기본경로
String dftFilePath = fileUploadPath;
logger.debug(dftFilePath);

String realFileNm = "";
SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
String today = formatter.format(new java.util.Date());
String year = today.substring(0, 4);
String month = today.substring(4, 6);
String day = today.substring(6, 8);

//파일 기본경로 _ 상세경로
String filePath = 	dftFilePath
					+ File.separator;
/*
					+ File.separator + year 
					+ File.separator + month 
					+ File.separator + day
*/
//System.out.println(filePath);
File file = new File(filePath);
if(!file.exists()) {
file.mkdirs();
}

//Set Init multipart/form-data
logger.debug(filePath);
MbUpload mbUpload = new MbUpload(request);

mbUpload.setUseDateFolder(true);
mbUpload.setUseUidName(true);
mbUpload.setRepositoryPath(filePath);
mbUpload.setSizeMax(10 * 1024 * 1024);
mbUpload.init();


// SmartEditor 연동 정보
String url = mbUpload.getParameter("callback");
//System.out.println("callback : " + url);
url += "?callback_func=" + mbUpload.getParameter("callback_func");

// Start Upload File
List<FileInfoVO> fileInfoList = mbUpload.upload();
if ( fileInfoList == null ){
	logger.debug("File has not.");
}else{
	Iterator<FileInfoVO> itr = fileInfoList.iterator();
	while (itr.hasNext()){
		FileInfoVO fileInfoVO = (FileInfoVO)itr.next();
		logger.debug("File name is " + fileInfoVO.getFieldName() + " : " + fileInfoVO.getName());
		
// realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
// String rlFileNm = filePath + realFileNm;
		/*
		// 수정 업로드에 의한 삭제 대상 파일 설정.
		String delFileFieldNm = fileInfoVO.getFieldName().replace("files", "fileId");
		String delFileId = mbUpload.getParameter(delFileFieldNm);
		
		if ( delFileId != null ) arrDelFileList.add(delFileId);
		
		bbsInfoVO.addFile(new BbsFileVO(fileInfoVO));
		
		$url .= "&bNewLine=true";
		$url .= "&sFileName=".urlencode(urlencode($name));
		$url .= "&sFileURL=/smarteditor/demo/upload/".urlencode(urlencode($name));
		*/
		url += "&bNewLine=true";
		url += "&sFileName=" + fileInfoVO.getName();
		url += "&sFileURL=" + domainUrl + request.getContextPath() + uploadWebRoot + "/" + year + "/" + month + "/" + day + "/" + fileInfoVO.getSaveName();
		
	}
}

//System.out.println("aaaaaaaaaaaaaaaaaaaaa1111111111");
//System.out.println(url);
response.sendRedirect(url);
%>