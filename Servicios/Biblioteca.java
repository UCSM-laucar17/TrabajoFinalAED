package servicios;

import estructuras.ArbolAVL;
import modelos.Libro;

public class Biblioteca {

    private ArbolAVL<Libro> catalogo;

    // Constructor
    public Biblioteca() {
        catalogo = new ArbolAVL<>();
    }

    // Registrar un libro
    public void registrarLibro(Libro libro) {

        if (libro == null) {
            System.out.println("No se puede registrar un libro nulo.");
            return;
        }

        // Verifica que no exista otro libro con el mismo código
        if (buscarCodigo(libro.getCodigo()) != null) {
            System.out.println("Ya existe un libro con ese código.");
            return;
        }

        catalogo.insertar(libro);
        System.out.println("Libro registrado correctamente.");
    }

    // Buscar libro por código
    public Libro buscarCodigo(int codigo) {

        Libro aux = new Libro(codigo, "", "", "", 0, true);

        return catalogo.buscar(aux);
    }

    // Eliminar libro por código
    public void eliminarLibro(int codigo) {

        Libro libro = buscarCodigo(codigo);

        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        catalogo.eliminar(libro);
        System.out.println("Libro eliminado correctamente.");
    }
    // Modifica la información de un libro existente
    public void modificarLibro(int codigo, String titulo, String autor,
                               String categoria, int anio, boolean estado) {
    
        // Busca el libro por su código
        Libro libro = buscarCodigo(codigo);
    
        // Si no existe, no se puede modificar
        if (libro == null) {
            System.out.println("Libro no encontrado.");
            return;
        }
    
        // Actualiza los datos del libro
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setCategoria(categoria);
        libro.setAnio(anio);
        libro.setEstado(estado);
    
        System.out.println("Libro modificado correctamente.");
    }
    // Muestra todos los libros registrados en el catálogo
    public void mostrarLibros() {
    
        // Si el árbol está vacío, se informa al usuario
        if (catalogo.estaVacio()) {
            System.out.println("No hay libros registrados.");
            return;
        }
    
        // El árbol ya posee un método para mostrar sus elementos en orden
        catalogo.mostrar();
    }
    // Muestra únicamente los libros disponibles
    public void mostrarDisponibles() {
    
        if (catalogo.estaVacio()) {
            System.out.println("No hay libros registrados.");
            return;
        }
    
        System.out.println("===== LIBROS DISPONIBLES =====");
    
        mostrarDisponibles(catalogo.getRaiz());
    }
    // Recorre el árbol en inOrden mostrando únicamente
    // los libros cuyo estado sea Disponible
    private void mostrarDisponibles(estructuras.NodoAVL<Libro> nodo) {
    
        // Caso base de la recursión
        if (nodo == null) {
            return;
        }
    
        // Recorre el subárbol izquierdo
        mostrarDisponibles(nodo.getIzquierdo());
    
        // Si el libro está disponible se muestra
        if (nodo.getDato().getEstado()) {
            System.out.println(nodo.getDato());
        }
    
        // Recorre el subárbol derecho
        mostrarDisponibles(nodo.getDerecho());
    }
    // Muestra únicamente los libros prestados
    public void mostrarPrestados() {
    
        if (catalogo.estaVacio()) {
            System.out.println("No hay libros registrados.");
            return;
        }
    
        System.out.println("===== LIBROS PRESTADOS =====");
    
        mostrarPrestados(catalogo.getRaiz());
    }
    // Recorre el árbol mostrando únicamente
    // los libros cuyo estado sea Prestado
    private void mostrarPrestados(estructuras.NodoAVL<Libro> nodo) {
    
        // Caso base
        if (nodo == null) {
            return;
        }
    
        // Recorre el subárbol izquierdo
        mostrarPrestados(nodo.getIzquierdo());
    
        // Si el libro NO está disponible significa que está prestado
        if (!nodo.getDato().getEstado()) {
            System.out.println(nodo.getDato());
        }
    
        // Recorre el subárbol derecho
        mostrarPrestados(nodo.getDerecho());
    }

}
