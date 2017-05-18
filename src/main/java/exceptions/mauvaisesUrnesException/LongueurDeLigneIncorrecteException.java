package exceptions.mauvaisesUrnesException;

/**
 * Created by maxence on 17/05/17.
 *
 * Exception renvoyée lorsque une ligne de l'urne donnée en entrée n'a pas la taille souhaitée
 */
public class LongueurDeLigneIncorrecteException extends MauvaiseUrneException {
    public LongueurDeLigneIncorrecteException(String message) {
        super(message);
    }
}
