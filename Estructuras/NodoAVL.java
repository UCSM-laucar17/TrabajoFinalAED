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
    public T getDato() {
        return dato;
    }
    
    public NodoAVL<T> getIzquierdo() {
        return izquierdo;
    }
    
    public NodoAVL<T> getDerecho() {
        return derecho;
    }
     public int getAltura() {
        return altura;
    }
}
