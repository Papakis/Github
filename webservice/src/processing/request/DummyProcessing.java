package processing.request;

public class DummyProcessing 
{
	public static void dummyProcess(String input){
		System.out.println("DUMMY| RECIEVED| " + input);
	}
	
	public static String getUser(String user){return null;}
	
	public static String getRepos(String user, String repo){return null;}
	
}
