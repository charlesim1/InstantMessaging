package com.InstantMessaging.message;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.InstantMessaging.connections.LoginConnection;
import com.google.gson.Gson;


/*
 * This is the Oracle table that was made to store all of the messages.

		CREATE TABLE all_messages (
		  no INT, 
		  info VARCHAR(200),
		  messages VARCHAR(500) 
		);
		 
 */

public class MessagingPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MessagingPage() {
        super();
        // TODO Auto-generated constructor stub
    }
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String nameOfUser = "";
//		String userId = request.getParameter("userId");
//		String friendId = request.getParameter("friendId");
//		
//		System.out.println(userId);
//		System.out.println(friendId);
//		
//		Connection conn = null;
//		Statement stmt = null;
//		
//		Stack<ChatLog> userLogs = new Stack<>();
//		
//		try {
//			// Getting connection to database
//			conn = new LoginConnection().getConnection();
//			stmt = conn.createStatement();
//			
//			// Getting the correct id and password form the database
//			String sql = "SELECT NO, INFO, MESSAGES FROM all_messages WHERE info='" + userId + "@" + friendId + "'";
//			sql += " OR info='" + friendId + "@" + userId + "'";
//			
//			ResultSet rs = stmt.executeQuery(sql);
//			
//			/*
//			 * This while loop takes the chat logs of the two users and pushes them into a stack called userLogs.
//			 * The data is then characterized by whoever sent the message. For example, if the user is the one who
//			 * sent the particular message, then isUser = true. Otherwise, isUser = false.
//			 */
//			while (rs.next()) {
//				String infoString = rs.getString(2);
//				ChatLog tempObject;
//				if ( infoString.substring(0, infoString.indexOf("@")).equals(nameOfUser) ) {
//					tempObject = new ChatLog(Integer.parseInt(rs.getString(1)), true, rs.getString(3));
//				} else {
//					tempObject = new ChatLog(Integer.parseInt(rs.getString(1)), false, rs.getString(3));
//				}
//				userLogs.push(tempObject);
//				
//				System.out.println(tempObject);
//			}
//			
//			// Sending the stack to the jsp page, as well as redirecting the page to the chatroom.
//			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/chat/chatRoom.jsp");
//			
//			String json = new Gson().toJson(userLogs);
//			request.setAttribute("userLogs", json);
//			
//			dispatcher.forward(request, response);
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String friendId = request.getParameter("friendId");
		
		Connection conn = null;
		Statement stmt = null;
		
		Stack<ChatLog> userLogs = new Stack<>();
		
		try {
			// Getting connection to database
			conn = new LoginConnection().getConnection();
			stmt = conn.createStatement();
			
			// Getting the correct id and password form the database
			String sql = "SELECT NO, INFO, MESSAGES FROM all_messages WHERE info='" + userId + "@" + friendId + "'";
			sql += " OR info='" + friendId + "@" + userId + "' ORDER BY NO ASC";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			/*
			 * This while loop takes the chat logs of the two users and pushes them into a stack called userLogs.
			 * The data is then characterized by whoever sent the message. For example, if the user is the one who
			 * sent the particular message, then isUser = true. Otherwise, isUser = false.
			 */
			while (rs.next()) {
				String infoString = rs.getString(2);
				ChatLog tempObject;
				System.out.println("sender: " + infoString.substring(0, infoString.indexOf("@")));
				System.out.println("name of user: " + userId);
				if ( infoString.substring(0, infoString.indexOf("@")).equals(userId) ) {
					tempObject = new ChatLog(Integer.parseInt(rs.getString(1)), true, rs.getString(3));
				} else {
					tempObject = new ChatLog(Integer.parseInt(rs.getString(1)), false, rs.getString(3));
				}
				userLogs.push(tempObject);
				
				System.out.println(tempObject);
			}
			
			// Sending the stack to the jsp page, as well as redirecting the page to the chatroom.
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/chat/chatRoom.jsp");
			
			String json = new Gson().toJson(userLogs);
			request.setAttribute("userLogs", json);
			request.setAttribute("userId", userId);
			request.setAttribute("friendId", friendId);
			
			dispatcher.forward(request, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}



















