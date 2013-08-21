package security;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class TokenSecurity {
	
	
	private static final String PASSPHRASE="VERY SECURE PASSPHRASE";
	private static StandardPBEStringEncryptor encryptor;
	
	static{
		encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(PASSPHRASE);
		encryptor.setAlgorithm("PBEWithMD5AndDES");
	}

	public static String encrypt(String data){
		return encryptor.encrypt(data);
	}
	
	public static String decrypt(String data){
		return encryptor.decrypt(data);
	}
	
}
