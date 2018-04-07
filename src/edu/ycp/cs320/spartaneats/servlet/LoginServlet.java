package edu.ycp.cs320.spartaneats.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.spartaneats.model.Account;
import edu.ycp.cs320.spartaneats.model.LoginModel;
import edu.ycp.cs320.spartaneats.persist.DerbyDatabase;

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

		
		
		List<Account> accountList = null;
		DerbyDatabase db = new DerbyDatabase();
		LoginModel model = new LoginModel();
		// decode POSTed form parameters and dispatch to controller
		model.setAccountName(req.getParameter("userName"));
		model.setPassword(req.getParameter("password"));
		model.setSuccess(false);
		try {
			accountList = db.findAccountbyUserName(model.getAccountName());
		} catch (SQLException e) {
			errorMessage = "Issue Finding Account";
		}
		 
		
		if (accountList.size() <= 0) {
			errorMessage = "Username does not exist";
		}
		else if (accountList.size() == 1) {
			if (accountList.get(0).isPasswordCorrect(model.getPassword())) {
				model.setSuccess(true);
			} else {
				errorMessage = "Password is Incorect";
			}
		} else {
			errorMessage = "More than one account with that Username";
		}
		
		
		
		
		model.setError(errorMessage);
		
		req.setAttribute("model", model);
		
		// Forward to view to render the result HTML document
		if (model.getSuccess()) {
			HttpSession session = req.getSession(true);    // create the session
			resp.sendRedirect(req.getContextPath()+"/index");
		}
		else {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}

	
}
