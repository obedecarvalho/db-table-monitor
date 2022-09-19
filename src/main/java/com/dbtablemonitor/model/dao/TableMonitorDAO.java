package com.dbtablemonitor.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TableMonitorDAO {

	private static final String URL_CONNECT = "jdbc:postgresql://%s";// Esperado host:port

	private static final String HOST = "127.0.0.1";

	private static final String PORT = "5432";
	
	private static final String BD = "fastfoot";

	private static Connection c;

	public static Connection getConnection() {

		if (c == null)

			try {
				Class.forName("org.postgresql.Driver");
				
				Properties props = new Properties();
				props.setProperty("user", "fastfoot");
				props.setProperty("password", "fastfoot_");
				//props.setProperty("ssl", "true");

				//c = DriverManager.getConnection(String.format(URL_CONNECT, HOST + ":" + PORT + "/" + BD), "fastfoot", "fastfoot_");
				c = DriverManager.getConnection(String.format(URL_CONNECT, HOST + ":" + PORT + "/" + BD), props);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
