
import java.time.LocalDate;

public class SolicitudPrestamo {

    private String codigoEstudiante;
    private String nombreEstudiante;
    private String codigoLibro;
    private LocalDate fechaSolicitud;

    public SolicitudPrestamo(String codigoEstudiante, String nombreEstudiante, String codigoLibro) {
        if (codigoEstudiante == null) {
            throw new IllegalArgumentException("El código del estudiante no puede estar vacío.");

        }
        if (nombreEstudiante == null) {
            throw new IllegalArgumentException("El nombre del estudiante no puede estar vacío.");
        }
        if (codigoLibro == null) {
            throw new IllegalArgumentException("El código del libro no puede estar vacío.");
        }

        this.codigoEstudiante = codigoEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.codigoLibro      = codigoLibro;
        this.fechaSolicitud   = LocalDate.now();
    }

    public String getCodigoEstudiante() { return codigoEstudiante; }
    public String getNombreEstudiante() { return nombreEstudiante; }
    public String getCodigoLibro()      { return codigoLibro; }
    public LocalDate getFechaSolicitud(){ return fechaSolicitud; }

    @Override
    public String toString() {
        return String.format("[Solicitud] Estudiante: %s (%s) | Libro: %s | Fecha: %s",
                nombreEstudiante, codigoEstudiante, codigoLibro, fechaSolicitud);
    }
}
