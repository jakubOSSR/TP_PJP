package com.gramatiky;

import java.util.HashSet;
import java.util.LinkedHashSet;

//trieda RegularnaGramatika dedí po triede Gramatika
public class RegularnaGramatika extends Gramatika {

    //vytvorenie konštruktora RegularnaGramatika s parametrami terminaly, neterminaly, zaciatocnySymbol a pravidla
    public RegularnaGramatika(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception {
        //voláme konštruktora predka (Gramatika) s parametrami terminaly, neterminaly, zaciatocnySymbol a pravidla
        super(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //tu sa kontroluje či pravá strana pravidla spĺňa podmienky pre reguláru gramatiku
        //cyklus prechádzajúci každé pravidlo
        for (Pravidlo p: pravidla){
            //kontrola či pravá strana pravidla obsahuje práve dva symboly
            if (p.getPravaStrana().size() > 2){
                throw new Exception("Prava strana obsahuje viac ako dva symboly. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
            }
            //kontrola ak je na pravej strane pravidla práve jeden symbol, ktorý musí byť terminál
            if (p.getPravaStrana().size() == 1){
                if (terminaly.contains(p.getPravaStrana().get(0))){

                }
                else{
                    throw new Exception("Ak je na pravej strane jeden symbol, musi byt terminal. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
                }
            }
            //kontrola ak sú na pravej strane pravidla práve dva symboly, kde prcý musí byť terminál a druhý neterminál
            if (p.getPravaStrana().size() == 2){
                if (terminaly.contains(p.getPravaStrana().get(0)) & neterminaly.contains(p.getPravaStrana().get(1))){

                }
                else{
                    throw new Exception("Ak su na pravej strane dva symboly, prvy musi byt terminal a druhy neterminal. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
                }
            }
        }
    }
}
