<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%--
	객체 표현
	let sawon={} => 변수 / 함수
	형식) {sabun1,name:"홍길동",dept:"개발부"} => 자바 VO
				=> ROW
 --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=()=>{
	let sawon={sabun:1,name:"홍길동",dept:"개발부",job:"대리",pay:3500}
	
	document.write("사번:"+sawon.sabun+"<br>");
	document.write("이름:"+sawon.name+"<br>");
	document.write("개발부:"+sawon.dept+"<br>");
	document.write("대리:"+sawon.job+"<br>");
	document.write("월급:"+sawon.pay+"<br>");
	document.write("<hr>");
	document.write("사번:"+sawon['sabun']+"<br>");
	document.write("사번:"+sawon['name']+"<br>");
	document.write("사번:"+sawon['dept']+"<br>");
	document.write("사번:"+sawon['job']+"<br>");
	document.write("사번:"+sawon['pay']+"<br>");
	document.write("<hr>");
	for(let key in sawon)
		{
			document.write(sawon[key]+"<br>")
		}
	
	}
</script>
</head>
<body>

</body>
</html>