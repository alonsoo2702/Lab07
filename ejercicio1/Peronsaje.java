package ejercicio1;

public class Personaje {
    private String nombre;
    private int vida;
    private int ataque;
    private int defensa;
    private int alcance;

    public Personaje(String nombre, int vida, int ataque, int defensa, int alcance) {
        setNombre(nombre);
        setVida(vida);
        setAtaque(ataque);
        setDefensa(defensa);
        setAlcance(alcance);
    }

    // Getters
    public String getNombre() { return nombre; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefensa() { return defensa; }
    public int getAlcance() { return alcance; }

    // Setters con validación
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    private void validarValorPositivo(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser mayor que cero");
        }
    }

    public void setVida(int vida) {
        validarValorPositivo(vida, "Vida");
        this.vida = vida;
    }

    public void setAtaque(int ataque) {
        validarValorPositivo(ataque, "Ataque");
        this.ataque = ataque;
    }

    public void setDefensa(int defensa) {
        validarValorPositivo(defensa, "Defensa");
        this.defensa = defensa;
    }

    public void setAlcance(int alcance) {
        validarValorPositivo(alcance, "Alcance");
        this.alcance = alcance;
    }

    @Override
    public String toString() {
        return String.format("%s,%d,%d,%d,%d", nombre, vida, ataque, defensa, alcance);
    }
}
