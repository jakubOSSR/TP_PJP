package com.gramatiky;

import java.util.HashSet;
import java.util.LinkedHashSet;

//trieda BezkontextovaGramatika dedí po triede Gramatika
public class BezkontextovaGramatika extends Gramatika {

    //vytvorenie konštruktora BezkontextovaGramatika s parametrami terminaly, neterminaly, zaciatocnySymbol a pravidla
    public BezkontextovaGramatika(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception {
        //voláme konštruktora predka (Gramatika) s parametrami terminaly, neterminaly, zaciatocnySymbol a pravidla
        super(terminaly, neterminaly, zaciatocnySymbol, pravidla);
        //tu sa kontroluje či pravá strana pravidla spĺňa podmienky pre bezkontextovú gramatiku
        //cyklus prechádzajúci každé pravidlo
        for (Pravidlo p: pravidla){
            //cyklus prechádzajúci každý symbol pravej strany prsvidla
            for(int i = 0; i < p.getPravaStrana().size(); i++){
                //kontrola či sa symbol nachádza v množine terminálov, neterminálov alebo je to epsilon
                if(terminaly.contains(p.getPravaStrana().get(i)) || neterminaly.contains(p.getPravaStrana().get(i)) || p.getPravaStrana().get(i) == "epsilon"){

                }
                else{
                    throw new Exception("Symbol '" + p.getPravaStrana().get(i) + "' sa nenachadza medzi terminalmi alebo neterminalmi. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
                }
            }
        }
    }
}
