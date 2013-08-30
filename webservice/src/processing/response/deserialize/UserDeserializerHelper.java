package processing.response.deserialize;

import processing.response.model.User;

/**
 * Model for deserializing user data from GitHub
 *
 */
public class UserDeserializerHelper {
	
	private String login;
	private int id;
	private String avatar_url;
	private String gravatar_id;
	private String url;
	private String name;
	private String company;
	private String blog;
	private String location;
	private String email;
	private String hireable;
	private String public_repos;
	private String public_gists;
	private String followers;
	private String following;
	private String html_url;
	private String created_at;
	private String type;

	public String getGravatar_id() {
		return gravatar_id;
	}

	public void setGravatar_id(String gravatar_id) {
		this.gravatar_id = gravatar_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getHireable() {
		return hireable;
	}

	public void setHireable(String hireable) {
		this.hireable = hireable;
	}

	public String getPublic_repos() {
		return public_repos;
	}

	public void setPublic_repos(String public_repos) {
		this.public_repos = public_repos;
	}

	public String getPublic_gists() {
		return public_gists;
	}

	public void setPublic_gists(String public_gists) {
		this.public_gists = public_gists;
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

	public String getHtml_url() {
		return html_url;
	}

	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public User toGenuineUser() {
		User user=new User();
		user.setAvatarUrl(avatar_url);
		user.setCreatedAt(created_at);
		user.setEmail(email);
		user.setId(id);
		user.setLogin(login);
		user.setUrl(url);
		user.setFollowers(followers);
		user.setFollowing(following);
		user.setHtmlUrl(html_url);
		user.setPublicRepos(public_repos);
		return user;
	}
	
	@Override
	public String toString() {
		return "\n\n-----------------------------\nUserDeserializerHelper [\nlogin=" + login + "\nid=" + id
				+ "\navatar_url=" + avatar_url + "\ngravatar_id=" + gravatar_id
				+ "\nurl=" + url + "\nname=" + name + "\ncompany=" + company
				+ "\nblog=" + blog + "\nlocation=" + location + "\nemail="
				+ email + "\nhireable=" + hireable + "\npublic_repos="
				+ public_repos + "\npublic_gists=" + public_gists
				+ "\nfollowers=" + followers + "\nfollowing=" + following
				+ "\nhtml_url=" + html_url + "\ncreated_at=" + created_at
				+ "\ntype=" + type + "]";

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
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
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
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

}
