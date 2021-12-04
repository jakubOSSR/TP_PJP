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
    private boolean jeEpsilon = false;
    private int krok =0;


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
        System.out.println("Uzaver STAVY");
        for (Map.Entry<String, HashSet<String>> m1 : uzaverStavy.entrySet()) {
            System.out.println(m1.getKey()+"    "+m1.getValue());
        }
        najdiNovyStav(ePomocnaPrechodova);
        upravTabulku(vyslednaTab);
        System.out.print("Prechodova tabulka ekv. DKA\n");
        ePrechodovaDKAFinal.vypisTabulku();
        System.out.println("STAVY SU:" + eStavy);

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
                    if (novyStav.contains(m1.getKey())) {
                        if(pravidlo.containsKey(m2.getKey())){
                            naslStavy=new HashSet<String>();
                            naslStavy.addAll(pravidlo.get(m2.getKey()));
                            naslStavy.addAll(m2.getValue());
                            pravidlo.put(m2.getKey(),naslStavy);
                        }
                        else{
                            pravidlo.putAll(m1.getValue());
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
                    uzaverMnozina.add(m1.getKey());
                    uzaverMnozina.add(m2.getValue().toString());
                    uzaverStavy.put(m1.getKey(),uzaverMnozina);

                }
            }
        }

    }

}

   /*
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
    private HashMap<String, HashMap<String, HashSet<String>>> ePomocnaPrechodova = new HashMap<String, HashMap<String, HashSet<String>>>();
    private Tabulka pomocnaPrechodova;
    private Tabulka riadok;
    private HashSet<String> novyStav;
    private HashMap<String,HashSet<String>> uzaverStavy = new HashMap<String,HashSet<String>>();
    private HashSet<String> uzaverMnozina;
    private boolean jeEpsilon = false;
    private int krok =0;


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
        System.out.println("Uzaver STAVY");
        for (Map.Entry<String, HashSet<String>> m1 : uzaverStavy.entrySet()) {
            System.out.println(m1.getKey()+"    "+m1.getValue());
        }
        najdiNovyStav(ePomocnaPrechodova);
        //upravTabulku(vyslednaTab);
       // System.out.print("Prechodova tabulka ekv. DKA\n");
        //ePrechodovaDKAFinal.vypisTabulku();
        //System.out.println("STAVY SU:" + eStavy);

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
            for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulkaNKA.entrySet()) {
                for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                    if (novyStav.contains(m1.getKey())) {
                        riadok.pridajRiadokPreZS(novyStav.toString(), m2.getKey(), m2.getValue());
                        riadok.vypisTabulku();

                    }
                }
            }
            eStavy.add(novyStav.toString());
            riadok1 = riadok.vratPrechodovuTabulku();
            vyslednaTab.putAll(riadok1);
            System.out.println("PO KROKOCH DKA");
            for (Map.Entry<String, HashMap<String,HashSet<String>>> m1 : vyslednaTab.entrySet()) {
                System.out.println(m1.getKey()+"    "+m1.getValue());
            }
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
                    uzaverMnozina.add(m1.getKey());
                    uzaverMnozina.add(m2.getValue().toString());
                    uzaverStavy.put(m1.getKey(),uzaverMnozina);

                }
            }
        }

    }

}

 */