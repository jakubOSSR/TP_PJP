package com.gramatiky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class BezkontextovaGramatikaTest {
    HashSet<String> terminalyBG = new HashSet<String>(Arrays.asList("begin", "end", "int", "string", "if", "then", "else", "output", "text"));
    HashSet<String> neterminalyBG = new HashSet<String>(Arrays.asList("<postupnost_prikazov>", "<deklaracia>", "<podmienka>", "<program>"));
    String zaciatocnySymbolBG = "<program>";
    Pravidlo pravidlo1BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
    Pravidlo pravidlo2BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<deklaracia>", "<postupnost_prikazov>")));
    Pravidlo pravidlo3BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("int", "text")));
    Pravidlo pravidlo4BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<podmienka>", "<postupnost_prikazov>")));
    Pravidlo pravidlo5BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<podmienka>")), new ArrayList<String>(Arrays.asList("if", "text", "then", "<postupnost_prikazov>", "else", "<postupnost_prikazov>")));
    Pravidlo pravidlo6BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "text")));
    Pravidlo pravidlo7BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("string", "text")));
    Pravidlo pravidlo8BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("epsilon")));

    Pravidlo pravidlo9BG = new Pravidlo(new ArrayList<String>(Arrays.asList("<podmienka>")), new ArrayList<String>(Arrays.asList("if", "text", "then", "<postupnost_prikazov>", "a", "<postupnost_prikazov>")));

    LinkedHashSet<Pravidlo> pravidlaBG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1BG, pravidlo2BG, pravidlo3BG, pravidlo4BG, pravidlo5BG, pravidlo6BG, pravidlo7BG, pravidlo8BG));
    LinkedHashSet<Pravidlo> pravidla2BG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1BG, pravidlo2BG, pravidlo3BG, pravidlo4BG, pravidlo5BG, pravidlo6BG, pravidlo7BG, pravidlo8BG, pravidlo9BG));

    @Test
    void testVsetkoOKBG(){
        try {
            BezkontextovaGramatika g = new BezkontextovaGramatika(terminalyBG, neterminalyBG, zaciatocnySymbolBG, pravidlaBG);
        }
        catch (Exception e){
            fail("Nema vyhodit vynimku.");
        }
    }

    @Test
    void testVsetkySymbolyPSOK(){
        try {
            BezkontextovaGramatika g = new BezkontextovaGramatika(terminalyBG, neterminalyBG, zaciatocnySymbolBG, pravidla2BG);
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            assertEquals("Symbol 'a' sa nenachadza medzi terminalmi alebo neterminalmi. Problem je s pravidlom: [<podmienka>]->[if, text, then, <postupnost_prikazov>, a, <postupnost_prikazov>]", e.getMessage());
        }
    }
}