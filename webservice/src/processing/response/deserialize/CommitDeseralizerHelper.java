package processing.response.deserialize;

import java.util.Arrays;

import processing.response.model.Commit;
import processing.response.model.Repository;

public class CommitDeseralizerHelper {
	private String[] days = new String[7];
	private String total;
	private String week;
	
	public Commit toGenuineCommit(){
		Commit commit = new Commit();
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
