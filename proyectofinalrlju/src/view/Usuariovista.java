package view;

import java.util.InputMismatchException;
import java.util.Scanner;
import controller.UsuarioController;

public class Usuariovista {
    private UsuarioController controller;

    public Usuariovista(UsuarioController controller) {
        this.controller = controller;
    }

    public void menu() {
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

    private void añadirCoche(Scanner sc) {
        System.out.println("Ingrese el nombre del coche:");
        String cocheNombre = sc.nextLine();
        controller.añadirCoche(cocheNombre);
    }

    private void editarCoche(Scanner sc) {
        int index = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Ingrese el índice del coche a editar:");
            try {
                index = sc.nextInt();
                sc.nextLine(); 
                validInput = true; 
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                sc.nextLine(); 
            }
        }

        System.out.println("Ingrese el nuevo nombre del coche:");
        String nuevoCoche = sc.nextLine();
        controller.editarCoche(index, nuevoCoche);
    }


    private void eliminarCoche(Scanner sc) {
        int index = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Ingrese el índice del coche a eliminar:");
            try {
                index = sc.nextInt();
                sc.nextLine(); 
                validInput = true; 
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                sc.nextLine();
            }
        }

        controller.eliminarCoche(index);
    }


    private void verCoche(Scanner sc) {
        int index = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Ingrese el índice del coche a ver:");
            try {
                index = sc.nextInt();
                sc.nextLine(); 
                validInput = true; 
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                sc.nextLine(); 
            }
        }

        try {
			controller.verCoche(index);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
    }

    private void verGastosCoche(Scanner sc) {
        int index = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.println("Ingrese el índice del coche para ver los gastos:");
            try {
                index = sc.nextInt();
                sc.nextLine();
                validInput = true; 
            } catch (InputMismatchException e) {
                System.out.println("Entrada no válida. Por favor, ingrese un número entero.");
                sc.nextLine(); 
            }
        }

        controller.verGastosCoche(index);
    }
}
