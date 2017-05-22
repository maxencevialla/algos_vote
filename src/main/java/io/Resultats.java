package io;

import exceptions.WrongCandidateNumberException;
import methodes.Borda;
import methodes.Methode;
import methodes.UninomDeuxTours;
import methodes.UninomUnTour;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maxence on 16/05/17.
 */
public class Resultats {
    private List<Resultat> mesResultats;
    private boolean bonVainqueurTrouve;

    private static Resultats ourInstance = new Resultats();

    public static Resultats getInstance() {
        return ourInstance;
    }

    private Resultats() {
        mesResultats = new ArrayList<Resultat>();
        bonVainqueurTrouve = false;
    }

    /**
     * Lance chaque méthode de vote disponible sur le paramètre et ajoute le Résultat à l'attributs mesResultats
     *
     * @param r : Données de votes à traiter
     */
    public void calculeResultats(Urne r) throws WrongCandidateNumberException {
        Methode[] methodes = {
                UninomUnTour.getInstance(),
                UninomDeuxTours.getInstance(),
                Borda.getInstance()
        };

        for(Methode m : methodes) {
            mesResultats.add(m.getResult(r));
            //m.printAndTimeResult(r);
        }
    }

    public List<Resultat> getMesResultats() {
        return mesResultats;
    }

    public boolean isBonVainqueurTrouve() {
        return bonVainqueurTrouve;
    }
}
