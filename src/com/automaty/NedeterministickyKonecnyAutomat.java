package com.automaty;

import java.util.*;

public class NedeterministickyKonecnyAutomat {
    private HashSet<String> stavyNKA;
    private HashSet<String> symboly;
    private HashSet<String> zaciatocnyStavNKA;
    private HashSet<String> akceptujuciStavNKA;
    private Tabulka prechodovaTabulkaNKA;
   //pomocne
    private boolean jeNKA;
    private boolean nachadzajuSaStavy;
    private boolean nachadzajuSaSymboly;
    private boolean jeAkceptujuciStav;
    private boolean jeZaciatocnStav;





    public NedeterministickyKonecnyAutomat(HashSet<String> stavyNKA, HashSet<String> symboly,
                                           HashSet<String> zaciatocnyStavNKA, HashSet<String> akceptujuciStavNKA,
                                           Tabulka prechodovaTabulkaNKA) throws Exception {
        this.stavyNKA = stavyNKA;
        this.symboly = symboly;
        this.zaciatocnyStavNKA = zaciatocnyStavNKA;
        this.akceptujuciStavNKA = akceptujuciStavNKA;
        this.prechodovaTabulkaNKA = prechodovaTabulkaNKA;
        prechodovaTabulkaNKA.vypisTabulku();
        //overenie NKA
        if((prechodovaTabulkaNKA.overAutomat() > 1)){
            jeNKA = true;
        }
        else{
            for(int i=0;i<prechodovaTabulkaNKA.overSymbolyVtabulke().length;i++){
                if(prechodovaTabulkaNKA.overSymbolyVtabulke()[i].equals("epsilon")){
                    jeNKA = true;
                    break;
                }
            }
        }

        //overovania stavov
        for(int i=0;i < prechodovaTabulkaNKA.overStavyVtabulke().length;i++)
        {
            if(stavyNKA.contains(prechodovaTabulkaNKA.overStavyVtabulke()[i])){
                nachadzajuSaStavy = true;
            }
            else{
                nachadzajuSaStavy=false;
                break;
            }
        }
        //overovanie symbolov
        for(int i=0;i < prechodovaTabulkaNKA.overSymbolyVtabulke().length;i++)
        {
            if(symboly.contains(prechodovaTabulkaNKA.overSymbolyVtabulke()[i])){
                nachadzajuSaSymboly = true;
            }
            else{
                if(prechodovaTabulkaNKA.overSymbolyVtabulke()[i] == "epsilon"){
                    nachadzajuSaSymboly = true;
                }
                else {
                    nachadzajuSaSymboly = false;
                    break;
                }
            }
        }
        //overenie zaciatocneho stavu
        for(int i=0;i < prechodovaTabulkaNKA.overZaciatocnyStav().length;i++)
        {
            if(zaciatocnyStavNKA.contains(prechodovaTabulkaNKA.overZaciatocnyStav()[i])){
                jeZaciatocnStav = true;
                break;
            }
            else{
                jeZaciatocnStav=false;
            }
        }
        //overenie akceptujuceho stavu
        for(int i=0;i < prechodovaTabulkaNKA.overAkceptujuciStav().length;i++)
        {
            if(akceptujuciStavNKA.contains(prechodovaTabulkaNKA.overAkceptujuciStav()[i])){
                jeAkceptujuciStav = true;
                break;
            }
            else{
                jeAkceptujuciStav=false;
            }
        }


        if(jeNKA==false){
            throw new Exception("Zadaný automat nie je NKA automat!!!!");
        }
        if(nachadzajuSaStavy==false){
            throw new Exception("Stavy v tabulke NKA automatu sa nenachádzaju v množine stavov!!");
        }
        if(nachadzajuSaSymboly==false){
            throw new Exception("Symboly v tabulke NKA automatu sa nenachádzaju v množine symbolov!!");
        }
        if(jeZaciatocnStav==false){
            throw new Exception("V tabulke NKA automatu, na lavej strane, sa nenachadza ani jeden krat ziaciatocny stav!!!");
        }
        if(jeAkceptujuciStav==false){
            throw new Exception("V tabulke, NKA automatu, sa nenachadza akceptujúci stav-automat nikdy nič neakceptuje!");
        }


    }

    public HashSet<String> vratStavyNKA(){
        return stavyNKA;
    }
    public HashSet<String> vratSymbolyAut(){
        return symboly;
    }
    public HashSet<String> vratZacStavNKA(){
        return zaciatocnyStavNKA;
    }
    public HashSet<String> vratAkcStavNKA(){
        return akceptujuciStavNKA;
    }
    public Tabulka vratTabulkuNKA(){return prechodovaTabulkaNKA;}
}
