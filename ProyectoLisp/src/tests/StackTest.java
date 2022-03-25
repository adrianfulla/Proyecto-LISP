package tests;

import comun.Stack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de Tests de los métodos del Stack
 *
 * @author Diego Alexander Hernández Silvestre
 * @author Renatto Esteban Guzman Sosa
 * @author Adrian Fulladolsa Palma
 * @author David Jonathan Aragon Vasquez
 */

class StackTest {

    /**
     * Prueba JUnit para count
     */
    @Test
    void testCount() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);
        Stack.push(5);
        Stack.push(6);

        assertEquals(3, Stack.count());
    }

    /**
     * Prueba JUnit para push
     */
    @Test
    void testPush() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);

        assertEquals(4, Stack.peek());
    }

    /**
     * Prueba JUnit para pull
     */
    @Test
    void testPull() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);
        Stack.pull();

        assertTrue(Stack.isEmpty());
    }

    /**
     * Prueba JUnit para isEmpty
     */
    @Test
    void testIsEmpty() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);

        assertFalse(Stack.isEmpty());

        Stack.pull();
        assertTrue(Stack.isEmpty());
    }

    /**
     * Prueba JUnit para peek
     */
    @Test
    void testPeek() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);
        Stack.push(5);
        Stack.push(6);

        assertEquals(6, Stack.peek());
    }
}