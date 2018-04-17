package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import edu.ycp.cs320.spartaneats.controller.OrderController;


import edu.ycp.cs320.spartaneats.controller.OrderController;
import edu.ycp.cs320.spartaneats.model.Account;
//import edu.ycp.cs320.spartaneats.model.AccountControllerPopulate;

import edu.ycp.cs320.spartaneats.model.CreateOrderModel;
import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;


public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Order Servlet: doGet");
		
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        Inventory inventory = new Inventory();
        Order order = new Order(false, 1);
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    session.setAttribute("inventory", inventory);
	    session.setAttribute("order", order);
	    req.getRequestDispatcher("/_view/vieworder.jsp").forward(req, resp);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Order Servlet: doPost");
		HttpSession session = req.getSession(false); 

		// holds the error message text, if there is any
		String errorMessage = null;

		
		//new AccountControllerPopulate(controller);
		Order order = (Order) session.getAttribute("order");
		Inventory inventory = (Inventory) session.getAttribute("inventory");
		CreateOrderModel model = new CreateOrderModel();
		model.setOrder(order);
		model.setInventory(inventory);
	
		Item removeItem = null;
		Extras removeExtra = null;
		Boolean continueOrder = false;
		
		continueOrder =  Boolean.valueOf(req.getParameter("continueOrder"));
		removeItem = inventory.getItem(req.getParameter("removeitem"));
		if (removeItem != null) {
			order.removeItem(removeItem);
			System.out.println("removing " + removeItem);
		}
		if (continueOrder) {
			req.getRequestDispatcher("/_view/createorder.jsp").forward(req, resp);
		}
		
		//errorMessage = "hello";
		
		//model.setError(errorMessage);
		
		req.setAttribute("model", model);
		req.setAttribute("inventory", inventory);
		req.setAttribute("order", order);
		session.setAttribute("order", order);
		session.setAttribute("inventory", inventory);
		
		req.getRequestDispatcher("/_view/vieworder.jsp").forward(req, resp);
		
	}

	
}
