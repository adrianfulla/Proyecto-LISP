package tests;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class QuoteTest {
    comun.Quote q = new comun.Quote();
    @org.junit.jupiter.api.Test

    void print() {
        String test = q.print("Hola mundo");
        assertEquals("Hola mundo", test);
    }

    @org.junit.jupiter.api.Test
    void testPrint() {
        String test = q.print((float) 3.141592);
        assertEquals("3.141592", test);
    }

    @org.junit.jupiter.api.Test
    void testPrint1() {
        ArrayList<Integer> par = new ArrayList<Integer>();
        par.add(1);
        par.add(2);
        par.add(3);
        par.add(4);
        String test = q.print(par);
        assertEquals("1,2,3,4", test);
    }

    @org.junit.jupiter.api.Test
    void testPrint2() {
        HashMap<String,String> par = new HashMap<>();
        par.put("Guatemala", "Peten");
        String test = q.print(par);
        assertEquals("Guatemala=Peten", test);
    }
}