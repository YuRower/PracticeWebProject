package ua.shvidkoy.webproject.model.connectionpool.utill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import ua.shvidkoy.webproject.controller.FrontController;

public final class DBConnector {
	private final static Logger LOGGER = Logger.getLogger(FrontController.class);


    /*
     * Registering driver to Mysql server
     */
    static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            LOGGER.log(Level.TRACE, "database driver was registered successfully.");
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, "database driver cant't be registered, throwing RuntimeException.", e);
            throw new RuntimeException("database driver cant't be registered.", e);
        }
}
	private static final String URL = DBResourceManager.getProperty(DBResourceManager.URL);
	private static final String USER = DBResourceManager.getProperty(DBResourceManager.USER);
	private static final String PASSWORD = DBResourceManager.getProperty(DBResourceManager.PASSWORD);

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
		return connection;
	}
}