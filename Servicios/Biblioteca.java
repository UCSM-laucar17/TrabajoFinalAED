package Servicios;

import Estructuras.ArbolAVL;
import Modelos.Libro;
import Estructuras.ListaEnlazada;
import Estructuras.NodoAVL;

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
    public void modificarLibro(int codigo, String titulo, String autor, String categoria, int anio, boolean estado) {
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
    // Busca todos los libros cuyo título coincida con el indicado
    public ListaEnlazada<Libro> buscarPorTitulo(String titulo) {
        // Lista donde se almacenarán los libros encontrados
        ListaEnlazada<Libro> encontrados = new ListaEnlazada<>();
        // Recorre todo el árbol buscando coincidencias
        buscarPorTitulo(catalogo.getRaiz(), titulo, encontrados);
        return encontrados;
    }
    // Método recursivo que recorre el árbol
    private void buscarPorTitulo(NodoAVL<Libro> nodo, String título, ListaEnlazada<Libro> encontrados) {
        // Caso base
        if (nodo == null) {
            return;
        }
        // Recorre el subárbol izquierdo
        buscarPorTitulo(nodo.getIzquierdo(), titulo, encontrados);
        // Obtiene el libro almacenado en el nodo
        Libro libro = nodo.getDato();
        // Si el título coincide, se agrega a la lista
        if (libro.getTitulo().equalsIgnoreCase(titulo)) {
            encontrados.insertar(libro);
        }
        // Recorre el subárbol derecho
        buscarPorTitulo(nodo.getDerecho(), titulo, encontrados);
    }
    // Busca todos los libros de un determinado autor
    public ListaEnlazada<Libro> buscarPorAutor(String autor) {
        ListaEnlazada<Libro> encontrados = new ListaEnlazada<>();
        buscarPorAutor(catalogo.getRaiz(), autor, encontrados);
        return encontrados;
    }
    // Método recursivo
    private void buscarPorAutor(NodoAVL<Libro> nodo, String autor, ListaEnlazada<Libro> encontrados) {
        if (nodo == null) {
            return;
        }
        buscarPorAutor(nodo.getIzquierdo(), autor, encontrados);
        Libro libro = nodo.getDato();
        // Compara el autor sin importar mayúsculas o minúsculas
        if (libro.getAutor().equalsIgnoreCase(autor)) {
            encontrados.insertar(libro);
        }
        buscarPorAutor(nodo.getDerecho(), autor, encontrados);
    }
    // Busca todos los libros pertenecientes a una categoría
    public ListaEnlazada<Libro> buscarPorCategoria(String categoria) {
        ListaEnlazada<Libro> encontrados = new ListaEnlazada<>();
        buscarPorCategoria(catalogo.getRaiz(), categoria, encontrados);
        return encontrados;
    }
    // Método recursivo
    private void buscarPorCategoria(NodoAVL<Libro> nodo, String categoría, ListaEnlazada<Libro> encontrados) {
        if (nodo == null) {
            return;
        }
        buscarPorCategoria(nodo.getIzquierdo(), categoria, encontrados);
    
        Libro libro = nodo.getDato();
        // Si pertenece a la categoría buscada, se agrega a la lista
        if (libro.getCategoria().equalsIgnoreCase(categoria)) {
            encontrados.insertar(libro);
        }
        buscarPorCategoria(nodo.getDerecho(), categoria, encontrados);
    }
    // Recorre el árbol y agrega todos los libros a una lista
    private void obtenerLibros(NodoAVL<Libro> nodo, ListaEnlazada<Libro> lista) {
        // Caso base
        if (nodo == null) {
            return;
        }
        // Recorre el árbol en inOrden
        obtenerLibros(nodo.getIzquierdo(), lista);
        // Agrega el libro a la lista
        lista.insertar(nodo.getDato());
        obtenerLibros(nodo.getDerecho(), lista);
    }
    // Ordena los libros alfabéticamente por título
    public void ordenarPorTitulo() {
        // Lista donde se copiarán todos los libros del árbol
        ListaEnlazada<Libro> lista = new ListaEnlazada<>();
        // Obtiene todos los libros del árbol
        obtenerLibros(catalogo.getRaiz(), lista);
        // Algoritmo Bubble Sort
        for (int i = 0; i < lista.size() - 1; i++) {
            for (int j = 0; j < lista.size() - i - 1; j++) {
                // Obtiene dos libros consecutivos
                Libro libro1 = lista.obtener(j);
                Libro libro2 = lista.obtener(j + 1);
                // Si el primero va después del segundo alfabéticamente
                if (libro1.getTitulo().compareToIgnoreCase(libro2.getTitulo()) > 0) {
                    // Intercambia las posiciones
                    lista.set(j, libro2);
                    lista.set(j + 1, libro1);
                }
            }
        }
    
        // Muestra la lista ordenada
        lista.mostrar();
    }

}
