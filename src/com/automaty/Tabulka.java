package com.automaty;

import java.util.*;


public class Tabulka {
    private HashMap<String, HashMap<String,HashSet<String>>> prechodovaTabulka = new HashMap<String, HashMap<String,HashSet<String>>>();
    private HashMap<String,HashSet<String>> pravidla;
    private HashSet<String> nasledujuciStav;
    //pomocne
    private List<Integer> pom = new ArrayList<Integer>();
    private String [] pomocna;
    private HashSet<String> overStavy = new HashSet<String>();
    private HashSet<String> overSym = new HashSet<String>();
    private HashSet<String> kontrolaZaciatocnehoStavu = new HashSet<String>();
    private HashSet<String> kontrolaAkceptujucehoStavu = new HashSet<String>();
    private HashMap<String,HashSet<String>> pravidla1;
    private HashSet<String> nasledujuciStav1;

    public void pridajRiadok(String stav, String symbol, String... stav123){
        nasledujuciStav = new HashSet<String>();
        pomocna = new String[stav123.length];
        pomocna = stav123;
        for(int i=0;i< pomocna.length;i++){
            nasledujuciStav.add(pomocna[i]);
            overStavy.add(pomocna[i]);
            kontrolaAkceptujucehoStavu.add(pomocna[i]);
        }
        pravidla = new HashMap<String,HashSet<String>>();

        pravidla.put(symbol, nasledujuciStav);

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
    public void pridajRiadokPreZS(String aktStav, String symbol, HashSet<String> naslStav){
        System.out.println("Aktualny stav: "+aktStav+" symbol: "+symbol+" nasledujuci stav: "+naslStav);
        if(prechodovaTabulka.containsKey(aktStav)){
            if(pravidla1.containsKey(symbol)){
                vratNachadzajuciSaStav(symbol,aktStav);
                naslStav.addAll(nasledujuciStav1);
                prechodovaTabulka.get(aktStav).put(symbol,naslStav);
            }
            else{
                prechodovaTabulka.get(aktStav).put(symbol,naslStav);
            }
        }
        else{
            pravidla1 = new HashMap<String,HashSet<String>>();
            pravidla1.put(symbol,naslStav);
            prechodovaTabulka.put(aktStav,pravidla1);
        }
    }
    public void vratNachadzajuciSaStav(String sym,String aktualS){
       nasledujuciStav1 = new HashSet<String>();
       for(Map.Entry<String,HashMap<String,HashSet<String>>> m : prechodovaTabulka.entrySet()){
           if(m.getKey().equals(aktualS)){
               for(Map.Entry<String,HashSet<String>> m1 : m.getValue().entrySet()){
                   if(m1.getKey().equals(sym)){
                       nasledujuciStav1.addAll(m1.getValue());
                   }
               }
           }
       }
    }

    public String[] overStavyVtabulke(){
        return overStavy.toArray(new String[overStavy.size()]);

    }
    public void vypisTabulku(){
        for (Map.Entry<String,HashMap<String,HashSet<String>>> s : prechodovaTabulka.entrySet()) {
            System.out.println(s.getKey()+" : "+s.getValue().toString());
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
    public HashMap<String, HashMap<String, HashSet<String>>> vratPrechodovuTabulku() {
        return prechodovaTabulka;
    }

}
/*
  public void pridajRiadokPreZS(String aktStav, String symbol, String naslStav){
        String s = aktStav.toString().replace("[","").replace("]","");
        nasledujuciStav1=new HashSet<String>();
        nasledujuciStav1.add(naslStav);
        if(prechodovaTabulka.containsKey(s)){
            if(pravidla1.containsKey(symbol)){
                if(obsahujeNaslStav(naslStav,symbol,s) == false){
                   if(!nasledujuciStav1.toString().contains(pomocnaNaslStav.toString().replace("[","").replace("]",""))){
                       nasledujuciStav1.addAll(pomocnaNaslStav);
                   }
                    prechodovaTabulka.get(s).put(symbol,nasledujuciStav1);
                }
            }
            else{
                prechodovaTabulka.get(s).put(symbol,nasledujuciStav1);
            }
        }
        else{
            pravidla1=new HashMap<String,HashSet<String>>();
            pravidla1.put(symbol,nasledujuciStav1);
            prechodovaTabulka.put(s,pravidla1);
        }
    }
 */
