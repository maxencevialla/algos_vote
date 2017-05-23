import io.Urne;
import io.Resultat;
import io.Resultats;
import methodes.ClassementParametrable;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Urne r = new Urne(5, 100);


        Resultats.getInstance().calculeResultats(r);

        /*for(Resultat res : Resultats.getInstance().getMesResultats()) {
            System.out.println(res);
        }*/
    }
}
