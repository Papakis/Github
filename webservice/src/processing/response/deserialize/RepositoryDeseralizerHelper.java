package processing.response.deserialize;

import processing.response.model.Repository;
import processing.response.model.User;

public class RepositoryDeseralizerHelper {
	
	private int id;
	private String name;
	private String full_name;
	private UserDeserializerHelper owner;
	private boolean Private;
	private String html_url;
	private String description;
	private String url;
	private String created_at;
	private String pushed_at;
	private int size;
	private String language;
	
	public Repository toGenuineRepository(){
		Repository repo=new Repository();
		repo.setCreatedAt(created_at);
		repo.setDescription(description);
		repo.setFullName(full_name);
		repo.setHtmlUrl(html_url);
		repo.setId(id);
		repo.setLanguage(language);
		repo.setName(name);
		repo.setOwner(owner.toGenuineUser());
		repo.setPrivate(Private);
		repo.setPushedAt(pushed_at);
		repo.setSize(size);
		repo.setUrl(url);
		return repo;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public UserDeserializerHelper getOwner() {
		return owner;
	}
	public void setOwner(UserDeserializerHelper owner) {
		this.owner = owner;
	}
	public boolean isPrivate() {
		return Private;
	}
	public void setPrivate(boolean private1) {
		Private = private1;
	}
	public String getHtml_url() {
		return html_url;
	}
	public void setHtml_url(String html_url) {
		this.html_url = html_url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getPushed_at() {
		return pushed_at;
	}
	public void setPushed_at(String pushed_at) {
		this.pushed_at = pushed_at;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

}
