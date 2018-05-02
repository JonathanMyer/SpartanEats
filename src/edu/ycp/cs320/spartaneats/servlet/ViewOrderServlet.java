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


public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Create Order Servlet: doGet");
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        Order order = new Order(false, 1, 1);
        List<OrderItem> orderItem = null;
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
	    order.setOrderId((int)session.getAttribute("order_id"));
	    try {
	    	// compile the order
			orderItem =  db.findOrderItemsFromOrderID(order.getOrderId());
			for (OrderItem o: orderItem) {
		    	 order.addItem(db.findItembyItemID(o.getItem_id()));
		    	 List<Condiments> tempCondArray = new ArrayList<Condiments>();
		    	 for(int i: o.getCondiment_id()) {
		    		 tempCondArray.add(db.findCondimentsbyCondimentID(i));
		    	 }
		    	 order.addCondArrayList(tempCondArray);
		    	 
		     }
			 session.setAttribute("order", order);
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
		DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
		Order order = (Order) session.getAttribute("order");
		String removeItemString = req.getParameter("removeItem");
		Boolean continueOrder = false;
		Boolean orderComplete = false;
		continueOrder =  Boolean.valueOf(req.getParameter("continueOrder"));
		orderComplete = Boolean.valueOf(req.getParameter("orderComplete"));

		if (removeItemString != null) {
			int removeItem = Integer.parseInt(removeItemString);
			System.out.println("Removed Item ID: " + removeItem);
			OrderItem removeOrderItem = order.getOrderItem(removeItem);
			try {
				db.removeItemFromOrder(removeOrderItem);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doGet(req,resp);
		}
		
		else if (orderComplete) {

			try {
				int orderId = (Integer) session.getAttribute("order_id");
				db.updateOrderToActive(orderId);
				resp.sendRedirect(req.getContextPath()+"/ordercomplete");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (continueOrder) {
			req.getRequestDispatcher("/_view/createorder.jsp").forward(req, resp);
		}
		else {


			req.getRequestDispatcher("/_view/vieworder.jsp").forward(req, resp);
		}
	}
}
