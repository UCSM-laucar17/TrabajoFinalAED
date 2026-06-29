package Interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
public class PanelPrestamos extends JPanel {

    //atributos de operaciones para prestamos
    private JTextField txtCodigoEstudiante;
    private JTextField txtNombre;
    private JTextField txtCodigoLibro;

    //atributos de una interfaz grafica
    private JButton btnRegistrar;
    private JButton btnConsultar;
    private JButton btnAtender;
    private JButton btnDevolver;
    private JButton btnLimpiar;

    private JTextArea areaHistorial;

    private JScrollPane scroll;

    //constructor para la pantalla
 public PanelPrestamos(){

        iniciarComponentes();

    }
    private void iniciarComponentes(){
        //diseño y disposicion de la pagina
        setLayout(new BorderLayout());
        setBackground(new Color(245,247,250));
        JLabel titulo = new JLabel("GESTIÓN DE PRÉSTAMOS");
        titulo.setHorizontalAlignment(JLabel.CENTER);
        titulo.setFont(new Font("Segoe UI",Font.BOLD,26));
        titulo.setBorder(new EmptyBorder(20,10,20,10));
        add(titulo,BorderLayout.NORTH);
        JPanel formulario = new JPanel(new GridLayout(8,2,10,10));
        formulario.setBorder(new EmptyBorder(20,30,20,30));
        formulario.setBackground(Color.WHITE);
        formulario.add(new JLabel("Código Estudiante"));
        txtCodigoEstudiante = new JTextField();
        formulario.add(txtCodigoEstudiante);
        formulario.add(new JLabel("Nombre"));
        txtNombre = new JTextField();
        formulario.add(txtNombre);
        formulario.add(new JLabel("Código Libro"));
        txtCodigoLibro = new JTextField();
        formulario.add(txtCodigoLibro);

        //Botones para acciones del ususario
        btnRegistrar = crearBoton("Registrar Solicitud");
        btnConsultar = crearBoton("Consultar Siguiente");
        btnAtender = crearBoton("Atender Solicitud");
        btnDevolver = crearBoton("Registrar Devolución");
        btnLimpiar = crearBoton("Limpiar");
        formulario.add(btnRegistrar);
        formulario.add(btnConsultar);
        formulario.add(btnAtender);
        formulario.add(btnDevolver);
        formulario.add(btnLimpiar);
        formulario.add(new JLabel());
        add(formulario,BorderLayout.WEST);
        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Consolas",Font.PLAIN,13));
        scroll = new JScrollPane(areaHistorial);
        scroll.setBorder(new TitledBorder("Historial"));
        add(scroll,BorderLayout.CENTER);
        agregarEventos();

    }

    //crar botones dentro de los lables ya definidos
    private JButton crearBoton(String texto){
        JButton boton = new JButton(texto);
        boton.setBackground(new Color(52,152,219));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI",Font.BOLD,14));
        //cambio de diseño del boton cuando se selecciona
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

    //metodos llamados con cada boton
    private void agregarEventos(){
        btnRegistrar.addActionListener(e->registrarSolicitud());
        btnConsultar.addActionListener(e->consultarSiguiente());
        btnAtender.addActionListener(e->atenderSolicitud());
        btnDevolver.addActionListener(e->devolverLibro());
        btnLimpiar.addActionListener(e->limpiar());
    }

    //Verifica y añade una solicitud de prestamo
    private void registrarSolicitud() {
        try {

            String codigoEst = txtCodigoEstudiante.getText().trim();
            String nombre = txtNombre.getText().trim();
            int codigoLibro = Integer.parseInt(txtCodigoLibro.getText().trim());

            //Comporbacion de integridad de los datos
            if (codigoEst.isEmpty() || nombre.isEmpty()){
                JOptionPane.showMessageDialog(this,"Complete todos los campos.");
                return;

            }
            boolean exito = SistemaBiblioteca.gestorPrestamos.registrarSolicitud(codigoEst, nombre, codigoLibro);
            if (exito) {
                JOptionPane.showMessageDialog(this,"Solicitud registrada correctamente.");
                actualizarSolicitudes();
                limpiar();

            } else {
                JOptionPane.showMessageDialog(this,"No existe un libro con ese código.");

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Código del libro inválido.");
        }
    }
    private void atenderSolicitud() {
        //verifica si las solicitud gue procesada 
        boolean exito =SistemaBiblioteca.gestorPrestamos.atenderSolicitud();
        if (exito) {
            JOptionPane.showMessageDialog(this,"Solicitud atendida correctamente.");
            actualizarHistorial();
        } else {
            JOptionPane.showMessageDialog(this,"No existen solicitudes pendientes.");
        }
        //despues de cualquier salida actualiza la biblioteca
        actualizarSolicitudes();
    }
    
    private void devolverLibro() {
        try {
            //por si acaso se convierte el codigo del libro a entero
            int codigoLibro =Integer.parseInt(txtCodigoLibro.getText().trim());
            boolean exito =SistemaBiblioteca.gestorPrestamos.devolverLibro(codigoLibro);
            if (exito) {
                JOptionPane.showMessageDialog(this,"Libro devuelto correctamente.");
            } else {
                JOptionPane.showMessageDialog(this,"No se pudo registrar la devolución.");
            }
            actualizarHistorial();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Código inválido.");
        }
    }
    private void actualizarSolicitudes() {

        areaHistorial.setText(SistemaBiblioteca.gestorPrestamos.obtenerSolicitudesTexto());
        scroll.setBorder(new TitledBorder("Solicitudes pendientes"));
    }
    private void actualizarHistorial() {

        areaHistorial.setText(SistemaBiblioteca.gestorPrestamos.obtenerHistorialTexto());
        scroll.setBorder(new TitledBorder("Historial de préstamos"));
    }
    private void limpiar() {
        txtCodigoEstudiante.setText("");
        txtNombre.setText("");
        txtCodigoLibro.setText("");
        txtCodigoEstudiante.requestFocus();

    }
}
