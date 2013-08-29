package processing.response.model;

import java.util.ArrayList;
import java.util.List;

public class Contributor {
	
	private User user;
	private Week[] weeks;
	private int total;
	private List<String> commitMessages=new ArrayList<String>();
	
	public Contributor(){
		user=new User();
	}
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < weeks.length; i++) {
			sb.append(i+1+".  "+weeks[i].toString()+"\n");
		}
		return user.toString()+"total commits: "+total+"\n"+"weeks:\n"+sb;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Week[] getWeeks() {
		return weeks;
	}
	public void setWeeks(Week[] weeks) {
		this.weeks = weeks;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public List<String> getCommitMessages() {
		return commitMessages;
	}

	public void setCommitMessages(List<String> commitMessages) {
		this.commitMessages = commitMessages;
	}

}
