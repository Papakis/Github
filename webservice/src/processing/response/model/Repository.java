package processing.response.model;

public class Repository {
	
	private int id;
	private String name;
	private String fullName;
	private User owner;
	private boolean isPrivate;
	private String htmlUrl;
	private String description;
	private String url;
	private String createdAt;
	private String pushedAt;
	private int size;
	private String language;
	
	public String toString(){
		return "id: "+id+"\nname: "+name+"\nfull name: "+fullName+"\nOWNER\n"+owner
				+"\nprivate: "+isPrivate+"\nhtml url: "+htmlUrl+"\ndescription: "+description
				+"\nurl: "+url+"\ncreated at: "+createdAt+"\npushed at: "+pushedAt
				+"\nsize: "+size+"\nlanguage: "+language;
		/*return "id: " + id + "\n" +
			   "full name: " + fullName + "\n" +
		       "OWNER: " + owner + "\n" +
		       "private: " + isPrivate + "\n" +
		       "html url: " + htmlUrl + "\n" +
		       "description: " + description + "\n" +
		       "url: " + url + "\n" +
		       "created at: " + createdAt + "\n" +
		       "pushed at: "+pushedAt + "\n" +
		       "size: " + size + "\n" +
		       "language: " + language;*/
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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	public String getHtmlUrl() {
		return htmlUrl;
	}
	public void setHtmlUrl(String htmlUrl) {
		this.htmlUrl = htmlUrl;
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
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getPushedAt() {
		return pushedAt;
	}
	public void setPushedAt(String pushedAt) {
		this.pushedAt = pushedAt;
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
