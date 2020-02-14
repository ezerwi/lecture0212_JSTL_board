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

@WebServlet("/board/modify")
public class BoardModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardModifyServlet() {
        super();
    }
    
    private DAO dao = null;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		this.dao = new DAO();
		Model m = new Model();
		m.set_idx(Integer.parseInt(request.getParameter("idx")));
		m.set_pageNum(request.getParameter("pageNum"));
		m.set_searchType(request.getParameter("searchType"));
		m.set_searchText(request.getParameter("searchText"));
		
		m = this.dao.select_one(m);
		request.setAttribute("m", m);
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/boardModify.jsp");
		rd.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		dao = new DAO();
		
		Model m = new Model();
		m.set_idx(Integer.parseInt(request.getParameter("idx")));
		m.set_subject(request.getParameter("subject"));
			System.out.println(m.get_subject());
		m.set_writer(request.getParameter("writer"));
		m.set_contents(request.getParameter("contents"));
			System.out.println(m.get_contents());
		m.set_ip(request.getRemoteAddr());
		
		m.set_pageNum(request.getParameter("pageNum"));
		m.set_searchType(request.getParameter("searchType"));
		m.set_searchText(request.getParameter("searchText"));
		
		boolean status = dao.modify(m);
		
		RequestDispatcher rd = null;
		if(status==true) {
			rd = request.getRequestDispatcher("list");
			request.setAttribute("m", m);
			rd.forward(request, response);
		}
		else response.sendRedirect("error.jsp");
	}

}
