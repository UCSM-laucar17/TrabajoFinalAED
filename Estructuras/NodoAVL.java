package estructuras;

public class NodoAVL<T extends Comparable<T>> {
    T dato;
    NodoAVL<T> izquierdo;
    NodoAVL<T> derecho;
    int altura;
public NodoAVL(T dato) {
        this.dato = dato;
        this.izquierdo = null;
        this.derecho = null;
        this.altura = 1;
    }
}
