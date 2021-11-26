package com.gramatiky;

import com.automaty.DeterministickyKonecnyAutomat;
import com.automaty.Tabulka;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Iterator;

public class DKAkRegularnejG {

    public DKAkRegularnejG(LinkedHashSet<String> terminaly, LinkedHashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception {
        Tabulka dkakrg;
        dkakrg = new Tabulka();
        for (Pravidlo p: pravidla){
            if (p.getPravaStrana().size() == 1){
                if (terminaly.contains(p.getPravaStrana().get(0))){


                }
            }
            if (p.getPravaStrana().size() == 2){
                if (terminaly.contains(p.getPravaStrana().get(0)) & neterminaly.contains(p.getPravaStrana().get(1))){

                    dkakrg.pridajRiadok(p.getLavaStrana().get(0), p.getPravaStrana().get(0),p.getPravaStrana().get(1));

                }
            }
        }

    }
}

