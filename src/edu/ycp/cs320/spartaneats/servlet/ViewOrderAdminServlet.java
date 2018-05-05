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


import edu.ycp.cs320.spartaneats.model.Account;
//import edu.ycp.cs320.spartaneats.model.AccountControllerPopulate;
import edu.ycp.cs320.spartaneats.model.Condiments;
import edu.ycp.cs320.spartaneats.model.CreateOrderModel;
import edu.ycp.cs320.spartaneats.model.Inventory;
import edu.ycp.cs320.spartaneats.model.Item;
import edu.ycp.cs320.spartaneats.model.Order;
import edu.ycp.cs320.spartaneats.model.OrderItem;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;


public class ViewOrderAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("View Order Admin Servlet: doGet");
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        Order order = null;
        List<Account> accounts = null;
        List<OrderItem> orderItem = null;
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
	    
	    try {
	    	// compile the order
	    	order = db.findOrderFromOrderId((int)session.getAttribute("order_id"));
			accounts = db.findAccountbyAccountID(order.getAccountId());
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
			 session.setAttribute("accounts", accounts.get(0));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    req.getRequestDispatcher("/_view/vieworderadmin.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("View Order Admin Servlet: doPost");
		HttpSession session = req.getSession(false); 
		
		DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");		
		
		Boolean adminPage = false;
		Boolean orderComplete = false;
		adminPage =  Boolean.valueOf(req.getParameter("adminpage"));
		orderComplete = Boolean.valueOf(req.getParameter("orderComplete"));	
		
		if (orderComplete) { // set order to inactive if this is true.

			try {
				int orderId = (Integer) session.getAttribute("order_id");
				db.updateOrderToInActive(orderId);
				// TODO make a order complete servlet and forward there from this.
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (adminPage) {
			req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
		}
	}
}
