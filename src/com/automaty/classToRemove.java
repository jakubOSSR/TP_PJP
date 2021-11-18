package com.automaty;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class classToRemove {
    public static void main (String [] args) throws Exception {
        List<String> stavyNKA = new ArrayList<>();
        List<String> akceptujuceStavyNKA = new ArrayList<>();
        List<String> symboly = new ArrayList<>();
        List<String> zaciatocnyStavNKA = new ArrayList<>();
        String [] riadkyTabulkyNKA;

        stavyNKA.add("q0");
        stavyNKA.add("q1");
        stavyNKA.add("q2");
        symboly.add("0");
        symboly.add("1");
        akceptujuceStavyNKA.add("q2");
        zaciatocnyStavNKA.add("q0");
        riadkyTabulkyNKA = new String[stavyNKA.size()];
        //do tabulky sa zapisuje vo formáte:  "stav" aktualny_stav "na" hodnota_prechodu/symbol "do" nasledujuci_stav "a" nasledujuci_stav
        riadkyTabulkyNKA[0]="zo stavu q0 na 0 do q0 a q1";
        riadkyTabulkyNKA[1]="zo stavu q1 na 1 do q1";
        riadkyTabulkyNKA[2]="zo stavu q2 na 0 do q1";
        new NedeterministickyKonecnyAutomat(stavyNKA,symboly,zaciatocnyStavNKA,akceptujuceStavyNKA,riadkyTabulkyNKA);







    }
}