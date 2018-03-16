package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import edu.ycp.cs320.spartaneats.controller.AccountController;
import edu.ycp.cs320.spartaneats.model.Account;
import edu.ycp.cs320.spartaneats.model.AccountControllerPopulate;
import edu.ycp.cs320.spartaneats.model.LoginModel;

public class LoginServlet extends HttpServlet {
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
		AccountController controller = new AccountController();
		//new AccountControllerPopulate(controller);
		LoginModel model = new LoginModel();
		Account account1 = new Account("billybones","ImaPirate");
		Account account2 = new Account("SwashBucket","ImaBucket");
		Account account3 = new Account("DeckBroom","ImaBroom");
		controller.addAccount(account1);
		controller.addAccount(account2);
		controller.addAccount(account3);
		
		// decode POSTed form parameters and dispatch to controller
		model.setAccountName(req.getParameter("userName"));
		model.setPassword(req.getParameter("password"));
		String name = req.getParameter("userName");
		
		Account account = new Account(req.getParameter("userName"),req.getParameter("password"));
		
		if (!controller.doesAccountExist(account.getAccountName())) {
			System.out.println("Hi THere");
			System.out.println(account.getAccountName());
			System.out.println(controller.getAccount(name).getAccountName());
			System.out.println(controller.getAccount("billybones").getAccountName());
			errorMessage = "Username does not exist";
		}
		else if (controller.getAccount(account.getAccountName()).isPasswordCorrect(model.getPassword()) == false) {
			errorMessage = "Password is not correct";
		}
		
		else {
			req.getRequestDispatcher("/_view/logginsuccess.jsp").forward(req, resp);
		}
		
		
		model.setError(errorMessage);
		
		req.setAttribute("model", model);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}

	
}
