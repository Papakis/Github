package processing.response.model;

public class Author {
	
	private String login;
	private int id;
	private String avatarUrl;
	private String url;
	private Week[] weeks;
	
	public String toString(){
		return "login: "+login+"\n"+"id: "+id+"\n"+"url: "+url+"\n"+"weeks: "+weeks;
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

}
