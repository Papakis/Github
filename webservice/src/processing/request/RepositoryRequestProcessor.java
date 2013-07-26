package processing.request;

import java.util.List;

import com.google.gson.Gson;

import processing.response.deserialize.ContributorsDeserializer;
import processing.response.deserialize.RepositoryDeseralizerHelper;
import processing.response.model.Repository;
import processing.response.model.User;
import api.communication.RequestSender;

public class RepositoryRequestProcessor {
	
	public static String getRepositoryInfo(String userName, String repoName){
		String request=URL.API+URL.REPOS+userName+"/"+repoName;
<<<<<<< HEAD
		String serverResponse=RequestSender.sendRequest(request);
=======
		String serverResponse=RequestSender.sendRequest(request).getEntity(String.class);
>>>>>>> upstream/master
		
		Gson gson=new Gson();
		
		RepositoryDeseralizerHelper deserializer=gson.fromJson(serverResponse, RepositoryDeseralizerHelper.class);
		Repository repo=deserializer.toGenuineRepository();
		return repo.toString();
	}

}
