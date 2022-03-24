package tests;

import comun.Interprete;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterpreteTest {

    @Test
    /**
     * JUnit de prueba suma.
     */
    void sumaTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  suma  es: 3" , t.suma("(+ 1 1 1)").perzonalizarOperacion());
    }

    @Test
    /**
     * JUnit de prueba rest.
     */
    void restaTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  resta  es: -1" , t.resta("(- 1 1 1)").perzonalizarOperacion());
    }

    @Test
    /**
     * JUnit de prueba multiplicacion
     */
    void multiplicacionTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  multiplicacion  es: 8" , t.multiplicacion("(* 2 2 2)").perzonalizarOperacion());
    }

    @Test
    /**
     * JUnit de prueba division
     */
    void divisionTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  division  es: 0.5" , t.division("(/ 2 2 2)").perzonalizarOperacion());
    }
    @Test
    /**
     * JUnit de prueba quote
     */
    void quoteTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  quote  es: ( hola)" , t.quote("(quote( hola))").perzonalizarOperacion());
    }

    @Test
    /**
     * JUnit de prueba setq
     */
    void setqTest() {
        Interprete t = new Interprete();
        assertEquals("Variable: b asignada con valor: 2" , t.setq("(setq a 1 b 2)").perzonalizarOperacion());
    }

    @Test
    /**
     * JUnit de prueba list
     */
    void listTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  list  es: ( a 1 b 2 )" , t.list("(list 'a '1 'b '2)").perzonalizarOperacion());
    }

    @Test
    /**
     * JUnit de prueba atom
     */
    void atomTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  atom  es: NIL" , t.atom("(atom '(1 2))").perzonalizarOperacion());
    }
}