package ua.shvidkoy.webproject.logic;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.LogicException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.connectionpool.utill.TransactionManager;

import ua.shvidkoy.webproject.model.entity.User;

public class GuestLogic extends ApplicationLogic {

	public User findUserByLogin(String login) throws LogicException, ConnectionException {

		try {
			//TransactionManager.beginTransaction();
			User user = userDao.findUserByLogin(login);
		//	TransactionManager.commitTransaction();

			return user;
		} catch (MySqlException e) {
			TransactionManager.rollbackTransaction();

			throw new LogicException(e);
		}
	}

}
