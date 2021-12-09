package com.NadbytocneSymboly;

import com.gramatiky.BezkontextovaGramatika;
import com.gramatiky.Pravidlo;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class OdstranenieNS {

    public static BezkontextovaGramatika odstranenieNS(BezkontextovaGramatika g) throws Exception {
        HashSet<String> terminalyRedukovana = new HashSet<>(); //Vytvorenie množiny terminálov redukovanej gramatiky
        HashSet<String> neterminalyRedukovana = new HashSet<>();//Vytvorenie množiny neterminálov redukovanej gramatiky
        String zaciatocnySymbolRedukovana = g.getZaciatocnySymbol();//Začiatocný symbol redukovanej gramatiky
        LinkedHashSet<Pravidlo> pravidlaRedukovana = new LinkedHashSet<>();//Vytvorenie množiny pravidiel redukovanej gramatiky

        //Hľadanie neterminálov, ktoré vieme pomocou pravidla priamo prepísať na terminalny reťazec alebo epsilon.
        for(Pravidlo p: g.getPravidla()){
            boolean jeNeterminal = false;
            for(int i = 0; i < p.getPravaStrana().size(); i++){
                //kontrola pravej strany pravidla, či obsahuje teminálny reťazec alebo epsilon
                if(g.getTerminaly().contains(p.getPravaStrana().get(i)) || p.getPravaStrana().get(i) == "epsilon"){

                }else{//na pravej strane pravidla sa nachádza neterminál
                    jeNeterminal = true;
                }
            }
            //ak sa na pravej strane nachádza terminálny retazec alebo epsilon, pridáme neterminál z ľavej strany pravidla do množiny neterminálov RG
            if(jeNeterminal == false){
                if(neterminalyRedukovana.contains(p.getLavaStrana().get(0))){
                }else{
                    neterminalyRedukovana.add(p.getLavaStrana().get(0));
                }
            }
        }
        //Hľadanie neterminálov, ktoré vieme prepísať na terminalny reťazec na i krokov odvodenia
        boolean zmena = true;
        while (zmena == true) {
            zmena = false;
            for (Pravidlo p : g.getPravidla()) {
                boolean jeOK = true;
                //kontrola, či je pravá strana pravidla tvorená len terminálmi a takými neterminálmi, o ktorých sme zistili, že ich vieme prepísat na terminálne retazce
                //ak nieje splnená podmienka jeOK=false;
                for (int i = 0; i < p.getPravaStrana().size(); i++) {
                    if (g.getTerminaly().contains(p.getPravaStrana().get(i)) || p.getPravaStrana().get(i) == "epsilon" || neterminalyRedukovana.contains(p.getPravaStrana().get(i))) {

                    } else {
                        jeOK = false;
                    }
                }
                //ak bola splnená podmienka, pridáme neterminál z ľavej strany pravidla do množiny neterminálov RG
                if (jeOK == true) {
                    if (neterminalyRedukovana.contains(p.getLavaStrana().get(0))) {

                    } else {
                        neterminalyRedukovana.add(p.getLavaStrana().get(0));
                        zmena = true;
                    }
                }
            }
        }
        //v prípade, že sa začiatočný symbol nenachádza medzi Nt vyskočí výnimka
        if(!neterminalyRedukovana.contains(zaciatocnySymbolRedukovana)){
            throw new Exception("Zaciatocny symbol sa nenachadza medzi Nt, jazyk generovaný gramatikou je prázdny a všetky jej symboly sú nadbytocné ");
        }
        //pridanie tých pravidiel do pravidiel RG, kde ľavá strana pravidla obsahuje neterminál, ktorý sa dá prepísať na terminalny reťazec(patrí do Nt)
        for(Pravidlo p: g.getPravidla()){
            if(neterminalyRedukovana.contains(p.getLavaStrana().get(0))){
                pravidlaRedukovana.add(p);
            }
        }
        //odstránenie tých pravidiel z pravidiel RG, kde pravá strana pravididla obsahuje neterminál, ktorý sa nedá prepísať na terminalny reťazec (nepatrí do Nt, jeOK=false)
        for(Pravidlo p: g.getPravidla()){
            boolean jeOK = true;
            for(int i = 0; i < p.getPravaStrana().size(); i++){
                if(neterminalyRedukovana.contains(p.getPravaStrana().get(i)) || p.getPravaStrana().get(i) == "epsilon" || g.getTerminaly().contains(p.getPravaStrana().get(i))){

                }else{
                    jeOK = false;
                }
            }
            if(jeOK == false){
                pravidlaRedukovana.remove(p);
            }
        }

        HashSet<String> netermpozac = new HashSet<>();//vytvorenie množiny dostupných neterminálov RG
        netermpozac.add(zaciatocnySymbolRedukovana);//pridanie zaciatocného symbolu do mnoziny dostupných neterminálov RG
        //Hľadanie dostupných symbolov zo zaciatocného symbola
        for(Pravidlo p: pravidlaRedukovana){
            if(p.getLavaStrana().get(0) == zaciatocnySymbolRedukovana){
                for(int i = 0; i < p.getPravaStrana().size(); i++){
                    //pridanie dostupných terminalov do mnoziny terminálov RG
                    if(g.getTerminaly().contains(p.getPravaStrana().get(i))){
                        if(terminalyRedukovana.contains(p.getPravaStrana().get(i))){
                        }else{
                            terminalyRedukovana.add(p.getPravaStrana().get(i));
                        }
                    }//pridanie dostupných neterminalov do mnoziny dostupných neterminálov RG
                    if(neterminalyRedukovana.contains(p.getPravaStrana().get(i))){
                        if(netermpozac.contains(p.getPravaStrana().get(i))){
                        }else{
                            netermpozac.add(p.getPravaStrana().get(i));
                        }
                    }
                }
            }
        }
        //Hľadanie dostupných symbolov gramatiky na i krokov odvodenia
        boolean zmena1 = true;
        while (zmena1 == true){
            zmena1 = false;
            for(Pravidlo p: pravidlaRedukovana){
                if(netermpozac.contains(p.getLavaStrana().get(0)) && p.getLavaStrana().get(0) != zaciatocnySymbolRedukovana){
                    for(int i = 0; i < p.getPravaStrana().size(); i++){
                        //pridanie dostupných terminalov do mnoziny terminálov RG
                        if(g.getTerminaly().contains(p.getPravaStrana().get(i))){
                            if(terminalyRedukovana.contains(p.getPravaStrana().get(i))){

                            }else{
                                terminalyRedukovana.add(p.getPravaStrana().get(i));
                                zmena1 = true;
                            }
                        }
                        //pridanie dostupných neterminalov do mnoziny dostupných neterminálov RG
                        if(neterminalyRedukovana.contains(p.getPravaStrana().get(i))){
                            if(netermpozac.contains(p.getPravaStrana().get(i))){

                            }else{
                                netermpozac.add(p.getPravaStrana().get(i));
                                zmena1 = true;
                            }
                        }
                    }
                }
            }

        }

        neterminalyRedukovana.clear();//vyprázdnenie množiny neterminálov RG
        neterminalyRedukovana = netermpozac;//naplnenie množiny neterminálov RG množinou dostupných neterminálov
        //Odstránenie pravidiel RG, kde na ľavej strane pravidla je nedostupný neterminál
        for(Pravidlo p: g.getPravidla()){
            if(neterminalyRedukovana.contains(p.getLavaStrana().get(0))){

            }else{
                pravidlaRedukovana.remove(p);
            }
        }

        System.out.println(" Terminaly redukovanej gramatiky: " + terminalyRedukovana);//Výpis terminálov redukovanej gramatiky
        System.out.println(" Neterminaly redukovanej gramatiky: " + neterminalyRedukovana);//Výpis neterminálov redukovanej gramatiky
        System.out.println(" Zaciatocny symbol redukovanej gramatiky: " + zaciatocnySymbolRedukovana);//Výpis začiatočného symbolu redukovanej gramatiky
        System.out.println(" Pravidla redukovanej gramatiky: ");
        for(Pravidlo p: pravidlaRedukovana){
            System.out.println(p.getLavaStrana().toString() + " => " + p.getPravaStrana().toString());//Výpis pravidiel redukovanej gramatiky
        }
        BezkontextovaGramatika redukovanaGramatika = new BezkontextovaGramatika(terminalyRedukovana, neterminalyRedukovana, zaciatocnySymbolRedukovana, pravidlaRedukovana);
        return redukovanaGramatika;
    }

}
