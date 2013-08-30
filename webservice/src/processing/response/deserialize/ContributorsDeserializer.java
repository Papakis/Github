package processing.response.deserialize;

import java.util.ArrayList;
import java.util.List;

import processing.response.model.Contributor;
import processing.response.model.User;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Changes contributors from GitHub response to contributors from application model.
 *
 */
public class ContributorsDeserializer {
	
	private List<Contributor> contributors=new ArrayList<Contributor>();
	
	public ContributorsDeserializer(String jsonData){
		
		Gson gson=new Gson();
		List<ContributorsDeserializerHelper> deserializedList=gson.fromJson(jsonData, new TypeToken<List<ContributorsDeserializerHelper>>(){}.getType());
		
		for (ContributorsDeserializerHelper deserializedItem : deserializedList) {
			contributors.add(deserializedItem.toContributor());
		}
		System.out.println(contributors.size());
		for (Contributor contributor : contributors) {
			System.out.println(contributor.toString());
		}
	}

	public List<Contributor> getContributors() {
		return contributors;
	}

	public void setContributors (List<Contributor> contributors) {
		this.contributors = contributors;
	}
	
}
