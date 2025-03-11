<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="cockstyle.css"> 
</head>
<body>
 <section class="ftco-section bg-light" style="padding:3rem 0 0" id="section-offer">
      <div class="container">
        <div class="row">
							<c:forEach var="vo" items="${list }">
        	<div class="col-md-3 col-sm-6 mb-4 ftco-animate fadeInUp" >
              <div class="item" style="height:350px;">
              	<a href="../cocktail/cocktail_detail.do?cno=${vo.cocktail_no }">
                <div class="media d-block mb-3 text-center ftco-media ftco-animate border-0 hoef">
                  <img src="${vo.image }" style="width:100%; height:282px;" class="img-fluid">
									<div>
									<h4 style="font-size:24px;text-align:left;line-height:24px;margin:5px 0">${vo.name }</h4>
									<p style="white-space:nowrap;overflow:hidden;text-overflow:ellipsis;font-size:14px;text-align:left; padding-bottom:15px;">${vo.comments }</p>
									</div>
                </div>
              	</a>
              </div>
          </div>
							</c:forEach>
        </div>
      </div>
 </section>
 
    <section class="ftco-section bg-light" style="padding:0 0 3rem" id="section-offer">
      <div class="container">
        <div class="row justify-content-center">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                            <c:if test="${curpage>1 }">
                            	<li class="page-item">
                                    <a class="page-link" href="../cocktail/cocktail_list.do?page=${curpage-1 }"><i class="fa fa-angle-double-left" aria-hidden="true"></i> 이전</a>
                                </li>
                                </c:if>
																	<div class="page-status px-3 align-items-center">
                            				<p style="margin:0; line-height:35px">Page ${ curpage } of ${totalpage } results</p>
                        					</div>
																<c:if test="${curpage<totalpage }">
                                <li class="page-item">
                                    <a class="page-link" href="../cocktail/cocktail_list.do?page=${curpage+1 }">다음 <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                                </li>
                                </c:if>
                            </ul>
                        </nav>
                 
                    </div>
                </div>
        </div>
   	</section>
      
   
</body>
</html>