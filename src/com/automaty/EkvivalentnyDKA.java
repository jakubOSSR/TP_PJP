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
        System.out.println("--------NKA TABULKA--------");
        pomocnaPrechodova.vypisTabulku();
        System.out.println("______PARAMETRE DKA______");
        ePrechodovaDKA.vypisTabulku();
        ePomocnaPrechodova = ePrechodovaDKA.vratPrechodovuTabulku();
        najdiNovyStav(ePomocnaPrechodova);
        System.out.println("STAVY SU:" + eStavy);
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
        System.out.println("NOVY STAV JE: " + novyStav);
        if (!novyStav.isEmpty()) {
            riadok = new Tabulka();
            for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulkaNKA.entrySet()) {
                for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                    if (novyStav.contains(m1.getKey())) {
                        riadok.pridajRiadokPreZS(novyStav.toString(), m2.getKey(), m2.getValue());
                        riadok.vypisTabulku();
                    }
                }
            }
            eStavy.add(novyStav.toString());
            tab1 = ePrechodovaDKA.vratPrechodovuTabulku();
            riadok1 = riadok.vratPrechodovuTabulku();
            vyslednaTab.putAll(tab1);
            vyslednaTab.putAll(riadok1);
            System.out.println("xxxxxxxxx");
            System.out.println(vyslednaTab);
            System.out.println("xxxxxxxxxx");
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
   /* public void upravTabulku(HashMap<String, HashMap<String,HashSet<String>>> tabulka) {
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

        vyslednaTab = ePrechodovaDKAFinal.vratPrechodovuTabulku();
        pomocnaTabulka.putAll(tabulka);
        System.out.println("DKA NOVA");
        for (Map.Entry<String, HashMap<String, HashSet<String>>> s : vyslednaTab.entrySet()) {
            System.out.println(s.getKey() + " : " + s.getValue().toString());
        }


    } */

/*
 public class EkvivalentnyDKA {
    private HashSet<String> eStavy = new HashSet<String>();
    private HashSet<String> eSymboly;
    private HashSet<String> eAkceptujuceStavy;
    private Tabulka ePrechodovaDKA = new Tabulka();
    private Tabulka ePrechodovaDKAFinal = new Tabulka();
    private HashSet<String> eZaciatocnyStav;
    //pomocne
    private HashMap<String, HashMap<String,HashSet<String>>> pomocnaTabulka;
    private HashMap<String, HashMap<String,HashSet<String>>> tab1;
    private HashMap<String, HashMap<String,HashSet<String>>> riadok1;
    private HashMap<String, HashMap<String,HashSet<String>>> vyslednaTab = new HashMap<String, HashMap<String,HashSet<String>>>();
    private HashMap<String, HashMap<String,HashSet<String>>> kopiaVyslednaTab = new HashMap<String, HashMap<String,HashSet<String>>>();
    private Tabulka pomocnaPrechodova;
    private Tabulka riadok;
    private ArrayList<String> noveStavy;
    private int k = 0;

    public EkvivalentnyDKA(NedeterministickyKonecnyAutomat nedeterministickyKA){
        this.eSymboly = nedeterministickyKA.vratSymbolyAut();
        this.eZaciatocnyStav = nedeterministickyKA.vratZacStavNKA();
        pomocnaPrechodova=nedeterministickyKA.vratTabulkuNKA();
        pomocnaTabulka= pomocnaPrechodova.vratPrechodovuTabulku();

        for(Map.Entry<String,HashMap<String,HashSet<String>>> m1 : pomocnaTabulka.entrySet()){
            for(Map.Entry<String,HashSet<String>> m2 : m1.getValue().entrySet()){
                if(m1.getKey().equals(eZaciatocnyStav.toString().replace("[","")
                        .replace("]",""))) {
                    ePrechodovaDKA.pridajRiadok(m1.getKey(), m2.getKey(), m2.getValue().toArray(new String[0]));
                    eStavy.add(m1.getKey());
                }
            }
        }

        najdiNovyStav(pomocnaTabulka);
         //System.out.println("DKA JE");
         //ePrechodovaDKAFinal.vypisTabulku();
    }
    public void najdiNovyStav(HashMap<String, HashMap<String,HashSet<String>>> tabulka){
        noveStavy= new ArrayList<String>();
        for(Map.Entry<String,HashMap<String,HashSet<String>>> m1 : tabulka.entrySet()){
            for(Map.Entry<String,HashSet<String>> m2 : m1.getValue().entrySet()){
                if(!eStavy.contains(m2.getValue().toString().replace("[","").replace("]",""))){
                    eStavy.add(m2.getValue().toString().replace("[","")
                            .replace("]","")
                            .replace(", ",""));
                    if(!noveStavy.contains(m2.getValue().toString())) {
                        noveStavy.add(m2.getValue().toString());

                    }
                }
            }
        }
        System.out.println("eStavy: "+eStavy+" noveStavy:"+noveStavy);
        if(!noveStavy.isEmpty()){
            for(int i=0;i< noveStavy.size();i++) {
                k=i;
                String s = noveStavy.get(i);
                pridajNovyStav(tabulka, s);
            }
        }

    }
    public void pridajNovyStav(HashMap<String, HashMap<String,HashSet<String>>> tabulka, String ns){
        riadok = new Tabulka();
        for(Map.Entry<String,HashMap<String,HashSet<String>>> m1 : tabulka.entrySet()){
                for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                    if(ns.contains(m1.getKey())){
                        riadok.pridajRiadokPreZS(ns, m2.getKey(),m2.getValue().toString().replace("[","")
                                .replace("]",""));
                        riadok.vypisTabulku();
                    }

                }

        }
        tab1 = ePrechodovaDKA.vratPrechodovuTabulku();
        riadok1 = riadok.vratPrechodovuTabulku();
        vyslednaTab.putAll(tab1);
        vyslednaTab.putAll(riadok1);
        kopiaVyslednaTab.putAll(vyslednaTab);
    if(k == (noveStavy.size()-1)) {
        upravTabulku(kopiaVyslednaTab);
    }

    }
    public void upravTabulku(HashMap<String, HashMap<String,HashSet<String>>> tabulka) {
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

        vyslednaTab = ePrechodovaDKAFinal.vratPrechodovuTabulku();
        pomocnaTabulka.putAll(tabulka);
        System.out.println("DKA NOVA");
        for (Map.Entry<String, HashMap<String, HashSet<String>>> s : vyslednaTab.entrySet()) {
            System.out.println(s.getKey() + " : " + s.getValue().toString());
        }
        //najdiNovyStav(pomocnaTabulka);

    }
}
 */