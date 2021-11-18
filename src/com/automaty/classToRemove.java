package com.automaty;
import java.util.ArrayList;
import java.util.List;

public class classToRemove {
    public static void main (String [] args) throws Exception {
        List<String> stavy = new ArrayList<>();
        List<String> akceptujuceStavy = new ArrayList<>();
        List<String> symboly = new ArrayList<>();
        String zaciatocnyStav = null;
        String [] riadkyTabulkyNKA;

        stavy.add("q0");
        stavy.add("q1");
        stavy.add("q2");
        symboly.add("0");
        symboly.add("1");
        akceptujuceStavy.add("q2");
        zaciatocnyStav = "q0";
        riadkyTabulkyNKA = new String[stavy.size()];
        //do tabulky sa zapise vo formáte:  aktualny_stav na hodnota_prechodu/symbol do nasledujuci_stav/nasledujuce_stavy
        riadkyTabulkyNKA[0]="q0 na 0 do q0 q1"; //q1
        riadkyTabulkyNKA[1]="q1 na 1 do q1";
        riadkyTabulkyNKA[2]="q2 na 0 do q1";//q2
        new NedeterministickyKonecnyAutomat(stavy,symboly,zaciatocnyStav,akceptujuceStavy,riadkyTabulkyNKA);

        new PrechodovaFunkcia(stavy,symboly,riadkyTabulkyNKA);






    }
}