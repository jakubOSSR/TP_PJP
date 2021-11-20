package com.automaty;

import java.util.*;

public class NedeterministickyKonecnyAutomat {

    private HashSet<String> stavyNKA;
    private HashSet<String> symboly;
    private String zaciatocnyStavNKA;
    private String akceptujuciStavNKA;
    private Transformacia prechodovaTabulkaNKA;
    private String eps = "epsilon";
   //pomocne
    private boolean jeNKA;
    private boolean nachadzajuSaStavy;
    private boolean nachadzajuSaSymboly;
    private boolean jeAkceptujuciStav;
    private boolean jeZaciatocnStav;





    public NedeterministickyKonecnyAutomat(HashSet<String> stavyNKA, HashSet<String> symboly,
                                           String zaciatocnyStavNKA, String akceptujuciStavNKA,
                                           Transformacia prechodovaTabulkaNKA) throws Exception {
        this.stavyNKA = stavyNKA;
        this.symboly = symboly;
        this.zaciatocnyStavNKA = zaciatocnyStavNKA;
        this.akceptujuciStavNKA = akceptujuciStavNKA;
        this.prechodovaTabulkaNKA = prechodovaTabulkaNKA;

        //overenie NKA
        if((symboly.contains(eps)) || (prechodovaTabulkaNKA.overNKA() > 1)){
            jeNKA = true;
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
                nachadzajuSaSymboly=false;
                break;
            }
        }
        //overenie zaciatocneho stavu
        if(prechodovaTabulkaNKA.overZaciatocnyStav().contains(zaciatocnyStavNKA)){
            jeZaciatocnStav = true;
        }

        if(prechodovaTabulkaNKA.overAkceptujuciStav().contains(akceptujuciStavNKA)){
            System.out.println("Dostnem sa?");
            jeAkceptujuciStav = true;
        }



        if(nachadzajuSaStavy==false){
            throw new Exception("Stavy v tabulke sa nenachádzaju v množine stavov!!");
        }
        if(nachadzajuSaSymboly==false){
            throw new Exception("Symboly v tabulke sa nenachádzaju v množine symbolov!!");
        }
        if(jeNKA==false){
            throw new Exception("Zadaný automat nie je NKA automat!!!!");
        }
        if(jeZaciatocnStav==false){
            throw new Exception("V tabulke, na pravej strane, sa nenachadza ani jeden krat ziaciatocny stav!!!");
        }
        if(jeAkceptujuciStav==false){
            throw new Exception("V tabulke, sa nenachadza akceptujúci stav-automat sa zasekne!!");
        }


    }

    public HashSet<String> vratStavyNKA(){
        return stavyNKA;
    }
    public HashSet<String> vratSymbolyAut(){
        return symboly;
    }
    public String vratZacStavNKA(){
        return zaciatocnyStavNKA;
    }
    public String vratAkcStavNKA(){
        return akceptujuciStavNKA;
    }
    public Transformacia vratTabulkuNKA(){
        return prechodovaTabulkaNKA;
    }
}
