package ejercicio1;
import java.io.*;
import java.util.*;

public class Gestor {
    private List<Personaje> personajes;
    private final String archivo = "personajes.txt";

    public Gestor() {
        personajes = new ArrayList<>();
        cargarPersonajes();
    }

    private void cargarPersonajes() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    Personaje p = new Personaje(
                        datos[0],
                        Integer.parseInt(datos[1]),
                        Integer.parseInt(datos[2]),
                        Integer.parseInt(datos[3]),
                        Integer.parseInt(datos[4])
                    );
                    personajes.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo.");
        }
    }

    private void guardarPersonajes() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Personaje p : personajes) {
                pw.println(p.toString());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los personajes: " + e.getMessage());
        }
    }

    public void agregarPersonaje(Personaje nuevo) {
        if (buscarPersonaje(nuevo.getNombre()) != null) {
            throw new IllegalArgumentException("Ya existe un personaje con ese nombre");
        }
        personajes.add(nuevo);
        guardarPersonajes();
    }

    public void mostrarPersonajes() {
        System.out.println("\nLista de Personajes:");
        System.out.printf("%-15s %-8s %-8s %-8s %-8s%n", "Nombre", "Vida", "Ataque", "Defensa", "Alcance");
        System.out.println("-".repeat(50));
        for (Personaje p : personajes) {
            System.out.printf("%-15s %-8d %-8d %-8d %-8d%n",
                p.getNombre(), p.getVida(), p.getAtaque(), p.getDefensa(), p.getAlcance());
        }
    }

    public Personaje buscarPersonaje(String nombre) {
        return personajes.stream()
            .filter(p -> p.getNombre().equalsIgnoreCase(nombre))
            .findFirst()
            .orElse(null);
    }

    public void modificarPersonaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        Personaje p = buscarPersonaje(nombre);
        if (p == null) {
            throw new IllegalArgumentException("Personaje no encontrado");
        }
        p.setVida(vida);
        p.setAtaque(ataque);
        p.setDefensa(defensa);
        p.setAlcance(alcance);
        guardarPersonajes();
    }

    public void borrarPersonaje(String nombre) {
        personajes.removeIf(p -> p.getNombre().equalsIgnoreCase(nombre));
        guardarPersonajes();
    }
}
