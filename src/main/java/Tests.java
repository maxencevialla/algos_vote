import arrays.ResultArray;
import arrays.UninomArray;
import tables.ResultMatrix;
import tables.UninomUnTour;

import java.util.ArrayList;

/**
 * Created by maxence on 10/05/17.
 */
public class Tests {
    public static void main(String[] args) {

        int nbVotants = 200000;
        int nbCandidats = 100;

        System.out.println("Lancement test avec " + nbVotants + " votants et " + nbCandidats + " candidats.");
        System.out.println();

        ResultMatrix m = new ResultMatrix(nbVotants, nbCandidats);

        ResultArray a = new ResultArray(nbVotants, nbCandidats);

        UninomArray.printResult(a);
        System.out.println("");
        UninomUnTour.printResult(m);
    }
}
