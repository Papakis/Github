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
			throw new RuntimeException(String.valueOf(serverResponse.getStatus()));
		case 404:
			JavaLogger.log("StatusCodeHandler| switch| 404");
			throw new RuntimeException("404");
		case 403:
			JavaLogger.log("StatusCodeHandler| switch| 403");
			throw new RuntimeException("403");
		}
		
			
		System.out.println(serverResponse.getStatus());
		return serverResponse;
	}
	

}
