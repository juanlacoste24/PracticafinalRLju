package view;

import controller.UsuarioController;
import model.Usuario;

import java.util.Scanner;

public class Consola {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce tu nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String pass = sc.nextLine();

        Usuario u = new Usuario(nombre, pass);
        if (UsuarioController.registrarUsuario(u)) {
            System.out.println("Registro exitoso. UUID: " + u.getUuid());
        } else {
            System.out.println("Error al registrar usuario.");
        }
    }
}
