package Interfaz;

import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame{
    private JPanel panelMenu;
    private JPanel panelContenido;
    private CardLayout card;
    private JButton btnLibros;
    private JButton btnPrestamo;
    private JButton btnReportes;
    public VentanaPrincipal(){
        iniciarComponentes();
        setVisible(true);
    }
    
    private void iniciarComponentes(){

        setTitle("Quick Library");
        setSize(1200,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //Menu inicio
        panelMenu=new JPanel();
        panelMenu.setBackground(Colores.MENU);
        panelMenu.setPreferredSize(new Dimension(220,700));
        panelMenu.setLayout(null);
        JLabel icono = new JLabel("📚");
        icono.setBounds(80,10,60,40);
        icono.setFont(new Font("Segoe UI Emoji",Font.PLAIN,30));
        icono.setForeground(Color.WHITE);
        panelMenu.add(icono);
        JLabel titulo=new JLabel("QUICK LIBRARY");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI",Font.BOLD,20));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBounds(10,50,190,30);
        panelMenu.add(titulo);
        btnLibros=new JButton("Libros");
        btnPrestamo=new JButton("Préstamos");
        btnReportes=new JButton("Reportes");
        configurarBoton(btnLibros,120);
        configurarBoton(btnPrestamo,190);
        configurarBoton(btnReportes,260);
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
        boton.setFont(new Font("Segoe UI",Font.BOLD,15));
        boton.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseEntered(java.awt.event.MouseEvent e){
                boton.setBackground(new Color(41,128,185));
            }
            public void mouseExited(java.awt.event.MouseEvent e){
                boton.setBackground(Colores.BOTON);
            }
        });
    }

}
