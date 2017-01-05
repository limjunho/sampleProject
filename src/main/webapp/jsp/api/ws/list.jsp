<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/lib/jquery-2.2.4.js"></script>
<script type="text/javascript" src="/js/lib/sockjs-0.3.4.js"></script>
<script type="text/javascript">
	var ws = null;
	var url = "/echo";

    $(document).ready(function(){
        $("#sendBtn").click(function(){
            sendMessage();
        });
    });
    
    //websocket을 지정한 URL로 연결
    var sock= new SockJS(url);
    //websocket 서버에서 메시지를 보내면 자동으로 실행된다.
    sock.onmessage = onMessage;
    //websocket 과 연결을 끊고 싶을때 실행하는 메소드
    sock.onclose = onClose;
    
    function sendMessage(){
    	//websocket으로 메시지를 보내겠다.
        sock.send($("#message").val());
    }
            
    //evt 파라미터는 websocket이 보내준 데이터다.
    function onMessage(evt){  //변수 안에 function자체를 넣음.
        var data = evt.data;
        $("#data").append(data+"<br/>");
        /* sock.close(); */
    }
    
    function onClose(evt){
        $("#data").append("연결 끊김");
    }
   
</script>
</head>
<body>
    <input type="text" id="message"/>
    <input type="button" id="sendBtn" value="전송"/>
    <div id="data"></div>
</body>
</html>