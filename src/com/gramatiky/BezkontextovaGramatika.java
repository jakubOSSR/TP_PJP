package com.gramatiky;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class BezkontextovaGramatika extends Gramatika {

    public BezkontextovaGramatika(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception {
        super(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        for (Pravidlo p: pravidla){
            for(int i = 0; i < p.getPravaStrana().size(); i++){
                if(terminaly.contains(p.getPravaStrana().get(i)) || neterminaly.contains(p.getPravaStrana().get(i)) || p.getPravaStrana().get(i) == "epsilon"){

                }
                else{
                    throw new Exception("Symbol '" + p.getPravaStrana().get(i) + "' sa nenachadza medzi terminalmi alebo neterminalmi. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
                }
            }
        }
    }
}
