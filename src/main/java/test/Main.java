package test;


import com.gramatiky.BezkontextovaGramatika;
import com.gramatiky.Gramatika;
import com.gramatiky.Pravidlo;
import first_follow.FirstAFollow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Main {
    public static void main(String[] args) throws Exception {

        HashSet<String> neterminaly =new HashSet<>(Arrays.asList("S","E","T","F"));
        HashSet<String> terminaly =new HashSet<>(Arrays.asList("$","+", "*","(",")","id"));
        String zaciatocnySymbol = "S";
        FirstAFollow firstAFollow;


        Pravidlo pravidlo1 = new Pravidlo(new ArrayList<>(Arrays.asList("S")), new ArrayList<>(Arrays.asList("E","$")));
        Pravidlo pravidlo2 = new Pravidlo(new ArrayList<>(Arrays.asList("E")), new ArrayList<>(Arrays.asList("E","+","T")));
        Pravidlo pravidlo3 = new Pravidlo(new ArrayList<>(Arrays.asList("E")), new ArrayList<>(Arrays.asList("T")));
        Pravidlo pravidlo4 = new Pravidlo(new ArrayList<>(Arrays.asList("T")), new ArrayList<>(Arrays.asList("T","*","F")));
        Pravidlo pravidlo5 = new Pravidlo(new ArrayList<>(Arrays.asList("T")), new ArrayList<>(Arrays.asList("F")));
        Pravidlo pravidlo6 = new Pravidlo(new ArrayList<>(Arrays.asList("F")), new ArrayList<>(Arrays.asList("id")));
        Pravidlo pravidlo7 = new Pravidlo(new ArrayList<>(Arrays.asList("F")), new ArrayList<>(Arrays.asList("(","E", ")")));
        LinkedHashSet<Pravidlo> pravidla = new LinkedHashSet<>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5, pravidlo6, pravidlo7));
        BezkontextovaGramatika grammar = new BezkontextovaGramatika(terminaly,neterminaly,zaciatocnySymbol,pravidla) {
        };

        //System.out.println(retazecFirst);
        //System.out.println(pravidlo2.getLavaStrana());
        //System.out.println(pravidlo2.getPravaStrana());
        //System.out.println(pravidlo3.getPravaStrana());
        firstAFollow = new FirstAFollow();
        firstAFollow.first(grammar);
        firstAFollow.vypisMnozinu();

    }}


