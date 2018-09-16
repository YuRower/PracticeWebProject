package ua.shvidkoy.webproject.command;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.guest.LoginCommand;
import ua.shvidkoy.webproject.logic.GuestLogic;


public class CommandContainer {
	
	private final static Logger LOGGER = Logger.getLogger(CommandContainer.class);

	private static Map<String, CommandStrategy> commands = new HashMap<String, CommandStrategy>();
	
	static {
		// common commands
		commands.put("login", new LoginCommand(new GuestLogic()));
		//commands.put("logout", new LogoutCommand());
		//commands.put("commandNotFound", new NoCommand());
		
		// admin commands
		//commands.put("initializeUserSession", new InitializeUserSessionCommand());
		//commands.put("new_user", new NewUserCommand());
		//commands.put("view_user", new ViewUserCommand());
		//commands.put("update_user", new UpdateUserCommand());
		//commands.put("delete_user", new DeleteUserCommand());
		
		// admin commands
		//commands.put("actionsPage", new ActionsPageCommand());
		//commands.put("adminAction", new AdminActionCommand());
		//commands.put("showActionConfirmed", new ShowActionConfirmedCommand());
		//commands.put("requestResponse", new RequestResponseCommand());
		
		//guest commands
		//commands.put("userList", new ListUsersCommand());
		//commands.put("superuserAction", new SuperuserActionCommand());
		//commands.put("addAdmin", new AddAdminCommand());
		
		LOGGER.debug("Command container was successfully initialized");
		LOGGER.trace("Number of commands --> " + commands.size());
	}

	
	public static CommandStrategy get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOGGER.trace("Command not found, name --> " + commandName);
			return commands.get("commandNotFound"); 
		}
		
		return commands.get(commandName);
	}
	
}