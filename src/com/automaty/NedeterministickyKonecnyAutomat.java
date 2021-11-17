package com.automaty;

import java.util.HashSet;
import java.util.List;

public class NedeterministickyKonecnyAutomat {

    private List<String> stavy;
    private List<String> symboly;
    private String zaciatocnyStav;
    private List<String> akceptujuceStavy;



    public NedeterministickyKonecnyAutomat(List<String> stavy, List<String> symboly,
                                           String zaciatocnyStav, List<String> akceptujuceStavy){
        this.stavy = stavy;
        this.symboly = symboly;
        this.zaciatocnyStav = zaciatocnyStav;
        this.akceptujuceStavy = akceptujuceStavy;
        



    }



}
