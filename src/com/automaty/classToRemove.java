package com.automaty;
import java.util.*;


public class classToRemove {
    public static void main (String [] args) throws Exception {
        HashSet<String> stavyNKA = new HashSet<>();
        String akceptujuciStavNKA = null;
        HashSet<String> symbolyNKA = new HashSet<>();
        String zaciatocnyStavNKA = null;
        Transformacia prechodovaNKA;
        //naplnenie Stavov
        stavyNKA.add("q0");
        stavyNKA.add("q1");
        stavyNKA.add("q2");
        //naplnenie Symbolov
        symbolyNKA.add("0");
        symbolyNKA.add("1");
        //naplnenie Akceptujucich
       akceptujuciStavNKA = "q2";
        //najplnenie Zaciatocnych
        zaciatocnyStavNKA = "q0";
        //naplnenie prechodovej tabulky
        prechodovaNKA = new Transformacia(symbolyNKA,stavyNKA);
        prechodovaNKA.pridajRiadok("q1","0","q0","q2");
        prechodovaNKA.pridajRiadok("q0","1","q1");
        new NedeterministickyKonecnyAutomat(stavyNKA,symbolyNKA,zaciatocnyStavNKA,akceptujuciStavNKA,prechodovaNKA);











    }
}