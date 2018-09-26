package ua.shvidkoy.webproject.model.dao;

import java.sql.SQLException;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.connectionpool.ProxyConnection;
import ua.shvidkoy.webproject.model.dao.mysql.MysqlDAOFactory;

public abstract class AbstractDAOFactory {
	
	public enum FactoryTypes{
		MYSQL
	}

	public abstract UserDAO getUserDAO();
	public abstract PhotoDAO getPhotoDAO();
	public abstract UserToRoleDAO getUserToRoleDAO();

	public static AbstractDAOFactory getDAOFactory(FactoryTypes type) {//throws MySqlException, ConnectionException, SQLException {

		switch (type) {
		case MYSQL:
			try {
				return MysqlDAOFactory.getInstance();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		default:
			return null;
		}
	}
}
