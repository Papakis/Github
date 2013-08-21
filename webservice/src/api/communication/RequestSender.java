package api.communication;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import loggin.JavaLogger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class RequestSender {
	
	public static ClientResponse sendRequest(String request){
		JavaLogger.log("RequestSender| sendRequest| Url| " + request);
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
//	    client.addFilter(new HTTPBasicAuthFilter("abd-shouman", "real4ever"));
	    WebResource service = client.resource(UriBuilder.fromUri(request).build());
	    
	    ClientResponse response = service.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
	    System.out.println(response.getHeaders().getFirst("X-RateLimit-Remaining"));
	    return response;
	}
	
	public static ClientResponse authorize(String request, String input, String username, String password){
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    client.addFilter(new HTTPBasicAuthFilter(username, password));
	    WebResource service = client.resource(UriBuilder.fromUri(request).build());
	    ClientResponse response = service.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, input);
	    return response;
	}

}
