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

    //  Rotaciones

    private NodoAVL<T> rotacionDerecha(NodoAVL<T> y) {
        NodoAVL<T> x  = y.izquierdo;
        NodoAVL<T> t2 = x.derecho;

        x.derecho   = y;
        y.izquierdo = t2;

        actualizarAltura(y);
        actualizarAltura(x);

        return x;
    }

    private NodoAVL<T> rotacionIzquierda(NodoAVL<T> x) {
        NodoAVL<T> y  = x.derecho;
        NodoAVL<T> t2 = y.izquierdo;

        y.izquierdo = x;
        x.derecho   = t2;

        actualizarAltura(x);
        actualizarAltura(y);

        return y;
    }

    private NodoAVL<T> rotacionDobleDerecha(NodoAVL<T> nodo) {
        nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
        return rotacionDerecha(nodo);
    }

    private NodoAVL<T> rotacionDobleIzquierda(NodoAVL<T> nodo) {
        nodo.derecho = rotacionDerecha(nodo.derecho);
        return rotacionIzquierda(nodo);
    }

    private NodoAVL<T> balancear(NodoAVL<T> nodo) {
        actualizarAltura(nodo);
        int balance = obtenerBalance(nodo);

        // Izquierda - Izquierda
        if (balance > 1 && obtenerBalance(nodo.izquierdo) >= 0)
            return rotacionDerecha(nodo);

        // Izquierda - Derecha
        if (balance > 1 && obtenerBalance(nodo.izquierdo) < 0)
            return rotacionDobleDerecha(nodo);

        // Derecha - Derecha
        if (balance < -1 && obtenerBalance(nodo.derecho) <= 0)
            return rotacionIzquierda(nodo);

        // Derecha - Izquierda
        if (balance < -1 && obtenerBalance(nodo.derecho) > 0)
            return rotacionDobleIzquierda(nodo);

        return nodo;
    }
    // Insertar
    
    
