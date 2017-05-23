import io.Urne;
import io.Resultats;

/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        Urne r = new Urne(5, 1000);

        Resultats.getInstance().calculeResultats(r);

        /*for(Resultat res : Resultats.getInstance().getMesResultats()) {
            System.out.println(res);
        }*/
    }
}
