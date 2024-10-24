package ejercicio2;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();

        // Agregar personajes iniciales
        try {
            gestor.agregarPersonaje(new Personaje("Caballero", 4, 2, 4, 2));
            gestor.agregarPersonaje(new Personaje("Guerrero", 2, 4, 2, 4));
            gestor.agregarPersonaje(new Personaje("Arquero", 2, 4, 1, 8));
            gestor.agregarPersonaje(new Personaje("Mago", 3, 5, 1, 6));
        } catch (IllegalArgumentException e) {
            System.out.println("Algunos personajes ya existían en el archivo");
        }

        // Mostrar personajes iniciales
        gestor.mostrarPersonajes();

        // Probar nuevas funcionalidades
        System.out.println("\n=== Estadísticas Generales ===");
        gestor.mostrarEstadisticas();

        System.out.println("\n=== Personajes Ordenados por Ataque ===");
        gestor.mostrarPersonajesOrdenados("ataque");

        System.out.println("\n=== Subir de Nivel ===");
        try {
            gestor.subirNivelPersonaje("Caballero");
            gestor.subirNivelPersonaje("Arquero");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Estado Final de los Personajes ===");
        gestor.mostrarPersonajes();
    }
}
