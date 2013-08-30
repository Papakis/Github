package processing.response.deserialize;

import java.util.Date;

import processing.response.model.Repository;
import processing.response.model.User;

/**
 * Model for deserializing repository data from GitHub
 *
 */
public class RepositoryDeseralizerHelper {
	
	private int id;
	private UserDeserializerHelper owner;
	private String name;
	private String full_name;
	private String description;
	private String commits_url;
	private boolean Private;
	private boolean fork;
	private String url;
	private String html_url;
	private String mirror_url;
	private String homepage;
	private String language;
	private int forks;
	private int forks_count;
	private int watchers;
	private int watchers_count;
	private int size;
	private String master_branch;
	private int open_issues;
	private int open_issues_count;
	private String pushed_at;
	private String created_at;
	private String updated_at;
	private UserDeserializerHelper organization; 
	private RepositoryDeseralizerHelper parent; 
	private RepositoryDeseralizerHelper source; 
	private boolean has_issues;
	private boolean has_wiki;
	private boolean has_downloads;
	
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
		repo.setCommitsUrl(commits_url);
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


	public String getCommits_url() {
		return commits_url;
	}


	public void setCommits_url(String commits_url) {
		this.commits_url = commits_url;
	}

}
