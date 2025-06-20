package controller;

import util.ConexionDB;

import java.sql.*;

import model.entities.Coche;
import model.entities.Usuario;

public class UsuarioController {
    private static final String ID = null;
	private static final String index = null;


	public static boolean registrarUsuario(Usuario usuario) throws ClassNotFoundException {
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "INSERT INTO usuarios (uuid, nombre, contrasena) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario.getUuid());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getContrasena());
            stmt.executeUpdate();
            return true;
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
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public void añadirCoche(String cocheNombre) {
     
        System.out.println("Coche añadido: " + cocheNombre);
    }

    public void editarCoche(int index, String nuevoCoche) {

        System.out.println("Coche editado en índice " + index + ": " + nuevoCoche);
    }

    public void eliminarCoche(int index) {
  
        System.out.println("Coche eliminado en índice: " + index);
    }
 
    public Coche verCoche(int index) throws ClassNotFoundException {
        Coche coche = null; 
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "SELECT * FROM Coche WHERE ID = ? ";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, index);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
    
                int id = rs.getInt("ID");
                String marca = rs.getString("Marca");
                String modelo = rs.getString("Modelo");
       
                
                coche = new Coche(id, marca, modelo, id, id, modelo);
            }
            
            rs.close(); 
            stmt.close(); 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coche; 
    }

    public void verGastosCoche(int index) {
      
        System.out.println("Mostrando gastos del coche en índice: " + index);
    }
}
