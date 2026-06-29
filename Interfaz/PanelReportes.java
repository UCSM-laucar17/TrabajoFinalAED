package Interfaz;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import Reportes.ExportadorReportes;
import Servicios.SistemaBiblioteca;
import Reportes.ExportadorReportes.*;

public class PanelReportes extends JPanel{
    private JLabel lblTotal;
    private JLabel lblDisponibles;
    private JLabel lblPrestados;
    private JLabel lblSolicitudes;
    private JButton btnActualizar;
    private JButton btnExportarTXT;
    private JButton btnExportarCSV;
    public PanelReportes(){
        iniciarComponentes();
        actualizarReportes();
    }
    private void iniciarComponentes(){
        setLayout(new BorderLayout());
        setBackground(new Color(245,247,250));
        JLabel titulo = new JLabel("REPORTES");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI",Font.BOLD,26));
        titulo.setBorder(new EmptyBorder(20,10,20,10));
        add(titulo,BorderLayout.NORTH);
        JPanel centro = new JPanel(new GridLayout(4,2,15,15));
        centro.setBorder(new EmptyBorder(30,50,30,50));
        centro.setBackground(Color.WHITE);
        centro.add(new JLabel("Total de libros"));
        lblTotal = new JLabel();
        centro.add(lblTotal);
        centro.add(new JLabel("Libros disponibles"));
        lblDisponibles = new JLabel();
        centro.add(lblDisponibles);
        centro.add(new JLabel("Libros prestados"));
        lblPrestados = new JLabel();
        centro.add(lblPrestados);
        centro.add(new JLabel("Solicitudes pendientes"));
        lblSolicitudes = new JLabel();
        centro.add(lblSolicitudes);
        add(centro,BorderLayout.CENTER);
        JPanel botones = new JPanel();
        botones.setBackground(new Color(245,247,250));
        btnActualizar = crearBoton("Actualizar");
        btnExportarTXT = crearBoton("Exportar TXT");
        btnExportarCSV = crearBoton("Exportar CSV");
        botones.add(btnActualizar);
        botones.add(btnExportarTXT);
        botones.add(btnExportarCSV);
        add(botones,BorderLayout.SOUTH);
        agregarEventos();
    }
    private JButton crearBoton(String texto){
            JButton boton = new JButton(texto);
            boton.setBackground(new Color(52,152,219));
            boton.setForeground(Color.WHITE);
            boton.setFocusPainted(false);
            boton.setFont(new Font("Segoe UI",Font.BOLD,14));
            boton.addMouseListener(new MouseAdapter(){
                public void mouseEntered(MouseEvent e){
                    boton.setBackground(new Color(41,128,185));
                }
                public void mouseExited(MouseEvent e){
                    boton.setBackground(new Color(52,152,219));
                }
            });
            return boton;
        }
        private void agregarEventos(){
            btnActualizar.addActionListener(e->actualizarReportes());
            btnExportarTXT.addActionListener(e->exportarTXT());
            btnExportarCSV.addActionListener(e->exportarCSV());
        }
        private void actualizarReportes(){
        //cambia los datos de los capos con los valores que se muestran
            lblTotal.setText(String.valueOf(SistemaBiblioteca.biblioteca.totalLibros()));
            lblDisponibles.setText(String.valueOf(SistemaBiblioteca.biblioteca.librosDisponibles()));
            lblPrestados.setText(String.valueOf(SistemaBiblioteca.biblioteca.librosPrestados()));
            lblSolicitudes.setText(String.valueOf(SistemaBiblioteca.gestorPrestamos.solicitudesPendientes()));
        }
     private void exportarTXT(){
        String reporte = generarReporte();
        ExportadorReportes.exportarTXT(reporte);
        JOptionPane.showMessageDialog(this,"Reporte TXT exportado correctamente.");
    }
    private void exportarCSV(){
        String reporte = generarReporte();
        ExportadorReportes.exportarCSV(reporte);
        JOptionPane.showMessageDialog(this,"Reporte CSV exportado correctamente.");
    }
    private String generarReporte(){
        String texto = "";
        texto += "========== QUICK LIBRARY ==========\n\n";
        texto += "TOTAL LIBROS       : "+ SistemaBiblioteca.biblioteca.totalLibros() + "\n";
        texto += "DISPONIBLES        : "+ SistemaBiblioteca.biblioteca.librosDisponibles() + "\n";
        texto += "PRESTADOS          : "+ SistemaBiblioteca.biblioteca.librosPrestados() + "\n";
        texto += "SOLICITUDES        : "+ SistemaBiblioteca.gestorPrestamos.solicitudesPendientes() + "\n";
        texto += "\n=========== CATÁLOGO ===========\n\n";
        texto += SistemaBiblioteca.biblioteca.obtenerCatalogoTexto();
        return texto;
    }

}
