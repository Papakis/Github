package recieving.request;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import processing.request.ContributorsRequestProcessor;
import processing.request.RepositoryRequestProcessor;

@Path("/repository")
public class RepositoryReceiver {

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("{username}/{reponame}/contributors")
  public String getContributors(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
  		return ContributorsRequestProcessor.getContributors(userName, repoName);
  }
  
  
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("{username}/{reponame}")
  public String getRepository(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
  		return RepositoryRequestProcessor.getRepositoryInfo(userName, repoName);
  }

//  @GET
//  @Produces(MediaType.APPLICATION_JSON)
//  @Path("{id}")
//  public String returnJson(@PathParam("id") String id) {
//    return "<?xml version=\"1.0\"?>" + "<hello> Hello repository| XML" + "</hello>";
//  }

  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("{username}/{reponame}")
  public String sayHtmlHello(@PathParam("username") String userName, @PathParam("reponame") String repoName) {
	  return "<html> " + "<title>" + "Repository " +repoName+ "</title>"+ "<body><h3>"+
			  RepositoryRequestProcessor.getRepositoryInfo(userName, repoName)
			 + "</h3>" + "</body>" + "</html> ";
//	  DummyProcessing.dummyProcess(id);
//    return "<html> " + "<title>" + "Repository" + "</title>"
//        + "<body><h1>" + "repository| HTML" + "<h2>" + "repoistory| " + id + "</h2>" + "</body></h1>" + "</html> ";
  }

} 