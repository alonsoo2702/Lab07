package ejercicio3;

public class Main {
    public static void main(String[] args) {
        EmpleadoDAO modelo = new EmpleadoDAO();
        EmpleadoVista vista = new EmpleadoVista();
        EmpleadoControlador controlador = new EmpleadoControlador(modelo, vista);
        controlador.iniciar();
    }
}
