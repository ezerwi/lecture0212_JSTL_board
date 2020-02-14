package com.wjh.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjh.dao.board.DAO;
import com.wjh.model.Model;
import com.wjh.util.PageNavigator;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardListServlet() {
        super();
    }
    
    private DAO dao = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNum = request.getParameter("pageNum");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		
		if(pageNum == null) {
			pageNum = "1";
		}
		
		if(searchType == null) {
			searchType = "";
			searchText = "";
		}
		
		Model m = new Model();
		m.set_pageNum(pageNum);
		m.set_searchText(searchText);
		m.set_searchType(searchType);
		
//		System.out.println("_servlet_pageNum: " + pageNum+"___");
//		System.out.println("_servlet_searchText:" + searchText + "___");
//		System.out.println("_servlet_searchType:" + searchType + "___");

		// DB table 접속해 쿼리 method 보유한 객체 생성
		this.dao = new DAO();
		
		
		// 게시물 총 수 
		int count = this.dao.select_count(m);
//		System.out.println("totalCount : " + totalCount);
		request.setAttribute("count", count);
		
		// 게시물 목록
		List<Model> list = this.dao.select_list(m);
//		System.out.println("_servlet_list_size : " + list.size());
		
		// 목록 하단 페이지 번호출력을 위한 객체
		PageNavigator pNavigator = new PageNavigator();
		
		String p_navi = pNavigator.getPageNavigator(count, m.get_list_count(), m.get_page_per_block(), Integer.parseInt(pageNum), searchType, searchText);
		request.setAttribute("p_navi", p_navi);
		
		request.setAttribute("list", list);
		request.setAttribute("m", m);
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardList.jsp");

		rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}


}
