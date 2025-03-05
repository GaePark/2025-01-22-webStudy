<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script>
	$(()=>{
		let img =$('img').attr('src')
		alert(img)
		$('img').attr('src','m2.jpg')
		
		$('img').attr({
			src:"m2.jpg",
			width: 500,
			height: 600
		})
	})
</script>
</head>
<body>
	<img src="m1.jpg" width="300px" height="350px" >
</body>
</html>