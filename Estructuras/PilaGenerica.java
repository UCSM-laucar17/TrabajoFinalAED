package Estructuras;

public class PilaGenerica<E> {
    private Object[] arreglo;
    private int top;
    private int capacidad;
     // Capacidad con la que arranca la pila. Es pequeña a proposito: redimensionar() la duplica
    private static final int CAPACIDAD_INICIAL = 100;
    public PilaGenerica() {
        this.capacidad = CAPACIDAD_INICIAL;
        this.arreglo = new Object[capacidad];
        this.top = -1;
    }
    // Insertar en el tope
    public void push(E data) {
        if (top == capacidad - 1) {
            redimensionar();
        }
         // Primero incrementa top, luego guarda el elemento
        arreglo[++top] = data;
    }
    // Retirar del tope
    @SuppressWarnings("unchecked")
    public E pop() throws ExcepcionVacia {
        if (isEmpty()) throw new ExcepcionVacia("Pila vacía, nada que retirar.");
        E data = (E) arreglo[top];
        arreglo[top] = null;
        top--;
        return data;
    }
    // Ver el tope sin retirarlo
    @SuppressWarnings("unchecked")
    public E peek() throws ExcepcionVacia {
        if (isEmpty()) throw new ExcepcionVacia("Pila vacía, nada que consultar.");
        return (E) arreglo[top];
    }
    public boolean isEmpty() {
        return top == -1;  //-1 indica que la pila está vacía
    }
    public int size() {
        return top + 1;
    }
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("  La pila está vacía.");
            return;
        }
        System.out.println("  Pila (tope → fondo):");
        for (int i = top; i >= 0; i--) {
            System.out.println("  [" + (top - i + 1) + "] " + arreglo[i]);
        }
    }
    // Redimensionar si se llena el arreglo
    private void redimensionar() {
        capacidad *= 2;
        Object[] nuevo = new Object[capacidad];
        System.arraycopy(arreglo, 0, nuevo, 0, arreglo.length);
        arreglo = nuevo;
    }
}
