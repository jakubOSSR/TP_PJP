package com.gramatiky;

import com.automaty.DeterministickyKonecnyAutomat;
import com.automaty.Tabulka;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Iterator;

public class DKAkRegularnejG {

    private HashSet<String> symboly ;
    private HashSet<String> stavyDKA;
    private HashSet<String> zaciatocnyStavDKA;
    private HashSet<String> akceptujuciStavDKA ;

    public DKAkRegularnejG(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception {
        this.stavyDKA = neterminaly;
        this.symboly = terminaly;
        akceptujuciStavDKA = new HashSet<>();
        Tabulka tabulka;

        tabulka = new Tabulka();
        int i=1;
        for (Pravidlo p: pravidla){
            if (p.getPravaStrana().size() == 1){
                if (terminaly.contains(p.getPravaStrana().get(0))){
                    for(String c: akceptujuciStavDKA ) {
                        akceptujuciStavDKA.add("qf" + i);
                        if(c.contains("qf"+1)){
                        tabulka.pridajRiadok(p.getLavaStrana().get(0), p.getPravaStrana().get(0), c);
                        }

                    }
                    i++;
                }
            }
            if (p.getPravaStrana().size() == 2){
                if (terminaly.contains(p.getPravaStrana().get(0)) & neterminaly.contains(p.getPravaStrana().get(1))){

                    tabulka.pridajRiadok(p.getLavaStrana().get(0), p.getPravaStrana().get(0),p.getPravaStrana().get(1));

                }
            }
        }
        zaciatocnyStavDKA.add(zaciatocnySymbol);
        System.out.println(stavyDKA);
        System.out.println(symboly);
        System.out.println(zaciatocnyStavDKA);
        System.out.println(akceptujuciStavDKA);
    }
    public HashSet<String>vratStavyDKA(){return stavyDKA;}
    public HashSet<String>vratSymbolyDKA(){return symboly;}
    public HashSet<String>vratZacStavDKA(){return zaciatocnyStavDKA;}
    public HashSet<String>vratAkcStavDKA(){return akceptujuciStavDKA;}
    public Tabulka vratTabulku(){return vratTabulku();}

}

