package ejercicio2;
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
                if (datos.length >= 5) {
                    int nivel = datos.length == 6 ? Integer.parseInt(datos[5]) : 1;
                    Personaje p = new Personaje(
                        datos[0],
                        Integer.parseInt(datos[1]),
                        Integer.parseInt(datos[2]),
                        Integer.parseInt(datos[3]),
                        Integer.parseInt(datos[4]),
                        nivel
                    );
                    personajes.add(p);
                }
            }
        } catch (IOException e) {
            System.out.println("Archivo no encontrado. Se creará uno nuevo.");
        }
    }

    public void guardarPersonajes() {
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
        System.out.printf("%-15s %-8s %-8s %-8s %-8s %-8s%n", 
            "Nombre", "Vida", "Ataque", "Defensa", "Alcance", "Nivel");
        System.out.println("-".repeat(60));
        for (Personaje p : personajes) {
            System.out.printf("%-15s %-8d %-8d %-8d %-8d %-8d%n",
                p.getNombre(), p.getVida(), p.getAtaque(), p.getDefensa(), 
                p.getAlcance(), p.getNivel());
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

    // Nuevos métodos de filtrado
    public void mostrarPersonajesOrdenados(String atributo) {
        List<Personaje> ordenados = new ArrayList<>(personajes);
        
        Comparator<Personaje> comparador = switch (atributo.toLowerCase()) {
            case "vida" -> Comparator.comparing(Personaje::getVida);
            case "ataque" -> Comparator.comparing(Personaje::getAtaque);
            case "defensa" -> Comparator.comparing(Personaje::getDefensa);
            case "alcance" -> Comparator.comparing(Personaje::getAlcance);
            case "nivel" -> Comparator.comparing(Personaje::getNivel);
            default -> Comparator.comparing(Personaje::getNombre);
        };
        
        ordenados.sort(comparador.reversed());  // Ordenar de mayor a menor
        
        System.out.println("\nPersonajes ordenados por " + atributo + ":");
        mostrarListaPersonajes(ordenados);
    }

    private void mostrarListaPersonajes(List<Personaje> lista) {
        System.out.printf("%-15s %-8s %-8s %-8s %-8s %-8s%n", 
            "Nombre", "Vida", "Ataque", "Defensa", "Alcance", "Nivel");
        System.out.println("-".repeat(60));
        for (Personaje p : lista) {
            System.out.printf("%-15s %-8d %-8d %-8d %-8d %-8d%n",
                p.getNombre(), p.getVida(), p.getAtaque(), p.getDefensa(), 
                p.getAlcance(), p.getNivel());
        }
    }

    public void mostrarEstadisticas() {
        if (personajes.isEmpty()) {
            System.out.println("No hay personajes para mostrar estadísticas.");
            return;
        }

        double vidaPromedio = personajes.stream().mapToInt(Personaje::getVida).average().orElse(0);
        double ataquePromedio = personajes.stream().mapToInt(Personaje::getAtaque).average().orElse(0);
        double defensaPromedio = personajes.stream().mapToInt(Personaje::getDefensa).average().orElse(0);
        double alcancePromedio = personajes.stream().mapToInt(Personaje::getAlcance).average().orElse(0);
        double nivelPromedio = personajes.stream().mapToInt(Personaje::getNivel).average().orElse(0);

        System.out.println("\nEstadísticas Generales:");
        System.out.println("-".repeat(40));
        System.out.printf("Total de personajes: %d%n", personajes.size());
        System.out.printf("Vida promedio: %.2f%n", vidaPromedio);
        System.out.printf("Ataque promedio: %.2f%n", ataquePromedio);
        System.out.printf("Defensa promedio: %.2f%n", defensaPromedio);
        System.out.printf("Alcance promedio: %.2f%n", alcancePromedio);
        System.out.printf("Nivel promedio: %.2f%n", nivelPromedio);
        
        Personaje masFuerte = personajes.stream()
            .max(Comparator.comparingInt(p -> 
                p.getVida() + p.getAtaque() + p.getDefensa() + p.getAlcance()))
            .orElse(null);
            
        if (masFuerte != null) {
            System.out.println("\nPersonaje más fuerte: " + masFuerte.getNombre());
        }
    }

    public void subirNivelPersonaje(String nombre) {
        Personaje p = buscarPersonaje(nombre);
        if (p == null) {
            throw new IllegalArgumentException("Personaje no encontrado");
        }
        p.subirNivel();
        guardarPersonajes();
        System.out.printf("¡%s ha subido al nivel %d!%n", p.getNombre(), p.getNivel());
    }
}
