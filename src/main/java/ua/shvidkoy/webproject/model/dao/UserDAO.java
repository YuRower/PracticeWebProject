package ua.shvidkoy.webproject.model.dao;

import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.entity.User;

public interface UserDAO  extends CrudDAO<User>{
	User findUserByLogin(String login) throws ConnectionException, MySqlException ;
}
