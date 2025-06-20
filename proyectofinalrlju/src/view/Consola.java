package view;

import controller.UsuarioController;
import model.Usuario;

import java.util.Scanner;

public class Consola {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        UsuarioController usuarioController = new UsuarioController();
        Usuariovista usuariovista = new Usuariovista(usuarioController);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1: 
                    System.out.print("Introduce tu nombre: ");
                    String nombreRegistro = sc.nextLine();
                    System.out.print("Introduce tu contraseña: ");
                    String passRegistro = sc.nextLine();
                    
                    Usuario nuevoUsuario = new Usuario(nombreRegistro, passRegistro);
                    if (UsuarioController.registrarUsuario(nuevoUsuario)) {
                        System.out.println("Registro exitoso. UUID: " + nuevoUsuario.getUuid());
                    } else {
                        System.out.println("Error al registrar usuario.");
                    }
                    break;

                case 2: 
                    System.out.print("Introduce tu nombre: ");
                    String nombreLogin = sc.nextLine();
                    System.out.print("Introduce tu contraseña: ");
                    String passLogin = sc.nextLine();
                    
                    if (UsuarioController.iniciarSesion(nombreLogin, passLogin)) {
                        System.out.println("Inicio de sesión exitoso. Bienvenido, " + nombreLogin + "!");
                        usuariovista.menu(); // Llamamos al menú de la instancia
                    } else {
                        System.out.println("Error al iniciar sesión. Verifica tus credenciales.");
                    }
                    break;

                case 3: 
                    exit = true;
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
        sc.close();
    }
}
