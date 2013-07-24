package recieving.request;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import processing.request.DummyProcessing;
import processing.request.UserRequestProcessor;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/user")
public class UserReceiver {

	@GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("{username}")
  public String sayPlainTextHello(@PathParam("username") String userName) {
    return UserRequestProcessor.getUser(userName);
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