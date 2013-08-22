package security;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class PasswordSecurity {
	

	static BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
	
	public static String encryptPassword(String password){
		return passwordEncryptor.encryptPassword(password);
	}
	
	public static boolean isPasswordLegit(String inputPassword, String encryptedPassword){
		if (passwordEncryptor.checkPassword(inputPassword, encryptedPassword)) {
			  return true;
			} else {
			  return false;
			}
	}

}
