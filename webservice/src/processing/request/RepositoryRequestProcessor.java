package processing.request;

import java.util.List;

import loggin.JavaLogger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;

import processing.response.deserialize.ContributorsDeserializer;
import processing.response.deserialize.ContributorsDeserializerHelper;
import processing.response.deserialize.RepositoryDeseralizerHelper;
import processing.response.model.CommitStats;
import processing.response.model.Contributor;
import processing.response.model.Repository;
import processing.response.model.User;
import api.communication.RequestSender;
import aspects.StatusCodeHandler;

/**
 * Proccesses every request connected with repository. Creates request and when gets it from RequestSender, creates proper JSON response
 *
 */
public class RepositoryRequestProcessor {
	
	public static String getRepositoryInfo(String userName, String repoName, String token){
		String request=URL.API+URL.REPOS+userName+"/"+repoName+URL.PER_PAGE;
		JavaLogger.log("RepositoryRequestProcessor| getRepositoryInfo| User| " + userName + " Repo| " + repoName + " Url| " + request);
		
		request=AuthorizationProcessor.addAuthorization(request, token);
		ClientResponse serverResponse=RequestSender.sendRequest(request);
		
		if(serverResponse == null){
			System.out.println("RepositoryRequestProcessor|getRepositoryInfo|Error|");
			return StatusCodeHandler.jsonErrorGenerator();
		}
		
		Gson gson=new Gson();
		RepositoryDeseralizerHelper deserializer=gson.fromJson(serverResponse.getEntity(String.class), RepositoryDeseralizerHelper.class);
		Repository repo=deserializer.toGenuineRepository();
		
		return gson.toJson(repo);
	}

	public static String getHourlyCommitsDistribution(String userName,String repoName, String token) {
		String request=URL.API+URL.REPOS+userName+"/"+repoName+URL.COMMITS_DISTRIBUTION;
		JavaLogger.log("RepositoryRequestProcessor| getRepositoryCommitsDistribution| User| " + userName + " Repo| " + repoName + " Url| " + request);
		
		request=AuthorizationProcessor.addAuthorization(request, token);
		ClientResponse serverResponse=RequestSender.sendRequest(request);
		
		if(serverResponse == null){
			System.out.println("RepositoryRequestProcessor|getRepositoryCommitsDistribution|Error|");
			return StatusCodeHandler.jsonErrorGenerator();
		}
		
		Gson gson=new Gson();
		List<int[]> deserializedList=gson.fromJson(serverResponse.getEntity(String.class), new TypeToken<List<int[]>>(){}.getType());
		int[][] days=new int[7][24];
		for (int[] is : deserializedList) {
				days[is[0]][is[1]]=is[2];
		}
		CommitStats comm=new CommitStats();
		comm.setCommitDistribution(days);
		
		return gson.toJson(comm.getCommitDistribution());
	}
	
	public static String getWeeklyCommitsDistribution(String userName, String repoName, String token){
		String request=URL.API+URL.REPOS+userName+"/"+repoName+URL.COMMIT_ACTIVITY;
		JavaLogger.log("RepositoryRequestProcessor| getCommitInfo| User| " + userName + " Repo| " + repoName + " Url| " + request);
		
		request=AuthorizationProcessor.addAuthorization(request, token);
		ClientResponse serverResponse=RequestSender.sendRequest(request);
		
		if(serverResponse == null){
			System.out.println("RepositoryRequestProcessor|getCommitInfo|Error|");
			return StatusCodeHandler.jsonErrorGenerator();
		}
			
		Gson gson=new Gson();
		List<CommitStats> commitsList=gson.fromJson(serverResponse.getEntity(String.class), new TypeToken<List<CommitStats>>(){}.getType());
		
		return gson.toJson(commitsList);
	}

}
