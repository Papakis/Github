package processing.response.model;

import java.util.Arrays;

public class Commit {
	private String[] days = new String[5];
	private int total;
	private int week;
	
	@Override
	public String toString() {
		return "Commit [\ndays=" + Arrays.toString(days) + "\ntotal=" + total
				+ "\nweek=" + week + "]";
	}
	public String[] getDays() {
		return days;
	}
	public void setDays(String[] days) {
		this.days = days;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}

}
