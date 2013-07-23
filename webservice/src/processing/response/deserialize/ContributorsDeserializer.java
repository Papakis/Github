package processing.response.deserialize;

import java.util.ArrayList;
import java.util.List;

import processing.response.model.Author;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ContributorsDeserializer {
	
	private List<Author> authors=new ArrayList<Author>();
	
	public ContributorsDeserializer(String jsonData){
		
		Gson gson=new Gson();
		List<ContributorsDeserializerHelper> deserializedList=gson.fromJson(jsonData, new TypeToken<List<ContributorsDeserializerHelper>>(){}.getType());
		
		for (ContributorsDeserializerHelper deserializedItem : deserializedList) {
			authors.add(deserializedItem.toAuthor());
		}
		System.out.println(authors.size());
		for (Author author : authors) {
			System.out.println(author.toString());
		}
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
//	class ContributorsDeserializer implements JsonDeserializer<Author>{
//
//		@Override
//		public Author deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
//				throws JsonParseException {
//			Gson gson=new Gson();
//			
//			JsonObject jo=(JsonObject)json;
//			
//			Author author=new Author();
//			JsonElement je=jo.get("author");
//			author=gson.fromJson(je.toString(), Author.class);
//			
//			
//			JsonElement je2=jo.get("weeks");
//			author.setWeeks(gson.fromJson(je2, Week[].class));
//			
//			System.out.println(author.toString());
//			
//			return null;
//			
//		}
//		
//	}
//	
}
