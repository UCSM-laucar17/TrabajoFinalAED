package Test;
import Modelos.Libro;
import Servicios.Biblioteca;

public class AVLTest {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();
        biblioteca.registrarLibro(new Libro(10,"Java","Autor A","Programación",2020,true));
        biblioteca.registrarLibro(new Libro(5,"C++","Autor B","Programación",2018,true));
        System.out.println("catalogo");
        biblioteca.mostrarLibros();
        System.out.println("\nBuscar:");
        System.out.println(biblioteca.buscarCodigo(5));
        System.out.println("\nEliminar");
        biblioteca.eliminarLibro(10);
        System.out.println("\ncatalogo:");
        biblioteca.mostrarLibros();
    }
}
