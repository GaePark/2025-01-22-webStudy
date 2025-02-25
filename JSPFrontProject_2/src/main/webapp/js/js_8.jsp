<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	논리연산자
	&&,||
	--직렬연산자
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=function(){
	let i=(6<7) && (6===7)
	
	let k=10
	k+=10;
	console.log("k="+k)
	k-=10
	console.log("k="k)
	let n=10/0
	console.log("n="+n)
	
	let g=(6%2===0)?"짝수":"홀수"
	console.log("g="+g)
	}
</script>
</head>
<body>

</body>
</html>