<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
	ES6 => 변경 (연산자)
				 비교연산자
				 ========== VuewJS / ReactJS
				 같다 : ==(경고)  => === 
				 같지않다 : !=(경고) => !==
				 => true / false => 조건문에서 주로 사용
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload=function(){
		let a="HELLO";
		let b="JavaScript";
		
		console.log("a===b"+(a===b))
		console.log("a!==b"+(a!==b))
		console.log("a<b"+(a<b))
		console.log("a>b"+(a>b))
		console.log("a<=b"+(a<=b))
		console.log("a>=b"+(a>=b))
	}
const send = () => {
	let frm=document.frm
	/*
			window
				ㅣ
			document
			 ㅣ
			form
	*/
	if(frm.id.value==="")
		{
			alert("아이디를 입력하세요");
			frm.id.focus();
			return;
		}
	if(frm.pwd.value==="")
		{
			alert("비밀번호를 입력하세요");
			frm.pwd.focus();
			return;
		}
	alert("ID:"+frm.id.value+"\n"+"password:"+frm.pwd.value)
}
</script>
</head>
<body>
	<form name="frm">
		ID:<input type=text name=id size=10><br>
		PWD: <input type=password name=pwd size=10><br>
		<input type=button value="전송" onclick="send()">
		</form>
</body>
</html>