package com.automaty;
import java.util.*;


public class classToRemove {
    public static void main (String [] args) throws Exception {
        //Deklaracia parametrov NKA
        HashSet<String> stavyNKA = new HashSet<>();
        HashSet<String> akceptujuciStavNKA= new HashSet<>();
        HashSet<String> symbolyNKA = new HashSet<>();
        HashSet<String> zaciatocnyStavNKA = new HashSet<>();
        Tabulka prechodovaNKA;

        //Deklaracia parametrov DKA
        HashSet<String> stavyDKA = new HashSet<>();
        HashSet<String> akceptujuciStavDKA= new HashSet<>();
        HashSet<String> symbolyDKA = new HashSet<>();
        HashSet<String> zaciatocnyStavDKA = new HashSet<>();
        Tabulka prechodovaDKA;

        //naplnenie Stavov NKA
        stavyNKA.add("q0");
        stavyNKA.add("q1");
        stavyNKA.add("q2");
        //naplnenie Symbolov NKA
        symbolyNKA.add("0");
        symbolyNKA.add("1");
        //naplnenie Akceptujucich NKA
       akceptujuciStavNKA.add("q2");
        //najplnenie Zaciatocnych NKA
       zaciatocnyStavNKA.add("q0");
        //naplnenie prechodovej tabulky NKA
        prechodovaNKA = new Tabulka();
        prechodovaNKA.pridajRiadok("q2","0","q1");
        prechodovaNKA.pridajRiadok("q0","epsilon","q2");
        new NedeterministickyKonecnyAutomat(stavyNKA,symbolyNKA,zaciatocnyStavNKA,akceptujuciStavNKA,prechodovaNKA);



        //Naplnenie stavov DKA
        stavyDKA.add("q0");
        stavyDKA.add("q1");
        stavyDKA.add("q2");
        //Naplnenie symbolov DKA
        symbolyDKA.add("0");
        symbolyDKA.add("1");
        //Naplnenie akceptujucich stavov
        akceptujuciStavDKA.add("q2");
        //Naplnenie zaciatocnych stavov
        zaciatocnyStavDKA.add("q0");
        //naplnenie prechodovej tabulky DKA
        prechodovaDKA = new Tabulka();
        prechodovaDKA.pridajRiadok("q0","0","q2");
        prechodovaDKA.pridajRiadok("q0","1","q1");
        new DeterministickyKonecnyAutomat(stavyDKA,symbolyDKA,zaciatocnyStavDKA,akceptujuciStavDKA,prechodovaDKA);









    }
}