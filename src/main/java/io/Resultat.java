package io;

import java.util.List;

/**
 * Created by maxence on 12/05/17.
 *
 *
 * Représente le résultat du sondage, qui sera utilisé par le serveur
 *
 */
public class Resultat {

    private List<Integer> classement; //Représente le classement des candidats, chacun étant représenté par son numéro dans l'entrée Urne
    private String nomMethode; //Méthode de vote utilisée pour réaliser ce classement

    public Resultat() {}

    public Resultat(List<Integer> classement, String nomMethode) {
        this();
        this.classement = classement;
        this.nomMethode = nomMethode;
    }

    @Override
    public String toString() {
        return "Numéro du vainqueur : " + this.classement.get(0) + " avec méthode " + this.nomMethode;
    }

    public List<Integer> getClassement() {
        return classement;
    }

    private void setClassement(List<Integer> classement) {
        this.classement = classement;
    }

    public String getNomMethode() {
        return nomMethode;
    }

    private void setNomMethode(String nomMethode) {
        this.nomMethode = nomMethode;
    }
}
