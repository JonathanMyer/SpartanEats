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
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;


public class AddItemsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Items Servlet: doGet");
		
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        
		
	    if (session == null) {    // no session exists, redirect to error page with error message
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
		List<Item> itemList = new ArrayList<Item>();
		System.out.println(req.getParameter("type"));
		
		
		try {
			itemList = db.findItembyType(req.getParameter("type"));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("itemList", itemList);
	    req.getRequestDispatcher("/_view/additems.jsp").forward(req, resp);
		
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Items Servlet: doPost");
		HttpSession session = req.getSession(false); 

		// holds the error message text, if there is any
		String errorMessage = null;

		DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
		
		
		try {
			Item addItem = (Item) db.findItembyName(req.getParameter("additem"));
			if (addItem.getCondiments().equals("true")) {
				List<Condiments> condList = db.findCondimentbyType(addItem.getItemType());
				req.setAttribute("condList", condList);
				session.setAttribute("addItem", addItem);
				resp.sendRedirect(req.getContextPath()+"/addcondiments");
				
				
			} else {
				int orderNum = (int)session.getAttribute("order_id");
				
				
				db.addItemToOrder(orderNum, addItem.getItemId(), 1, new ArrayList<Integer>(0));
				resp.sendRedirect(req.getContextPath()+"/vieworder");
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		req.getRequestDispatcher("/_view/additems.jsp").forward(req, resp);
		
	}

	
}
