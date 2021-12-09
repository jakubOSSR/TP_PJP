package com.NadbytocneSymboly;

import com.gramatiky.BezkontextovaGramatika;
import com.gramatiky.Pravidlo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OdstranenieNSTest {
    public static void main(String[] args) throws Exception {
        //vytvorenie množiny obsahujúcej terminály
        HashSet<String> terminalyNSBG = new HashSet<String>(Arrays.asList("begin", "end", "int", "string", "if", "then", "else", "output", "text","//"));
        //vytvorenie množiny obsahujúcej neterminály
        HashSet<String> neterminalyNSBG = new HashSet<String>(Arrays.asList("<postupnost_prikazov>", "<deklaracia>", "<podmienka>", "<program>", "<prikaz>","<komentar>"));
        //zadefinovanie začiatočného symbolu
        String zaciatocnySymbolNSBG = "<program>";

        //zadefinovanie pravidiel gramatiky
        Pravidlo pravidlo1NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
        Pravidlo pravidlo2NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<deklaracia>", "<postupnost_prikazov>")));
        Pravidlo pravidlo3NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("int", "text")));
        Pravidlo pravidlo4NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<podmienka>", "<postupnost_prikazov>")));
        Pravidlo pravidlo5NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<podmienka>")), new ArrayList<String>(Arrays.asList("if", "text", "then", "<postupnost_prikazov>", "else", "<postupnost_prikazov>")));
        Pravidlo pravidlo6NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "text")));
        Pravidlo pravidlo7NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("string", "text")));
        Pravidlo pravidlo8NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("epsilon")));

        //pridanie pravidla, ktoré na ľavej strane obsahuje neterminál, ktorý nieje možné prepísať na terminálny reťazec
        Pravidlo pravidlo9NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<prikaz>")), new ArrayList<String>(Arrays.asList("<prikaz>")));

        //pridanie pravidla, ktoré na pravej strane obsahuje neterminál, ktorý nieje možné prepísať na terminálny reťazec
        Pravidlo pravidlo10NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<deklaracia>", "<prikaz>")));

        //pridanie pravidla, ktoré na lavej strane obsahuje neterminál, ktorý je nedostupným symbolom
        Pravidlo pravidlo11NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<komentar>")), new ArrayList<String>(Arrays.asList("//","text")));
        //zoskupenie pravidiel do usporiadanej množiny
        LinkedHashSet<Pravidlo> pravidlaNSBG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1NSBG, pravidlo2NSBG, pravidlo3NSBG, pravidlo4NSBG, pravidlo5NSBG, pravidlo6NSBG, pravidlo7NSBG, pravidlo8NSBG,pravidlo9NSBG,pravidlo10NSBG, pravidlo11NSBG ));
        //vytvorenie bezkontextovej gramatiky, ktorá by nemala vyhodiť výnimku
        BezkontextovaGramatika g = new BezkontextovaGramatika(terminalyNSBG, neterminalyNSBG, zaciatocnySymbolNSBG, pravidlaNSBG);
        //test, ktorý by mal uspešne odstrániť nadbytočné symboly z testovanej gramatiky
        //a následne vypísat terminály, neterminály, začiatočné symbol a pravidlá redukovanej gramatiky
        OdstranenieNS.odstranenieNS(g);
        //Vo výpise sa nebude nachádzať terminál "//" a neterminály <prikaz> a <komentar>
        //taktiež sa odstránia pravidlá (pravidlo9NSBG, pravidlo10NSBG, pravidlo11NSBG)
    }
}