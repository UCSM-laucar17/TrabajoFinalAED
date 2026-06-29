package Servicios;
import java.time.LocalDate;
import Modelos.*;
import Estructuras.*;

public class GestorPrestamosbin {
    private ColaGenerica<SolicitudPrestamo> colaSolicitudes;
    private PilaGenerica<Prestamo> historialPrestamos;
    private Biblioteca biblioteca;

    public GestorPrestamosbin(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
        this.colaSolicitudes = new ColaGenerica<>();
        this.historialPrestamos = new PilaGenerica<>();
    }

    // ====================== SOLICITUDES ======================
    public void registrarSolicitud(String codEstudiante, String nomEstudiante, int codLibro) {
        Libro libro = biblioteca.buscarCodigo(codLibro);
        if (libro == null) {
            System.out.println("No existe un libro con código '" + codLibro + "'. Solicitud rechazada.");
            return;
        }

        SolicitudPrestamo s = new SolicitudPrestamo(codEstudiante, nomEstudiante, codLibro);
        colaSolicitudes.enqueue(s);
        System.out.println("Solicitud registrada: " + s);
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

    public void atenderSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }

        SolicitudPrestamo siguiente = null;
        try {
            siguiente = colaSolicitudes.peek();
            boolean exito = prestarLibro(siguiente);
            colaSolicitudes.dequeue();

            if (!exito) {
                System.out.println("La solicitud fue retirada de la cola sin completarse.");
            }
        } catch (Exception e) {
            System.out.println("Error al atender solicitud: " + e.getMessage());
        }
    }

    // ====================== PRÉSTAMOS ======================
    public boolean prestarLibro(SolicitudPrestamo solicitud) {
        Libro libro = biblioteca.buscarCodigo(solicitud.getCodigoLibro());
        if (libro == null) {
            System.out.println("Libro con código '" + solicitud.getCodigoLibro() + "' no encontrado.");
            return false;
        }

        if (!libro.getEstado()) {
            System.out.println("El libro '" + libro.getTitulo() + "' no está disponible actualmente.");
            return false;
        }

        libro.setEstado(false);        
        Prestamo prestamo = new Prestamo(solicitud);
        historialPrestamos.push(prestamo);

        System.out.println("Préstamo realizado exitosamente:");
        System.out.println("   Libro     : " + libro.getTitulo());
        System.out.println("   Estudiante: " + solicitud.getNombreEstudiante());
        System.out.println("   Fecha     : " + prestamo.getFechaPrestamo());
        return true;
    }

    public void devolverLibro(int codigoLibro) {
        if (codigoLibro <=0) {
            System.out.println("Código de libro inválido.");
            return;
        }

        Libro libro = biblioteca.buscarCodigo(codigoLibro);

        if (libro == null) {
            System.out.println("Libro con código '" + codigoLibro + "' no encontrado.");
            return;
        }

        if (libro.getEstado()) {
            System.out.println("El libro '" + libro.getTitulo() + "' ya está disponible.");
            return;
        }

        libro.setEstado(true);
        marcarDevolucionEnHistorial(codigoLibro);

        System.out.println("Devolución registrada para: " + libro.getTitulo());
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
}
