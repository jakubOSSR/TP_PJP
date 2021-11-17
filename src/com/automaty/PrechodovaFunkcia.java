package com.automaty;


import java.util.Arrays;
import java.util.List;


public class PrechodovaFunkcia {

    private List<String> stavy;
    private List<String> symboly;
    private String [][][] prechodovaTabulka;


    public PrechodovaFunkcia(List<String> stavy, List<String> symboly, String [][][] prechodovaTabulka){
        this.stavy=stavy;
        this.symboly=symboly;
        this.prechodovaTabulka = prechodovaTabulka;
        System.out.println("Stavy: "+stavy);
        System.out.println("Symboly: "+symboly);
        System.out.println("Prechodova tabulka:\n");
        System.out.println(Arrays.deepToString(prechodovaTabulka).replace("]]"," \n")
                                                                 .replace("["," ")
                                                                 .replace("]"," \t\t")
                                                                 .replace("null","")
                                                                 .replace(",",""));

    }


}
