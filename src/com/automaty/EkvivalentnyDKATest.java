package com.automaty;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.fail;

public class EkvivalentnyDKATest {
    private static HashSet<String> stavyNKA,stavyDKA;
    private static HashSet<String> symbolyNKA,symbolyDKA;
    private static HashSet<String> akceptujuciStavNKA,akceptujuciStavDKA;
    private static HashSet<String> zaciatocnyStavNKA,zaciatocnyStavDKA;
    private static Tabulka prechodovaNKA,prechodovaDKA;
    private static NedeterministickyKonecnyAutomat nka;
    private static DeterministickyKonecnyAutomat dka;
    private static EkvivalentnyDKA eDka;

    @BeforeAll
    static void naplnenieParametrovDKAaNKA() throws Exception {
        stavyNKA = new HashSet<String>(Arrays.asList("q0","q1","q2","q3","qf","q4"));
        symbolyNKA = new HashSet<String>(Arrays.asList("0","1","2"));
        akceptujuciStavNKA = new HashSet<String>(Arrays.asList("q2"));
        zaciatocnyStavNKA = new HashSet<String>(Arrays.asList("q0"));

        prechodovaNKA = new Tabulka();

        prechodovaNKA.pridajRiadok("q0","0","q0","q1");
        prechodovaNKA.pridajRiadok("q0","1","q0","q2");
        prechodovaNKA.pridajRiadok("q1","0","q1","q3");
        prechodovaNKA.pridajRiadok("q1","1","q1");
        prechodovaNKA.pridajRiadok("q2","0","q2");
        prechodovaNKA.pridajRiadok("q2","1","q2","qf");
        prechodovaNKA.pridajRiadok("q3","0","q3");
        prechodovaNKA.pridajRiadok("q3","epsilon","qf");

        /*
        prechodovaNKA.pridajRiadok("q0","0","q0");
        prechodovaNKA.pridajRiadok("q0","epsilon","q1");
        prechodovaNKA.pridajRiadok("q1","epsilon","q2");
        prechodovaNKA.pridajRiadok("q1","1","q1");
        prechodovaNKA.pridajRiadok("q2","2","q2");

         */

    /*
        prechodovaNKA.pridajRiadok("q0","epsilon","q1","q2");
        prechodovaNKA.pridajRiadok("q1","0","q3");
        prechodovaNKA.pridajRiadok("q2","1","q3");
        prechodovaNKA.pridajRiadok("q3","1","q4");
*/


        nka = new NedeterministickyKonecnyAutomat(stavyNKA,symbolyNKA,zaciatocnyStavNKA,akceptujuciStavNKA,prechodovaNKA);

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

        dka = new DeterministickyKonecnyAutomat(stavyDKA,symbolyDKA,zaciatocnyStavDKA,akceptujuciStavDKA,prechodovaDKA);

    }
    @Test
    public void testEkvivalentnyDKAkNKA(){
       try {
           eDka = new EkvivalentnyDKA(nka);
       }
       catch (Exception e){
           e.printStackTrace();
           fail("Neočakávaná výnimka");
       }

    }


}
