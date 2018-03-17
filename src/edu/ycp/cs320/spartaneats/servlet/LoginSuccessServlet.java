package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




public class LoginSuccessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Success Servlet: doGet");
		
		
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    
	    
		req.getRequestDispatcher("/_view/loginsuccess.jsp").forward(req, resp);
	}
	
	
}
