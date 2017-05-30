import io.Resultat;
import io.Urne;
import io.Resultats;
import methodes.Schulze;

/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Urne r = new Urne(8, 25000);
        //r.forceOne();

        Resultats.getInstance().calculeResultats(r);

        for(Resultat res : Resultats.getInstance().getMesResultats()) {
            System.out.println(res);
        }
    }
}
