package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


	
	public class DatabaseConnection implements IModel {

		private static String url = "jdbc:mysql://localhost:3306/vehiculos";
		private static String user = "root";
		private static String password = "PracticaRoot";
		private Connection connection;

		public DatabaseConnection() throws SQLException {
			String usuario = null;
			Properties contraseña = null;
			this.setConnection(DriverManager.getConnection(usuario, contraseña));
	}

		public static String getUrl() {
			return url;
		}

		public static void setUrl(String url) {
			DatabaseConnection.url = url;
		}

		public static String getUser() {
			return user;
		}

		public static void setUser(String user) {
			DatabaseConnection.user = user;
		}

		public static String getPassword() {
			return password;
		}

		public static void setPassword(String password) {
			DatabaseConnection.password = password;
		}

		public Connection getConnection() {
			return connection;
		}

		public void setConnection(Connection connection) {
			this.connection = connection;
		}

}
