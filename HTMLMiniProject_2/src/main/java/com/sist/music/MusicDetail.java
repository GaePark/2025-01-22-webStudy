package com.sist.music;

import jakarta.servlet.ServletException; 
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.*;

import com.sist.dao.*;
import com.sist.vo.*;
import java.sql.*;
import java.util.List;

@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String mno=request.getParameter("mno");
		
		MusicDAO dao = MusicDAO.newInstance();
		MusicVO vo = dao.musicDetailData(Integer.parseInt(mno));
		
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("<link rel=stylesheet href=css/food.css>");
		out.println("<script src=js/update.js></script>");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<div class=row>");
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td style=\"width:30%\" rowspan=6><img src="+vo.getPoster()+" style=\"width:270px,height:300px\"/></td>");
		out.println("<td >노래</td>");
		out.println("<td >"+vo.getTitle()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10%>순위변동</td>");
		out.println("<td width=60%>"+vo.getState()+vo.getIdcrement()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10%>가수</td>");
		out.println("<td width=60%>"+vo.getSinger()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10%>앨범</td>");
		out.println("<td width=60%>"+vo.getAlbum()+"</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td width=10%>조회수</td>");
		out.println("<td width=60%>"+vo.getHit()+"</td>");
		out.println("</tr>");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		out.println("<tr>");
		out.println("<td class=text-right colspan=2>");
		if(id!=null)
		{
		out.println("<a href=# class=\"btn btn-xs btn-danger\">좋아요</a>");
		}
		out.println("<a href=MainServlet?mode=1&cno="+vo.getCno()+" class=\"btn btn-xs btn-primary\">목록</a>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<div style=\"margin:0 auto; width:700px;height:500px\">");
		//나중에는 키값을 받아서 뮤직비디오 영상 실행할 수 있또록하자
		out.println("<iframe style=\"width:100%; height:100%\" src=\"https://youtube.com/embed/EkoS0426BeQ\" ></iframe>");
		out.println("</div>");
		
		out.println("<div class=row style=\"margin-top:20px;\">");
		
		out.println("<div class=col-sm-8>");
		out.println("<h3>댓글</h3>");
		//댓글
		ReplyDAO rdao = ReplyDAO.newInstance();
		List<ReplyVO> list = rdao.replyListData(Integer.parseInt(mno));
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td>");
		for(ReplyVO rvo: list)
		{
			out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td class=text-left>");
		out.println("◐"+rvo.getName()+"&nbsp;(");
		out.println(rvo.getDbday()+")");
			out.println("</td>");
			out.println("<td class=text-right>");
			if(rvo.getId().equals(id)) {
				//<html> => 사용자 정의가 없다 속성은 사용자 정의가 가능
				out.println("<a class=\"btn btn-xs btn-success update\" data-rno="+rvo.getRno()+">수정</a>");
				out.println("<a href=ReplyInsert?mno="+mno+"&rno="+rvo.getRno()+" class=\"btn btn-xs btn-info\">삭제</a>");
			}
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td colspan=2>");
			out.println("<pre style=\"white-space:pre-wrap;background-color:white;border:none\">"+rvo.getMsg()+"</pre>");
			out.println("</td>");
			out.println("</tr>");
			out.println("<tr id=m"+rvo.getRno()+" class=ups style=\"display:none\">");
			out.println("<td>");
			
			out.println("<form method=post action=ReplyUpdate>");
			out.println("<textarea rows=4 cols=45 name=msg style=\"float:left;\" requird>"+rvo.getMsg()+"</textarea>");
			out.println("<input type=hidden name=mno value="+mno+">");
			out.println("<input type=hidden name=rno value="+rvo.getRno()+">");
			out.println("<input type=submit value=댓글수정 class=\"btn-primary\" style=\"resize:none;float:left;width:80px;height:96px\">");
			out.println("</form>");
			
			out.println("</td>");
			out.println("</tr>");
			out.println("</table>");
		}
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		if(id!=null) {
		
		out.println("<form method=post action=ReplyInsert>");
		out.println("<table class=table>");
		out.println("<tr>");
		out.println("<td>");
		out.println("<textarea rows=4 cols=60 name=msg style=\"float:left;\" requird></textarea>");
		out.println("<input type=hidden name=mno value="+mno+">");
		out.println("<input type=submit value=댓글쓰기 class=\"btn-primary\" style=\"resize:none;float:left;width:80px;height:96px\">");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("</form>");
		
		}
		out.println("</div>");
		out.println("<div class=col-sm-4>");
		out.println("<h3>인기맛집</h3>");
		List<MusicVO> mList = dao.musicTop10();
		out.println("<table class=\"table table-striped\">");
		out.println("<tr>");
		out.println("<th class=text-center></th>");
		out.println("<th class=text-center>업체명</th>");
		out.println("<th class=text-center>조회수</th>");
		out.println("</tr>");
		for(MusicVO fvo : mList) {
			out.println("<tr>");
			out.println("<td class=text-center><img src="+fvo.getPoster()+" width=30 height=30></td>");
			out.println("<td class=text-center>"+fvo.getTitle()+"</td>");
			out.println("<td class=text-center>"+fvo.getHit()+"</td>");
			out.println("</tr>");
		}
		out.println("</table>");

		
		out.println("</div>");
		
		out.println("</div>");
		out.println("</div>");
		out.println("</body");
		out.println("</html");
	}

}
