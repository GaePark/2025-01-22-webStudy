<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    	1. 클래스 : HttpServletResponse
    	2. 역할
    		1) 서버 응답
    			=> HTML
    				setContentType("text/html;charset=UTF-8)
    			=> Cookie
    				addCookie(cookie)
    			** 동시에 전송이 불가능 => 한개씩 보내야함
    			=> setHeader() : 다운로드
    		2) 화면 이동
    			sendRedirect("이동할 파일명")=> GET방식만 사용 가능
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>