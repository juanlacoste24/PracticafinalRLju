package view;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import controller.CocheController;
import controller.UsuarioController;
import model.entities.Coche;
import model.entities.Gasto;

public class Usuariovista {
    private UsuarioController controller;

    public Usuariovista(UsuarioController controller) {
        this.controller = controller;
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Salir");
            System.out.println("2. Añadir coche");
            System.out.println("3. Editar coche");
            System.out.println("4. Eliminar coche");
            System.out.println("5. Ver coche");
            System.out.println("6. Ver gastos coche");
            System.out.print("Seleccione una opción: ");
            
            try {
                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {
                    case 1:
                        exit = true;
                        System.out.println("Saliendo del sistema...");
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
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número.");
                sc.nextLine(); // Limpiar el buffer
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        }
        sc.close();
    }


    private void verGastosCoche(Scanner sc) {
		// TODO Auto-generated method stub
		
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
			
			Coche coche = controller.verCoche(index);
			System.out.print(coche);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
    }
    private void verGastosCoche(int index) {
        try {
           
            Coche coche = controller.verCoche(index);
            if (coche == null) {
                System.out.println("No se encontró el coche con índice: " + index);
                return;
            }

            // Obtener los gastos del coche
            List<Gasto> gastos = CocheController.obtenerGastosDeCoche(coche.getId());
            
            System.out.println("\n--- Gastos del coche " + coche.getMarca() + " " + coche.getModelo() + " ---");
            System.out.println("Matrícula: " + coche.getMatricula());
            System.out.println("Año: " + coche.getAnio());
            System.out.println("----------------------------------");
            
            if (gastos.isEmpty()) {
                System.out.println("No hay gastos registrados para este coche.");
            } else {
                DecimalFormat df = new DecimalFormat("#,##0.00€");
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                
                for (Gasto gasto : gastos) {
                    System.out.println("Fecha: " + sdf.format(Date.valueOf(gasto.getFecha())));
                    System.out.println("Tipo: " + gasto.getTipo());
                    System.out.println("Kilometraje: " + gasto.getKilometraje() + " km");
                    System.out.println("Importe: " + df.format(gasto.getImporte()));
                    System.out.println("Descripción: " + (gasto.getDescripcion() != null ? gasto.getDescripcion() : ""));
                    System.out.println("----------------------------------");
                }
                
                double total = gastos.stream().mapToDouble(Gasto::getImporte).sum();
                System.out.println("TOTAL GASTADO: " + df.format(total));
            }
            
            System.out.println("\nOpciones:");
            System.out.println("1. Añadir nuevo gasto");
            System.out.println("2. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            sc.nextLine();
            
            if (opcion == 1) {
                añadirGasto(coche.getId(), sc);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private void añadirGasto(int cocheId, Scanner sc) {
        try {
            System.out.println("\n--- Añadir nuevo gasto ---");
            
            System.out.print("Tipo (Combustible/Mantenimiento/Reparación/Seguro/Impuestos/Otros): ");
            String tipo = sc.nextLine();
            
            System.out.print("Kilometraje: ");
            int kilometraje = sc.nextInt();
            sc.nextLine();
            
            System.out.print("Fecha (YYYY-MM-DD): ");
            String fechaStr = sc.nextLine();
            LocalDate fecha = LocalDate.parse(fechaStr);
            
            System.out.print("Importe: ");
            double importe = sc.nextDouble();
            sc.nextLine();
            
            System.out.print("Descripción (opcional): ");
            String descripcion = sc.nextLine();
            
            Gasto nuevoGasto = new Gasto(0, cocheId, tipo, kilometraje, fecha, importe, descripcion);
            
            if (CocheController.agregarGasto(nuevoGasto)) {
                System.out.println("Gasto añadido correctamente.");
            } else {
                System.out.println("Error al añadir el gasto.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    }

