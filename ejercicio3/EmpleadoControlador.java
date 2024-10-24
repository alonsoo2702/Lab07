package ejercicio3;

import java.io.IOException;
import java.util.Scanner;

public class EmpleadoControlador {
    private EmpleadoDAO modelo;
    private EmpleadoVista vista;

    public EmpleadoControlador(EmpleadoDAO modelo, EmpleadoVista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void iniciar() {
        boolean continuar = true;
        while (continuar) {
            try {
                vista.mostrarMenu();
                int opcion = Integer.parseInt(new Scanner(System.in).nextLine());
                
                switch (opcion) {
                    case 1 -> vista.mostrarEmpleados(modelo.obtenerTodos());
                    case 2 -> agregarEmpleado();
                    case 3 -> buscarEmpleado();
                    case 4 -> eliminarEmpleado();
                    case 5 -> continuar = false;
                    default -> vista.mostrarMensaje("Opción inválida");
                }
            } catch (NumberFormatException e) {
                vista.mostrarMensaje("Error: Ingrese un número válido");
            } catch (Exception e) {
                vista.mostrarMensaje("Error: " + e.getMessage());
            }
        }
    }

    private void agregarEmpleado() {
        try {
            Empleado empleado = vista.pedirDatosEmpleado();
            modelo.agregarEmpleado(empleado);
            vista.mostrarMensaje("Empleado agregado exitosamente");
        } catch (Exception e) {
            vista.mostrarMensaje("Error al agregar empleado: " + e.getMessage());
        }
    }

    private void buscarEmpleado() {
        int numero = vista.pedirNumeroEmpleado();
        Empleado empleado = modelo.buscarEmpleado(numero);
        if (empleado != null) {
            vista.mostrarMensaje(empleado.toString());
        } else {
            vista.mostrarMensaje("Empleado no encontrado");
        }
    }

    private void eliminarEmpleado() {
        try {
            int numero = vista.pedirNumeroEmpleado();
            if (modelo.eliminarEmpleado(numero)) {
                vista.mostrarMensaje("Empleado eliminado exitosamente");
            } else {
                vista.mostrarMensaje("Empleado no encontrado");
            }
        } catch (IOException e) {
            vista.mostrarMensaje("Error al eliminar empleado: " + e.getMessage());
        }
    }
}
