package processing.response.model;

import java.util.ArrayList;
import java.util.List;

public class Commits {
	
	private List<String[]> commitMessages=new ArrayList<String[]>();
	private float averageCommitMessageLength;
	
	
	public List<String[]> getCommitMessages() {
		return commitMessages;
	}
	public void setCommitMessages(List<String[]> commitMessages) {
		this.commitMessages = commitMessages;
		int sum=0;
		for (String[] commit : commitMessages) {
			sum+=Integer.parseInt(commit[2]);
		}
		averageCommitMessageLength=sum/commitMessages.size();
		
	}
	public float getAverageCommitLength() {
		return averageCommitMessageLength;
	}
	public void setAverageCommitLength(float averageCommitLength) {
		this.averageCommitMessageLength = averageCommitLength;
	}

}
