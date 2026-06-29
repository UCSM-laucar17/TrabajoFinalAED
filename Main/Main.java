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
            ventana.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            ventana.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    GestorArchivos.guardarLibrosCSV(SistemaBiblioteca.biblioteca);
                    ventana.dispose();
                    System.exit(0);
                }
            });
            ventana.setVisible(true);
        });
    }
}
