import io.ResultArray;
import io.Resultat;
import methodes.Borda;
import methodes.UninomDeuxTours;
import methodes.UninomUnTour;

/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) {
        ResultArray r = new ResultArray(8, 4000);

        long start = System.currentTimeMillis();
        Resultat res1Tour = UninomUnTour.getResult(r);

        System.out.println("Résultat 1 tour calculé en " + (System.currentTimeMillis() - start) + "ms.");

        start = System.currentTimeMillis();
        Resultat res2Tours = UninomDeuxTours.getResult(r);

        System.out.println("Résultat 2 tours calculé en " + (System.currentTimeMillis() - start) + "ms.");

        start = System.currentTimeMillis();
        Resultat resBorda = Borda.getResult(r);

        System.out.println("Résultat Borda calculé en " + (System.currentTimeMillis() - start) + "ms.");

        System.out.println();

        for(int i = 0 ; i < res1Tour.getClassement().size() ; i++) {
            System.out.print(res1Tour.getClassement().get(i) + " ");
        }

        System.out.println();

        for(int i = 0 ; i < res2Tours.getClassement().size() ; i++) {
            System.out.print(res2Tours.getClassement().get(i) + " ");
        }

        System.out.println();

        for(int i = 0 ; i < resBorda.getClassement().size() ; i++) {
            System.out.print(resBorda.getClassement().get(i) + " ");
        }
    }
}
