import io.ResultArray;
import methodes.Borda;
import methodes.UninomDeuxTours;
import methodes.UninomUnTour;

/**
 * Created by maxence on 12/05/17.
 */
public class Main {

    public static void main(String[] args) {
        ResultArray r = new ResultArray(2, 4000);

        Borda.getInstance().printAndTimeResult(r);
        UninomUnTour.getInstance().printAndTimeResult(r);
        UninomDeuxTours.getInstance().printAndTimeResult(r);
    }
}
