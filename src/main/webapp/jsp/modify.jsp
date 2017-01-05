<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Spring security</title>
</head>
<body>
    <div style="margin-top:100px;">
    <form action="./updateUserPw" method="post">
     	아이디 : <input type="text" id="userId" name="userId">
     	비밀번호 : <input type="text" id="userPw" name="userPw">
    	<button type="submit">등록</button>
    </form>
	</div>
</body>
</html>
