package main;

import java.sql.SQLException;
import java.util.List;

import model.Coche;


public interface IModel {

	List<Coche> list() throws SQLException;

	void add(Coche coche) throws SQLException;

	Coche findById(int id) throws Exception;

	void editCoche(Coche modifiedCoche) throws Exception;

	void removeCar(Coche carForRemove) throws Exception;

}
