package processing.request;

/**
 * Contains URLs to GitHub API
 *
 */
public abstract class URL {
	
	public static final String API="https://api.github.com/";
	public static final String REPOS = "repos/";
	public static final String USERS = "users/";
	public static final String CONTRIBUTORS = "/stats/contributors";
	public static final String COMMITS = "/commits";
	public static final String COMMITS_DISTRIBUTION = "/stats/punch_card";
	public static final String COMMIT_ACTIVITY = "/stats/commit_activity";
	public static final String PER_PAGE = "?per_page=100";		//displaying x items per page
	
	public static final String AUTHORIZATION = "authorizations";

}
