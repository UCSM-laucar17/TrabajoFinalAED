
import java.io.*;
import java.util.List;

public class GestorArchivos {

    private static final String RUTA_CSV = "libros.csv";
    private static final String CABECERA  = "codigo,titulo,autor,categoria,anio,estado";

    /**
     * Carga libros desde el CSV y los registra en la Biblioteca.
     * Omite la cabecera y líneas con formato incorrecto.
     * Complejidad: O(n log n) — n inserciones O(log n) en el AVL.
     */
    public static void cargarLibrosCSV(Biblioteca biblioteca) {
        File archivo = new File(RUTA_CSV); //Colocar el enlace de la unicabion del txt 
        if (!archivo.exists()) {
            System.out.println("Archivo '" + RUTA_CSV + "' no encontrado. Se inicia con catálogo vacío.");
            return;
        }
        int cargados = 0, errores = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) { primeraLinea = false; continue; } // saltar cabecera
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                String[] partes = linea.split(",", 6);
                if (partes.length < 6) {
                    errores++;
                    continue;
                }
                try {
                    String  codigo    = partes[0].trim();
                    String  titulo    = partes[1].trim();
                    String  autor     = partes[2].trim();
                    String  categoria = partes[3].trim();
                    int     anio      = Integer.parseInt(partes[4].trim());
                    boolean disponible = partes[5].trim().equalsIgnoreCase("Disponible");

                    // Usar registrarLibro() de Biblioteca para pasar por sus validaciones
                    Libro libro = new Libro(codigo, titulo, autor, categoria, anio, disponible);
                    biblioteca.registrarLibroDirecto(libro); // ver nota abajo
                    cargados++;
                } catch (NumberFormatException e) {
                    System.out.println("  Línea ignorada (año inválido): " + linea);
                    errores++;
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        System.out.printf("CSV cargado: %d libros importados, %d líneas con error.%n", cargados, errores);
    }

    /**
     * Guarda todos los libros de la Biblioteca en el CSV.
     * Obtiene la lista mediante inOrden() del AVL (orden por código).
     * Complejidad: O(n)
     */
    public static void guardarLibrosCSV(Biblioteca biblioteca) {
        List<Libro> libros = biblioteca.obtenerTodosInOrden();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_CSV))) {
            bw.write(CABECERA);
            bw.newLine();

            for (Libro libro : libros) {
                String estado = libro.isDisponible() ? "Disponible" : "Prestado";
                String linea  = String.join(",",
                        libro.getCodigo(),
                        libro.getTitulo(),
                        libro.getAutor(),
                        libro.getCategoria(),
                        String.valueOf(libro.getAnio()),
                        estado);
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Catálogo guardado en '" + RUTA_CSV + "' (" + libros.size() + " libros).");
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo CSV: " + e.getMessage());
        }
    }
}

