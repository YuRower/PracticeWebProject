package ua.shvidkoy.webproject.model.dao;



import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.entity.AbstractEntity;

public interface CrudDAO<E extends AbstractEntity> {

	public E selectEntityById(int id) throws MySqlException,ConnectionException;

	public boolean update(E entity) throws MySqlException, ConnectionException;

	public boolean addEntity(E entity) throws MySqlException, ConnectionException;

	public boolean removeEntity(E entity) throws MySqlException, ConnectionException;

	

}