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
        while (actual.getNext() != null) {
            actual = actual.getNext();
        }
        actual.setNext(nuevo);
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
            actual = actual.getNext();
        }
        return actual.getData();
    }
    // Reemplaza el dato de una posición
    public void set(int indice, T dato) {
    // Verifica que el índice sea válido
        if (indice < 0 || indice >= tamanio) {
        return;
        }
        Nodo<T> actual = cabeza;
        // Avanza hasta la posición indicada
        for (int i = 0; i < indice; i++) {
            actual = actual.getNext();
        }
        actual.setData(dato);
    }
// Elimina el elemento de una posición
    public void eliminar(int indice) {
        // Verifica que el índice sea válido
        if (indice < 0 || indice >= tamanio) {
            return;
        }
        // Si se elimina la cabeza
        if (indice == 0) {
            cabeza = cabeza.getNext();
            tamanio--;
            return;
        }
        Nodo<T> anterior = cabeza
        // Avanza hasta el nodo anterior al que se eliminará
        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getNext();
        }
        anterior.setNext(anterior.getNext().getNext());
        tamanio--;
    }
    // Busca un elemento dentro de la lista
    public boolean contiene(T dato) {
        Nodo<T> actual = cabeza;
        // Recorre toda la lista
        while (actual != null) {
            if (actual.getData().equals(dato)) {
                return true;
            }
            actual = actual.getNext();
        }
        return false;
    }
    // Vacía completamente la lista
    public void limpiar() {
        cabeza = null;
        tamanio = 0;
    }
    // Muestra todos los elementos de la lista
    public void mostrar() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo<T> actual = cabeza;
        // Recorre toda la lista mostrando cada elemento
        while (actual != null) {
            System.out.println(actual.getData());
            actual = actual.getNext();
        }
    } 
    // Devuelve la posición de un elemento
    public int indiceDe(T dato) {
    
        Nodo<T> actual = cabeza;
        int indice = 0;
        // Recorre la lista buscando el dato
        while (actual != null) {
            if (actual.getData().equals(dato)) {
                return indice;
            }
            actual = actual.getNext();
            indice++;
        }
        return -1;
    }
}