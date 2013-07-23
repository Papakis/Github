package processing.response.model;

import java.sql.Date;
import java.util.concurrent.TimeUnit;

public class Week {
	
	private long time; //time in miliseconds
	private int numOfAdditions;
	private int numOfDeletions;
	private int numOfCommits;
	
	public String toString(){
		return "time: "+new Date(time*1000).toString()+
				"\tA:"+numOfAdditions+
				"\tD: "+numOfDeletions+
				"\tC: "+numOfCommits;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	public long getTime() {
		return time;
	}
	public int getNumOfAdditions() {
		return numOfAdditions;
	}
	public void setNumOfAdditions(int numOfAdditions) {
		this.numOfAdditions = numOfAdditions;
	}
	public int getNumOfDeletions() {
		return numOfDeletions;
	}
	public void setNumOfDeletions(int numOfDeletions) {
		this.numOfDeletions = numOfDeletions;
	}
	public int getNumOfCommits() {
		return numOfCommits;
	}
	public void setNumOfCommits(int numOfCommits) {
		this.numOfCommits = numOfCommits;
	}

}
