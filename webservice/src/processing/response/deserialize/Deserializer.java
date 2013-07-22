package processing.response.deserialize;

import java.lang.reflect.Type;

import processing.response.model.Author;
import processing.response.model.Week;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class Deserializer {
	
	public Deserializer(String jsonData){
		
//		Gson gson=new Gson();
		System.out.println(jsonData);
		Gson gson=new GsonBuilder().registerTypeAdapter(Author.class, new ContributorsDeserializer()).create();
		Author[] authors=gson.fromJson(jsonData, Author[].class);
	}
	
	class ContributorsDeserializer implements JsonDeserializer<Author>{

		@Override
		public Author deserialize(JsonElement json, Type arg1, JsonDeserializationContext arg2)
				throws JsonParseException {
			Gson gson=new Gson();
			
			JsonObject jo=(JsonObject)json;
			
			Author author=new Author();
			JsonElement je=jo.get("author");
			author=gson.fromJson(je.toString(), Author.class);
			
			
			JsonElement je2=jo.get("weeks");
			author.setWeeks(gson.fromJson(je2, Week[].class));
			
			System.out.println(author.toString());
			
//			System.out.println(je.toString());
//			System.out.println(je2.toString());
			
			
			
			
			
			return null;
			
		}
		
	}
	
}
