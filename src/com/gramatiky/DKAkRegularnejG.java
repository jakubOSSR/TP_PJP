package com.gramatiky;

import com.automaty.DeterministickyKonecnyAutomat;
import com.automaty.Tabulka;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Iterator;

public class DKAkRegularnejG {

    private HashSet<String> symboly;
    private HashSet<String> stavyDKA;

    public DKAkRegularnejG(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception {
        this.stavyDKA = neterminaly;
        this.symboly = terminaly;
        HashSet<String> akceptujuciStavDKA = new HashSet<>();
        Tabulka tabulka;
        tabulka = new Tabulka();
        HashSet<String>zaciatocnyStavDKA = new HashSet<>();
        zaciatocnyStavDKA.add(zaciatocnySymbol);
        int i = 1;
        for (Pravidlo p : pravidla) {
            if (p.getPravaStrana().size() == 1) {
                if (terminaly.contains(p.getPravaStrana().get(0))) {
                    akceptujuciStavDKA.add("qf" + i);
                    for (String c : akceptujuciStavDKA) {

                        if (c.contains("qf" + i)) {
                            tabulka.pridajRiadok(p.getLavaStrana().get(0), p.getPravaStrana().get(0), c);
                        }

                    }
                    i++;
                }
            }
            if (p.getPravaStrana().size() == 2) {
                if (terminaly.contains(p.getPravaStrana().get(0)) & neterminaly.contains(p.getPravaStrana().get(1))) {

                    tabulka.pridajRiadok(p.getLavaStrana().get(0), p.getPravaStrana().get(0), p.getPravaStrana().get(1));

                }
            }

        }
        System.out.println(i);
        System.out.println(stavyDKA);
        System.out.println(symboly);
        System.out.println(zaciatocnyStavDKA);

        tabulka.vypisTabulku();
    }

}


