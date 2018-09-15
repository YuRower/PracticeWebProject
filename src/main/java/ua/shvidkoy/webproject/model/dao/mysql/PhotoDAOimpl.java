package ua.shvidkoy.webproject.model.dao.mysql;

import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.connectionpool.ProxyConnection;
import ua.shvidkoy.webproject.model.dao.PhotoDAO;
import ua.shvidkoy.webproject.model.entity.Photo;

public class PhotoDAOimpl implements PhotoDAO {

	public PhotoDAOimpl(ProxyConnection connection) {
		// TODO Auto-generated constructor stub
	}

	public PhotoDAOimpl(MysqlDAOFactory instance) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Photo selectEntityById(int id) throws MySqlException {
		return null;
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

}
