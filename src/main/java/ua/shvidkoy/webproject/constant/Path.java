package ua.shvidkoy.webproject.constant;

public class Path {
	// pages
		public static final String PAGE_LOGIN = "login.jsp";
		public static final String PAGE_ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";
		public static final String PAGE_VALIDATION_COMPLETED = "/WEB-INF/jsp/validation_completed.jsp";
	

	

		// commands
		public static final String COMMAND_LIST_ACCOUNTS = "controller?command=listAccounts";
		public static final String COMMAND_LIST_TRANSACTIONS= "controller?command=listTransactions";
		public static final String COMMAND_LIST_REQUESTS = "controller?command=listRequests";
		public static final String COMMAND_LIST_ADMINS= "controller?command=listAdmins";
		public static final String COMMAND_REDIRECT_REGISTRATION_COMPLETED= "controller?command=redirectRegistrationCompleted";
		public static final String COMMAND_REDIRECT_VALIDATION_COMPLETED = "controller?command=redirectValidationCompleted";
		public static final String COMMAND_REDIRECT_TRANSACTION_COMPLETED = "controller?command=redirectReplenishmentCompleted";
		public static final String COMMAND_INITIALIZE_USER_SESSION= "controller?command=initializeUserSession";
		public static final String COMMAND_SORT_ACCOUNTS= "controller?command=sortAccounts";
		public static final String COMMAND_SORT_TRANSACTIONS= "controller?command=sortTransactions";
		public static final String COMMAND_ADMIN_ACTION_CONFIRMED= "controller?command=showActionConfirmed";

	}
