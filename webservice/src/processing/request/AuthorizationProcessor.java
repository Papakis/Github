package processing.request;

import loggin.JavaLogger;
import processing.response.model.Authorization;
import security.TokenSecurity;
import api.communication.RequestSender;
import aspects.StatusCodeHandler;

import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

import database.DAO;

public class AuthorizationProcessor {
	
	public static final String CLIENT_ID="7a829cc3e6e3beb72c00";
	public static final String CLIENT_SECRET="8cf0259157375910d92d9d971bf5685d06d45bab";
	
	public static String authorizeUser(String username, String password){
		
		
		String token=DAO.getUserToken(username, password);
		if(token!=null){
			System.out.println("token found in database");
			return new Gson().toJson(TokenSecurity.encrypt(token));
		}
		
		String request=URL.API+URL.AUTHORIZATION;
		JavaLogger.log("AuthorizationProcessor| authorizeUser| User| " + username + " URL| " + request);
		
		String input="{\"scopes\": [\"repo\"],"
	    		+ "\"client_id\":\""+CLIENT_ID+"\","
	    		+ "\"client_secret\":\""+CLIENT_SECRET+"\"}";
		
		ClientResponse serverResponse=RequestSender.authorize(request, input, username, password);
		
		if(serverResponse == null){
			System.out.println("AuthorizationProcessor| authorizeUser|Error|");
			return StatusCodeHandler.jsonErrorGenerator();
		}
		
		JavaLogger.log("AuthorizationProcessor| authorizeUser| ServerResponse| " + serverResponse);
		Gson gson=new Gson();
		Authorization authorization=gson.fromJson(serverResponse.getEntity(String.class), Authorization.class);
		
		String newToken=authorization.getToken();
		DAO.insertUser(username, password, newToken);
	
		return gson.toJson(TokenSecurity.encrypt(newToken));
	}

	public static String addAuthorization(String request, String token) {
		if(token==null){
			return request;
		}
		else{
			if(request.contains("?")) {
				System.out.println("TOKEN"+token);
				request=request.concat("&access_token="+TokenSecurity.decrypt(token));
			}
			else request=request.concat("?access_token="+TokenSecurity.decrypt(token));
			System.out.println(request);
			return request;
		}
	}

}
