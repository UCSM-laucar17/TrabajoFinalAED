package estructuras;

public class ArbolAVL<T extends Comparable<T>> {

    private NodoAVL<T> raiz;

    public ArbolAVL() {
        this.raiz = null;
    }
// Altura y balance
private int obtenerAltura(NodoAVL<T> nodo) {
        if (nodo == null) return 0;
        return nodo.altura;
    }

    private int obtenerBalance(NodoAVL<T> nodo) {
        if (nodo == null) return 0;
        return obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }

    private void actualizarAltura(NodoAVL<T> nodo) {
        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo),
                                   obtenerAltura(nodo.derecho));
    }
//Rotaciones
