package Estructuras;

import Exceptions.ExceptionIsEmpty;
import IEstructuras.Queue;
import IEstructuras.Node;

public class LinkedQueue<E> implements Queue<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public LinkedQueue() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    // Insertar al final de la cola
    @Override
    public void enqueue(E data) {
        Node<E> newNode = new Node<>(data);
        if (isEmpty()) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
    }

    // Retirar del frente de la cola
    @Override
    public E dequeue() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty(
                "Cola vacía, nada que desencolar.");

        E data = first.getData();
        first = first.getNext();
        size--;

        if (first == null) {
            last = null;
        }
        return data;
    }

    // Ver el primer elemento sin retirarlo
    @Override
    public E peek() throws ExceptionIsEmpty {
        if (isEmpty()) throw new ExceptionIsEmpty(
                "Cola vacía, nada que consultar.");
        return first.getData();
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return this.size;
    }

    // Mostrar todos los elementos de la cola sin modificarla
    @Override
    public void mostrar() {
        if (isEmpty()) {
            System.out.println("  La cola está vacía.");
            return;
        }
        Node<E> aux = first;
        int pos = 1;
        System.out.println("  Cola (frente → final):");
        while (aux != null) {
            System.out.println("  [" + pos + "] " + aux.getData());
            aux = aux.getNext();
            pos++;
        }
    }
}
