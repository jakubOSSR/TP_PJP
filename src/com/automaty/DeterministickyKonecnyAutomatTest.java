package com.automaty;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;


public class DeterministickyKonecnyAutomatTest {
    private static HashSet<String> stavyDKA;
    private static HashSet<String> symbolyDKA;
    private static HashSet<String> akceptujuciStavDKA;
    private static HashSet<String> zaciatocnyStavDKA;
    private static Tabulka prechodovaDKA;
    private static DeterministickyKonecnyAutomat dka;

    @BeforeAll
    static void naplnenieParametrovDKA() throws Exception{
        stavyDKA=new HashSet<String>(Arrays.asList("q0","q1","qf"));
        symbolyDKA=new HashSet<String>(Arrays.asList("0","1"));
        akceptujuciStavDKA=new HashSet<String>(Arrays.asList("qf"));
        zaciatocnyStavDKA=new HashSet<String>(Arrays.asList("q0"));
        prechodovaDKA=new Tabulka();
        prechodovaDKA.pridajRiadok("q0","0","q0");
        prechodovaDKA.pridajRiadok("q0","1","q1");
        prechodovaDKA.pridajRiadok("q1","0","qf");
        prechodovaDKA.pridajRiadok("q1","1","q1");
        prechodovaDKA.pridajRiadok("qf","0","qf");
        prechodovaDKA.pridajRiadok("qf","1","qf");

    }
    @Test
    public void overenieDKA(){
        try{
            dka = new DeterministickyKonecnyAutomat(stavyDKA,symbolyDKA,zaciatocnyStavDKA,akceptujuciStavDKA,prechodovaDKA);
        }
        catch (Exception e){
             fail(e.getMessage());
        }
    }
}
