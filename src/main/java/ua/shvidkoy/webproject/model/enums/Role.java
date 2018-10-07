package ua.shvidkoy.webproject.model.enums;

import org.apache.log4j.Logger;


import ua.shvidkoy.webproject.model.entity.User;

public enum Role {
	GUEST, ADMIN, USER;
	private final static Logger LOGGER = Logger.getLogger(Role.class);

	public static Role getRole(User user) {
		
		int roleId = user.getUserRoleId();
		return Role.values()[roleId];
	}
	public static String getRole(Integer roleId) {
		return Role.values()[roleId].name();
	}
	
	
	public String getName() {
		return name().toLowerCase();
	}
	
}
