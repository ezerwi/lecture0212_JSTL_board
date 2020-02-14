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

@WebServlet("/board/write")
public class BoardWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardWriteServlet() {
        super();
    }

    private DAO dao = null;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	RequestDispatcher rd = request.getRequestDispatcher("/board/boardWrite.jsp");
    	rd.forward(request, response);
    	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		
		dao = new DAO();
		
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String contents = request.getParameter("contents");
		String ip = request.getRemoteAddr();
		
		Model m = new Model();
		m.set_subject(subject);
			System.out.println(m.get_subject());
		m.set_writer(writer);
		m.set_contents(contents);
		m.set_ip(ip);
		
		boolean status = dao.insert(m);
		
		if(status==true) response.sendRedirect("list");
		else response.sendRedirect("error.jsp");
				
	}

}
