package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderServlet {
private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Order Servlet: doGet");
		
		req.getRequestDispatcher("/_view/order.jsp").forward(req, resp);
	}
	
}
