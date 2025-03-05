<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(function(){
		/*
				$()
				 -- selector(태그 선택자)
				 -- 브라우저 객체
				 		----- 취치
				$(this)
					------ 자신의 객체
		*/
		$('img').attr({
			width:250,
			height:300
		})
		
		$('img').hover(function(){
			$(this).css('cursor','pointer')
			$(this).css('opacity',0.2)
		},function() {
			$(this).css('opacity',1.0)
		})
		
		})
		
</script>
</head>
<body>
	<img alt="" src="m1.jpg">
	<img alt="" src="m2.jpg">
</body>
</html>