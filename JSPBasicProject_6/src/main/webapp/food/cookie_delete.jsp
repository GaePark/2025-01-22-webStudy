<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 파일명?fno=10
	String fno=request.getParameter("fno");
	Cookie[] cookies=request.getCookies();
	
	if(cookies!=null)
	{
		for(int i=0;i<cookies.length;i++)
		{
			if(cookies[i].getName().equals("house_"+fno))
			{
				cookies[i].setPath("/");
				cookies[i].setMaxAge(0);
				
				response.addCookie(cookies[i]);
				break;
			}
		}
		
	}
	response.sendRedirect("list.jsp");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>