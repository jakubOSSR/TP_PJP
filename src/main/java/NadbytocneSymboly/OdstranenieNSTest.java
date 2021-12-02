package NadbytocneSymboly;

import com.gramatiky.BezkontextovaGramatika;
import com.gramatiky.Pravidlo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;

import static org.junit.jupiter.api.Assertions.*;

class OdstranenieNSTest {
    HashSet<String> terminalyNSBG = new HashSet<String>(Arrays.asList("begin", "end", "int", "string", "if", "then", "else", "output", "text","//"));
    HashSet<String> neterminalyNSBG = new HashSet<String>(Arrays.asList("<postupnost_prikazov>", "<deklaracia>", "<podmienka>", "<program>", "<prikaz>","<komentar>"));
    String zaciatocnySymbolNSBG = "<program>";
    Pravidlo pravidlo1NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<program>")), new ArrayList<String>(Arrays.asList("begin", "<postupnost_prikazov>", "end")));
    Pravidlo pravidlo2NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<deklaracia>", "<postupnost_prikazov>")));
    Pravidlo pravidlo3NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("int", "text")));
    Pravidlo pravidlo4NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<podmienka>", "<postupnost_prikazov>")));
    Pravidlo pravidlo5NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<podmienka>")), new ArrayList<String>(Arrays.asList("if", "text", "then", "<postupnost_prikazov>", "else", "<postupnost_prikazov>")));
    Pravidlo pravidlo6NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("output", "text")));
    Pravidlo pravidlo7NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<deklaracia>")), new ArrayList<String>(Arrays.asList("string", "text")));
    Pravidlo pravidlo8NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("epsilon")));
    Pravidlo pravidlo9NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<postupnost_prikazov>")), new ArrayList<String>(Arrays.asList("<deklaracia>", "<prikaz>")));
    Pravidlo pravidlo10NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<prikaz>")), new ArrayList<String>(Arrays.asList("<prikaz>")));
    Pravidlo pravidlo11NSBG = new Pravidlo(new ArrayList<String>(Arrays.asList("<komentar>")), new ArrayList<String>(Arrays.asList("//","text")));

    LinkedHashSet<Pravidlo> pravidlaNSBG = new LinkedHashSet<Pravidlo>(Arrays.asList(pravidlo1NSBG, pravidlo2NSBG, pravidlo3NSBG, pravidlo4NSBG, pravidlo5NSBG, pravidlo6NSBG, pravidlo7NSBG, pravidlo8NSBG,pravidlo9NSBG,pravidlo10NSBG, pravidlo11NSBG ));

    @Test
    void testOdstranenieNadbytocnychSymbolov(){
        try {
            BezkontextovaGramatika g = new BezkontextovaGramatika(terminalyNSBG, neterminalyNSBG, zaciatocnySymbolNSBG, pravidlaNSBG);
            BezkontextovaGramatika g1 = OdstranenieNS.redukovanaGramatika(g);
            System.out.println("Terminaly gramatiky g: "+g.getTerminaly().toString());
            System.out.println("Neterminaly gramatiky g: "+g.getNeterminaly().toString());
            System.out.println("Terminaly redukovanej gramatiky g: "+g1.getTerminaly().toString());
            System.out.println("Neterminaly redukovanej gramatiky g: "+g1.getNeterminaly().toString());
            System.out.println("Pravidla redukovanej gramatiky g: ");
            for ( Pravidlo r: g1.getPravidla() ) {
                System.out.println(r.getLavaStrana().toString()+" = "+r.getPravaStrana().toString());
                }



        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Unexepected exception");
        }
    }
}