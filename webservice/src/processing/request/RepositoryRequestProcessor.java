package processing.request;

import java.util.List;

import processing.response.deserialize.ContributorsDeserializer;
import processing.response.model.Author;
import api.communication.RequestSender;

public class RepositoryRequestProcessor {
	
	private static final String API_URL = "https://api.github.com/";
	private static final String REPOS = "repos/";
	private static final String CONTRIBUTORS = "/stats/contributors";

	public static String getRepositoryInfo(String userName, String repoName){
		String request=API_URL+REPOS+userName+"/"+repoName+CONTRIBUTORS;
		String serverResponse=RequestSender.sendRequest(request);
		ContributorsDeserializer deserializer=new ContributorsDeserializer(serverResponse);
		List<Author> authors=deserializer.getAuthors();
		StringBuffer sb=new StringBuffer();
		for (Author author : authors) {
			sb.append(author.toString());
		}
		return sb.toString();
	}

}
