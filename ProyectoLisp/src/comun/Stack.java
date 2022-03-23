package comun;

import java.util.*;

/**
 * Stack
 * @param <T>
 * @author Renatto Guzmán
 */

public class Stack<T> {

    private ArrayList<T> lista;

    public Stack() {
        lista = new ArrayList<>();
    }

    /**
     *
     * @return tamaño del Stack
     */
    public int count() {
        return lista.size();
    }
    /**
     *
     * @return si está vacio el Stack true, si no false
     */

    public boolean isEmpty() {
        return lista.isEmpty();
    }
    /**
     *
     * @return T, primera posición del Stack
     */

    public T peek() {
        return lista.get(0);
    }
    /**
     *
     * @return T, el Stack sin la primera posición
     */

    public void pull() {
        lista.remove(0);    }

    /**
     *
     * @param value valor a meter en el stack
     */

    public void push(T value) {
        lista.add(0, value);
    }

    /**
     *
     * @return pop valor en la primera posición del Stack que se borró
     */
    public T pop(){
        T pop = lista.get(0);
        lista.remove(0);

        return pop;
    }
}
