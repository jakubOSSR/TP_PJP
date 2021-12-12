package com.gramatiky;

import java.util.ArrayList;
import java.util.HashMap;

public class FirstAFollow {
    public static HashMap<String, ArrayList<String>> first(BezkontextovaGramatika g) {

        HashMap<String, ArrayList<String>> vysledokfirst = new HashMap<>();

        for (String t : g.getTerminaly()) {                     //Prehladavanie terminalov
            ArrayList<String> mnozina = new ArrayList<>();
            mnozina.add(t);
            vysledokfirst.put(t, mnozina);                           //Vytvorenie mnozin first pre terminaly... First(a) = a
        }

        for (String n : g.getNeterminaly()) {                   //Prehladavanie neterminalov
            ArrayList<String> mnozina = new ArrayList<>();
            vysledokfirst.put(n, mnozina);                           //Pridanie neterminalov ako klucov do mapy
        }
        //Riesenie pravidiel tvaru A->aα, kedy "a" patri do First(A)
        for (Pravidlo p : g.getPravidla()) {                    //Prehladavanie pravidiel
            if (g.getTerminaly().contains(p.getPravaStrana().get(0))) {     //Ak je prvy retazec na pravej strane terminal,
                if (!vysledokfirst.get(p.getLavaStrana().get(0)).contains(p.getPravaStrana().get(0))) {
                    vysledokfirst.get(p.getLavaStrana().get(0)).add(p.getPravaStrana().get(0)); //prida sa do First neterminalu na lavej strane
                }
            }
        }
        //Riesenie pravidiel tvaru A->epsilon, kedy "epsilon" patri do First(A)
        for (Pravidlo p : g.getPravidla()) {
            if (p.getPravaStrana().get(0).equals("epsilon")) {       //Ak je na pravej strane "epsilon",
                vysledokfirst.get(p.getLavaStrana().get(0)).add("epsilon");   //prida sa do First neterminalu na lavej strane
            }
        }
        //Riesenie pravidel tvaru A->Bα
        boolean zmena = true;
        while (zmena) { //cyklus sa opakuje pokial sa do niektorej z First mnozin prida novy prvok
            zmena = false;
            for (Pravidlo p : g.getPravidla()) {
                boolean jeepsilon = false;
                if (g.getNeterminaly().contains(p.getPravaStrana().get(0))) { //ked je prvy retazec na pravej strane neterminal,
                    if (vysledokfirst.get(p.getPravaStrana().get(0)).contains("epsilon")) { //zistujeme ci do jeho first mnoziny patri "epsilon"
                        jeepsilon = true;
                    } else {
                        jeepsilon = false;
                    }
                    for (int i = 0; i < vysledokfirst.get(p.getPravaStrana().get(0)).size(); i++) { //prehladame mnozinu first neterminalu na pravej strane
                        if (vysledokfirst.get(p.getLavaStrana().get(0)).contains(vysledokfirst.get(p.getPravaStrana().get(0)).get(i))) {
                            /*Ak mnozina first neterminalu na lavej strane pravidla uz obsahuje dany prvok mnoziny
                            first neterminalu na pravej strane, tak sa nevykona nic */
                        } else {                                                            //v opacnom pripade
                            if (vysledokfirst.get(p.getPravaStrana().get(0)).get(i).equals("epsilon")) { //overime, ci dany prvok je epsilon
                            /*Ak nie je epsilon, tak dany prvok pridame do mnoziny first neterminalu na lavej strane pravidla
                            a zaznamename, ze bola vykonana zmena (do first mnoziny bol pridany novy prvok) */
                            } else {
                                vysledokfirst.get(p.getLavaStrana().get(0)).add(vysledokfirst.get(p.getPravaStrana().get(0)).get(i));
                                zmena = true;
                            }
                        }
                    }
                }
                for (int j = 1; j < p.getPravaStrana().size(); j++) { //prehladavame zvysne prvky pravej strany pravidla
                    if (jeepsilon) {       // pre pripad, ze first mnozina predchadzajuceho neterminalu na pravej strane obsahuje epsilon
                        if (g.getTerminaly().contains(p.getPravaStrana().get(j))) { //ak je nasledujuci prvok terminal
                            if (vysledokfirst.get(p.getLavaStrana().get(0)).contains(p.getPravaStrana().get(j))) {

                            } else {        // a este nieje vo first mnozine neterminalu na lavej strane,
                                vysledokfirst.get(p.getLavaStrana().get(0)).add(p.getPravaStrana().get(j)); //tak ho tam pridame
                                zmena = true;         //a zaznamename, ze bola vykonana zmena
                            }
                        }
                        if (g.getNeterminaly().contains(p.getPravaStrana().get(j))) {  //ak je nasledujuci prvok neterminal
                            if (vysledokfirst.get(p.getPravaStrana().get(j)).contains("epsilon")) { //zistime, ci jeho first mnozina obsahuje epsilon
                                jeepsilon = true;
                            } else {
                                jeepsilon = false;
                            }
                            for (int k = 0; k < vysledokfirst.get(p.getPravaStrana().get(j)).size(); k++) { //prehladame first mnozinu tohto neterminalu
                                if (vysledokfirst.get(p.getLavaStrana().get(0)).contains(vysledokfirst.get(p.getPravaStrana().get(j)).get(k))) {

                                } else {                    // ak prvok z first mnoziny neterminalu este nieje v mnozine first neterminalu na lavej strane
                                    if (vysledokfirst.get(p.getPravaStrana().get(0)).get(k).equals("epsilon")) {

                                    } else {                // a tento prvok nieje epsilon
                                        vysledokfirst.get(p.getLavaStrana().get(0)).add(vysledokfirst.get(p.getPravaStrana().get(j)).get(k)); //tak ho pridame do first mnoziny neterminalu na lavej strane
                                        zmena = true;                                       // a zaznamename, ze sa vykonala zmena
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return vysledokfirst;
    }
    /*Hladanie mnozin Follow*/
    public static HashMap<String, ArrayList<String>> follow(BezkontextovaGramatika g) {
        HashMap<String, ArrayList<String>> vysledokfirst = first(g);
        HashMap<String, ArrayList<String>> vysledokfollow = new HashMap<>();

        for (String n : g.getNeterminaly()) {                 //pre vsetky Neterminaly vytvorime prazdne mnoziny Follow
            ArrayList<String> mnozina = new ArrayList<>();
            vysledokfollow.put(n, mnozina);
        }
        ArrayList<String> mnozina = new ArrayList<>();              //do mnoziny Follow zaciatocneho symbolu pridame epsilon
        mnozina.add("epsilon");
        vysledokfollow.get(g.getZaciatocnySymbol()).add("epsilon");

        boolean zmena = true;
        while (zmena) {       //cyklus sa vykonava vzdy ked v jeho priebehu nastane zmena(do niektorej follow mnoziny sa prida novy prvok)
            zmena = false;
            for (Pravidlo p : g.getPravidla()) {                    //prehladame pravidla
                for (int i = 0; i < p.getPravaStrana().size(); i++) {    //prehladame pravu stranu
                    if (g.getNeterminaly().contains(p.getPravaStrana().get(i))) {    //ked sa na pozicii i nachadza neterminal
                        if (i + 1 != p.getPravaStrana().size()) {               // a tento neterminal nieje na konci pravej strany pozrieme sa na nasledujuci prvok
                            if (g.getTerminaly().contains(p.getPravaStrana().get(i + 1))) {  //ked nasleduje terminal
                                if (!vysledokfollow.get(p.getPravaStrana().get(i)).contains(p.getPravaStrana().get(i + 1))) { //a este nieje v mmnozine follow neterminalu
                                    vysledokfollow.get(p.getPravaStrana().get(i)).add(p.getPravaStrana().get(i + 1)); //tak ho tam pridame
                                    zmena = true;                                           // a zaznamename, ze sa vykonala zmena
                                }
                            } else {                                                         // ked za neterminalom(i) je neterminal
                                for (int j = i + 1; j < p.getPravaStrana().size(); j++) {   //prehladavame celu pravu stranu za neterminalom(i)
                                    for (int k = 0; k < vysledokfirst.get(p.getPravaStrana().get(j)).size(); k++) { //prehladavame first mnozinu prvku(j)
                                        if (!vysledokfirst.get(p.getPravaStrana().get(j)).get(k).equals("epsilon")) {
                                            //if(k < vysledokfirst.get(p.getPravaStrana().get(j)).size()){
                                            if (!vysledokfollow.get(p.getPravaStrana().get(i)).contains(vysledokfirst.get(p.getPravaStrana().get(j)).get(k))) {
                                                vysledokfollow.get(p.getPravaStrana().get(i)).add(vysledokfirst.get(p.getPravaStrana().get(j)).get(k)); //vsetko okrem epsilonu pridame do follow(neterminalu(i))

                                                zmena = true;
                                            }
                                            //}

                                        }
                                    }
                                    if (!vysledokfirst.get(p.getPravaStrana().get(j)).contains("epsilon")) { //ked First niektoreho prvku za terminalom(i) neobsahuje epsilon
                                        break;                                                               // pravu stranu dalej neprehladavame
                                    }
                                    if (j + 1 == p.getPravaStrana().size()) {                  //ak pri prehladavani dosiahneme posledny prvok na pravej strane
                                        if (vysledokfirst.get(p.getPravaStrana().get(j)).contains("epsilon")){ //a first mnozina tohoto prvku obsahuje epsilon
                                            for (int k = 0; k < vysledokfollow.get(p.getLavaStrana().get(0)).size(); k++) {
                                                if (!vysledokfollow.get(p.getLavaStrana().get(0)).get(k).equals("epsilon")) {
                                                    if (!vysledokfollow.get(p.getPravaStrana().get(i)).contains(vysledokfollow.get(p.getLavaStrana().get(0)).get(k))) {
                                                        //tak do mnoziny follow(neterminal(i)) pridame vsetko co sa nachadza vo follow(Ľava Strana pravidla)
                                                        vysledokfollow.get(p.getPravaStrana().get(i)).add(vysledokfollow.get(p.getLavaStrana().get(0)).get(k));
                                                        zmena = true;
                                                    }
                                                }
                                            }
                                    }
                                }


                                }

                            }
                        } else {          //ked sa neterminal nachadza na konci
                            for (int l = 0; l < vysledokfollow.get(p.getLavaStrana().get(0)).size(); l++) {
                                if (!vysledokfollow.get(p.getLavaStrana().get(0)).get(l).equals("epsilon")) {
                                    if (!vysledokfollow.get(p.getPravaStrana().get(i)).contains(vysledokfollow.get(p.getLavaStrana().get(0)).get(l))) {
                                        //tak do mnoziny follow(neterminal) pridame vsetko co sa nachadza vo follow(Ľava Strana pravidla)
                                        vysledokfollow.get(p.getPravaStrana().get(i)).add(vysledokfollow.get(p.getLavaStrana().get(0)).get(l));
                                        zmena = true;

                                    }
                                }
                            }
                        }
                    }
                }
            }


        }
        /*Nasledujuca cast riesi pridavanie epsilonu do follow mnoziny neterminalov,
        ktore sa mozu nachadzat na konci niektorej vetnej formy:
        Prípad: S->ABC, epsilon patri do first(C) a first(B), takze do follow vsetkych troch neterminalov patri epsilon*/
        for (Pravidlo p : g.getPravidla()) {                //prehladame pravidla
            if (p.getLavaStrana().get(0).equals(g.getZaciatocnySymbol())) {   //ked je na lavej strane zaciatocny symbol
                for (int m = (p.getPravaStrana().size()) - 1; m >= 0; m--) {   //prehladavame pravu stranu pravidla od konca
                    if (m + 1 == p.getPravaStrana().size()) {                  //ked sme na poslednom symbole
                        if (g.getNeterminaly().contains(p.getPravaStrana().get(m))) {   //a je to neterminal
                            if (!vysledokfollow.get(p.getPravaStrana().get(m)).contains("epsilon")) {
                                vysledokfollow.get(p.getPravaStrana().get(m)).add("epsilon"); //tak do jeho follow mnoziny pridame epsilon
                            }
                        }
                    }
                    if (vysledokfirst.get(p.getPravaStrana().get(m)).contains("epsilon")) {    //ak first mnozina prvku pravej strany obsahuje epsilon, musime sa pozriet na predchadzajuci prvok
                        if ((m >= 1)) {
                            if (g.getNeterminaly().contains(p.getPravaStrana().get(m - 1))) { //ked sa tam nachadza neterminal
                                if (!p.getPravaStrana().get(m - 1).equals("epsilon")) {
                                    if (!vysledokfollow.get(p.getPravaStrana().get(m - 1)).contains("epsilon")) {
                                        vysledokfollow.get(p.getPravaStrana().get(m - 1)).add("epsilon");  //tak do jeho follow mnoziny pridame epsilon
                                    }
                                }
                            }
                        }
                    }
                    else{
                        break;
                    }

                }

            }

        }
        /*Nasledujuca cast riesi pridavanie epsilonu do follow mnoziny neterminalov,
        ktore sa mozu nachadzat na konci niektorev vetnej formy:
        Prípad: S->ABC, C->D, D->E, takze do follow D a follow E taktiez patri epsilon*/
        String lavaStrana = g.getZaciatocnySymbol();   //v prvom cykle hladame pravidla, ked na lavej strane je zaciatocny symbol
        for (Pravidlo p : g.getPravidla()) { //prehladame pravidla
            if (p.getLavaStrana().get(0).equals(lavaStrana)) {
                for (int m = (p.getPravaStrana().size()) - 1; m >= 0; m--) {   //prehladavame odkonca pravu stranu pravidla s prislusnou lavou stranou
                    if (g.getNeterminaly().contains(p.getPravaStrana().get(m))) { //ked to je neterminal
                        lavaStrana = p.getPravaStrana().get(m);    //tak budeme hladat pravidla kde na lavej strane je tento neterminal
                        for (Pravidlo o : g.getPravidla()) {       //opat prehladavame pravidla
                            if (o.getLavaStrana().get(0).equals(lavaStrana)) {
                                for (int n = (o.getPravaStrana().size()) - 1; n >= 0; n--) { //opat prehladame od konca pravu stranu najdeneho pravidla
                                    if (g.getNeterminaly().contains(o.getPravaStrana().get(n))) { //ked je to neterminal
                                        if (!vysledokfollow.get(o.getPravaStrana().get(n)).contains("epsilon")) {
                                            vysledokfollow.get(o.getPravaStrana().get(n)).add("epsilon"); //tak do jeho follow mnoziny pridame epsilon
                                            lavaStrana = o.getPravaStrana().get(n); //tento neterminal sa opat stane hladanou lavou stranou
                                        }
                                    } else {
                                        break; //ked sa na pozicii n nachadza terminal, tak sa ukonci prehladavanie pravej strany
                                    }
                                    if (vysledokfirst.get(o.getPravaStrana().get(n)).contains("epsilon")) {   // ak First(prvku(n)) obsahuje epsilon
                                        if (!vysledokfollow.get(o.getPravaStrana().get(n-1)).contains("epsilon")) {
                                            vysledokfollow.get(o.getPravaStrana().get(n-1)).add("epsilon"); //tak aj do follow(prvku(n-1)) pridame epsilon
                                        }
                                    }
                                    else {
                                        break; //ak first(prvku(n)) neobsahuje epsilon, prehladavanie pravej strany sa skonci
                                    }
                                }
                            }
                        }
                        if (!vysledokfirst.get(p.getPravaStrana().get(m)).contains("epsilon")) {
                            break;
                        }
                    }
                    else {
                        break; //ked sa na pozicii m nachadza terminal, tak sa ukonci prehladavanie pravej strany
                    }
                }
            }
        }
        return vysledokfollow;
    }
}




