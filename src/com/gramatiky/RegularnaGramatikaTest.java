package com.gramatiky;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class RegularnaGramatikaTest {
    HashSet<String> terminalyRG = new HashSet<String>(Arrays.asList("begin", "end", "int", "string", "if", "then", "else", "output", "text"));
    HashSet<String> neterminalyRG = new HashSet<String>(Arrays.asList("<postupnost_prikazov>", "<program>"));
    String zaciatocnySymbolRG = "<program>";
    Pravidlo pravidlo1RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>")));
    Pravidlo pravidlo2RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("text", "<postupnost_prikazov>")));
    Pravidlo pravidlo3RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("if", "<postupnost_prikazov>")));
    Pravidlo pravidlo4RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("int", "<postupnost_prikazov>")));
    Pravidlo pravidlo5RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("text")));
    Pravidlo pravidlo6RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("then", "<postupnost_prikazov>")));
    Pravidlo pravidlo7RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("else", "<postupnost_prikazov>")));
    Pravidlo pravidlo8RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "<postupnost_prikazov>")));
    Pravidlo pravidlo9RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("end")));

    Pravidlo pravidlo10RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "<postupnost_prikazov>", "end")));

    Pravidlo pravidlo11RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<program>")));

    Pravidlo pravidlo12RG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<postupnost_prikazov>", "end")));

    LinkedHashSet<Pravidlo> pravidlaRG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1RG, pravidlo2RG, pravidlo3RG, pravidlo4RG, pravidlo5RG, pravidlo6RG, pravidlo7RG, pravidlo8RG, pravidlo9RG));
    LinkedHashSet<Pravidlo> pravidla2RG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1RG, pravidlo2RG, pravidlo3RG, pravidlo4RG, pravidlo5RG, pravidlo6RG, pravidlo7RG, pravidlo8RG, pravidlo9RG, pravidlo10RG));
    LinkedHashSet<Pravidlo> pravidla3RG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1RG, pravidlo2RG, pravidlo3RG, pravidlo4RG, pravidlo5RG, pravidlo6RG, pravidlo7RG, pravidlo8RG, pravidlo9RG, pravidlo11RG));
    LinkedHashSet<Pravidlo> pravidla4RG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1RG, pravidlo2RG, pravidlo3RG, pravidlo4RG, pravidlo5RG, pravidlo6RG, pravidlo7RG, pravidlo8RG, pravidlo9RG, pravidlo12RG));

    @Test
    void testVsetkoOKRG(){
        try {
            RegularnaGramatika g = new RegularnaGramatika(terminalyRG, neterminalyRG, zaciatocnySymbolRG, pravidlaRG);
        }
        catch (Exception e){
            fail("Nema vyhodit vynimku.");
        }
    }

    @Test
    void testJedenSymbolLS(){
        try {
            RegularnaGramatika g = new RegularnaGramatika(terminalyRG, neterminalyRG, zaciatocnySymbolRG, pravidla2RG);
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            assertEquals("Prava strana obsahuje viac ako dva symboly. Problem je s pravidlom: [<postupnost_prikazov>]->[output, <postupnost_prikazov>, end]", e.getMessage());
        }
    }

    @Test
    void testJedenSymbolTerminal(){
        try {
            RegularnaGramatika g = new RegularnaGramatika(terminalyRG, neterminalyRG, zaciatocnySymbolRG, pravidla3RG);
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            assertEquals("Ak je na pravej strane jeden symbol, musi byt terminal. Problem je s pravidlom: [<postupnost_prikazov>]->[<program>]", e.getMessage());
        }
    }

    @Test
    void testDvaSymboly(){
        try {
            RegularnaGramatika g = new RegularnaGramatika(terminalyRG, neterminalyRG, zaciatocnySymbolRG, pravidla4RG);
            fail("Nevyhodilo vynimku.");
        }
        catch (Exception e){
            assertEquals("Ak su na pravej strane dva symboly, prvy musi byt terminal a druhy neterminal. Problem je s pravidlom: [<postupnost_prikazov>]->[<postupnost_prikazov>, end]", e.getMessage());
        }
    }
}