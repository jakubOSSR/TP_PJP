package first_follow;
import com.gramatiky.BezkontextovaGramatika;
import com.gramatiky.Pravidlo;

import java.util.ArrayList;
import java.util.HashMap;

public class FirstAFollow {
    public static HashMap<String, ArrayList<String>> first(BezkontextovaGramatika g) {

        HashMap<String, ArrayList<String>> vysledok = new HashMap<>();

        for (String t : g.getTerminaly()) {                     //Prehladavanie terminalov
            ArrayList<String> mnozina = new ArrayList<>();
            mnozina.add(t);
            vysledok.put(t, mnozina);                           //Vytvorenie mnozin first pre terminaly... First(a) = a
        }

        for (String n : g.getNeterminaly()) {                   //Prehladavanie neterminalov
            ArrayList<String> mnozina = new ArrayList<>();
            vysledok.put(n, mnozina);                           //Pridanie neterminalov ako klucov do mapy
        }
        //Riesenie pravidiel tvaru A->aα, kedy "a" patri do First(A)
        for (Pravidlo p : g.getPravidla()) {                    //Prehladavanie pravidiel
            if (g.getTerminaly().contains(p.getPravaStrana().get(0))) {     //Ak je prvy retazec na pravej strane terminal,
                vysledok.get(p.getLavaStrana().get(0)).add(p.getPravaStrana().get(0)); //prida sa do First neterminalu na lavej strane
            }
        }
        //Riesenie pravidiel tvaru A->epsilon, kedy "epsilon" patri do First(A)
        for (Pravidlo p : g.getPravidla()) {
            if (p.getPravaStrana().get(0) == "epsilon") {       //Ak je na pravej strane "epsilon",
                vysledok.get(p.getLavaStrana().get(0)).add("epsilon");   //prida sa do First neterminalu na lavej strane
            }
        }
        //Riesenie pravidel tvaru A->Bα
        boolean zmena = true;
        while (zmena == true) { //cyklus sa opakuje pokial sa do niektorej z First mnozin prida novy prvok
            zmena = false;
            for (Pravidlo p : g.getPravidla()) {
                boolean jeepsilon = false;
                if (g.getNeterminaly().contains(p.getPravaStrana().get(0))) { //ked je prvy retazec na pravej strane neterminal,
                    if (vysledok.get(p.getPravaStrana().get(0)).contains("epsilon")) { //zistujeme ci do jeho first mnoziny patri "epsilon"
                        jeepsilon = true;
                    } else {
                        jeepsilon = false;
                    }
                    for (int i = 0; i < vysledok.get(p.getPravaStrana().get(0)).size(); i++) { //prehladame mnozinu first neterminalu na pravej strane
                        if (vysledok.get(p.getLavaStrana().get(0)).contains(vysledok.get(p.getPravaStrana().get(0)).get(i))) {
                            /*Ak mnozina first neterminalu na lavej strane pravidla uz obsahuje dany prvok mnoziny
                            first neterminalu na pravej strane, tak sa nevykona nic */
                        } else {                                                            //v opacnom pripade
                            if (vysledok.get(p.getPravaStrana().get(0)).get(i) == "epsilon") { //overime, ci dany prvok je epsilon
                            /*Ak nie je epsilon, tak dany prvok pridame do mnoziny first neterminalu na lavej strane pravidla
                            a zaznamename, ze bola vykonana zmena (do first mnoziny bol pridany novy prvok) */
                            } else {
                                vysledok.get(p.getLavaStrana().get(0)).add(vysledok.get(p.getPravaStrana().get(0)).get(i));
                                zmena = true;
                            }
                        }
                    }
                }
                for (int j = 1; j < p.getPravaStrana().size(); j++) { //prehladavame zvysne prvky pravej strany pravidla
                    if (jeepsilon == true) {       // pre pripad, ze first mnozina predchadzajuceho neterminalu na pravej strane obsahuje epsilon
                        if (g.getTerminaly().contains(p.getPravaStrana().get(j))) { //ak je nasledujuci prvok terminal
                            if (vysledok.get(p.getLavaStrana().get(0)).contains(p.getPravaStrana().get(j))) {

                            } else {        // a este nieje vo first mnozine neterminalu na lavej strane,
                                vysledok.get(p.getLavaStrana().get(0)).add(p.getPravaStrana().get(j)); //tak ho tam pridame
                                zmena = true;         //a zaznamename, ze bola vykonana zmena
                            }
                        }
                        if (g.getNeterminaly().contains(p.getPravaStrana().get(j))) {  //ak je nasledujuci prvok neterminal
                            if (vysledok.get(p.getPravaStrana().get(j)).contains("epsilon")) { //zistime, ci jeho first mnozina obsahuje epsilon
                                jeepsilon = true;
                            } else {
                                jeepsilon = false;
                            }
                            for (int k = 0; k < vysledok.get(p.getPravaStrana().get(j)).size(); k++) { //prehladame first mnozinu tohto neterminalu
                                if (vysledok.get(p.getLavaStrana().get(0)).contains(vysledok.get(p.getPravaStrana().get(j)).get(k))) {

                                } else {                    // ak prvok z first mnoziny neterminalu este nieje v mnozine first neterminalu na lavej strane
                                    if (vysledok.get(p.getPravaStrana().get(0)).get(k) == "epsilon") {

                                    } else {                // a tento prvok nieje epsilon
                                        vysledok.get(p.getLavaStrana().get(0)).add(vysledok.get(p.getPravaStrana().get(j)).get(k)); //tak ho pridame do first mnoziny neterminalu na lavej strane
                                        zmena = true;                                       // a zaznamename, ze sa vykonala zmena
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return vysledok;
    }
}


