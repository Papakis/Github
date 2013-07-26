package aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import com.sun.jersey.api.client.ClientResponse;

@Aspect
public class StatusCodeHandler {
	
	@Around("call (ClientResponse api.communication.*.sendRequest(..))")
	public ClientResponse doSomethign(ProceedingJoinPoint joinPoint) throws Throwable{
		ClientResponse serverResponse=(ClientResponse) joinPoint.proceed();
//		System.out.println(serverResponse.getEntity(String.class));
		switch (serverResponse.getStatus()) {
		case 404:
			throw new RuntimeException("404");
//		case 403:
//			throw new RuntimeException("403");
		default:
			System.out.println("defaultss");
		}
			
		System.out.println(serverResponse.getStatus());
//		System.out.println(serverResponse.getEntity(String.class));
		return serverResponse;
	}
//	@Before("execution(* api.communication.*.sendRequest(..))")
//	public void printsth(){
//		System.out.println("aftersending");
//	}
	

}
