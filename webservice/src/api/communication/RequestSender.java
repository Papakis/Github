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
		System.out.println(request);
		//System.out.println("Request Sender| send Request| " + request);
		JavaLogger.log("RequestSender| sendRequest| Url| " + request);
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
//	    client.addFilter(new HTTPBasicAuthFilter("abd-shouman", "real4ever"));
	    WebResource service = client.resource(UriBuilder.fromUri(request).build());
	    
	    ClientResponse response = service.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
	    System.out.println(response.getHeaders());
	    System.out.println(response.getHeaders().getFirst("X-RateLimit-Remaining"));
//	    System.out.println(response.getEntity(String.class));
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
	
//	public static void sendRequest(Client client){
//		String request="https://api.github.com/authorizations";
//		ClientConfig config = new DefaultClientConfig();
//	    Client client2 = Client.create(config);
//	    client2.addFilter(new HTTPBasicAuthFilter("abd-shouman", "real4ever"));
//	    WebResource service = client2.resource(UriBuilder.fromUri(request).build());
//	    String input="{\"scopes\": [\"repo\"],"
//	    		+ "\"client_id\":\"7a829cc3e6e3beb72c00\","
//	    		+ "\"client_secret\":\"8cf0259157375910d92d9d971bf5685d06d45bab\"}";
//	    System.out.println(input);
//	    ClientResponse response = service.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, input);
//	    System.out.println(response.getEntity(String.class));
//	    
//	    
//		
//	}

}
