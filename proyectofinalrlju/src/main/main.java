package main;

import controller.UsuarioController;

public class main {
    public main(String[] args) {
        try {
            
            UsuarioController UsuarioController = new UsuarioController();

           
            UsuarioController.start();

        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
