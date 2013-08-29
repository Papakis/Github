package processing.response.deserialize;

import java.util.Arrays;

import processing.response.model.CommitStats;
import processing.response.model.Repository;

public class CommitStatsDeseralizerHelper {
	private String[] days = new String[7];
	private String total;
	private String week;
	
	public CommitStats toGenuineCommit(){
		CommitStats commit = new CommitStats();
		commit.setDays(days);
		commit.setTotal(Integer.parseInt(total));
		commit.setWeek(Integer.parseInt(week));
		
		return commit;
	}

	@Override
	public String toString() {
		return "CommitDeseralizerHelper [\ndays=" + Arrays.toString(days)
				+ "\ntotal=" + total + "\nweek=" + week + "]";
	}

	public String[] getDays() {
		return days;
	}

	public void setDays(String[] days) {
		this.days = days;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}
}
