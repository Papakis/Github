package loggin;

import java.util.logging.Logger;

public class JavaLogger {
	private static Logger theLogger = Logger.getLogger(JavaLogger.class.getName());
	
	public static void main(String args[]){
		log("Hi");
	}
	
	public static void log(String message){
		theLogger.info(message);
	    System.err.println();
	}

}