package api.communication;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
<<<<<<< HEAD
=======
import javax.xml.bind.DatatypeConverter;

import processing.request.URL;
>>>>>>> upstream/master

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
<<<<<<< HEAD

public class RequestSender {
	
	public static String sendRequest(String request){
		
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(UriBuilder.fromUri(request).build());
	    
	    ClientResponse response = service.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
//	    System.out.println(response.getHeaders());
		//TODO: code number checking, 202,404 etc
	    return response.getEntity(String.class);
=======
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.Base64;

public class RequestSender {
	
	public static ClientResponse sendRequest(String request){
		String login=""; String pwd="";
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    
	    HTTPBasicAuthFilter authFilter = new HTTPBasicAuthFilter(login, pwd);
	    client.addFilter(authFilter);
	    
	    WebResource service = client.resource(UriBuilder.fromUri(request).build());

	    ClientResponse response = service.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
//	    System.out.println(response.getHeaders());
		//TODO: code number checking, 202,404 etc
//	    System.out.println(response.getEntity(String.class));
	    return response;
//	    return response.getEntity(String.class);
>>>>>>> upstream/master
	}

}
