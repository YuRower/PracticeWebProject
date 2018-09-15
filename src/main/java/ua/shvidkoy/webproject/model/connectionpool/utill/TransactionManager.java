package ua.shvidkoy.webproject.model.connectionpool.utill;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

	private TransactionManager() {

	}

	public static void beginTransaction() {
		try {
			DBConnector.getConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollbackTransaction() {
		try {
			Connection connection = DBConnector.getConnection();
			connection.rollback();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commitTransaction() {
		try {
			Connection connection = DBConnector.getConnection();
			connection.commit();
			connection.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}