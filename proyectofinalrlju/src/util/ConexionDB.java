package util;

import java.sql.*;

public class ConexionDB {
	private static String url = "jdbc:mysql://localhost:3306/practicafinalrlju";
	private static String user = "root";
	private static String password = "PracticaRoot";

    public static Connection conectar() throws SQLException, ClassNotFoundException {
    	
    	Class.forName("com.mysql.cj.jdbc.Driver");
    	
        return DriverManager.getConnection(url, user, password);
    }
}