import exceptions.EgaliteException;
import exceptions.WrongCandidateNumberException;
import io.Resultat;
import io.Resultats;
import io.Urne;
import methodes.UninomUnTour;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by maxence on 17/05/17.
 */
public class UninomUnTourTest {

    static Urne r;
    static Resultat res;

    @BeforeClass
    public static void initResultat() throws WrongCandidateNumberException, EgaliteException {
        r = new Urne(5, 2000);
        res = UninomUnTour.getInstance().getResult(r);
    }

    @Test
    public void formatDonnees() {
        assertEquals("Le classement doit avoir une longueur égale au nombre de candidats",
               r.getNbCandidats(), res.getClassement().size());
    }

}
