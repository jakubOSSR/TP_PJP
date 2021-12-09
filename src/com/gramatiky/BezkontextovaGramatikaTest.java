package com.gramatiky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class BezkontextovaGramatikaTest {
    //vytvorenie množiny obsahujúcej terminály
    HashSet<String> terminalyBG = new HashSet<String>(Arrays.asList("begin", "end", "int", "string", "if", "then", "else", "output", "text"));
    //vytvorenie množiny obsahujúcej neterminály
    HashSet<String> neterminalyBG = new HashSet<String>(Arrays.asList("<postupnost_prikazov>", "<deklaracia>", "<podmienka>", "<program>"));
    //zadefinovanie začiatočného symbolu
    String zaciatocnySymbolBG = "<program>";
    //zadefinovanie pravidiel gramatiky
    Pravidlo pravidlo1BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
    Pravidlo pravidlo2BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<deklaracia>", "<postupnost_prikazov>")));
    Pravidlo pravidlo3BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("int", "text")));
    Pravidlo pravidlo4BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<podmienka>", "<postupnost_prikazov>")));
    Pravidlo pravidlo5BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<podmienka>")), new ArrayList<String>(Arrays.asList("if", "text", "then", "<postupnost_prikazov>", "else", "<postupnost_prikazov>")));
    Pravidlo pravidlo6BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "text")));
    Pravidlo pravidlo7BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("string", "text")));
    Pravidlo pravidlo8BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("epsilon")));

    //pridanie chybného pravidla, ktoré obsahuje na pravej strane symbol, ktorý nepatrí medzi množinu terminálov, množinu neterminálov a nie je to ani epsilon
    Pravidlo pravidlo9BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<podmienka>")), new ArrayList<String>(Arrays.asList("if", "text", "then", "<postupnost_prikazov>", "a", "<postupnost_prikazov>")));

    //zoskupenie pravidiel do usporiadanej množiny
    //množina pravidiel obsahujúca všetky pravidlá, ktoré spĺňajú podmienky pre bezkontextovú gramatiku
    LinkedHashSet<Pravidlo> pravidlaBG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1BG, pravidlo2BG, pravidlo3BG, pravidlo4BG, pravidlo5BG, pravidlo6BG, pravidlo7BG, pravidlo8BG));
    //množina pravidiel do ktorej sme okrem spĺňajúcich pravididel pridali aj chybné pravidlo 9BG
    LinkedHashSet<Pravidlo> pravidla2BG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1BG, pravidlo2BG, pravidlo3BG, pravidlo4BG, pravidlo5BG, pravidlo6BG, pravidlo7BG, pravidlo8BG, pravidlo9BG));

    //test, ktorý kontroluje či všetky pravidlá spĺňajú podmienky pre bezkontextovú gramatiku
    @Test
    void testVsetkoOKBG(){
        try {
            //vytvorenie bezkontextovej gramatiky, ktorá by nemala vyhodiť výnimku
            BezkontextovaGramatika g = new BezkontextovaGramatika(terminalyBG, neterminalyBG, zaciatocnySymbolBG, pravidlaBG);
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, test skončí neúspešne
            fail("Nema vyhodit vynimku.");
        }
    }

    //test, ktorý kontroluje či dôjde k vyhodeniu požadovanej výnimky, ak bude pravá strana obsahovať symbol, ktorý nie je z množiny terminálov, neterminálov a nie je to ani epsilon
    @Test
    void testVsetkySymbolyPSOK(){
        try {
            //vytvorenie bezkontextovej gramatiky, ktorá by mala vyhodiť výnimku
            BezkontextovaGramatika g = new BezkontextovaGramatika(terminalyBG, neterminalyBG, zaciatocnySymbolBG, pravidla2BG);
            //ak nedôjde k vyhodeniu výnimky test skončí neúspešne
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, otestujeme či očakávaná správa výnimky sa zhoduje s aktuálnou správou výnimky
            assertEquals("Symbol 'a' sa nenachadza medzi terminalmi alebo neterminalmi. Problem je s pravidlom: [<podmienka>]->[if, text, then, <postupnost_prikazov>, a, <postupnost_prikazov>]", e.getMessage());
        }
    }
}