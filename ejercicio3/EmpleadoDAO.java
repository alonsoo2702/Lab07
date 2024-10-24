package ejercicio3;

import java.io.*;
import java.util.*;

public class EmpleadoDAO {
    private final String archivo = "empleados.txt";
    private List<Empleado> empleados;

    public EmpleadoDAO() {
        empleados = new ArrayList<>();
        leerEmpleados();
    }

    public List<Empleado> obtenerTodos() {
        return new ArrayList<>(empleados);
    }

    public void agregarEmpleado(Empleado empleado) throws IOException {
        if (buscarEmpleado(empleado.getNumero()) != null) {
            throw new IllegalArgumentException("Ya existe un empleado con ese número");
        }
        empleados.add(empleado);
        guardarEmpleados();
    }

    public Empleado buscarEmpleado(int numero) {
        return empleados.stream()
            .filter(e -> e.getNumero() == numero)
            .findFirst()
            .orElse(null);
    }

    public boolean eliminarEmpleado(int numero) throws IOException {
        boolean eliminado = empleados.removeIf(e -> e.getNumero() == numero);
        if (eliminado) {
            guardarEmpleados();
        }
        return eliminado;
    }

    private void leerEmpleados() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                empleados.add(new Empleado(
                    Integer.parseInt(datos[0]),
                    datos[1],
                    Double.parseDouble(datos[2])
                ));
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo al guardar.");
        }
    }

    private void guardarEmpleados() throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Empleado e : empleados) {
                pw.println(e.getNumero() + "," + e.getNombre() + "," + e.getSueldo());
            }
        }
    }
}
