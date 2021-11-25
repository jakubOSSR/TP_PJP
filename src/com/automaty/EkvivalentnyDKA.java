package com.automaty;

import javax.sound.midi.SysexMessage;
import java.util.*;

public class EkvivalentnyDKA {
    private HashSet<String> eStavy;
    private HashSet<String> eSymboly;
    private HashSet<String> eAkceptujuceStavy = new HashSet<String>();
    private HashSet<String> eZaciatocnyStav;
    private HashMap<String, HashMap<String,HashSet<String>>> pomocnaTabulka;
    private HashMap<String,HashSet<String>> pomocnePravidlaTabulky;
    private HashSet<String> nasledujuciStav;
    private Tabulka prechodovaNKA;
    private Tabulka ePrechodovaDKA;
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
        System.out.println(eStavy);
        System.out.println(eAkceptujuceStavy);
        pomocnaTabulka = prechodovaNKA.vratPrechodovuTabulku();
       /*  for (Map.Entry<String,HashMap<String,HashSet<String>>> s : pomocnaTabulka.entrySet()) {
            System.out.println(s.getKey()+" : "+s.getValue());
        }
        */
        for(int i = 0; i< pomocnaTabulka.size();i++){
            pomocnePravidlaTabulky = new HashMap<String,HashSet<String>>();
            for(int j =0; j< pomocnePravidlaTabulky.size();j++){

            }

        }








    }
}
