
package Test;

import Estructuras.ColaGenerica;
public class ColaTest {
    public static void main(String[] args) {

        ColaGenerica<String> cola = new ColaGenerica<>();

        cola.enqueue("Lola");
        cola.enqueue("Paluza");
        cola.enqueue("exe404");


        System.out.println("Contenido de la cola:");
        cola.mostrar();

        try{
            System.out.println("\nFrente: " + cola.peek());
            System.out.println("\nSale: " + cola.dequeue());
            System.out.println("\nNueva cola:");
            cola.mostrar();

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }

}
