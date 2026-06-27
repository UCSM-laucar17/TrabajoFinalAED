package Estructuras;
//clase nodo con su atributos
public class Nodo<E> {
    E data;
    Nodo<E> next; //nodo siguiente
    public Nodo(E data) {
        this.data = data; //referenciamos al dato, y al nodo siguiente con valores
        this.next = null;
    } 
    //metodos de Nodo
    public E getData() {
        return data;
    }
    public void setData(E data) {
        this.data = data;
    }
    public Nodo<E> getNext() {
        return next;
    }
    public void setNext(Nodo<E> next) {
        this.next = next;
    }
}
