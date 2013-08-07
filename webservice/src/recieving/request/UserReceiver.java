package recieving.request;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import loggin.JavaLogger;
import processing.request.DummyProcessing;
import processing.request.UserRequestProcessor;

@Path("/user")
public class UserReceiver {
	
	public Response wrapResponse(String entity){
		return Response.ok(entity).header("Access-Control-Allow-Origin", "*").build();
	}

	@GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("{username}")
  public Response getJsonUser(@PathParam("username") String userName) {
    try {
    	JavaLogger.log("UserReceiver| Name| " + userName);
    	String entity=UserRequestProcessor.getUser(userName);
		return wrapResponse(entity);
	} catch (RuntimeException e) {
    	JavaLogger.log("UserReceiver| Exception| " + e.getStackTrace());
		//throw e;
		return wrapResponse("EXCEPTION!!!   "+ e.getStackTrace());
	}
  }


  /*@GET
  @Produces(MediaType.TEXT_HTML)
  @Path("{id}")
  public String sayHtmlHello(@PathParam("id") String id) {
	  //System.out.println("HTML| I have recived i| " + id);
	  DummyProcessing.dummyProcess(id);
	  return "<html> " + "<title>" + "student" + "</title>"
      + "<body><h1>" + "Student| HTML" + "<h2>" + "student| " + id + "</h2>" + "</body></h1>" + "</html> ";
  }*/

} 