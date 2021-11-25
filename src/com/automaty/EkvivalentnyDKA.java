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
        pomocnaTabulka = prechodovaNKA.vratPrechodovuTabulku();
        pomocnePravidlaTabulky = new HashMap<String,HashSet<String>>();
        nasledujuciStav = new HashSet<String>();
        pomocnaSymbol = null;
        pomocnaStav = null;
        nasledujuciStavS = null;
        zdruzenyStavy = new ArrayList<>();
        krok = pomocnaTabulka.size();
        for (Map.Entry<String,HashMap<String,HashSet<String>>> s : pomocnaTabulka.entrySet()) {
            krok--;
            pomocnePravidlaTabulky=s.getValue();
            pomocnaStav = s.getKey();
            for(Map.Entry<String,HashSet<String>> t : pomocnePravidlaTabulky.entrySet()){
                pomocnaSymbol = t.getKey();
                nasledujuciStav = t.getValue();
                if(nasledujuciStav.size()>1){
                   nasledujuciStavS = nasledujuciStav.toString().replace(", ","")
                                                                .replace("[","")
                                                                .replace("]","");
                   if(!zdruzenyStavy.contains(nasledujuciStavS))
                   {
                       zdruzenyStavy.add(nasledujuciStavS);
                   }
                   ePrechodovaDKA.pridajRiadok(pomocnaStav,pomocnaSymbol,nasledujuciStavS);
                }
                else{
                    nasledujuciStavS=nasledujuciStav.toString().replace("[","")
                                                               .replace("]","");

                    ePrechodovaDKA.pridajRiadok(pomocnaStav,pomocnaSymbol,nasledujuciStavS);
                }
            }
            if(krok == 0){
                for (Map.Entry<String,HashMap<String,HashSet<String>>> x : pomocnaTabulka.entrySet()){
                    for(int i = 0; i< zdruzenyStavy.size();i++){
                       if(zdruzenyStavy.get(i).contains(x.getKey())){
                           pomocnePravidlaTabulky=x.getValue();
                           for(Map.Entry<String,HashSet<String>> y : pomocnePravidlaTabulky.entrySet()){
                               pomocnaSymbol = y.getKey();
                               nasledujuciStav = y.getValue();
                               


                           }
                       }
                    }
                }
            }

        }


       // ePrechodovaDKA.vypisTabulku();


    }
}
