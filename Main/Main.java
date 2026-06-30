package Main;
import Interfaz.VentanaPrincipal;
import Servicios.GestorArchivos;
import Servicios.SistemaBiblioteca;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) {
        // Cargar libros almacenados
        GestorArchivos.cargarLibrosCSV(SistemaBiblioteca.biblioteca);
        javax.swing.SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            // Guardar automáticamente al cerrar
            ventana.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE); //evita que la ventana se cierre 
            ventana.addWindowListener(new WindowAdapter() {  //es un "escuchador oara detectar cuando el usuario inyenta cerrar"
                public void windowClosing(WindowEvent e) { //acción de ejecutar antes de cerrar 
                    GestorArchivos.guardarLibrosCSV(SistemaBiblioteca.biblioteca); //almacena los datos en un CSV
                    ventana.dispose();
                    System.exit(0);
                }
            });
            ventana.setVisible(true);
        });
    }
}
