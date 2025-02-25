<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	사용자 행위에 따른 함수 호출
	------------ 마우스 , 키보드 => 이벤트
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	const onClickBtn = () => {
	let n1= document.querySelector(".num1").value;
	let n2= document.querySelector(".num2").value;
			document.write(Number(n1)+Number(n2));
	}
</script>
</head>
<body>
	<input type="text" class="num1">
	<input type="text" class="num2">
	<input type="button" value="버튼" onclick="onClickBtn()">
</body>
</html>