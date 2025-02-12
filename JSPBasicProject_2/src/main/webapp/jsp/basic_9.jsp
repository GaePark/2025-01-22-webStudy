<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" errorPage="error.jsp"  %>
    <%--
    	어떤 에러든 상관없이 같은 페이지를 출력한다
    	=> 404
    	
    	=> 500
    	
    	=> 400
    	------------------------- web.xml
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	int a = 10/0;
%>
</body>
</html>