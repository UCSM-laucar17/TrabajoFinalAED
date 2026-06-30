package Interfaz;
import java.awt.*;
import java.awt.color.ColorSpace;
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
        setBackground((Colores.FONDO));
        JLabel titulo = new JLabel("REPORTES");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI",Font.BOLD,26));
        titulo.setBorder(new EmptyBorder(20,10,20,10));
        titulo.setForeground(Colores.TEXTO);
        add(titulo,BorderLayout.NORTH);
            JPanel centro = new JPanel(new GridLayout(2,2,20,20));
        centro.setBorder(new EmptyBorder(30,40,30,40));
        centro.setBackground(Colores.FONDO);

        lblTotal = crearValor();
        lblDisponibles = crearValor();
        lblPrestados = crearValor();
        lblSolicitudes = crearValor();

        centro.add(crearTarjeta("Total de Libros", lblTotal));
        centro.add(crearTarjeta("Libros Disponibles", lblDisponibles));
        centro.add(crearTarjeta("Libros Prestados", lblPrestados));
        centro.add(crearTarjeta("Solicitudes Pendientes", lblSolicitudes));

        add(centro, BorderLayout.CENTER);
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
            boton.setBackground((Colores.BOTON));
            boton.setForeground(Color.WHITE);
            boton.setFocusPainted(false);
            boton.setContentAreaFilled(false);
            boton.setOpaque(true);
            boton.setFont(new Font("Segoe UI",Font.BOLD,14));
            boton.addMouseListener(new MouseAdapter(){
                public void mouseEntered(MouseEvent e){
                    boton.setBackground(Colores.BOTON_HOVER);
                }
                public void mouseExited(MouseEvent e){
                    boton.setBackground(Colores.BOTON);
                }
                public void mousePressed(MouseEvent e) {
                    // Cuando se mantiene presionado el botón
                    boton.setBackground(Colores.BOTON_PRESIONADO);
                }
                @Override
                public void mouseReleased(MouseEvent e) {
                    // Cuando se suelta el clic
                    if (boton.getBounds().contains(e.getPoint())) {
                        boton.setBackground(Colores.BOTON_HOVER); // Si se soltó dentro
                    } else {
                        boton.setBackground(Colores.BOTON); // Si se soltó fuera
                    }
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
    private JLabel crearValor() {
        JLabel lbl = new JLabel("0");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lbl.setForeground(Colores.ENCABEZADO);
        return lbl;
    }

    private JPanel crearTarjeta(String titulo, JLabel valor) {

        JPanel tarjeta = new JPanel(new BorderLayout(5,5));
        tarjeta.setBackground(Colores.TARJETA);
        tarjeta.setBorder(new CompoundBorder(
                new LineBorder(new Color(210,210,210),1,true),
                new EmptyBorder(15,15,15,15)));

        JLabel txt = new JLabel(titulo);
        txt.setHorizontalAlignment(SwingConstants.CENTER);
        txt.setFont(new Font("Segoe UI",Font.BOLD,16));
        txt.setForeground(Colores.TEXTO);

        tarjeta.add(txt, BorderLayout.NORTH);
        tarjeta.add(valor, BorderLayout.CENTER);

        return tarjeta;
    }

}
