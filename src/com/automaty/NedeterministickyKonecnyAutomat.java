package com.automaty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NedeterministickyKonecnyAutomat {

    private List<String> stavyNKA;
    private List<String> symboly;
    private List<String> zaciatocnyStavNKA;
    private List<String> akceptujuceStavyNKA;
    private String [] riadkyTabulkyNKA;
    String eps = "epsilon";
    boolean withEps = false;
    boolean sameSymbol = false;


    public NedeterministickyKonecnyAutomat(List<String> stavyNKA, List<String> symboly,
                                          List<String> zaciatocnyStavNKA, List<String> akceptujuceStavyNKA,
                                           String [] riadkyTabulkyNKA) throws Exception{
        this.stavyNKA = stavyNKA;
        this.symboly = symboly;
        this.zaciatocnyStavNKA = zaciatocnyStavNKA;
        this.akceptujuceStavyNKA = akceptujuceStavyNKA;
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
                throw new Exception("Zadaný automat nie je NKA automat!!");
            }
            else{
                System.out.println("NKA automat bol správne zadaný!");
            }

             System.out.println("Stavy: "+stavyNKA);
             System.out.println("Symboly: "+symboly);
             System.out.println("Prechodové tabulka NKA automatu:");
             System.out.println(Arrays.deepToString(riadkyTabulkyNKA).replace("["," ")
                                                                     .replace("]","")
                                                                     .replace(",","\n"));

    }
    public String [] transformujTabulku (String [] riadkyTabulkyNKA,List<String> stavyNKA,
                                         List<String> symboly,List<String>akceptujuceStavyNKA,
                                         List<String> zaciatocnyStavNKA){
        String [] riadkyTabulkyDKA;
        String s = null;
        for(int i=0;i<riadkyTabulkyNKA.length;i++){
            s = riadkyTabulkyNKA[i];

        }

        return null;
    }
    public List<String> akceptujuceStavyDKA(){
        return null;
    }
    public List<String> stavyDKA(){
        return null;
    }
    public List<String> zaciatocnyStavDKA(){
        return null;
    }



}
