package processing.response.model;

import java.sql.Date;
import java.util.Arrays;

public class CommitStats {
	private String[] days = new String[7]; // 0-6 weekdays, starting from Sunday
	private int total;
	private long week;
	
	private int[][] commitDistribution= new int[7][24]; // 0-6 weekdays, starting from Sunday
	
	public String printCommitDistribution(){
		StringBuilder sb=new StringBuilder("");
		for (int i = 0; i < commitDistribution.length; i++) {
			int sum = 0;
			for (int j = 0; j < commitDistribution[i].length; j++) {
				sum+=commitDistribution[i][j];
			}
			sb.append("Day "+i+": "+ sum+"\n");
		}
		return sb.toString();
	}
	
	@Override
	public String toString() {
		return "Commit [\ndays=" + Arrays.toString(days) + "\ntotal=" + total
				+ "\nweek=" + new Date(week*1000).toString() + "]";
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
	public long getWeek() {
		return week;
	}
	public void setWeek(long week) {
		this.week = week;
	}
	public int[][] getCommitDistribution() {
		return commitDistribution;
	}
	public void setCommitDistribution(int[][] commitDistribution) {
		this.commitDistribution = commitDistribution;
	}

}
