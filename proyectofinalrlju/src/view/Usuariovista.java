package view;

import java.util.Scanner;
import controller.UsuarioController;

public class Usuariovista {
    private static UsuarioController controller;

    public Usuariovista(UsuarioController controller) {
        this.controller = controller;
    }

    public static void menu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Salir");
            System.out.println("2. Añadir coche");
            System.out.println("3. Editar coche");
            System.out.println("4. Eliminar coche");
            System.out.println("5. Ver coche");
            System.out.println("6. Ver gastos coche");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    exit = true;
                    System.out.println("Volviendo al menú de inicio");
                    break;
                case 2:
                    añadirCoche(sc);
                    break;
                case 3:
                    editarCoche(sc);
                    break;
                case 4:
                    eliminarCoche(sc);
                    break;
                case 5:
                    verCoche(sc);
                    break;
                case 6:
                    verGastosCoche(sc);
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }
        sc.close();
    }

    private static void añadirCoche(Scanner sc) {
        System.out.println("Ingrese el nombre del coche:");
        String cocheNombre = sc.nextLine();
      
			controller.añadirCoche(cocheNombre);
		
		
		}
    

    private static void editarCoche(Scanner sc) {
        System.out.println("Ingrese el índice del coche a editar:");
        int index = sc.nextInt();
        sc.nextLine();
        System.out.println("Ingrese el nuevo nombre del coche:");
        String nuevoCoche = sc.nextLine();
        controller.editarCoche(index, nuevoCoche);
    }

    private static void eliminarCoche(Scanner sc) {
        System.out.println("Ingrese el índice del coche a eliminar:");
        int index = sc.nextInt();
        controller.eliminarCoche(index);
    }

    private static void verCoche(Scanner sc) {
        System.out.println("Ingrese el índice del coche a ver:");
        int index = sc.nextInt();
        controller.verCoche(index);
    }

    private static void verGastosCoche(Scanner sc) {
        System.out.println("Ingrese el índice del coche para ver los gastos:");
        int index = sc.nextInt();
        controller.verGastosCoche(index);
    }
}
