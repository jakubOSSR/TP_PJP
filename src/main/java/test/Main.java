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




        HashSet<String> neterminaly =new HashSet<>(Arrays.asList("program","prikazy","prikaz"));
        HashSet<String> terminaly =new HashSet<>(Arrays.asList("begin","end", "$", ";", "p"));
        String zaciatocnySymbol = "program";
        FirstAFollow firstAFollow = new FirstAFollow();


        Pravidlo pravidlo1 = new Pravidlo(new ArrayList<>(Arrays.asList("program")), new ArrayList<>(Arrays.asList("begin","prikazy","end","$")));
        Pravidlo pravidlo2 = new Pravidlo(new ArrayList<>(Arrays.asList("prikazy")), new ArrayList<>(Arrays.asList("prikaz",";", "prikazy")));
        Pravidlo pravidlo3 = new Pravidlo(new ArrayList<>(Arrays.asList("prikazy")), new ArrayList<>(Arrays.asList("epsilon")));
        Pravidlo pravidlo4 = new Pravidlo(new ArrayList<>(Arrays.asList("prikaz")), new ArrayList<>(Arrays.asList("p")));
        LinkedHashSet<Pravidlo> pravidla = new LinkedHashSet<>(Arrays.asList(pravidlo1, pravidlo2, pravidlo3, pravidlo4));
        BezkontextovaGramatika g = new BezkontextovaGramatika(terminaly,neterminaly,zaciatocnySymbol,pravidla);
        firstAFollow.follow(g);


    }}


