<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*, java.net.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<H1>Creating Client/Server Applications</H1>
<% 
try{
     int character;
     Socket socket = new Socket("192.168.1.69", 7777);

     InputStream inSocket = socket.getInputStream();
     OutputStream outSocket = socket.getOutputStream();

     String str = "Hello!\n";
     byte buffer[] = str.getBytes();
     outSocket.write(buffer);

     while ((character = inSocket.read()) != -1) {
         out.print((char) character);
     }

     socket.close();
 }
 catch(java.net.ConnectException e){
 %>
     You must first start the server application 
     (YourServer.java) at the command prompt.
 <%
 }
 %>
</body>
</html>