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

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
/*
 * 이벤트
 				=> 함수 호출
 				------------
 				elt btn = document.querySelector('#id')
 				btn.onclick=()=>{}
 				-----$('#id').click(()=>{})
 				=> 고전 이벤트 처리
 				=> 이벤트 리스너
 				elt btn = document.querySelector('#id')
 				btn.addEventListener('click',()=>{})
				----------$('#id').on('click',()=>{}) 				
 */

$(()=>{
	$('#count').on('change',function(){
			let count=$(this).val()
			let price=$('#price').text()
			price=price.replace(',','');
			price=price.replace('원','');
			
			let total=Number(count)*Number(price)
			
			$('#total').text(total)
	})
	
	/*
		글자 조작
		---------
		val() / text() / html() / attr() / append()
		-----   ------   ------   ------
															태그 속성을 활용
										 태그와 태그 사이 태그들 가져온다
						태그와 태그사이 문자를 이용
		value값을 채우는 태그
		<input> <select>
		<textarea>
	*/
})



</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<span id="price">30,000원</span>
			<select class="input-sm" id="count">
				<option value='1'>1개</option>
				<option value='2'>2개</option>
				<option value='3'>3개</option>
				<option value='4'>4개</option>
				<option value='5'>5개</option>
			</select>
			<br>
			총금액 : <span id="total"></span>
		</div>
	</div>
</body>
</html>