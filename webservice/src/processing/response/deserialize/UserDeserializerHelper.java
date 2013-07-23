package processing.response.deserialize;

import processing.response.model.User;

public class UserDeserializerHelper {
	
	private String login;
	private int id;
	private String avatar_url;
	private String url;
	private String email;
	private String created_at;
	
	public User toGenuineUser() {
		User user=new User();
		user.setAvatarUrl(avatar_url);
		user.setCreatedAt(created_at);
		user.setEmail(email);
		user.setId(id);
		user.setLogin(login);
		user.setUrl(url);
		return user;
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
