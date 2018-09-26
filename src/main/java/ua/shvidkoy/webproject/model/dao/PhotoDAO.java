package ua.shvidkoy.webproject.model.dao;

import java.util.List;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.entity.Photo;

public interface PhotoDAO  extends CrudDAO<Photo>{

	List<Photo> selectEntity() throws MySqlException, ConnectionException;

}
