package com.automaty;

import java.util.*;


public class Tabulka {
    private HashMap<String, HashMap<String,HashSet<String>>> prechodovaTabulka = new HashMap<String, HashMap<String,HashSet<String>>>();
    private HashMap<String,HashSet<String>> pravidla;
    private HashSet<String> nasledujuciStav;
    private HashMap<String,HashSet<String>> zdruzenyStavPravidlo;
    //pomocne
    private List<Integer> pom = new ArrayList<Integer>();
    private String [] pomocna;
    private HashSet<String> eStavyPomocna = new HashSet<String>();
    private HashSet<String> overStavy = new HashSet<String>();
    private HashSet<String> overSym = new HashSet<String>();
    private HashSet<String> kontrolaZaciatocnehoStavu = new HashSet<String>();
    private HashSet<String> kontrolaAkceptujucehoStavu = new HashSet<String>();


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
    public String vratNaslStavZS(String zdruzenyStav, String symbol){
        for (Map.Entry<String,HashMap<String,HashSet<String>>> s : prechodovaTabulka.entrySet()) {
            if(s.getKey().equals(zdruzenyStav)){
                pravidla = s.getValue();
               for(Map.Entry<String,HashSet<String>> l : pravidla.entrySet()){
                   if(l.getKey().equals(symbol)){
                       return l.getValue().toString().replace(", ","")
                                                     .replace("[","")
                                                     .replace("]","");
                   }
               }
            }
        }
        return null;
    }
    public HashMap<String,HashSet<String>> vratPravidlaStavu(String zdruzenyStav){
        for (Map.Entry<String,HashMap<String,HashSet<String>>> s : prechodovaTabulka.entrySet()) {
            if(s.getKey().equals(zdruzenyStav)){
                zdruzenyStavPravidlo = s.getValue();
            }
        }
        return zdruzenyStavPravidlo;
    }
    public boolean obsahujeZdruzenyStav(String zdruzenyStav){
        for (Map.Entry<String,HashMap<String,HashSet<String>>> s : prechodovaTabulka.entrySet()) {
            if(s.getKey().equals(zdruzenyStav)){
                return true;
            }
        }
        return false;

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

    public HashMap<String, HashMap<String, HashSet<String>>> vratPrechodovuTabulku() {
        return prechodovaTabulka;
    }
}
