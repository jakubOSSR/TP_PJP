package com.gramatiky;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class Gramatika {
    private HashSet<String> terminaly;
    private HashSet<String> neterminaly;
    private String zaciatocnySymbol;
    private LinkedHashSet<Pravidlo> pravidla;

    public Gramatika(HashSet<String> terminaly, HashSet<String> neterminaly, String zaciatocnySymbol, LinkedHashSet<Pravidlo> pravidla) throws Exception{
        this.terminaly = terminaly;
        this.neterminaly = neterminaly;
        this.zaciatocnySymbol = zaciatocnySymbol;

        int ibaJedenSymbol = 0;
        int jeStartSymbol = 0;
        int ibaJedenSymbolNeterminal = 0;

        for (Pravidlo p: pravidla) {
            if (p.getLavaStrana().size() > 1) {
                throw new Exception("Lava strana musi obsahovat iba jeden symbol. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
            }
            else{
                ibaJedenSymbol ++;
            }
            if (neterminaly.contains(p.getLavaStrana().get(0))) {
                ibaJedenSymbolNeterminal ++;
            } else {
                throw new Exception("Ak je na lavej strane jeden symbol, musi to byt neterminal. Problem je s pravidlom: " + p.getLavaStrana() + "->" + p.getPravaStrana());
            }
            if (p.getLavaStrana().get(0).equals(zaciatocnySymbol)) {
                jeStartSymbol++;
            }
        }
        if (jeStartSymbol == 0){
            throw new Exception("Neexistuje ani jedno pravidlo pre zaciatocny symbol.");
        }
        if (pravidla.size() == ibaJedenSymbol & pravidla.size() == ibaJedenSymbolNeterminal & jeStartSymbol > 0){
            this.pravidla = pravidla;
        }
    }

    public HashSet<String> getTerminaly(){
        return terminaly;
    }

    public HashSet<String> getNeterminaly(){
        return neterminaly;
    }

    public String getZaciatocnySymbol(){
        return zaciatocnySymbol;
    }

    public LinkedHashSet<Pravidlo> getPravidla(){
        return pravidla;
    }

    public void setTerminaly(HashSet<String> terminaly){
        this.terminaly = terminaly;
    }

    public void setNeterminaly(HashSet<String> neterminaly){
        this.neterminaly = neterminaly;
    }

    public void setZaciatocnySymbol(String zaciatocnySymbol){
        this.zaciatocnySymbol = zaciatocnySymbol;
    }

    public void setPravidla(LinkedHashSet<Pravidlo> pravidla){
        this.pravidla = pravidla;
    }
}
