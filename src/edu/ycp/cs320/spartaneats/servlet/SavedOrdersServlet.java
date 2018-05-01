package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eclipse.jetty.server.session.Session;

import edu.ycp.cs320.spartaneats.model.Order;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;

public class SavedOrdersServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Saved Orders Servlet: doGet");
		
		
		HttpSession session = req.getSession(false);    // fetch the session and handle 
        
	    if (session == null) {    // no session exists, redirect to login page
	    	resp.sendRedirect(req.getContextPath()+"/login");
	        } 
	    
	    DerbyDatabase db =  (DerbyDatabase) session.getAttribute("db");
	    int userId = (int) session.getAttribute("account_id");
	    List <Order> orderList= null;
	    try {
			orderList = db.findOrdersFromAccountID(userId);
			System.out.println("Order Length is: " + orderList.size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    req.setAttribute("orderList", orderList);
		req.getRequestDispatcher("/_view/savedorders.jsp").forward(req, resp);
	}                                 
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Saved Orders Servlet: doPost");
		HttpSession session = req.getSession(false); 
		String orderNumberString = (String)req.getParameter("orderId");
		if (orderNumberString != null) {
			int orderNumber = Integer.parseInt(orderNumberString);
			System.out.println("Order Number is: " + orderNumber);
			session.setAttribute("order_id", orderNumber);
			resp.sendRedirect(req.getContextPath()+"/vieworder");
		}
		else {
			req.getRequestDispatcher("/_view/savedorders.jsp").forward(req, resp);
		}
	}
	
}