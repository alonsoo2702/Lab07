package ejercicio4;

import java.io.*;
import java.util.*;

public class ContadorPalabras {
 public static void main(String[] args) {
     // Variables para el conteo
     int lineas = 0;
     int palabras = 0;
     int caracteres = 0;
     Map<String, Integer> frecuenciaPalabras = new HashMap<>();

     try {
         // Abre el archivo (asumimos que está en el mismo directorio)
         FileReader archivo = new FileReader("lear.txt");
         BufferedReader lector = new BufferedReader(archivo);
         String linea;

         // Lee el archivo línea por línea
         while ((linea = lector.readLine()) != null) {
             lineas++;
             caracteres += linea.length();
             
             // Divide la línea en palabras
             String[] palabrasEnLinea = linea.split("\\s+");
             for (String palabra : palabrasEnLinea) {
                 // Solo cuenta si contiene letras o números
                 if (!palabra.isEmpty()) {
                     palabras++;
                     // Guarda la frecuencia de cada palabra
                     palabra = palabra.toLowerCase();
                     frecuenciaPalabras.put(palabra, 
                         frecuenciaPalabras.getOrDefault(palabra, 0) + 1);
                 }
             }
         }
         
         lector.close();

         // Calcula el promedio de palabras por línea
         double promedio = (double) palabras / lineas;

         // Muestra los resultados
         System.out.println("Resultados del análisis:");
         System.out.println("Total de líneas: " + lineas);
         System.out.println("Total de palabras: " + palabras);
         System.out.println("Total de caracteres: " + caracteres);
         System.out.printf("Promedio de palabras por línea: %.2f%n", promedio);
         
         // Muestra las palabras más frecuentes
         System.out.println("\nPalabras más frecuentes:");
         frecuenciaPalabras.entrySet().stream()
             .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
             .limit(5)
             .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue() + " veces"));

     } catch (IOException e) {
         System.out.println("Error al leer el archivo: " + e.getMessage());
     }
 }
}
