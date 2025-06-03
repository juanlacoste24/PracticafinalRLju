package controller;

import model.Coche;
import util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CocheController {
    public static boolean crearCoche(Coche coche, String usuarioUuid) {
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

    public static List<Coche> obtenerCochesDeUsuario(String usuarioUuid) {
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
}
