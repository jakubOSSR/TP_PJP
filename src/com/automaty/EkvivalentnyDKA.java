package com.automaty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class EkvivalentnyDKA {
    private HashSet<String> eStavy;
    private HashSet<String> eSymboly;
    private HashSet<String> eAkceptujuceStavy;
    private HashSet<String> eZaciatocnyStav;
    private HashMap<String, HashMap<String,HashSet<String>>> ePrechodovaTabulka;
    private Tabulka prechodovaNKA;

    public EkvivalentnyDKA(NedeterministickyKonecnyAutomat nedeterministickyKA){
        this.eSymboly = nedeterministickyKA.vratSymbolyAut();
        this.eZaciatocnyStav = nedeterministickyKA.vratZacStavNKA();
        this.prechodovaNKA = nedeterministickyKA.vratTabulkuNKA();
        ePrechodovaTabulka = new HashMap<String, HashMap<String,HashSet<String>>>();
    }
}
