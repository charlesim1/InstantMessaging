package com.InstantMessaging.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpForm extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/login/signUpForm.jsp");
		dispatcher.forward(req, res);
//		PrintWriter out;
//		try {
//			out = res.getWriter();
//			out.println("test success");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
	}
}
