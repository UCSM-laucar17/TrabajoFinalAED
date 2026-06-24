package estructuras;

public class NodoAVL<T extends Comparable<T>> {

    T dato;
    NodoAVL<T> izquierdo;
    NodoAVL<T> derecho;
    int altura;
