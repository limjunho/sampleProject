<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="portal.common.util.StringUtil"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.io.Reader"%>
<%@ page import="java.util.List" %>  

<%@ page import="org.apache.log4j.Logger" %>

<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="org.springframework.context.support.FileSystemXmlApplicationContext" %>

<%@ page import="java.io.*"%>
<%@ page import="java.util.UUID"%>
<%@ page import="java.text.SimpleDateFormat"%>

<%@ page import="portal.common.*"%>
<%@ page import="portal.common.util.*"%>
<%@ page import="com.motionblue.mi.common.*"%>

<%
Logger logger = Logger.getLogger(this.getClass());

EditorService editorService = null;
WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
editorService = (EditorService)wac.getBean("editorService");

String domainUrl = "";//PortalProperty.get("DIRECT_URL");
String uploadWebRoot = PortalProperty.get("EDITOR_UPLOAD_FILE_PATH");
String fileUploadPath = application.getRealPath(uploadWebRoot);

//파일정보
String sFileInfo = "";
//파일명을 받는다 - 일반 원본파일명
String filename = request.getHeader("file-name");
//파일 확장자
String filename_ext = filename.substring(filename.lastIndexOf(".")+1);
//확장자를소문자로 변경
filename_ext = filename_ext.toLowerCase();
   
//이미지 검증 배열변수
String[] allow_file = {"jpg","png","bmp","gif"};

//돌리면서 확장자가 이미지인지 
int cnt = 0;
for(int i=0; i<allow_file.length; i++) {
  if(filename_ext.equals(allow_file[i])){
      cnt++;
  }
}

//이미지가 아님
if(cnt == 0) {
  out.println("NOTALLOW_"+filename);
} else {
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
							+ File.separator + year 
							+ File.separator + month 
							+ File.separator + day
							+ File.separator;
		
		//System.out.println(filePath);
		File file = new File(filePath);
		if(!file.exists()) {
		  file.mkdirs();
		}
		
		realFileNm = today+UUID.randomUUID().toString() + filename.substring(filename.lastIndexOf("."));
		String rlFileNm = filePath + realFileNm;
		
		///////////////// 서버에 파일쓰기 ///////////////// 
		InputStream is = request.getInputStream();
		OutputStream os=new FileOutputStream(rlFileNm);
		int numRead;
		byte b[] = new byte[Integer.parseInt(request.getHeader("file-size"))];
		while((numRead = is.read(b,0,b.length)) != -1){
		  os.write(b,0,numRead);
		}
		if(is != null) {
		  is.close();
		}
		os.flush();
		os.close();
		///////////////// 서버에 파일쓰기 /////////////////
	
		//정보 출력
		sFileInfo += "&bNewLine=true";
		//sFileInfo += "&sFileName="+ realFileNm;;
		//img 태그의 title 속성을 원본파일명으로 적용시켜주기 위함
		sFileInfo += "&sFileName="+ filename;;
		sFileInfo += "&sFileURL=" + domainUrl + request.getContextPath() + uploadWebRoot + "/" + year + "/" + month + "/" + day + "/" + realFileNm;
		out.print(sFileInfo);
		
		EditorFileVo vo = new EditorFileVo();
		vo.setName(filename);
		vo.setFolder(year + "/" + month + "/" + day);
		vo.setSize(StringUtil.parseInt(request.getHeader("file-size")));
		vo.setExt(FileHandler.getFileExt(filename));
		vo.setSaveName(realFileNm);
		editorService.add(vo);
		
		//System.out.println(sFileInfo);
		//System.out.println(realFileNm);
		//System.out.println(request.getHeader("file-size"));
}
%>