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

@Path("/user")
public class UserReceiver {
	
	public Response wrapResponse(String entity){
		return Response.ok(entity).header("Access-Control-Allow-Origin", "*").build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{username}")
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
	@Path("{username}/commits")
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
	@Path("authorization")
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
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("db")
	public Response databaseTest() {  // TESTING PURPOSE ONLY
		DatabaseHelper.startDatabase();
//		DatabaseHelper.insertStuff();
//		DatabaseHelper.getStuff();
		String username="michalbrz";
		String password = "pwd";
		String token = "token123123";
		DAO.insertUser(username, password, token);
		System.out.println(DAO.getUserCookie(username));
		System.out.println(DAO.getUserToken(username, password));
		return wrapResponse("DF");
	}

} 