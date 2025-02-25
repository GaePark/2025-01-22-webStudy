<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
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
	width: 960px;
}
p{
overflow: hidden;
white-space: nowrap;
text-overflow: ellipsis;
}


img:hover{
cursor:pointer;
}
</style>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="text/javascript">
	let food_list=[];
	let startPage=0;
	let endPage=0;
	let totalpage=0;
	let curpage=0;
	
	window.onload=()=>{
	let html='';
	axios.get('http://localhost/JSPFrontProject_3/food/find_js.do')
	.then((response)=>{
		console.log(response.data);
		food_list=response.data;
		curpage=response.data[0].curpage;
		totalpage=response.data[0].totalpage
		startPage=response.data[0].startPage;
		endPage=response.data[0].endPage;
		
		console.log(curpage)
		console.log(totalpage)
		console.log(startPage)
		console.log(endPage)
		console.log(food_list)
		
		food_list.map((vo)=>{
			html+='<div class="col-sm-4">'
			     +'<div class="thumbnail">'
			     +'<img src="'+vo.poster+'" style="width:100%" onclick="detail('+vo.fno+')">'
			     +'<p>'+vo.name+'</p>'
			     +'</div>'
			     +'</div>'
		})
		let main=document.querySelector("#poster");
		//CSS selector
		main.innerHTML=html;
		
	})
	}
	const foodFind = () => {
		let addr=document.querySelector("#fd").value;
		if(addr.trim()==="")
			{
				alert("지역을 입력하세요")
				document.querySelector("#fd").focus();
				return
			}
		let html='';
		axios.get('http://localhost/JSPFrontProject_3/food/find_js.do',{
			params: {
				page:10,
				fd:addr
			}
		})
		.then((response)=>{
			console.log(response.data);
			food_list=response.data;
			curpage=response.data[0].curpage;
			totalpage=response.data[0].totalpage
			startPage=response.data[0].startPage;
			endPage=response.data[0].endPage;
			
			console.log(curpage)
			console.log(totalpage)
			console.log(startPage)
			console.log(endPage)
			console.log(food_list)
			
			food_list.map((vo)=>{
			html+='<div class="col-sm-4">'
			     +'<div class="thumbnail">'
			     +'<img src="'+vo.poster+'" style="width:100%" onclick="detail('+vo.fno+')">'
			     +'<p>'+vo.name+'</p>'
			     +'</div>'
			     +'</div>'
			})
			let main=document.querySelector("#poster");
			//CSS selector
			main.innerHTML=html;
			
		})
	}
const detail=(fno)=>{
	axios.get("http://localhost/JSPFrontProject_3/food/detail_js.do",{
		params:{
			fno:fno
		}
	})
	.then((response) => {
		console.log(response.data)
	})
}
</script>
</head>
<body>
<div class="container">
	<div class="row">
		<input type="text" id="fd" size=20 class="input-sm">
		<input type="button" id="btn" value="검색" class="btn-sm btn-success" onclick="foodFind()">
	</div>
	<div class="row">
		<div class="col-sm-8" id="poster">
			
		</div>
		<div class="col-sm-4" id="detail" style="display:none">
		</div>
	</div>
		<div class="row" style="margin-top: 10px">
			<div class="text-center" id="pages">
			</div>
		</div>
</div>
</body>
</html>