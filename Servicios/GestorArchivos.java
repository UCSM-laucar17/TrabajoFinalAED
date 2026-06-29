package Servicios;
import java.io.*;
import Estructuras.ListaEnlazada;
import Modelos.Libro;

public class GestorArchivos {
    //Carga libros desde el CSV y los registra en la Biblioteca
    private static final String RUTA = "libros.csv";
    public static void guardarLibrosCSV(Biblioteca biblioteca) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA));
            bw.write("codigo,titulo,autor,categoria,anio,estado");
            bw.newLine();
            ListaEnlazada<Libro> lista = biblioteca.obtenerTodosLosLibros();
            for(int i=0;i<lista.size();i++){
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
            System.out.println(e.getMessage());
        }
    }
    public static void cargarLibrosCSV(Biblioteca biblioteca){
        try{
            BufferedReader br=new BufferedReader(new FileReader(RUTA));
            String linea;
            br.readLine();
            while((linea=br.readLine())!=null){
                String datos[]=linea.split(",");
                int codigo=Integer.parseInt(datos[0]);
                String titulo=datos[1];
                String autor=datos[2];
                String categoria=datos[3];
                int anio=Integer.parseInt(datos[4]);
                boolean estado=datos[5].equalsIgnoreCase("Disponible");
                biblioteca.registrarLibro(new Libro(codigo,titulo,autor,categoria,anio,estado));
            }
            br.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
