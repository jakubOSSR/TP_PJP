package com.automaty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class DeterministickyKonecnyAutomat {

    private HashSet<String> stavyDKA;
    private HashSet<String> symboly;
    private String zaciatocnyStavDKA;
    private HashSet<String> akceptujuceStavyDKA;
    Map<String, HashMap<String,HashSet<String>>> prechodovaTabulkaDKA;

    public DeterministickyKonecnyAutomat(HashSet<String> stavyDKA, HashSet<String> symboly, String zaciatocnyStavDKA,
                                         HashSet<String> akceptujuceStavyDKA, HashSet<String> riadkyTabulkyDKA,
                                         Map<String, HashMap<String,HashSet<String>>> prechodovaTabulkaDKA){
        this.stavyDKA=stavyDKA;
        this.symboly=symboly;
        this.zaciatocnyStavDKA=zaciatocnyStavDKA;
        this.akceptujuceStavyDKA=akceptujuceStavyDKA;
        this.prechodovaTabulkaDKA = prechodovaTabulkaDKA;
    }

}
