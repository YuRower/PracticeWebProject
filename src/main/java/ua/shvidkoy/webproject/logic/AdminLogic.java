package ua.shvidkoy.webproject.logic;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.connectionpool.utill.TransactionManager;
import ua.shvidkoy.webproject.model.dao.AbstractDAOFactory;
import ua.shvidkoy.webproject.model.dao.UserDAO;
import ua.shvidkoy.webproject.model.dao.AbstractDAOFactory.FactoryTypes;
import ua.shvidkoy.webproject.model.entity.User;

public class AdminLogic {

	private static AbstractDAOFactory mysqlFactory = AbstractDAOFactory.getDAOFactory(FactoryTypes.MYSQL);
	private static UserDAO userDao = mysqlFactory.getUserDAO();

}