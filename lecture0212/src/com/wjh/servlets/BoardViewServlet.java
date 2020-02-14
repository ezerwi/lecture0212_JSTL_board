package com.wjh.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wjh.dao.board.DAO;
import com.wjh.model.Model;

@WebServlet("/board/view")
public class BoardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardViewServlet() {
        super();
    }
    
    private DAO dao = null;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String pageNum = request.getParameter("pageNum");
		String searchType = request.getParameter("searchType");
		String searchText = request.getParameter("searchText");
		
		this.dao = new DAO();
		
		Model m = new Model();
		m.set_idx(idx);
		m.set_pageNum(pageNum);
		m.set_searchType(searchType);
		m.set_searchText(searchText);
		
		this.dao.hit_up(m);
		
		request.setAttribute("m", this.dao.select_one(m));
//		System.out.println("__servlet_getHit()__"+m.get_hit());
		
		RequestDispatcher rd = request.getRequestDispatcher("boardView.jsp?"); 
		rd.forward(request, response);
		
	}

}
