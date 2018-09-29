package ua.shvidkoy.webproject.logic;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.LogicException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.connectionpool.utill.TransactionManager;
import ua.shvidkoy.webproject.model.entity.User;

public class AdminLogic extends ApplicationLogic {
	private final static Logger LOGGER = Logger.getLogger(AdminLogic.class);

	public boolean isLoginFree(String login) throws LogicException, ConnectionException {

		try {

			return !userDao.isExist(login);
		} catch (MySqlException e) {
			throw new LogicException(e);
		}
	}

	public static void addUser(User user) throws MySqlException, ConnectionException {
		try {
			TransactionManager.beginTransaction();
			boolean isUserAdd = userDao.addEntity(user);
			LOGGER.info(isUserAdd + " status user add");
			TransactionManager.commitTransaction();
		} catch (ConnectionException e) {
			e.printStackTrace();
			TransactionManager.rollbackTransaction();
			throw e;

		}

	}

	public static void deleteUser(User user) throws MySqlException, ConnectionException {
		try {
			//TransactionManager.beginTransaction();
			boolean isUserAdd = userDao.removeEntity(user);
			LOGGER.info(isUserAdd + " status user delete");
			//TransactionManager.commitTransaction();
		} catch (ConnectionException e) {
			e.printStackTrace();
			TransactionManager.rollbackTransaction();
			throw e;

		}

	}

	public void newUserWithDefaultValues(User user) throws MySqlException, ConnectionException {
		try {
		//	TransactionManager.beginTransaction();
			boolean isUserAdd = userDao.newUserWithDefaultValues(user);
			LOGGER.info(isUserAdd + " status user add");
			//TransactionManager.commitTransaction();
		} catch (ConnectionException e) {
			e.printStackTrace();
			TransactionManager.rollbackTransaction();
			throw e;

		}
	}

	public void  getEntityById(int userId) throws MySqlException, ConnectionException {
		User user = userDao.selectEntityById(userId);
		LOGGER.info("USer that will be deleted --> "+ user);
		deleteUser(user);
	}
}