package com.gramatiky;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class FirstAFollowTest {
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
    //zoskupenie pravidiel do usporiadanej množiny
    LinkedHashSet<Pravidlo> pravidlaBG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1BG, pravidlo2BG, pravidlo3BG, pravidlo4BG, pravidlo5BG, pravidlo6BG, pravidlo7BG, pravidlo8BG));
    BezkontextovaGramatika bg;
    {
        try {
            bg = new BezkontextovaGramatika(terminalyBG, neterminalyBG,zaciatocnySymbolBG, pravidlaBG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Naplname mapu ktora predstavuje mnozinu first bezkontextovej gramatiky
    public static HashMap<String, ArrayList<String>> naplnenieMnozinyFirst() {
        HashMap<String, ArrayList<String>> vysledokFirst = new HashMap<>();
        ArrayList<String> firstProgram = new ArrayList<>();
        firstProgram.add("begin");
        vysledokFirst.put("<program>", firstProgram);

        ArrayList<String> firstPostupnostPrikazov = new ArrayList<>();
        firstPostupnostPrikazov.add("output");
        firstPostupnostPrikazov.add("epsilon");
        firstPostupnostPrikazov.add("string");
        firstPostupnostPrikazov.add("int");
        firstPostupnostPrikazov.add("if");
        vysledokFirst.put("<postupnost_prikazov>", firstPostupnostPrikazov);

        ArrayList<String> firstDeklaracia = new ArrayList<>();
        firstDeklaracia.add("int"); firstDeklaracia.add("string");
        vysledokFirst.put("<deklaracia>", firstDeklaracia);

        ArrayList<String> firstPodmienka = new ArrayList<>();
        firstPodmienka.add("if");
        vysledokFirst.put("<podmienka>", firstPodmienka);

        ArrayList<String> firstBegin = new ArrayList<>();
        firstBegin.add("begin");
        vysledokFirst.put("begin", firstBegin);

        ArrayList<String> firstEnd = new ArrayList<>();
        firstEnd.add("end");
        vysledokFirst.put("end", firstEnd);

        ArrayList<String> firstInt = new ArrayList<>();
        firstInt.add("int");
        vysledokFirst.put("int", firstInt);

        ArrayList<String> firstString = new ArrayList<>();
        firstString.add("string");
        vysledokFirst.put("string", firstString);

        ArrayList<String> firstIf = new ArrayList<>();
        firstIf.add("if");
        vysledokFirst.put("if", firstIf);

        ArrayList<String> firstThen = new ArrayList<>();
        firstThen.add("then");
        vysledokFirst.put("then", firstThen);

        ArrayList<String> firstElse = new ArrayList<>();
        firstElse.add("else");
        vysledokFirst.put("else", firstElse);

        ArrayList<String> firstOutput = new ArrayList<>();
        firstOutput.add("output");
        vysledokFirst.put("output", firstOutput);

        ArrayList<String> firstText = new ArrayList<>();
        firstText.add("text");
        vysledokFirst.put("text", firstText);

        return vysledokFirst;
    }
    //Naplname mapu ktora predstavuje mnozinu first bezkontextovej gramatiky
    public static HashMap<String, ArrayList<String>> naplnenieMnozinyFollow() {
        HashMap<String, ArrayList<String>> vysledokFollow = new HashMap<>();
        ArrayList<String> followProgram = new ArrayList<>();
        followProgram.add("epsilon");
        vysledokFollow.put("<program>", followProgram);

        ArrayList<String> followPostupnostPrikazov = new ArrayList<>();
        followPostupnostPrikazov.add("end");
        followPostupnostPrikazov.add("else");
        followPostupnostPrikazov.add("output");
        followPostupnostPrikazov.add("int");
        followPostupnostPrikazov.add("string");
        followPostupnostPrikazov.add("if");
        vysledokFollow.put("<postupnost_prikazov>", followPostupnostPrikazov);

        ArrayList<String> followDeklaracia = new ArrayList<>();
        followDeklaracia.add("output");
        followDeklaracia.add("int");
        followDeklaracia.add("string");
        followDeklaracia.add("if");
        followDeklaracia.add("end");
        followDeklaracia.add("else");
        vysledokFollow.put("<deklaracia>", followDeklaracia);

        ArrayList<String> followPodmienka = new ArrayList<>();
        followPodmienka.add("output");
        followPodmienka.add("int");
        followPodmienka.add("if");
        followPodmienka.add("end");
        followPodmienka.add("else");
        followPodmienka.add("string");
        vysledokFollow.put("<podmienka>", followPodmienka);
        return vysledokFollow;
        }
    @Test
    public void overenieFirst(){
        HashMap<String,ArrayList<String>> expectedFirst = naplnenieMnozinyFirst();     //mnozina first definovana vyssie
        HashMap<String,ArrayList<String>> vysledokFirst = FirstAFollow.first(bg);      //mnozina first definovana metodou first v triede FirstAFollow
        Set kluceExp = expectedFirst.keySet();                                         // vytvorenie zoznamu vsetkych terminalov a neterminalov
        Iterator exp = kluceExp.iterator();                                            // vytvorenie iteratora
        while(exp.hasNext()){                                                          //prehladame kluce mnoziny expectedFirst
            String kluc = exp.next().toString();
            ArrayList<String> expected = expectedFirst.get(kluc);                      //ziskame mnozinu first prislusneho kluca z expectedFirst
            ArrayList<String> first = vysledokFirst.get(kluc);                         //ziskame mnozinu first prislusneho kluca z vysledokFirst
            /*porovname first mnoziny daneho kluca a predpokladame ze su rovnake,
            ak niesu, test zlyha*/
            assertTrue(first.containsAll(expected) && expected.containsAll(first));
        }
    }
    /*pri teste pre follow, analogicky postup ako pri first*/
    @Test
    public void overenieFollow(){
        HashMap<String,ArrayList<String>> expectedFollow = naplnenieMnozinyFollow();
        HashMap<String,ArrayList<String>> vysledokFollow = FirstAFollow.follow(bg);
        Set kluceExp = expectedFollow.keySet();
        Iterator exp = kluceExp.iterator();
        while(exp.hasNext()){
            String kluc = exp.next().toString();
            ArrayList<String> expected = expectedFollow.get(kluc);
            ArrayList<String> first = vysledokFollow.get(kluc);
            /*porovname follow mnoziny daneho kluca a predpokladame ze su rovnake,
            ak niesu, test zlyha*/
            assertTrue(first.containsAll(expected) && expected.containsAll(first));
        }
    }


}

