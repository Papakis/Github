package processing.response.deserialize;

import java.util.Date;

public class CommitDeserializerHelper {
	
	private Commit commit;
	
	public class Commit{
		private String message;
		private Author author;

		public class Author{
			private String date;
			
			public String getDate() {
				return date;
			}
			
			public void setDate(String date) {
				date=date.substring(0, date.length()-1).replace("T", " ");
//				String[] splittedDate=date.split("T");
				this.date = date;
			}
		}
		
		public Author getAuthor() {
			return author;
		}
		
		public void setAuthor(Author author) {
			this.author = author;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}
	

	public Commit getCommit() {
		return commit;
	}

	public void setCommit(Commit commit) {
		this.commit = commit;
	}


}
