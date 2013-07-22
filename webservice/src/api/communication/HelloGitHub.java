package api.communication;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import processing.response.deserialize.Deserializer;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class HelloGitHub {
	public static void main(String[] args) {
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
	    getRepoStats();
	  }

	  private static void getRepoStats() {
		  ClientConfig config = new DefaultClientConfig();
		    Client client = Client.create(config);
		    WebResource service = client.resource(UriBuilder.fromUri("https://api.github.com").build());
		    
		    ClientResponse response = service.path("repos/Papakis/Github/stats/contributors")
		    		.accept(MediaType.APPLICATION_JSON)
		    		.get(ClientResponse.class);
//		    System.out.println(response.getHeaders());
//		    System.out.println(response.getEntity(String.class));
		    Deserializer deserializer=new Deserializer(response.getEntity(String.class));
		
	}

	private static void pitupitu() {
		  ClientConfig config = new DefaultClientConfig();
		    Client client = Client.create(config);
		    WebResource service = client.resource(UriBuilder.fromUri("https://api.github.com").build());
		    
		    ClientResponse response = service.path("users/michalbrz/repos")
		    		.accept(MediaType.APPLICATION_JSON)
		    		.get(ClientResponse.class);
		    System.out.println(response.getHeaders());
		    System.out.println(response.getEntity(String.class));
		    
//		    System.out.println(service.path("/users/defunkt").accept(MediaType.APPLICATION_JSON).get(String.class));
	}

	private static void authentication(WebResource service) {
		  ClientResponse response=service.path("michalbrz?client_id=michalbrz&client_secret=secret").
					 accept(MediaType.APPLICATION_JSON).
					 header("User-Agent", "Awesome-Octocat-App").
					 get(ClientResponse.class);
//		  System.out.println(response.getEntity(String.class));
//		  System.out.println(response.getHeaders());
//		  System.out.println(response.getStatus());
	}

	private static URI getBaseURI() {
	    return UriBuilder.fromUri("https://api.github.com/users").build();
	  }

}
