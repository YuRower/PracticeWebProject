package ua.shvidkoy.webproject.logic;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.controller.FrontController;
import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.connectionpool.utill.TransactionManager;
import ua.shvidkoy.webproject.model.dao.AbstractDAOFactory;
import ua.shvidkoy.webproject.model.dao.AbstractDAOFactory.FactoryTypes;
import ua.shvidkoy.webproject.model.dao.UserDAO;
import ua.shvidkoy.webproject.model.entity.User;

public class UserLogic extends ApplicationLogic {

	private static AbstractDAOFactory mysqlFactory = AbstractDAOFactory.getDAOFactory(FactoryTypes.MYSQL);
	private static UserDAO userDao = mysqlFactory.getUserDAO();

	public static long LoginUser(User user, String login) throws MySqlException, ConnectionException {
		// user.setPassword(EncryptUtil.encryptString(user.getPassword()));
		TransactionManager.beginTransaction();
		// long photoId = PhotoDao.insert(user.getPhoto());
		// user.getPhoto().setId(photoId);
		try {
			user = userDao.findUserByLogin(login);
			TransactionManager.commitTransaction();
			return user.getId();
		} catch (ConnectionException e) {
			e.printStackTrace();
			TransactionManager.rollbackTransaction();
			throw e;

		}
		// long userId = userDao.addEntity(user);

	}

}
