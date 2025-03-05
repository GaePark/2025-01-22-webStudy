<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
window.onload=() => {
	let h1=document.querySelector("#h1")
	
	let green=document.querySelector("#green")
	let blue=document.querySelector("#blue")
	let red=document.querySelector("#red")
	
	green.addEventListener('click',() => {
	h1.style.color="green"
	})
	blue.addEventListener('click',() => {
	h1.style.color="blue"
	})
	red.addEventListener('click',() => {
	h1.style.color="red"
	})
	
}
</script>
</head>
<body>
	<h1 id="h1" >제목</h1>
	<button id="green">Green</button>
	<button id="blue">Blue</button>
	<button id="red">Red</button>
</body>
</html>