package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Order;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;




public class SetDeliveryMethod extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Set Delivery Servlet: doGet");

		HttpSession session = req.getSession(false);    // fetch the session and handle 
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        }
		req.getRequestDispatcher("/_view/setdeliverymethod.jsp").forward(req, resp);
	}	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Set Delivery Servlet: doPost");
		HttpSession session = req.getSession(false);
		DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
		try {
	    	
			int order_id = db.createOrder((int) session.getAttribute("account_id"), req.getParameter("deliverypref"), req.getParameter("deliveryDest"));
			System.out.println("Order ID: " + order_id);
			System.out.println("DeilveryDest: " + req.getParameter("deliveryDest"));
			session.setAttribute("order_id", order_id);
	    
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.sendRedirect(req.getContextPath()+"/createorder");
	}
}
