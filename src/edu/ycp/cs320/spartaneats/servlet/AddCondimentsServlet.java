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
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;


public class AddCondimentsServlet extends HttpServlet {
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
		List<Condiments> condimentList = new ArrayList<Condiments>();
		System.out.println(req.getParameter("type"));
		try {
			Item addItem =(Item) session.getAttribute("addItem");
			condimentList = db.findCondimentbyType(addItem.getItemType());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		req.setAttribute("condimentList", condimentList);
	    req.getRequestDispatcher("/_view/addcondiments.jsp").forward(req, resp);
		
	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Items Servlet: doPost");
		HttpSession session = req.getSession(false); 

		// holds the error message text, if there is any
		String errorMessage = null;

		DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
		
		String condiments[] = req.getParameterValues("addcondiments");
		List<String> condimentList = Arrays.asList(condiments);
		List<Integer> condimentListIds = new ArrayList<Integer>();
		for (String s: condimentList) {
			System.out.println(s);
			try {
				condimentListIds.add(db.findCondimentsbyCondimentName(s).getCondID());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		int item_id = ((Item)session.getAttribute("addItem")).getItemId();
		System.out.println(item_id);
		int order_id = (int)session.getAttribute("order_id");
		try {
			db.addItemToOrder(order_id, item_id, 1, condimentListIds);
			resp.sendRedirect(req.getContextPath()+"/vieworder");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//req.getRequestDispatcher("/_view/addcondiments.jsp").forward(req, resp);
	}
}