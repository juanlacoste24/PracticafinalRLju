package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.IModel;
import util.ConexionDB;

public class ModelDatabase implements IModel {



	private Connection connection;
                             
	public ModelDatabase() throws SQLException, ClassNotFoundException {
		this.connection = ConexionDB.conectar();
	}

	public List<Coche> list() throws SQLException {
		String query = "SELECT Id, Marca, Modelo, Consumo, Emisiones, Imagen FROM coches";
		PreparedStatement ps = connection.prepareStatement(query);

		ResultSet rs = ps.executeQuery();
		List<Coche> list = new ArrayList<Coche>();
		while (rs.next()) {
			int id = rs.getInt(1);
			String brand = rs.getString(2);
			String model = rs.getString(3);
			float consume = rs.getFloat(4);
			int emissions = rs.getInt(5);
			String imagen = rs.getString(6);
			Coche coche = new Coche(id, brand, model, consume, emissions, imagen);
			list.add(coche);
		}
		return list;
	}

	public void add(Coche coche) throws SQLException {
		String query = "INSERT INTO coches (Marca, Modelo, Consumo, Emisiones, Imagen) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setLong(1, coche.getId());
		ps.setString(2, coche.getModelo());
		ps.setFloat(3, coche.getConsume());
		ps.setInt(4, coche.getEmissions());
		ps.setString(5, coche.getImagen());

		ps.executeUpdate();
	}

	public Coche findById(int id) throws Exception {
		String query = "SELECT Marca, Modelo, Consumo, Emisiones, Imagen FROM coches WHERE Id = ?";
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();
		Coche coche = null;
		while (rs.next()) {
			String brand = rs.getString(1);
			String model = rs.getString(2);
			float consume = rs.getFloat(3);
			int emissions = rs.getInt(4);
			String imagen = rs.getString(5);
			if (coche == null)
				coche = new Coche(id, brand, model, consume, emissions, imagen);
			else {
				throw new Exception("No puede haber dos coches con el id: " + id);
			}
		}
		return coche;
	}

	public void editCoche(Coche modifiedCoche) throws Exception {
		String query = "UPDATE coches SET Marca = ?, Modelo = ?, Consumo = ?, Emisiones = ?, Imagen = ? WHERE Id = ?";
		PreparedStatement ps = connection.prepareStatement(query);

		ps.setLong(1, modifiedCoche.getId());
		ps.setString(2, modifiedCoche.getModelo());
		ps.setFloat(3, modifiedCoche.getConsume());
		ps.setInt(4, modifiedCoche.getEmissions());
		ps.setString(5, modifiedCoche.getImagen());
		ps.setInt(6, modifiedCoche.getId());

		ps.executeUpdate();
	}

	public void removeCar(Coche carForRemove) throws Exception {
		// TODO Auto-generated method stub

	}
}