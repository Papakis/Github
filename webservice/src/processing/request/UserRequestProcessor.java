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
		System.out.println("CHECKPOINT");
		User user=deserializedUser.toGenuineUser();
		System.out.println("genuine?");
//		ContributorsDeserializer deserializer=new ContributorsDeserializer(serverResponse);
//		List<Author> authors=deserializer.getAuthors();
//		StringBuffer sb=new StringBuffer();
		return user.toString();
	}

}
