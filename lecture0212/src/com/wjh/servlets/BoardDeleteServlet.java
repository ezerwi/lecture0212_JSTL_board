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

@WebServlet("/board/delete")
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDeleteServlet() {
        super();
    }

    private DAO dao = null;
    private Model m = null;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dao = new DAO();
		m = new Model();
		m.set_idx(Integer.parseInt(request.getParameter("idx")));
		
		RequestDispatcher rd = request.getRequestDispatcher("/board/delete_check.jsp");
		request.setAttribute("m", m);
    	rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dao = new DAO();
		
		m = new Model();
		m.set_idx(Integer.parseInt(request.getParameter("idx")));
		boolean status = dao.delete_one(m);
		
		if(status==true) response.sendRedirect("list");
		else response.sendRedirect("error.jsp");
	}
}
