package com.automaty;

import java.util.*;

public class NedeterministickyKonecnyAutomat {

    private HashSet<String> stavyNKA;
    private HashSet<String> symboly;
    private String zaciatocnyStavNKA;
    private HashSet<String> akceptujuceStavyNKA;
    Map<String, HashMap<String,HashSet<String>>> prechodovaTabulkaNKA;

    String eps = "epsilon";
    boolean withEps = false;
    boolean sameSymbol = false;


    public NedeterministickyKonecnyAutomat(HashSet<String> stavyNKA, HashSet<String> symboly,
                                           String zaciatocnyStavNKA, HashSet<String> akceptujuceStavyNKA,
                                           Map<String, HashMap<String,HashSet<String>>> prechodovaTabulkaNKA)
                                           throws Exception{
        this.stavyNKA = stavyNKA;
        this.symboly = symboly;
        this.zaciatocnyStavNKA = zaciatocnyStavNKA;
        this.akceptujuceStavyNKA = akceptujuceStavyNKA;
        this.prechodovaTabulkaNKA = prechodovaTabulkaNKA;


    }

}
