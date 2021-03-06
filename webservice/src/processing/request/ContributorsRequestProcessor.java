package processing.request;

import java.util.List;

import loggin.JavaLogger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.ClientResponse;

import processing.response.deserialize.ContributorsDeserializer;
import processing.response.model.Contributor;
import processing.response.model.User;
import api.communication.RequestSender;
import aspects.StatusCodeHandler;

/**
 * Proccesses contributors request. Creates request and when gets it from RequestSender, creates proper JSON response
 *
 */
public class ContributorsRequestProcessor {
	
	public static String getContributors(String userName, String repoName, String token){
		String request=URL.API+URL.REPOS+userName+"/"+repoName+URL.CONTRIBUTORS+URL.PER_PAGE;
		JavaLogger.log("ContributorsRequestProcessor| getContributors| User| " + userName + " Repo| " + repoName);
		
		request=AuthorizationProcessor.addAuthorization(request, token);
		
		ClientResponse serverResponse = RequestSender.sendRequest(request);
		
		if(serverResponse == null){
			System.out.println("ContributorsRequestProcessor|getContributors|Error|");
			return StatusCodeHandler.jsonErrorGenerator();
		}
		ContributorsDeserializer deserializer=new ContributorsDeserializer(serverResponse.getEntity(String.class));
		List<Contributor> contributors=deserializer.getContributors();
		
		Gson gson=new Gson();
		return gson.toJson(contributors, new TypeToken<List<Contributor>>(){}.getType());
	}

}
