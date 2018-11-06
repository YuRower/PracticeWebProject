package ua.shvidkoy.webproject.model.dao;

import java.util.List;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.entity.Media;

public interface MediaDAO extends CrudDAO<Media> {

	List<Media> selectEntity() throws MySqlException, ConnectionException;

}
