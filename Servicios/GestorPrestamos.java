package Servicios;
import java.time.LocalDate;
import Modelos.*;
import Estructuras.*;

public class GestorPrestamos {
    private ColaGenerica<SolicitudPrestamo> colaSolicitudes;
    private PilaGenerica<Prestamo> historialPrestamos;
    private Biblioteca biblioteca;
    public GestorPrestamos(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.colaSolicitudes = new ColaGenerica<>();
        this.historialPrestamos = new PilaGenerica<>();
    }
    // solicitudes
    public boolean registrarSolicitud(String codigoEstudiante,String nomEstudiante,int codLibro) {
        Libro libro = biblioteca.buscarCodigo(codLibro);
        if (libro == null) {
            return false;
        }
        SolicitudPrestamo solicitud =new SolicitudPrestamo(codigoEstudiante,nomEstudiante,codLibro);
        colaSolicitudes.enqueue(solicitud);
        return true;
    }
    public void mostrarSolicitudes() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }
        System.out.println("=== Cola de solicitudes pendientes ===");
        colaSolicitudes.mostrar();
    }
    public void consultarSiguiente() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes en cola.");
            return;
        }
        try {
            System.out.println("Siguiente a atender: " + colaSolicitudes.peek());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public boolean atenderSolicitud(){
        if (colaSolicitudes.isEmpty()){
            return false;
        }
        try {
            SolicitudPrestamo siguiente = colaSolicitudes.peek();
            boolean exito = prestarLibro(siguiente);
            colaSolicitudes.dequeue();
            return exito;
        }catch (Exception e){
            return false;
        }
    }
    // prestamos
    public boolean prestarLibro(SolicitudPrestamo solicitud) {
        Libro libro = biblioteca.buscarCodigo(solicitud.getCodigoLibro());
        if (libro == null) {
            return false;
        }
        if (!libro.getEstado()) {
            return false;
        }
        libro.setEstado(false);
        historialPrestamos.push(new Prestamo(solicitud));
        return true;
    }
    public boolean devolverLibro(int codigoLibro){
        if (codigoLibro <= 0) {
            return false;
        }
        Libro libro = biblioteca.buscarCodigo(codigoLibro);
        if (libro == null) {
            return false;
        }
        if (libro.getEstado()) {
            return false;
        }
        libro.setEstado(true);
        marcarDevolucionEnHistorial(codigoLibro);
        return true;
    }
    private void marcarDevolucionEnHistorial(int codigoLibro) {
        PilaGenerica<Prestamo> auxiliar = new PilaGenerica<>();
        boolean marcado = false;
        while (!historialPrestamos.isEmpty()) {
            Prestamo p;
            try {
                p = historialPrestamos.pop();
            } catch (Exception e) {
                break;
            }
            if (!marcado && p.getCodigoLibro()==codigoLibro && p.estaActivo()) {
                p.registrarDevolucion();
                marcado = true;
            }
            auxiliar.push(p);
        }
        while (!auxiliar.isEmpty()) {
            try {
                historialPrestamos.push(auxiliar.pop());
            } catch (Exception ignored) {}
        }
    }
    public void mostrarHistorial() {
        if (historialPrestamos.isEmpty()) {
            System.out.println("El historial de préstamos está vacío.");
            return;
        }
        System.out.println("=== Historial de préstamos (más reciente primero) ===");
        historialPrestamos.mostrar();
    }
    public int getCantidadSolicitudesPendientes() {
        return colaSolicitudes.size();
    }
    public int getCantidadPrestamosRealizados() {
        return historialPrestamos.size();
    }
    public String obtenerSiguienteSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            return "No hay solicitudes pendientes.";
        }
        try {
            return colaSolicitudes.peek().toString();
        } catch (Exception e) {
            return "No hay solicitudes.";
        }
    }   
    public String obtenerSolicitudesTexto() {
        //cola
        return colaSolicitudes.obtenerContenido();
    }
    public String obtenerHistorialTexto() {
        //pila
        return historialPrestamos.obtenerContenido();
    }
    public int solicitudesPendientes(){
        return colaSolicitudes.size();
    }
}
