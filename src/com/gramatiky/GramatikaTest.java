package com.gramatiky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class GramatikaTest {
    HashSet<String> terminaly = new HashSet<String>(Arrays.asList("begin", "end", "int", "string", "if", "then", "else", "output", "text"));
    HashSet<String> neterminaly = new HashSet<String>(Arrays.asList("<postupnost_prikazov>", "<deklaracia>", "<podmienka>", "<program>"));
    String zaciatocnySymbol = "<program>";
    Pravidlo pravidlo1 = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
    Pravidlo pravidlo2 = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<deklaracia>", "<postupnost_prikazov>")));
    Pravidlo pravidlo3 = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("int", "text")));
    Pravidlo pravidlo4 = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<podmienka>", "<postupnost_prikazov>")));
    Pravidlo pravidlo5 = new Pravidlo(new ArrayList<String>(Arrays.asList("<podmienka>")), new ArrayList<String>(Arrays.asList("if", "text", "then", "<postupnost_prikazov>", "else", "<postupnost_prikazov>")));
    Pravidlo pravidlo6 = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "text")));
    Pravidlo pravidlo7 = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("string", "text")));
    Pravidlo pravidlo8 = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("epsilon")));

    Pravidlo pravidlo9 = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>", "begin")), new ArrayList<String>(Arrays.asList("end")));

    Pravidlo pravidlo10 = new Pravidlo(new ArrayList<String>(Arrays.asList("begin")), new ArrayList<String>(Arrays.asList("end")));

    LinkedHashSet<Pravidlo> pravidla = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7, pravidlo8));
    LinkedHashSet<Pravidlo> pravidla2 = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7, pravidlo8, pravidlo9));
    LinkedHashSet<Pravidlo> pravidla3 = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7, pravidlo8, pravidlo10 ));
    LinkedHashSet<Pravidlo> pravidla4 = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6));

    @Test
    void testVsetkoOKLS(){
        try {
            Gramatika g = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        }
        catch (Exception e){
            fail("Nema vyhodit vynimku.");
        }
    }

    @Test
    void testJedenSymbolLS(){
        try {
            Gramatika g = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla2);
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            assertEquals("Lava strana musi obsahovat iba jeden symbol. Problem je s pravidlom: [<deklaracia>, begin]->[end]", e.getMessage());
        }
    }

    @Test
    void testJedenNeterminalLS(){
        try {
            Gramatika g = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla3);
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            assertEquals("Ak je na lavej strane jeden symbol, musi to byt neterminal. Problem je s pravidlom: [begin]->[end]", e.getMessage());
        }
    }

    @Test
    void testPravidlaPreZSLS(){
        try {
            Gramatika g = new Gramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla4);
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            assertEquals("Neexistuje ani jedno pravidlo pre zaciatocny symbol.", e.getMessage());
        }
    }
}