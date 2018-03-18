package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.spartaneats.controller.AccountController;
import edu.ycp.cs320.spartaneats.model.Account;
import edu.ycp.cs320.spartaneats.model.AccountControllerPopulate;
import edu.ycp.cs320.spartaneats.model.CreateOrderModel;
import edu.ycp.cs320.spartaneats.model.LoginModel;
import edu.ycp.cs320.spartaneats.model.Order;
import edu.ycp.cs320.spartaneats.model.OrderModel;

public class CreateOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");
		
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;

		// result of calculation goes here
		OrderController controller = new OrderController();
		//new AccountControllerPopulate(controller);
		Order order = new Order();
		Inventory inventory = new Inventory();
		CreateOrderModel model = new CreateOrderModel();
		model.setOrder(order);
		model.setInventory(inventory);
		
		// decode POSTed form parameters and dispatch to controller
		inventory.addItem(new Item("Hamburger",8.24,3));
		inventory.addItem(new Item("Coke",3.09, 4));
		inventory.addItem(new Item("French Fries", 4.49, 8));
		
		
		if (!controller.doesAccountExist(model.getAccountName())) {
			errorMessage = "Username does not exist";
		}
		else if (controller.getAccount(model.getAccountName()).isPasswordCorrect(model.getPassword()) == false) {
			errorMessage = "Password is not correct";
		}
		
		else {
			model.setSuccess(true);
		}
		
		
		model.setError(errorMessage);
		
		req.setAttribute("model", model);
		
		
		// Forward to view to render the result HTML document
		if (model.isSuccess()) {
			HttpSession session = req.getSession(true);    // create the session
			resp.sendRedirect(req.getContextPath()+"/loginsuccess");
		}
		else {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}

	
}
