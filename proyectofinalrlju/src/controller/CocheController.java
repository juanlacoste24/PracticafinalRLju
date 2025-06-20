package controller;

import util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.entities.Coche;
import model.entities.Gasto;

public class CocheController {
    public static boolean crearCoche(Coche coche, String usuarioUuid) throws ClassNotFoundException {
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "INSERT INTO coches (marca, modelo, matricula, anio) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, coche.getMarca());
            stmt.setString(2, coche.getModelo());
            stmt.setString(3, coche.getMatricula());
            stmt.setInt(4, coche.getAnio());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                int cocheId = rs.getInt(1);
                String sqlRel = "INSERT INTO usuarios_coches (usuario_uuid, coche_id) VALUES (?, ?)";
                PreparedStatement relStmt = conn.prepareStatement(sqlRel);
                relStmt.setString(1, usuarioUuid);
                relStmt.setInt(2, cocheId);
                relStmt.executeUpdate();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Coche> obtenerCochesDeUsuario(String usuarioUuid) throws ClassNotFoundException {
        List<Coche> coches = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "SELECT c.* FROM coches c JOIN usuarios_coches uc ON c.id = uc.coche_id WHERE uc.usuario_uuid = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuarioUuid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                coches.add(new Coche(
                    rs.getInt("id"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getString("matricula"),
                    rs.getInt("anio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coches;
    }

    public static boolean actualizarCoche(Coche coche) throws ClassNotFoundException {
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "UPDATE coches SET marca = ?, modelo = ?, matricula = ?, anio = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, coche.getMarca());
            stmt.setString(2, coche.getModelo());
            stmt.setString(3, coche.getMatricula());
            stmt.setInt(4, coche.getAnio());
            stmt.setInt(5, coche.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean eliminarCoche(int cocheId) throws ClassNotFoundException {
        try (Connection conn = ConexionDB.conectar()) {
            // Primero eliminar la relación en usuarios_coches
            String sqlRel = "DELETE FROM usuarios_coches WHERE coche_id = ?";
            PreparedStatement relStmt = conn.prepareStatement(sqlRel);
            relStmt.setInt(1, cocheId);
            relStmt.executeUpdate();

            // Luego eliminar el coche
            String sql = "DELETE FROM coches WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cocheId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

public static List<Gasto> obtenerGastosDeCoche(int cocheId) throws ClassNotFoundException {
    List<Gasto> gastos = new ArrayList<>();
    try (Connection conn = ConexionDB.conectar()) {
        String sql = "SELECT * FROM gastos WHERE coche_id = ? ORDER BY fecha DESC";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, cocheId);
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            gastos.add(new Gasto(
                rs.getInt("id"),
                rs.getInt("coche_id"),
                rs.getString("tipo"),
                rs.getInt("kilometraje"),
                rs.getDate("fecha").toLocalDate(),
                rs.getDouble("importe"),
                rs.getString("descripcion")
            ));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return gastos;
}

public static boolean agregarGasto(Gasto gasto) throws ClassNotFoundException {
    try (Connection conn = ConexionDB.conectar()) {
        String sql = "INSERT INTO gastos (coche_id, tipo, kilometraje, fecha, importe, descripcion) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, gasto.getCocheId());
        stmt.setString(2, gasto.getTipo());
        stmt.setInt(3, gasto.getKilometraje());
        stmt.setDate(4, Date.valueOf(gasto.getFecha()));
        stmt.setDouble(5, gasto.getImporte());
        stmt.setString(6, gasto.getDescripcion());
        
        return stmt.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
}

