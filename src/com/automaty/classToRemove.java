package com.automaty;
import java.util.ArrayList;
import java.util.List;

public class classToRemove {
    public static void main (String [] args){
        List<String> stavy = new ArrayList<>();
        List<String> akceptujuceStavy = new ArrayList<>();
        List<String> symboly = new ArrayList<>();
        String zaciatocnyStav = null;
        String [][][] prechodovaTabulka;


        stavy.add("q0");
        stavy.add("q1");
        stavy.add("q2");
        symboly.add("0");
        symboly.add("1");
        akceptujuceStavy.add("q2");
        zaciatocnyStav = "q0";

        prechodovaTabulka = new String[stavy.size()][symboly.size()][symboly.size()];
        prechodovaTabulka[0][0][0] ="q0";
        prechodovaTabulka[0][0][1] ="q1";
        prechodovaTabulka[0][1][0] ="q1";
        prechodovaTabulka[1][0][0] ="q2";
        prechodovaTabulka[1][1][0] ="q0";
        prechodovaTabulka[2][0][0] ="q2";
        prechodovaTabulka[2][1][0] ="q1";
        prechodovaTabulka[2][1][1] ="q2";
        new PrechodovaFunkcia(stavy,symboly,prechodovaTabulka);
        new NedeterministickyKonecnyAutomat(stavy,symboly,zaciatocnyStav,akceptujuceStavy);





    }
}