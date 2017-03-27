package com.aohuan.dodo.java_demo.common.tools.gen;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MysqlTableReader {
	
	public static final String driver = "com.mysql.jdbc.Driver";
	public static final String MYSQL_HOST = "192.168.1.150";
	public static final String MYSQL_PORT = "3306";
	
	public static final String user = "root";
	public static final String password = "root";
	
	public static final String DB_NAME = "zk_huizhi";
	
	public static final String URL = "jdbc:mysql://"+MYSQL_HOST+":"+MYSQL_PORT+"/"+DB_NAME+"?useUnicode=true&characterEncoding=UTF-8&createDatabaseIfNotExist=true";
	
	public static final String MYSQL_TABLE_PREFIX = "zk_";
	
	
	
	private static Connection getConnection(){
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, user, password);
			if (!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
				return conn;
			}else{
				System.err.println("connect filed");
				
			}				
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return null;
	}
	
	public static ArrayList<String> getTables(){
		Connection connection = getConnection();
		if(connection == null){
			return null;
		}		
		try {
			return getTableArray(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	

	private static ArrayList<String> getTableArray(Connection conn) throws SQLException {
		ArrayList<String> strArray = new ArrayList<String>();
		DatabaseMetaData dbMetData = conn.getMetaData();
		ResultSet rs = dbMetData.getTables(null, convertDatabaseCharsetType("root", "mysql"), null,
				new String[] { "TABLE", "VIEW" });

		while (rs.next()) {
			if (rs.getString(4) != null
					&& (rs.getString(4).equalsIgnoreCase("TABLE") || rs.getString(4).equalsIgnoreCase("VIEW"))) {
				String tableName = rs.getString(3).toLowerCase();
//				System.out.print(tableName + "\t");
				if(tableName.startsWith(MYSQL_TABLE_PREFIX)){
					strArray.add(tableName);	
				}
				
			}
		}
		conn.close();
		return strArray;
	}

	
	private static String convertDatabaseCharsetType(String in, String type) {
		String dbUser;
		if (in != null) {
			if (type.equals("oracle")) {
				dbUser = in.toUpperCase();
			} else if (type.equals("postgresql")) {
				dbUser = "public";
			} else if (type.equals("mysql")) {
				dbUser = null;
			} else if (type.equals("mssqlserver")) {
				dbUser = null;
			} else if (type.equals("db2")) {
				dbUser = in.toUpperCase();
			} else {
				dbUser = in;
			}
		} else {
			dbUser = "public";
		}
		return dbUser;
	}

	
}
