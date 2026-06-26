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

}
