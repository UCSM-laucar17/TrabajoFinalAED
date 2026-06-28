

public class GestorPrestamos {

    private ColaGenerica<SolicitudPrestamo> colaSolicitudes;
    private PilaGenerica<Prestamo> historialPrestamos;
    private Biblioteca biblioteca;

    public GestorPrestamos(Biblioteca biblioteca) {
        this.biblioteca         = biblioteca;
        this.colaSolicitudes    = new ColaGenerica<>();
        this.historialPrestamos = new PilaGenerica<>();
    }

    // ── SOLICITUDES ──────────────────────────────────────────

    /**
     * RF03 – Registrar solicitud al final de la cola.
     * Complejidad: O(1)
     */
    public void registrarSolicitud(String codEstudiante, String nomEstudiante, String codLibro) {
        // Validar que el libro exista antes de aceptar la solicitud
        Libro libro = biblioteca.buscarPorCodigo(codLibro.trim());
        if (libro == null) {
            System.out.println("No existe un libro con código '" + codLibro + "'. Solicitud rechazada.");
            return;
        }
        SolicitudPrestamo s = new SolicitudPrestamo(codEstudiante, nomEstudiante, codLibro);
        colaSolicitudes.enqueue(s);
        System.out.println("Solicitud registrada: " + s);
    }

    /**
     * RF03 – Mostrar toda la cola de solicitudes.
     * Complejidad: O(n)
     */
    public void mostrarSolicitudes() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }
        System.out.println("=== Cola de solicitudes pendientes ===");
        colaSolicitudes.mostrar();
    }

    /**
     * RF03 – Ver la siguiente solicitud sin retirarla.
     * Complejidad: O(1)
     */
    public void consultarSiguiente() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes en cola.");
            return;
        }
        System.out.println("Siguiente a atender: " + colaSolicitudes.peek());
    }

    /**
     * RF03 + RF04 – Atender la primera solicitud de la cola.
     * Intenta el préstamo y retira la solicitud de la cola en cualquier caso.
     * Complejidad: O(log n) por la búsqueda AVL dentro de prestarLibro()
     */
    public void atenderSolicitud() {
        if (colaSolicitudes.isEmpty()) {
            System.out.println("No hay solicitudes pendientes.");
            return;
        }
        SolicitudPrestamo siguiente = colaSolicitudes.peek();
        boolean exito = prestarLibro(siguiente);
        colaSolicitudes.dequeue(); // se retira siempre (solicitud atendida)

        if (!exito) {
            System.out.println("  La solicitud fue retirada de la cola sin completarse.");
        }
    }

    // ── PRÉSTAMOS ─────────────────────────────────────────────

    /**
     * RF04 – Flujo completo de préstamo.
     * 1. Verifica existencia del libro (O(log n) en AVL).
     * 2. Verifica disponibilidad.
     * 3. Cambia estado a prestado.
     * 4. Guarda en historial (pila) O(1).
     * Retorna true si el préstamo fue exitoso.
     */
    public boolean prestarLibro(SolicitudPrestamo solicitud) {
        // 1. Verificar existencia
        Libro libro = biblioteca.buscarPorCodigo(solicitud.getCodigoLibro());
        if (libro == null) {
            System.out.println("Libro con código '" + solicitud.getCodigoLibro() + "' no encontrado.");
            return false;
        }
        // 2. Verificar disponibilidad
        if (!libro.isDisponible()) {
            System.out.println("El libro '" + libro.getTitulo() + "' no está disponible actualmente.");
            return false;
        }
        // 3. Cambiar estado
        libro.setDisponible(false);

        // 4. Registrar en historial (pila)
        Prestamo prestamo = new Prestamo(solicitud);
        historialPrestamos.push(prestamo);

        System.out.println("Préstamo realizado exitosamente:");
        System.out.println("Libro    : " + libro.getTitulo());
        System.out.println("Estudiante: " + solicitud.getNombreEstudiante());
        System.out.println("Fecha    : " + prestamo.getFechaPrestamo());
        return true;
    }

    /**
     * Registrar devolución de un libro por código.
     * Complejidad: O(log n) por búsqueda AVL + O(n) para buscar en historial de pila.
     */
    public void devolverLibro(String codigoLibro) {
        if (codigoLibro == null || codigoLibro.isBlank()) {
            System.out.println("Código de libro inválido.");
            return;
        }
        codigoLibro = codigoLibro.trim();

        // Verificar que el libro exista
        Libro libro = biblioteca.buscarPorCodigo(codigoLibro);
        if (libro == null) {
            System.out.println("Libro con código '" + codigoLibro + "' no encontrado.");
            return;
        }
        // Verificar que esté efectivamente prestado
        if (libro.isDisponible()) {
            System.out.println("El libro '" + libro.getTitulo() + "' ya está disponible.");
            return;
        }
        // Cambiar estado a disponible
        libro.setDisponible(true);

        // Marcar el préstamo activo más reciente en el historial
        // La pila no permite acceso aleatorio: recorremos con mostrar() o con un auxiliar
        marcarDevolucionEnHistorial(codigoLibro);

        System.out.println("Devolución registrada:");
        System.out.println("  Libro: " + libro.getTitulo() + " ahora está disponible.");
    }

    /**
     * Recorre la pila buscando el préstamo activo más reciente del libro indicado.
     * Usa una pila auxiliar para no perder los demás elementos.
     * Complejidad: O(n)
     */
    private void marcarDevolucionEnHistorial(String codigoLibro) {
        PilaGenerica<Prestamo> auxiliar = new PilaGenerica<>();
        boolean marcado = false;

        // Vaciar la pila original a la auxiliar, marcando el primero activo encontrado
        while (!historialPrestamos.isEmpty()) {
            Prestamo p = historialPrestamos.pop();
            if (!marcado && p.getCodigoLibro().equals(codigoLibro) && p.estaActivo()) {
                p.registrarDevolucion();
                marcado = true;
            }
            auxiliar.push(p);
        }
        // Restaurar la pila original en el mismo orden
        while (!auxiliar.isEmpty()) {
            historialPrestamos.push(auxiliar.pop());
        }
    }

    /**
     * Muestra el historial completo de préstamos (desde el más reciente).
     * Complejidad: O(n)
     */
    public void mostrarHistorial() {
        if (historialPrestamos.isEmpty()) {
            System.out.println("El historial de préstamos está vacío.");
            return;
        }
        System.out.println("=== Historial de préstamos (más reciente primero) ===");
        historialPrestamos.mostrar();
    }

    // Getters para reportes
    public int getCantidadSolicitudesPendientes() { return colaSolicitudes.size(); }
    public int getCantidadPrestamosRealizados()   { return historialPrestamos.size(); }
}
