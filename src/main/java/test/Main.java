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


        HashSet<String> neterminaly =new HashSet<>(Arrays.asList("S","A","B","C"));
        HashSet<String> terminaly =new HashSet<>(Arrays.asList("a","b", "c"));
        String zaciatocnySymbol = "S";
        FirstAFollow firstAFollow = new FirstAFollow();


        Pravidlo pravidlo1 = new Pravidlo(new ArrayList<>(Arrays.asList("S")), new ArrayList<>(Arrays.asList("A","B")));
        Pravidlo pravidlo2 = new Pravidlo(new ArrayList<>(Arrays.asList("S")), new ArrayList<>(Arrays.asList("c","B")));
        Pravidlo pravidlo3 = new Pravidlo(new ArrayList<>(Arrays.asList("A")), new ArrayList<>(Arrays.asList("a","A","c","B")));
        Pravidlo pravidlo4 = new Pravidlo(new ArrayList<>(Arrays.asList("A")), new ArrayList<>(Arrays.asList("epsilon")));
        Pravidlo pravidlo5 = new Pravidlo(new ArrayList<>(Arrays.asList("B")), new ArrayList<>(Arrays.asList("a","b")));
        Pravidlo pravidlo6 = new Pravidlo(new ArrayList<>(Arrays.asList("B")), new ArrayList<>(Arrays.asList("A","c")));
        Pravidlo pravidlo7 = new Pravidlo(new ArrayList<>(Arrays.asList("B")), new ArrayList<>(Arrays.asList("C")));
        Pravidlo pravidlo8 = new Pravidlo(new ArrayList<>(Arrays.asList("C")), new ArrayList<>(Arrays.asList("epsilon")));
        LinkedHashSet<Pravidlo> pravidla = new LinkedHashSet<>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4, pravidlo5,pravidlo6, pravidlo7,pravidlo8));
        BezkontextovaGramatika g = new BezkontextovaGramatika(terminaly,neterminaly,zaciatocnySymbol,pravidla);
        firstAFollow.follow(g);

    }}


