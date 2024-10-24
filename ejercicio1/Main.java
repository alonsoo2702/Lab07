package ejercicio1;

public class Main {
    public static void main(String[] args) {
        Gestor gestor = new Gestor();

        // Agregar personajes iniciales
        try {
            gestor.agregarPersonaje(new Personaje("Caballero", 4, 2, 4, 2));
            gestor.agregarPersonaje(new Personaje("Guerrero", 2, 4, 2, 4));
            gestor.agregarPersonaje(new Personaje("Arquero", 2, 4, 1, 8));
        } catch (IllegalArgumentException e) {
            System.out.println("Algunos personajes ya existían en el archivo");
        }

        // Mostrar personajes
        gestor.mostrarPersonajes();

        // Ejemplo de modificación
        try {
            gestor.modificarPersonaje("Caballero", 5, 3, 4, 2);
            System.out.println("\nDespués de modificar al Caballero:");
            gestor.mostrarPersonajes();
        } catch (IllegalArgumentException e) {
            System.out.println("Error al modificar: " + e.getMessage());
        }

        // Ejemplo de borrado
        gestor.borrarPersonaje("Arquero");
        System.out.println("\nDespués de borrar al Arquero:");
        gestor.mostrarPersonajes();
    }
}
