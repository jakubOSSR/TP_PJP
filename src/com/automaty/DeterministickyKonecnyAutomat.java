package com.automaty;

import java.util.List;

public class DeterministickyKonecnyAutomat {

    private List<String> stavyDKA;
    private List<String> symboly;
    private String zaciatocnyStavDKA;
    private List<String> akceptujuceStavyDKA;
    private String [] riadkyTabulkyDKA;

    public DeterministickyKonecnyAutomat(List<String> stavyDKA, List<String> symboly, String zaciatocnyStavDKA,
                                         List<String> akceptujuceStavyDKA, String [] riadkyTabulkyDKA){
        this.stavyDKA=stavyDKA;
        this.symboly=symboly;
        this.zaciatocnyStavDKA=zaciatocnyStavDKA;
        this.akceptujuceStavyDKA=akceptujuceStavyDKA;
        this.riadkyTabulkyDKA = riadkyTabulkyDKA;
    }

}
