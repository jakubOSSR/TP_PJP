package com.gramatiky;

import com.automaty.Tabulka;
import org.junit.platform.engine.support.descriptor.FileSystemSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Skuska {
    public static void main (String [] args) throws Exception {


        HashSet<String> terminaly = new HashSet<>();
        HashSet<String> neterminaly = new HashSet<>();
        String zaciatocnySymbol;

        terminaly.add("e");
        terminaly.add("l");
        terminaly.add("s");
        terminaly.add("n");
        terminaly.add("d");

        neterminaly.add("S");
        neterminaly.add("A");
        neterminaly.add("B");
        neterminaly.add("C");
        neterminaly.add("D");

        zaciatocnySymbol ="S";

        Pravidlo pravidlo1 = new Pravidlo(new ArrayList<String>(Arrays.asList("S")),new ArrayList<String>(Arrays.asList("e", "A")));
        Pravidlo pravidlo2 = new Pravidlo(new ArrayList<String>(Arrays.asList("A")),new ArrayList<String>(Arrays.asList("l", "B")));
        Pravidlo pravidlo3 = new Pravidlo(new ArrayList<String>(Arrays.asList("A")),new ArrayList<String>(Arrays.asList("n", "C")));
        Pravidlo pravidlo4 = new Pravidlo(new ArrayList<String>(Arrays.asList("B")),new ArrayList<String>(Arrays.asList("s", "D")));
        Pravidlo pravidlo5 = new Pravidlo(new ArrayList<String>(Arrays.asList("C")),new ArrayList<String>(Arrays.asList("d")));
        Pravidlo pravidlo6 = new Pravidlo(new ArrayList<String>(Arrays.asList("D")),new ArrayList<String>(Arrays.asList("e")));

        LinkedHashSet<Pravidlo> pravidla = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6));


        LinkedHashSet<String> akceptujuciStavDKA=new LinkedHashSet<>();
        int i = 0;
        new RegularnaGramatika(terminaly,neterminaly,zaciatocnySymbol,pravidla);
        Tabulka tabulka;
        tabulka = new Tabulka();
        for (Pravidlo p: pravidla){
            if (p.getPravaStrana().size() == 1){
                if (terminaly.contains(p.getPravaStrana().get(0))){
                    akceptujuciStavDKA.add("qf" + i);
                    for(String c: akceptujuciStavDKA ) {

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
        tabulka.vypisTabulku();


    }
}
