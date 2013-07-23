package processing.response.model;

public class Author {
	
	private String login;
	private int id;
	private String avatarUrl;
	private String url;
	private Week[] weeks;
	private int total;
	
	public String toString(){
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < weeks.length; i++) {
			sb.append(i+1+".  "+weeks[i].toString()+"\n");
		}
		return "login: "+login+"\n"+"id: "+id+"\n"+"url: "+url+"\n"+"total commits: "+total+"\n"+"weeks:\n"+sb;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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

}
