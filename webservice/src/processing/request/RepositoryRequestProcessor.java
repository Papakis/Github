package processing.request;

import java.util.List;

import loggin.JavaLogger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;

import processing.response.deserialize.ContributorsDeserializer;
import processing.response.deserialize.ContributorsDeserializerHelper;
import processing.response.deserialize.RepositoryDeseralizerHelper;
import processing.response.model.Repository;
import processing.response.model.User;
import api.communication.RequestSender;

public class RepositoryRequestProcessor {
	
	public static String getRepositoryInfo(String userName, String repoName){
		String request=URL.API+URL.REPOS+userName+"/"+repoName;
		JavaLogger.log("RepositoryRequestProcessor| getRepositoryInfo| User| " + userName + " Repo| " + repoName + " Url| " + request);
		ClientResponse serverResponse=RequestSender.sendRequest(request);
		
		Gson gson=new Gson();
		
		RepositoryDeseralizerHelper deserializer=gson.fromJson(serverResponse.getEntity(String.class), RepositoryDeseralizerHelper.class);
		Repository repo=deserializer.toGenuineRepository();
		return repo.toString();
	}

	public static String getRepositoryCommitsDistribution(String userName,
			String repoName) {
		String request=URL.API+URL.REPOS+userName+"/"+repoName+URL.COMMITS_DISTRIBUTION;
		JavaLogger.log("RepositoryRequestProcessor| getRepositoryCommitsDistribution| User| " + userName + " Repo| " + repoName + " Url| " + request);
		ClientResponse serverResponse=RequestSender.sendRequest(request);
		
		Gson gson=new Gson();
		List<int[]> deserializedList=gson.fromJson(serverResponse.getEntity(String.class), new TypeToken<List<int[]>>(){}.getType());
		
		return deserializedList.toString();
	}

}
