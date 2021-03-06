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
import processing.request.AuthorizationProcessor;
import processing.request.UserRequestProcessor;
import database.DAO;
import database.DatabaseHelper;

/**
 * Handles all user connected REST request
 *
 */
@Path("/user")
public class UserReceiver {
	
	public Response wrapResponse(String entity){ //to get JSON from outer server, this header must be added
		return Response.ok(entity).header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}") // user info
	public Response getJsonUser(@PathParam("username") String userName, @FormParam("token") String token) {
	    try {
	    	JavaLogger.log("UserReceiver| Name| " + userName);
	    	String entity=UserRequestProcessor.getUser(userName, token);
			return wrapResponse(entity);
		} catch (RuntimeException e) {
	    	JavaLogger.log("UserReceiver| Exception| " + e.getMessage());
			return wrapResponse("EXCEPTION!!!   "+ e.getStackTrace());
		}
  }
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}/commits") //user commits to given repository
	public Response getJsonUserCommits(@PathParam("username") String userName, @FormParam("token") String token,
			@FormParam("repoName") String repoName, @FormParam("owner") String owner) {
	    try {
	    	JavaLogger.log("UserReceiver| Name| " + userName);
	    	String entity=UserRequestProcessor.getUserCommits(userName, repoName, owner, token);
			return wrapResponse(entity);
		} catch (RuntimeException e) {
	    	JavaLogger.log("UserReceiver| Exception| " + e.getMessage());
			return wrapResponse("EXCEPTION!!!   "+ e.getStackTrace());
		}
  }
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("authorization") //user authorization
	public Response authorizeUser(@FormParam("username") String userName, @FormParam("password") String password) {
		try {
			JavaLogger.log("UserReceiver| authorizeUser |  Name| " + userName);
			String entity=AuthorizationProcessor.authorizeUser(userName, password);
			return wrapResponse(entity);
		} catch (RuntimeException e) {
			JavaLogger.log("UserReceiver| authorizeUser | Exception| " + e.getMessage());
			e.printStackTrace();
			return wrapResponse("EXCEPTION!!!   "+ e.getStackTrace());
		}
	}
	
} 