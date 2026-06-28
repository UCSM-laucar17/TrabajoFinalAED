import java.time.LocalDate;

public class Prestamo {

    private String codigoEstudiante;
    private String nombreEstudiante;
    private String codigoLibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    public Prestamo(SolicitudPrestamo solicitud) {
        this.codigoEstudiante = solicitud.getCodigoEstudiante();
        this.nombreEstudiante = solicitud.getNombreEstudiante();
        this.codigoLibro      = solicitud.getCodigoLibro();
        this.fechaPrestamo    = LocalDate.now();
        this.fechaDevolucion  = null;
    }

    public void registrarDevolucion() {
        this.fechaDevolucion = LocalDate.now();
    }

    public boolean estaActivo() {
        return fechaDevolucion == null;
    }

    public String getCodigoEstudiante(){ 
        return codigoEstudiante; 
    }
    public String getNombreEstudiante() { 
        return nombreEstudiante; 
    }
    public String getCodigoLibro(){ 
        return codigoLibro; 
    }
    public LocalDate getFechaPrestamo() { 
        return fechaPrestamo; 
    }
    public LocalDate getFechaDevolucion(){ 
        return fechaDevolucion; 
    }

    @Override
    public String toString() {
        String dev = (fechaDevolucion != null) ? fechaDevolucion.toString() : "En préstamo";
        return String.format("[Préstamo] Libro: %s | Estudiante: %s | Prestado: %s | Devuelto: %s",
                codigoLibro, nombreEstudiante, fechaPrestamo, dev);
    }
}
