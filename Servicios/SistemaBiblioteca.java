package Servicios;

public class SistemaBiblioteca {

    public static Biblioteca biblioteca = new Biblioteca();
    public static GestorPrestamos gestorPrestamos =new GestorPrestamos(biblioteca);

}
