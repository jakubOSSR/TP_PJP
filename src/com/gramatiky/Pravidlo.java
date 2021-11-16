package com.gramatiky;

import java.util.ArrayList;

public class Pravidlo {
    private ArrayList<String> pravaStrana;
    private ArrayList<String> lavaStrana;

    public Pravidlo (ArrayList<String> lavaStrana, ArrayList<String> pravaStrana){
        this.lavaStrana = lavaStrana;
        this.pravaStrana = pravaStrana;
    }

    public ArrayList<String> getPravaStrana(){
        return pravaStrana;
    }

    public void setPravaStrana(ArrayList<String> pravaStrana){
        this.pravaStrana = pravaStrana;
    }

    public ArrayList<String> getLavaStrana(){
        return lavaStrana;
    }

    public void setLavaStrana(ArrayList<String> lavaStrana){
        this.lavaStrana = lavaStrana;
    }
}
