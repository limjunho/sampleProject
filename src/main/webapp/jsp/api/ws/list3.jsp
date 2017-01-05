<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/js/lib/jquery-2.2.4.js"></script>
<script type="text/javascript" src="/js/lib/sockjs-0.3.4.js"></script>
<script type="text/javascript" src="/js/lib/stomp.js"></script>
<script type="text/javascript">
//Create stomp client over sockJS protocol (see Note 1)
/*
 The client starts by create a sockJS by specifying the endpoint(ie./hello) to connect to and then a stomp client is created over the socket. 
 The endpoint here should match that defined in the Spring MVC configuration in the lines.
 Note also the 2nd line referring to sockJS.
 	<websocket:stomp-endpoint path="/hello">
 	<websocket:sockjs/>
 	<websocket:stomp-endpoint>
 */
var socket = new SockJS("/hello");
var stompClient = Stomp.over(socket);

// callback function to be called when stomp client is connected to server (see Note 2)
/*
Then a callback function is created and assigned to a variable connectCallback.
This is called when a successful connection is made by the stomp client.
This allows us to start making subscriptions to messages (as in codes, repeated below) and sending messages.
Note the subscription is for the topin "/topic/greetings"
	stompClient.subscribe("/topic/greetings", function(greeting){
		alert(JSON.pares(greeting.body).content);
	});
 */
var connectCallback = function() {
     alert("connected!");
     stompClient.subscribe('/topic/greetings', function(greeting){
          alert(JSON.parse(greeting.body).content);
     });
}; 

// callback function to be called when stomp client could not connect to server (see Note 3)
/*
A error callback function is defined if stomp client fails to connect to server.
 */
var errorCallback = function(error) {
     // display the error's message header:
     alert(error.headers.message);
};

// Connect as guest (Note 4)
/*
This line makes the connection registering the callback functions.
 */
stompClient.connect("guest", "guest", connectCallback, errorCallback);
   
function fnSayHi(){
	stompClient.send("/app/hello", JSON.stringify({'name': 'Joe'}));
} 
$(function(){
	$("#sendBtn").click(function(){
		fnSayHi();
	});	
});
</script>
</head>
<body>
    <input type="text" id="message"/>
    <input type="button" id="sendBtn" value="전송"/>
    <div id="data"></div>
</body>
</html>