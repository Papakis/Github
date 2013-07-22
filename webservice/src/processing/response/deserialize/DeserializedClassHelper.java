package processing.response.deserialize;

import java.util.ArrayList;
import java.util.List;

import processing.response.model.Author;
import processing.response.model.Week;

public class DeserializedClassHelper {
	
	private int total;
	private WeekHolder[] weeks;
	AuthorHolder author;
	
	public Author toAuthor(){
		Author genuineAuthor=author.toGenuineAuthor();
		genuineAuthor.setTotal(total);
		
		for (int i = 0; i < weeks.length; i++) {
			System.out.println(weeks[i].a);
			System.out.println(weeks[i].c);
			System.out.println(weeks[i].d);
			System.out.println(weeks[i].w);
		}
		
		Week[] genuineWeeks=new Week[weeks.length];
		for (int i = 0; i < genuineWeeks.length; i++) {
			genuineWeeks[i]=weeks[i].toGenuineWeek();
		}
		genuineAuthor.setWeeks(genuineWeeks);
		return genuineAuthor;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public WeekHolder[] getWeeks() {
		return weeks;
	}

	public void setWeeks(WeekHolder[] weeks) {
		this.weeks = weeks;
	}

	class WeekHolder{
		long w;
		int a;
		int d;
		int c;
		
		Week toGenuineWeek(){
			Week week=new Week();
			week.setNumOfAdditions(a);
			week.setNumOfCommits(c);
			week.setNumOfDeletions(d);
			week.setTime(w);
			return week;
		}
	}
	
	class AuthorHolder{
		String login;
		int id;
		String avatar_url;
		String url;
		
		Author toGenuineAuthor(){
			Author genuineAuthor=new Author();
			genuineAuthor.setAvatarUrl(avatar_url);
			genuineAuthor.setId(id);
			genuineAuthor.setLogin(login);
			genuineAuthor.setUrl(url);
			return genuineAuthor;
		}
	}

}
