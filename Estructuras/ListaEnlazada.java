package estructuras;

public class ListaEnlazada<T> {

    // Primer nodo de la lista
    private Nodo<T> cabeza;

    // Cantidad de elementos
    private int tamanio;

    // Constructor
    public ListaEnlazada() {
        cabeza = null;
        tamanio = 0;
    }

    // Indica si la lista está vacía
    public boolean estaVacia() {
        return cabeza == null;
    }

    // Devuelve la cantidad de elementos
    public int size() {
        return tamanio;
    }

    // Devuelve el primer nodo
    public Nodo<T> getCabeza() {
        return cabeza;
    }

    // Inserta un elemento al final de la lista
    public void insertar(T dato) {

        Nodo<T> nuevo = new Nodo<>(dato);

        // Si la lista está vacía, el nuevo nodo será la cabeza
        if (cabeza == null) {
            cabeza = nuevo;
            tamanio++;
            return;
        }

        Nodo<T> actual = cabeza;

        // Avanza hasta el último nodo
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }

        actual.setSiguiente(nuevo);

        tamanio++;
    }

    // Obtiene el elemento de una posición
    public T obtener(int indice) {

        // Verifica que el índice sea válido
        if (indice < 0 || indice >= tamanio) {
            return null;
        }

        Nodo<T> actual = cabeza;

        // Avanza hasta la posición indicada
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }

        return actual.getDato();
    }

}
