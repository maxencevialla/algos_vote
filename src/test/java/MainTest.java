import io.Urne;
import org.junit.Test;

/**
 * Created by maxence on 16/05/17.
 */
public class MainTest {

    @Test
    public void checkBordaTotalPoints() {

        int nbCandidats = 10, nbVotants = 10000;

        Urne r = new Urne(nbCandidats, nbVotants);

        //TODO se débrouiller pour arriver à faire intervenir des méthodes privées dans les tests...
        //Map<Integer, Integer> m = Borda.getInstance().getNbPoints(r);

        int totalNbPoints = nbVotants * (nbVotants - 1) * nbCandidats / 2;


    }
}
