package processing.request;

import java.rmi.AccessException;
import java.util.List;

import com.google.gson.Gson;

import processing.response.deserialize.ContributorsDeserializer;
import processing.response.deserialize.UserDeserializerHelper;
import processing.response.model.User;
import api.communication.RequestSender;

public class UserRequestProcessor {

	public static String getUser(String userName) throws RuntimeException{
		String request=URL.API+URL.USERS+userName;
		String serverResponse=RequestSender.sendRequest(request).getEntity(String.class);
		Gson gson=new Gson();
		UserDeserializerHelper deserializedUser=gson.fromJson(serverResponse, UserDeserializerHelper.class);
		User user=deserializedUser.toGenuineUser();
		return user.toString();
	}

}
