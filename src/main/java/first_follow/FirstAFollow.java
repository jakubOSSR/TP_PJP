package first_follow;
import com.gramatiky.*;

import javax.naming.event.ObjectChangeListener;
import java.security.PrivilegedAction;
import java.util.*;

public class FirstAFollow {

    private HashSet<String> mnozina = new HashSet<>();
    private HashMap<String, HashSet<String>> vysledok = new HashMap<>();
    private HashSet<String> pomocna = new HashSet<>();

    String eps = "epsilon";

    public void first(BezkontextovaGramatika g) throws Exception {


        for (String t : g.getTerminaly()) { // Prehladavanie terminalov
            mnozina = new HashSet<>();
            mnozina.add(t);
            vysledok.put(t, mnozina);      // vytvorenie mnozin first pre terminaly... First(a) = a
        }

        for (Pravidlo p : g.getPravidla()) {  //prehladavanie pravidiel
            String symbol = p.getLavaStrana().get(0);   //do premennej "symbol" sa nacita prvy retazec pravej strany pravidla
            mnozina = new HashSet<>();
            if (g.getTerminaly().contains(p.getPravaStrana().get(0))) { //ked je na pravej strane terminal, tak
                mnozina.add(p.getPravaStrana().get(0));                 //sa tento terminal prida do "mnoziny"
                if (vysledok.containsKey(symbol)) {                     // ak sa uz v mape nachadza prislusny kluc
                    vysledok.get(symbol).add(p.getPravaStrana().get(0));    //tak sa k nemu prida "mnozina" obsahujuca terminal
                } else                                                  //ak v mape neexistuje este kluc
                    vysledok.put(p.getLavaStrana().get(0), mnozina);    //tak sa vytvori kluc a prida sa k nemu "mnozina"

            } else if (p.getPravaStrana().get(0).equals(eps)) {         //ak je na pravej strane terminal
                mnozina.add(eps);
                if (vysledok.containsKey(symbol))                       //rovnakou podmienkou ako v predchadzajucom pripade
                    vysledok.get(symbol).add(eps);                      //sa vlozi hodnota "epsilon" do vysledku
                else
                    vysledok.put(symbol, mnozina);
            } }
        for (Pravidlo p : g.getPravidla()) {
            String symbol = p.getLavaStrana().get(0);
            if (g.getNeterminaly().contains(p.getPravaStrana().get(0))) {  //ak je na pravej strane neterminal
            najdiMnozinu(p.getPravaStrana().get(0));
                //Iterator<String> itr = mnozina.iterator();

           // while(itr.hasNext()) {
                //System.out.println(mnozina);
               // System.out.println(itr.next());
                //if (!.getValue().contains(ele)) {
                    //mnozina.add(ele);
                    if (vysledok.containsKey(symbol)) {
                        vysledok.get(symbol).addAll(mnozina);
                    } else
                        vysledok.put(symbol, mnozina);                      // tak sa do vysledku prida neterminal
                //}
            }
        }
           /* for (Map.Entry<String, HashSet<String>> s : vysledok.entrySet()) {  //prehladavaju sa vysledky
                System.out.println(vysledok.entrySet());
                for (String neterminal : g.getNeterminaly()) {
                    if (s.getValue().contains(neterminal)){
                        najdiMnozinu(neterminal);
                        System.out.println(mnozina);
                        for(String ele: mnozina) {
                            if (!s.getValue().contains(ele)) {
                                mnozina.add(ele);
                                if (vysledok.containsKey(s.getKey()))
                                    vysledok.get(s.getKey()).add(ele.replace("[", "").replace("]", ""));
                                else
                                    vysledok.put(s.getKey(), mnozina);

                            }
                        }
                    }





                }
            }*/



        /*for(Map.Entry<String,HashSet<String>> s : vysledok.entrySet()){
            for (String neterminal: g.getNeterminaly()) {
                System.out.println(neterminal);
                if (s.getValue().contains(neterminal)) {
                    System.out.println(neterminal);
                    System.out.println(s.getValue());

                    //vysledok.remove(s.getKey(), s.getValue());
                }
            }

        }*/
        //vycistiVypis(g.getNeterminaly());
    }

