package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.spartaneats.controller.OrderController;
import edu.ycp.cs320.spartaneats.model.Order;

public class PreferencesServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Preferences Servlet: doGet");
		
		req.getRequestDispatcher("/_view/preferences.jsp").forward(req, resp);
	
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	    } 
		
	    req.getRequestDispatcher("/_view/preferences.jsp").forward(req, resp);

	
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Preferences Servlet: doPost");
		HttpSession session = req.getSession(false); 

		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		OrderController controller = new OrderController();
		
		Order order = new Order(null, 0, 0);
		
		
		
	}
}
