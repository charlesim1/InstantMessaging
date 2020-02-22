package com.InstantMessaging.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
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

/**
 * Servlet implementation class RefreshMessages
 */
public class RefreshMessages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RefreshMessages() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int chatNum = Integer.parseInt(request.getParameter("chatNum"));
		System.out.println("chatNum is " + chatNum);
		String userId = request.getParameter("userId");
		String friendId = request.getParameter("friendId");
		
		Connection conn = null;
		Statement stmt = null;
		
		Stack<ChatLog> userLogs = new Stack<>();
		
		response.getWriter().flush();
		
		try {
			// Getting connection to database
			conn = new LoginConnection().getConnection();
			stmt = conn.createStatement();
			
			// Getting the correct id and password form the database
			String sql = "SELECT NO, INFO, MESSAGES FROM all_messages WHERE NO>" + chatNum
					+ " AND info='" + userId + "@" + friendId + "'";
			sql += " OR info='" + friendId + "@" + userId + "'";
			sql += " ORDER BY NO ASC";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			/*
			 * This while loop takes the chat logs of the two users and pushes them into a stack called userLogs.
			 * The data is then characterized by whoever sent the message. For example, if the user is the one who
			 * sent the particular message, then isUser = true. Otherwise, isUser = false.
			 */
			while (rs.next()) {
				int number = rs.getInt(1);
				String infoString = rs.getString(2);
				ChatLog tempObject;
				
				if (number <= chatNum) {
					continue;
				}
				
				if ( infoString.substring(0, infoString.indexOf("@")).equals(userId) ) {
					tempObject = new ChatLog(Integer.parseInt(rs.getString(1)), true, rs.getString(3));
				} else {
					tempObject = new ChatLog(Integer.parseInt(rs.getString(1)), false, rs.getString(3));
				}
				userLogs.push(tempObject);
			}
			
			// Sending the stack to the jsp page, as well as redirecting the page to the chatroom.
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/chat/chatRoom.jsp");
			
			String json = new Gson().toJson(userLogs);

			response.getWriter().write(json);
			response.getWriter().flush();

//			dispatcher.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
