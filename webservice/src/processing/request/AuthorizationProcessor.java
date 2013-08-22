package processing.request;

import java.util.UUID;

import javax.ws.rs.core.Response;

import loggin.JavaLogger;
import processing.response.deserialize.UserDeserializerHelper;
import processing.response.model.Authorization;
import processing.response.model.User;
import security.TokenSecurity;
import api.communication.RequestSender;
import aspects.StatusCodeHandler;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import database.DAO;

public class AuthorizationProcessor {
	
	public static final String CLIENT_ID="7a829cc3e6e3beb72c00";
	public static final String CLIENT_SECRET="8cf0259157375910d92d9d971bf5685d06d45bab";
	
	public static String authorizeUser(String username, String password, boolean createCookie){
		
		
		String token=DAO.getUserToken(username, password);
		if(token!=null){
			System.out.println("token found in database");
			if(createCookie){
				String cookie=UUID.randomUUID().toString();
				DAO.insertCookie(username,cookie);
			}
			return new Gson().toJson("login successful");
		}
		
		String request=URL.API+URL.AUTHORIZATION;
		JavaLogger.log("AuthorizationProcessor| authorizeUser| User| " + username + " URL| " + request);
		
		String input="{\"scopes\": [\"repo\"],"
	    		+ "\"client_id\":\""+CLIENT_ID+"\","
	    		+ "\"client_secret\":\""+CLIENT_SECRET+"\"}";
		
		ClientResponse serverResponse=RequestSender.authorize(request, input, username, password);
		
		if(serverResponse != null){
			JavaLogger.log("AuthorizationProcessor| authorizeUser| ServerResponse| " + serverResponse);
			Gson gson=new Gson();
			Authorization authorization=gson.fromJson(serverResponse.getEntity(String.class), Authorization.class);
			
			String newToken=authorization.getToken();
			DAO.insertUser(username, password, newToken);
		
			return gson.toJson(TokenSecurity.encrypt(newToken));
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
			if(request.contains("?")) request=request.concat("&access_token="+TokenSecurity.decrypt(token));
			else request=request.concat("?access_token="+TokenSecurity.decrypt(token));
			return request;
		}
	}

}
