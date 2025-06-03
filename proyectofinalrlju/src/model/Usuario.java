package model;

import java.util.UUID;

public class Usuario {
    private String uuid;
    private String nombre;
    private String contrasena;

    public Usuario(String nombre, String contrasena) {
        this.uuid = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public String getUuid() { return uuid; }
    public String getNombre() { return nombre; }
    public String getContrasena() { return contrasena; }
}
