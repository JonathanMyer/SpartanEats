package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.spartaneats.controller.OrderController;
import edu.ycp.cs320.spartaneats.model.Order;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Admin Servlet: doGet");
		
		req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
	
		HttpSession session = req.getSession(true);    // fetch the session and handle 
        
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	    } 
		
	    req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);

	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Admin Servlet: doPost");
		HttpSession session = req.getSession(true); 

		// holds the error message text, if there is any
		String errorMessage = null;
		
		
		
	}
}

