package com.InstantMessaging.login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.InstantMessaging.connections.LoginConnection;

public class SignUpComplete extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			sendInfo(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/login/signUpConfirm.jsp");
		dispatcher.forward(request, response);	
	} 
	
	/*
	 * This function sends the values that the user inputted 
	 * into the database.
	 */
	public void sendInfo(HttpServletRequest req, HttpServletResponse res) throws ClassNotFoundException, SQLException {

		
		Connection conn = null;
		Statement stmt = null;
		
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		try {
			conn = new LoginConnection().getConnection();
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO t_user (name, email, id, password) ";
			sql += "VALUES ('" + name + "', '" + email + "', '" + id + "', '" + password + "')";
			ResultSet rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}














