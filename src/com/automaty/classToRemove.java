package com.automaty;

import java.util.HashSet;

public class classToRemove {
    public static void main (String [] args){
        HashSet<String> stavy;
        HashSet<String> akceptujuceStavy;
        HashSet<String> symboly;
        String zaciatocnyStav = null;
        String [][] prechodovaTabulka;
        PrechodovaFunkcia prechodova;

        stavy = new HashSet<String>();
        stavy.add("q0");
        stavy.add("q1");
        stavy.add("q2");
        symboly = new HashSet<String>();
        symboly.add("0");
        symboly.add("1");
        akceptujuceStavy = new HashSet<String>();
        akceptujuceStavy.add("q2");
        zaciatocnyStav = "q0";
        prechodova = new PrechodovaFunkcia(stavy,symboly);

        new NedeterministickyKonecnyAutomat(stavy,symboly,zaciatocnyStav,akceptujuceStavy);





    }
}