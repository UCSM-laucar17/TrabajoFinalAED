package Interfaz;
import java.awt.*; //es la primera liberia grafica esa componetes nativos se iso botones menus
import javax.swing.*; //libreria ligera que no depende del sistema , personaliza la ventana principal 

import java.awt.event.*;

public class VentanaPrincipal extends JFrame{ //ventana principal herredada de swing
    //atributos
    private JPanel panelMenu;
    private JPanel panelContenido; //panel principal
    private CardLayout card; //cambiar entre pantallas
    private JButton btnLibros; //botones del menu 
    private JButton btnPrestamo;
    private JButton btnReportes;
    //constructor
    public VentanaPrincipal(){
        iniciarComponentes();
        setVisible(true);
    }
    private void iniciarComponentes(){  
        setTitle("Quick Library"); //titulo de la ventana
        setSize(1200,700);
        setLocationRelativeTo(null); //centra la pantalla
        setDefaultCloseOperation(EXIT_ON_CLOSE); //cierra la aplicacion al salir
        setLayout(new BorderLayout());
        //Menu inicio
        panelMenu=new JPanel();
        panelMenu.setBackground(Colores.MENU);
        panelMenu.setPreferredSize(new Dimension(220,700));
        panelMenu.setLayout(null); //posiciona elementos por coordenadas
        ImageIcon iconoOriginal = new ImageIcon(
                getClass().getResource("/logo.png"));
        Image imagen = iconoOriginal.getImage().getScaledInstance(
                120, 120, Image.SCALE_SMOOTH);
        JLabel icono = new JLabel(new ImageIcon(imagen));
        icono.setBounds(45, 25, 120, 120);
        panelMenu.add(icono);
        JLabel titulo=new JLabel("QUICK LIBRARY");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI",Font.BOLD,20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(10,170,190,30);
        panelMenu.add(titulo);
        btnLibros=new JButton("Libros");
        btnPrestamo=new JButton("Préstamos");
        btnReportes=new JButton("Reportes");
        configurarBoton(btnLibros,270);
        configurarBoton(btnPrestamo,340);
        configurarBoton(btnReportes,410);
        panelMenu.add(btnLibros);
        panelMenu.add(btnPrestamo);
        panelMenu.add(btnReportes);
        JLabel pie = new JLabel("v1.0");
        pie.setForeground(Color.LIGHT_GRAY);
        pie.setHorizontalAlignment(SwingConstants.CENTER);
        pie.setBounds(0,640,220,20);
        panelMenu.add(pie);
        //Estucutura de la poantalla
        card=new CardLayout();
        panelContenido=new JPanel(card);
        panelContenido.setBackground(Colores.FONDO);
        panelContenido.add(new PanelLibros(),"LIBROS");
        panelContenido.add(new PanelPrestamos(),"PRESTAMOS");
        panelContenido.add(new PanelReportes(),"REPORTES");
        card.show(panelContenido,"LIBROS");
        add(panelMenu,BorderLayout.WEST);
        add(panelContenido,BorderLayout.CENTER);
        //acciones segun botones
        btnLibros.addActionListener(e->{
            card.show(panelContenido,"LIBROS");
        });
        btnPrestamo.addActionListener(e->{
            card.show(panelContenido,"PRESTAMOS");
        });
        btnReportes.addActionListener(e->{
            card.show(panelContenido,"REPORTES");
        });
    }
    private void configurarBoton(JButton boton,int y){
        boton.setBounds(20,y,170,45);
        boton.setBackground(Colores.BOTON);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setFont(new Font("Segoe UI",Font.BOLD,15));
        boton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                boton.setBackground(Colores.BOTON_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent e){
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

    }
}
