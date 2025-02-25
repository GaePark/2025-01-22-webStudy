<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	객체지향 프로그램 : {} => JSON
	================================JavaScript문법
	=> 라이브러리
		1) Jquery => Ajax : 1차
		2) VueJS => axios : JSON
		3) 서버 / 클라이언트 분리
		    ㅣ        ㅣvscode
		   이클립스
		   
		while / do~while / for
		// 반복회수가 없는 경우 => 자바스크립트에서는 사용빈도가 없다
		
		초기값 ---------1
		while(조건문)---2
		{
			반복수행문장--3
			증가식--------4
		}
		
		do~while : 반드시 한번이상 반복 수행 => 사용빈도는 없다
		초기값 ----1
		do
		{
			반복수행문장 --2
			증가식 --------3
		} while(조건식); --4
		
		for
		웹 프로그램 => 한눈에 모든 데이터가 보이게 만든다
									 ====> 이미지 : 12~ 15개
									 ====> 게시물 : 20
									 ====> 페이지 나누기
									 ====> 출력 회수는 지정
									 
		**** 조건문 / for => Jquery / Vue / React							 
		= 1. 일반 for
					for(초기값;조건식;증가식)
					{
							반복 수행문장 ===> 자바와 동일
					}
					for(let i=0;i<10;i++)
							------- let/var => const는 사용할 수 없다
																---------
																ㅣ상수 => 변경이 불가능
																ㅣ자바에서 데이터를 받는 경우
		= 2. for-each : 배열 / JSON
					= for in
						for(변수 in 배열)
								-------------
								배열의 인덱스번호 읽기 (0부터)
								=> 배열 여러개를 동시에 출력할 떄
				= for of
						for(변수 of 배열)
								--- 배열의 값을 1개씩 읽어올때
				= forEach
						배열.forEach(funtion(변수){})
															  ------배열의 값을 1개씩 읽어올때
															  
				*** = map
						배열.map(function(변수){})
															---- 배열의 값을 1개씩 읽어온다
						배열.map((변수)=>{})
										 			--- function을제거 (화살표함수 => 람다식)
		반복제어문
			break / continue
			자바 => 데이터 전송 => 전송된 데이터를 출려하는 용도
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=()=>{
	const names=["홍길동","심청이","박문수","이순신","춘향이"];
	const sexs=["남자","여자","남자","남자","여자"];
	
	document.write("<h3>일반 for</h3>")
	
	for(let i=0;i<names.length;i++)
		{
			document.write(names[i]+"<br>")
		}
	document.write("<hr>")
	document.write("<h3>for-in(인덱스)</h3>")
	for(let index in names)
		{
		document.write(names[index]+"("+sexs[index]+")"+"<br>")
		}
	document.write("<hr>")
	document.write("<h3>for-of(for-each)</h3>")
	// => 배열에서 데이터를 한개씩 읽어오는 for
	for(let name of names)
		{
	document.write(name+"<br>")
	}
	document.write("<hr>")
	document.write("<h3>forEach함수</h3>")
	names.forEach((name)=> {
		document.write(name+"<br>")
	})
	document.write("<hr>")
	document.write("<h3>Map</h3>")
	names.map((name) => {
	document.write(name+"<br>")
	})
	}
</script>
</head>
<body>

</body>
</html>