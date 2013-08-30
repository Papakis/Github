package recieving.request;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import loggin.JavaLogger;

/**
 * Displays placeholder webpage
 *
 */
@Path("/")
public class Welcome {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public static String welcome() {
		JavaLogger.log("Welcome| welcome|");
		return "<html> " + "<title>" + "Welcome" + "</title>"
	      + "<body><h1>" + "Hi, welcome to our Project" + "</body></h1>" + "</html> ";
	}

}
