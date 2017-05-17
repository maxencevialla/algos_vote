import io.Urne;
import io.Resultat;
import io.Resultats;

/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Urne r = new Urne(5, 40);

        Resultats.getInstance().getAllResults(r);

        for(Resultat res : Resultats.getInstance().getMesResultats()) {
            System.out.println(res);
        }
    }
}
