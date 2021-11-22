package com.automaty;


import java.util.HashSet;


public class DeterministickyKonecnyAutomat {

    private HashSet<String> stavyDKA;
    private HashSet<String> symboly;
    private String zaciatocnyStavDKA;
    private String akceptujuciStavDKA;
    private Tabulka prechodovaTabulkaDKA;

    private boolean jeDKA = true;
    private boolean nachadzajuSaStavy;
    private boolean nachadzajuSaSymboly;
    private boolean jeAkceptujuciStav;
    private boolean jeZaciatocnStav;

    public DeterministickyKonecnyAutomat(HashSet<String> stavyDKA, HashSet<String> symboly,
                                         String zaciatocnyStavDKA, String akceptujuciStavDKA,
                                         Tabulka prechodovaTabulkaDKA) throws Exception{
        this.stavyDKA=stavyDKA;
        this.symboly=symboly;
        this.zaciatocnyStavDKA=zaciatocnyStavDKA;
        this.akceptujuciStavDKA=akceptujuciStavDKA;
        this.prechodovaTabulkaDKA = prechodovaTabulkaDKA;
        //overenie DKA
        if(symboly.contains("epsilon") || prechodovaTabulkaDKA.overAutomat() > 1){
            jeDKA = false;

        }
        //overenie stavov
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
        //overenie symbolov
        for(int i=0;i < prechodovaTabulkaDKA.overSymbolyVtabulke().length;i++)
        {
            if(symboly.contains(prechodovaTabulkaDKA.overSymbolyVtabulke()[i])){
                nachadzajuSaSymboly = true;
            }
            else{
                nachadzajuSaSymboly=false;
                break;
            }
        }
        //overenie zaciatocneho stavu
        if(prechodovaTabulkaDKA.overZaciatocnyStav().contains(zaciatocnyStavDKA)){
            jeZaciatocnStav = true;
        }
        //overenie akceptujuceho stavu
        if(prechodovaTabulkaDKA.overAkceptujuciStav().contains(akceptujuciStavDKA)){
            jeAkceptujuciStav = true;
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
    }
    public HashSet<String> vratStavyDKA(){
        return stavyDKA;
    }
    public HashSet<String> vratSymbolyAut(){
        return symboly;
    }
    public String vratZacStavDKA(){
        return zaciatocnyStavDKA;
    }
    public String vratAkcStavDKA(){
        return akceptujuciStavDKA;
    }
    public Tabulka vratTabulkuDKA(){return prechodovaTabulkaDKA;}


}
