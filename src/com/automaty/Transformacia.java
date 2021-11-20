package com.automaty;

import java.util.*;
import java.util.stream.Stream;


public class Transformacia {
    private HashSet<String> symboly;
    private HashSet<String> stavy;
    private HashMap<String, HashMap<String,HashSet<String>>> prechodovaTabulka = new HashMap<String, HashMap<String,HashSet<String>>>();
    private HashMap<String,HashSet<String>> pravidla;
    private HashSet<String> nasledujuciStav;
    //pomocne
    private List<Integer> pom = new ArrayList<Integer>();
    private String [] pomocna;
    private Set<String> overStavy = new HashSet<String>();
    private Set<String> overSym = new HashSet<String>();
    private HashSet<String> kontrolaZaciatocnehoStavu = new HashSet<String>();
    private HashSet<String> kontrolaAkceptujucehoStavu = new HashSet<String>();

    public Transformacia(HashSet<String> symboly, HashSet<String> stavy,
                         HashMap<String, HashMap<String,HashSet<String>>> prechodovaTabulka){
        this.symboly = symboly;
        this.stavy = stavy;
        this.prechodovaTabulka = prechodovaTabulka;
    }
    public Transformacia(HashSet<String> symboly, HashSet<String> stavy){
        this.symboly = symboly;
        this.stavy = stavy;
    }
    public void pridajNasledujuciStav(String symbol,String... stav123){
        nasledujuciStav = new HashSet<String>();
        pomocna = new String[stav123.length];
        pomocna = stav123;
        for(int i=0;i< pomocna.length;i++){
            nasledujuciStav.add(pomocna[i]);
            overStavy.add(pomocna[i]);
            kontrolaAkceptujucehoStavu.add(pomocna[i]);
        }
        prechod(symbol,nasledujuciStav);

    }
    public void prechod(String symbol, HashSet<String> naslStav){
        pravidla = new HashMap<String,HashSet<String>>();
        pravidla.put(symbol,naslStav);
    }
    public void pridajRiadok(String stav, String symbol, String... stav123){
        pridajNasledujuciStav(symbol,stav123);
        overStavy.add(stav);
        overSym.add(symbol);
        kontrolaZaciatocnehoStavu.add(stav);
        pom.add(stav123.length);
        prechodovaTabulka.put(stav,pravidla);
        System.out.print(prechodovaTabulka.toString());
    }
    public int overNKA(){
        return Collections.max(pom);
    }
    public String[] overStavyVtabulke(){
        return overStavy.toArray(new String[overStavy.size()]);

    }
    public String[] overSymbolyVtabulke(){
        return overSym.toArray(new String[overSym.size()]);
    }
    public HashSet<String> overZaciatocnyStav(){
        return kontrolaZaciatocnehoStavu;
    }
    public HashSet<String> overAkceptujuciStav(){
        return kontrolaAkceptujucehoStavu;
    }



}
