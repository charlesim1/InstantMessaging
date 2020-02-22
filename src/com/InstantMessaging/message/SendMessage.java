package com.InstantMessaging.message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.InstantMessaging.connections.LoginConnection;

public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SendMessage() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("-----------------------------------------");
		
		String message = request.getParameter("message");
		String userId = request.getParameter("userId");
		String friendId = request.getParameter("friendId");
		
		String infoString = userId + "@" + friendId;
		
		System.out.println("message: " + message + " userId: " + userId + " friendId: " + friendId);
		
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = new LoginConnection().getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT * FROM (SELECT no FROM all_messages ORDER BY no DESC) WHERE ROWNUM = 1";
			
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			
			// rs.getInt(1) returns the current highest integer, so the nextNum variable is one more than that of the 
			// current highest number.
			
			int nextNum = 0;
			try {
				nextNum = rs.getInt(1) + 1;
			} catch (Exception e) {
				
			}
			
			
			sql = "INSERT INTO all_messages (no, info, messages) "
					+ "VALUES (" + nextNum + ", '" + infoString + "', '" + message + "')";
			rs = stmt.executeQuery(sql);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}





















