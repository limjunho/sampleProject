<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>로그인페이지</title>  
</head>
  
<body onload="document.f.userId.focus();">
<h2>로그인 </h2>
<!-- 
<form name="form" method="post" action="./doLogin.do">
<table>
    <tr height="40px">
        <td>사용자아이디</td>
        <td><input type="text" name="userId"></td>
    </tr>
    <tr height="40px">
        <td>패스워드</td>
        <td><input type="password" name="userPw"></td>
    </tr>
</table>
<table>
    <tr>
        <td align="center"><input type="submit" value="로그인"></td>
        <td align="center"><input type="reset" value="리셋"></td>
    </tr>
</table>
</form>
 -->
<form name="f" method="POST" action="./doLogin" >
	<table>
	  <tr>
	  	<td>
	  		User:
	  	</td>
	  	<td>
	  		<input type="text" name="userId" value="" />
	  	</td>
	  </tr>
	  <tr>
	  	<td>
	  		Password:
	  	</td>
	  	<td>
	  		<input type="password" name="userPw" />
	  	</td>
	  </tr>
	  <tr>
	  	<td colspan="2">
	  		<input type="submit" name="submit" />
	  	</td>	
	  </tr>		
	  <tr>
	  	<td>
	  		<input type="reset" name="reset" />
	  	</td>
	  </tr>		
	</table>
</form>
</body>
</html>