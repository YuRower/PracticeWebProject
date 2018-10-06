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
import ua.shvidkoy.webproject.model.connectionpool.ProxyConnection;
import ua.shvidkoy.webproject.model.dao.PhotoDAO;
import ua.shvidkoy.webproject.model.entity.Photo;
import ua.shvidkoy.webproject.model.entity.User;

public class PhotoDAOimpl implements PhotoDAO {
	private static final String SQL_FIND_PHOTO_BY_ID = "SELECT * FROM photo WHERE id = ? ";
	private static final String SQL_FIND_PHOTO = "SELECT * FROM photo";
	private static final String SQL_INSERT_PHOTO = "INSERT INTO photo VALUES (?,?)";
	private static final String SQL_UPDATE_PHOTO = "UPDATE photo SET name = ? WHERE id=?";

	private MysqlDAOFactory factory;
	private final static Logger LOGGER = Logger.getLogger(PhotoDAOimpl.class);
	public static final String PHOTO_ID = "id";
	public static final String PHOTO_NAME = "name";

	public PhotoDAOimpl(MysqlDAOFactory instance) {
		this.factory = instance;
	}

	public List<Photo> selectEntity() throws MySqlException, ConnectionException {
		List<Photo> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_FIND_PHOTO);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(extractPhoto(rs));
			}
			// con.commit();
		} catch (SQLException ex) {
			// factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
			throw new MySqlException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		LOGGER.info(result.toString());
		return result;
	}

	private Photo extractPhoto(ResultSet rs) throws SQLException {
		Photo photo = new Photo();
		photo.setId(rs.getInt(PHOTO_ID));
		photo.setName(rs.getString(PHOTO_NAME));

		LOGGER.info(photo.toString());
		return photo;
	}

	@Override
	public boolean update(Photo entity) throws MySqlException, ConnectionException {
		boolean result;
		PreparedStatement pstmt = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_UPDATE_PHOTO);
			pstmt.setString(1, entity.getName());

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
	public boolean addEntity(Photo entity) throws MySqlException, ConnectionException {
		boolean result = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_INSERT_PHOTO, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, entity.getId());
			pstmt.setString(2, entity.getName());
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
	public boolean removeEntity(Photo entity) throws MySqlException {
		return false;
	}

	@Override
	public Photo selectEntityById(int id) throws MySqlException, ConnectionException {
		Photo photo = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_FIND_PHOTO_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				photo = extractPhoto(rs);
			}
			// con.commit();
		} catch (SQLException ex) {
			// factory.rollback(con);
			LOGGER.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
			throw new MySqlException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		return photo;
	}

}
