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
    static void naplnenieParametrovDKA(){
        stavyDKA = new HashSet<String>(Arrays.asList("q0","q1q0","q2q0","q1q3qfq0","q1q2q0","q2qfq0","q1q2q3qfq0","q1q2qfq0"));
        symbolyDKA = new HashSet<String>(Arrays.asList("0","1"));
        akceptujuciStavDKA = new HashSet<String>(Arrays.asList("q1q3qfq0","q2qfq0","q1q2q3qfq0","q1q2qfq0"));
        zaciatocnyStavDKA = new HashSet<String>(Arrays.asList("q0"));

        prechodovaDKA = new Tabulka();
        prechodovaDKA.pridajRiadok("q0","0","q1q0");
        prechodovaDKA.pridajRiadok("q0","1","q2q0");
        prechodovaDKA.pridajRiadok("q1q0","0","q1q3qfq0");
        prechodovaDKA.pridajRiadok("q1q0","1","q1q2q0");
        prechodovaDKA.pridajRiadok("q2q0","0","q1q2q0");
        prechodovaDKA.pridajRiadok("q2q0","1","q2qfq0");
        prechodovaDKA.pridajRiadok("q1q3qfq0","0","q1q3qfq0");
        prechodovaDKA.pridajRiadok("q1q3qfq0","1","q1q2q0");
        prechodovaDKA.pridajRiadok("q1q2q0","0","q1q2q3qfq0");
        prechodovaDKA.pridajRiadok("q1q2q0","1","q1q2qfq0");
        prechodovaDKA.pridajRiadok("q2qfq0","0","q1q2q0");
        prechodovaDKA.pridajRiadok("q2qfq0","1","q2qfq0");
        prechodovaDKA.pridajRiadok("q1q2q3qfq0","0","q1q2q3qfq0");
        prechodovaDKA.pridajRiadok("q1q2q3qfq0","1","q1q2qfq0");
        prechodovaDKA.pridajRiadok("q1q2qfq0","0","q1q2q3qfq0");
        prechodovaDKA.pridajRiadok("q1q2qfq0","1","q1q2qfq0");

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
