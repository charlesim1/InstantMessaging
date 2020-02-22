package com.InstantMessaging.friends;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.InstantMessaging.connections.LoginConnection;

/**
 * Servlet implementation class getFriends
 */
public class getFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getFriends() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");

		String allFriends = "";
		
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// Getting connection to database
			conn = new LoginConnection().getConnection();
			stmt = conn.createStatement();
			
			// Getting the correct id and password form the database
			String sql = "SELECT FRIENDS FROM t_user WHERE id='" + userId + "'";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			allFriends = rs.getString(1);
			
			response.getWriter().write(allFriends);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
