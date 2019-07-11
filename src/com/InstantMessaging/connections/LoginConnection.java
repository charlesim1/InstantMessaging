package com.InstantMessaging.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginConnection {
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "c##charlesim";
		String pass = "Water65343";
		Connection conn = DriverManager.getConnection(url, user, pass);
		
		return conn;
	}
}
