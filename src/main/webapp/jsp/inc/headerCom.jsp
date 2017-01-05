<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.net.*" %>

<%@ page import="com.motionblue.mi.login.LoginService" %>
<%@ page import="com.motionblue.mi.common.*" %>
<%@ page import="com.motionblue.mi.user.UserVo" %>
<%@ page import="portal.common.PortalProperty" %>

<%--
<%@ page import="com.motionblue.mi.login.LoginServiceImpl" %>
<%@ page import="com.ibatis.common.resources.Resources"%>
<%@ page import="com.ibatis.sqlmap.client.SqlMapClient"%>
<%@ page import="com.ibatis.sqlmap.client.SqlMapClientBuilder"%>
<%@ page import="config.SqlManager" %>
--%>

<%@ page import="portal.common.util.*" %>
<%@ page import="portal.common.PortalProperty" %>
<%@ page import="org.springframework.web.context.WebApplicationContext" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="org.springframework.context.support.FileSystemXmlApplicationContext" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="pu" uri="/WEB-INF/tld/PortalUtil.tld" %>
<!doctype html>
<html lang="ko">
<head>
<meta charset="utf-8">
<meta name="title" content="모블로 통합앱 구축">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="format-detection" content="telephone=no">
<title>모블로 통합앱</title>
<%
	WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
	
	CommonService commonService = null;
	commonService = (CommonService)wac.getBean("commonService");
%>
<%

	LoginService loginService = new LoginService();
	boolean isLogin = loginService.isLogin(request);
	String _globalContextPath = request.getContextPath();
	
	UserVo globalUserVo = (UserVo)request.getSession().getAttribute("UserVo");
	if(isLogin)
	{

	}

	String _userNm = "";
	String _userCls = "";
	String _userClsNm = "";
	String _userSeq = "0";
	String _userEmailReceptionYn = "";
	String _userSmsReceptionYn = "";

	if(globalUserVo != null)
	{
		_userNm = globalUserVo.getUserNm();						//사용자 명
		_userCls = globalUserVo.getUserCls();					//사용자 등급
		_userClsNm = globalUserVo.getUserClsNm();				//사용자 등급 명
		_userSeq = String.valueOf(globalUserVo.getUserSeq());	//사용자 일련번호
		_userEmailReceptionYn = globalUserVo.getEmailReceptionYn();	//사용자 이메일 수신여부
		_userSmsReceptionYn = globalUserVo.getSmsReceptionYn();	//사용자 SMS 수신여부
	}
	//List<MenuVo> serviceMenuList = new ArrayList<MenuVo>();
	List<MenuVo> permitMenuList = new ArrayList<MenuVo>();
	//List<MenuVo> subMenuList = new ArrayList<MenuVo>();

	//serviceMenuList = commonService.getServiceMenuList();
	MenuVo menuVo = new MenuVo();

	//permitMenuList = commonService.getPermitList(Long.parseLong(_userSeq));
	//subMenuList = commonService.getSubMenuList(menuVo);
	String loginFlag = (String)request.getParameter("loginFlag");
	
	
	int maxInactive = request.getSession().getMaxInactiveInterval(); 
	
	//2016.3.10, cache 수정
	response.setHeader("Cache-Control","no-store");   
	response.setHeader("Pragma","no-cache");   
	response.setDateHeader("Expires",0);  
	if (request.getProtocol().equals("HTTP/1.1")) 
		response.setHeader("Cache-Control", "no-cache"); 

%>

<c:set var="permitMenuList" value="<%=permitMenuList %>" />  
<c:set var="_userCls" value="<%=_userCls %>" />  
<c:set var="_userSeq" value="<%=_userSeq %>" />  

<script type="text/javascript" >
var __globalContextPath = "<%= request.getContextPath() %>";
var _isLogin = <%=isLogin%>;
var _loginFlag = <%=loginFlag%>;
var _userNm = "<%=_userNm%>";

var setTime = Number("<%=maxInactive %>") * 1000;

var timerId;
//타임 체크
if(_isLogin)
{
	timerId = window.setTimeout("timeOut()",setTime);
	function resetTimer() {
		//debugConsole("setTime : " + setTime);
		window.clearTimeout(timerId);
		timerId = window.setTimeout("timeOut()",setTime);
	}

	function timeOut(){	
		alert("장시간 사용하지 않아 자동 로그아웃 되었습니다.\n로그인 후 이용해 주시기 바랍니다.");
		location.href = __globalContextPath + "/doLogout.do?autoLogout=true";
		/*
		if(!remainTime()){
			alert("장시간 사용하지 않아 자동 로그아웃 되었습니다.\n로그인 후 이용해 주시기 바랍니다.");
			location.href = __globalContextPath + "/doLogout.do?autoLogout=true";
		}
		else
		{
			resetTimer();
		}
		*/
	}
	/*
	function remainTime()
	{
		var rtFlag;
		$.ajax({
			type : "POST",
			url : "./remainTime.do",
			async: false,
			dataType : "json",
			success : function(data) {
				//debugConsole(data);
				//세션시간 종료시 false
				if(data.sessionFlag == "false")
				{
					rtFlag = false;
				}
				else
				{
					rtFlag = true;
					setTime = 1000 * Math.abs(data.remainTime);
				}
				return rtFlag;
			},
			failure : function(data) {
			}
		});
		//debugConsole(rtFlag);
		return rtFlag;
	}
	*/
}
</script>
<%@ include file="/jsp/inc/commonCss.jsp" %>
<%@ include file="/jsp/inc/headerScript.jsp" %>
<%@ include file="/jsp/inc/commonScript.jsp" %>


