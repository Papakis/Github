package githup.API.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;

public class TEST {
	
	public void main(String args[]){
		//Building and configuring the client
	    Client c = Client.create();
	    c.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
	    
	    //Building Web resource
	    WebResource r = c.resource("http://localhost:8080/recieving.request");
	    
	}

}
