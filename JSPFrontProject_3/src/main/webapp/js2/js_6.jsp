<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=()=>{
		//등록
		let img = document.querySelector("img");
		img.onmouseover=()=>{
		img.style.opacity=0.3
		}
		img.onmouseout=()=>{
		img.style.opacity=1
		}
		
		let btn=document.querySelector("button");
		btn.onclick=()=>{
			alert("버튼 클릭")
		}
		
		
	}
</script>
</head>
<body>
	<h1>고전 이벤트 처리</h1>
	<img src="m1.jpg" width="200px" height="250px"/>
	<p>
	<button>클릭</button>
</body>
</html>