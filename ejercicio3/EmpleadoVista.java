package ejercicio3;

import java.util.List;
import java.util.Scanner;

public class EmpleadoVista {
    private Scanner scanner;

    public EmpleadoVista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        System.out.println("\n=== MENÚ EMPLEADOS ===");
        System.out.println("1. Listar empleados");
        System.out.println("2. Agregar empleado");
        System.out.println("3. Buscar empleado");
        System.out.println("4. Eliminar empleado");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void mostrarEmpleados(List<Empleado> empleados) {
        if (empleados.isEmpty()) {
            System.out.println("\nNo hay empleados registrados.");
            return;
        }
        System.out.println("\nLista de Empleados:");
        empleados.forEach(System.out::println);
    }

    public Empleado pedirDatosEmpleado() {
        System.out.print("\nNúmero de empleado: ");
        int numero = Integer.parseInt(scanner.nextLine());
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Sueldo: ");
        double sueldo = Double.parseDouble(scanner.nextLine());
        return new Empleado(numero, nombre, sueldo);
    }

    public int pedirNumeroEmpleado() {
        System.out.print("\nIngrese el número de empleado: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }
}
