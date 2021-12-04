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
        stavyNKA.add("q3");
        stavyNKA.add("qf");
        //naplnenie Symbolov NKA
        symbolyNKA.add("0");
        symbolyNKA.add("1");
        //naplnenie Akceptujucich NKA
       akceptujuciStavNKA.add("qf");
        //najplnenie Zaciatocnych NKA
       zaciatocnyStavNKA.add("q0");
        //naplnenie prechodovej tabulky NKA
        prechodovaNKA = new Tabulka();
        /*
        //PRIKLAD 1
        prechodovaNKA.pridajRiadok("q0","0","q0");
        prechodovaNKA.pridajRiadok("q0","1","q1");
        prechodovaNKA.pridajRiadok("q1","0","q1","q2");
        prechodovaNKA.pridajRiadok("q1","1","q1");
        prechodovaNKA.pridajRiadok("q2","0","q2");
        prechodovaNKA.pridajRiadok("q2","1","q1","q2");
         //PRIKLAD 2
        prechodovaNKA.pridajRiadok("q0","0","q0","q1");
        prechodovaNKA.pridajRiadok("q0","1","q1");
        prechodovaNKA.pridajRiadok("q1","1","q0","q1");
        */
        //PRIKLAD 3

        prechodovaNKA.pridajRiadok("q0","0","q0","q1");
        prechodovaNKA.pridajRiadok("q0","1","q0","q2");
        prechodovaNKA.pridajRiadok("q1","0","q1","q3");
        prechodovaNKA.pridajRiadok("q1","1","q1");
        prechodovaNKA.pridajRiadok("q2","0","q2");
        prechodovaNKA.pridajRiadok("q2","1","q2","qf");
        prechodovaNKA.pridajRiadok("q3","0","q3");
        prechodovaNKA.pridajRiadok("q3","epsilon","qf");
        /*
        //PRIKLAD 4
        prechodovaNKA.pridajRiadok("q0","0","q0");
        prechodovaNKA.pridajRiadok("q0","1","q1","q2");
        prechodovaNKA.pridajRiadok("q1","0","q1","q2");
        prechodovaNKA.pridajRiadok("q1","1","q2");
        prechodovaNKA.pridajRiadok("q2","0","q0","q1");
        prechodovaNKA.pridajRiadok("q2","1","q1");
        */
        //PRIKLAD 5
        /*
        prechodovaNKA.pridajRiadok("q0","a","q1","q2");
        prechodovaNKA.pridajRiadok("q2","a","q1","q2");
        prechodovaNKA.pridajRiadok("q2","b","q2");

         */

        NedeterministickyKonecnyAutomat nka = new NedeterministickyKonecnyAutomat(stavyNKA,symbolyNKA,zaciatocnyStavNKA,akceptujuciStavNKA,prechodovaNKA);


        //Naplnenie stavov DKA
        stavyDKA.add("q0");
        stavyDKA.add("q1");
        stavyDKA.add("q3");
        //Naplnenie symbolov DKA
        symbolyDKA.add("0");
        symbolyDKA.add("1");
        //Naplnenie akceptujucich stavov
        akceptujuciStavDKA.add("q1");
        //Naplnenie zaciatocnych stavov
        zaciatocnyStavDKA.add("q0");
        //naplnenie prechodovej tabulky DKA
        prechodovaDKA = new Tabulka();
        prechodovaDKA.pridajRiadok("q0","0","q3");
        prechodovaDKA.pridajRiadok("q1","1","q1");

        new DeterministickyKonecnyAutomat(stavyDKA,symbolyDKA,zaciatocnyStavDKA,akceptujuciStavDKA,prechodovaDKA);
        new EkvivalentnyDKA(nka);








    }
}