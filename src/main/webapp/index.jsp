<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String rurl = request.getContextPath() + "/signin";
	response.sendRedirect(rurl); 
%>