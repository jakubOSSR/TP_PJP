package com.automaty;

import javax.sound.midi.SysexMessage;
import java.sql.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class EkvivalentnyDKA {
    private HashSet<String> eStavy = new HashSet<String>();
    private HashSet<String> eSymboly;
    private HashSet<String> eAkceptujuceStavy = new HashSet<String>();
    private Tabulka ePrechodovaDKA = new Tabulka();
    private Tabulka ePrechodovaDKAFinal = new Tabulka();
    private HashSet<String> eZaciatocnyStav;
    //pomocne
    private HashMap<String, HashMap<String, HashSet<String>>> tabulkaNKA;
    private HashMap<String, HashMap<String, HashSet<String>>> riadok1;
    private HashMap<String, HashMap<String, HashSet<String>>> vyslednaTab = new HashMap<String, HashMap<String, HashSet<String>>>();
    private HashMap<String, HashMap<String, HashSet<String>>> ePomocnaPrechodova = new HashMap<String, HashMap<String, HashSet<String>>>();
    private Tabulka pomocnaPrechodova;
    private Tabulka riadok;
    private HashSet<String> novyStav;
    private HashMap<String,HashSet<String>> uzaverStavy = new HashMap<String,HashSet<String>>();
    private HashSet<String> uzaverMnozina;
    private HashMap<String,HashSet<String>> pravidlo;
    private HashSet<String> naslStavy;
    private HashSet<String> pomocna = new HashSet<String>();


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
        vyslednaTab.putAll(ePomocnaPrechodova);
        uzaverEps(tabulkaNKA);
        najdiNovyStav(ePomocnaPrechodova);
        upravTabulku(vyslednaTab);
        System.out.print("Prechodova tabulka ekv. DKA\n");
        ePrechodovaDKAFinal.vypisTabulku();
        System.out.println("STAVY SU:" + eStavy.toString().replace(", ",""));
        System.out.println("Zaciatocny STAV: "+eZaciatocnyStav);
        pomocna.addAll(nedeterministickyKA.vratAkcStavNKA());
        najdiAkcStavy(eStavy,pomocna);
        System.out.println("Akceptujuce stavy su: "+ eAkceptujuceStavy.toString().replace(", ",""));


    }
    public void najdiAkcStavy(HashSet<String> s, HashSet<String> aNKA){
        for(String str : s){
            for(String str1 : aNKA){
                if(str.contains(str1)){
                    eAkceptujuceStavy.add(str);
                }
            }
        }
    }
    public void najdiNovyStav(HashMap<String, HashMap<String, HashSet<String>>> tabulka) {
        novyStav = new HashSet<String>();
        for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulka.entrySet()) {
            for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                if (!eStavy.contains(m2.getValue().toString())) {
                    novyStav=new HashSet<String>();
                    novyStav.addAll(m2.getValue());
                }
            }
        }
        if (!novyStav.isEmpty()) {
            riadok = new Tabulka();
            pravidlo= new HashMap<String,HashSet<String>>();
            for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulkaNKA.entrySet()) {
                for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                    if(!m2.getKey().equals("epsilon")) {
                        if (novyStav.contains(m1.getKey())) {
                            if (pravidlo.containsKey(m2.getKey())) {
                                naslStavy = new HashSet<String>();
                                naslStavy.addAll(pravidlo.get(m2.getKey()));
                                naslStavy.addAll(m2.getValue());
                                for(Map.Entry<String,HashSet<String>> uzaver : uzaverStavy.entrySet()){
                                    if(naslStavy.contains(uzaver.getKey())){
                                        naslStavy.addAll(uzaverStavy.get(uzaver.getKey()));
                                    }
                                }
                                pravidlo.put(m2.getKey(), naslStavy);
                            }
                            else
                            {
                                naslStavy=new HashSet<String>();
                                naslStavy.addAll(m2.getValue());
                                pravidlo.put(m2.getKey(),naslStavy);
                            }

                        }
                    }
                }
            }
            riadok.pridajRiadokPreZS(novyStav.toString(),pravidlo);
            eStavy.add(novyStav.toString());
            riadok1 = riadok.vratPrechodovuTabulku();
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

    public void uzaverEps(HashMap<String, HashMap<String, HashSet<String>>> tabulka){
        for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulkaNKA.entrySet()) {
            for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                if(m2.getKey().equals("epsilon")){
                    uzaverMnozina = new HashSet<String>();
                    uzaverMnozina.addAll(m2.getValue());
                    uzaverStavy.put(m1.getKey(),uzaverMnozina);

                }
            }
        }

    }

}
