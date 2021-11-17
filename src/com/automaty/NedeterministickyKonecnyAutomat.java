package com.automaty;

import java.util.HashSet;

public class NedeterministickyKonecnyAutomat {

    private HashSet<String> stavy;
    private HashSet<String> symboly;
    private String zaciatocnyStav;
    private HashSet<String> akceptujuceStavy;



    public NedeterministickyKonecnyAutomat(HashSet<String> stavy, HashSet<String> symboly,
                                           String zaciatocnyStav, HashSet<String> akceptujuceStavy){
        this.stavy = stavy;
        this.symboly = symboly;
        this.zaciatocnyStav = zaciatocnyStav;
        this.akceptujuceStavy = akceptujuceStavy;

        System.out.println("________NFA___________");
        System.out.println("Stavy sú"+stavy);
        System.out.println("Symboly sú:"+symboly);
        System.out.println("Zaciatocny stav je:"+zaciatocnyStav);
        System.out.println("Akceptujuce stavy su:"+akceptujuceStavy);



    }



}
