package com.crm.generic.databaseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {
	Connection con;
	public void getConnection(String url, String username, String password)  {
		try {Driver d = new Driver();
		DriverManager.registerDriver(d);
		con= DriverManager.getConnection(url, username, password);			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void getDbConnection() {
		try {Driver d = new Driver();
		DriverManager.registerDriver(d);
		con= DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void closeDbConnection()  {
		try {
			con.close();
		} 
		catch (Exception e) {}
	}
	public ResultSet executeSelectQuery(String query)  {
		ResultSet resobj = null;
		try {
			Statement st = con.createStatement();
			resobj = st.executeQuery(query);
		} 
		catch (Exception e) {	}
		return resobj;
	}
	public int executeNonSelectQuery(String query) {
		int res = 0;
		try {
			Statement st = con.createStatement();
			res = st.executeUpdate(query);
		}
		catch (Exception e) {}
		return res;
	}
	

}
