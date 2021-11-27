package com.gramatiky;

import com.automaty.DeterministickyKonecnyAutomat;
import com.automaty.Tabulka;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Iterator;

public class DKAkRegularnejG {
    private int i = 1;
    public DKAkRegularnejG(LinkedHashSet<String> terminaly, LinkedHashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception {
        Tabulka tabulka;
        LinkedHashSet<String> akceptujuciStavDKA= new LinkedHashSet<String>();
        tabulka = new Tabulka();
        for (Pravidlo p: pravidla){
            if (p.getPravaStrana().size() == 1){
                if (terminaly.contains(p.getPravaStrana().get(0))){
                    for(String c: akceptujuciStavDKA ) {
                        akceptujuciStavDKA.add("qf" + i);
                        tabulka.pridajRiadok(p.getLavaStrana().get(0), p.getPravaStrana().get(0), c);
                        i++;
                    }
                }
            }
            if (p.getPravaStrana().size() == 2){
                if (terminaly.contains(p.getPravaStrana().get(0)) & neterminaly.contains(p.getPravaStrana().get(1))){

                    tabulka.pridajRiadok(p.getLavaStrana().get(0), p.getPravaStrana().get(0),p.getPravaStrana().get(1));

                }
            }

        }

    }
}

