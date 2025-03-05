<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0 auto;
	width: 350px;
}
/*
	id / class= 구분이 없는 겨우
	input / select / textarea => 속성선택자
*/

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let bCheck= true;
	$(() => {
		$('tr:last-child').hide();
		$('.btn-sm').on('click',function(){
			if(bCheck===true)
			{
				bCheck=false
				$(this).val('취소')
				$('tr:last-child').show("slow");
				} else{
					bCheck=true
				$(this).val('삭제')
				$('tr:last-child').hide("slow");
			}
		})
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="text-center">show/hide</h3>
			<table class="table">
				<tr>
					<td class="text-right">
						<input type=button value="삭제" class="btn-sm btn-danger">
					</td>
				</tr>
				<tr>
					<td>
						비밀번호: <input type=password size=20 class="input-sm">
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>