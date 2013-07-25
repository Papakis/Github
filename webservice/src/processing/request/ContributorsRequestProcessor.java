package processing.request;

import java.util.List;

import processing.response.deserialize.ContributorsDeserializer;
import processing.response.model.Contributor;
import processing.response.model.User;
import api.communication.RequestSender;

public class ContributorsRequestProcessor {
	
	public static String getContributors(String userName, String repoName){
		String request=URL.API+URL.REPOS+userName+"/"+repoName+URL.CONTRIBUTORS;
		String serverResponse=RequestSender.sendRequest(request).getEntity(String.class);
		ContributorsDeserializer deserializer=new ContributorsDeserializer(serverResponse);
		List<Contributor> contributors=deserializer.getContributors();
		StringBuffer sb=new StringBuffer();
		for (Contributor contributor : contributors) {
			sb.append(contributor.toString());
		}
		return sb.toString();
	}

}
