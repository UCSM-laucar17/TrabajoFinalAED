package estructuras;
//nodo de lista enlazada
public class NodoL<T> {

    // Dato almacenado en el nodo
    private T dato;

    // Referencia al siguiente nodo
    private Nodo<T> siguiente;

    // Constructor
    public NodoL(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // Getter del dato
    public T getDato() {
        return dato;
    }

    // Setter del dato
    public void setDato(T dato) {
        this.dato = dato;
    }

    // Getter del siguiente nodo
    public NodoL<T> getSiguiente() {
        return siguiente;
    }

    // Setter del siguiente nodo
    public void setSiguiente(NodoL<T> siguiente) {
        this.siguiente = siguiente;
    }

}
