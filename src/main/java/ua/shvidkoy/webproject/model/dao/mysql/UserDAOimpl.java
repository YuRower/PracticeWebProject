package ua.shvidkoy.webproject.model.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.controller.FrontController;
import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.Messages;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.connectionpool.ConnectionPool;
import ua.shvidkoy.webproject.model.connectionpool.ProxyConnection;
import ua.shvidkoy.webproject.model.dao.UserDAO;
import ua.shvidkoy.webproject.model.entity.User;

public class UserDAOimpl implements UserDAO {
	private final static Logger LOGGER = Logger.getLogger(FrontController.class);

	private MysqlDAOFactory factory;

	public UserDAOimpl(MysqlDAOFactory instance) {
		this.factory = instance;
	}

	public static final String USER_ID = "id_user";
	public static final String USER_NAME = "first_name";
	public static final String USER_SURNAME = "last_name";
	public static final String USER_LOGIN = "login";
	public static final String USER_PASSWORD = "password";
	public static final String USER_ROLE_ID = "id_role";
	public static final String USER_PHOTO_ID = "id_photo";
	// SQL queries
	private static final String SQL_REMOVE_ADMIN = "DELETE FROM users WHERE user_id=?";
	private static final String SQL_FIND_ADMINS = "SELECT * FROM users WHERE user_type_id=1";
	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM users WHERE user_id=?";
	private static final String SQL_INSERT_USER_FULL_INFO = "INSERT INTO users VALUES (DEFAULT,?, ?, ?, ?, ?, ?)";
	private static final String SQL_INSERT_USER_SHORT_VARIANT = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT, DEFAULT)";
	private static final String SQL_UPDATE_USER = "UPDATE users SET name = ?, surname = ?, email = ?, email_verification = ?, "
			+ "password=?, user_type_id = ?, user_status_id = ? WHERE user_id=?";
	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";

	@Override
	public User selectEntityById(int id) throws MySqlException, ConnectionException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
			throw new MySqlException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return user;
	}

	@Override
	public boolean update(User entity) throws MySqlException, ConnectionException {
		boolean result;
		PreparedStatement pstmt = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_USER);
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setString(3, entity.getLogin());
			pstmt.setString(4, entity.getPassword());
			pstmt.setInt(5, entity.getUserRoleId());
			pstmt.setInt(6, entity.getUserPhotoId());
			pstmt.setInt(7, entity.getId());
			result = pstmt.executeUpdate() > 0;
			con.commit();
		} catch (SQLException ex) {
			factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
			throw new MySqlException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			factory.close(con);
			factory.close(pstmt);
		}
		return result;
	}

	@Override
	public boolean addEntity(User entity) throws MySqlException, ConnectionException {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_INSERT_USER_FULL_INFO, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setString(3, entity.getLogin());
			pstmt.setString(4, entity.getPassword());
			pstmt.setInt(5, entity.getUserRoleId());
			pstmt.setInt(6, entity.getUserPhotoId());
			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					entity.setId(rs.getInt(1));
					result = true;
				}
			}
			con.commit();
		} catch (SQLException ex) {
			factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new MySqlException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return result;
	}

	@Override
	public boolean removeEntity(User entity) throws MySqlException, ConnectionException {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_REMOVE_ADMIN);
			pstmt.setInt(1, entity.getId());
			result = pstmt.executeUpdate() > 0;
			con.commit();
		} catch (SQLException ex) {
			factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_DELETE_ADMIN, ex);
			throw new MySqlException(Messages.ERR_CANNOT_DELETE_ADMIN, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return result;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt(USER_ID));
		user.setFirstName(rs.getString(USER_NAME));
		user.setLastName(rs.getString(USER_SURNAME));
		user.setLogin(rs.getString(USER_LOGIN));
		user.setPassword(rs.getString(USER_PASSWORD));
		user.setUserRoleId(rs.getInt(USER_ROLE_ID));
		user.setUserPhotoId(rs.getInt(USER_PHOTO_ID));
		return user;
	}

	@Override
	public User findUserByLogin(String login) throws ConnectionException, MySqlException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
			pstmt.setString(1, login);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user = extractUser(rs);
			}
			con.commit();
		} catch (SQLException ex) {
			factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
			throw new MySqlException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return user;
	}

}
