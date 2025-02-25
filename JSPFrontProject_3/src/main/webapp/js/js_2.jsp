<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트 사용법
	1. 내부 스크립트 : 파일 한개 제어
		<script type="text/javascript"></script>
	2. 외부 스크립트 : 여러 파일 제어
	3. 인라인 스크립트 : 한개 태그 제어
	
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<%--
			ES6
			Vue / React / Next
			<script type="text/babel>
				소스코딩
			</script>
			
			<script>
				소스코딩
			</script>
	 --%>
<script type="text/javascript">
//main
window.onload=() => {
	let a=10;
	let b=20;
	let p1=plus1(a,b)
	let p2=plus2(a,b)
	let p3=plus3(a,b)
	let p4=plus4(a,b)
	
	document.write("p1="+p1+"<br>")
	document.write("p2="+p2+"<br>")
	document.write("p3="+p3+"<br>")
	document.write("p4="+p4+"<br>")
}

	function plus1(a,b)
	{
	return a+b
	}
	let plus2=function(a,b){
		return a+b
	}
	let plus3=(a,b)=>
	{
		return a+b
	}
	//가독성
	let plus4=(a,b)=>a+b
	
	
</script>
</head>
<body>

</body>
</html>