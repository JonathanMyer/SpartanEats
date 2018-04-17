package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Order;




public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("CreateOrder Servlet: doGet");
		
		
		HttpSession session = req.getSession(false);    // fetch the session and handle 
		
		Inventory inventory = new Inventory();
        
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    // create an order if one doesn't already exist
	    if (session.getAttribute("order") == null) {
	    	Order order = new Order(false, 1);
	    	session.setAttribute("order", order);
	    }
	    
	    session.setAttribute("inventory", inventory);
	    
		
	   
	    
		req.getRequestDispatcher("/_view/createorder.jsp").forward(req, resp);
	}
	
	
}
