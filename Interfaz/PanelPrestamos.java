package Interfaz;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import Servicios.SistemaBiblioteca;

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
    private void iniciarComponentes() {
        setLayout(new BorderLayout(15,15));
        setBackground(Colores.FONDO);

        // Título
        JLabel titulo = new JLabel("GESTIÓN DE PRÉSTAMOS");
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setBorder(new EmptyBorder(15,10,15,10));
        titulo.setForeground(Colores.TITULO);
        add(titulo, BorderLayout.NORTH);


        // Panel Izquierdo (Formulario)
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(Colores.TARJETA);
        panelFormulario.setBorder(
            new CompoundBorder(
                new TitledBorder("Datos del Préstamo"),
                new EmptyBorder(10,75,10,75)
            )
        );
        panelFormulario.setLayout(new GridBagLayout());
        

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Código Estudiante
        c.gridx = 0;    
        c.gridy = 0;
        c.weightx = 0;
        panelFormulario.add(new JLabel("Código Estudiante"), c);

        txtCodigoEstudiante = new JTextField();
        txtCodigoEstudiante.setPreferredSize(new Dimension(125, 20));
        c.gridx = 1;
         c.weightx = 1;
        panelFormulario.add(txtCodigoEstudiante, c);

        // Nombre
        c.gridx = 0;
        c.gridy++;
        panelFormulario.add(new JLabel("Nombre"), c);

        txtNombre = new JTextField();
        c.gridx = 1;
        
        panelFormulario.add(txtNombre, c);

        // Código Libro
        c.gridx = 0;
        c.gridy++;
        panelFormulario.add(new JLabel("Código Libro"), c);

        txtCodigoLibro = new JTextField();
        c.gridx = 1;
        panelFormulario.add(txtCodigoLibro, c);

        // Panel de Botones
        JPanel panelBotones = new JPanel(new GridLayout(5,1,10,10));
        panelBotones.setBackground(Colores.FONDO);

        btnRegistrar = crearBoton("Registrar Solicitud");
        btnConsultar = crearBoton("Consultar Siguiente");
        btnAtender = crearBoton("Atender Solicitud");
        btnDevolver = crearBoton("Registrar Devolución");
        btnLimpiar = crearBoton("Limpiar");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnConsultar);
        panelBotones.add(btnAtender);
        panelBotones.add(btnDevolver);
        panelBotones.add(btnLimpiar);

        c.gridx = 0;
        c.gridy++;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.BOTH;
        panelFormulario.add(panelBotones, c);

        add(panelFormulario, BorderLayout.WEST);


        // Panel Derecho (Historial)
        JPanel panelHistorial = new JPanel(new BorderLayout());
        panelHistorial.setBackground(Colores.TARJETA);
        panelHistorial.setBorder(new TitledBorder("Historial de Préstamos"));

        areaHistorial = new JTextArea();
        areaHistorial.setEditable(false);
        areaHistorial.setFont(new Font("Consolas", Font.PLAIN, 14));
        areaHistorial.setMargin(new Insets(10,10,10,10));

        scroll = new JScrollPane(areaHistorial);
        panelHistorial.add(scroll, BorderLayout.CENTER);

        add(panelHistorial, BorderLayout.CENTER);

        // Ajustes del área de texto
        areaHistorial.setLineWrap(true);
        areaHistorial.setWrapStyleWord(true);
        areaHistorial.setCaretPosition(0);
        areaHistorial.setBackground(Colores.TARJETA);

        // Hover de botones
        agregarHover(btnRegistrar);
        agregarHover(btnConsultar);
        agregarHover(btnAtender);
        agregarHover(btnDevolver);
        agregarHover(btnLimpiar);

        // Eventos
        agregarEventos();
    }
    private void agregarHover(JButton boton){ //mejora de botones en interfaz   
        boton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(Colores.BOTON_HOVER);
            }
            public void mouseExited(MouseEvent e) {
                boton.setBackground((Colores.BOTON));
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
    //crar botones dentro de los lables ya definidos
    private JButton crearBoton(String texto){
        JButton boton = new JButton(texto);
        boton.setBackground(Colores.BOTON);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setContentAreaFilled(false);
        boton.setOpaque(true);
        boton.setFont(new Font("Segoe UI",Font.BOLD,14));
        //cambio de diseño del boton cuando se selecciona
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
    private void consultarSiguiente() {
        JOptionPane.showMessageDialog(this,SistemaBiblioteca.gestorPrestamos.obtenerSiguienteSolicitud());
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
