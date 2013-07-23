package processing.response.deserialize;

import java.util.ArrayList;
import java.util.List;

import processing.response.model.Contributor;
import processing.response.model.User;
import processing.response.model.Week;

public class ContributorsDeserializerHelper {
	
	private int total;
	private WeekHolder[] weeks;
	ContributorHolder author;
	
	public Contributor toContributor(){
		Contributor genuineContributor=author.toGenuineContributor();
		genuineContributor.setTotal(total);
		
		Week[] genuineWeeks=new Week[weeks.length];
		for (int i = 0; i < genuineWeeks.length; i++) {
			genuineWeeks[i]=weeks[i].toGenuineWeek();
		}
		genuineContributor.setWeeks(genuineWeeks);
		return genuineContributor;
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
	
	class ContributorHolder{
		String login;
		int id;
		String avatar_url;
		String url;
		
		Contributor toGenuineContributor(){
			Contributor genuineContributor=new Contributor();
			genuineContributor.getUser().setAvatarUrl(avatar_url);
			genuineContributor.getUser().setId(id);
			genuineContributor.getUser().setLogin(login);
			genuineContributor.getUser().setUrl(url);
			return genuineContributor;
		}
	}

}
