package recieving.request;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import loggin.JavaLogger;
import processing.request.ContributorsRequestProcessor;
import processing.request.RepositoryRequestProcessor;
import processing.response.model.Contributor;

import com.google.gson.Gson;

@Path("/repos")
public class RepositoryReceiver {
	
	public Response wrapResponse(String entity){
		return Response.ok(entity).header("Access-Control-Allow-Origin", "*").build();
	}

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{username}/{reponame}/contributors")
  public Response getJsonContributors(@PathParam("username") String userName,
		  @PathParam("reponame") String repoName,  @FormParam("token") String token) {
	  	JavaLogger.log("RepositoryReceiver| getJsonContributors| User| " + userName + "Repo| " + repoName);
	  	String entity=ContributorsRequestProcessor.getContributors(userName, repoName, token);
	  	return wrapResponse(entity);
  }
  
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{username}/{reponame}")
  public Response getJsonRepository(@PathParam("username") String userName, 
		  @PathParam("reponame") String repoName,  @FormParam("token") String token) {
	  JavaLogger.log("RepositoryReceiver| getJsonRepository| User| " + userName + "Repo| " + repoName);
	  String entity= RepositoryRequestProcessor.getRepositoryInfo(userName, repoName, token);
	  return wrapResponse(entity);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{username}/{reponame}/commits_distribution") //number of commits per hour in each day
  public Response getJsonRepositoryCommitsDistribution(@PathParam("username") String userName,
		  @PathParam("reponame") String repoName,  @FormParam("token") String token) {
	  JavaLogger.log("RepositoryReceiver| getJsonRepositoryCommitsDistribution| User| " + userName + "Repo| " + repoName);
	  String entity= RepositoryRequestProcessor.getHourlyCommitsDistribution(userName, repoName, token);
	  return wrapResponse(entity);
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{username}/{reponame}/commit_activity")  //number of commits per day in each week
  public Response getJsonWeeklyCommitActivity(@PathParam("username") String userName, 
		  @PathParam("reponame") String repoName,  @FormParam("token") String token) {
	  	JavaLogger.log("RepositoryReceiver| getJsonCommitActivity| User| " + userName + "Repo| " + repoName);
  		String entity=  RepositoryRequestProcessor.getWeeklyCommitsDistribution(userName, repoName, token);
  		return wrapResponse(entity);
  }

} 