package modelos;
public class Libro implements Comparable<Libro> {

    // Atributos
    private int codigo;
    private String titulo;
    private String autor;
    private String categoria;
    private int anio;
    private boolean estado; // true = Disponible | false = Prestado

    // Constructor 
    public Libro(int codigo, String titulo, String autor,
                 String categoria, int anio, boolean estado) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.anio = anio;
        this.estado = estado;
    }
     // Getters

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getAnio() {
        return anio;
    }

    public boolean getEstado() {
        return estado;
    }
}
