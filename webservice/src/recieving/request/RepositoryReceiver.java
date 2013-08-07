package recieving.request;

import javax.ws.rs.GET;
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

//  @GET
//  @Produces(MediaType.TEXT_PLAIN)
//  @Path("{username}/{reponame}/contributors")
//  public String getContributors(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
//	  	JavaLogger.log("RepositoryReceiver| getContributors| User| " + userName + "Repo| " + repoName);
//	  	return ContributorsRequestProcessor.getContributors(userName, repoName);
//  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{username}/{reponame}/contributors")
  public Response getJsonContributors(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
	  	JavaLogger.log("RepositoryReceiver| getJsonContributors| User| " + userName + "Repo| " + repoName);
	  	String entity=ContributorsRequestProcessor.getContributors(userName, repoName);
	  	return wrapResponse(entity);
  }
  
  
//  @GET
//  @Produces(MediaType.TEXT_PLAIN)
//  @Path("{username}/{reponame}")
//  public String getRepository(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
//	  	JavaLogger.log("RepositoryReceiver| getRepository| User| " + userName + "Repo| " + repoName);
//  		return RepositoryRequestProcessor.getRepositoryInfo(userName, repoName);
//  }
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{username}/{reponame}")
  public Response getJsonRepository(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
	  JavaLogger.log("RepositoryReceiver| getJsonRepository| User| " + userName + "Repo| " + repoName);
	  String entity= RepositoryRequestProcessor.getRepositoryInfo(userName, repoName);
	  return wrapResponse(entity);
  }

//  @GET
//  @Produces(MediaType.TEXT_PLAIN)
//  @Path("{username}/{reponame}/commits_distribution")
//  public String getRepositoryCommitsDistribution(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
//	  JavaLogger.log("RepositoryReceiver| getRepositoryCommitsDistribution| User| " + userName + "Repo| " + repoName);
//	  return RepositoryRequestProcessor.getRepositoryCommitsDistribution(userName, repoName);
//  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{username}/{reponame}/commits_distribution")
  public Response getJsonRepositoryCommitsDistribution(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
	  JavaLogger.log("RepositoryReceiver| getJsonRepositoryCommitsDistribution| User| " + userName + "Repo| " + repoName);
	  String entity= RepositoryRequestProcessor.getRepositoryCommitsDistribution(userName, repoName);
	  return wrapResponse(entity);
  }

//  @GET
//  @Produces(MediaType.TEXT_PLAIN)
//  @Path("{username}/{reponame}/commit_activity")
//  public String getCommitActivity(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
//	  	JavaLogger.log("RepositoryReceiver| getCommitActivity| User| " + userName + "Repo| " + repoName);
//  		return RepositoryRequestProcessor.getCommitInfo(userName, repoName);
//  }
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{username}/{reponame}/commit_activity")
  public Response getJsonCommitActivity(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
	  	JavaLogger.log("RepositoryReceiver| getJsonCommitActivity| User| " + userName + "Repo| " + repoName);
  		String entity=  RepositoryRequestProcessor.getCommitInfo(userName, repoName);
  		return wrapResponse(entity);
  }

//  @GET
//  @Produces(MediaType.TEXT_HTML)
//  @Path("{username}/{reponame}")
//  public String sayHtmlHello(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
//	  JavaLogger.log("RepositoryReceiver| sayHtmlHello| User| " + userName + "Repo| " + repoName);
//	  return "<html> " + "<title>" + "Repository " +repoName+ "</title>"+ "<body><h3>"+
//			  RepositoryRequestProcessor.getRepositoryInfo(userName, repoName)
//			 + "</h3>" + "</body>" + "</html> ";
//	  DummyProcessing.dummyProcess(id);
//    return "<html> " + "<title>" + "Repository" + "</title>"
//        + "<body><h1>" + "repository| HTML" + "<h2>" + "repoistory| " + id + "</h2>" + "</body></h1>" + "</html> ";
//  }

} 