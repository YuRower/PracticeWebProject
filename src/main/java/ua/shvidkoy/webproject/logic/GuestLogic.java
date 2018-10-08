package ua.shvidkoy.webproject.logic;

import java.util.List;
import ua.shvidkoy.webproject.exception.ConnectionException;
import ua.shvidkoy.webproject.exception.LogicException;
import ua.shvidkoy.webproject.exception.MySqlException;
import ua.shvidkoy.webproject.model.entity.Photo;
import ua.shvidkoy.webproject.model.entity.User;

public class GuestLogic extends ApplicationLogic {

	public User findUserByLogin(String login) throws LogicException, ConnectionException {

		try {
			User user = userDao.findUserByLogin(login);

			return user;
		} catch (MySqlException e) {
			throw new LogicException(e);
		}
	}

	public List<Photo> loadPhoto() throws LogicException, ConnectionException, MySqlException {
		return photoDao.selectEntity();
	}
}
