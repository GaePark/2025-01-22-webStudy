<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
	브라우저 내장객체
	------------------
								window (브라우저 외부관리) => 메뉴바 , 상태바...
									ㅣ => open / close
				----------------------------
			 ㅣ       ㅣ        ㅣ       ㅣ
		document   location  screan   history
		--------   --------  ------   -------
																		ㅣ사이트 이동 => 정보
													ㅣ위치,화면크기
		  ㅣ          ㅣ화면이동 => href
		 body/form.. (태그 관리
		 
		 => write / querySelector()
	내장 객체
	--------- String / Number / Date / Array
	          ------------------------------ Java
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
	width: 500px;
}

</style>
<script type="text/javascript">
	const ok=(zip,addr)=>{
		console.log(zip+" "+addr)
		opener.frm.post1.value=zip.cubstring(0,3);
		opener.frm.post2.value=zip.cubstring(4-7);
		opener.frm.addr1=addr
		self.close();
	}
</script>
</head>
<body>
	<div class="container">
		<h3 class="text-center">우편번호 검색</h3>
		<div class="row">
		<form method="POST" action="../js/postfind.do">
			<table class="table">
				<tr>
					<td>
						<input type=text name=dong class="input-sm" placeholder="동/읍/면 입력">
						<input type=submit class="btn btn-sm btn-danger" value="검색">
					</td>
				</tr>
			</table>
			<table class="table user-table">
			<thead>
				<tr>
					<th width=15%>
						우편번호
					</th>
					<td width=85%>
						주소
					</td>
				</tr>
				<tr>
				</thead>
				<tbody>
				<c:if test="${list!=null }">
				<c:forEach var="vo" items="${list }" >
				<tr>
				
					<td width=15% class="text-center">
						${vo.zipcode }
					</td>
					<td width=85%>
						<a onclick="ok(${vo.zipcode},${vo.address })">
						${vo.address }
						</a>
					</td>
				</tr>
					
				</c:forEach>
				</c:if>
				</tbody>
			</table>
			</form>
		</div>
	</div>
</body>
</html>