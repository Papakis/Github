package processing.request;

import java.util.List;

import com.google.gson.Gson;

import processing.response.deserialize.ContributorsDeserializer;
import processing.response.model.User;
import api.communication.RequestSender;

public class UserRequestProcessor {

	public static String getUser(String userName){
		String request=URL.API+URL.USERS+userName;
		String serverResponse=RequestSender.sendRequest(request);
		System.out.println(serverResponse);
		Gson gson=new Gson();
		User user=gson.fromJson(serverResponse, User.class);
		
		
//		ContributorsDeserializer deserializer=new ContributorsDeserializer(serverResponse);
//		List<Author> authors=deserializer.getAuthors();
//		StringBuffer sb=new StringBuffer();
		return user.toString();
	}

}
