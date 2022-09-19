package com.dbtablemonitor.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigurationDAO {

	private static final String URL_CONNECT = "jdbc:sqlite:%s";// Esperado arquivo

	private static final String FILE_DB = "db-table-monitor.db";

	private static Connection c;

	public static Connection getConnection() {
		
		if (c == null) {

			try {
				// Class.forName("org.postgresql.Driver");
				c = DriverManager.getConnection(String.format(URL_CONNECT, FILE_DB));
			} catch (/* ClassNotFoundException | */ SQLException e) {
				e.printStackTrace();
			}
		}

		return c;
	}

	@Override
	protected void finalize() throws Throwable {
		if (c != null) {
			c.close();
		}
	}
}
