package controller;

import util.ConexionDB;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.entities.Gasto;

public class GastoController {
    public static boolean agregarGasto(Gasto gasto) throws ClassNotFoundException {
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "INSERT INTO gastos (coche_id, tipo, kilometraje, fecha, importe, descripcion) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, gasto.getCocheId());
            stmt.setString(2, gasto.getTipo());
            stmt.setInt(3, gasto.getKilometraje());
            stmt.setDate(4, Date.valueOf(gasto.getFecha()));
            stmt.setDouble(5, gasto.getImporte());
            stmt.setString(6, gasto.getDescripcion());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Gasto> obtenerGastosDeCoche(int cocheId) throws ClassNotFoundException {
        List<Gasto> lista = new ArrayList<>();
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "SELECT * FROM gastos WHERE coche_id = ? ORDER BY fecha DESC";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, cocheId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Gasto(
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
        return lista;
    }

    public static boolean actualizarGasto(Gasto gasto) throws ClassNotFoundException {
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "UPDATE gastos SET tipo = ?, kilometraje = ?, fecha = ?, importe = ?, descripcion = ? WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, gasto.getTipo());
            stmt.setInt(2, gasto.getKilometraje());
            stmt.setDate(3, Date.valueOf(gasto.getFecha()));
            stmt.setDouble(4, gasto.getImporte());
            stmt.setString(5, gasto.getDescripcion());
            stmt.setInt(6, gasto.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean eliminarGasto(int gastoId) throws ClassNotFoundException {
        try (Connection conn = ConexionDB.conectar()) {
            String sql = "DELETE FROM gastos WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, gastoId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
