package first_follow;
import com.gramatiky.*;

import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class FirstAFollow {

    public static ArrayList<String> first(ArrayList<String> retazecFirst, BezkontextovaGramatika g) throws Exception {
        ArrayList<String> vysledok = new ArrayList<>();
        String eps = "epsilon";
        if(g.getTerminaly().contains(retazecFirst.get(0))){
            vysledok.add(retazecFirst.get(0));
        }
        else
        {
            if(g.getNeterminaly().contains(retazecFirst.get(0))) {
                }
            else {
                if(retazecFirst.get(0).equals(eps)){
                    vysledok.add(retazecFirst.get(0));
                }
            else
                {
                throw new Exception("Prvy symbol sa nenachadza medzi terminalmi ani neterminalmi");
            }}}
        System.out.println(vysledok);
        return vysledok;
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


