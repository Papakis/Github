package processing.request;

import java.util.List;

import loggin.JavaLogger;

import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

import processing.response.deserialize.ContributorsDeserializer;
import processing.response.deserialize.UserDeserializerHelper;
import processing.response.model.User;
import api.communication.RequestSender;

public class UserRequestProcessor {

	public static String getUser(String userName){
		//System.out.println("Request Processor| GET USER| " + userName);
		String request=URL.API+URL.USERS+userName;
		JavaLogger.log("UserRequestProcessor| getUser| User| " + userName + " URL| " + request);
		ClientResponse serverResponse= RequestSender.sendRequest(request);
		//System.out.println(serverResponse);
		JavaLogger.log("UserRequestProcessor| getUser| ServerResponse| " + serverResponse);
		Gson gson=new Gson();
		
		UserDeserializerHelper deserializedUser = gson.fromJson(serverResponse.getEntity(String.class), UserDeserializerHelper.class);
		User user = deserializedUser.toGenuineUser();
		

		return user.toString();
	}

}
