package model.entities;

import java.util.List;
import java.util.ArrayList;

public class Coche {
    private int id;
    private String marca;
    private String modelo;
    private String matricula;
    private int anio;
    private List<Gasto> gastos;
    
    public  String toString() {
		return modelo+" "+id;
    }
    

    public Coche(int id, String marca, String modelo, String matricula, int anio) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.anio = anio;
        this.gastos = new ArrayList<>();
    }

	// Getters
    public int getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getMatricula() { return matricula; }
    public int getAnio() { return anio; }
    public List<Gasto> getGastos() { return gastos; }

    // Setters
    public void setMarca(String marca) { this.marca = marca; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public void setAnio(int anio) { this.anio = anio; }
    public void agregarGasto(Gasto gasto) { this.gastos.add(gasto); }

	public void setNombre(String nuevoCoche) {
		// TODO Auto-generated method stub
		
	}

	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

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

	
}
