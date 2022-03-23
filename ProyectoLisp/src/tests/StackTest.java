package tests;

import comun.Stack;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {
    @Test
    void testCount() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);
        Stack.push(5);
        Stack.push(6);

        assertEquals(3, Stack.count());
    }

    @Test
    void testPush() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);

        assertEquals(4, Stack.peek());
    }

    @Test
    void testPull() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);
        Stack.pull();

        assertTrue(Stack.isEmpty());
    }

    @Test
    void testIsEmpty() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);

        assertFalse(Stack.isEmpty());

        Stack.pull();
        assertTrue(Stack.isEmpty());
    }

    @Test
    void testPeek() {
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);
        Stack.push(5);
        Stack.push(6);

        assertEquals(6, Stack.peek());
    }

    @Test
    void testPop(){
        Stack<Integer> Stack = new Stack<Integer>();
        Stack.push(4);
        Stack.push(5);
        Stack.push(6);
        int num6 = Stack.peek();

        assertEquals(num6, Stack.pop());
        assertNotEquals(num6, Stack.pop());
    }
}