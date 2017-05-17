import exceptions.WrongCandidateNumberException;
import io.Resultat;
import io.Resultats;
import io.Urne;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by maxence on 17/05/17.
 */
public class ResultatsTest {

    static Urne r;

    @BeforeClass
    public static void initResultats() throws WrongCandidateNumberException {
        r = new Urne(5, 250);
        Resultats.getInstance().calculeResultats(r);
    }

    @Test
    public void consistanceResultats() {

        for(Resultat res : Resultats.getInstance().getMesResultats()) {
            assertEquals("Resultat par " + res.getNomMethode() + " n'a pas la bonne taille",
                    r.getNbCandidats(), res.getClassement().size());
        }

    }
}
