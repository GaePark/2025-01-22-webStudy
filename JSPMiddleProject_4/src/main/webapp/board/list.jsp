<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.model.*" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%
	
%>
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
	width: 700px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<h3 class="text-center">MVC를 이용한 게시판</h3>
			<table class="table">
				<tr>
					<td><a href="insert.do" class="btn btn-sm btn-primary">새글</a></td>
				</tr>
			</table>
			<table class="table">
				<tr>
					<th width=10% class="text-center">번호</th>
					<th width=45% class="text-center">제목</th>
					<th width=15% class="text-center">이름</th>
					<th width=20% class="text-center">작성일</th>
					<th width=10% class="text-center">조회수</th>
				</tr>
				<c:forEach var="vo" items="${list }" >
					<tr>
						<th width=10% class="text-center">${vo.no }</th>
						<th width=45% >${vo.subject }</th>
						<th width=15% class="text-center">${vo.name }</th>
						<th width=20% class="text-center">${vo.regdate }</th>
						<th width=10% class="text-center">${vo.hit }</th>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>