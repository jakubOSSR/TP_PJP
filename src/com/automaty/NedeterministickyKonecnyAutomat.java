package com.automaty;

import java.util.Arrays;
import java.util.List;

public class NedeterministickyKonecnyAutomat {

    private List<String> stavy;
    private List<String> symboly;
    private String zaciatocnyStav;
    private List<String> akceptujuceStavy;
    private String [] riadkyTabulkyNKA;
    String eps = "epsilon";
    boolean withEps = false;
    boolean sameSymbol = false;


    public NedeterministickyKonecnyAutomat(List<String> stavy, List<String> symboly,
                                           String zaciatocnyStav, List<String> akceptujuceStavy,
                                           String [] riadkyTabulkyNKA) throws Exception{
        this.stavy = stavy;
        this.symboly = symboly;
        this.zaciatocnyStav = zaciatocnyStav;
        this.akceptujuceStavy = akceptujuceStavy;
        this.riadkyTabulkyNKA = riadkyTabulkyNKA;

            int index1 = 0;
            int index2 = 0;

            for(int i=0; i< riadkyTabulkyNKA.length;i++){
                if(riadkyTabulkyNKA[i].contains(eps)){
                    withEps = true;
                }
                index1 = riadkyTabulkyNKA[i].indexOf("do")+2;
                index2 = riadkyTabulkyNKA[i].length();
                for(int j = index1; j<riadkyTabulkyNKA[i].length();j++){
                    if((index2-index1) > 3){
                        sameSymbol = true;
                    }
                }
            }

            if((withEps == false && sameSymbol==false )){
                throw new Exception("Zadaný autom nie je NKA automat!!");
            }
            else{
                System.out.println("NKA automat bol správne zadaný!");
            }

             System.out.println("Stavy: "+stavy);
             System.out.println("Symboly: "+symboly);
             System.out.println("Prechodové pravidla NKA automatu:");
             System.out.println(Arrays.deepToString(riadkyTabulkyNKA).replace("["," ")
                                                                     .replace("]","")
                                                                     .replace(",","\n"));


    }



}