        /*if (g.getTerminaly().contains(retazecFirst.get(0))) {
                vysledok.add(retazecFirst.get(0));
        } else {
                if (g.getNeterminaly().contains(retazecFirst.get(0))) {
                    for(Pravidlo p: g.getPravidla()){
                        if (p.getLavaStrana().contains(retazecFirst.get(0))) {
                            retazecFirst.clear();
                            retazecFirst.add(p.getPravaStrana().get(0));
                                if (g.getTerminaly().contains(retazecFirst.get(0))) {
                                    vysledok.add(p.getPravaStrana().get(0));
                                    g.getPravidla().iterator().next();
                                }
                        }
                    }
                } else {
                    if (retazecFirst.get(0).equals(eps)) {
                        vysledok.add(retazecFirst.get(0));
                    } else {
                        throw new Exception("Prvy symbol sa nenachadza medzi terminalmi ani neterminalmi");
                    }
                }
            }*/


    // System.out.println(retazecFirst);


    public void vypisMnozinu() {

        for (Map.Entry<String, HashSet<String>> s : vysledok.entrySet()) {
            System.out.println("First(" + s.getKey() + ") = " + s.getValue());
        }
        //vycistiVypis();
    }

    public HashSet<String> najdiMnozinu(String neterminal) {
        for (Map.Entry<String, HashSet<String>> s : vysledok.entrySet()) {
            if (s.getKey().equals(neterminal)) {
                System.out.println(s.getValue().toString());
                mnozina.addAll(s.getValue());

            }
        }

        return mnozina;

    }


    /*public HashSet<String> iteruj(String neterminal) {
        for (Map.Entry<String, HashSet<String>> s : vysledok.entrySet()) {
            for (String ele : s.getValue()) {
                if (neterminal.equals(ele)) {
                    najdiMnozinu(neterminal);

                }

            }
        }
        return mnozina;
    }*/

    /*public void vycistiVypis() {
        for (Map.Entry<String, HashSet<String>> s : vysledok.entrySet()) {
            System.out.println(s.getKey());
            pomocna = s.getValue();
            System.out.println(pomocna);

        }

    }*/



    /*private HashSet<String> terminaly;
    private HashSet<String> neterminaly;
    private String zaciatocnySymbol;
    private LinkedHashSet<Pravidlo> pravidla;
    ArrayList<ArrayList<String>> firstSet = new ArrayList<ArrayList<String>>();
    ArrayList<String> firstInnerSet = new ArrayList<String>();*/

    //ArrayList<String> firstSet = new ArrayList<String>();

    // BezkontextovaGramatika BKgramatika = new BezkontextovaGramatika(terminaly, neterminaly, zaciatocnySymbol, pravidla);

    /*public First() {
        for (Pravidlo p : pravidla) {
            ArrayList A = p.getLavaStrana();
            for (int i = 0; i < p.getPravaStrana().size(); i++) {
                if(terminaly.contains(p.getPravaStrana().get(i))){
                  A.add(p.getPravaStrana().get(i));
                  firstInnerSet.add(i,p.getPravaStrana().get(i));
                }
            }
   /* private HashSet<String> terminaly;
    private HashSet<String> neterminaly;
    private String zaciatocnySymbol;
    private LinkedHashSet<Pravidlo> pravidla;

    public HashSet First(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla){
        this.terminaly = terminaly;
        this.neterminaly = neterminaly;
        this.zaciatocnySymbol = zaciatocnySymbol;
        for (Pravidlo p:pravidla){
            for(terminaly.)

        }

 /*HashMap<String,HashSet<String>> kopia = (HashMap<String, HashSet<String>>) vysledok.clone();
        System.out.println(neterminal);
           for(Map.Entry<String,HashSet<String>> s : vysledok.entrySet()){
               for(String ele: s.getValue()) {
                   System.out.println(s.getKey());
                   System.out.println(neterminal);
                   System.out.println(ele);
                   if (neterminal.equals(ele)) {
                       System.out.println("okej");
                       System.out.println(s.getKey());
                       System.out.println(s.getValue());
                       System.out.println(kopia);
                       kopia.remove(s.getKey(),ele);
                       System.out.println(kopia);
                   }


               }

    } vysledok = kopia; System.out.println(kopia);return kopia;
}*/



   /* public ArrayList<String> getTerminaly(){
        return terminaly;
    }

    public ArrayList<String> getNeterminaly(){
        return neterminaly;
    }

    public String getZaciatocnySymbol(){
        return zaciatocnySymbol;
    }

    public LinkedHashSet<Pravidlo> getPravidla(){
        return pravidla;
    }*/}


