package processing.request;

import javax.ws.rs.core.Response;

import loggin.JavaLogger;
import processing.response.deserialize.UserDeserializerHelper;
import processing.response.model.Authorization;
import processing.response.model.User;
import api.communication.RequestSender;
import aspects.StatusCodeHandler;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

public class AuthorizationProcessor {
	
	public static final String CLIENT_ID="7a829cc3e6e3beb72c00";
	public static final String CLIENT_SECRET="8cf0259157375910d92d9d971bf5685d06d45bab";
	
	public static String authorizeUser(String userName, String password){
		String request=URL.API+URL.AUTHORIZATION;
		JavaLogger.log("AuthorizationProcessor| authorizeUser| User| " + userName + " URL| " + request);
		
		String input="{\"scopes\": [\"repo\"],"
	    		+ "\"client_id\":\""+CLIENT_ID+"\","
	    		+ "\"client_secret\":\""+CLIENT_SECRET+"\"}";
		
		ClientResponse serverResponse=RequestSender.authorize(request, input, userName, password);
		
		if(serverResponse != null){
			JavaLogger.log("AuthorizationProcessor| authorizeUser| ServerResponse| " + serverResponse);
			Gson gson=new Gson();
			Authorization authorization=gson.fromJson(serverResponse.getEntity(String.class), Authorization.class);
		
			return gson.toJson(authorization.getToken());
		}
		else
		{
			System.out.println("AuthorizationProcessor| authorizeUser|Error|");
			return StatusCodeHandler.jsonErrorGenerator();
		}
	}

	public static String addAuthorization(String request, String token) {
		if(token==null){
			return request;
		}
		else{
			if(request.contains("?")) request=request.concat("&access_token="+token);
			else request=request.concat("?access_token="+token);
			return request;
		}
	}

}
