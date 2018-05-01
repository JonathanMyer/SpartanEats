package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.spartaneats.controller.OrderController;
import edu.ycp.cs320.spartaneats.model.Condiments;
import edu.ycp.cs320.spartaneats.model.Order;
import edu.ycp.cs320.spartaneats.model.OrderItem;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("Admin Servlet: doGet");

		HttpSession session = req.getSession(false);    // fetch the session and handle 

		if (session == null) {    // no session exists, redirect to error page with error message
			resp.sendRedirect(req.getContextPath()+"/login");
		} 
		DerbyDatabase db = (DerbyDatabase) session.getAttribute("db");
		try {
			// compile the order
			List<Order> ActiveOrder =  db.findActiveOrders();
			session.setAttribute("activeOrder", ActiveOrder);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Admin Servlet: doGet");
		
		HttpSession session = req.getSession(false); 
		String orderNumberString = (String)req.getParameter("orderId");
		if (orderNumberString != null) {
			int orderNumber = Integer.parseInt(orderNumberString);
			System.out.println("Order Number is: " + orderNumber);
			session.setAttribute("order_id", orderNumber);
			resp.sendRedirect(req.getContextPath()+"/vieworderadmin");
		}
		else {
			req.getRequestDispatcher("/_view/admin.jsp").forward(req, resp);
		}
	}

}

