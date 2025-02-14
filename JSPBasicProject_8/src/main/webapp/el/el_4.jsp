<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.vo.*" %>
    <%@taglib prefix="c" uri="jakarta.tags.core" %>
<%
	SawonVO vo = new SawonVO();
	vo.setSabun(1);
	vo.setName("홍길동");
	vo.setDept("개발부");
	vo.setJob("대리");
	vo.setPay(3500);
	//${} => 출력할 수 없다 => <%=
	// Model
	request.setAttribute("vo", vo);
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
		width: 560px;
		margin: 0 auto;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
		<h3>사원정보</h3>
		사번:${vo.sabun } <br>
		이름:${vo.name } <br>
		부서:${vo.dept} <br>
		직위:${vo.job } <br>
		연봉:${vo.pay } <br>
		
			<h3 class="text-center">구구단</h3>
			<table class="table">
				<tr>
					<%
						for(int i=2;i<=9;i++)
						{
					%>
						<th class=text-center><%=i+"단" %></th>
					<%
						}
					%>
				</tr>
				<%
					for(int i=1;i<=9;i++)
					{
				%>
					<tr>
						<%
							for(int j=2;j<=9;j++)
							{
						%>
						<td class="text-center"><%=j+"*"+i+"="+(j*i) %></td>
						<%
							}
						%>
					</tr>
				<%
					}
				%>
			</table>
			<h3 class="text-center"구구단></h3>
						<table class="table">
				<tr>
				<c:forEach var="i" begin="2" end="9" step="1"><th class=text-center>${i+="단"}</th></c:forEach>
				</tr>
				<c:forEach var="i" begin="1" end="9" step="1">
					<tr>
									<c:forEach var="j" begin="2" end="9" step="1">
										<td class="text-center">${j }*${i }=${j*i }</td>
									</c:forEach>
					</tr>
				</c:forEach>
				</table>
		</div>
	</div>
</body>
</html>