<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		331page 객체
		=> 여러개의 데이터를 묶어서 사용
		=> 배열 , 객체
				[]     {} => JSON
				=> 배열 (Array)  : 혼합이 가능
				=> let names["","","","",""];
				=> let sawon=[1,"이름",90,90,90,180,90.0]
				=> 지원하는 함수
						1. 추가 : sawon[]0]=2
											push()
						2. 삭제 : pop()
						3. 갯수 : length
						4. 자르기 : slice() => arraycopy
						5. 찾기 : find
						6. 완전 삭제 : delete
					
 --%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=() => {
		let names=["홍길동","심청이","박문수"]
		console.log(names);
		console.log("인원:"+names.length);
		//데이터 추가
		names.push("이순신");
		console.log(names);
		console.log("인원:"+names.length);
		//데이터 삭제
/* 		names.pop()
		console.log(names);
		console.log("인원:"+names.length);
		
		// 전체 삭제
			delete names[0];
		console.log(names);
		console.log("인원:"+names.length);
		names.shift()
		console.log(names);
		console.log("인원:"+names.length); */
		
		console.log(names.slice(2))
		console.log(names.slice(1,2))
		
		
	}
	
</script>
</head>
<body>

</body>
</html>