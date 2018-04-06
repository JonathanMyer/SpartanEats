package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Order;


import edu.ycp.cs320.spartaneats.controller.OrderController;


import edu.ycp.cs320.spartaneats.controller.OrderController;
import edu.ycp.cs320.spartaneats.model.Account;
//import edu.ycp.cs320.spartaneats.model.AccountControllerPopulate;

import edu.ycp.cs320.spartaneats.model.CreateOrderModel;
import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;


public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Order Servlet: doGet");
		
		HttpSession session = req.getSession(false);    // fetch the session and handle 
		
		Inventory inventory = new Inventory();
        
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
<<<<<<< HEAD
	    // create an order if one doesn't already exist
	    if (session.getAttribute("order") == null) {
	    	Order order = new Order(false, false, 1);
	    	session.setAttribute("order", order);
	    }
	    
	    session.setAttribute("inventory", inventory);
	    
		
	   
=======
		
	 // holds the error message text, if there is any
	 		String errorMessage = null;

	 		// result of calculation goes here
	 		OrderController controller = new OrderController();
	 		//new AccountControllerPopulate(controller);
	 		Order order = new Order( false, true, 1);
	 		Inventory inventory = new Inventory();
	 		CreateOrderModel model = new CreateOrderModel();
	 		model.setOrder(order);
	 		model.setInventory(inventory);
	 		
	 		// decode POSTed form parameters and dispatch to controller
	 		inventory.addItem(new Item(3,"Water",1.00));
			inventory.addItem(new Item(4,"Soda",3.09));
			inventory.addItem(new Item(5, "Sandwich", 4.49));
			inventory.addItem(new Item(6, "Pizza", 4.49));
			inventory.addItem(new Item(7, "Chips", 4.49));
			inventory.addItem(new Item(8, "Candy", 4.49));
			inventory.addItem(new Item(9, "Nuts", 4.49));
	 		
	 		
	 		errorMessage = "hello";
	 		
	 		model.setError(errorMessage);
	 		session.setAttribute("order", order);
	 		session.setAttribute("inventory", inventory);
	 		req.setAttribute("model", model);
	 		req.setAttribute("inventory", inventory);
>>>>>>> refs/remotes/origin/jmyer
	    
		req.getRequestDispatcher("/_view/createorder.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Order Servlet: doPost");
		HttpSession session = req.getSession(false); 

		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		OrderController controller = new OrderController();
		//new AccountControllerPopulate(controller);
		Order order = (Order) session.getAttribute("order");
		Inventory inventory = (Inventory) session.getAttribute("inventory");
		CreateOrderModel model = new CreateOrderModel();
		model.setOrder(order);
		model.setInventory(inventory);
		
		Item remove = null;
		Item add = null;
		add = inventory.getItem(req.getParameter("additem"));
		remove = inventory.getItem(req.getParameter("removeitem"));
		if (add != null) {
			order.addItem(add);
		}
		if (remove != null) {
			order.removeItem(remove);
		}
		
		
		errorMessage = "hello";
		
		model.setError(errorMessage);
		
		req.setAttribute("model", model);
		req.setAttribute("inventory", inventory);
		req.setAttribute("order", order);
		session.setAttribute("order", order);
		session.setAttribute("inventory", inventory);
		
		req.getRequestDispatcher("/_view/createorder.jsp").forward(req, resp);
		
	}

	
}
