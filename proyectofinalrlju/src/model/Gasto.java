package model;

import java.time.LocalDate;

public class Gasto {
    private int id;
    private int cocheId;
    private String tipo;
    private int kilometraje;
    private LocalDate fecha;
    private double importe;
    private String descripcion;

    public Gasto(int id, int cocheId, String tipo, int kilometraje, LocalDate fecha, double importe, String descripcion) {
        this.id = id;
        this.cocheId = cocheId;
        this.tipo = tipo;
        this.kilometraje = kilometraje;
        this.fecha = fecha;
        this.importe = importe;
        this.descripcion = descripcion;
    }

    public int getId() { return id; }
    public int getCocheId() { return cocheId; }
    public String getTipo() { return tipo; }
    public int getKilometraje() { return kilometraje; }
    public LocalDate getFecha() { return fecha; }
    public double getImporte() { return importe; }
    public String getDescripcion() { return descripcion; }
}
