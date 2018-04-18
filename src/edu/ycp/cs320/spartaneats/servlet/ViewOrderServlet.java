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

import edu.ycp.cs320.spartaneats.model.CreateOrderModel;
import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;
import edu.ycp.cs320.spartaneats.model.OrderItem;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;


public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Order Servlet: doGet");
		
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        Inventory inventory = new Inventory();
        Order order = new Order(false, 1, 1);
        List<OrderItem> orderItem = null;
        order.getCondArray();
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
	    
	    try {
	    	
	    	// compile the order
			orderItem =  db.findOrderItemsFromOrderID((int)session.getAttribute("order_id"));
			for (OrderItem o: orderItem) {
		    	 order.addItem(db.findItembyItemID(o.getItem_id()));
		    	 List<String> tempCondArray = new ArrayList<String>();
		    	 for(int i: o.getCondiment_id()) {
		    		 tempCondArray.add(db.findCondimentsbyCondimentID(i).getCondName());
		    	 }
		    	 order.getCondArray().add(tempCondArray);
		    	 
		     }
			 req.setAttribute("order", order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	    
	    
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
		
		
		
		req.setAttribute("model", model);
		req.setAttribute("inventory", inventory);
		req.setAttribute("order", order);
		session.setAttribute("order", order);
		session.setAttribute("inventory", inventory);
		
		req.getRequestDispatcher("/_view/vieworder.jsp").forward(req, resp);
		
	}

	
}
