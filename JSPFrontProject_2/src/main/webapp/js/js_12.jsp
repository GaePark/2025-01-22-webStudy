<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		1. HTML태그 읽기
				document:문서저장 공간
				document.getElementById("#ID")
				document.getElementByTagName("태그")
				document.getElementByClassName("클래스명")
				***document.querySelector("CSS")
																	-------
																	ID => #아이디명
																	Class => .클래스명
																	태그명 => 태그명
																	선택자 => 이벤트처리가능
		2. let 변수명 =[]
					=> List list = new ArrayList()
			 let 변수명={} :객체
			 						--- JSON(*****)
			 		=> MovieVO
			 		사용법
			 		let sawon = {사번}
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
	margin:0 auto;
	width: 900px;
}
</style>
<script type="text/javascript">
	const sawons=[
		{sabun:1,name:"홍길동",dept:"개발부",job:"대리",pay:"4500"},
		{sabun:2,name:"심청이",dept:"개발부",job:"사원",pay:"3500"},
		{sabun:3,name:"김갑수",dept:"개발부",job:"사원",pay:"3500"},
		{sabun:4,name:"이순신",dept:"개발부",job:"부장",pay:"5500"},
		{sabun:5,name:"춘향이",dept:"개발부",job:"사원",pay:"3500"}
	];
window.onload=()=>{
	let html="";
	sawons.map((sawon)=>{
		html+='<li>'+(sawon.sabun+" "+sawon.name+" "+sawon.dept)+'</li>'
	})
	document.querySelector('ul').innerHTML=html
	}
</script>
</head>
<body>
	<ul></ul>
</body>
</html>