
package Test;

import Modelos.Libro;
import Servicios.Biblioteca;
import Servicios.GestorPrestamos;


public class PrestamoTest {

    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        biblioteca.registrarLibro(new Libro(1,"Librodesconocido","SUNpry","Nose",2023,true));
        biblioteca.registrarLibro(new Libro(2,"Wattpad","Mark","Weebboom",2022,true));
        GestorPrestamos gestor = new GestorPrestamos(biblioteca);

        gestor.registrarSolicitud("2025001","Fernando",1);
        gestor.registrarSolicitud("2025002","Mikoto",2);
        System.out.println("\nSolicitudes:");
        System.out.println(gestor.obtenerSolicitudesTexto());

        gestor.atenderSolicitud();
        System.out.println("\nHistorial:");
        System.out.println(gestor.obtenerHistorialTexto());

        gestor.devolverLibro(1);
        System.out.println("\nHistorial actualizado:");
        System.out.println(gestor.obtenerHistorialTexto());

    }

}
