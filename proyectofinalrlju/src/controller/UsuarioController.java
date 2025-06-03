package controller;

import model.Usuario;
import util.ConexionDB;

import java.sql.*;

public class UsuarioController {
    public static boolean registrarUsuario(Usuario usuario) {
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "INSERT INTO usuarios (uuid, nombre, contrasena) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getUuid());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getContrasena());
            stmt.executeUpdate();
            return true;
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Nombre duplicado. Registro fallido.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
}

	public void start() {
		// TODO Auto-generated method stub
		
	}
}