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
 * Servlet implementation class addFriend
 */
public class addFriend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addFriend() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String friendId = request.getParameter("friendId");
		String userId = request.getParameter("userId");
		
		if (friendId.equals(userId)) {
			return;
		}
		
		
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
			
			String allFriends = rs.getString(1);
			
			if (allFriends == null) {
				sql = "UPDATE t_user SET friends='" + friendId + "'" + "WHERE id='" + userId + "'";
				rs = stmt.executeQuery(sql);
				return;
			}
			
			String[] friendList = allFriends.split(",");

			// this loop checks if the id is the same as any of the user's current friends.
			for (int i = 0; i < friendList.length; i += 1) {
				if (friendId.equals(friendList[i])) {
					return; // if an id matches with the one from the Friend's List, then it gets sent as an error.
				}
			}
			
			String updatedFriends = "";
			
			// If the user has no current friends, the friends column is updated to the id sent from the client
			if (allFriends.equals("")) {
				updatedFriends = friendId;
			} else {
				// if the user has at least one friend, the new friend is added into the comma separated String of friends.
				updatedFriends = allFriends + "," + friendId;
			}
			
			// This updates the friends column
			sql = "UPDATE t_user SET friends='" + updatedFriends + "'" + "WHERE id='" + userId + "'";
			rs = stmt.executeQuery(sql);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
