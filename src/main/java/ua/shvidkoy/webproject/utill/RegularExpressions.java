package ua.shvidkoy.webproject.utill;

public class RegularExpressions {
	
		private RegularExpressions() {}
		
		public static final String LOGIN = "^[\\p{L}0-9]{5,16}$";
		public static final String NAME = "^[a-zA-Zа-яА-Я][a-zA-Zа-яА-Я-.\'\\s]{0,44}";
		public static final String ACCOUNT_NAME = "^[a-zA-Zа-яА-Я0-9][0-9a-zA-Zа-яА-Я-\\s]{0,44}";
		public static final String EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
		public static final String PASSWORD = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}$";
		

	
}
