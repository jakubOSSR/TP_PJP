package com.gramatiky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import static org.junit.jupiter.api.Assertions.*;

class GramatikaTest {
    //vytvorenie množiny obsahujúcej terminály
    HashSet<String> terminaly = new HashSet<String>(Arrays.asList("begin", "end", "int", "string", "if", "then", "else", "output", "text"));
    //vytvorenie množiny obsahujúcej neterminály
    HashSet<String> neterminaly = new HashSet<String>(Arrays.asList("<postupnost_prikazov>", "<deklaracia>", "<podmienka>", "<program>"));
    //zadefinovanie začiatočného symbolu
    String zaciatocnySymbol = "<program>";
    //zadefinovanie pravidiel gramatiky
    Pravidlo pravidlo1 = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
    Pravidlo pravidlo2 = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<deklaracia>", "<postupnost_prikazov>")));
    Pravidlo pravidlo3 = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("int", "text")));
    Pravidlo pravidlo4 = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<podmienka>", "<postupnost_prikazov>")));
    Pravidlo pravidlo5 = new Pravidlo(new ArrayList<String>(Arrays.asList("<podmienka>")), new ArrayList<String>(Arrays.asList("if", "text", "then", "<postupnost_prikazov>", "else", "<postupnost_prikazov>")));
    Pravidlo pravidlo6 = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "text")));
    Pravidlo pravidlo7 = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("string", "text")));
    Pravidlo pravidlo8 = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("epsilon")));

    //pridanie chybného pravidla, ktoré obsahuje na ľavej strane dva symboly
    Pravidlo pravidlo9 = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>", "begin")), new ArrayList<String>(Arrays.asList("end")));

    //pridanie chybného pravidla, ktoré obsahuje na ľavej strane jeden symbol, ale ten je terminál
    Pravidlo pravidlo10 = new Pravidlo(new ArrayList<String>(Arrays.asList("begin")), new ArrayList<String>(Arrays.asList("end")));

    //pridanie chybného pravidla, ktoré neobsahuje na pravej strane žiaden symbol
    Pravidlo pravidlo11 = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList()));

    //zoskupenie pravidiel do usporiadanej množiny
    //množina pravidiel obsahujúca všetky pravidlá, ktoré spĺňajú podmienky pre ľavú stranu pravidla
    LinkedHashSet<Pravidlo> pravidla = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7, pravidlo8));
    //množina pravidiel do ktorej sme okrem spĺňajúcich pravididel pridali aj chybné pravidlo 9
    LinkedHashSet<Pravidlo> pravidla2 = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7, pravidlo8, pravidlo9));
    //množina pravidiel do ktorej sme okrem spĺňajúcich pravididel pridali aj chybné pravidlo 10
    LinkedHashSet<Pravidlo> pravidla3 = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7, pravidlo8, pravidlo10));
    //množina pravidiel obsahujúca všetky pravidlá, ktoré spĺňajú podmienky pre ľavú stranu pravidla, ale odstránili sme pravidlo pre začiatočný symbol
    LinkedHashSet<Pravidlo> pravidla4 = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6));
    //množina pravidiel do ktorej sme okrem spĺňajúcich pravididel pridali aj chybné pravidlo 11
    LinkedHashSet<Pravidlo> pravidla5 = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7, pravidlo8, pravidlo11));

    //test, ktorý kontroluje či všetky pravidlá spĺňajú podmienky pre ľavú stranu pravidla
    @Test
    void testVsetkoOKLS(){
        try {
            //vytvorenie gramatiky, ktorá by nemala vyhodiť výnimku
            Gramatika g = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, test skončí neúspešne
            fail("Nema vyhodit vynimku.");
        }
    }

    //test, ktorý kontroluje či dôjde k vyhodeniu požadovanej výnimky, ak budú na ľavej strane pravidla dva symboly
    @Test
    void testJedenSymbolLS(){
        try {
            //vytvorenie gramatiky, ktorá by mala vyhodiť výnimku
            Gramatika g = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla2);
            //ak nedôjde k vyhodeniu výnimky test skončí neúspešne
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, otestujeme či očakávaná správa výnimky sa zhoduje s aktuálnou správou výnimky
            assertEquals("Lava strana musi obsahovat iba jeden symbol. Problem je s pravidlom: [<deklaracia>, begin]->[end]", e.getMessage());
        }
    }

    //test, ktorý kontroluje či dôjde k vyhodeniu požadovanej výnimky, ak bude na ľavej strane jeden symbol, ktorý je terminál
    @Test
    void testJedenNeterminalLS(){
        try {
            //vytvorenie gramatiky, ktorá by mala vyhodiť výnimku
            Gramatika g = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla3);
            //ak nedôjde k vyhodeniu výnimky test skončí neúspešne
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, otestujeme či očakávaná správa výnimky sa zhoduje s aktuálnou správou výnimky
            assertEquals("Ak je na lavej strane jeden symbol, musi to byt neterminal. Problem je s pravidlom: [begin]->[end]", e.getMessage());
        }
    }

    //test, ktorý kontroluje či dôjde k vyhodeniu požadovanej výnimky, ak nebude existovať ani jedno pravidlo pre začiatočný symbol
    @Test
    void testPravidlaPreZSLS(){
        try {
            //vytvorenie gramatiky, ktorá by mala vyhodiť výnimku
            Gramatika g = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla4);
            //ak nedôjde k vyhodeniu výnimky test skončí neúspešne
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, otestujeme či očakávaná správa výnimky sa zhoduje s aktuálnou správou výnimky
            assertEquals("Neexistuje ani jedno pravidlo pre zaciatocny symbol.", e.getMessage());
        }
    }

    //test, ktorý kontroluje či dôjde k vyhodeniu požadovanej výnimky, ak nebude na pravej strane pravidla žiaden symbol
    @Test
    void testZiadenSymbolPS(){
        try {
            //vytvorenie gramatiky, ktorá by mala vyhodiť výnimku
            Gramatika g = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla5);
            //ak nedôjde k vyhodeniu výnimky test skončí neúspešne
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            //ak sa vyhodí výnimka, otestujeme či očakávaná správa výnimky sa zhoduje s aktuálnou správou výnimky
            assertEquals("Na pravej strane pravidla sa nenachadza ziaden symbol. Doplnte pravu stranu alebo pravidlo odstrante. Problem je s pravidlom: [<postupnost_prikazov>]->", e.getMessage());
        }
    }

    //test, ktorý kontroluje či metóda getTerminaly vráti správnu hodnotu
    @Test
    void testVratTerminaly() throws Exception {
        //vytvorenie gramatiky
        Gramatika gr = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //do premennej actualTerminaly načítame terminály pomocou metódy getTerminaly
        HashSet<String> actualTerminaly = gr.getTerminaly();
        //vytvoríme si premennú expectedTerminaly, do ktorej vložíme očakávané terminály
        HashSet<String> expectedTerminaly = new HashSet<String>(Arrays.asList("begin", "end", "int", "string", "if", "then", "else", "output", "text"));
        //porovnáme premenné, ak sú rovnaké test bude úspešný
        assertEquals(expectedTerminaly, actualTerminaly);
    }

    //test, ktorý kontroluje či metóda getNeterminaly vráti správnu hodnotu
    @Test
    void testVratNeterminaly() throws Exception {
        //vytvorenie gramatiky
        Gramatika gr = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //do premennej actualNeterminaly načítame neterminály pomocou metódy getNeterminaly
        HashSet<String> actualNeterminaly = gr.getNeterminaly();
        //vytvoríme si premennú expectedNeterminaly, do ktorej vložíme očakávané neterminály
        HashSet<String> expectedNeterminaly = new HashSet<String>(Arrays.asList("<postupnost_prikazov>", "<deklaracia>", "<podmienka>", "<program>"));
        //porovnáme premenné, ak sú rovnaké test bude úspešný
        assertEquals(expectedNeterminaly, actualNeterminaly);
    }

    //test, ktorý kontroluje či metóda getZaciatocnySymbol vráti správnu hodnotu
    @Test
    void testVratZaciatocnySymbol() throws Exception {
        //vytvorenie gramatiky
        Gramatika gr = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //do premennej actualZaciatocnySymbol načítame začiatočný symbol pomocou metódy getZaciatocnySymbol
        String actualZaciatocnySymbol = gr.getZaciatocnySymbol();
        //vytvoríme si premennú expectedZaciatocnySymbol, do ktorej vložíme očakávaný začiatočný symbol
        String expectedZaciatocnySymbol = "<program>";
        //porovnáme premenné, ak sú rovnaké test bude úspešný
        assertEquals(expectedZaciatocnySymbol, actualZaciatocnySymbol);
    }

    //test, ktorý kontroluje či metóda getPravidla vráti správnu hodnotu
    @Test
    void testVratPravidla() throws Exception {
        //vytvorenie gramatiky
        Gramatika gr = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //do premennej actualPravidla načítame pravidlá pomocou metódy getPravidla
        LinkedHashSet<Pravidlo> actualPravidla = gr.getPravidla();
        //vytvoríme si premennú expectedPravidla, do ktorej vložíme očakávané pravidlá
        LinkedHashSet<Pravidlo> expectedPravidla = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7, pravidlo8));
        //porovnáme premenné, ak sú rovnaké test bude úspešný
        assertEquals(expectedPravidla, actualPravidla);
    }

    //kontrola či metóda setTerminaly nastaví správnu hodnotu
    @Test
    void testNastavTerminaly() throws Exception {
        //vytvorenie gramatiky
        Gramatika gr = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //vytvoríme si premennú expectedTerminaly, ktorú budeme na konci testu porovnávať
        HashSet<String> expectedTerminaly = new HashSet<String>(Arrays.asList("start", "end", "int", "string", "if", "then", "else", "output", "text", "koniec"));
        //vytvoríme si premennú actualTerminaly
        HashSet<String> actualTerminaly = new HashSet<>();
        //do premennej actualTerminaly vkladáme terminály
        actualTerminaly.add("start");
        actualTerminaly.add("end");
        actualTerminaly.add("int");
        actualTerminaly.add("string");
        actualTerminaly.add("if");
        actualTerminaly.add("then");
        actualTerminaly.add("else");
        actualTerminaly.add("output");
        actualTerminaly.add("text");
        actualTerminaly.add("koniec");
        //actualTerminaly nastavíme ako terminály gramatiky pomocou metódy setTerminaly
        gr.setTerminaly(actualTerminaly);
        //porovnáme očakávaný výsledok s aktuálnymi terminálmi, ak sú rovnaké test bude úspešný
        assertEquals(expectedTerminaly, gr.getTerminaly());
    }

    //kontrola či metóda setNeterminaly nastaví správnu hodnotu
    @Test
    void testNastavNeterminaly() throws Exception {
        //vytvorenie gramatiky
        Gramatika gr = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //vytvoríme si premennú expectedNeterminaly, ktorú budeme na konci testu porovnávať
        HashSet<String> expectedNeterminaly = new HashSet<String>(Arrays.asList("<PostupnostPrikazov>", "<Deklaracia>", "<Podmienka>", "<Program>"));
        //vytvoríme si premennú actualNeterminaly
        HashSet<String> actualNeterminaly = new HashSet<>();
        //do premennej actualNeterminaly vkladáme neterminály
        actualNeterminaly.add("<PostupnostPrikazov>");
        actualNeterminaly.add("<Deklaracia>");
        actualNeterminaly.add("<Program>");
        actualNeterminaly.add("<Podmienka>");
        //actualNeterminaly nastavíme ako neterminály gramatiky pomocou metódy setNeterminaly
        gr.setNeterminaly(actualNeterminaly);
        //porovnáme očakávaný výsledok s aktuálnymi neterminálmi, ak sú rovnaké test bude úspešný
        assertEquals(expectedNeterminaly, gr.getNeterminaly());
    }

    //kontrola či metóda setZaciatocnySymbol nastaví správnu hodnotu
    @Test
    void testNastavZS() throws Exception {
        //vytvorenie gramatiky
        Gramatika gr = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //vytvoríme si premennú expectedZaciatocnySymbol, ktorú budeme na konci testu porovnávať
        String expectedZaciatocnySymbol = "<postupnost_prikazov>";
        //pomocou metódy setZaciatocnySymbol nastavíme požadovaný začiatočný symbol
        gr.setZaciatocnySymbol("<postupnost_prikazov>");
        //porovnáme očakávaný výsledok s aktuálnym začiatočným symbolom, ak sú rovnaké test bude úspešný
        assertEquals(expectedZaciatocnySymbol, gr.getZaciatocnySymbol());
    }

    //kontrola či metóda setPravidla nastaví správnu hodnotu
    @Test
    void testNastavPravidla() throws Exception {
        //vytvorenie gramatiky
        Gramatika gr = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //vytvoríme si premennú expectedPravidla, ktorú budeme na konci testu porovnávať
        LinkedHashSet<Pravidlo> expectedPravidla = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7));
        //vytvoríme si premennú actualPravidla
        LinkedHashSet<Pravidlo> actualPravidla = new LinkedHashSet<>();
        //do premennej actualPravidla vkladáme pravidlá
        actualPravidla.add(pravidlo1);
        actualPravidla.add(pravidlo2);
        actualPravidla.add(pravidlo3);
        actualPravidla.add(pravidlo4);
        actualPravidla.add(pravidlo5);
        actualPravidla.add(pravidlo6);
        actualPravidla.add(pravidlo7);
        //actualPravidla nastavíme ako pravidlá gramatiky pomocou metódy setPravidla
        gr.setPravidla(actualPravidla);
        //porovnáme očakávaný výsledok s aktuálnymi pravidlami, ak sú rovnaké test bude úspešný
        assertEquals(expectedPravidla, gr.getPravidla());
    }
}