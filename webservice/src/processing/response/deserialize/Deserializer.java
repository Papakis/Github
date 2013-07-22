package processing.response.deserialize;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import processing.response.model.Author;
import processing.response.model.Week;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class Deserializer {
	
	public Deserializer(String jsonData){
		
		Gson gson=new Gson();
		List<DeserializedClassHelper> deserializedList=gson.fromJson(jsonData, new TypeToken<List<DeserializedClassHelper>>(){}.getType());
		List<Author> authors=new ArrayList<Author>();
		for (DeserializedClassHelper deserializedItem : deserializedList) {
			authors.add(deserializedItem.toAuthor());
		}
		System.out.println(authors.size());
		for (Author author : authors) {
			System.out.println(author.toString());
		}
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
