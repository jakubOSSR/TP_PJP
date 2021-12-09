package com.automaty;


import java.util.HashSet;


public class DeterministickyKonecnyAutomat {
    //deklarácia parametrov pre NKA
    private HashSet<String> stavyDKA;
    private HashSet<String> symboly;
    private HashSet<String> zaciatocnyStavDKA;
    private HashSet<String> akceptujuciStavDKA;
    private Tabulka prechodovaTabulkaDKA;
    //pomocné premenné na určovanie toho, kedy vyhodiť výnimku a kedy nie
    private boolean jeDKA = true;
    private boolean nachadzajuSaStavy;
    private boolean nachadzajuSaSymboly;
    private boolean jeAkceptujuciStav;
    private boolean jeZaciatocnStav;

    //konštruktor
    public DeterministickyKonecnyAutomat(HashSet<String> stavyDKA, HashSet<String> symboly,
                                         HashSet<String> zaciatocnyStavDKA, HashSet<String> akceptujuciStavDKA,
                                         Tabulka prechodovaTabulkaDKA) throws Exception{
        this.stavyDKA=stavyDKA;
        this.symboly=symboly;
        this.zaciatocnyStavDKA=zaciatocnyStavDKA;
        this.akceptujuciStavDKA=akceptujuciStavDKA;
        this.prechodovaTabulkaDKA = prechodovaTabulkaDKA;
        //overenie DKA z pohľadu prechodu na epsilon a prechodu na rovnaký symbol do dvoch stavov
        if((prechodovaTabulkaDKA.overAutomat() > 1)){
            jeDKA = false;
        }
        else{
            for(int i=0;i<prechodovaTabulkaDKA.vratSymbolyVTabulke().length;i++){
                if(prechodovaTabulkaDKA.vratSymbolyVTabulke()[i].equals("epsilon")){
                    jeDKA = false;
                    break;
                }
            }
        }
        //overenie toho, či sa stavy v tabuľke nachádzaju v množine stavov
        for(int i=0;i < prechodovaTabulkaDKA.overStavyVtabulke().length;i++)
        {
            if(stavyDKA.contains(prechodovaTabulkaDKA.overStavyVtabulke()[i])){
                nachadzajuSaStavy = true;
            }
            else{
                nachadzajuSaStavy=false;
                break;
            }
        }
        //overenie toho, či sa symboly v tabulke nachádzaju v množine symbolov
        for(int i=0;i < prechodovaTabulkaDKA.vratSymbolyVTabulke().length;i++)
        {
            if(symboly.contains(prechodovaTabulkaDKA.vratSymbolyVTabulke()[i])){
                nachadzajuSaSymboly = true;
            }
            else{
                nachadzajuSaSymboly=false;
                break;
            }
        }
        //overenie toho, či sa začiatočný stav nachádza v tabulke
        for(int i=0;i < prechodovaTabulkaDKA.vratZaciatocnyStav().length;i++)
        {
            if(zaciatocnyStavDKA.contains(prechodovaTabulkaDKA.vratZaciatocnyStav()[i])){
               jeZaciatocnStav = true;
               break;
            }
            else{
                jeZaciatocnStav=false;
            }
        }
        //overenie či sa akceptujúci stav nachádza v prechodovej tabulke
        for(int i=0;i < prechodovaTabulkaDKA.vratAkceptujuceStavy().length;i++)
        {
            if(akceptujuciStavDKA.contains(prechodovaTabulkaDKA.vratAkceptujuceStavy()[i])){
                jeAkceptujuciStav = true;
                break;
            }
            else{
                jeAkceptujuciStav=false;
            }
        }

        if(jeDKA==false){
            throw new Exception("Zadaný automat nie je DKA automat!!");
        }
        if(nachadzajuSaStavy==false){
            throw new Exception("Stavy v tabulke DKA automatu sa nenachádzaju v množine stavov!!");
        }
        if(nachadzajuSaSymboly==false){
            throw new Exception("Symboly v tabulke DKA automatu sa nenachádzaju v množine symbolov!!");
        }
        if(jeZaciatocnStav==false){
            throw new Exception("V tabulke DKA automatu, na lavej strane, sa nenachadza ani jeden krat ziaciatocny stav!!!");
        }
        if(jeAkceptujuciStav==false){
            throw new Exception("V tabulke, DKA automatu, sa nenachadza akceptujúci stav-automat nikdy nič neakceptuje!");
        }

        //výpis prechodovej tabulky DKA
        System.out.print("________________Prechodova tabulka DKA_________________\n");
        prechodovaTabulkaDKA.vypisTabulku();
        System.out.print("_______________________________________________________\n");

    }

    //metody, ktoré vracaju parametre DKA - množinu stavov, symbolov... ( get metody)
    public HashSet<String> vratStavyDKA(){
        return stavyDKA;
    }
    public HashSet<String> vratSymbolyAut(){
        return symboly;
    }
    public HashSet<String> vratZacStavDKA(){
        return zaciatocnyStavDKA;
    }
    public HashSet<String> vratAkcStavDKA(){
        return akceptujuciStavDKA;
    }
    public Tabulka vratTabulkuDKA(){return prechodovaTabulkaDKA;}


}
