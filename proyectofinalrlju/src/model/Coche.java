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

    public int getId() { return id; }
    public String getMarca() { return marca; }
    public String getModelo() { return modelo; }
    public String getMatricula() { return matricula; }
    public int getAnio() { return anio; }
}