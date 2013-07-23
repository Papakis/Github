package api.communication;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class RequestSender {
	
	public static String sendRequest(String request){
		
		ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(UriBuilder.fromUri(request).build());
	    
	    ClientResponse response = service.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
//	    System.out.println(response.getHeaders());
		//TODO: code number checking, 202,404 etc
	    return response.getEntity(String.class);
	}

}
