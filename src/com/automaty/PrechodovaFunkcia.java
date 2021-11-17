package com.automaty;

import java.util.HashSet;

public class PrechodovaFunkcia {

    private HashSet<String> stavy;
    private HashSet<String> symboly;

    private String aktualnyStav;

    public PrechodovaFunkcia(HashSet<String> stavy, HashSet<String> symboly){
        this.stavy = stavy;
        this.symboly=symboly;

        System.out.println("\n______________PRECHODOVA FUNKCIA_______________");
        System.out.println("------vstupne parametre--------");
        System.out.println(stavy);
        System.out.println(symboly);

    }
}
