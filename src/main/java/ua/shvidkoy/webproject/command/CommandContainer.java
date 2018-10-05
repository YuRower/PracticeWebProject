package ua.shvidkoy.webproject.command;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.command.admin.AddUserCommand;
import ua.shvidkoy.webproject.command.admin.DeleteUserCommand;
import ua.shvidkoy.webproject.command.admin.RedirectAfterAction;
import ua.shvidkoy.webproject.command.guest.LoginCommand;
import ua.shvidkoy.webproject.command.guest.PresentPhotoCommand;
import ua.shvidkoy.webproject.command.user.LogoutCommand;
import ua.shvidkoy.webproject.command.user.RedirectToProfileCommand;
import ua.shvidkoy.webproject.command.user.UpdatePhotoCommand;
import ua.shvidkoy.webproject.command.user.UpdateUserCommand;
import ua.shvidkoy.webproject.command.user.UserListCommand;
import ua.shvidkoy.webproject.logic.AdminLogic;
import ua.shvidkoy.webproject.logic.GuestLogic;
import ua.shvidkoy.webproject.logic.UserLogic;

public class CommandContainer {

	private final static Logger LOGGER = Logger.getLogger(CommandContainer.class);

	private static Map<String, CommandStrategy> commands = new HashMap<String, CommandStrategy>();

	static {
		commands.put("login", new LoginCommand(new GuestLogic()));
		commands.put("init_user_list", new UserListCommand(new GuestLogic()));
		commands.put("logout", new LogoutCommand());
		commands.put("get_photo", new PresentPhotoCommand(new GuestLogic()));
		commands.put("update_user", new UpdateUserCommand(new UserLogic()));
		commands.put("commandNotFound", new NoCommand());
		commands.put("redirect_profile", new RedirectToProfileCommand(new UserLogic()));
		commands.put("add_user", new AddUserCommand(new AdminLogic()));
		commands.put("delete_user", new DeleteUserCommand(new AdminLogic()));
		commands.put("redirect_after_action", new RedirectAfterAction());
		commands.put("update_photo", new UpdatePhotoCommand(new UserLogic()));

		LOGGER.debug("Command container was successfully initialized");
		LOGGER.trace("Number of commands --> " + commands.size());
	}

	public static CommandStrategy get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOGGER.info("Command not found, name --> " + commandName);
			return commands.get("commandNotFound");
		}

		return commands.get(commandName);
	}

}