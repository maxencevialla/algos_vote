import io.Resultat;
import io.Urne;
import io.Resultats;

import java.util.Arrays;
import java.util.logging.LogManager;

/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        //LogManager.getLogManager().reset();

        Urne r = new Urne(15, 2500);
        r.setNumCandidatPref((byte)5);
        //r.forceOne();


        Resultats.getInstance().calculeResultats(r);

        for(Resultat res : Resultats.getInstance().getMesResultats()) {
            System.out.println(res);
            System.out.println("Classement complet avec " + res.getNomMethode()
                    + " : " + Arrays.toString(res.getClassement().toArray()));
            System.out.println();
        }
    }
}
