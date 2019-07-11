package com.InstantMessaging.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.InstantMessaging.connections.LoginConnection;

public class Login extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/*
	 * Checks if the user-entered username and password are the same as the ones on the database.
	 * If they're the same the user is redirected to the instant messaging page. If not they're shown an 
	 * error page telling them that they entered their information wrong.
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Boolean loginPass = false;
		
		Connection conn = null;
		Statement stmt = null;
		
		String attemptedId = req.getParameter("user");
		String attemptedPass = req.getParameter("pass");
		String id = "";
		String pass = "";
		
		try {
			// Getting connection to database
			conn = new LoginConnection().getConnection();
			stmt = conn.createStatement();
			
			// Getting the correct id and password form the database
			String sql = "SELECT ID, PASSWORD FROM t_user WHERE id='" + attemptedId + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			id = rs.getString(1);
			pass = rs.getString(2);
			
			// Comparing the usernames and passwords
			if (attemptedId.equals(id) && attemptedPass.equals(pass)) {
				loginPass = true;
			} 
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if (loginPass) { // if their credentials are correct, the user is redirected to instant messaging page
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/login/signUpConfirm.jsp");
			dispatcher.forward(req, res);
		} else { // if their credentials are incorrect, the user is redirected to a screen telling them that their login was incorrect
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/login/signUpConfirm.jsp");
			dispatcher.forward(req, res);
		}
		
	} 
}

















