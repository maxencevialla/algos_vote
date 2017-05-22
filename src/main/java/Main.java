import io.Urne;
import io.Resultat;
import io.Resultats;

import java.util.function.Function;

/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Urne r = new Urne(25, 1000);

        Function<Integer, Integer> function = x -> 2+x;

        Resultats.getInstance().calculeResultats(r);

        for(Resultat res : Resultats.getInstance().getMesResultats()) {
            System.out.println(res);
        }
    }
}
