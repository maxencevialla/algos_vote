package io;

import exceptions.EgaliteException;
import exceptions.WrongCandidateNumberException;
import methodes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * Created by maxence on 16/05/17.
 *
 * Contient une liste de Resultat représentant chacun le résultat d'un sondage selon une méthode de vote particulière
 */
public class Resultats {
    private List<Resultat> mesResultats;
    private boolean bonVainqueurTrouve;

    private static Resultats ourInstance = new Resultats();

    public static Resultats getInstance() {
        return ourInstance;
    }

    private Resultats() {
        this.mesResultats = new ArrayList<Resultat>();
        this.bonVainqueurTrouve = false;
    }

    /**
     * Lance chaque méthode de vote disponible sur l'urne d'entrée et ajoute le Résultat à l'attributs mesResultats
     *
     * @param r : Données de votes à traiter
     */
    public void calculeResultats(Urne r) throws WrongCandidateNumberException, EgaliteException {
        purgeResultats(); //Evite d'ajouter les résultats courants à des résultats pré-existants

        //Déclaration des fonctions utilisées pour la méthode de vote ClassementParamètrable

        Function<ArrayList<Byte>, Double> moyenne = x -> {
            double d = 0;
            for(Byte b : x) {
                d += b;
            }
            return d/x.size();
        };

        Function<ArrayList<Byte>, Double> mediane = x -> {
            Collections.sort(x);
            return x.get(x.size()/2).doubleValue();
        };

        /**
         * Problème actuel : on a très souvent une égalité même si le vote est biaisé lorsque le nombre de candidat est faible
         * Inhérent aux order functions ?
         */
        Function<ArrayList<Byte>, Double> orderFunction = x -> {
          Collections.sort(x);
          return x.get(x.size()/10).doubleValue();
        };

        Methode[] methodes = {
                UninomUnTour.getInstance(),
                UninomDeuxTours.getInstance(),
                Borda.getInstance(),
                VoteAlternatif.getInstance(),
                Schulze.getInstance(),
                new ClassementParametrable(moyenne, "moyenne"),
                new ClassementParametrable(mediane, "mediane"),
                new ClassementParametrable(orderFunction, "orderFunction")
        };

        for(Methode m : methodes) {
            mesResultats.add(m.getResult(r));
        }
    }

    /**
     * Permet de vider le contenu de la liste de résultats entre chaque calcul,
     * Il n'est pas nécessaire de réaliser la purge manuellement.
     */
    public void purgeResultats() {
        this.mesResultats = new ArrayList<>();
        this.bonVainqueurTrouve = false;
    }

    public List<Resultat> getMesResultats() {
        return mesResultats;
    }

    public boolean isBonVainqueurTrouve() {
        return bonVainqueurTrouve;
    }
}
