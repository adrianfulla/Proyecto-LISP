package comun;

import java.util.*;

/**
 * Stack
 * @param <T>
 * @author Renatto Guzmán
 */

public class Stack<T> {

    private ArrayList<T> miListaInterna;

    public Stack() {
        miListaInterna = new ArrayList<T>();
    }

    /**
     * Añade un valor a la pila en la primer posición.
     * @param value Valor a agregar.
     */

    public void push(T value) {
        miListaInterna.add(0, value);
    }

    /**
     * Elimina el elemento de la pila en la posición cero.
     * @return El elemento removido
     */

    public T pull() {
        return miListaInterna.remove(0);
    }

    /**
     * Obtiene el valor del elemento de la posición cero de la pila
     * @return El elemento obtenido.
     */

    public T peek() {
        return miListaInterna.get(0);
    }

    /**
     * Cuenta la cantidad de datos que tiene la pila en ese momento.
     * @return El número de elementos de la pila.
     */

    public int count() {
        return miListaInterna.size();
    }

    /**
     * Verifica si la pila se encuentra vacia.
     * @return True = Vacia, False = Contiene elementos.
     */

    public boolean isEmpty() {
        return miListaInterna.isEmpty();
    }
}
