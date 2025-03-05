<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	Jqeury / Vue (Vuex) / React (React-Query , Redux)
			=> 문서 객체 (태그를 제어) => 태그를 선택후에 처리
			=> 태그에 대한 이벤트 처리
			=> DOMScript
			=> 단점 : 데이터가 없다 (데이터 => 서버)
																				 ---- 오라클 연결
			=> 출력 (MVC => View)
			=> 출력 속도가 빠르다
																				 
	1. CSS 선택자
			$('선택자') , Vue => <button ref="">
									 React => e.target
				 선택자
				 -----
				 	1) 태그 => $('태그명').함수()
				 												 ------ 값 설정
				 												 val("")
				 												 text("") / html("") / attr("") / append("")
				 												 ----- 값 읽기
				 												 val() / text() / html() / attr()
				 												 ----- 이벤트
				 												 			 ------ 모든 태그에 이벤트 가능
				 												 $('선택자').on('click',()=>{})
				 												 $('선택자').click(()=>{})
				 	2) ID (속성값) => $('#아이디명')
				 	3) CLASS(속성값) => $('.클래스명')
				 	4) 자손 => $('태그명 > 자손명')
				 		 후손 => $('태그명 자손명')
				 	5) 속성선택자 => 
				 								$('태그명[속성=값']' )=> a,img,input
				 													=>equals
				 								$('태그명[속성$=값']' ) => endsWith
				 								$('태그명[속성^=값']' ) => startsWith
				 								$('태그명[속성*=값']' ) => contains
				 								xpath => div[0]/div/......(셀레니움)
				 								------------------------------------ 파이썬
				 	6) 가상 선택자 =>
				 								 nth-child => 1 / eq => 0
				 								 $('태그명:nth-child(odd)')
				 								 $('태그명:nth-child(even)')
				 								 $('태그명:nth-child(2n+1)')
				 								 $('태그명:eq(0)')
				 				$('') => 내장 객체 , 태그 선택자
				 								 
	2. 자바스크립트 기본
			JSP / Spring / Spring-Boot : Java
			Django : python , Asp.net : C#
			NextJS / ReactJS / VueJS / Jquery => JavaScript
			
			변수
				let : 지역변수 , const : 상수
				=> 일반변수      => function / 배열처리, 객체처리
			데이터형
				number : 숫자 (정수 , 실수)
				string : 문자 , 문자열
				boolean : true / false
									숫자 : 0,0.0외에는 true
									문자 : null이 아닌 경우 true
				Object
						=> 배열[] 	=> List외 호환
						=> 객체 {} 	=> VO와 호환
				undefined: 데이터값이 없는 경우
				function : 데이터형으로 취급
									 => 매개변수로 사용이 가능
									 => 이벤트 / react-query / redux
									 map(()=>{})
									 forEach(()=>{})
									 
				연산자
					산술연산자 => / (정수/정수 = 실수)
					비교연산자 => 
											==
											=== (엄격비교)
					형변환 => Number, ParseInt => 문자열을 정수형으로 변경
										=> 데이터 읽기 : 문자열로 읽어 온다(HTML)
										문자열 변환 => String(10)=>"10"
										논리형변경 => Boolean(1) => true
																  Boolean(0) => false
																  Boolean('aaa')=> true
																  Boolean(null) => false
				제어문
					if / if~else
					for / for-each
					=> 출력
					일반 for
					for(let i in 배열)=> i는 인덱스번호
					for (let key in 객체)=> key는 key값
					***배열.map((배열값)=>{})
					배열.forEach((배열값)=>{})
					
				함수 : 필수 (요청 처리)
							function func_name(매개변수){}
										=> return형을 사용하지 않는다, 매개변수는 변수명
							
							
							1. 자바스크립트의 main
								 window.onload=()=>{}
							2. Jquery
								 $(function(){})
								 $((document).ready(function(){})
							3. vue
								 mounted:fucntion(){}
							4. react
									useEffect(()=>{})
				내장 함수 : String
												subwtring() / substr() => 문자열 자르기
												indexOf / lastIndexOf => 문자 위치 찾기
												length() : 문자 개수
												split() : 문자 분리
												trim() : 좌우 공백제거
												replace()
				 						Array
				 								push : 배열 데이터 추가
				 								pop() : 배열 데이터 제거
				 								slice() : 데이터를 잘라서 새로운 배열
				 								... : scope연산자 => 배열 전체 복사
				 						Number
				 							=> 형변환
				 							=> NaN : 연산이 안된 경우
				 						Date
				 							=> getFullYear()
				 							=> getMonth() => 0번 시작
				 							=> getDate() => 일
				 							=> getDay() => 요일
				 						Math
				 							=> ceil() : 올림
				 							=> reound() : 반올림
				 							
				브라우저 : window : open,close,scrollbar조절
									 document : 문서 관리(태그)
									 location  : 이동 => href
									 history : back() => 뒤로가기
	3. 서버연결 => 데이터 읽기 => Ajax => 직접 제작
	----------------------------------    ---------- ajax
			=> 1) 로그인 처리 / 회원가입 (아이디 찾기,비밀번호 찾기)
													아이디 중복 체크
			=> 2) 검색
			=> 3) 추천
			=> 4) 예약
			=> 입력된 데이터를 유지 => Ajax
				 jsp => 서버 => jsp초기화 (새로운 jsp생성)
				 login.jsp => 서버 => login.jsp
				 ---------            ---------
				    ㅣ                    ㅣ
				     ----------------------
				     	메모리 주소가 다르다
			
			=> 5) 채팅
			
			Jquery => Ajax, axios
			$.ajax({
					url: ''
					data: {}
					sucess:{데이터 받기}
			})
			axios.get("URL",{parms})
			<a> , <form> , location.href
			=> client => server : URL
			=> 클라이언트에서만 작업
			Jquery
				=> 값읽기 / 값쓰기
						val()   text()
				=> 이벤트처리 => 언제 데이터 읽기 / 언제 데이터 쓰기
				
				1. <input> <select> <textarea>
				2.  checkbox
				3. 목록 제어
				4. 스크롤 제어
				4-1. 약간의 효과
				4-2 show / hide
				4-3. ui => 사용법
						=> 완성 : codepen.io : 템플릿의 구조가 깨진다
				5. 서버 연결
			
 --%>    
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
<script>
// 이벤트 리스너 이용
// 고전 이벤트
// $('#login').click(function(){})
/*
 *  change : 선택이 변경 => <select>
 		mousedown / mouseup
 		hover
 		keyup / keydown
 		
 		=> $().함수()
 					 --
 					 1. 디자인 변경 : css()
 					 2. 속성 변경 : attr()
 					 3. 값을 읽기 : val(), text() , html()
 					 4. 값을 저장 : val(""),text(""),html("")
 					 								여러개 출력: append()
 */
	$(() => {
		$('#login').on('click',()=>{
			//$('form').submit() => 데이터 전송
			//=> let formData=new FormData()
			let id = $('#id').val()
			let pwd = $('#pwd').val()
			if(id.trim()==="") {
				$('#print').text('아이디를 입력해주세요')
				$('#print').css('color','red')
				$('#id').focus()
				return
			}
			else if(pwd.trim()===""){
				$('#print').text('비밀번호를 입력해주세요')
				$('#print').css('color','red')
				$('#pwd').focus()
				return
			}
			else
				{
				$('print').text("")
				alert('로그인 성공')

				}
			
		})
		
		$('#find').on('click',()=>{
		let fd=$('#keyword').val();
			if(fd.trim()==="")
			{
				$('#keyword').focus()
				return
			}
			alert("검색어:" + fd)
			$('#keyword').val("")
		})
		$('#keyword').on('keydown',(e)=>{
			if(e.keyCode===13)
			{
				let fd=$('#keyword').val();
				if(fd.trim()==="")
				{
					$('#keyword').focus()
					return
				}
				alert("검색어:" + fd)
				$('#keyword').val("")
			}
		})
	})
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="text-center">Login(val()/text())</h3>
			<table class="table">
				<tr>
					<th class="text-center" width="20%">ID</th>
					<td width=80%>
						<input type="text" id="id" size=15 class="input-sm">
					</td>
				</tr>
				<tr>
					<th class="text-center" width="20%">PWD</th>
					<td width=80%>
						<input type="password" id="pwd" size=15 class="input-sm">
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text-center">
						<input type=button value="로그인" class="btn-sm btn-success" id="login">
				</tr>
				<tr>
					<td colspan="2">
						<span id="print"></span>
					</td>
				</tr>
			</table>
			<h3 class=text-center>Key이벤트</h3>
			<input type=text id="keyword" size=20>
			<input type=button id="find" value="검색" class="btn-sm btn-danger" >
			
		</div>
	</div>
</body>
</html>