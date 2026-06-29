
package Interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;


public class PanelLibros extends JPanel {
//atributos de espacios para identificar un libro
    private JTextField txtCodigo;
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtCategoria;
    private JTextField txtAnio;
    private JRadioButton rbDisponible;
    private JRadioButton rbPrestado;
    private JButton btnRegistrar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    private JTextArea areaCatalogo;

    private JScrollPane scroll;

    //Constructor
    public PanelLibros() {
    //constructor para poder iniciar funciones de la pantalla

        iniciarComponentes();

    }
private void iniciarComponentes() {

        setLayout(new BorderLayout(15,15));

        setBackground(new Color(236,240,241));

        // Título
        JLabel titulo = new JLabel("Pagi404");//pon titulo de pagina

        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI",Font.BOLD,26));
        titulo.setBorder(new EmptyBorder(15,10,15,10));
        add(titulo,BorderLayout.NORTH);


        // Panel Izquierdo
        JPanel panelFormulario = new JPanel();
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(new TitledBorder("Datos del Libro"));
        panelFormulario.setPreferredSize(new Dimension(430,500));
        panelFormulario.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.fill = GridBagConstraints.HORIZONTAL;

        // Código
        c.gridx=0;
        c.gridy=0;
        panelFormulario.add(new JLabel("Código"),c);
        txtCodigo = new JTextField();
        c.gridx=1;
        panelFormulario.add(txtCodigo,c);


        // Título
        c.gridx=0;
        c.gridy++;
        panelFormulario.add(new JLabel("Título"),c);
        txtTitulo = new JTextField();
        c.gridx=1;
        panelFormulario.add(txtTitulo,c);
        // Autor
        c.gridx=0;
        c.gridy++;
        panelFormulario.add(new JLabel("Autor"),c);
        txtAutor = new JTextField();
        c.gridx=1;
        panelFormulario.add(txtAutor,c);


        // Categoría
        c.gridx=0;
        c.gridy++;
        panelFormulario.add(new JLabel("Categoría"),c);
        txtCategoria = new JTextField();
        c.gridx=1;
        panelFormulario.add(txtCategoria,c);


        // Año
        c.gridx=0;
        c.gridy++;
        panelFormulario.add(new JLabel("Año"),c);
        txtAnio = new JTextField();
        c.gridx=1;
        panelFormulario.add(txtAnio,c);


        // Estado
        c.gridx=0;
        c.gridy++;
        panelFormulario.add(new JLabel("Estado"),c);
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEstado.setBackground(Color.WHITE);
        rbDisponible = new JRadioButton("Disponible");
        rbPrestado = new JRadioButton("Prestado");
        rbDisponible.setBackground(Color.WHITE);
        rbPrestado.setBackground(Color.WHITE);
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbDisponible);
        grupo.add(rbPrestado);
        rbDisponible.setSelected(true);
        panelEstado.add(rbDisponible);
        panelEstado.add(rbPrestado);
        c.gridx=1;
        panelFormulario.add(panelEstado,c);


        // Botones
        JPanel panelBotones = new JPanel(new GridLayout(3,2,10,10));
        panelBotones.setBackground(Color.WHITE);
        btnRegistrar = crearBoton("Registrar");
        btnBuscar = crearBoton("Buscar");
        btnModificar = crearBoton("Modificar");
        btnEliminar = crearBoton("Eliminar");
        btnLimpiar = crearBoton("Limpiar");
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);
        c.gridx=0;
        c.gridy++;
        c.gridwidth=2;
        c.fill=GridBagConstraints.BOTH;
        panelFormulario.add(panelBotones,c);
        add(panelFormulario,BorderLayout.WEST);


        // Panel Derecho
        JPanel panelLista = new JPanel(new BorderLayout());
        panelLista.setBackground(Color.WHITE);
        panelLista.setBorder(new TitledBorder("Catálogo de Libros"));
        areaCatalogo = new JTextArea();
        areaCatalogo.setEditable(false);
        areaCatalogo.setFont(new Font("Consolas",Font.PLAIN,14));
        areaCatalogo.setMargin(new Insets(10,10,10,10));
        scroll = new JScrollPane(areaCatalogo);
        panelLista.add(scroll,BorderLayout.CENTER);
        add(panelLista,BorderLayout.CENTER);
    

        //modificaciones al catalogo
        areaCatalogo.setLineWrap(true);
        areaCatalogo.setWrapStyleWord(true);
        areaCatalogo.setCaretPosition(0);
        areaCatalogo.setBackground(new Color(248,249,250));

        //hover es diseño de sobras en botones
        agregarHover(btnRegistrar);
        agregarHover(btnBuscar);
        agregarHover(btnModificar);
        agregarHover(btnEliminar);
        agregarHover(btnLimpiar);
                
        //metodos esperados en para una primera vez
        agregarEventos();
        actualizarCatalogo();
        agregarValidaciones();
    }
  // Botones
    private JButton crearBoton(String texto){
        JButton boton = new JButton(texto);
        boton.setFocusPainted(false);
        boton.setBackground(new Color(52,152,219));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Segoe UI",Font.BOLD,14));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(new EmptyBorder(8,8,8,8));
        return boton;
    }

  //que hace cada boten 
    private void agregarEventos() {
        btnRegistrar.addActionListener(e -> registrarLibro());
        btnBuscar.addActionListener(e -> buscarLibro());
        btnModificar.addActionListener(e -> modificarLibro());
        btnEliminar.addActionListener(e -> eliminarLibro());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

  //metodos por boton 
    private void registrarLibro(){

        try {
            //combierte los tipos de datos con parse
            int codigo = Integer.parseInt(txtCodigo.getText());
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String categoria = txtCategoria.getText();
            int anio = Integer.parseInt(txtAnio.getText());
            boolean estado = rbDisponible.isSelected();
            //existe?
            if(titulo.isEmpty() ||
            autor.isEmpty() ||
            categoria.isEmpty()){
                JOptionPane.showMessageDialog(this,"Complete todos los campos.");
                return;
            }

            Libro libro = new Libro(codigo,titulo,autor,categoria,anio,estado
            );
            SistemaBiblioteca.biblioteca.registrarLibro(libro);
            actualizarCatalogo();
            limpiarCampos();
            JOptionPane.showMessageDialog(this,"Libro registrado correctamente.");
        }
        catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,"Código y año deben ser números.");
        }
    }



}
