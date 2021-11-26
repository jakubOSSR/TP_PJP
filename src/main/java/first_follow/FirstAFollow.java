package first_follow;
import com.gramatiky.*;

import java.security.PrivilegedAction;
import java.util.*;

public class FirstAFollow {

    private  HashSet<String> mnozina = new HashSet<>();
    private  HashMap<String,HashSet<String>> vysledok = new HashMap<>();
    String eps = "epsilon";

    public void first(BezkontextovaGramatika g) throws Exception {


        for (String t : g.getTerminaly()){
            mnozina = new HashSet<>();
            mnozina.add(t);
            vysledok.put(t,mnozina);
        }

       for(Pravidlo p: g.getPravidla()){
           String symbol = p.getLavaStrana().get(0);
           mnozina = new HashSet<>();
            if(g.getTerminaly().contains(p.getPravaStrana().get(0))) {
                mnozina.add(p.getPravaStrana().get(0));
                if(vysledok.containsKey(symbol)){
                  vysledok.get(symbol).add(p.getPravaStrana().get(0));
                }
                else
                    vysledok.put(p.getLavaStrana().get(0),mnozina);


            }
            else if(p.getPravaStrana().get(0).equals(eps)){
                mnozina.add(eps);
                if(vysledok.containsKey(symbol)){
                    vysledok.get(symbol).add(eps);
                }
                else
                    vysledok.put(symbol,mnozina);
            }
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

    }
    public void vypisMnozinu(){
        for(Map.Entry<String,HashSet<String>> s : vysledok.entrySet()){
            System.out.println("First("+s.getKey()+") = " +s.getValue());
        }
    }

    }
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




    }
    public ArrayList<String> getTerminaly(){
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
    }*/

