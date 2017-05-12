import java.util.ArrayList;
import java.util.Map;

/**
 * Created by maxence on 12/05/17.
 */
public class Resultat {

    private int nbGagnant;
    private Map<Integer, Integer> classement;

    public Resultat() {}

    public Resultat(int nbGagnant) {
        this();
        this.nbGagnant = nbGagnant;
    }

    public Resultat(int nbGagnant, Map<Integer, Integer> classement) {
        this();
        this.classement = classement;
        this.nbGagnant = nbGagnant;
    }

    public int getNbGagnant() {
        return nbGagnant;
    }

    public void setNbGagnant(int nbGagnant) {
        this.nbGagnant = nbGagnant;
    }

    public Map<Integer, Integer> getClassement() {
        return classement;
    }

    public void setClassement(Map<Integer, Integer> classement) {
        this.classement = classement;
    }
}
