package view;

import java.util.Scanner;

public class Usuariovista {
    public static void menu() {
    	 Scanner sc = new Scanner(System.in);
         boolean exit = false;

         while (!exit) {
        	 System.out.println("Seleccione una opción:");
             System.out.println("1. Salir");
             int opcion = sc.nextInt();
             sc.nextLine(); 

             switch (opcion) {
             case 1: 
            	 exit = true;
            	 System.out.println("Volviendo al menú de inicio");
                 break;
             default:
                 System.out.println("Opción no válida. Intenta nuevamente.");
         }
             
             
    }
}

}

