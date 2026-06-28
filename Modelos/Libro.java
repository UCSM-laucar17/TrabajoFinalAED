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
    public Libro(int codigo, String titulo, String autor, String categoria, int anio, boolean estado) {
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
     // Setters
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setAnio(int anio) {
        this.anio = anio;
    }
    public void setEstado(boolean estado) {
        this.estado = estado;
    }
     // Comparable
    @Override
    public int compareTo(Libro otro) {
        if (this.codigo < otro.codigo){
            return -1;
        }
        if (this.codigo > otro.codigo){
            return 1;
        }
        return 0;
    } 
    // toString()
    @Override
    public String toString() {
        String estadoLibro;
        if (estado) {
               estadoLibro = "Disponible";
        } else {
               estadoLibro = "Prestado";
        }
        return "Código: " + codigo +
               " | Título: " + titulo +
               " | Autor: " + autor +
               " | Categoría: " + categoria +
               " | Año: " + anio +
               " | Estado: " + estadoLibro;
    }
}