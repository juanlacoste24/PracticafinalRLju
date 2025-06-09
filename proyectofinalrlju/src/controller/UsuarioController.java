package controller;

import java.sql.SQLException;
import model.Usuariomodelo;

public class UsuarioController {
	   private Usuariomodelo modelo;
   


 

     

        public UsuarioController() throws SQLException, ClassNotFoundException {
            this.modelo = new Usuariomodelo();
        }

        public void añadirCoche(String cocheNombre) {
            modelo.añadirCoche(cocheNombre);
        }

        public void editarCoche(int index, String nuevoCoche) {
            modelo.editarCoche(index, nuevoCoche);
        }

        public void eliminarCoche(int index) {
            modelo.eliminarCoche(index);
        }

        public void verCoche(int index) {
            modelo.verCoche(index);
        }

        public void verGastosCoche(int index) {
            modelo.verGastosCoche(index);
        }
    

}

