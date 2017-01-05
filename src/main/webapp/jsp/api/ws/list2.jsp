<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/lib/jquery-2.2.4.js"></script>
<script type="text/javascript">
	var ws = null;
	var url = "ws://miapp.com/echo/websocket";

	$(document).ready(function(){
        connect();
        
        $("#sendBtn").click(function(){
        	echo();
        });
    });

    function connect(){
    	ws = new WebSocket(url);
    	
    	ws.onopen = function(){
    		// 서버에 접속한 후 이벤트 정의
    	};
    	
    	ws.onmessage = function (event){
    		// 서버에서 Session.send를 이용해서 메시지 전송할 때 수신 이벤트 정의
    	    var data = event.data;
    	    $("#data").append(data+"<br/>");
    	};
    	
    	ws.onclose = function(event){
    		// 서버에서 접속 종료한 후 이벤트 정의
    		$("#data").append("연결 끊김");
    	};
    }
    
    function disconnect(){
    	ws.close();
    	ws = null;
    }
    
    function echo(){
    	// send 명령을 이용해서 전송을 한다.
    	var message = $("#message").val();
    	ws.send(message);
    }
</script>
</head>
<body>
    <input type="text" id="message"/>
    <input type="button" id="sendBtn" value="전송" />
    <div id="data"></div>
</body>
</html>