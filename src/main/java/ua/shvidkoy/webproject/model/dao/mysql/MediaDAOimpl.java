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
import ua.shvidkoy.webproject.model.dao.MediaDAO;
import ua.shvidkoy.webproject.model.entity.Media;

public class MediaDAOimpl implements MediaDAO {
	private MysqlDAOFactory factory;
	private final static Logger LOGGER = Logger.getLogger(MediaDAOimpl.class);
	private static final String SQL_FIND_ALL_MEDIA = "SELECT * FROM media";

	public static final String MEDIA_ID = "id";
	public static final String MEDIA_NAME = "name";
	public static final String MEDIA_TYPE = "type";

	public MediaDAOimpl(MysqlDAOFactory instance) {
		this.factory = instance;
	}
	
	@Override
	public List<Media> selectEntity() throws MySqlException, ConnectionException {
		List<Media> result = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ProxyConnection con = null;
		try {
			con = factory.getProxyConnection();
			pstmt = con.prepareStatement(SQL_FIND_ALL_MEDIA);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				result.add(extractMedia(rs));
			}
		} catch (SQLException ex) {
			LOGGER.error(Messages.ERR_CANNOT_OBTAIN_PHOTOS, ex);
			throw new MySqlException(Messages.ERR_CANNOT_OBTAIN_PHOTOS, ex);
		} finally {
			factory.close(con, pstmt, rs);
		}
		LOGGER.info(result.toString());
		return result;
	}

	private Media extractMedia(ResultSet rs) throws SQLException {
		Media media = new Media();
		media.setId(rs.getInt(MEDIA_ID));
		media.setName(rs.getString(MEDIA_NAME));
		media.setType(rs.getString(MEDIA_TYPE));
		return media;
	}

	@Override
	public Media selectEntityById(int id) throws MySqlException, ConnectionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Media entity) throws MySqlException, ConnectionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addEntity(Media entity) throws MySqlException, ConnectionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeEntity(Media entity) throws MySqlException, ConnectionException {
		// TODO Auto-generated method stub
		return false;
	}

}
