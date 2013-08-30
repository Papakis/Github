package processing.request;

import java.util.ArrayList;
import java.util.List;

import loggin.JavaLogger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;

import processing.response.deserialize.CommitDeserializerHelper;
import processing.response.deserialize.ContributorsDeserializer;
import processing.response.deserialize.UserDeserializerHelper;
import processing.response.model.Commits;
import processing.response.model.Contributor;
import processing.response.model.Repository;
import processing.response.model.User;
import recieving.request.RepositoryReceiver;
import api.communication.RequestSender;
import aspects.StatusCodeHandler;

/**
 * Proccesses every request connected with user. Creates request and when gets it from RequestSender, creates proper JSON response
 *
 */
public class UserRequestProcessor {

	public static String getUser(String userName, String token){
		String request=URL.API+URL.USERS+userName;
		JavaLogger.log("UserRequestProcessor| getUser| User| " + userName + " URL| " + request);
		
		request=AuthorizationProcessor.addAuthorization(request, token);
		ClientResponse serverResponse=RequestSender.sendRequest(request);
		
		if(serverResponse == null){
			System.out.println("UserRequestProcessor|getUser|Error|");
			return StatusCodeHandler.jsonErrorGenerator();
		}
		
		JavaLogger.log("UserRequestProcessor| getUser| ServerResponse| " + serverResponse);
		Gson gson=new Gson();
		
		UserDeserializerHelper deserializedUser = gson.fromJson(serverResponse.getEntity(String.class), UserDeserializerHelper.class);
		User user = deserializedUser.toGenuineUser();
		
		return gson.toJson(user);
	}

	public static String getUserCommits(String userName, String repoName,String owner, String token) {
		
		String request=URL.API+URL.REPOS+owner+"/"+repoName+URL.COMMITS+"?author="+userName;
		JavaLogger.log("ContributorsRequestProcessor| getContributors| User| " + userName + " Repo| " + repoName);
		
		request=AuthorizationProcessor.addAuthorization(request, token);
		ClientResponse serverResponse = RequestSender.sendRequest(request);
		
		if(serverResponse == null){
			System.out.println("UserRequestProcessor|getUserCommits|Error|");
			return StatusCodeHandler.jsonErrorGenerator();
		}
		Gson gson=new Gson();
		List<CommitDeserializerHelper> deserializedCommits=gson.fromJson(serverResponse.getEntity(String.class), new TypeToken<List<CommitDeserializerHelper>>(){}.getType());
		List<String[]> tempCommits=new ArrayList<String[]>();
		
		for (CommitDeserializerHelper commit : deserializedCommits) {
			String date=commit.getCommit().getAuthor().getDate();
			String day,time;
			String[] dayTime=date.substring(0, date.length()-1).split("T");
			day=dayTime[0];
			time=dayTime[1];
			tempCommits.add(new String[]{
					day,
					time,
					Integer.toString(commit.getCommit().getMessage().length()),
					commit.getCommit().getMessage()});
		}
		
		Commits commits=new Commits();
		commits.setCommitMessages(tempCommits);
		
		return gson.toJson(commits);
	}

}
