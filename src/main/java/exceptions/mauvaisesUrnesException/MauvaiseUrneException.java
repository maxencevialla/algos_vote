package exceptions.mauvaisesUrnesException;

/**
 * Created by maxence on 16/05/17.
 *
 * Exception générale regroupant toutes les exceptions liées au mauvais format de l'urne donnée en entrée.
 */
public class MauvaiseUrneException extends Exception {
    public MauvaiseUrneException(String message) {
        super(message);
    }
}
