package recieving.request;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Plain old Java Object it does not extend as class or implements 
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation. 
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML. 

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/repository")
public class Repository {

  // This method is called if TEXT_PLAIN is request
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  @Path("{id}")
  public String sayPlainTextHello(@PathParam("id") String id) {
    return "Hello repository|" + id + "PLAIN";
  }

  // This method is called if XML is request
  @GET
  @Produces(MediaType.TEXT_XML)
  @Path("{id}")
  public String sayXMLHello(@PathParam("id") String id) {
    return "<?xml version=\"1.0\"?>" + "<hello> Hello repository| XML" + "</hello>";
  }

  // This method is called if HTML is request
  @GET
  @Produces(MediaType.TEXT_HTML)
  @Path("{id}")
  public String sayHtmlHello(@PathParam("id") String id) {
	  System.out.println("HTML| I have recived i| " + id);
    return "<html> " + "<title>" + "Repository" + "</title>"
        + "<body><h1>" + "repository| HTML" + "<h2>" + "repoistory| " + id + "</h2>" + "</body></h1>" + "</html> ";
  }

} 