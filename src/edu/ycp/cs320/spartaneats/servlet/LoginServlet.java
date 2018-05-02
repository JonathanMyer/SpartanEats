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
			errorMessage = "Username or Password is Incorrect";
		}
		else if (accountList.size() == 1) {
			if (accountList.get(0).isPasswordCorrect(model.getPassword())) {
				model.setSuccess(true);
				model.setAccount(accountList.get(0));
			} else {
				errorMessage = "Username or Password is Incorrect";
			}
			if (accountList.get(0).isAdminValidated()) {
				model.setAdmin(true);
			}
		} else {
			errorMessage = "More than one account with that Username";
		}		
		model.setError(errorMessage);
		
		req.setAttribute("model", model);

		
		
		// Forward to view to render the result HTML document
		if (model.getSuccess()) {
			model.setAdmin(accountList.get(0).isAdminValidated());
			HttpSession session = req.getSession(true);    // create the session
			int account_id = model.getAccount().getAccountId();
			session.setAttribute("account_id", account_id);
			session.setAttribute("db", db);
			session.setAttribute("username", model.getAccountName());
			if(model.getAdmin()==true) {
				System.out.println("Admin Status was found as admin");
				resp.sendRedirect(req.getContextPath()+"/admin");
			}
			else if(model.getAdmin()==false) {
			//Admin Status found to be false
			System.out.println("Admin Status was found as user or null");
			resp.sendRedirect(req.getContextPath()+"/index");
			}
			else {
				//Trial
				System.out.println("Else statement achieved in login servlet");
				resp.sendRedirect(req.getContextPath()+"/index");
			}
		}
		else {
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
	}	
}
