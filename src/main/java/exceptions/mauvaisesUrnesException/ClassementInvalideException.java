package exceptions.mauvaisesUrnesException;

/**
 * Created by maxence on 17/05/17.
 *
 * Exception renvoyée lorsque une ligne de l'urne ne contient pas un classement valide
 */
public class ClassementInvalideException extends MauvaiseUrneException {
    public ClassementInvalideException(String message) {
        super(message);
    }
}
