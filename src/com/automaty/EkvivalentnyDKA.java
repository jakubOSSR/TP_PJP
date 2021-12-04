package com.automaty;

import javax.sound.midi.SysexMessage;
import java.sql.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class EkvivalentnyDKA {
    private HashSet<String> eStavy = new HashSet<String>();
    private HashSet<String> eSymboly;
    private HashSet<String> eAkceptujuceStavy;
    private Tabulka ePrechodovaDKA = new Tabulka();
    private Tabulka ePrechodovaDKAFinal = new Tabulka();
    private HashSet<String> eZaciatocnyStav;
    //pomocne
    private HashMap<String, HashMap<String, HashSet<String>>> tabulkaNKA;
    private HashMap<String, HashMap<String, HashSet<String>>> tab1;
    private HashMap<String, HashMap<String, HashSet<String>>> riadok1;
    private HashMap<String, HashMap<String, HashSet<String>>> vyslednaTab = new HashMap<String, HashMap<String, HashSet<String>>>();
    private HashMap<String, HashMap<String, HashSet<String>>> kopiaVyslednaTab = new HashMap<String, HashMap<String, HashSet<String>>>();
    private HashMap<String, HashMap<String, HashSet<String>>> ePomocnaPrechodova = new HashMap<String, HashMap<String, HashSet<String>>>();
    private Tabulka pomocnaPrechodova;
    private Tabulka riadok;
    private HashSet<String> novyStav;
    private int k = 0;

    public EkvivalentnyDKA(NedeterministickyKonecnyAutomat nedeterministickyKA) {
        this.eSymboly = nedeterministickyKA.vratSymbolyAut();
        this.eZaciatocnyStav = nedeterministickyKA.vratZacStavNKA();
        pomocnaPrechodova = nedeterministickyKA.vratTabulkuNKA();
        tabulkaNKA = pomocnaPrechodova.vratPrechodovuTabulku();

        for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulkaNKA.entrySet()) {
            for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                if (m1.getKey().equals(eZaciatocnyStav.toString().replace("[", "")
                        .replace("]", ""))) {
                    ePrechodovaDKA.pridajRiadok(m1.getKey(), m2.getKey(), m2.getValue().toArray(new String[0]));
                }
            }
        }
        eStavy.add(eZaciatocnyStav.toString());
        ePomocnaPrechodova = ePrechodovaDKA.vratPrechodovuTabulku();
        najdiNovyStav(ePomocnaPrechodova);
        upravTabulku(vyslednaTab);
        ePrechodovaDKAFinal.vypisTabulku();

    }

    public void najdiNovyStav(HashMap<String, HashMap<String, HashSet<String>>> tabulka) {
        novyStav = new HashSet<String>();
        for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulka.entrySet()) {
            for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                if (!eStavy.contains(m2.getValue().toString())) {
                    novyStav.addAll(m2.getValue());
                }
            }
        }
        if (!novyStav.isEmpty()) {
            riadok = new Tabulka();
            for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulkaNKA.entrySet()) {
                for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                    if (novyStav.contains(m1.getKey())) {
                        riadok.pridajRiadokPreZS(novyStav.toString(), m2.getKey(), m2.getValue());
                    }
                }
            }
            eStavy.add(novyStav.toString());
            tab1 = ePrechodovaDKA.vratPrechodovuTabulku();
            riadok1 = riadok.vratPrechodovuTabulku();
            vyslednaTab.putAll(tab1);
            vyslednaTab.putAll(riadok1);
            najdiNovyStav(vyslednaTab);

        }
    }

    public void upravTabulku(HashMap<String, HashMap<String, HashSet<String>>> tabulka) {
        for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulka.entrySet()) {
            for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                if (m1.getKey().length() > 2) {
                    ePrechodovaDKAFinal.pridajRiadok(m1.getKey().toString().replace(", ", "")
                                    .replace("[", "")
                                    .replace("]", ""),
                            m2.getKey(), m2.getValue().toString().replace(", ", "")
                                    .replace("[", "")
                                    .replace("]", ""));
                } else {
                    ePrechodovaDKAFinal.pridajRiadok(m1.getKey().toString(), m2.getKey(), m2.getValue().toString()
                            .replace(", ", "")
                            .replace("[", "")
                            .replace("]", ""));
                }
            }
        }
    }
}

