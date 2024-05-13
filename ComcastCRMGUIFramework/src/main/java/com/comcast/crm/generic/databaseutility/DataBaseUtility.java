package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection conn;
	public void getDbConnection(String url,String username,String password) throws SQLException {
		try {
		Driver driverRef= new Driver();
		DriverManager.registerDriver(driverRef);
		 conn	= DriverManager.getConnection(url,username,password);
		}catch(Exception e) {}
	}
	
	public void getDbConnection() throws SQLException {
		try {
		Driver driverRef= new Driver();
		DriverManager.registerDriver(driverRef);
		 conn	= DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		}catch(Exception e) {}
	}
	
	public void closeDBConnection() {
		try {
			conn.close();
		}
		catch (Exception e) {}
	}
	
	public ResultSet executeSelectQuery(String query) {
		ResultSet res=null;
		try {
		Statement state	=conn.createStatement();
	 res =state.executeQuery(query);
		} catch(Exception e) {}
		return res;
	}
	
	public int executeNonSelectQuery(String query) {
		int res=0;
		try {
			Statement state	=conn.createStatement();
		    res =state.executeUpdate(query);
			} catch(Exception e) {}
		return res;
		
	}
}
