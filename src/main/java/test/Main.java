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

        HashSet<String> neterminaly =new HashSet<>(Arrays.asList("S","A","B","C","D"));
        HashSet<String> terminaly =new HashSet<>(Arrays.asList("a","b","c","d"));
        String zaciatocnySymbol = "S";


        Pravidlo pravidlo1 = new Pravidlo(new ArrayList<>(Arrays.asList("S")), new ArrayList<>(Arrays.asList("B", "B", "a")));
        Pravidlo pravidlo2 = new Pravidlo(new ArrayList<>(Arrays.asList("B")), new ArrayList<>(Arrays.asList("A",  "A")));
        Pravidlo pravidlo3 = new Pravidlo(new ArrayList<>(Arrays.asList("A")), new ArrayList<>(Arrays.asList("c",  "b")));
        LinkedHashSet<Pravidlo> pravidla = new LinkedHashSet<>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3));
        BezkontextovaGramatika grammar = new BezkontextovaGramatika(terminaly,neterminaly,zaciatocnySymbol,pravidla) {
        };
        ArrayList<String> retazecFirst = new ArrayList<>();
        retazecFirst.add("S");
        //System.out.println(retazecFirst);
        //System.out.println(pravidlo2.getLavaStrana());
        //System.out.println(pravidlo2.getPravaStrana());
        //System.out.println(pravidlo3.getPravaStrana());

    FirstAFollow.first(retazecFirst, grammar);
    }}


