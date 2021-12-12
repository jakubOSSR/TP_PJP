package com.gramatiky;

import com.automaty.Tabulka;
import com.sun.jdi.event.ExceptionEvent;
import junit.framework.JUnit4TestAdapter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class DKAkRegularnejGTest {
    HashSet<String> terminaly = new HashSet<String>(Arrays.asList("e","l","s","n","d"));
    HashSet<String> neterminaly = new HashSet<String>(Arrays.asList("S","A","B","C","D"));

        String zaciatocnySymbol ="S";

    Pravidlo pravidlo1 = new Pravidlo(new ArrayList<String>(Arrays.asList("S")),new ArrayList<String>(Arrays.asList("e", "A")));
    Pravidlo pravidlo2 = new Pravidlo(new ArrayList<String>(Arrays.asList("A")),new ArrayList<String>(Arrays.asList("l", "B")));
    Pravidlo pravidlo3 = new Pravidlo(new ArrayList<String>(Arrays.asList("A")),new ArrayList<String>(Arrays.asList("n", "C")));
    Pravidlo pravidlo4 = new Pravidlo(new ArrayList<String>(Arrays.asList("B")),new ArrayList<String>(Arrays.asList("s", "D")));
    Pravidlo pravidlo5 = new Pravidlo(new ArrayList<String>(Arrays.asList("C")),new ArrayList<String>(Arrays.asList("d")));
    Pravidlo pravidlo6 = new Pravidlo(new ArrayList<String>(Arrays.asList("D")),new ArrayList<String>(Arrays.asList("e")));

    LinkedHashSet<Pravidlo> pravidla = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6));
    HashSet<String> stavyT = new HashSet<String>();
    HashSet<String> symbolyT = new HashSet<String>();
    String zaciatocnyStavT;
    HashSet<String> akceptujuceStavyT = new HashSet<String>();
    Tabulka tabulkaT = new Tabulka();

    @Test
    void DKAkRGTest(){
        try {
            RegularnaGramatika com = new RegularnaGramatika(terminaly,neterminaly,zaciatocnySymbol,pravidla);
            DKAkRegularnejG pom = new DKAkRegularnejG(terminaly,neterminaly,zaciatocnySymbol,pravidla);
            stavyT.add("S");
            stavyT.add("A");
            stavyT.add("B");
            stavyT.add("C");
            stavyT.add("D");
            symbolyT.add("e");
            symbolyT.add("l");
            symbolyT.add("s");
            symbolyT.add("n");
            symbolyT.add("d");
            symbolyT.add("e");
            zaciatocnyStavT = "S";
            akceptujuceStavyT.add("qf1");
            akceptujuceStavyT.add("qf2");
            tabulkaT.pridajRiadok("S","e","A");
            tabulkaT.pridajRiadok("A","l","B");
            tabulkaT.pridajRiadok("A","n","C");
            tabulkaT.pridajRiadok("B","s","D");
            tabulkaT.pridajRiadok("C","d","qf1");
            tabulkaT.pridajRiadok("D","e","gf2");
            assertEquals(stavyT, pom.vratStavyDKAkRG());
            assertEquals(symbolyT, pom.vratSymbolyDKAkRG());
            assertEquals(zaciatocnyStavT, pom.vratZaciatocnySymbolDKAkRG());
            assertEquals(akceptujuceStavyT, pom.vratAkceptujuceStavyDKAkRG());
            assertEquals(tabulkaT, pom.tabulkaDKAkRG());
        }
        catch (Exception e){
            }
    }
}
