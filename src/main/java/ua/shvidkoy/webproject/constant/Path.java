package ua.shvidkoy.webproject.constant;

public class Path {
	// pages
	public static final String PAGE_START = "/jsp/welcome.jsp";

	public static final String PAGE_PROFILE = "/jsp/user_profile.jsp";
	public static final String PAGE_COMPLETED_ADDITION = "/jsp/user_completed.jsp";

	public static final String PAGE_ERROR_PAGE = "jsp/error_page.jsp";
	public static final String PAGE_VALIDATION_COMPLETED = "/jsp/validation_completed.jsp";

	public static final String INDEX = "/index.jsp";
	public static final String INDEX_LOG = "index.jsp";

	// commands
	public static final String COMMAND_REDIRECT_TO_USERPROFILE = "front_controller?command=redirect_profile";
	public static final String COMMAND_INITIALIZE_USER_SESSION = "front_controller?command=init_user_list";
	public static final String COMMAND_REDIRECT_AFTER_ACTION = "front_controller?command=redirect_after_action";

}
