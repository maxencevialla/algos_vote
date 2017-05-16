package io;

import methodes.Borda;
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
    public void getAllResults(Urne r) {
        mesResultats.add(UninomUnTour.getInstance().getResult(r));
        mesResultats.add(UninomDeuxTours.getInstance().getResult(r));
        mesResultats.add(Borda.getInstance().getResult(r));
    }

    public List<Resultat> getMesResultats() {
        return mesResultats;
    }

    public boolean isBonVainqueurTrouve() {
        return bonVainqueurTrouve;
    }
}
