// Carrizales Arenas Jazmin Aracely
package Estructuras;

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
    // retorna 0 si el nodo es null, evita NullPointerException al calcular alturas
    private int obtenerAltura(NodoAVL<T> nodo) 
    {
        if (nodo == null) return 0;
        return nodo.altura;
    }
    // balance positivo = pesa más a la izquierda, negativo = pesa más a la derecha
    private int obtenerBalance(NodoAVL<T> nodo) 
    {
        if (nodo == null) return 0;
        return obtenerAltura(nodo.izquierdo) - obtenerAltura(nodo.derecho);
    }
    // se recalcula después de cada rotación o inserción
    private void actualizarAltura(NodoAVL<T> nodo) 
    {
        nodo.altura = 1 + Math.max(obtenerAltura(nodo.izquierdo),obtenerAltura(nodo.derecho));
    }
    // caso izquierda-izquierda: el hijo izquierdo sube y el nodo actual baja a la derecha
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
    // caso derecha-derecha: el hijo derecho sube y el nodo actual baja a la izquierda
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
    // verifica el factor de balance y aplica la rotación correspondiente
    private NodoAVL<T> balancear(NodoAVL<T> nodo) 
    {
        actualizarAltura(nodo);
        int balance = obtenerBalance(nodo);
        // balance > 1 significa desbalance hacia la izquierda
        if (balance > 1 && obtenerBalance(nodo.izquierdo) >= 0)
            return rotacionDerecha(nodo);
        // Izquierda - Derecha
        if (balance > 1 && obtenerBalance(nodo.izquierdo) < 0)
            return rotacionDobleDerecha(nodo);
        // balance < -1 significa desbalance hacia la derecha
        if (balance < -1 && obtenerBalance(nodo.derecho) <= 0)
            return rotacionIzquierda(nodo);
        // Derecha - Izquierda
        if (balance < -1 && obtenerBalance(nodo.derecho) > 0)
            return rotacionDobleIzquierda(nodo);
        return nodo;
    }
    // método público que inicia la inserción desde la raíz
    public void insertar(T dato) 
    {
        raiz = insertar(raiz, dato);
    }
    // desciende recursivamente hasta encontrar la posición correcta e inserta
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
        // al regresar de la recursión se rebalancea cada nodo afectado
        return balancear(nodo);
    }
    // método público que inicia la búsqueda desde la raíz
    public T buscar(T dato) 
    {
        return buscar(raiz, dato);
    }
    // descarta mitad del árbol en cada paso gracias al orden del AVL
    private T buscar(NodoAVL<T> nodo, T dato) 
    {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) return buscar(nodo.izquierdo, dato);
        if (cmp > 0) return buscar(nodo.derecho, dato);
        return nodo.dato;
    }
    // inOrden produce los elementos ordenados de menor a mayor
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
    // preOrden: raíz, izquierdo, derecho
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
    // postOrden: izquierdo, derecho, raíz
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
    // método público que inicia la eliminación desde la raíz
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
            // caso 1: nodo hoja, se elimina directamente
            if (nodo.izquierdo == null && nodo.derecho == null)
                return null;
            // caso 2: un solo hijo, se reemplaza con ese hijo
            if (nodo.izquierdo == null)
                return nodo.derecho;
            if (nodo.derecho == null)
                return nodo.izquierdo;
            // caso 3: dos hijos, se reemplaza con el sucesor inorden (mínimo del subárbol derecho)
            NodoAVL<T> sucesor = obtenerMinimo(nodo.derecho);
            nodo.dato = sucesor.dato;
            nodo.derecho = eliminar(nodo.derecho, sucesor.dato);
        }
        // rebalancear al subir de la recursión
        return balancear(nodo);
    }
        // baja por la izquierda hasta encontrar el nodo más pequeño
        private NodoAVL<T> obtenerMinimo(NodoAVL<T> nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }
}
