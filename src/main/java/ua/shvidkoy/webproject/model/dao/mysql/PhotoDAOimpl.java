package ua.shvidkoy.webproject.model.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	private static final String SQL_FIND_PHOTO_BY_ID = "SELECT * FROM photo";
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
			pstmt = con.prepareStatement(SQL_FIND_PHOTO_BY_ID);
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
	public boolean update(Photo entity) throws MySqlException {
		return false;
	}

	@Override
	public boolean addEntity(Photo entity) throws MySqlException {
		return false;
	}

	@Override
	public boolean removeEntity(Photo entity) throws MySqlException {
		return false;
	}

	@Override
	public Photo selectEntityById(int id) throws MySqlException, ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

}
