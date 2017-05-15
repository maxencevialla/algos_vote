package methodes;

import io.ResultArray;
import io.Resultat;

/**
 * Created by maxence on 15/05/17.
 */
public abstract class Methode {

    public static Resultat getResult(ResultArray r) {
        return new Resultat(null, null);
    }
}
