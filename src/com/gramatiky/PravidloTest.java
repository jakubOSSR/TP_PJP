package com.gramatiky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PravidloTest {

    //kontrola či metóda getLavaStrana vráti správnu hodnotu
    @Test
    void testVratLS(){
        //vytvoríme nové pravidlo
        Pravidlo pravidlo = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
        //vytvoríme si premennú expectedLS
        ArrayList<String> expectedLS = new ArrayList<>();
        //do premennej expectedLS vložíme hodnotu
        expectedLS.add("<program>");
        //porovnáme očakávanú a aktuálnu hodnotu, ak sa zhodujú test bude úspešný
        assertEquals(expectedLS, pravidlo.getLavaStrana());
    }

    //kontrola či metóda getPravaStrana vráti správnu hodnotu
    @Test
    void testVratPS(){
        //vytvoríme nové pravidlo
        Pravidlo pravidlo = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
        //vytvoríme si premennú expectedPS
        ArrayList<String> expectedPS = new ArrayList<>();
        //do premennej expectedPS vložíme hodnoty
        expectedPS.add("begin");
        expectedPS.add("<postupnost_prikazov>");
        expectedPS.add("end");
        //porovnáme očakávanú a aktuálnu hodnotu, ak sa zhodujú test bude úspešný
        assertEquals(expectedPS, pravidlo.getPravaStrana());
    }

    //kontrola či metóda setLavaStrana nastaví správnu hodnotu
    @Test
    void testNastavLS(){
        //vytvoríme pravidlo v ktorom budeme meniť ľavú stranu
        Pravidlo actualPravidlo = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
        //vytvoríme pravidlo, ktoré budeme porovnávať
        Pravidlo expectedPravidlo = new Pravidlo(new ArrayList<String>(Arrays.asList("<start>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
        //vytvoríme si premennú zmenaLS
        ArrayList<String> zmenaLS = new ArrayList<>();
        //do premennej zmenaLS nastavíme hodnotu
        zmenaLS.add("<start>");
        //nastavíme hodnotu ľavej strany pre pravidlo actualPravidlo pomocou metódy setLavaStrana
        actualPravidlo.setLavaStrana(zmenaLS);
        //porovnáme ľavé strany pravidiel, ak sa zhodujú test bude úspešný
        assertEquals(expectedPravidlo.getLavaStrana(), actualPravidlo.getLavaStrana());
    }

    //kontrola či metóda setPravaStrana nastaví správnu hodnotu
    @Test
    void testNastavPS(){
        //vytvoríme pravidlo v ktorom budeme meniť pravú stranu
        Pravidlo actualPravidlo = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
        //vytvoríme pravidlo, ktoré budeme porovnávať
        Pravidlo expectedPravidlo = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("start", "prikazy", "koniec")));
        //vytvoríme si premennú zmenaPS
        ArrayList<String> zmenaPS = new ArrayList<>();
        //do premennej zmenaPS nastavíme hodnoty
        zmenaPS.add("start");
        zmenaPS.add("prikazy");
        zmenaPS.add("koniec");
        //nastavíme hodnoty pravej strany pre pravidlo actualPravidlo pomocou metódy setPravaStrana
        actualPravidlo.setPravaStrana(zmenaPS);
        //porovnáme pravé strany pravidiel, ak sa zhodujú test bude úspešný
        assertEquals(expectedPravidlo.getPravaStrana(), actualPravidlo.getPravaStrana());
    }
}