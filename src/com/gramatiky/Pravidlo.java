package com.gramatiky;

import java.util.ArrayList;

public class Pravidlo {
    private ArrayList<String> pravaStrana; //pravá strana je reprezentovaná zoznamom, kde každý prvok je typu String
    private ArrayList<String> lavaStrana; //ľavá strana je reprezentovaná zoznamom, kde každý prvok je typu, nutnosť mať na ľavej strane iba jeden neterminál pri regulárnych a bezkontextových gramatikách je ošetrená v triede Gramatika

    //vytvorenie konštruktora Pravidlo s parametrami lavaStrana a pravaStrana
    public Pravidlo (ArrayList<String> lavaStrana, ArrayList<String> pravaStrana){
        this.lavaStrana = lavaStrana;
        this.pravaStrana = pravaStrana;
    }

    //metóda vráti pravú stranu pravidla
    public ArrayList<String> getPravaStrana(){
        return pravaStrana;
    }

    //metóda nastaví pravú stranu pravidla
    public void setPravaStrana(ArrayList<String> pravaStrana){
        this.pravaStrana = pravaStrana;
    }

    //metóda vráti ľavú stranu pravidla
    public ArrayList<String> getLavaStrana(){
        return lavaStrana;
    }

    //metóda nastaví ľavú stranu pravidla
    public void setLavaStrana(ArrayList<String> lavaStrana){
        this.lavaStrana = lavaStrana;
    }
}
