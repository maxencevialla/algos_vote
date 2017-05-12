package tests;

import tests.arrays.ResultTestArray;
import tests.arrays.UninomArray;
import tests.tables.ResultMatrix;
import tests.tables.UninomUnTourTest;

/**
 * Created by maxence on 10/05/17.
 */
public class Tests {
    public static void main(String[] args) {

        int nbVotants;
        int nbCandidats = 10;

        int nbTests = 100;

        int avgTab = 0, avgArr = 0;

        //for(nbCandidats = 2 ; nbCandidats < 65 ; nbCandidats+=2) {
            for(nbVotants = 20000 ; nbVotants < 1000000 ; nbVotants+=20000) {
                System.out.println("Lancement  de test avec " + nbVotants + " votants et " + nbCandidats + " candidats.");
                for(int i = 0 ; i < nbTests ; i++) {
                    ResultMatrix m = new ResultMatrix(nbVotants, nbCandidats);
                    ResultTestArray a = new ResultTestArray(nbVotants, nbCandidats);

                    avgArr += UninomArray.printResult(a);
                    avgTab += UninomUnTourTest.printResult(m);
                }
                System.out.println("Moyenne pour tests.arrays : " + (avgArr/nbTests) + "ms.");
                System.out.println("Moyenne pour tableaux : " + (avgTab/nbTests) + "ms.");
            }
        //}
    }
}
