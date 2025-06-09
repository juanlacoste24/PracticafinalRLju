package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import model.Usuario;
import util.ConexionDB;

public class UsuarioController {
   
    public static boolean registrarUsuario(Usuario usuario) throws ClassNotFoundException {
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

  
    public static boolean iniciarSesion(String nombre, String contrasena) throws ClassNotFoundException {
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
          
			stmt.setString(1, nombre);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
              
                return true;
            } else {
              
                System.out.println("Credenciales inválidas. Intenta de nuevo.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

   
    public void start() {
        // TODO Auto-generated method stub
    }
}
