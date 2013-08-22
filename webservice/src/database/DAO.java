package database;

import security.PasswordSecurity;
import security.TokenSecurity;

public class DAO {

	private static String USERNAME = "username"; // columns names
	private static String PASSWORD = "password";
	private static String TOKEN = "token";
	private static String COOKIE = "cookie";

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
		
//		
//		String query2 = "SELECT token FROM users " 
//					+ "WHERE " + USERNAME + "='" + username
//					+ "' AND " + PASSWORD + "='"+ PasswordSecurity.encryptPassword(password)+"'";
//		return DatabaseHelper.getSingleElement(query2, TOKEN);
	}
	
	public static String getUserCookie(String username) {
		String query = "SELECT cookie FROM users " 
					+ "WHERE " + USERNAME + "='"	+ username + "'";
		return DatabaseHelper.getSingleElement(query, COOKIE);
	}

	public static void insertUser(String username, String password, String token) {
		String query = "INSERT INTO users (" + USERNAME + "," + PASSWORD + ","+ TOKEN + ") " 
				+ "VALUES ('" + username + "','"
				+ PasswordSecurity.encryptPassword(password) + "','" + token
				+ "')";
		DatabaseHelper.insert(query);
	}

	public static void insertUserWithCookie(String username, String password,
			String token, String cookie) {
		String query = "INSERT INTO users (" + USERNAME + "," + PASSWORD + ","+ TOKEN +","+COOKIE+ ") " 
				+ "VALUES ('" + username + "','"
				+ PasswordSecurity.encryptPassword(password) + "','" + token
				+ "','" + PasswordSecurity.encryptPassword(cookie) + "')";
		DatabaseHelper.insert(query);
	}

	public static void insertCookie(String username, String cookie) {
		String query = "INSERT INTO users (" + COOKIE + ") " 
				+ "VALUES ('" + PasswordSecurity.encryptPassword(cookie) + "') "
				+ "WHERE " + USERNAME + "='"	+ username + "'";
		DatabaseHelper.insert(query);
	}

}
