package Interfaz;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

public class PruebaColores extends JFrame {

    public PruebaColores() {
        setTitle("Paleta de Colores");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel principal = new JPanel(new GridLayout(0, 2, 15, 15));
        principal.setBorder(new EmptyBorder(20,20,20,20));
        principal.setBackground(Color.WHITE);

        agregarColor(principal, "FONDO", Colores.FONDO);
        agregarColor(principal, "MENU", Colores.MENU);
        agregarColor(principal, "PANEL", Colores.PANEL);
        agregarColor(principal, "TARJETA", Colores.TARJETA);
        agregarColor(principal, "ENCABEZADO", Colores.ENCABEZADO);
        agregarColor(principal, "TEXTO", Colores.TEXTO);
        agregarColor(principal, "TEXTO_SECUNDARIO", Colores.TEXTO_SECUNDARIO);
        agregarColor(principal, "BOTON", Colores.BOTON);
        agregarColor(principal, "BOTON_HOVER", Colores.BOTON_HOVER);
        agregarColor(principal, "BOTON_PRESIONADO", Colores.BOTON_PRESIONADO);
        agregarColor(principal, "TEXTO_BOTON", Colores.TEXTO_BOTON);

        add(new JScrollPane(principal));
    }

    private void agregarColor(JPanel panel, String nombre, Color color) {

        JPanel tarjeta = new JPanel(new BorderLayout(10,10));
        tarjeta.setBorder(new LineBorder(Color.GRAY));

        JPanel muestra = new JPanel();
        muestra.setBackground(color);
        muestra.setPreferredSize(new Dimension(120,70));

        JLabel titulo = new JLabel(
                "<html><b>" + nombre + "</b><br>" +
                String.format("#%06X", color.getRGB() & 0xFFFFFF) +
                "</html>");

        titulo.setBorder(new EmptyBorder(10,10,10,10));

        tarjeta.add(muestra, BorderLayout.WEST);
        tarjeta.add(titulo, BorderLayout.CENTER);

        panel.add(tarjeta);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PruebaColores().setVisible(true);
        });
    }
}
