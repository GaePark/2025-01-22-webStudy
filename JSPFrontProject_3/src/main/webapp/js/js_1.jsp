<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트  : 브라우저에서 화면 출력
									정적 => HTML/CSS
									동적 => JavaScript
													---------- 서버 요청 / 서버 응답 => 동시에 수행
																		=> Ajax / axios
	=> 1. 데이터형
				자동 데이터형 변경
				------------------
				let / const
				=> NUMBER(숫자형)
				=> STRING(문자형) => '',""
														--- 여러 글자
				=> object => 배열, 객체
											[]		{}=> JSON
				=> function => 매개변수로 사용 가능
										   -------- callback
										   map(function(){})
				=> null , NaN , infinity
				=> typeof 데이터형 확인 가능
		 2. 연산자
		 			+ : 문자열 결합 / 덧셈
		 			/ : 0으로 나누는 경우 : infinity
		 					정수/정수=실수
		 			비교연산자
		 					=> 5==='5' => false
		 					=> 5=='5' => true
		 			형변환 연산자
		 			-------------
		 			 정수변환 : Number('10') , parseInt('10')
		 			 논리변환 : Boolean(1) , Boolean(0)
		 			 						=> 0,0.0이 아닌 수는 모두 true
		 			 						=> null이 아닌 값 true
		 			 문자변환 : String(10)
		 			 *** HTML에서 값을 가지고 오는 경우
		 			 		 => 모든 데이터형 String이다
		 			 
		 3. 제어문
		 		조건문
		 				단일 조건문
		 					if(조건문)
		 					{
		 						조건이 true일 경우 처리
		 					}
		 				선택 조건문
		 					if(조건문)
		 					{
		 						조건 true
		 					}
		 					else
		 					{
		 						조건 false
		 					}
		 				다중 조건문
		 					if(조건문)
		 					{
		 						조건  true
		 					}
		 					else if(조건문)
		 					{
		 						위 조건이 false이고 현조건 true
		 					}
		 					else
		 					{
		 						모든 조건 false
		 					}
		 			반목문 : for => while(채팅)
		 					일반 for
		 4. 함수
		 		$('#btn').click(function(){
		 				버튼 클릭시 처리 => 고전적 
		 		})
		 		선언적 함수
		 		** 리턴형 선언
		 		** 매개변수의 데이터형을 사용하지 않는다
		 		=> 국어,영어,수학 점수를 받는다
		 			function gesan(kor,eng,math)
		 										 ============= 변수명만 사용
		 		function 함수명(매개변수) => 선언부
		 		{
		 				구현부
		 		}
		 		익명의 함수
		 		let 함수명=function(매개변수){}
		 		let 함수명(매개변수) => {}
		 		
		 		class A
		 		{
		 			constructor(msg)
		 			{
		 					this.msg=msg
		 			}
		 			test:function(){
		 				axios.get('URL',{데이터전송})
		 				.then((response)=>{
		 						응답을 받아서 처리
		 						this.msg=response.data.msg(x)
		 				})
		 			}
		 		}
		 		
		 		=> 사용처는 이벤트 처리
		 			onclick
		 			onmouseover / onmouseout
		 			onkeyup / onkeydown
		 			=> 사용자 정의 함수를 주로 이용
		 5. 태그 선택 (객체 모델)
		 6. 객체 (배열 / JSON)
		 7. 이벤트 처리
		 8. 내장 객체 (브라우저 객체 , 일반 객체)
		 															 --------- String / Date / Number
	=> Jquery 이용해서 처리
	   ------ 자바스크립트 기반
	   => JSP / Spring / Spring-Boot => 자바
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	let login=() =>{
		
	}
	window.onload=() =>{
		document.write("login="+typeof login)
	}
	
	
</script>
</head>
<body>

</body>
</html>