package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import edu.ycp.cs320.spartaneats.controller.OrderController;


import edu.ycp.cs320.spartaneats.controller.OrderController;
import edu.ycp.cs320.spartaneats.model.Account;
//import edu.ycp.cs320.spartaneats.model.AccountControllerPopulate;
import edu.ycp.cs320.spartaneats.model.Condiments;
import edu.ycp.cs320.spartaneats.model.CreateOrderModel;
import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;
import edu.ycp.cs320.spartaneats.model.OrderItem;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;


public class OrderCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Create Order Servlet: doGet");
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        Order order;
        
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
	    
	    try {
			order = db.findOrderFromOrderId((int)session.getAttribute("order_id"));
			int time = 10;
		    if (order.getDelivery() == true) {
		    	time += 5;
		    }
			List<Order> activeOrders = db.findActiveOrders();
			time += activeOrders.size();
			req.setAttribute("order", order);
			req.setAttribute("time", time);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    
	    
	    req.getRequestDispatcher("/_view/ordercomplete.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Create Order Servlet: doPost");
		HttpSession session = req.getSession(false); 

			req.getRequestDispatcher("/_view/ordercomplete.jsp").forward(req, resp);
		
	}
}
