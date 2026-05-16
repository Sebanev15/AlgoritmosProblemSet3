package ucu.edu.aed.ej15;

public class Libro {
    private String isbn ;
    private String titulo ;
    private String autor ;
    private int anio ;

    public Libro(String isbn, String titulo, String autor, int anio) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
    }

    public String getIsbn() {return isbn;}
    public String getTitulo() {return titulo;}
    public String getAutor() {return autor;}
    public int getAnio() {return anio;}

    public void setIsbn(String isbn) {this.isbn = isbn;}
    public void setTitulo(String titulo) {this.titulo = titulo;}
    public void setAutor(String autor) {this.autor = autor;}
    public void setAnio(int anio) {this.anio = anio;}

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Libro other = (Libro) obj;
        return isbn.equals(other.isbn);
    }

    @Override
    public int hashCode() {
        return isbn.hashCode();
    }
}
