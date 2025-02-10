<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%--
    	JSP : Java Server Page => 서버에서 실행되는 자바 파일
    		 = Back-End
    		 = JSP : Servlet을 쉽게 작성할 수 있게 만든 파일
    		 		 => 영역 : doGet / doPost
    		 		 => 메소드 영역이다 => 대부분은 지역변수
    		 		 => JSP는 View역할 (화면 출력)
    		 		 => Front-End로 전향
    		 = Servlet : 보안 (라이브러리 , 보안 파일 , 연결)
    		 				  ----------- 스프링
    		 				  
    		 Back-End : Java / Oracle / Servlet / Spring
    		 Front-End : HTML / CSS / JavaScript / JSP
    		 --------- + Full Stack
    		 
   		JSP=> HTML / CSS / JavaScript / Java / Oracle연동
   						   ----------
   			1. HTML + 자바가 섞여있음 : 구분이 어렵다
   			   자바를 코딩하는 영역 : 스크립트
   			   <%
   			   	일반 자바 : 메소드 호출 / 변수 설정 / 제어문....
   			   %> : 스크립트릿
   			   <%= %> : 표현식 => out.println()
   			   					  데이터 출력
   			   전역변수 , 메소드 제작 : <%! %> : 선언문
   			   *** 스크립트를 벗어나면 일반 문자열이다
   			2. 동작방식 => 정적페이지 / 동적페이지
   						   ----------   -------
   						   				한개의 파일을 이용해서 데이터를 변경
   						   				=> JSP / PHP / ASP / CGI
   						   				   ---------------------
   						   				   희망부서 : 자바 웹개발
   						   데이터변경이 없다 (HTML)
   			3. JSP => 사용법
   						1. 지시자
   							page : JSP에 대한 정보를 저장
   							       1) 변환 => 브라우저 출력시 어떤 형식을 출력할지 지정
   							          html => text/html
   							          xml => text/xml
   							          json => text/plain
   							          --------------------
   							          자바스크립트 객체 표현법
   							          => JavaScript Object Nontation
   							          => 자바 : VO => 자바스크립트에서는 VO인식이 불가능
   							          VO => {변수:값....}
   							          let sawon={}
   							          List => [] => Array
   						2. 자바 코딩 방법
   						3. 지원하는 라이브러리 (내장 객체)
   						4. Cookie / Session
   						5. JSP 액션태그
   						6. JSTL / EL
   						7. MVC => MV => MVC
   						
   						=> JSP 브라우저에서 실행
   						       -----------------
   						       => 톰캣 => 클래스로 변경
   						       a.jsp=> a_jsp.java => 컴파일 => a_jsp.class
   						       								   -----------
   						       								   서블릿 파일
   						       								   ㅣ
   						       								  인터프리터
   						       								   ㅣ
   						       								  HTML만 메모리에 출력 (Buffer)
   						       								  --------------------
   						       								   ㅣ브라우저에서 읽어 간다
    	
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!
	int a=10;
%>
<%
	String name="홍길동";
//  out.println(name);
%>
<%= name %>
</body>
</html>