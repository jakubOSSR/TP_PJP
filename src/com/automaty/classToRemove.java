package com.automaty;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class classToRemove {
    public static void main (String [] args){
        List<String> stavy = new ArrayList<>();
        List<String> akceptujuceStavy = new ArrayList<>();
        List<String> symboly = new ArrayList<>();
        String zaciatocnyStav = null;
        PrechodovaFunkcia prechodova;
        String [] [] [] prechodovaTabulka;


        stavy.add("q0");
        stavy.add("q1");
        stavy.add("q2");
        symboly.add("0");
        symboly.add("1");
        akceptujuceStavy.add("q2");
        zaciatocnyStav = "q0";
        prechodova = new PrechodovaFunkcia(stavy,symboly);

        new NedeterministickyKonecnyAutomat(stavy,symboly,zaciatocnyStav,akceptujuceStavy);





    }
}