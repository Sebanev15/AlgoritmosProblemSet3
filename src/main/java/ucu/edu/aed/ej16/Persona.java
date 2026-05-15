package ucu.edu.aed.ej16;


public class Persona implements Comparable<Persona>{
    private String nombre;
    private int anioNacimiento;

    // La clase Persona funciona como un nodo muy simple, no necesita tener 
    // la responsabilidad de conocer ni sus padres ni sus hijos
    public Persona(String nombre, int anioNacimiento) {
        this.nombre = nombre;
        this.anioNacimiento = anioNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public int getAnioNacimiento() {
        return anioNacimiento;
    }

    @Override
    public int compareTo(Persona otro) {
        return this.nombre.compareTo(otro.nombre);
    }

    @Override
    public String toString() {
        return nombre + " (" + anioNacimiento + ")";
    }
}
