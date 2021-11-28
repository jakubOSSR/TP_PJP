package com.automaty;

import javax.sound.midi.SysexMessage;
import java.util.*;

public class EkvivalentnyDKA {
    private HashSet<String> eStavy;
    private HashSet<String> eSymboly;
    private HashSet<String> eAkceptujuceStavy = new HashSet<String>();
    private Tabulka ePrechodovaDKA = new Tabulka();
    private HashSet<String> eZaciatocnyStav;
    //pomocne
    private Tabulka prechodovaNKA;
    private String[] pomocna;
    private HashMap<String, HashMap<String,HashSet<String>>> pomocnaTabulka;
    private HashMap<String,HashSet<String>> pomocnePravidlaTabulky;
    private HashSet<String> nasledujuciStav;
    private String pomocnaSymbol;
    private String pomocnaStav;
    private String nasledujuciStavS;
    private ArrayList <String> zdruzenyStavy;
    private int krok;
    private String nasledujuciStavZ;

    public EkvivalentnyDKA(NedeterministickyKonecnyAutomat nedeterministickyKA){
        this.eSymboly = nedeterministickyKA.vratSymbolyAut();
        this.eZaciatocnyStav = nedeterministickyKA.vratZacStavNKA();
        this.prechodovaNKA = nedeterministickyKA.vratTabulkuNKA();
        this.eStavy = prechodovaNKA.eStavyDKA();

        
    }
}
