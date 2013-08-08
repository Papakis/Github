package aspects;

import java.net.URI;
import java.util.List;

import loggin.JavaLogger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.sun.jersey.api.client.ClientResponse;

@Aspect
public class StatusCodeHandler {
	private static int statusCode;
	
	
	@Around("call (ClientResponse api.communication.*.sendRequest(java.lang.String))"
			+ "&& args(inputUrl)")
	public ClientResponse doSomethign(ProceedingJoinPoint joinPoint, String inputUrl) throws Throwable{
		JavaLogger.log("StatusCodeHandler| logAround| Before_Proceeding");
		ClientResponse serverResponse=(ClientResponse) joinPoint.proceed();
		JavaLogger.log("StatusCodeHandler| logAround| After_Proceeding| Status|" + serverResponse.getStatus());
		switch (serverResponse.getStatus()) {
		case 202:
			JavaLogger.log("StatusCodeHandler| switch| 202");
			Thread.sleep(1000);
			ClientResponse repeatedServerResponse=(ClientResponse) joinPoint.proceed();
			serverResponse=repeatedServerResponse;
			break;
		case 302:
		case 307:
			JavaLogger.log("StatusCodeHandler| switch| 302/307");
			String newUrl=serverResponse.getHeaders().getFirst("Location");
			ClientResponse newServerResponse=(ClientResponse) joinPoint.proceed(new Object[]{newUrl});
			serverResponse=newServerResponse;
			//throw new RuntimeException(String.valueOf(serverResponse.getStatus()));
			statusCode = 302;
			return null;
		case 404:
			JavaLogger.log("StatusCodeHandler| switch| 404");
			//throw new RuntimeException("404");
			statusCode = 404;
			return null;
		case 403:
			JavaLogger.log("StatusCodeHandler| switch| 403");
			//throw new RuntimeException("403");
			statusCode = 403;
			return null;
		}
		
			
		System.out.println(serverResponse.getStatus());
		return serverResponse;
	}
	
	public static String jsonErrorGenerator(){
		System.out.println("json generator| " + statusCode);
		String gson = "{";
		gson += "\"error\": " + "\"" + statusCode + "\",";
		gson += "\"message\": " + "\"" + statusErrorMessages(statusCode) + "\"";
		gson += "}";
	    
		System.out.println("jsonErrorGenerator| Gson Object| \n" + gson + "\n");
		return gson;
	}
	
	public static String statusErrorMessages(int statusCode){
		System.out.println("status Message:" + statusCode);
		switch(statusCode){
		case 302:
		case 307: return "Temporary Redirection";
		case 404: return "Requested is not found or requires authentication";
		case 403: return "Authentication is required";
		default: return "Error Unkown";
	
		}
	
	}
	
	public int getResponse(){
		return statusCode;
	}

}
