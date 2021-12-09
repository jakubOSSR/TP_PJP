package com.gramatiky;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Gramatika {
    private HashSet<String> terminaly; //terminály sú reprezentované množinou, kde každý prvok je typu String
    private HashSet<String> neterminaly; //neterminály sú reprezentované množinou, kde každý prvok je typu String
    private String zaciatocnySymbol; //začiatočný symbol gramatiky je reprezentovaný typom String
    private LinkedHashSet<Pravidlo> pravidla; //pravidlá sú reprezentované usporiadanou množinou (sú v takom poradí, v akom ich do množiny vkladáme), kde každé pravidlo je typu Pravidlo

    //vytvorenie konštruktora Gramatika s parametrami terminaly, neterminaly, zaciatocnySymbol a pravidla
    public Gramatika(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception{
        this.terminaly = terminaly;
        this.neterminaly = neterminaly;
        this.zaciatocnySymbol = zaciatocnySymbol;

        int ibaJedenSymbol = 0;
        int jeStartSymbol = 0;
        int ibaJedenSymbolNeterminal = 0;

        //tu sa kontroluje to, čo majú regulárna a bezkontextová gramatika spoločné, a teda, že na ľavej strane môže byť iba jeden neterminál
        //cyklus prechádzajúci každé pravidlo
        for (Pravidlo p: pravidla) {
            //kontrola či sa na ľavej strane pravidla nachádza práve jeden symbol
            if (p.getLavaStrana().size() > 1 || p.getLavaStrana().size() == 0) {
                throw new Exception("Lava strana musi obsahovat iba jeden symbol. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
            }
            else{
                ibaJedenSymbol ++;
            }
            //kontrola či ten jeden symbol je z množiny zadefinovaných neterminálov
            if (neterminaly.contains(p.getLavaStrana().get(0))) {
                ibaJedenSymbolNeterminal ++;
            } else {
                throw new Exception("Ak je na lavej strane jeden symbol, musi to byt neterminal. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
            }
            //kontrola či existuje aspoň jedno pravidlo pre začiatočný symbol
            if (p.getLavaStrana().get(0).equals(zaciatocnySymbol)) {
                jeStartSymbol++;
            }
            //kontrola či sa na pravej strane nachádza aspoň jeden symbol
            if (p.getPravaStrana().size() == 0){
                throw new Exception("Na pravej strane pravidla sa nenachadza ziaden symbol. Doplnte pravu stranu alebo pravidlo odstrante. Problem je s pravidlom: " + p.getLavaStrana() + "->");
            }
        }
        //ak nastane situácia, že neexistuje ani jedno pravidlo pre začiatočný symbol vyhodí sa výnimka
        if (jeStartSymbol == 0){
            throw new Exception("Neexistuje ani jedno pravidlo pre zaciatocny symbol.");
        }
        //kontrola či každé pravidlo spĺňa požadované podmienky, a teda, že na ľavej strane je iba jeden symbol, ktorý je neterminál a zároveň kontrola, že existuje aspoň jedno pravidlo pre začiatočný symbol
        if (pravidla.size() == ibaJedenSymbol & pravidla.size() == ibaJedenSymbolNeterminal & jeStartSymbol > 0){
            this.pravidla = pravidla;
        }
    }

    //metóda vráti množinu terminálov
    public HashSet<String> getTerminaly(){
        return terminaly;
    }

    //metóda vráti množinu neterminálov
    public HashSet<String> getNeterminaly(){
        return neterminaly;
    }

    //metóda vráti začiatočný symbol
    public String getZaciatocnySymbol(){
        return zaciatocnySymbol;
    }

    //metóda vráti množinu pravidiel
    public LinkedHashSet<Pravidlo> getPravidla(){
        return pravidla;
    }

    //metóda nastaví množinu terminálov
    public void setTerminaly(HashSet<String> terminaly){
        this.terminaly = terminaly;
    }

    //metóda nastaví množinu neterminálov
    public void setNeterminaly(HashSet<String> neterminaly){
        this.neterminaly = neterminaly;
    }

    //metóda nastaví začiatočný symbol
    public void setZaciatocnySymbol(String zaciatocnySymbol){
        this.zaciatocnySymbol = zaciatocnySymbol;
    }

    //metóda nastaví množinu pravidiel
    public void setPravidla(LinkedHashSet<Pravidlo> pravidla){
        this.pravidla = pravidla;
    }

    //metóda, ktorá vypíše terminály, neterminály, začiatočný symbol a pravidlá gramatiky
    public static void vypisGramatiku(Gramatika g){
        System.out.println("G = (T,N,S,P)");
        System.out.println();
        System.out.println("Terminály (T) :");
        System.out.println(g.getTerminaly());
        System.out.println();
        System.out.println("Neterminály (N) :");
        System.out.println(g.getNeterminaly());
        System.out.println();
        System.out.println("Začiatočný symbol (S) :");
        System.out.println(g.getZaciatocnySymbol());
        System.out.println();
        System.out.println("Pravidlá (P) :");
        for(Pravidlo p: g.getPravidla()){
            System.out.println(p.getLavaStrana() + " -> " + p.getPravaStrana());
        }
    }
}
