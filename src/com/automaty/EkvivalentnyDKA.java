package com.automaty;

import java.util.*;

public class EkvivalentnyDKA {
    private HashSet<String> eStavy;
    private HashSet<String> eSymboly;
    private HashSet<String> eAkceptujuceStavy = new HashSet<String>();
    private HashSet<String> eZaciatocnyStav;
    private HashMap<String, HashMap<String,HashSet<String>>> ePrechodovaTabulka;
    private Tabulka prechodovaNKA;
    private String[] pomocna;

    public EkvivalentnyDKA(NedeterministickyKonecnyAutomat nedeterministickyKA){
        this.eSymboly = nedeterministickyKA.vratSymbolyAut();
        this.eZaciatocnyStav = nedeterministickyKA.vratZacStavNKA();
        this.prechodovaNKA = nedeterministickyKA.vratTabulkuNKA();
        this.eStavy = prechodovaNKA.eStavyDKA();
        pomocna = new String[eStavy.size()];
        eStavy.toArray(pomocna);

        for(int i = 0; i< pomocna.length;i++){
            if(pomocna[i].contains(nedeterministickyKA.vratAkcStavNKA().toString().replace("[","")
                                                                                  .replace("]",""))){
                this.eAkceptujuceStavy.add(pomocna[i]);
            }
        }
        







    }
}
