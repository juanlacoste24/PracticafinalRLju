package model;

public class Coche {
    private int id;
    private String marca;
    private String modelo;
    private String matricula;
    private int anio;

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
int getId() { return id; }
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

	}
