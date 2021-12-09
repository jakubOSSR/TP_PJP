package com.gramatiky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class RegularnaGramatikaTest {
    //vytvorenie množiny obsahujúcej terminály
    HashSet<String> terminalyRG = new HashSet<String>(Arrays.asList("begin", "end", "int", "string", "if", "then", "else", "output", "text"));
    //vytvorenie množiny obsahujúcej neterminály
    HashSet<String> neterminalyRG = new HashSet<String>(Arrays.asList("<postupnost_prikazov>", "<program>"));
    //zadefinovanie začiatočného symbolu
    String zaciatocnySymbolRG = "<program>";
    //zadefinovanie pravidiel gramatiky
    Pravidlo pravidlo1RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>")));
    Pravidlo pravidlo2RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("text", "<postupnost_prikazov>")));
    Pravidlo pravidlo3RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("if", "<postupnost_prikazov>")));
    Pravidlo pravidlo4RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("int", "<postupnost_prikazov>")));
    Pravidlo pravidlo5RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("text")));
    Pravidlo pravidlo6RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("then", "<postupnost_prikazov>")));
    Pravidlo pravidlo7RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("else", "<postupnost_prikazov>")));
    Pravidlo pravidlo8RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "<postupnost_prikazov>")));
    Pravidlo pravidlo9RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("end")));

    //pridanie chybného pravidla, ktoré obsahuje na pravej strane viac ako dva symboly
    Pravidlo pravidlo10RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "<postupnost_prikazov>", "end")));

    //pridanie chybného pravidla, ktoré obsahuje na pravej strane jeden symbol, ktorý je neterminál
    Pravidlo pravidlo11RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<program>")));

    //pridanie chybného pravidla, ktoré obsahuje na pravej strane dva symboly, ale prvý je neterminál a druhý terminál
    Pravidlo pravidlo12RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<postupnost_prikazov>", "end")));

    //zoskupenie pravidiel do usporiadanej množiny
    //množina pravidiel obsahujúca všetky pravidlá, ktoré spĺňajú podmienky pre regulárnu gramatiku
    LinkedHashSet<Pravidlo> pravidlaRG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1RG, pravidlo2RG, pravidlo3RG, pravidlo4RG, pravidlo5RG, pravidlo6RG, pravidlo7RG, pravidlo8RG, pravidlo9RG));
    //množina pravidiel do ktorej sme okrem spĺňajúcich pravididel pridali aj chybné pravidlo 10RG
    LinkedHashSet<Pravidlo> pravidla2RG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1RG, pravidlo2RG, pravidlo3RG, pravidlo4RG, pravidlo5RG, pravidlo6RG, pravidlo7RG, pravidlo8RG, pravidlo9RG, pravidlo10RG));
    //množina pravidiel do ktorej sme okrem spĺňajúcich pravididel pridali aj chybné pravidlo 11RG
    LinkedHashSet<Pravidlo> pravidla3RG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1RG, pravidlo2RG, pravidlo3RG, pravidlo4RG, pravidlo5RG, pravidlo6RG, pravidlo7RG, pravidlo8RG, pravidlo9RG, pravidlo11RG));
    //množina pravidiel do ktorej sme okrem spĺňajúcich pravididel pridali aj chybné pravidlo 12RG
    LinkedHashSet<Pravidlo> pravidla4RG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1RG, pravidlo2RG, pravidlo3RG, pravidlo4RG, pravidlo5RG, pravidlo6RG, pravidlo7RG, pravidlo8RG, pravidlo9RG, pravidlo12RG));

    //test, ktorý kontroluje či všetky pravidlá spĺňajú podmienky pre regulárnu gramatiku
    @Test
    void testVsetkoOKRG(){
        try {
            //vytvorenie regulárnej gramatiky, ktorá by nemala vyhodiť výnimku
            RegularnaGramatika g = new RegularnaGramatika(terminalyRG, neterminalyRG, zaciatocnySymbolRG, pravidlaRG);
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, test skončí neúspešne
            fail("Nema vyhodit vynimku.");
        }
    }

    //test, ktorý kontroluje či dôjde k vyhodeniu požadovanej výnimky, ak budú na pravej strane viac ako dva symboly
    @Test
    void testJedenSymbolLS(){
        try {
            //vytvorenie regulárnej gramatiky, ktorá by mala vyhodiť výnimku
            RegularnaGramatika g = new RegularnaGramatika(terminalyRG, neterminalyRG, zaciatocnySymbolRG, pravidla2RG);
            //ak nedôjde k vyhodeniu výnimky test skončí neúspešne
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, otestujeme či očakávaná správa výnimky sa zhoduje s aktuálnou správou výnimky
            assertEquals("Prava strana obsahuje viac ako dva symboly. Problem je s pravidlom: [<postupnost_prikazov>]->[output, <postupnost_prikazov>, end]", e.getMessage());
        }
    }

    //test, ktorý kontroluje či dôjde k vyhodeniu požadovanej výnimky, ak bude na pravej strane jeden symbol, ktorý je neterminál
    @Test
    void testJedenSymbolTerminal(){
        try {
            //vytvorenie regulárnej gramatiky, ktorá by mala vyhodiť výnimku
            RegularnaGramatika g = new RegularnaGramatika(terminalyRG, neterminalyRG, zaciatocnySymbolRG, pravidla3RG);
            //ak nedôjde k vyhodeniu výnimky test skončí neúspešne
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, otestujeme či očakávaná správa výnimky sa zhoduje s aktuálnou správou výnimky
            assertEquals("Ak je na pravej strane jeden symbol, musi byt terminal. Problem je s pravidlom: [<postupnost_prikazov>]->[<program>]", e.getMessage());
        }
    }

    //test, ktorý kontroluje či dôjde k vyhodeniu požadovanej výnimky, ak budú na pravej strane dva symboly, ale prvý bude neterminál a druhý terminál
    @Test
    void testDvaSymboly(){
        try {
            //vytvorenie regulárnej gramatiky, ktorá by mala vyhodiť výnimku
            RegularnaGramatika g = new RegularnaGramatika(terminalyRG, neterminalyRG, zaciatocnySymbolRG, pravidla4RG);
            //ak nedôjde k vyhodeniu výnimky test skončí neúspešne
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, otestujeme či očakávaná správa výnimky sa zhoduje s aktuálnou správou výnimky
            assertEquals("Ak su na pravej strane dva symboly, prvy musi byt terminal a druhy neterminal. Problem je s pravidlom: [<postupnost_prikazov>]->[<postupnost_prikazov>, end]", e.getMessage());
        }
    }
}