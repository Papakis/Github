package recieving.request;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import processing.request.UserRequestProcessor;

@Path("/user")
public class UserReceiver {

	@GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("{username}")
  public String sayPlainTextHello(@PathParam("username") String userName) {
    try {
		return UserRequestProcessor.getUser(userName);
	} catch (RuntimeException e) {
		throw e;
//		return "EXCEPTION!!!11111111    "+e.getStackTrace();
	}
  }

//  @GET
//  @Produces(MediaType.TEXT_HTML)
//  @Path("{id}")
//  public String sayHtmlHello(@PathParam("id") String id) {
//	  //System.out.println("HTML| I have recived i| " + id);
//	  DummyProcessing.dummyProcess(id);
//	  return "<html> " + "<title>" + "student" + "</title>"
//      + "<body><h1>" + "Student| HTML" + "<h2>" + "student| " + id + "</h2>" + "</body></h1>" + "</html> ";
//  }

} 