package com.automaty;
import java.util.ArrayList;
import java.util.List;

public class classToRemove {
    public static void main (String [] args) throws Exception {
        List<String> stavyNKA = new ArrayList<>();
        List<String> akceptujuceStavyNKA = new ArrayList<>();
        List<String> symboly = new ArrayList<>();
        String zaciatocnyStavNKA = null;
        String [] riadkyTabulkyNKA;

        stavyNKA.add("q0");
        stavyNKA.add("q1");
        stavyNKA.add("q2");
        symboly.add("0");
        symboly.add("1");
        akceptujuceStavyNKA.add("q2");
        zaciatocnyStavNKA = "q0";
        riadkyTabulkyNKA = new String[stavyNKA.size()];
        //do tabulky sa zapisuje vo formáte:  aktualny_stav na hodnota_prechodu/symbol do nasledujuci_stav/nasledujuce_stavy
        riadkyTabulkyNKA[0]="q0 na 0 do q0 q1";
        riadkyTabulkyNKA[1]="q1 na 1 do q1";
        riadkyTabulkyNKA[2]="q2 na 0 do q1";
        new NedeterministickyKonecnyAutomat(stavyNKA,symboly,zaciatocnyStavNKA,akceptujuceStavyNKA,riadkyTabulkyNKA);







    }
}