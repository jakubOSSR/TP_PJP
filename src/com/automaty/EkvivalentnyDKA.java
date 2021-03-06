package com.automaty;

import java.util.*;

public class EkvivalentnyDKA {
    //deklarácia parametrov pre ekvivalentný DKA
    private HashSet<String> eStavy = new HashSet<String>();
    private HashSet<String> eSymboly;
    private HashSet<String> eAkceptujuceStavy = new HashSet<String>();
    private Tabulka prvyRiadok = new Tabulka();
    private Tabulka ePrechodovaDKA = new Tabulka();
    private Tabulka ePrechodovaDKAFinal = new Tabulka();
    private HashSet<String> eZaciatocnyStav;
    //pomocné
    private HashMap<String, HashMap<String, HashSet<String>>> prvyRiadokMapa = new HashMap<String, HashMap<String, HashSet<String>>>();
    private HashMap<String, HashMap<String, HashSet<String>>> tabulkaNKA;
    private HashMap<String, HashMap<String, HashSet<String>>> riadok1;
    private HashMap<String, HashMap<String, HashSet<String>>> vyslednaTab = new HashMap<String, HashMap<String, HashSet<String>>>();
    private HashMap<String, HashMap<String, HashSet<String>>> ePomocnaPrechodova = new HashMap<String, HashMap<String, HashSet<String>>>();
    private Tabulka pomocnaPrechodova;
    private Tabulka riadok;
    private HashSet<String> novyStav;
    private HashMap<String,HashSet<String>> uzaverStavy=new HashMap<String,HashSet<String>>();;
    private HashSet<String> uzaverMnozina;
    private HashMap<String,HashSet<String>> pravidlo;
    private HashSet<String> naslStavy;
    private HashSet<String> pomocna = new HashSet<String>();
    private String novyZacStav ="-";
    private boolean epseps = false;



