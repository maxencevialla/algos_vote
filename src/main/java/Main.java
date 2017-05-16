import io.ResultArray;
import io.Resultat;
import io.Resultats;
import methodes.Borda;
import methodes.UninomDeuxTours;
import methodes.UninomUnTour;

import java.util.List;

/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) {
        ResultArray r = new ResultArray(5, 40);

//        Borda.getInstance().printAndTimeResult(r);
//        UninomUnTour.getInstance().printAndTimeResult(r);
//        UninomDeuxTours.getInstance().printAndTimeResult(r);

        Resultats.getInstance().getAllResults(r);

        for(Resultat res : Resultats.getInstance().getMesResultats()) {
            System.out.println(res);
        }
    }
}
