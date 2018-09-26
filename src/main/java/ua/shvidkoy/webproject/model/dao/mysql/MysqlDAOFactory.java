package ua.shvidkoy.webproject.model.dao.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.controller.FrontController;
import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.Messages;
import ua.shvidkoy.webproject.model.connectionpool.ConnectionPool;
import ua.shvidkoy.webproject.model.connectionpool.ProxyConnection;
import ua.shvidkoy.webproject.model.dao.AbstractDAOFactory;
import ua.shvidkoy.webproject.model.dao.PhotoDAO;
import ua.shvidkoy.webproject.model.dao.UserDAO;
import ua.shvidkoy.webproject.model.dao.UserToRoleDAO;

public class MysqlDAOFactory extends AbstractDAOFactory {
	private static ProxyConnection connection;
	private final static Logger LOGGER = Logger.getLogger(FrontController.class);

	public ProxyConnection getProxyConnection() throws ConnectionException, SQLException {
		connection = ConnectionPool.getInstance().getConnection();
		return connection;

	}
	private static MysqlDAOFactory instance;
	
	public static synchronized MysqlDAOFactory getInstance() throws SQLException {
		if (instance == null) {
			instance = new MysqlDAOFactory();
		}
		return instance;
}
	private MysqlDAOFactory() {

    }

	@Override
	public UserDAO getUserDAO() {
		return new UserDAOimpl(instance);
	}

	@Override
	public PhotoDAO getPhotoDAO() {
		return new PhotoDAOimpl(instance);
	}

	@Override
	public UserToRoleDAO getUserToRoleDAO() {
		return null;
	}
	
	public void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				LOGGER.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}

	public void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				LOGGER.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	public void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				LOGGER.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}

	
	public void close(ProxyConnection con, Statement stmt, ResultSet rs) {
		close(rs);
		close(stmt);
		close(con);
}
	/*public void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				LOGGER.error("Cannot rollback transaction", ex);
			}
		}
	}*/

}
