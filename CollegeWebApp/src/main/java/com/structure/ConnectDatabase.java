package com.structure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {
	private final static String url="jdbc:postgresql://localhost:5432/collegedata";
	private final static String userName="postgres";
	private final static String passWord="admin";
	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnect() throws SQLException {
		return DriverManager.getConnection(url,userName,passWord);
	}
}
