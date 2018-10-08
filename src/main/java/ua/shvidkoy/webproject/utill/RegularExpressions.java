package ua.shvidkoy.webproject.utill;

public class RegularExpressions {
	
		private RegularExpressions() {}
		
		public static final String LOGIN = "^[a-zA-Z0-9_-]{3,15}$";
		public static final String PASSWORD = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{4,}";
		

	
}
