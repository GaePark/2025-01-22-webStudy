<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	자바스크립트는 브라우저에서 실행 => 인터프리터 => 에러출력(X)
		=> 개발자 도구
	1. 변수 => 대입값에 따라 데이터형이 벼경
			let (지역변수) / const (상수)
			=> number : 숫자형 (int, double)
			=> string : 문자형 (char,string)
			=> object : 배열 / 객체
									[]      {} => JSON
									
			=> function : 함수
	2. 연산자
			=> 단항연산자
					1) 증감연산자 ++,--
					2) 형변환연산자
						 숫자 변환 : Number("10") , parseInt("10")
						 문자 변환 : string(10) => "10"
						 논리 변환 : Boolean(1) > true
						 						 Boolean(0) > false
						 						 0,0.0이 아닌수는 true
						 						 null이 아닌 경우 true
						 => HTML 태그에서 읽는 값 => String
								-------------------------------
			=> 이항 연산자
				 산술 연산자 : + , /
				 							 -- --
				 							 		0으로 나누면 infinity , 정수/정수=실수
				 							 문자열 결합 가능 / 덧셈
				 비교연산자 : true
				 							문자 / 숫자 / 날짜 => 비교가능
				 							===, !== , < , > , <= , >=
				 논리연산자 : &&(범위 포함) , ||(포함 안된 범위)							
				 대입연산자 : = . += , -=
			=> 삼항연산자 : (조건) ? 값1 : 값2
														true=> 값1
														false=> 값2
			
			=> 문자열 결합 / 상품 갯수 => 총합(오라클)
			=> 모든 처리 => 자바(서버)
			=> 자바스크립트/HTML => 출력
											---- 데이터 변경이 어렵다 (정적페이지)
				 ----------- 데이터 변경이 가능 (동적페이지)
	3. 제어문
		 조건문 : if
		 		단일조건문
		 		if(조건문)
		 		{
		 		}
		 		선택조건문
		 		
		 반복문 : for / for-each
		 		=> 일반 for
		 반복제어문 : break
	
	4. 함수 : 기능처리 (이벤트 : 사용자가 행위를 했을때 => 키보드 , 마우스 => 브라우저에서만 작동)
																	onclick / onmouseover
																	onchange(select) / onmouseout
																	onkeydown / onkeyup ....
					=> 함수 생성 방법
						 선언적 함수
						 => 리턴형 서술이 없다
						 => 매개변수에 변수명 설정 (name) => (let name)(x)
						 function 함수명(매개변수)
						 {
						 		fucntion 함수명()=> X
						 }
						 익명의 함수 (*****)
						 let 함수명 = function(){}
						 let 함수명 = () => {}
						 
						 => 사용자 정의 함수 => 자동 호출이 안된다
						 => 언제 호출할 것인지
						 
						 -------------------------------------------
						 					리턴형       매개변수
						 -------------------------------------------
						 						O							O
						 -------------------------------------------
						 						O							X
						 -------------------------------------------
						 						X							O
						 -------------------------------------------
						 						X							X
						 -------------------------------------------
						 권장 : => (화살표함수 이용)
						 			 --- function / return 제거 : 람다식(함수 포인터)
		5. 배열 / 객체
							---- {}(JSON) ==> 서버에서 값 전송
									 => 자바 : VO
									 => 자바 : List
									 
			 ----[] => 자바 : List
			 	   --- 값 추가 : push() , 자르기 : slice()
			 	   --- 값 제거 : pop()
		6. 객체 문서 모델
				=> HTML태그를 선택해서 제어
					 --------------
					 이벤트 처리
					 속성값 변경
					 CSS 적용
					 
					 *** HTML파일 => 전체를 가지고 있는 객체 : document
					 태그 1개
					 		= document.getElementById(아이디명)
					 		= document.qeurySelector(CSS의 Selector)
					 														 화면 디자인
					 														 태그 선택
					 														 크롤링
					 태그 여러개
					 		= document.getElementByClassName(클래스명)
					 		= document.getElementByTagName(태그명)
					 		= document.querySelectorAll(클래스명, 태그명)
					 		
					 => Jquery
					 		$('태그,아이디,클래스명')
					 => Vue => <a ref="id">
					 		$refs.id
					 => React => target.id
					 		Next => target.id
					 		
		7. 이벤트 등록
		8. 내장 객체
			 내장 객체
			 브라우저 내장 객체
			 ------------------ 라이브러리화 : Jquery
 --%>    						
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	//시작점 => 자동 호출 => main
	/*
		Jquery : $(function(){})
		Vue    : mounted(){}
		React  : useEffect(()=>{}) => hooks => 16버전 => 17버전 ~ 19버전
	*/
	window.onload=()=>{
	//id
	
/* 	let h1=document.getElementById("h1");
	let h2 =document.querySelector("h2");
	
	h1.style.backgroundColor="yellow"; */
/* 	let h1=document.querySelector("h1")
	h1.style.backgroundColor='pink' */
/* 	let h1=document.querySelectorAll("h1")
	h1[0].style.backgroundColor='yellow'
	h1[1].style.backgroundColor='pink' */
	let h2 = document.querySelector('h2');
	h2.style.color="red";
	}
</script>
</head>
<body>
	<h1>Hello JavaScript-1</h1>
	<h2>Hello JavaScript-2</h2>
	<h1 id="h1">Hello JavaScript-3</h1>

</body>
</html>