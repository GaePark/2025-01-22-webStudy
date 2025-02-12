<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*" %>
    <%--
    	제어문 사용
    	----------------------- if / for
     --%>
<%
	//1. 자바를 이용해서 => 출력할 데이터를 가지고 온다
	EmpDAO dao = EmpDAO.newInstance();
    List<EmpVO> list = dao.empListData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
<style type="text/css">
	.container{
	width: 900px;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>사원목록</h3>
		<table class="table_content">
		<tr>
			<th>사번</th>
			<th>이름</th>
			<th>직위</th>
			<th>입사일</th>
			<th>급여</th>
		</tr>
		<%
			for(EmpVO vo:list)
			{
		%>
			<tr>
				<td><%= vo.getEmpno() %></td>
				<td><%= vo.getEname() %></td>
				<td><%= vo.getJob() %></td>
				<td><%= vo.getHiredate().toString() %></td>
				<td><%= vo.getSal() %></td>
			</tr>
		<%
			}
		%>
		
		</table>
	</div>
</body>
</html>