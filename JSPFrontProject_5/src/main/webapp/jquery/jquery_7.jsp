<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
	$(() => {
// 같은 태그 여러개 있는 경우에 인덱스로 이용 => 0번부터
		$('span:eq(0)').text('HELLO JQUERY');
		$('span:eq(1)').html("<font color=red>HELLO JQUERY</font>")
		$('span:eq(0)').append("<font color=blue>HELLO JQUERY</font>")
	})

</script>
</head>
<body>

<span>
</span> <br>
<span>

</span>
</body>
</html>