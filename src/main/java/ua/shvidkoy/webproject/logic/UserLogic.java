package ua.shvidkoy.webproject.logic;

import java.util.List;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.controller.FrontController;
import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.LogicException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.connectionpool.utill.TransactionManager;
import ua.shvidkoy.webproject.model.dao.AbstractDAOFactory;
import ua.shvidkoy.webproject.model.dao.AbstractDAOFactory.FactoryTypes;
import ua.shvidkoy.webproject.model.dao.UserDAO;
import ua.shvidkoy.webproject.model.entity.Photo;
import ua.shvidkoy.webproject.model.entity.User;

public class UserLogic extends ApplicationLogic {

	private static AbstractDAOFactory mysqlFactory = AbstractDAOFactory.getDAOFactory(FactoryTypes.MYSQL);
	private static UserDAO userDao = mysqlFactory.getUserDAO();
	private final static Logger LOGGER = Logger.getLogger(UserLogic.class);

	public long LoginUser(User user, String login) throws MySqlException, ConnectionException {
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

	public boolean updateUser(User user) throws MySqlException, ConnectionException {
		boolean updated = userDao.update(user);
		return updated;

	}

	public boolean updatePhoto(Photo photo) throws MySqlException, ConnectionException {
		boolean updated = photoDao.update(photo);
		return updated;

	}

	public User getUserByID(int id) throws MySqlException, ConnectionException {
		// TransactionManager.beginTransaction();
		// long photoId = PhotoDao.insert(user.getPhoto());
		// user.getPhoto().setId(photoId);
		try {
			User user = userDao.selectEntityById(id);
			// TransactionManager.commitTransaction();
			return user;
		} catch (ConnectionException e) {
			e.printStackTrace();
			TransactionManager.rollbackTransaction();
			throw e;

		}

	}

	public boolean insertPhoto(Photo photo) throws MySqlException, ConnectionException {
		// TODO Auto-generated method stub
		return photoDao.addEntity(photo);
	}

	public Photo loadPhotoById(int id) throws MySqlException, ConnectionException {
		// TODO Auto-generated method stub
		return photoDao.selectEntityById(id);
	}

	public void insertPhotoToUser(int id) throws MySqlException, ConnectionException {
		User user = getUserByID(id);
		user.setUserPhotoId(id);
		userDao.updatePhoto(user);
	}

}
