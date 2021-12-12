package com.automaty;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;


public class NedeterministickyKonecnyAutomatTest {
    private static HashSet<String> stavyNKA;
    private static HashSet<String> symbolyNKA;
    private static HashSet<String> akceptujuciStavNKA;
    private static HashSet<String> zaciatocnyStavNKA;
    private static Tabulka prechodovaNKA;
    private static NedeterministickyKonecnyAutomat nka;

    @BeforeAll
    static void naplnenieParametrovNKA(){

        stavyNKA=new HashSet<String>(Arrays.asList("q0","q1","q2","qf"));
        symbolyNKA=new HashSet<String>(Arrays.asList("0","1","+","-"));
        akceptujuciStavNKA=new HashSet<String>(Arrays.asList("qf"));
        zaciatocnyStavNKA=new HashSet<String>(Arrays.asList("q0"));
        prechodovaNKA=new Tabulka();
        prechodovaNKA.pridajRiadok("q0","epsilon","q1");
        prechodovaNKA.pridajRiadok("q0","+","q1");
        prechodovaNKA.pridajRiadok("q0","-","q1");
        prechodovaNKA.pridajRiadok("q1","0","qf");
        prechodovaNKA.pridajRiadok("q1","1","q2","qf");
        prechodovaNKA.pridajRiadok("q2","0","q2","qf");
        prechodovaNKA.pridajRiadok("q2","1","q2","qf");



    }
    @Test
    public void overenieNKA(){
       try{
           nka = new NedeterministickyKonecnyAutomat(stavyNKA,symbolyNKA,zaciatocnyStavNKA,akceptujuciStavNKA,prechodovaNKA);
       }
       catch (Exception e){
           fail(e.getMessage());
       }
    }
}
