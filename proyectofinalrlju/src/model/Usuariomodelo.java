package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import model.entities.Coche;
import model.entities.Usuario;
import util.ConexionDB;

public class Usuariomodelo {
    private Connection connection;
    private List<Coche> coches;

    public Usuariomodelo() throws SQLException, ClassNotFoundException {
        this.connection = ConexionDB.conectar();
        this.coches = new ArrayList<>();
    }
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
    public void añadirCoche(String cocheNombre) {
        coches.add(new Coche(cocheNombre));
        System.out.println("Coche añadido: " + cocheNombre);
    }

    public void editarCoche(int index, String nuevoCoche) {
        if (index >= 0 && index < coches.size()) {
            coches.get(index).setNombre(nuevoCoche);
            System.out.println("Coche actualizado: " + nuevoCoche);
        } else {
            System.out.println("Índice no válido.");
        }
    }

    public void eliminarCoche(int index) {
        if (index >= 0 && index < coches.size()) {
            String cocheEliminado = coches.remove(index).getNombre();
            System.out.println("Coche eliminado: " + cocheEliminado);
        } else {
            System.out.println("Índice no válido.");
        }
    }

    public void verCoche(int index) {
        if (index >= 0 && index < coches.size()) {
            Coche coche = coches.get(index);
            System.out.println("Coche: " + coche.getNombre());
        } else {
            System.out.println("Índice no válido.");
        }
    }

    public void verGastosCoche(int index) {
        if (index >= 0 && index < coches.size()) {
            Coche coche = coches.get(index);
            System.out.println("Gastos para el coche: " + coche.getNombre());
            if (coche.getGastos().isEmpty()) {
                System.out.println("No hay gastos registrados.");
            } else {
                for (String gasto : coche.getGastos()) {
                    System.out.println("- " + gasto);
                }
            }
        } else {
            System.out.println("Índice no válido.");
        }
    }
}
