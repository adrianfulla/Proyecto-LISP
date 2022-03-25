package tests;

import comun.Interprete;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de Tests de los metodos del interprete
 *
 * @author Diego Alexander Hernández Silvestre
 * @author Renatto Esteban Guzman Sosa
 * @author Adrian Fulladolsa Palma
 * @author David Jonathan Aragon Vasquez
 */

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
    /**
     * JUnit de prueba setq
     */
    @Test

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

    /**
     * JUnit de prueba menor
     */
    @Test
    void menor() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  menor  es: EL valor 1 es menor a el valor 2" , t.menor("(< 1 2)").perzonalizarOperacion());
    }

    /**
     * JUnit de prueba mayor
     */
    @Test
    void mayor() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  menor  es: EL valor 2 es mayor a el valor 1", t.mayor("(> 1 2)").perzonalizarOperacion());
    }
    /**
     * JUnit de prueba atom
     */
    @Test
    void atomTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  atom  es: NIL" , t.atom("(atom '(1 2))").perzonalizarOperacion());
    }

    /**
     * JUnit de prueba cond
     */
    @Test
    void condTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  cond  es: exitoso" , t.cond("(cond ((< 1 2) (list 'a '1 'b '2) (atom '(1 2))").perzonalizarOperacion());
    }

    /**
     * JUnit de prueba equals
     */
    @Test
    void equalsTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  menor  es: EL valor list 1 2 3 es igual al valor list 1 2 3" , t.equals("(equal (list 1 2 3) (list 1 2 3))").perzonalizarOperacion());
    }

    /**
     * JUnit de prueba combinada
     */
    @Test
    void operacionCombinadaTest() {
        Interprete t = new Interprete();
        assertEquals("El resultado de la operacion  Operacion Combinada  es: 72" , t.combinada("(+ 2 3 5 (+ 2 1 3 46 (* 1 2 3 4 5 6 (* 2 3 4 5 (/ 2 3 4 5)))) 5 5 )").perzonalizarOperacion());
    }
}