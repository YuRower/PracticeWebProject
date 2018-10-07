package ua.shvidkoy.webproject.model.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.Messages;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.connectionpool.ConnectionPool;
import ua.shvidkoy.webproject.model.connectionpool.ProxyConnection;
import ua.shvidkoy.webproject.model.dao.UserDAO;
import ua.shvidkoy.webproject.model.entity.User;

public class UserDAOimpl implements UserDAO {
	private final static Logger LOGGER = Logger.getLogger(UserDAOimpl.class);

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

	private static final String SQL_REMOVE_USER = "DELETE FROM user WHERE id_user=?";
	private static final String SQL_FIND_ALL_USER = "SELECT * FROM user";
	private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM user WHERE id_user=?";

	private static final String SQL_INSERT_USER_FULL_INFO = "INSERT INTO user VALUES (DEFAULT,?, ?, ?, ?, ?, ?)";
	private static final String SQL_UPDATE_USER = "UPDATE user SET first_name = ?, last_name = ?, login = ?, "
			+ "password=?, id_role = ? WHERE id_user=?";
	private static final String SQL_UPDATE_USER_PHOTO = "UPDATE user SET id_photo= ? WHERE id_user=?";
	private static final String SQL_UPDATE_PASSWORD = "UPDATE user SET password= ? WHERE id_user=?";

	private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM user WHERE login=?";
	private static final String SQL_INSERT_USER_SHORT_VARIANT = "INSERT INTO user VALUES (DEFAULT, ?, ?, ?, ?, ?, DEFAULT)";

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
			// con.commit();
		} catch (SQLException ex) {
			// factory.rollback(con);
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
			pstmt.setInt(6, entity.getId());
			result = pstmt.executeUpdate() > 0;
			// con.commit();
		} catch (SQLException ex) {
			// factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
			throw new MySqlException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			factory.close(con);
			factory.close(pstmt);
		}
		return result;
	}

	@Override
	public boolean updatePhoto(User entity) throws MySqlException, ConnectionException {
		boolean result;
		PreparedStatement pstmt = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_USER_PHOTO);
			pstmt.setInt(1, entity.getUserPhotoId());
			pstmt.setInt(2, entity.getId());
			result = pstmt.executeUpdate() > 0;
			// con.commit();
		} catch (SQLException ex) {
			// factory.rollback(con);
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
			// con.commit();
		} catch (SQLException ex) {
			// factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_INSERT_USER, ex);
			throw new MySqlException(Messages.ERR_CANNOT_INSERT_USER, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return result;
	}

	@Override
	public boolean newUserWithDefaultValues(User user) throws MySqlException, ConnectionException {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_INSERT_USER_SHORT_VARIANT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, user.getFirstName());
			pstmt.setString(2, user.getLastName());
			pstmt.setString(3, user.getLogin());
			pstmt.setString(4, user.getPassword());
			pstmt.setInt(5, user.getUserRoleId());

			if (pstmt.executeUpdate() > 0) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					user.setId(rs.getInt(1));
					result = true;
				}
			}
		} catch (SQLException ex) {
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
			pstmt = con.prepareStatement(SQL_REMOVE_USER);
			pstmt.setInt(1, entity.getId());
			result = pstmt.executeUpdate() > 0;
			// con.commit();
		} catch (SQLException ex) {
			// factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_DELETE_USER, ex);
			throw new MySqlException(Messages.ERR_CANNOT_DELETE_USER, ex);
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
			// con.commit();
		} catch (SQLException ex) {
			// factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
			throw new MySqlException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return user;
	}

	@Override
	public boolean isExist(String login) throws MySqlException, ConnectionException {

		try (ProxyConnection connection = ConnectionPool.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_USER_BY_LOGIN)) {
			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();
			return resultSet.next();

		} catch (SQLException e) {
			throw new MySqlException("SQL exception (query or table failed)", e);
		}
	}

	@Override
	public List<User> findAll() throws MySqlException, ConnectionException {

		List<User> result = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(SQL_FIND_ALL_USER);
			while (rs.next()) {
				result.add(extractUser(rs));
			}
			// con.commit();
		} catch (SQLException ex) {
			// factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_OBTAIN_USER_LIST, ex);
			throw new MySqlException(Messages.ERR_CANNOT_OBTAIN_USER_LIST, ex);
		} finally {
			factory.close(con, stmt, rs);
		}
		return result;
	}

	@Override
	public boolean updatePassword(User user) throws MySqlException, ConnectionException {
		boolean result;
		PreparedStatement pstmt = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_PASSWORD);
			pstmt.setString(1, user.getPassword());
			pstmt.setInt(2, user.getId());
			result = pstmt.executeUpdate() > 0;
		} catch (SQLException ex) {
			LOGGER.error(Messages.ERR_CANNOT_UPDATE_USER, ex);
			throw new MySqlException(Messages.ERR_CANNOT_UPDATE_USER, ex);
		} finally {
			factory.close(con);
			factory.close(pstmt);
		}
		return result;
	}

}
