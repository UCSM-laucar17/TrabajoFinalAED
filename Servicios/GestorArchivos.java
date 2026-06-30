package Servicios;
import java.io.*;
import Estructuras.ListaEnlazada;
import Modelos.Libro;

public class GestorArchivos {
    //Carga libros desde el CSV y los registra en la Biblioteca
    private static final String RUTA = "libros.csv";
    public static void guardarLibrosCSV(Biblioteca biblioteca) {
        try {
            //creamos un bufferWriter con la RUTA de nuestro parametro
            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA));
            bw.write("codigo,titulo,autor,categoria,anio,estado");
            bw.newLine(); //saltamos linea
            ListaEnlazada<Libro> lista = biblioteca.obtenerTodosLosLibros(); //obtenemos y almacenamos la lista de libros
            for(int i=0;i<lista.size();i++){ //recorremos todos los datos de lista y masrcamos si estado disponible o prestando 
                Libro l=lista.obtener(i);
                String estado;
                if(l.getEstado())
                    estado="Disponible";
                else
                    estado="Prestado";
                bw.write(l.getCodigo()+","+l.getTitulo()+","+l.getAutor()+","+l.getCategoria()+","+l.getAnio()+","+estado);
                bw.newLine();
            }
            bw.close();
            System.out.println("CSV guardado.");
        }catch(IOException e){
            System.out.println(e.getMessage()); //mostramosmensaje si ocurre una excepcion
        }
    }
    public static void cargarLibrosCSV(Biblioteca biblioteca){  //cargamos los libros den archivo
        try{
            BufferedReader br=new BufferedReader(new FileReader(RUTA));
            String linea;
            br.readLine();
            //
            while((linea=br.readLine())!=null){ 
                String datos[]=linea.split(","); //separa partes del archivo por comas
                int codigo=Integer.parseInt(datos[0]);
                String titulo=datos[1]; //convertimos el titulo en string y asi los siguiente
                String autor=datos[2];
                String categoria=datos[3];
                int anio=Integer.parseInt(datos[4]);
                boolean estado=datos[5].equalsIgnoreCase("Disponible");
                biblioteca.registrarLibro(new Libro(codigo,titulo,autor,categoria,anio,estado)); //creamosu n nuevi ibjeto y lo agregamos a la biblioteca
            }
            br.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
