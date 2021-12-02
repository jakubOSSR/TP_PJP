package NadbytocneSymboly;

import com.gramatiky.BezkontextovaGramatika;
import com.gramatiky.Pravidlo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class OdstranenieNS {
    public static ArrayList<String> vytvorNt(BezkontextovaGramatika gramatika) {
        // Vytvorenie množiny neterminálov, ktoré môžu generovať terminálne retazce

        ArrayList<String> Nt = new ArrayList<String>();
        ArrayList<String> Pom_Nt = new ArrayList<String>();
        do {
            boolean find = false;
            Pom_Nt.clear();
            Pom_Nt.addAll(Nt);

            for (Pravidlo r: gramatika.getPravidla()) {
                find = false;
                for (String t : r.getPravaStrana()) {
                    if (gramatika.getNeterminaly().contains(t) && !Nt.contains(t)) {
                        find = true;
                    }
                }

                if ( !find && !Nt.contains(r.getLavaStrana().get(0))) Nt.add(r.getLavaStrana().get(0));

            }

        } while ( Nt.size() != Pom_Nt.size() );
        return Nt;

    }

    public static ArrayList<String> vytvorVd(BezkontextovaGramatika gramatika) {
        //Vytvorenie množiny dostupných symbolov gramatiky

        ArrayList<String> Vd = new ArrayList<String>();
        ArrayList<String> Pom_Vd = new ArrayList<String>();
        do {
            if (Vd.isEmpty()) {
                Vd.add(gramatika.getZaciatocnySymbol());
            } else {

                Pom_Vd.clear();
                Pom_Vd.addAll(Vd);

                for(Pravidlo r: gramatika.getPravidla()) {
                    if ( Vd.contains(r.getLavaStrana().get(0)) ) {
                        for(String sym:r.getPravaStrana())
                            if (!Vd.contains(sym)) {
                                Vd.add(sym);
                            }
                    }
                }

            }
        } while(Vd.size() != Pom_Vd.size());
        return Vd;
    }

    public static BezkontextovaGramatika redukovanaGramatika (BezkontextovaGramatika gramatika) {
        //1.krok odstranienie neterminalov z gramatiky
        BezkontextovaGramatika redG = null; // vysledná redukovana gramatika, zatial null
        ArrayList<String> Nt = vytvorNt(gramatika); // mnozina Neterminalov z ktorych vieme odvodit terminaly
        ArrayList<String> Nt_nep = new ArrayList<String>(); // mnozina Neterminalov, ktore nepatria do Nt
        HashSet<String> nter = new HashSet<String>(); // nova mnozina neterminalov pre redukovanu gramatiku

        for ( String N : gramatika.getNeterminaly() ) {
            if (!Nt.contains(N)) Nt_nep.add(N); else nter.add(N); // naplnenie mnoziny Nt_nep a novych neterminalov
        }

        LinkedHashSet<Pravidlo> novePravidla = new LinkedHashSet<Pravidlo>(); // nove pravidla pre gramatiku
        // odstranenie Neterminalnych symbolov z gramatiky
        for ( Pravidlo r: gramatika.getPravidla() ) {
            if ( Nt.contains(r.getLavaStrana().get(0)) ) {
                novePravidla.add(r);
                for (int i=0;i<r.getPravaStrana().size();i++)
                    if ( Nt_nep.contains(r.getPravaStrana().get(i)) )
                        novePravidla.remove(r);
            }
        }

        try { // 2. krok vytvorenie gramatiky a hladanie dostupnych symbolov
            BezkontextovaGramatika novaGramatika = new BezkontextovaGramatika (gramatika.getTerminaly(),
                    nter,
                    gramatika.getZaciatocnySymbol(),
                    novePravidla);

            ArrayList<String> Vd = vytvorVd(novaGramatika);
            ArrayList<String> Vn = new ArrayList<String>();
            HashSet<String> terminals = new HashSet<String> ();
            HashSet<String> nterminals = new HashSet<String>();

            for (String s : novaGramatika.getNeterminaly())
                if (!Vd.contains(s)) Vn.add(s); else  nterminals.add(s);
            for (String s : novaGramatika.getTerminaly())
                if (!Vd.contains(s)) Vn.add(s); else terminals.add(s);

            LinkedHashSet<Pravidlo> redukovanePravidla = new LinkedHashSet<Pravidlo>();

            for ( Pravidlo r: novaGramatika.getPravidla() ) {
                if ( Vd.contains(r.getLavaStrana().get(0)) ) {
                    redukovanePravidla.add(r);
                    for (int i=0;i<r.getPravaStrana().size();i++)
                        if ( Vn.contains(r.getPravaStrana().get(i)) )
                            redukovanePravidla.remove(r);
                }
            }
            redG = new BezkontextovaGramatika (terminals,
                    nterminals,
                    novaGramatika.getZaciatocnySymbol(),
                    redukovanePravidla );



        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return redG;

    }

}
