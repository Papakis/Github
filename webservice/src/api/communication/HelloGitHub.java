package api.communication;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientRequest;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class HelloGitHub {
	public static void main(String[] args) {
	    ClientConfig config = new DefaultClientConfig();
	    Client client = Client.create(config);
	    WebResource service = client.resource(getBaseURI());
//	    test1(service);
//	    test2(service);
	    pitupitu();
//	    authentication(service);
//some change
	  }

	  private static void pitupitu() {
		  ClientConfig config = new DefaultClientConfig();
		    Client client = Client.create(config);
		    WebResource service = client.resource(UriBuilder.fromUri("https://api.github.com").build());
		    
		    ClientResponse response = service.path("/users/defunkt?client_id=michalbrz&client_secret=kieken7")
		    		.accept(MediaType.APPLICATION_JSON)
		    		.get(ClientResponse.class);
//		    ClientResponse response = service.path("/users/defunkt")
//		    		.accept(MediaType.APPLICATION_JSON)
//		    		.get(ClientResponse.class);
		    System.out.println(response.getHeaders());
		    System.out.println(response.getEntity(String.class));
		    
//		    System.out.println(service.path("/users/defunkt").accept(MediaType.APPLICATION_JSON).get(String.class));
	}

	private static void authentication(WebResource service) {
		  ClientResponse response=service.path("michalbrz?client_id=michalbrz&client_secret=kieken7").
					 accept(MediaType.APPLICATION_JSON).
					 header("User-Agent", "Awesome-Octocat-App").
					 get(ClientResponse.class);
//		  System.out.println(response.getEntity(String.class));
//		  System.out.println(response.getHeaders());
//		  System.out.println(response.getStatus());
	}

	private static void test1(WebResource service) {
		 ClientResponse response=service.path("octocat").
				 path("orgs").
				 accept(MediaType.APPLICATION_JSON).
				 header("User-Agent", "Awesome-Octocat-App").
				 get(ClientResponse.class);
//		  ClientResponse response=service.get(ClientResponse.class);
		  System.out.println(response.getEntity(String.class));
		  System.out.println(response.getHeaders());
		  System.out.println(response.getStatus());
	}

	private static void test2(WebResource service) {
		service.setProperty("User-Agent", "Awesome-Octocat-App");
		System.out.println("test2");
		System.out.println(service.path("octocat").path("orgs").accept(MediaType.APPLICATION_JSON).get(String.class));
		
//		service.path("octocat").path("orgs").accept(MediaType.APPLICATION_JSON).header("User-Agent", "Awesome-Octocat-App");
//		  System.out.println(service.get(String.class));
	}

	private static URI getBaseURI() {
	    return UriBuilder.fromUri("https://api.github.com/users").build();
	  }

}
