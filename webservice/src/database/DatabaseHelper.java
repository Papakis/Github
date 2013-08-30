package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.tools.Server;

/**
 * Registers database and handles all queries
 *
 */
public class DatabaseHelper {

	private static String URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static String USER = "sa";
	private static String PWD = "";

	static DataSource ds = JdbcConnectionPool.create(URL, USER, PWD);
	static Connection conn;

	public static void startDatabase() {
		try {
			conn = ds.getConnection();
			conn.createStatement().executeUpdate(
					"CREATE TABLE users (" + " key VARCHAR(255) PRIMARY KEY AUTO_INCREMENT,"
							+ " username VARCHAR(100),"
							+ " password VARCHAR(100)," 
							+ " token VARCHAR(100),"
							+ " )");
			Server server = Server.createTcpServer().start(); // so we can display database in h2 console
			System.out.println("URL: jdbc:h2:" + server.getURL() + "/mem:test");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static String getSingleElement(String query, String columnName) {
		if(conn==null) startDatabase();
		String result=null;
		Statement statement = null;
		try{
		    statement=conn.createStatement();
		    ResultSet results=null;
		    try{
		        results = statement.executeQuery(query);
		        while(results.next()) {
		        	result=results.getString(columnName);
		        }
		    } finally {
		        if(result != null) results.close();
		        if(statement != null) statement.close();
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static void insert(String query){
		if(conn==null) startDatabase();
		Statement statement = null;
		try{
		    statement=conn.createStatement();
		    try{
		        statement.executeUpdate(query);
		    } finally {
		        if(statement != null) statement.close();
		    }
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
