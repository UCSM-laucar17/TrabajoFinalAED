package estructuras;

public class ArbolAVL<T extends Comparable<T>> 
{
    private NodoAVL<T> raiz;
    public ArbolAVL() 
    {
        this.raiz = null;
    }
    public NodoAVL<T> getRaiz() {
    return raiz;
    }
    // Altura y balance
    private int obtenerAltura(NodoAVL<T> nodo) 
    {
        if (nodo == null) return 0;
        return nodo.altura;
    }
    private int obtenerBalance(NodoAVL<T> nodo) 
    {
        if (nodo == null) return 0;
        return obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }
    private void actualizarAltura(NodoAVL<T> nodo) 
    {
        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo),
                                   obtenerAltura(nodo.derecho));
    }
    //  Rotaciones
    private NodoAVL<T> rotacionDerecha(NodoAVL<T> y) 
    {
        NodoAVL<T> x  = y.izquierdo;
        NodoAVL<T> t2 = x.derecho;
        x.derecho   = y;
        y.izquierdo = t2;
        actualizarAltura(y);
        actualizarAltura(x);
        return x;
    }
    private NodoAVL<T> rotacionIzquierda(NodoAVL<T> x) 
    {
        NodoAVL<T> y  = x.derecho;
        NodoAVL<T> t2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho   = t2;
        actualizarAltura(x);
        actualizarAltura(y);
        return y;
    }
    // rotación doble: primero izquierda al hijo, luego derecha al nodo
    private NodoAVL<T> rotacionDobleDerecha(NodoAVL<T> nodo) 
    {
        nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
        return rotacionDerecha(nodo);
    }

    // rotación doble: primero derecha al hijo, luego izquierda al nodo
    private NodoAVL<T> rotacionDobleIzquierda(NodoAVL<T> nodo) 
    {
        nodo.derecho = rotacionDerecha(nodo.derecho);
        return rotacionIzquierda(nodo);
    }
    private NodoAVL<T> balancear(NodoAVL<T> nodo) 
    {
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
    public void insertar(T dato) 
    {
        raiz = insertar(raiz, dato);
    }
    private NodoAVL<T> insertar(NodoAVL<T> nodo, T dato) 
    {
        if (nodo == null) return new NodoAVL<>(dato);
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0)
            nodo.izquierdo = insertar(nodo.izquierdo, dato);
        else if (cmp > 0)
            nodo.derecho = insertar(nodo.derecho, dato);
        else
            return nodo; // código duplicado, no se inserta
        return balancear(nodo);
    }
    // Buscar
    public T buscar(T dato) 
    {
        return buscar(raiz, dato);
    }
    private T buscar(NodoAVL<T> nodo, T dato) 
    {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) return buscar(nodo.izquierdo, dato);
        if (cmp > 0) return buscar(nodo.derecho, dato);
        return nodo.dato;
    }
    //Recorridos
    public void inOrden() 
    {
        inOrden(raiz);
        System.out.println();
    }
    private void inOrden(NodoAVL<T> nodo) 
    {
        if (nodo == null) return;
        inOrden(nodo.izquierdo);
        System.out.print(nodo.dato + "  ");
        inOrden(nodo.derecho);
    }
    public void preOrden() {
        preOrden(raiz);
        System.out.println();
    }
    private void preOrden(NodoAVL<T> nodo) 
    {
        if (nodo == null) return;
        System.out.print(nodo.dato + "  ");
        preOrden(nodo.izquierdo);
        preOrden(nodo.derecho);
    }
    public void postOrden()
    {
        postOrden(raiz);
        System.out.println();
    }
    private void postOrden(NodoAVL<T> nodo) 
    {
        if (nodo == null) return;
        postOrden(nodo.izquierdo);
        postOrden(nodo.derecho);
        System.out.print(nodo.dato + "  ");
    }
    //mostrar
    public void mostrar() 
    {
        if (estaVacio()) 
        {
            System.out.println("El árbol está vacío.");
            return;
        }
        System.out.println("Libros en el catálogo (orden por código):");
        mostrarInOrden(raiz);
    }
    private void mostrarInOrden(NodoAVL<T> nodo) 
    {
        if (nodo == null) return;
        mostrarInOrden(nodo.izquierdo);
        System.out.println("  " + nodo.dato);
        mostrarInOrden(nodo.derecho);
    }
    // Contar y vacío
    public int contar() 
    {
        return contar(raiz);
    }
    private int contar(NodoAVL<T> nodo) 
    {
        if (nodo == null) return 0;
        return 1 + contar(nodo.izquierdo) + contar(nodo.derecho);
    }
    public boolean estaVacio() 
    {
        return raiz == null;
    }
    public void eliminar(T dato) {
    raiz = eliminar(raiz, dato);
}

    private NodoAVL<T> eliminar(NodoAVL<T> nodo, T dato) {
    
        if (nodo == null)
            return null;
    
        int cmp = dato.compareTo(nodo.dato);
    
        if (cmp < 0) {
            nodo.izquierdo = eliminar(nodo.izquierdo, dato);
        } else if (cmp > 0) {
            nodo.derecho = eliminar(nodo.derecho, dato);
        } else {
    
            // Caso 1: sin hijos
            if (nodo.izquierdo == null && nodo.derecho == null)
                return null;
    
            // Caso 2: un hijo
            if (nodo.izquierdo == null)
                return nodo.derecho;
    
            if (nodo.derecho == null)
                return nodo.izquierdo;
    
            // Caso 3: dos hijos
            NodoAVL<T> sucesor = obtenerMinimo(nodo.derecho);
            nodo.dato = sucesor.dato;
            nodo.derecho = eliminar(nodo.derecho, sucesor.dato);
        }
    
        return balancear(nodo);
    }
        private NodoAVL<T> obtenerMinimo(NodoAVL<T> nodo) {
    
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
    
        return nodo;
    }
}
