package processing.response.deserialize;

import java.util.ArrayList;
import java.util.List;

import processing.response.model.Contributor;
import processing.response.model.User;
import processing.response.model.Week;

/**
 * Model for deserializing contributors data from GitHub
 *
 */
public class ContributorsDeserializerHelper {
	
	private int total;
	private WeekHolder[] weeks;
	UserDeserializerHelper author;
	
	public Contributor toContributor(){
		Contributor genuineContributor=new Contributor();
		genuineContributor.setUser(author.toGenuineUser());
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
	
}
