package com.gramatiky;

import com.automaty.DeterministickyKonecnyAutomat;
import com.automaty.Tabulka;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Iterator;

public class DKAkRegularnejG {

    public HashSet<String> akceptujuciStavDKAkRG;
    public HashSet<String> stavyDKAkRG;
    public HashSet<String> symbolyDKAkRG;
    public String zaciatocnyStavDKAkRG;
    public Tabulka tabulkaDKAkRG;
    public DKAkRegularnejG(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception {
       HashSet<String> akceptujuciStav = new HashSet<>();
        Tabulka tabulka;
        tabulka = new Tabulka();
        int i = 1;
        for (Pravidlo p : pravidla) {
            if (p.getPravaStrana().size() == 1) {
                if (terminaly.contains(p.getPravaStrana().get(0))) {
                    akceptujuciStav.add("qf" + i);
                    for (String c : akceptujuciStav) {

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
        this.akceptujuciStavDKAkRG=akceptujuciStav;
        this.zaciatocnyStavDKAkRG = zaciatocnySymbol;
        this.tabulkaDKAkRG=tabulka;
        this.stavyDKAkRG=neterminaly;
        this.symbolyDKAkRG=terminaly;
        tabulka.vypisTabulku();
        System.out.println(akceptujuciStavDKAkRG);
        System.out.println(zaciatocnyStavDKAkRG);
    }
    public HashSet<String> vratAkceptujuceStavyDKAkRG(){return akceptujuciStavDKAkRG;}
    public HashSet<String> vratStavyDKAkRG(){return stavyDKAkRG;}
    public HashSet<String> vratSymbolyDKAkRG(){return symbolyDKAkRG;}
    public String vratZaciatocnySymbolDKAkRG(){return zaciatocnyStavDKAkRG;}
    public Tabulka tabulkaDKAkRG(){return tabulkaDKAkRG;}

}


