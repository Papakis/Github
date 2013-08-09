package processing.response.model;

public class User {
	
	private String login;
	private int id;
	private String avatarUrl;
	private String url;
	private String htmlUrl;
	private String email;
	private String createdAt;
	private String reposUrl;
	private String publicRepos;
	private String followers;
	private String following;
	
	
	public String toString(){
			return "login: "+login+"\n"+"id: "+id+"\n"+"url: "+url+"\n"+"avatar url: "+avatarUrl+"\n";
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getReposUrl() {
		return reposUrl;
	}

	public void setReposUrl(String reposUrl) {
		this.reposUrl = reposUrl;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
	}

	public String getPublicRepos() {
		return publicRepos;
	}

	public void setPublicRepos(String publicRepos) {
		this.publicRepos = publicRepos;
	}

	public String getFollowers() {
		return followers;
	}

	public void setFollowers(String followers) {
		this.followers = followers;
	}

	public String getFollowing() {
		return following;
	}

	public void setFollowing(String following) {
		this.following = following;
	}

}
