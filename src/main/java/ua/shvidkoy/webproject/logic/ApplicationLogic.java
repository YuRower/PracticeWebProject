package ua.shvidkoy.webproject.logic;

import java.util.List;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.LogicException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.dao.AbstractDAOFactory;
import ua.shvidkoy.webproject.model.dao.UserDAO;
import ua.shvidkoy.webproject.model.entity.User;
import ua.shvidkoy.webproject.model.dao.AbstractDAOFactory.FactoryTypes;
import ua.shvidkoy.webproject.model.dao.PhotoDAO;

public class ApplicationLogic {
	protected static AbstractDAOFactory mysqlFactory = AbstractDAOFactory.getDAOFactory(FactoryTypes.MYSQL);
	protected static UserDAO userDao = mysqlFactory.getUserDAO();
	protected static PhotoDAO photoDao = mysqlFactory.getPhotoDAO();


	public List<User> findAllUsers() throws LogicException, ConnectionException {

		try {
			return userDao.findAll();
		} catch (MySqlException e) {
			throw new LogicException(e);
		}
	}
}
