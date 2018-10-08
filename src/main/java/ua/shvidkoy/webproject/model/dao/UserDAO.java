package ua.shvidkoy.webproject.model.dao;

import java.util.List;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.entity.User;

public interface UserDAO extends CrudDAO<User> {
	User findUserByLogin(String login) throws ConnectionException, MySqlException;

	List<User> findAll() throws MySqlException, ConnectionException;

	boolean newUserWithDefaultValues(User user) throws  MySqlException, ConnectionException;

	boolean updatePhoto(User entity) throws MySqlException, ConnectionException;

	boolean updatePassword(User user) throws MySqlException, ConnectionException;
}
