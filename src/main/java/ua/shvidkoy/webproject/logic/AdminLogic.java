package ua.shvidkoy.webproject.logic;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.LogicException;
import ua.shvidkoy.webproject.exception.MySqlException;

public class AdminLogic extends ApplicationLogic{

	

	public boolean isLoginFree(String login) throws LogicException, ConnectionException {

		try {

			return !userDao.isExist(login);
		} catch (MySqlException e) {
			throw new LogicException(e);
		}
	}
}