/*package aspectj;

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


}*/

package aspectj;

import loggin.JavaLogger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;

import com.sun.jersey.api.client.ClientResponse;
 
@Aspect
public class StatusCodeHandler { //Wired name but lets stick to what michel said
 
   @Around("call(ClientResponse api.communication.*.sendRequest(..))")
   public ClientResponse logAround(ProceedingJoinPoint joinPoint) throws Throwable {
 
	//System.out.println("logAround() is running!");
	//System.out.println("Around before procceding ..");
	JavaLogger.log("StatusCodeHandler| logAround| Before_Proceeding");
	ClientResponse serverResponse = (ClientResponse) joinPoint.proceed(); //Return the result of proceeding the joinpoint function
	JavaLogger.log("StatusCodeHandler| logAround| After_Proceeding| Status|" + serverResponse.getStatus());

//	switch(serverResponse.getStatus()){
//	case 404: throw new RuntimeException("404");
//	default: System.out.println("How did u come here");
//	}
	
	//System.out.println("Around after procceding");
	//System.out.println("Status| " + serverResponse.getStatus());
	
	return serverResponse;
  }
 
}

/*
public aspect TestAspectJ {
	pointcut sendingRequest():
		call(String api.communication.RequestSender.sendRequest(*));
	pointcut processingRequest():
		call(String processing.request.UserRequestProcessor.getUser(*));
	pointcut reciingResponse():
		call(String recieving.request.UserReceiver.getUser(*));
}
*/
//after() : sendingRequest() {
		//System.out.println("Demo Aspect Pointcut - after sending request ...");