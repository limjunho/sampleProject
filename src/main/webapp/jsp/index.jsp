<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/lib/jquery-2.2.4.js"></script>
<script type="text/javascript">
	var name = "<spring:message code="input.name" javaScriptEscape="true"/>";
	alert(name);
</script>
</head>
<body>
<h2>Hello World!</h2>
Language : <a href="?lang=en">English</a> | <a href="?lang=ko">한국어</a>
<br/>
<spring:message code="list.title" />
</body>
</html>
