package com.automaty;

import java.util.*;


public class Tabulka<V,K> {
    private HashMap<String, HashMap<String,HashSet<String>>> prechodovaTabulka = new HashMap<String, HashMap<String,HashSet<String>>>();
    private HashMap<String,HashSet<String>> pravidla;
    private HashSet<String> nasledujuciStav;
    //pomocne
    private List<Integer> pom = new ArrayList<Integer>();
    private String [] pomocna;
    private HashSet<String> eStavyPomocna = new HashSet<String>();
    private HashSet<String> overStavy = new HashSet<String>();
    private HashSet<String> overSym = new HashSet<String>();
    private HashSet<String> kontrolaZaciatocnehoStavu = new HashSet<String>();
    private HashSet<String> kontrolaAkceptujucehoStavu = new HashSet<String>();

    public Tabulka(){

    }

    public void pridajRiadok(String stav, String symbol, String... stav123){
        nasledujuciStav = new HashSet<String>();
        pomocna = new String[stav123.length];
        pomocna = stav123;
        eStavyPomocna.add(Arrays.toString(pomocna).replace(", ",""));
        for(int i=0;i< pomocna.length;i++){
            nasledujuciStav.add(pomocna[i]);
            overStavy.add(pomocna[i]);
            kontrolaAkceptujucehoStavu.add(pomocna[i]);
        }
        pravidla = new HashMap<String,HashSet<String>>();
        pravidla.put(symbol,nasledujuciStav);

        pom.add(stav123.length);
        overStavy.add(stav);
        overSym.add(symbol);
        kontrolaZaciatocnehoStavu.add(stav);

        if(prechodovaTabulka.containsKey(stav)) {
            prechodovaTabulka.get(stav).put(symbol,nasledujuciStav);
        }
        else{
            prechodovaTabulka.put(stav, pravidla);
        }
    }

    public String[] overStavyVtabulke(){
        return overStavy.toArray(new String[overStavy.size()]);

    }

    public void vypisTabulku(){
        for (Map.Entry<String,HashMap<String,HashSet<String>>> s : prechodovaTabulka.entrySet()) {
            System.out.println(s.getKey()+" : "+s.getValue());
        }
    }

    public int overAutomat(){
        return Collections.max(pom);
    }
    public String[] vratSymbolyVTabulke(){
        return overSym.toArray(new String[overSym.size()]);
    }
    public String[] vratZaciatocnyStav(){
        return kontrolaZaciatocnehoStavu.toArray(new String[kontrolaZaciatocnehoStavu.size()]);
    }
    public String[] vratAkceptujuceStavy(){
        return kontrolaAkceptujucehoStavu.toArray(new String[kontrolaAkceptujucehoStavu.size()]);
    }
    public HashSet<String> eStavyDKA(){
        return eStavyPomocna;
    }





}
