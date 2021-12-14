package com.gramatiky;

import com.automaty.DeterministickyKonecnyAutomat;
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
            stavyT.add("qf1");
            stavyT.add("qf2");
            symbolyT.add("e");
            symbolyT.add("l");
            symbolyT.add("s");
            symbolyT.add("n");
            symbolyT.add("d");
            symbolyT.add("e");
            zaciatocnyStavT = "S";
            akceptujuceStavyT.add("qf1");
            akceptujuceStavyT.add("qf2");

            tabulkaT.pridajRiadok("A","l","B");
            tabulkaT.pridajRiadok("A","n","C");
            tabulkaT.pridajRiadok("B","s","D");
            tabulkaT.pridajRiadok("S","e","A");
            tabulkaT.pridajRiadok("C","d","qf1");
            tabulkaT.pridajRiadok("D","e","gf2");
            assertEquals(stavyT, pom.vratStavyDKAkRG());
            assertEquals(symbolyT, pom.vratSymbolyDKAkRG());
            assertEquals(zaciatocnyStavT, pom.vratZaciatocnyStavDKAkRG());
            assertEquals(akceptujuceStavyT, pom.vratAkceptujuceStavyDKAkRG());
            System.out.println("Očakávaná tabuľka:");
            pom.tabulkaDKAkRG().vypisTabulku();
            System.out.println("Stavy DKAkRG: " + pom.vratStavyDKAkRG());
            System.out.println("Symboly DKAkRG: " + pom.vratSymbolyDKAkRG());
            System.out.println("Zaciatocny stav DKAkRG: " + pom.vratZaciatocnyStavDKAkRG());
            System.out.println("Akcpetujúce stavy DKAkRG: " + pom.vratAkceptujuceStavyDKAkRG());
        }
        catch (Exception e){
            fail(e.getMessage());
            }
    }
    @Test
    void AutomatTest(){
        try {
            DKAkRegularnejG ten = new DKAkRegularnejG(terminaly,neterminaly,zaciatocnySymbol,pravidla);
            HashSet <String> zacstavy = new HashSet<String>();
            zacstavy.add(zaciatocnySymbol);
            DeterministickyKonecnyAutomat dka = new DeterministickyKonecnyAutomat(ten.stavyDKAkRG,ten.symbolyDKAkRG,zacstavy,ten.akceptujuciStavDKAkRG, ten.tabulkaDKAkRG);
        }
        catch(Exception e){
            fail(e.getMessage());
        }
    }
}
