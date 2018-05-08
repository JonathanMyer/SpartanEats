package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("View Order Servlet: doGet");
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        Order order = new Order(false, 1, 1);
        List<OrderItem> orderItem = null;
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
	    order.setOrderId((int)session.getAttribute("order_id"));
	    order.setAccount_id((int)session.getAttribute("account_id"));
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
		}try {
			double flex =  db.findFlexBalance(order.getAccountId());
			session.setAttribute("flexBalance", flex);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			// compile the order
			double dining =  db.findDiningBalance(order.getAccountId());
			session.setAttribute("diningBalance", dining);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    req.getRequestDispatcher("/_view/vieworder.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("View Order Servlet: doPost");
		HttpSession session = req.getSession(false); 
		// holds the error message text, if there is any
		String errorMessage = null;
		DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
		Order order = (Order) session.getAttribute("order");
		String removeItemString = req.getParameter("removeItem");
		String condiment = req.getParameter("removeCondiment");
		String fromItem = req.getParameter("fromItem");
		String orderName = req.getParameter("orderName");
		String deliveryDest = req.getParameter("DORM");
		String delivery = req.getParameter("tab");
		String payment = req.getParameter("payment");
		System.out.println("Delivery Dest: " + deliveryDest +" Delivery: " + delivery);
		System.out.println("Order Name: "+ orderName);
		Boolean continueOrder = false;
		Boolean orderComplete = false;
		continueOrder =  Boolean.valueOf(req.getParameter("continueOrder"));
		orderComplete = Boolean.valueOf(req.getParameter("orderComplete"));
		if (delivery != null) {
			if(delivery.equals("false")) {
				deliveryDest = "Pickup";
			}
			try {
				db.updateDeliveryPreferences(order.getOrderId(), delivery, deliveryDest);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (condiment != null && fromItem != null) {
			OrderItem removeOrderItem2 = order.getOrderItem(Integer.parseInt(fromItem));
			try {
				System.out.println("Remove Condiment: "+condiment +" from OrderItem Number: " + fromItem);
				db.removeCondFromOrderItem(removeOrderItem2, Integer.parseInt(condiment));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			doGet(req,resp);
		}
		else if (removeItemString != null) {
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
				if(payment != null) {
					if(payment.equals("Flex")) {
						System.out.println("Old Flex Balance: " + db.findFlexBalance(order.getAccountId()));
						db.updateFlexBalance(order.getTotalPrice(), order.getAccountId());
						System.out.println("New Flex Balance: " + db.findFlexBalance(order.getAccountId()));
					}
					if(payment.equals("Dining")) {
						System.out.println("Old Dining Balance: " + db.findDiningBalance(order.getAccountId()));
						db.updateDiningBalance(order.getTotalPrice(), order.getAccountId());
						System.out.println("New Dining Balance: " + db.findDiningBalance(order.getAccountId()));
					}
				}
				
				int orderId = (Integer) session.getAttribute("order_id");
				if(orderName != null) {
					db.insertOrderName(orderId, orderName);
				}
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
