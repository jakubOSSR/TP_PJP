package com.gramatiky;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class RegularnaGramatika extends Gramatika {

    public RegularnaGramatika(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception {
        super(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        for (Pravidlo p: pravidla){
            if (p.getPravaStrana().size() > 2){
                throw new Exception("Prava strana obsahuje viac ako dva symboly. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
            }
            if (p.getPravaStrana().size() == 1){
                if (terminaly.contains(p.getPravaStrana().get(0))){

                }
                else{
                    throw new Exception("Ak je na pravej strane jeden symbol, musi byt terminal. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
                }
            }
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
