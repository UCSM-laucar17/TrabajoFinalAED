package Estructuras;

public class ColaGenerica<E> 
{  //loas atributos de la cola 
    private Nodo<E> first;  
    private Nodo<E> last;
    private int size;
    //constructor de cola incializamos los valores
    public ColaGenerica() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    // Insertar al final de la cola el dato 
    public void enqueue(E data) 
    {
        Nodo<E> newNode = new Nodo<>(data);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }
    // Retirar del frente de la cola
    public E dequeue() throws Exception {
        if (isEmpty()) throw new Exception("Cola vacia, nada que desencolar.");
        E data = first.getData();
        first = first.getNext();
        size--;
        if (first == null) last = null;
        return data;
    }
    // Ver el primer elemento sin retirarlo de la cola
    public E peek() throws Exception {
        if (isEmpty()) throw new Exception("Cola vacaa, nada que consultar.");
        return first.getData();
    }
    //verifica si esta vacia la cola
    public boolean isEmpty() {
        return first == null;
    }
    //devuelve el numero de elementos en la cola
    public int size() {
        return this.size;
    }
    //muetsra todo los elelmentos de la cola en orden
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("  La cola esta vacia.");
            return;
        }
        Nodo<E> aux = first; //nodo auxiliar para recorrer la cola
        int pos = 1;
        System.out.println("  Cola (frente → final):");
        while (aux != null) {
            System.out.println("  [" + pos + "] " + aux.getData());
            aux = aux.getNext(); //avanzamos al siguiente nodo
            pos++;
        }
    }
    public Nodo<E> getFrente(){
        return first;
    }
    public String obtenerContenido() {
        if (isEmpty()) {
            return "No hay solicitudes pendientes.";
        }
        String resultado = ""; 
        Nodo<E> aux = first;
        int pos = 1;
        while (aux != null) {
            resultado += pos + ". " + aux.getData() + "\n\n";
            aux = aux.getNext();
            pos++;
        }
        return resultado;
    }
}
