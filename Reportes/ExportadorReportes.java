

package Reportes;

import java.io.*;

public class ExportadorReportes {
  public static void exportarCSV(String contenido){
        try{
            BufferedWriter bw=new BufferedWriter(new FileWriter("ReporteBiblioteca.csv"));
            bw.write(contenido);
            bw.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
