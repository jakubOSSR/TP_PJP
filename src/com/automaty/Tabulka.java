package com.automaty;

import java.util.*;


public class Tabulka {
    //deklarácia - pomocné premenné
    private HashMap<String, HashMap<String,HashSet<String>>> prechodovaTabulka = new HashMap<String, HashMap<String,HashSet<String>>>();
    private HashMap<String,HashSet<String>> pravidla;
    private HashSet<String> nasledujuciStav;
    private List<Integer> pom = new ArrayList<Integer>();
    private String [] pomocna;
    private HashSet<String> overStavy = new HashSet<String>();
    private HashSet<String> overSym = new HashSet<String>();
    private HashSet<String> kontrolaZaciatocnehoStavu = new HashSet<String>();
    private HashSet<String> kontrolaAkceptujucehoStavu = new HashSet<String>();
    private HashSet<String> nasledujuciStav1;
    //metoda pridaj riadok zabezpečuje pridanie záznamu do prechodovej tabulky,
    // tabulka je reprezentovaná vo formate Map<K,MAP<K1,V>>...táto metóda služi na naplnenie tejto tabuľky
    public void pridajRiadok(String stav, String symbol, String... stav123){  //prijíma parametre stav, symbol a stav123 (varargs) - formát tabulky STAV--sybol-->naslStav/naslStavy
        nasledujuciStav = new HashSet<String>();
        pomocna = new String[stav123.length]; //definovanie veľkosti poľa na základe toho, koľko stringov prišlo formou varargs
        pomocna = stav123;  //pomocná premenná, do ktorej sú uložené stringy stav123, kvôli naslednému prehľadávaniu
        for(int i=0;i< pomocna.length;i++){
            nasledujuciStav.add(pomocna[i]); //reťazce, resp. nasledujuce stavy ukladá do množiny nasledujucich stavov
            overStavy.add(pomocna[i]); //automaticky ukladá stavy aj do množiny overStavy, ktorá neskôr slúži na zistovanie toho či sa v množine statvov DKA/NKA nachádzajú všetky stavy, ktoré su v tabuľke
            kontrolaAkceptujucehoStavu.add(pomocna[i]); //ukladá nasedujuce stavy do množiny, kvôli neskoršemu overovaniu toho, či sa v prechodovej tabuľke DKA/NKA nachádza akceprujúci stav na pravej strane
        }
        pravidla = new HashMap<String,HashSet<String>>();

        pravidla.put(symbol, nasledujuciStav); // vnútorná mapa - ukladá symbol a nasledujuce stavy

        pom.add(stav123.length); // pomocná premenná ktorá ukladá veľkosť nasledujucich stavov ( či sú tam 2 stavy alebo 1 stav)
        overStavy.add(stav); //do mnoziny over stavy pridá aktuálny stav
        overSym.add(symbol); // do množiny overSym pridá symbol, táto množina sa neskôr používa na overenie symbolov v tabuľke
        kontrolaZaciatocnehoStavu.add(stav); // množina, do ktorej sa ukladajú stavy na pravej strane tabuľky...neskôr sa pomocou nej kontroluje to, či sa začiatočný stav nachádza na pravej strane tabulky.

        if(prechodovaTabulka.containsKey(stav)) { //ak prechodova tabulka ( mapa v hore uvedenom formáte) obsahuje už daný stav
            prechodovaTabulka.get(stav).put(symbol,nasledujuciStav); // vrati stav(kľuč) a uloží doň novú hodnotu
        }
        else{ //ak nie vloží nový stav aj s hodnotou
            prechodovaTabulka.put(stav, pravidla);
        }
    }
    public void pridajRiadokPreZS(String aktStav, HashMap<String,HashSet<String>> mnozina){//metoda na pridanie záznamu do mapy - oproti vyššie uvedenej sa líši v počte a type prijímaných parametrov
            prechodovaTabulka.put(aktStav,mnozina);

    }

    public String[] overStavyVtabulke(){ //metóda, ktorá vráti množinu stavov, ktoré sa nachádzajú v tabuľke
        return overStavy.toArray(new String[overStavy.size()]);

    }
    public void vypisTabulku(){ // metoda, ktora po zavolaní vypíše tabuľku
        for (Map.Entry<String,HashMap<String,HashSet<String>>> s : prechodovaTabulka.entrySet()) {
            System.out.println(s.getKey()+" : "+s.getValue().toString());
        }
    }
    public int overAutomat(){ //metóda, ktorá vráti max. číslo z množiny - použitie: overenie DKA z pohľadu prechodu na jeden symbol do viac stavov
        return Collections.max(pom);
    }
    public String[] vratSymbolyVTabulke(){// metoda, ktorá vráti množinu symbolov nachádzajúcich sa v tabuľke
        return overSym.toArray(new String[overSym.size()]);
    }
    public String[] vratZaciatocnyStav(){ //metoda ktorá vráti všetky stavy na ľavej strane tabuľky - použitie: overovanie začiatočného stavu
        return kontrolaZaciatocnehoStavu.toArray(new String[kontrolaZaciatocnehoStavu.size()]);
    }
    public String[] vratAkceptujuceStavy(){ //metoda, ktorá várti všetky stavy nachádzajuce sa na pravej strane tabuľky - použitie: overovanie akc. stavu
        return kontrolaAkceptujucehoStavu.toArray(new String[kontrolaAkceptujucehoStavu.size()]);
    }
    public HashMap<String, HashMap<String, HashSet<String>>> vratPrechodovuTabulku() { // metoda, ktorá vráti prechodovu tabuľku
        return prechodovaTabulka;
    }

}
