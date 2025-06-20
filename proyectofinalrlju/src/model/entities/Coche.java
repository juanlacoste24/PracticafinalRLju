package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Coche {
    private int id;
    private String marca;
    private String modelo;
    private String matricula;
    private int anio;
	private String nombre;
	private ArrayList gastos;

    public Coche(int id, String marca, String modelo, String matricula, int anio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.anio = anio;
    }
 public Coche(int id2, String brand, String model, float consume, int emissions, String imagen) {
		// TODO Auto-generated constructor stub
	}
public int getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getMatricula() { return matricula; }
    public int getAnio() { return anio; }
	public float getConsume() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getEmissions() {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getImagen() {
		// TODO Auto-generated method stub
		return null;
	}

	


    public Coche(String nombre) {
        this.nombre = nombre;
        this.gastos = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void añadirGasto(String gasto) {
        List<String> gastos = null;
		gastos.add(gasto);
    }

    public List<String> getGastos() {
        return getGastos();
    }
}


