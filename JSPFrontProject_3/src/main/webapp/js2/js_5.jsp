<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	이벤트 : 사용자가 해위를 한 경우 실행
						--------------------- 브라우저안에서 사용자 요청
						onclick : 이미지 , 버튼 클릭...
						onmouseover / ounmouser
						onkeydown / onkeyup
						onchange / onsubmit
	1. 이벤트 처리
			= 인라인 이벤트 처리
					<button onclick="함수호출">
			= 고전 이벤트 처리
				
				$('button').onclick(()=>{})
			= 이벤트 리스너 이용 => Jquery....
				$('button').on('click',()=>{})
			
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	const btnClick = () => {
		alert("버튼 클릭")
	}
		
	
</script>
</head>
<body>
	<input type="button" value="클릭" onclick="btnClick()">
</body>
</html>