    public EkvivalentnyDKA(NedeterministickyKonecnyAutomat nedeterministickyKA) {
        this.eSymboly = nedeterministickyKA.vratSymbolyAut(); // naplnenie symbolov eDKA pomocou get metody NKA triedy
        this.eZaciatocnyStav = nedeterministickyKA.vratZacStavNKA(); // určenie začiatočného stavu pomocou get metody NKA triedy
        pomocnaPrechodova = nedeterministickyKA.vratTabulkuNKA();  // uloženie taubulky typu Tabulka do pomocnej premennej
        tabulkaNKA = pomocnaPrechodova.vratPrechodovuTabulku();  //vrátenie tabulky vo formáte MAP
        uzaverEps(); //vykoná sa uzáver v prípade prechodu na eps
        pravidlo = new HashMap<String,HashSet<String>>();
        for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulkaNKA.entrySet()) {  //cyklus ktorý vloži do tabulky eDKA prvý riadok -> riadok kde sa na ľavej strane nachádza zač. stav
                if (m1.getKey().equals(eZaciatocnyStav.toString().replace("[", "")
                        .replace("]", ""))) {
                    for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                        if(!m2.getKey().equals("epsilon")) { //vynechá prechod na epsilon
                             naslStavy = new HashSet<String>();
                             naslStavy.addAll(m2.getValue());
                             for(Map.Entry<String,HashSet<String>> uzaver : uzaverStavy.entrySet()){ // cyklus, ktorý zabezpečí prehľadanie uzaverovej mnoziny, kt. bola vytvorená metodou uzaverEps
                                 if(naslStavy.contains(uzaver.getKey())){
                                       naslStavy.addAll(uzaverStavy.get(uzaver.getKey())); //ak nasl. stavy obsahujú stav, pri ktorom sa robil uzáver pridáme jeho hodnotu -> uzaverStavy je typu MAP<K,V>, kde k je stav a V je stav, resp. množina stavov pri prechode na eps
                                  }
                             }
                             pravidlo.put(m2.getKey(),naslStavy); //do pravidla pridíme k danému klúču - symbolu všetky nasledujuce stavy
                        }
                    }
                }
        }
        prvyRiadok.pridajRiadokPreZS(eZaciatocnyStav.toString().replace("[","").replace("]",""),pravidlo); //vytvoríme prvý riadok,
        prvyRiadokMapa=prvyRiadok.vratPrechodovuTabulku(); // pretransformujeme prvy riadok z typu Tabulka na typ Mapa
        for(Map.Entry<String,HashSet<String>> mm : uzaverStavy.entrySet()){ // cyklus, ktorý opäť prehľadá mapu uzaverStavy
            if(prvyRiadokMapa.containsKey(mm.getKey())){ // ak kľúč v prvom riadku vystupuje aj v mape uzaverStavy ako kľuč
                novyZacStav = mm.getValue().toString(); //vytvoríme nový začiatočný stav
                novyStav = new HashSet<String>();
                novyStav.addAll(mm.getValue()); //pridame nový stav
                eZaciatocnyStav.clear(); //vymažeme predchadzajúci začiatočný stav
                eZaciatocnyStav.add(novyZacStav.replace("[","").replace("]","").replace(", ","")); //pridáme nový začiatočný stav
            }
        }
        if(novyZacStav.equals("-")){ //ak nový začiatočný stav sa rovna "-", čo je hodnota zadaná pri deklarácií premennej
            ePrechodovaDKA=prvyRiadok;
            this.eStavy.add(eZaciatocnyStav.toString()); //pridanie začiatočného stavu do množiny stavov eDKA
        }
        else{ //ak nie
            if(pravidlo.isEmpty()){ //ak pravidlo je prázdne (tzn. riadok, kde začíname zač. stav. má definované lem prechody na eps
                epseps = true;
            }
            ePrechodovaDKA.pridajRiadokPreZS(novyZacStav,pravidlo); //do ePrechodovej tabulky pridáme nový riadok
        }

        ePomocnaPrechodova = ePrechodovaDKA.vratPrechodovuTabulku(); //vrátenie tabulky vo formáte MAP -> vráti tu tabulku, ktorú sme vyššie vytvárali v cykle
        vyslednaTab.putAll(ePomocnaPrechodova); //do vyslednej tabulky typu MAP uloží všetko z novej tabulky eDKA -> riadky, kde zač. stav je na ľavej stra e
        najdiNovyStav(ePomocnaPrechodova);  // volanie metody, ktorá pridá nový stav ( ako parateter berie novú tabulku eDKA)
        upravTabulku(vyslednaTab); // upravý výslednú tabulku -> vymaže medzery medzi stavmi, zátvorky
        System.out.print("_________Prechodova tabulka ekvivalentného DKA_________\n");
        //výpis tabulky, stavov...
        ePrechodovaDKAFinal.vypisTabulku();
        System.out.println("\n");
        System.out.println("STAVY eDKA "+eStavy);
        System.out.println("Zaciatocny STAV: "+eZaciatocnyStav);
        pomocna.addAll(nedeterministickyKA.vratAkcStavNKA());
        najdiAkcStavy(eStavy,pomocna); //volanie metody, kt. zabezpečí najdenie všetkych akceptujúcich stavov
        System.out.println("Akceptujuce stavy su: "+ eAkceptujuceStavy.toString().replace(", q","q"));


    }
    public void najdiAkcStavy(HashSet<String> s, HashSet<String> aNKA){ //metoda, ktorá nájde všetky akceptujúce stavy
        for(String str : s){
            for(String str1 : aNKA){
                if(str.contains(str1)){
                    this.eAkceptujuceStavy.add(str);
                }
            }
        }
    }
    public void najdiNovyStav(HashMap<String, HashMap<String, HashSet<String>>> tabulka) { //rekurzívna funkcia (konkrétne direktívna rekurzia), ktorá volá samú seba v prípade nesplenia podmienky, tzn. ak je podmienka false, ak je true -> metoda prejde na svoj koniec a automaticky sa preruší jej vykonávanie
        if(epseps == false) { //ak epseps je false
            novyStav = new HashSet<String>();
              for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulka.entrySet()) { // cyklus ktorý nájde nov= stavy
                  for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                      if (!eStavy.contains(m2.getValue().toString())) {
                          novyStav = new HashSet<String>();
                          novyStav.addAll(m2.getValue());
                      }
                  }
              }
          }
          else{ //ak epseps je true
              epseps = false;
          }
        if (!novyStav.isEmpty()) { //dôležitá podmienka, ktorá v prípade, že sa našli nové stavy vykoná telo IF, v ktorom zavolá znova funkciu najdiNovyStav, ak je množina noveStavy prázdna, preskočí sa telo IF a funkcia skončí
            riadok = new Tabulka();
            pravidlo= new HashMap<String,HashSet<String>>();
            //cyklus, ktorý zabezpečí prehľadanie NKA tabulky, hľadajú sa stavy, z ktorých sa skladá nový stav a naplnaju sa množiny pre prechody
            for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulkaNKA.entrySet()) {
                for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                    if(!m2.getKey().equals("epsilon")) { // uzaver sa rieši metodou uzaverEps -> v tomto cykle ignorujeme prechody na eps
                        if (novyStav.contains(m1.getKey())) { //ak nový stav obsahuje stav s NKA tabulky ( stav ktorý je na lavej strane)
                            if (pravidlo.containsKey(m2.getKey())) { //ak Mapa pravidlo už obsahuje kľuč(v tomto prípade symbol)
                                naslStavy = new HashSet<String>();
                                naslStavy.addAll(pravidlo.get(m2.getKey())); //do množiny nasl. stavov pridá všetko čo sa už nachádza pri danom symbole
                                naslStavy.addAll(m2.getValue()); //do množiny stavov ďalej pridá aj nové následujuce stavy
                                for(Map.Entry<String,HashSet<String>> uzaver : uzaverStavy.entrySet()){ // cyklus, ktorý zabezpečí prehľadanie uzaverovej mnoziny, kt. bola vytvorená metodou uzaverEps
                                    if(naslStavy.contains(uzaver.getKey())){
                                        naslStavy.addAll(uzaverStavy.get(uzaver.getKey())); //ak nasl. stavy obsahujú stav, pri ktorom sa robil uzáver pridáme jeho hodnotu -> uzaverStavy je typu MAP<K,V>, kde k je stav a V je stav, resp. množina stavov pri prechode na eps
                                    }
                                }
                                pravidlo.put(m2.getKey(), naslStavy); //pridame do Mapy pravidlo nový záznam
                            }
                            else //ak Mapa pravidlo neobsahuje symbol, pridá nový prechod
                            {
                                naslStavy=new HashSet<String>();
                                naslStavy.addAll(m2.getValue()); //do množina nasledujuci stav sa prida všetko čo je v množine Mapy NKA, kde je symbol m2.key
                                for(Map.Entry<String,HashSet<String>> uzaver : uzaverStavy.entrySet()){ // cyklus, ktorý zabezpečí prehľadanie uzaverovej mnoziny, kt. bola vytvorená metodou uzaverEps
                                    if(naslStavy.contains(uzaver.getKey())){
                                        naslStavy.addAll(uzaverStavy.get(uzaver.getKey())); //ak nasl. stavy obsahujú stav, pri ktorom sa robil uzáver pridáme jeho hodnotu -> uzaverStavy je typu MAP<K,V>, kde k je stav a V je stav, resp. množina stavov pri prechode na eps
                                    }
                                }
                                pravidlo.put(m2.getKey(),naslStavy); //prida do Mapy pravidlo nový záznam K-> symbol, V->množina nasledujucich stavov
                            }

                        }
                    }
                }
            }
            riadok.pridajRiadokPreZS(novyStav.toString(),pravidlo); //vytvoríme nový riadok s hore získaných hodnôt, riadok je typu Tabulka, kde voláme metodu pridajRiadokPreZS, kde metoda prijíma parametre String a HashMap
            this.eStavy.add(novyStav.toString()); //prida združený stav(nový stav) do množiny stavov eDKA
            riadok1 = riadok.vratPrechodovuTabulku(); // riadok transformuje na tabulku
            vyslednaTab.putAll(riadok1); //tabulku uloží do vyslednej tabulky
            najdiNovyStav(vyslednaTab); //funkcia zavolá samú seba s parametrom novej tabulky(tabulky doplnenej o nový stav)

        }
    }

    public void upravTabulku(HashMap<String, HashMap<String, HashSet<String>>> tabulka) {
        for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulka.entrySet()) { //cyklus v cykle, ktorý zabezpečí odstránenie medzier, čiarok a zátvorok
            for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                if (m1.getKey().length() > 2) {
                    this.ePrechodovaDKAFinal.pridajRiadok(m1.getKey().toString().replace(", ", "")
                                    .replace("[", "")
                                    .replace("]", ""),
                            m2.getKey(), m2.getValue().toString().replace(", ", "")
                                    .replace("[", "")
                                    .replace("]", ""));
                } else {
                    this.ePrechodovaDKAFinal.pridajRiadok(m1.getKey().toString(), m2.getKey(), m2.getValue().toString()
                            .replace(", ", "")
                            .replace("[", "")
                            .replace("]", ""));
                }
            }
        }
    }

    public void uzaverEps() {
        for (Map.Entry<String, HashMap<String, HashSet<String>>> m1 : tabulkaNKA.entrySet()) { //cyklus v cykle, ktorý zabezpečuje prehľadanie NKA tabulky z dôvodu hľadanie uzaveru
            for (Map.Entry<String, HashSet<String>> m2 : m1.getValue().entrySet()) {
                if (m2.getKey().equals("epsilon")) { //ak sa symbol rovná epsilon
                    uzaverMnozina = new HashSet<String>();
                    uzaverMnozina.addAll(m2.getValue()); //do uzaverMnozina pridáme naslStav pri prechode na epsilom
                    for (Map.Entry<String, HashMap<String, HashSet<String>>> p1 : tabulkaNKA.entrySet()) { //cyklus, ktorý overí či aj naslStav nemá prechod na epsilon
                        if (uzaverMnozina.contains(p1.getKey())) {
                            for (Map.Entry<String, HashSet<String>> p2 : p1.getValue().entrySet()) {
                                if(p2.getKey().equals("epsilon")){
                                    uzaverMnozina.addAll(p2.getValue());
                                }
                            }
                        }
                    }
                    uzaverMnozina.add(m1.getKey());
                    uzaverStavy.put(m1.getKey(),uzaverMnozina);
                }
            }
        }
    }

}


