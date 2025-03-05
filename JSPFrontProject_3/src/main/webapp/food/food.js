let food_list=[];
let startPage=0;
let endPage=0;
let totalpage=0;
let curpage=1;
window.onload=()=>{
	let input = document.getElementById("fd")
	input.value='마포'
	dataRecv("마포",curpage);
}

const foodFind = () => {
	let fd = document.getElementById("fd").value
	if(fd===null)
		{
			alert("검색어를 입력하세요")
			document.querySelector("#fd").focus();
			return
		}
	dataRecv(fd,1);
	
}
const pageChange = (page)=>{
	let fd = document.getElementById("fd").value
	dataRecv(fd,page);
	
}
const detail = (fno) => {
	let div=document.querySelector("#detail");
	div.style.display="";
	// 현재 배운것(ajax까지)web 2.0 =>(vue부터) web 3.0
	//back-end : MSA / DevOps
	axios.get('http://localhost/JSPFrontProject_3/food/detail_js.do',{
		params:{
			fno
		}
	})
	.then((response)=> {
		const vo = response.data;
		document.querySelector("#title").innerText=vo.name;
		document.querySelector("#score").textContent=vo.score;
		document.querySelector("#type").textContent=vo.type;
		document.querySelector("#address").textContent=vo.address;
		document.querySelector("#phone").textContent=vo.phone;
		document.querySelector("#price").textContent=vo.price;
		document.querySelector("#parking").textContent=vo.parking;
		document.querySelector("#time").textContent=vo.time;
		document.querySelector("#theme").textContent=vo.theme;
		document.querySelector("#content").textContent=vo.content;
		document.querySelector("#poster1").src=vo.poster;
		
	})
	
}

const dataRecv = (fd,page) => {
	let html='';
		axios.get('http://localhost/JSPFrontProject_3/food/find_js.do',{
			params:{
				page,
				fd
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
			
			let pages=document.querySelector("#pages")
			let pp= '<ul class="pagination">'
			if(startPage>1)
				pp+='<li><a onclick="pageChange('+(startPage-1)+')">&lt;</a></li>'
			for(let i=startPage;i<=endPage;i++)
				{
					let style=''
					if(i===curpage)
						{
							style='class=active'
						}
							pp+='<li '+style+'><a onclick="pageChange('+i+')">'+i+'</a></li>';
				}
				if(endPage<totalpage)
					pp+='<li><a onclick="pageChange('+(endPage+1)+')">&gt;</a></li>'
				pp+='</ul>'
				
				pages.innerHTML=pp;
			})
}