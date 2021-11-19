package com.automaty;
import java.util.*;


public class classToRemove {
    public static void main (String [] args) throws Exception {
        HashSet<String> stavyNKA = new HashSet<>();
        HashSet<String> akceptujuceStavyNKA = new HashSet<>();
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
        akceptujuceStavyNKA.add("q2");
        //najplnenie Zaciatocnych
        zaciatocnyStavNKA = "q0";
        //naplnenie prechodovej tabulky
        prechodovaNKA = new Transformacia(symbolyNKA,stavyNKA);
        prechodovaNKA.pridajRiadok("q0","0","q0","q1");
        prechodovaNKA.pridajRiadok("q0","1","q1");











    }
}