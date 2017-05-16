import io.ResultArray;
import methodes.Borda;
import org.junit.Test;

import java.util.Map;

/**
 * Created by maxence on 16/05/17.
 */
public class MainTest {

    @Test
    public void checkBordaTotalPoints() {

        int nbCandidats = 10, nbVotants = 10000;

        ResultArray r = new ResultArray(nbCandidats, nbVotants);

        //TODO se débrouiller pour arriver à faire intervenir des méthodes privées dans les tests...
        //Map<Integer, Integer> m = Borda.getInstance().getNbPoints(r);

        int totalNbPoints = nbVotants * (nbVotants - 1) * nbCandidats / 2;


    }
}
