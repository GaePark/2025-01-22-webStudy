<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%--
    	지시자 / 내장 객체 / 액션태그 => JSP 기초
    	Cookie / Session / 데이터베이스
    	JSPL / EL => MVC (Spring)
    	------------------------- 자바관련 (데이터 관리)
    	=> 브라우저안에서 처리 (팝업 , 애니메이션 , ...) : JavaScript
    		JSPFront
    	지시자
    		page / taglib / include => <jsp:include>
    		----
    		=> JSP에 대한 정보
    		4개
    			=> import : 라이브러리 로드
    			=> contentType : 브라우저에 보낼 데이터 형식 지정
    				
    			=> errorPage : 에러 발생시 화면 이동
    			404/403/405/412/500 => 화면이동
    			=> buffer : 출력에 필요한 HTML을 저장할 메모리 공간크기
    			  --------9kb
    			
    			*** page에서 제공하는 모든 속성은 한번만 사용이 가능
    			
    			=> isErrorPage="true" => exception사용시에 주로 사용
     --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%= application.getRealPath("/") %>
</body>
</html>