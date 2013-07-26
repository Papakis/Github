package processing.request;

<<<<<<< HEAD
=======
import java.rmi.AccessException;
>>>>>>> upstream/master
import java.util.List;

import com.google.gson.Gson;

import processing.response.deserialize.ContributorsDeserializer;
<<<<<<< HEAD
=======
import processing.response.deserialize.UserDeserializerHelper;
>>>>>>> upstream/master
import processing.response.model.User;
import api.communication.RequestSender;

public class UserRequestProcessor {

<<<<<<< HEAD
	public static String getUser(String userName){
		String request=URL.API+URL.USERS+userName;
		String serverResponse=RequestSender.sendRequest(request);
		System.out.println(serverResponse);
		Gson gson=new Gson();
		User user=gson.fromJson(serverResponse, User.class);
		
		
=======
	public static String getUser(String userName) throws RuntimeException{
		String request=URL.API+URL.USERS+userName;
		String serverResponse=RequestSender.sendRequest(request).getEntity(String.class);
		Gson gson=new Gson();
		UserDeserializerHelper deserializedUser=gson.fromJson(serverResponse, UserDeserializerHelper.class);
		System.out.println("CHECKPOINT");
		User user=deserializedUser.toGenuineUser();
		System.out.println("genuine?");
>>>>>>> upstream/master
//		ContributorsDeserializer deserializer=new ContributorsDeserializer(serverResponse);
//		List<Author> authors=deserializer.getAuthors();
//		StringBuffer sb=new StringBuffer();
		return user.toString();
	}

}
