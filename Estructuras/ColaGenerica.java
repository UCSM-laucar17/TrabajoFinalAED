package Estructuras;

public class ColaGenerica<E> {
    private Nodo<E> first;
    private Nodo<E> last;
    private int size;
    public ColaGenerica() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    // Insertar al final de la cola
    public void enqueue(E data) {
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
        if (isEmpty()) throw new Exception("Cola vacía, nada que desencolar.");
        E data = first.getData();
        first = first.getNext();
        size--;
        if (first == null) last = null;
        return data;
    }
    // Ver el primer elemento sin retirarlo
    public E peek() throws Exception {
        if (isEmpty()) throw new Exception("Cola vacía, nada que consultar.");
        return first.getData();
    }
    public boolean isEmpty() {
        return first == null;
    }
    public int size() {
        return this.size;
    }
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("  La cola está vacía.");
            return;
        }
        Nodo<E> aux = first;
        int pos = 1;
        System.out.println("  Cola (frente → final):");
        while (aux != null) {
            System.out.println("  [" + pos + "] " + aux.getData());
            aux = aux.getNext();
            pos++;
        }
    }
}
