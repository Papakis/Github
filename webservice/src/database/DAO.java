package database;

import security.PasswordSecurity;
import security.TokenSecurity;

/**
 * creates queries for database
 *
 */
public class DAO {

	private static String USERNAME = "username"; // columns names
	private static String PASSWORD = "password";
	private static String TOKEN = "token";

	public static String getUserToken(String username, String password) {
		String query = "SELECT password FROM users " 
				+ "WHERE " + USERNAME + "='" + username+"'";
		String passwordFromDatabase= DatabaseHelper.getSingleElement(query, PASSWORD);
		
		if(PasswordSecurity.isPasswordLegit(password, passwordFromDatabase)){
			String passwordQuery = "SELECT token FROM users " 
					+ "WHERE " + USERNAME + "='" + username+"'";
			return DatabaseHelper.getSingleElement(passwordQuery, TOKEN);
		}else{
			return null;
		}
	}
	
	public static void insertUser(String username, String password, String token) {
		String query = "INSERT INTO users (" + USERNAME + "," + PASSWORD + ","+ TOKEN + ") " 
				+ "VALUES ('" + username + "','"
				+ PasswordSecurity.encryptPassword(password) + "','" + token
				+ "')";
		DatabaseHelper.insert(query);
	}

}
